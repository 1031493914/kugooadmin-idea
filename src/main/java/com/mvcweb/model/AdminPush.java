package com.mvcweb.model;

import java.util.Date;

public class AdminPush {
	private int id;
	private String push_title;
	private String push_content;
	private int push_platform;
	private Date create_time;
	private int is_success;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPush_title() {
		return push_title;
	}

	public void setPush_title(String push_title) {
		this.push_title = push_title;
	}

	public String getPush_content() {
		return push_content;
	}

	public void setPush_content(String push_content) {
		this.push_content = push_content;
	}

	public int getPush_platform() {
		return push_platform;
	}

	public void setPush_platform(int push_platform) {
		this.push_platform = push_platform;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public int getIs_success() {
		return is_success;
	}

	public void setIs_success(int is_success) {
		this.is_success = is_success;
	}
}
