package com.mvcweb.model;

import java.io.Serializable;

public class DeviceSellerAddressList implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String country;
	private int num;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
