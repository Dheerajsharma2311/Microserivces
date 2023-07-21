package Com.java.encrypt_decrypt;

import java.io.FileWriter;
import java.security.*;

import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.Base64;

public class Keys {

    private static final String ASYMMETRIC_ALGORITHM = "RSA";

    public static void main(String[] args) throws Exception {
        KeyPair keyPair = generateKeyPair();
        System.out.println("Key pair generated successfully.");

        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        savePublicKey(publicKey, "public_key.pem");
        savePrivateKey(privateKey, "private_key.pem");

        System.out.println("Keys saved in PEM format.");
    }

    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ASYMMETRIC_ALGORITHM);
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    public static void savePublicKey(PublicKey publicKey, String publicKeyFile) throws Exception {
        byte[] publicKeyBytes = publicKey.getEncoded();
        String base64Key = Base64.getEncoder().encodeToString(publicKeyBytes);
        String pemFormat = "-----BEGIN PUBLIC KEY-----\n" + base64Key + "\n-----END PUBLIC KEY-----";
        FileWriter fileWriter = new FileWriter(publicKeyFile);
        fileWriter.write(pemFormat);
        fileWriter.close();
    }

    public static void savePrivateKey(PrivateKey privateKey, String privateKeyFile) throws Exception {
        byte[] privateKeyBytes = privateKey.getEncoded();
        String base64Key = Base64.getEncoder().encodeToString(privateKeyBytes);
        String pemFormat = "-----BEGIN PRIVATE KEY-----\n" + base64Key + "\n-----END PRIVATE KEY-----";
        FileWriter fileWriter = new FileWriter(privateKeyFile);
        fileWriter.write(pemFormat);
        fileWriter.close();
    }
}
