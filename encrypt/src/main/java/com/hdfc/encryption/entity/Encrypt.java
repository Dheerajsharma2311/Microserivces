package com.hdfc.encryption.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="encrypt")
public class Encrypt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String encryptId;
    private String RequestSignatureEncryptedValue;
    private String SymmetricKeyEncryptedValue;
    private String Scope;
    private String TransactionId;
    private String OAuthTokenValue;
	public String getEncryptId() {
		return encryptId;
	}
	public void setEncryptId(String encryptId) {
		this.encryptId = encryptId;
	}
	public String getRequestSignatureEncryptedValue() {
		return RequestSignatureEncryptedValue;
	}
	public void setRequestSignatureEncryptedValue(String requestSignatureEncryptedValue) {
		RequestSignatureEncryptedValue = requestSignatureEncryptedValue;
	}
	public String getSymmetricKeyEncryptedValue() {
		return SymmetricKeyEncryptedValue;
	}
	public void setSymmetricKeyEncryptedValue(String symmetricKeyEncryptedValue) {
		SymmetricKeyEncryptedValue = symmetricKeyEncryptedValue;
	}
	public String getScope() {
		return Scope;
	}
	public void setScope(String scope) {
		Scope = scope;
	}
	public String getTransactionId() {
		return TransactionId;
	}
	public void setTransactionId(String transactionId) {
		TransactionId = transactionId;
	}
	public String getOAuthTokenValue() {
		return OAuthTokenValue;
	}
	public void setOAuthTokenValue(String oAuthTokenValue) {
		OAuthTokenValue = oAuthTokenValue;
	}
    
    
	
}
