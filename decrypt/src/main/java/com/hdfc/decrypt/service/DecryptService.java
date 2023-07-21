package com.hdfc.decrypt.service;

import com.hdfc.decrypt.VO.Encrypt;
import com.hdfc.decrypt.VO.ResponseTemplateVO;

import com.hdfc.decrypt.repository.DecryptRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class DecryptService {

    @Autowired
    private DecryptRepository decryptRepository;

//    @Autowired
//    private RestTemplate restTemplate;
//
//    public Decrypt saveDecrypt(Decrypt decrypt) {
//        return decryptRepository.save(decrypt);
//    }
//
//    public ResponseTemplateVO getdecryptWithEncrypt(Long decryptId) {
//        ResponseTemplateVO vo = new ResponseTemplateVO();
//        Decrypt decrypt = decryptRepository.findByDecryptId(decryptId);
//
//        Encrypt encrypt =
//                restTemplate.getForObject("http://ENCRYPT-SERVICE/encrypts/" + decrypt.getEncryptId(),
//                        Encrypt.class);
//
//        vo.setDecrypt(decrypt);
//        vo.setEncrypt(encrypt);
//
//        return vo;
//    }
}
