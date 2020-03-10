package com.mvcweb.model;

import java.io.Serializable;
import java.util.Date;

public class DeviceInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String phonecompany;
	private String phonetype;
	private String imei;
	private String phonesystem;
	private Date createtime;
	private String bluemac;
	private String companycode;
	private int status;
	
	
	
	public String getBluemac() {
		return bluemac;
	}
	public void setBluemac(String bluemac) {
		this.bluemac = bluemac;
	}
	public String getCompanycode() {
		return companycode;
	}
	public void setCompanycode(String companycode) {
		this.companycode = companycode;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhonecompany() {
		return phonecompany;
	}
	public void setPhonecompany(String phonecompany) {
		this.phonecompany = phonecompany;
	}
	public String getPhonetype() {
		return phonetype;
	}
	public void setPhonetype(String phonetype) {
		this.phonetype = phonetype;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getPhonesystem() {
		return phonesystem;
	}
	public void setPhonesystem(String phonesystem) {
		this.phonesystem = phonesystem;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	
	
	
	
}
