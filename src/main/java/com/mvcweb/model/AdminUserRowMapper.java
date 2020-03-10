package com.mvcweb.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class AdminUserRowMapper implements RowMapper<AdminUser>{
	
	@Override
	public AdminUser mapRow(ResultSet rs, int rowNum) throws SQLException {
		AdminUser adminUser = new AdminUser();
		adminUser.setId(rs.getInt("id"));
		adminUser.setLoginname(rs.getString("loginname"));
		adminUser.setLoginpwd(rs.getString("loginpwd"));
		adminUser.setRole(rs.getInt("role"));
		adminUser.setContent(rs.getString("content"));
		adminUser.setCreatetime(rs.getDate("createtime"));
		adminUser.setStatus(rs.getInt("status"));
		return adminUser;
	}
}
