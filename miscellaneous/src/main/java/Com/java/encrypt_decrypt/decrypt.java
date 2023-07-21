package Com.java.encrypt_decrypt;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class decrypt {

    public static void main(String[] args) {
        String encryptedFilePath = "Encrypted_DigitalSigned_A2A.pem";
        String decryptedOutputFile = "Decrypted_DigitalSigned_A2A.xml";
        String symmetricKeyFile = "symmetric_key.pem"; // File path of the symmetric key file

        try {
            // Read the encrypted data from the PEM file
            String pemData = readPemFile(encryptedFilePath);

            // Read the symmetric key from the file
            String base64Key = readSymmetricKey(symmetricKeyFile);

            // Decode the base64 encoded key to raw bytes
            byte[] secretKey = Base64.getDecoder().decode(base64Key);

            // Decrypt the encrypted data
            String decryptedXmlData = decrypt(pemData, secretKey);

            // Save the decrypted data to the output file
            saveDecryptedXmlToFile(decryptedOutputFile, decryptedXmlData);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    // Decryption method using AES algorithm
    private static String decrypt(String pemData, byte[] secretKey) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(secretKey, "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec);

            byte[] encryptedData = Base64.getDecoder().decode(extractBase64DataFromPem(pemData));
            byte[] decryptedData = cipher.doFinal(encryptedData);

            return new String(decryptedData, StandardCharsets.UTF_8);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
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

    // Helper method to read the symmetric key from a file
    private static String readSymmetricKey(String filePath) {
        try {
            Path path = Paths.get(filePath);
            String pemData = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
            return extractBase64KeyFromPem(pemData);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    // Helper method to extract Base64 encoded key from PEM format
    private static String extractBase64KeyFromPem(String pemData) {
        int startIndex = pemData.indexOf("-----BEGIN SYMMETRIC KEY-----") + "-----BEGIN SYMMETRIC KEY-----".length();
        int endIndex = pemData.indexOf("-----END SYMMETRIC KEY-----");
        return pemData.substring(startIndex, endIndex).replaceAll("\\s", "");
    }

    // Helper method to extract Base64 encoded data from PEM format
    private static String extractBase64DataFromPem(String pemData) {
        int startIndex = pemData.indexOf("-----BEGIN ENCRYPTED DATA-----") + "-----BEGIN ENCRYPTED DATA-----".length();
        int endIndex = pemData.indexOf("-----END ENCRYPTED DATA-----");
        return pemData.substring(startIndex, endIndex).replaceAll("\\s", "");
    }

    // Helper method to save the decrypted XML data to a file
    private static void saveDecryptedXmlToFile(String filePath, String decryptedXmlData) {
        try {
            Files.write(Paths.get(filePath), decryptedXmlData.getBytes(StandardCharsets.UTF_8));
            System.out.println("Decrypted data saved to file: " + filePath);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
