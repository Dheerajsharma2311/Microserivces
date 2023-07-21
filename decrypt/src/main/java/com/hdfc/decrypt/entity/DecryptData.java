package com.hdfc.decrypt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="decrypt")
public class DecryptData{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long decryptId;
    private String requestSignatureEncryptedValue;
    private String rymmetricKeyEncryptedValue;
    private String acope;
    private String transactionId;
    private String oAuthTokenValue;
	public Long getDecryptId() {
		return decryptId;
	}
	public void setDecryptId(Long decryptId) {
		this.decryptId = decryptId;
	}
	public String getRequestSignatureEncryptedValue() {
		return requestSignatureEncryptedValue;
	}
	public void setRequestSignatureEncryptedValue(String requestSignatureEncryptedValue) {
		this.requestSignatureEncryptedValue = requestSignatureEncryptedValue;
	}
	public String getRymmetricKeyEncryptedValue() {
		return rymmetricKeyEncryptedValue;
	}
	public void setRymmetricKeyEncryptedValue(String rymmetricKeyEncryptedValue) {
		this.rymmetricKeyEncryptedValue = rymmetricKeyEncryptedValue;
	}
	public String getAcope() {
		return acope;
	}
	public void setAcope(String acope) {
		this.acope = acope;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getoAuthTokenValue() {
		return oAuthTokenValue;
	}
	public void setoAuthTokenValue(String oAuthTokenValue) {
		this.oAuthTokenValue = oAuthTokenValue;
	}
	
	
	

	
}
