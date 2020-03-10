package com.mvcweb.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeviceSellersRowMapper implements RowMapper<DeviceSeller>{

	@Override
	public DeviceSeller mapRow(ResultSet rs, int rowNum) throws SQLException {
		DeviceSeller deviceSeller = new DeviceSeller();
		deviceSeller.setId(rs.getInt("id"));
		deviceSeller.setCreate_time(rs.getTimestamp("create_time"));
		deviceSeller.setName(rs.getString("name"));
		deviceSeller.setCountry(rs.getString("country"));
		return deviceSeller;
	}

}
