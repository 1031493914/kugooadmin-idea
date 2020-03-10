package com.mvcweb.model;

import java.io.Serializable;
import java.util.Date;

public class DeviceSeller implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String country;
	private Date create_time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
}
