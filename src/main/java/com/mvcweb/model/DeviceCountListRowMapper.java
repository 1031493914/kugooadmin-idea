package com.mvcweb.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeviceCountListRowMapper implements RowMapper<DeviceCountList>{

	@Override
	public DeviceCountList mapRow(ResultSet rs, int rowNum) throws SQLException {
		DeviceCountList deviceCountList = new DeviceCountList();
		deviceCountList.setId(rs.getInt("id"));
		deviceCountList.setNum(rs.getInt("num"));
		deviceCountList.setDatetime(rs.getTimestamp("createtime"));
		return deviceCountList;
	}

}
