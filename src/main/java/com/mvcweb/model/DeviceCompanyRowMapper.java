package com.mvcweb.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeviceCompanyRowMapper implements RowMapper<DeviceCompany>{

	@Override
	public DeviceCompany mapRow(ResultSet rs, int rowNum) throws SQLException {
		DeviceCompany deviceCompany = new DeviceCompany();
		deviceCompany.setId(rs.getInt("id"));
		deviceCompany.setCreate_time(rs.getTimestamp("create_time"));
		deviceCompany.setName(rs.getString("name"));
		deviceCompany.setCode(rs.getString("code"));
		deviceCompany.setStatus(rs.getInt("status"));
		deviceCompany.setAdminuserid(rs.getString("adminuserid"));
		return deviceCompany;
	}

}
