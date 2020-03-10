package com.mvcweb.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class DeviceInfoRowMapper implements RowMapper<DeviceInfo>{

	@Override
	public DeviceInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		DeviceInfo deviceinfo = new DeviceInfo();
		deviceinfo.setId(rs.getInt("id"));
		deviceinfo.setCreatetime(rs.getTimestamp("createtime"));
		deviceinfo.setImei(rs.getString("imei"));
		deviceinfo.setPhonecompany(rs.getString("phonecompany"));
		deviceinfo.setPhonesystem(rs.getString("phonesystem"));
		deviceinfo.setPhonetype(rs.getString("phonetype"));
		deviceinfo.setBluemac(rs.getString("bluemac"));
		deviceinfo.setCompanycode(rs.getString("companycode"));
		deviceinfo.setStatus(rs.getInt("status"));
		return deviceinfo;
	}

}
