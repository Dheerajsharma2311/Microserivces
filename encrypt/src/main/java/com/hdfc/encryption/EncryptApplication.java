package com.hdfc.encryption;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication
@EnableEurekaServer
public class EncryptApplication {


	public static void main(String[] args) {
		SpringApplication.run(EncryptApplication.class, args);
	}

}
