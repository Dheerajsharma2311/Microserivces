package com.hdfc.decrypt.method;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class SymmetrickeyDecryption {

    public void decryptAndSaveSymmetricKey() {
        String privateKeyFile = "private_key.pem";
        String encryptedKeyFile = "encrypted_symmetric_key.pem";
        String decryptedKeyFile = "decrypted_symmetric_key.pem";

        try {
            // Load the private key from the PEM file
            byte[] privateKeyBytes = loadPrivateKeyBytes(privateKeyFile);
            PrivateKey privateKey = loadPrivateKey(privateKeyBytes);

            // Load the encrypted key from the PEM file
            byte[] encryptedKeyBytes = loadEncryptedKeyBytes(encryptedKeyFile);

            // Decrypt the symmetric key using the private key
            byte[] decryptedKeyBytes = decryptSymmetricKey(encryptedKeyBytes, privateKey);

            // Save the decrypted key in PEM format
            saveDecryptedKeyToPEM(decryptedKeyBytes, decryptedKeyFile);

            System.out.println("Symmetric key decrypted and saved successfully.");
        } catch (Exception exception) {
            System.out.println("An error occurred: " + exception.getMessage());
            exception.printStackTrace();
        }
    }

    public byte[] loadPrivateKeyBytes(String privateKeyFile) {
        StringBuilder privateKeyPem = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(privateKeyFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("-----BEGIN PRIVATE KEY-----") || line.contains("-----END PRIVATE KEY-----")) {
                    continue;
                }
                privateKeyPem.append(line);
            }
        } catch (Exception exception) {
            System.out.println("An error occurred while loading the private key: " + exception.getMessage());
            exception.printStackTrace();
        }
        return Base64.getDecoder().decode(privateKeyPem.toString());
    }

    public PrivateKey loadPrivateKey(byte[] privateKeyBytes) {
        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(keySpec);
        } catch (Exception exception) {
            System.out.println("An error occurred while loading the private key: " + exception.getMessage());
            exception.printStackTrace();
        }
        return null;
    }

    public byte[] loadEncryptedKeyBytes(String encryptedKeyFile) {
        StringBuilder encryptedKeyPem = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(encryptedKeyFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("-----BEGIN SYMMETRIC KEY-----")
                        || line.contains("-----END SYMMETRIC KEY-----")) {
                    continue;
                }
                encryptedKeyPem.append(line);
            }
        } catch (Exception exception) {
            System.out.println("An error occurred while loading the encrypted key: " + exception.getMessage());
            exception.printStackTrace();
        }
        return Base64.getDecoder().decode(encryptedKeyPem.toString());
    }

    public byte[] decryptSymmetricKey(byte[] encryptedKeyBytes, PrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(encryptedKeyBytes);
        } catch (Exception exception) {
            System.out.println("An error occurred while decrypting the symmetric key: " + exception.getMessage());
            exception.printStackTrace();
        }
        return null;
    }

    public void saveDecryptedKeyToPEM(byte[] decryptedKeyBytes, String decryptedKeyFile) {
        String base64DecryptedKey = Base64.getEncoder().encodeToString(decryptedKeyBytes);

        try (FileOutputStream fileOutputStream = new FileOutputStream(decryptedKeyFile)) {
            fileOutputStream.write("-----BEGIN SYMMETRIC KEY-----\n".getBytes());
            fileOutputStream.write(base64DecryptedKey.getBytes());
            fileOutputStream.write("\n-----END SYMMETRIC KEY-----\n".getBytes());
        } catch (Exception exception) {
            System.out.println("An error occurred while saving the decrypted key: " + exception.getMessage());
            exception.printStackTrace();
        }
    }
}