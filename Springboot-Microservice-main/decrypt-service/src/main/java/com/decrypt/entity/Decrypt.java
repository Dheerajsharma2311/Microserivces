package com.decrypt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Decrypt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long decryptId;
    private String RequestSignatureEncryptedValue;
    private String SymmetricKeyEncryptedValue;
    private String Scope;
    private String TransactionId;
    private String OAuthTokenValue;
	public String getEncryptId() {
		// TODO Auto-generated method stub
		return null;
	}
}
