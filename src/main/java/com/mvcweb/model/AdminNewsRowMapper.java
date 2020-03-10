package com.mvcweb.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class AdminNewsRowMapper implements RowMapper<AdminNews>{
	
	@Override
	public AdminNews mapRow(ResultSet rs, int rowNum) throws SQLException {
		AdminNews adminNews = new AdminNews();
		adminNews.setId(rs.getInt("id"));
		adminNews.setTitle(rs.getString("title"));
		adminNews.setContent(rs.getString("content"));
		adminNews.setCreate_time(rs.getTimestamp("create_time"));
		adminNews.setStatus(rs.getInt("status"));
		return adminNews;
	}
}
