package com.mvcweb.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeviceSellerAddressRowMapper implements RowMapper<DeviceSellerAddress>{

	@Override
	public DeviceSellerAddress mapRow(ResultSet rs, int rowNum) throws SQLException {
		DeviceSellerAddress deviceSellerAddress = new DeviceSellerAddress();
		deviceSellerAddress.setId(rs.getInt("id"));
		deviceSellerAddress.setCreatetime(rs.getTimestamp("createtime"));
		deviceSellerAddress.setAddress(rs.getString("address"));
		deviceSellerAddress.setDeviceid(rs.getInt("deviceid"));
		deviceSellerAddress.setCity(rs.getString("city"));
		deviceSellerAddress.setCountry(rs.getString("country"));
		deviceSellerAddress.setProvince(rs.getString("province"));


		return deviceSellerAddress;
	}

}
