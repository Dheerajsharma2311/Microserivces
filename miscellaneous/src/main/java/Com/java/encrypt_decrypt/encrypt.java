package Com.java.encrypt_decrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;

//Generate symmetric key and encrypt XML data
public class encrypt {
    public static void main(String[] args) {
        try {
            // Generate and save the symmetric key
            String symmetricKeyFilePath = "symmetric_key.pem";
            String symmetricKey = generateAndSaveSymmetricKey(symmetricKeyFilePath);
            System.out.println("Symmetric key saved to " + symmetricKeyFilePath);

            // Use the symmetric key for encryption
            String xmlFilePath = "Digital_Signed_A2A.xml";
            String outputFile = "Encrypted_DigitalSigned_A2A.pem";

            // Read the XML file content into a string
            String xmlData = readXmlFile(xmlFilePath);

            // Decode the base64 encoded key to raw bytes
            byte[] secretKey = Base64.getDecoder().decode(symmetricKey);

            // Encrypt the XML data
            byte[] encryptedData = encrypt(xmlData, secretKey);

            // Save the encrypted data in PEM format to the output file
            saveEncryptedDataToPemFile(outputFile, encryptedData);
        } catch (Exception exception) {
        	exception.printStackTrace();
        }
    }

    // Encryption method using AES algorithm
    private static byte[] encrypt(String data, byte[] secretKey) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(secretKey, "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);

            return cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    // Helper method to read XML file content
    private static String readXmlFile(String filePath) {
        try {
            Path path = Paths.get(filePath);
            return new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        } catch (Exception exception) {
        	exception.printStackTrace();
            return null;
        }
    }

    // Helper method to generate and save the symmetric key to PEM file
    private static String generateAndSaveSymmetricKey(String filePath) {
        try {
            int numBytes = 32; // 32 bytes = 256 bits

            byte[] randomBytes = generateRandomBytes(numBytes);
            String randomString = encodeBytesToBase64(randomBytes);

            saveKeyToPemFile(filePath, randomString);

            return randomString;
        } catch (Exception exception) {
        	exception.printStackTrace();
            return null;
        }
    }

    private static byte[] generateRandomBytes(int numBytes) {
        try {
            SecureRandom secureRandom = new SecureRandom();
            byte[] randomBytes = new byte[numBytes];
            secureRandom.nextBytes(randomBytes);
            return randomBytes;
        } catch (Exception exception) {
        	exception.printStackTrace();
            return null;
        }
    }

    private static String encodeBytesToBase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    private static void saveKeyToPemFile(String filePath, String key) {
       try {
            String pemString = "-----BEGIN SYMMETRIC KEY-----\n" + key + "\n-----END SYMMETRIC KEY-----";
            try (FileWriter fileWriter = new FileWriter(filePath)) {
                fileWriter.write(pemString);
            } catch (IOException exception) {
            	exception.printStackTrace();
            }
        } catch (Exception exception) {
        	exception.printStackTrace();
        }
    }

    // Helper method to save the encrypted data in PEM format to a file
    private static void saveEncryptedDataToPemFile(String filePath, byte[] encryptedData) {
        try {
            String base64Encoded = Base64.getEncoder().encodeToString(encryptedData);
            String pemContent = "-----BEGIN ENCRYPTED DATA-----\n" + base64Encoded + "\n-----END ENCRYPTED DATA-----";
            Files.write(Paths.get(filePath), pemContent.getBytes(StandardCharsets.UTF_8));
            System.out.println("Encrypted data saved to PEM file: " + filePath);
        } catch (Exception exception) {
        	exception.printStackTrace();
        }
    }
}
