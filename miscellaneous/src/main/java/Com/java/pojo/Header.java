package Com.java.pojo;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.sql.Date;

public class Header {
	
	
    private String extsysname;
    private Date datpost;
    private int batchnumext;
    private String idtxn;
    private String codcurr;
    private String iduser;
    private int idcust;
    private String groupid;
    private Timestamp reqdatetime;//datetime
	
	
	public String getExtsysname() {
		return extsysname;
	}
	public Date getDatpost() {
		return datpost;
	}
	public int getBatchnumext() {
		return batchnumext;
	}
	public String getIdtxn() {
		return idtxn;
	}
	public String getCodcurr() {
		return codcurr;
	}
	public String getIduser() {
		return iduser;
	}
	public int getIdcust() {
		return idcust;
	}
	public String getGroupid() {
		return groupid;
	}
	public Timestamp getReqdatetime() {
		return reqdatetime;
	}
	
	public void setExtsysname(String extsysname) {
		this.extsysname = extsysname;
	}
	public void setDatpost(Date datpost) {
		this.datpost = datpost;
	}
	public void setBatchnumext(int batchnumext) {
		this.batchnumext = batchnumext;
	}
	public void setIdtxn(String idtxn) {
		this.idtxn = idtxn;
	}
	public void setCodcurr(String codcurr) {
		this.codcurr = codcurr;
	}
	public void setIduser(String iduser) {
		this.iduser = iduser;
	}
	public void setIdcust(int idcust) {
		this.idcust = idcust;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public void setReqdatetime(Timestamp reqdatetime) {
		this.reqdatetime = reqdatetime;
	}
    

}
