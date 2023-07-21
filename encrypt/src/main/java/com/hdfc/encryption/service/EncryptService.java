package com.hdfc.encryption.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfc.encryption.entity.Encrypt;
import com.hdfc.encryption.repo.EncryptRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EncryptService {

    @Autowired
    private EncryptRepository encryptRepository;
    
   // Logger log=new Logger("", EncryptService.class);
    public Encrypt saveEncrypt(Encrypt encrypt) {
     //   log.info("Inside saveEncrypt of EncryptService");
        return encryptRepository.save(encrypt);
    }

//    public Encrypt findEncryptById(Long encryptId) {
//        //log.info("Inside saveEncrypt of EncryptService");
//        //return encryptRepository.findByEncryptId(encryptId);
//    }

	
}
