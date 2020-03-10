package com.mvcweb.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeviceBreakDownListRowMapper implements RowMapper<DeviceBreakDownList>{

	@Override
	public DeviceBreakDownList mapRow(ResultSet rs, int rowNum) throws SQLException {
		DeviceBreakDownList deviceBreakDownList = new DeviceBreakDownList();
		deviceBreakDownList.setId(rs.getInt("id"));
		deviceBreakDownList.setContent(rs.getString("content"));
		deviceBreakDownList.setNum(rs.getInt("num"));
		return deviceBreakDownList;
	}

}
