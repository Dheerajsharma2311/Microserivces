package Com.java.encrypt_decrypt;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

 

public class DigitalSigned {
    public static void main(String[] args) throws Exception {
         // Read private key from file
        String privateKeyPath = "private_key.pem";
        String privateKeyContent = new String(Files.readAllBytes(Paths.get(privateKeyPath)), StandardCharsets.UTF_8);

 

        // Extract key content from private key file
        String privateKeyPEM = privateKeyContent
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s+", "");

 

        // Decode Base64 encoded private key
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyPEM);

 

        // Generate PrivateKey from decoded key bytes
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

 

        // Load your XML data to be signed here
        String xmlFilePath = "A2A.xml";
        String xmlContent = new String(Files.readAllBytes(Paths.get(xmlFilePath)), StandardCharsets.UTF_8);

 

        // Generate the digital signature
        byte[] dataBytes = xmlContent.getBytes(StandardCharsets.UTF_8);
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(dataBytes);
        byte[] signatureBytes = signature.sign();
        String signatureBase64 = Base64.getEncoder().encodeToString(signatureBytes);

 

        // Calculate the digest value
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] digestBytes = digest.digest(dataBytes);
        String digenstValue = Base64.getEncoder().encodeToString(digestBytes);

 

        // Build the signed XML content
        String signedXmlContent = "<faxml>\n" +
                "  <creditlist>\n" +
                xmlContent +
                "  </creditlist>\n" +
                "  <ds:Signature xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\">\n" +
                "    <ds:SignedInfo>\n" +
                "      <ds:CanonicalizationMethod Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/>\n" +
                "      <ds:SignatureMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#rsa-sha256\"/>\n" +
                "      <ds:Reference URI=\"#faxml-0-8dc5d12711c6cc0d3c0d33aa1d062119\">\n" +
                "        <ds:Transforms>\n" +
                "          <ds:Transform Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/>\n" +
                "        </ds:Transforms>\n" +
                "        <ds:DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\"/>\n" +
                "        <ds:DigestValue>"+digenstValue+"</ds:DigestValue>\n" +
                "      </ds:Reference>\n" +
                "    </ds:SignedInfo>\n" +
                "    <ds:SignatureValue>" + signatureBase64 + "</ds:SignatureValue>\n" +
                "    <ds:KeyInfo>\n" +
                "      <ds:X509Data>\n" +
                "        <ds:X509Certificate>MIIC5D...DyHy</ds:X509Certificate>\n" +
                "      </ds:X509Data>\n" +
                "    </ds:KeyInfo>\n" +
                "  </ds:Signature>\n" +
                "</faxml>\n";

 

        // Save the signed XML to a file
        String signedXmlFilePath = "Digital_Signed_A2A.xml";
        Files.write(Paths.get(signedXmlFilePath), signedXmlContent.getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE);

 

        System.out.println("Digital Signature: " + signatureBase64);
        System.out.println("Signed XML saved to: " + signedXmlFilePath);
    }
}