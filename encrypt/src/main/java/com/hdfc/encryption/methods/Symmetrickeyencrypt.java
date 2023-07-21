package com.hdfc.encryption.methods;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Symmetrickeyencrypt {

    public void encryptAndSaveSymmetricKey() {
        String publicKeyFile = "public_key.pem";
        String symmetricKeyFile = "symmetric_key.pem";
        String encryptedKeyFile = "encrypted_symmetric_key.pem";

        try {
            // Load the recipient's public key from the PEM file
            byte[] publicKeyBytes = Files.readAllBytes(Paths.get(publicKeyFile));
            PublicKey publicKey = loadPublicKey(publicKeyBytes);

            // Load the symmetric key from the PEM file
            byte[] symmetricKeyBytes = Files.readAllBytes(Paths.get(symmetricKeyFile));
            Key symmetricKey = loadSymmetricKey(symmetricKeyBytes);

            // Encrypt the symmetric key using the recipient's public key
            byte[] encryptedKeyBytes = encryptSymmetricKey(symmetricKey, publicKey);

            // Save the encrypted key in PEM format
            saveEncryptedKeyToPEM(encryptedKeyBytes, encryptedKeyFile);

            System.out.println("Symmetric key encrypted and saved successfully.");
        } catch (Exception exception) {
            System.err.println("An error occurred: " + exception.getMessage());
            exception.printStackTrace();
        }
    }

    public PublicKey loadPublicKey(byte[] publicKeyBytes) {
        try {
            String publicKeyPEM = new String(publicKeyBytes);
            publicKeyPEM = publicKeyPEM.replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "")
                    .replaceAll("\\s+", "");

            byte[] publicKeyDecoded = Base64.getDecoder().decode(publicKeyPEM);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyDecoded);
            return keyFactory.generatePublic(keySpec);
        } catch (Exception exception) {
            System.err.println("Error loading public key: " + exception.getMessage());
            exception.printStackTrace();
            return null;
        }
    }

    public Key loadSymmetricKey(byte[] symmetricKeyBytes) {
        try {
            String symmetricKeyPEM = new String(symmetricKeyBytes);
            symmetricKeyPEM = symmetricKeyPEM.replace("-----BEGIN SYMMETRIC KEY-----", "")
                    .replace("-----END SYMMETRIC KEY-----", "")
                    .replaceAll("\\s+", "");

            byte[] symmetricKeyDecoded = Base64.getDecoder().decode(symmetricKeyPEM);

            // Create a SecretKeySpec object using the decoded key data
            return new SecretKeySpec(symmetricKeyDecoded, "AES");
        } catch (Exception exception) {
            System.err.println("Error loading symmetric key: " + exception.getMessage());
            exception.printStackTrace();
            return null;
        }
    }

    public byte[] encryptSymmetricKey(Key symmetricKey, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return cipher.doFinal(symmetricKey.getEncoded());
        } catch (Exception exception) {
            System.err.println("Error encrypting symmetric key: " + exception.getMessage());
            exception.printStackTrace();
            return null;
        }
    }

    public void saveEncryptedKeyToPEM(byte[] encryptedKeyBytes, String filePath) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            String base64EncryptedKey = Base64.getEncoder().encodeToString(encryptedKeyBytes);
            fileOutputStream.write("-----BEGIN ENCRYPTED SYMMETRIC KEY-----\n".getBytes());
            fileOutputStream.write(base64EncryptedKey.getBytes());
            fileOutputStream.write("\n-----END ENCRYPTED SYMMETRIC KEY-----\n".getBytes());
            fileOutputStream.close();
        } catch (Exception exception) {
            System.err.println("Error saving encrypted key: " + exception.getMessage());
            exception.printStackTrace();
        }
    }
}
