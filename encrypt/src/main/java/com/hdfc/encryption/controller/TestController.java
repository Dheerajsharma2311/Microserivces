package com.hdfc.encryption.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdfc.encryption.methods.DigitalSigned;
import com.hdfc.encryption.methods.Encryption;
import com.hdfc.encryption.methods.Keys;
import com.hdfc.encryption.methods.Symmetrickeyencrypt;

@RestController
@RequestMapping("/test")
public class TestController {
	
	
	Keys keys=new Keys();
	DigitalSigned digitalSigned=new  DigitalSigned();
	Encryption    encryption =new Encryption();
	Symmetrickeyencrypt symmetrickeyencrypt =new Symmetrickeyencrypt();
	
	@GetMapping
	public String test()
	{
		return "test is running";
	}
	
	@GetMapping("/key")
	public String key()
	{
		try {
			keys.generateAndSaveKeys();
			return "your key genrated ";
		} catch (Exception exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
			return "your key not genrated "+exception;
		}
	
	}
	
	
	@GetMapping("/digitalSigned")
	public String digitalSigned()
	{
		try {
			digitalSigned.signXmlData();
			return "digitalSigned genrated ";
		} catch (Exception exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
			return "digitalSigned not genrated "+exception;
		}
	
	}
	
	@GetMapping("/encryption")
	public String encryption()
	{
		try {
			encryption.encryptXmlData();
			return "encryption genrated ";
		} catch (Exception exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
			return "encryption not genrated "+exception;
		}
	
	}

	@GetMapping("/symmetrickeyencrypt")
	public String symmetrickeyencrypt()
	{
		try {
			symmetrickeyencrypt.encryptAndSaveSymmetricKey();
			return "symmetrickeyencrypt genrated ";
		} catch (Exception exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
			return "symmetrickeyencrypt not genrated "+exception;
		}
	
	}
	
}
