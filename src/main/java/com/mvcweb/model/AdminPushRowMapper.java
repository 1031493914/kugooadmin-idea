package com.mvcweb.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class AdminPushRowMapper implements RowMapper<AdminPush>{
	
	@Override
	public AdminPush mapRow(ResultSet rs, int rowNum) throws SQLException {
		AdminPush adminPush = new AdminPush();
		adminPush.setId(rs.getInt("id"));
		adminPush.setPush_title(rs.getString("push_title"));
		adminPush.setPush_content(rs.getString("push_content"));
		adminPush.setPush_platform(rs.getInt("push_platform"));
		adminPush.setCreate_time(rs.getTimestamp("create_time"));
		adminPush.setIs_success(rs.getInt("is_success"));
		return adminPush;
	}
}
