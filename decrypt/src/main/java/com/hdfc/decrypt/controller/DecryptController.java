package com.hdfc.decrypt.controller;

import com.hdfc.decrypt.VO.ResponseTemplateVO;
import com.hdfc.decrypt.entity.DecryptData;
import com.hdfc.decrypt.method.Decrypt;
import com.hdfc.decrypt.method.SaveStringToPem;
import com.hdfc.decrypt.method.SymmetrickeyDecryption;
import com.hdfc.decrypt.service.DecryptService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/decrypt")
@Slf4j
public class DecryptController {

    @Autowired
    private DecryptService decryptService;

//    @PostMapping("/")
//    public Decrypt saveDecrypt(@RequestBody Decrypt decrypt) {
//        return decryptService.saveDecrypt(decrypt);
//    }
//
// @GetMapping("/{id}")
//    public ResponseTemplateVO getDecryptWithEncrypt(@PathVariable("id") Long decryptId) {
//        return decryptService.getdecryptWithEncrypt(decryptId);
//    }
// 
 
 @GetMapping("/decrypt")
 public String decryptAndSaveXmlData() {
	 Decrypt decryptor = new Decrypt();
     try {
         decryptor.decryptAndSaveXmlData();
         
         return "decryption-success"; // Return a success view or redirect
     } catch (Exception e) {
         e.printStackTrace();
         return "decryption-failure"; // Return an error view or redirect
     }
 }


 @GetMapping("/decryptSymmetricKey")
 public String decryptAndSaveSymmetricKey() {
	 SymmetrickeyDecryption decryptor = new SymmetrickeyDecryption();
     try {
         decryptor.decryptAndSaveSymmetricKey();
         
         // Add any additional logic or return statements as needed
         
         return "decryption-success"; // Return a success view or redirect
     } catch (Exception e) {
         e.printStackTrace();
         return "decryption-failure"; // Return an error view or redirect
     }
 }
 
 
 @PostMapping(value = "/encryptData")
 public String encryptData(@RequestBody DecryptData decryptData)
 {
	 SaveStringToPem saveStringToPem=new SaveStringToPem();
		try{
			
			//return "hello";
			System.out.println(decryptData.getRequestSignatureEncryptedValue());
			saveStringToPem.encryptDataIntoPem(decryptData.getRequestSignatureEncryptedValue(),"Encrypted_DigitalSigned_A2A.pem");
			saveStringToPem.saveStringToPemFileSemmetric(decryptData.getRymmetricKeyEncryptedValue(),"encrypted_symmetric_key.pem");
			return decryptData.getRequestSignatureEncryptedValue();
 
     } catch (Exception e) {
         e.printStackTrace();
         return "decryption-failure"; // Return an error view or redirect
     }
 }
 

}
