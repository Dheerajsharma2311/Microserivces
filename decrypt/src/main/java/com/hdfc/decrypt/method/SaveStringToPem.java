package com.hdfc.decrypt.method;

import java.io.FileWriter;
import java.io.IOException;

public class SaveStringToPem {

    public void encryptDataIntoPem(String data, String filePath) {
       
      

        try {
            saveStringToPemFile(data, filePath);
            System.out.println("String saved to PEM file successfully.");
        } catch (IOException e) {
            System.err.println("Error saving string to PEM file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void saveStringToPemFile(String data, String filePath) throws IOException {
        String pemString = "-----BEGIN ENCRYPTED DATA-----\n" + data + "\n-----END ENCRYPTED DATA-----";

        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(pemString);
        }
    }
    
    /////////////////
    
    
    public void encryptDataIntoPemSemmetric(String data, String filePath) {
        
        

        try {
            saveStringToPemFile(data, filePath);
            System.out.println("String saved to PEM file successfully.");
        } catch (IOException e) {
            System.err.println("Error saving string to PEM file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void saveStringToPemFileSemmetric(String data, String filePath) throws IOException {
        String pemString = "-----BEGIN SYMMETRIC KEY-----\n" + data + "\n-----END SYMMETRIC KEY-----";

        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(pemString);
        }
    }
}
