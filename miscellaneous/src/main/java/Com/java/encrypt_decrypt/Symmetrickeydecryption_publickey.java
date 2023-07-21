package Com.java.encrypt_decrypt;



import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;

public class Symmetrickeydecryption_publickey {

    public static void main(String[] args) {
        String publicKeyFile = "public_key.pem";
        String encryptedKeyFile = "encrypted_symmetric_key.pem";
        String decryptedKeyFile = "decrypted_symmetric_key.pem";

        try {
            // Load the public key from the PEM file
            byte[] publicKeyBytes = loadPublicKeyBytes(publicKeyFile);
            PublicKey publicKey = loadPublicKey(publicKeyBytes);

            // Load the encrypted key from the PEM file
            byte[] encryptedKeyBytes = loadEncryptedKeyBytes(encryptedKeyFile);

            // Decrypt the symmetric key using the public key
            byte[] decryptedKeyBytes = decryptSymmetricKey(encryptedKeyBytes, publicKey);

            // Save the decrypted key in PEM format
            saveDecryptedKeyToPEM(decryptedKeyBytes, decryptedKeyFile);

            System.out.println("Symmetric key decrypted and saved successfully.");
        } catch (Exception exception) {
            System.out.println("An error occurred: " + exception.getMessage());
            exception.printStackTrace();
        }
    }

    public static byte[] loadPublicKeyBytes(String publicKeyFile) {
        StringBuilder publicKeyPem = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(publicKeyFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("-----BEGIN PUBLIC KEY-----") || line.contains("-----END PUBLIC KEY-----")) {
                    continue;
                }
                publicKeyPem.append(line);
            }
        } catch (Exception exception) {
            System.out.println("An error occurred while loading the public key: " + exception.getMessage());
            exception.printStackTrace();
        }
        return Base64.getDecoder().decode(publicKeyPem.toString());
    }

    public static PublicKey loadPublicKey(byte[] publicKeyBytes) {
        try {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(keySpec);
        } catch (Exception exception) {
            System.out.println("An error occurred while loading the public key: " + exception.getMessage());
            exception.printStackTrace();
        }
        return null;
    }

    public static byte[] loadEncryptedKeyBytes(String encryptedKeyFile) {
        StringBuilder encryptedKeyPem = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(encryptedKeyFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("-----BEGIN ENCRYPTED SYMMETRIC KEY-----")
                        || line.contains("-----END ENCRYPTED SYMMETRIC KEY-----")) {
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

    public static byte[] decryptSymmetricKey(byte[] encryptedKeyBytes, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return cipher.doFinal(encryptedKeyBytes);
        } catch (Exception exception) {
            System.out.println("An error occurred while decrypting the symmetric key: " + exception.getMessage());
            exception.printStackTrace();
        }
        return null;
    }

    public static void saveDecryptedKeyToPEM(byte[] decryptedKeyBytes, String decryptedKeyFile) {
        String base64DecryptedKey = Base64.getEncoder().encodeToString(decryptedKeyBytes);

        try (FileOutputStream fileOutputStream = new FileOutputStream(decryptedKeyFile)) {
            fileOutputStream.write("-----BEGIN DECRYPTED SYMMETRIC KEY-----\n".getBytes());
            fileOutputStream.write(base64DecryptedKey.getBytes());
            fileOutputStream.write("\n-----END DECRYPTED SYMMETRIC KEY-----\n".getBytes());
        } catch (Exception exception) {
            System.out.println("An error occurred while saving the decrypted key: " + exception.getMessage());
            exception.printStackTrace();
        }
    }
}
