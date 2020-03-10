package com.mvcweb.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeviceSellerAddressListRowMapper implements RowMapper<DeviceSellerAddressList>{

	@Override
	public DeviceSellerAddressList mapRow(ResultSet rs, int rowNum) throws SQLException {
		DeviceSellerAddressList deviceSellerAddressList = new DeviceSellerAddressList();
		deviceSellerAddressList.setId(rs.getInt("id"));
		deviceSellerAddressList.setNum(rs.getInt("num"));
		deviceSellerAddressList.setCountry(rs.getString("country"));
		return deviceSellerAddressList;
	}

}
