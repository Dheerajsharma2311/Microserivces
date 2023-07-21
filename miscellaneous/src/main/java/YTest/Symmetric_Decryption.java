package YTest;




import java.io.BufferedReader;

import java.io.FileOutputStream;

import java.io.FileReader;

import java.nio.charset.StandardCharsets;

import java.security.KeyFactory;

import java.security.PrivateKey;

import java.security.spec.PKCS8EncodedKeySpec;

import java.util.Base64;

import javax.crypto.Cipher;

 

public class Symmetric_Decryption {

    

    public static void main(String args[])

 

    {

        String s = decryptSymmatrickey("puujGEic1DmVCdYvGkdYp2v6/bGMIVfjWna/8+8RoA4D7Y5uZH5+3vXm98gdAoXN+O9a+e6OLPlwMlRCTsaM26oIihytr/KNDTgdM0sZSFMflvoulTXOZzB1RgQef1FWDQjU4cz3bUt2l8vbMWLKiSIUoyWi8A1mip4lYbj0qRZrghTokvmQ3hn1I1Ex8Oka7xs70OA6H/80fbaD8XbzQhdI8DqlNJiAIzM6WAaxhaQ0mfJIosRvfqUYOlBIx8390kLFuQZHAft4mV/NzbeAEsz049QouydmOfEX/Xy33S2YzLqmA94XwhIGKfIxMFNdOJ/JWzYnXXeOxpAGko8boQ==");

        System.out.println(s);
    }

 

    public static  String decryptSymmatrickey(String encryptedKey) {
        String privateKeyFile = "private_key.pem";

        try {
            // Load the private key from the PEM file
            byte[] privateKeyBytes = loadPrivateKeyBytes(privateKeyFile);
            PrivateKey privateKey = loadPrivateKey(privateKeyBytes);

            // Decrypt the symmetric key using the private key
            byte[] decryptedKeyBytes = decryptSymmetricKey(encryptedKey, privateKey);

            // Convert the decrypted key to a string
            String decryptedKey = new String(decryptedKeyBytes, StandardCharsets.UTF_8);

            return decryptedKey;
        } catch (Exception exception) {
            System.out.println("An error occurred: " + exception.getMessage());
            exception.printStackTrace();
        }

        return null;
    }

 

    public static byte[] loadPrivateKeyBytes(String privateKeyFile) {

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

 

    public static PrivateKey loadPrivateKey(byte[] privateKeyBytes) {

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

 

    public static byte[] decryptSymmetricKey(String encryptedKey, PrivateKey privateKey) {

        try {

            byte[] encryptedKeyBytes = Base64.getDecoder().decode(encryptedKey);

            Cipher cipher = Cipher.getInstance("RSA");

            cipher.init(Cipher.DECRYPT_MODE, privateKey);

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

 

