package com.mvcweb.model;

import java.io.Serializable;
import java.util.Date;

public class DeviceBreakDown implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private int deviceid;
	private String breakdownid;
	private String content;
	private Date create_time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(int deviceid) {
		this.deviceid = deviceid;
	}

	public String getBreakdownid() {
		return breakdownid;
	}

	public void setBreakdownid(String breakdownid) {
		this.breakdownid = breakdownid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
}
