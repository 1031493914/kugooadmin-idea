package com.mvcweb.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeviceBreakDownRowMapper implements RowMapper<DeviceBreakDown>{

	@Override
	public DeviceBreakDown mapRow(ResultSet rs, int rowNum) throws SQLException {
		DeviceBreakDown deviceBreakDown = new DeviceBreakDown();
		deviceBreakDown.setId(rs.getInt("id"));
		deviceBreakDown.setDeviceid(rs.getInt("deviceid"));
		deviceBreakDown.setBreakdownid(rs.getString("breakdownid"));
		deviceBreakDown.setContent(rs.getString("content"));
		deviceBreakDown.setCreate_time(rs.getTimestamp("create_time"));
		return deviceBreakDown;
	}

}
