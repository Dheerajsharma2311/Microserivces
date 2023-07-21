package com.cloud.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {

    @GetMapping("/DecryptServiceFallBack")
    public String decryptServiceFallBackMethod() {
        return "Decrypt Service is taking longer than Expected." +
                " Please try again later";
    }

    @GetMapping("/EncryptServiceFallBack")
    public String departmentServiceFallBackMethod() {
        return "Encrypt Service is taking longer than Expected." +
                " Please try again later";
    }
}
