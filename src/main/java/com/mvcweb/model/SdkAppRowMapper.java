package com.mvcweb.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SdkAppRowMapper implements RowMapper<SdkApp>{

	@Override
	public SdkApp mapRow(ResultSet rs, int rowNum) throws SQLException {
		SdkApp sdkApp = new SdkApp();
		sdkApp.setId(rs.getInt("id"));
		sdkApp.setCreatetime(rs.getTimestamp("createtime"));
		sdkApp.setAppname(rs.getString("appname"));
		sdkApp.setAppkey(rs.getString("appkey"));
		sdkApp.setPackagename(rs.getString("packagename"));
		sdkApp.setStatus(rs.getInt("status"));
		sdkApp.setUserid(rs.getInt("userid"));
		return sdkApp;
	}

}
