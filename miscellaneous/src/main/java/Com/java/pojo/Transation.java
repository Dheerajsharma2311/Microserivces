package Com.java.pojo;

import java.util.List;

public class Transation {

	
    private Header header;
    private Summary summary;
    private Debit debit;
    private List<Credit> creditList;
	
	public Header getHeader() {
		return header;
	}
	public Summary getSummary() {
		return summary;
	}
	public Debit getDebit() {
		return debit;
	}
	public List<Credit> getCreditList() {
		return creditList;
	}
	
	public void setHeader(Header header) {
		this.header = header;
	}
	public void setSummary(Summary summary) {
		this.summary = summary;
	}
	public void setDebit(Debit debit) {
		this.debit = debit;
	}
	public void setCreditList(List<Credit> creditList) {
		this.creditList = creditList;
	}
    
}
