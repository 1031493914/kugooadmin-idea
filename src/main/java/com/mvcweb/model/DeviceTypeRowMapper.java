package com.mvcweb.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeviceTypeRowMapper implements RowMapper<DeviceType>{

	@Override
	public DeviceType mapRow(ResultSet rs, int rowNum) throws SQLException {
		DeviceType deviceType = new DeviceType();
		deviceType.setId(rs.getInt("id"));
		deviceType.setCreate_time(rs.getTimestamp("create_time"));
		deviceType.setCompanycode(rs.getString("companycode"));
		deviceType.setTypecode(rs.getString("typecode"));
		deviceType.setPici(rs.getString("pici"));
		return deviceType;
	}

}
