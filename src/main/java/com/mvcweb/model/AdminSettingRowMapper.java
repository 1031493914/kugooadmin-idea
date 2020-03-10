package com.mvcweb.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class AdminSettingRowMapper implements RowMapper<AdminSetting>{
	
	@Override
	public AdminSetting mapRow(ResultSet rs, int rowNum) throws SQLException {
		AdminSetting adminSetting = new AdminSetting();
		adminSetting.setId(rs.getInt("id"));
		adminSetting.setType(rs.getInt("type"));
		adminSetting.setNum(rs.getInt("num"));
		return adminSetting;
	}
}
