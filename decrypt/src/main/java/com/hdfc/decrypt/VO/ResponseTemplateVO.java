package com.hdfc.decrypt.VO;



import com.hdfc.decrypt.entity.DecryptData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {

    private DecryptData decrypt;
    private Encrypt encrypt;
	public DecryptData getDecrypt() {
		return decrypt;
	}
	public void setDecrypt(DecryptData decrypt) {
		this.decrypt = decrypt;
	}
	public Encrypt getEncrypt() {
		return encrypt;
	}
	public void setEncrypt(Encrypt encrypt) {
		this.encrypt = encrypt;
	}
    
    
}
