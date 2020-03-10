package com.mvcweb.model;

import java.io.Serializable;
import java.util.Date;

public class DeviceCountList implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private Date datetime;
	private String strdatetime;
	private int num;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getStrdatetime() {
		return strdatetime;
	}

	public void setStrdatetime(String strdatetime) {
		this.strdatetime = strdatetime;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
