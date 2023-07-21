package com.decrypt.controller;

import com.decrypt.VO.ResponseTemplateVO;
import com.decrypt.entity.Decrypt;
import com.decrypt.service.DecryptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/decrypt")
@Slf4j
public class DecryptController {

    @Autowired
    private DecryptService decryptService;

    @PostMapping("/")
    public Decrypt saveDecrypt(@RequestBody Decrypt decrypt) {
        return decryptService.saveDecrypt(decrypt);
    }

 @GetMapping("/{id}")
    public ResponseTemplateVO getDecryptWithEncrypt(@PathVariable("id") Long decryptId) {
        return decryptService.getdecryptWithEncrypt(decryptId);
    }


}
