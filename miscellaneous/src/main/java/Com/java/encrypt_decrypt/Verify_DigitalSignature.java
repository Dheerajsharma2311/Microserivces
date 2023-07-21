package Com.java.encrypt_decrypt;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class Verify_DigitalSignature {
    public static void main(String[] args) throws Exception {
        // Read public key from file
        String publicKeyPath = "public_key.pem";
        String publicKeyContent = new String(Files.readAllBytes(Paths.get(publicKeyPath)), StandardCharsets.UTF_8);

        // Extract key content from public key file
        String publicKeyPEM = publicKeyContent
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s+", "");

        // Decode Base64 encoded public key
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyPEM);

        // Generate PublicKey from decoded key bytes
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);

        // Load the signed XML content
        String signedXmlFilePath = "Decrypted_DigitalSigned_A2A.xml";
        String signedXmlContent = new String(Files.readAllBytes(Paths.get(signedXmlFilePath)), StandardCharsets.UTF_8);

        // Extract the signature value from the signed XML
        String signatureBase64 = signedXmlContent.substring(signedXmlContent.indexOf("<ds:SignatureValue>") + "<ds:SignatureValue>".length(), signedXmlContent.indexOf("</ds:SignatureValue>"));
        byte[] signatureBytes = Base64.getDecoder().decode(signatureBase64);

        // Remove the signature from the signed XML
        String xmlContent = signedXmlContent.replaceAll("<ds:SignatureValue>[^<]*</ds:SignatureValue>", "");

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] digestBytes = digest.digest(xmlContent.getBytes(StandardCharsets.UTF_8));
        String digenstValue = Base64.getEncoder().encodeToString(digestBytes);

        // Verify the signature
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(xmlContent.getBytes(StandardCharsets.UTF_8));
        boolean isSignatureValid = signature.verify(signatureBytes);

        System.out.println("Digital Signature Verification: " + isSignatureValid);
    }
}
