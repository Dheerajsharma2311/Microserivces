package Com.java.pojo;

public class Debit {

	
    private String stanext;
    private String accountno;
    private Float orgamount;
    private String txndesc;
	
	public String getStanext() {
		return stanext;
	}
	public String getAccountno() {
		return accountno;
	}
	public Float getOrgamount() {
		return orgamount;
	}
	public String getTxndesc() {
		return txndesc;
	}
	
	public void setStanext(String stanext) {
		this.stanext = stanext;
	}
	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}
	public void setOrgamount(Float orgamount) {
		this.orgamount = orgamount;
	}
	public void setTxndesc(String txndesc) {
		this.txndesc = txndesc;
	}
    
}
