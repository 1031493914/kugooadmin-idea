package com.mvcweb.model;

import java.io.Serializable;
import java.util.Date;

public class DeviceType implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	//设备厂商代码0公版  1高德 2永泰 3驴电 4酷格  5本田
	private String companycode;
	//型号
	private String typecode;
	//批次
	private String pici;
	private Date create_time;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCompanycode() {
		return companycode;
	}

	public void setCompanycode(String companycode) {
		this.companycode = companycode;
	}

	public String getTypecode() {
		return typecode;
	}

	public void setTypecode(String typecode) {
		this.typecode = typecode;
	}

	public String getPici() {
		return pici;
	}

	public void setPici(String pici) {
		this.pici = pici;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
}
