package YTest;



import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import java.util.Base64;

public class DataDecryption {

    public static String encryptData(String data, String publicKeyFile) {
        try {
            // Read the public key from the PEM file
            String publicKeyData = readPemFile(publicKeyFile);

            // Remove whitespace and newline characters from the public key data
            publicKeyData = publicKeyData.replaceAll("\\s", "");

            // Decode the public key bytes
            byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyData);

            // Load the public key
            PublicKey publicKey = loadPublicKey(publicKeyBytes);

            // Encrypt the data
            byte[] encryptedBytes = encrypt(data.getBytes(StandardCharsets.UTF_8), publicKey);

            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static String decryptData(String encryptedData, String privateKeyFile) {
        try {
            // Read the private key from the PEM file
            String privateKeyData = readPemFile(privateKeyFile);

            // Remove whitespace and newline characters from the private key data
            privateKeyData = privateKeyData.replaceAll("\\s", "");

            // Decode the private key bytes
            byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyData);

            // Load the private key
            PrivateKey privateKey = loadPrivateKey(privateKeyBytes);

            // Decrypt the data
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);
            byte[] decryptedBytes = decrypt(encryptedBytes, privateKey);

            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    // Encrypt the data using RSA algorithm and the provided public key
    private static byte[] encrypt(byte[] data, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            return cipher.doFinal(data);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    // Decrypt the data using RSA algorithm and the provided private key
    private static byte[] decrypt(byte[] encryptedData, PrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            return cipher.doFinal(encryptedData);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    // Load the public key from the provided key bytes
    private static PublicKey loadPublicKey(byte[] publicKeyBytes) throws Exception {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    // Load the private key from the provided key bytes
    private static PrivateKey loadPrivateKey(byte[] privateKeyBytes) throws Exception {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    // Helper method to read PEM file content
    private static String readPemFile(String filePath) {
        try {
            Path path = Paths.get(filePath);
            return new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
