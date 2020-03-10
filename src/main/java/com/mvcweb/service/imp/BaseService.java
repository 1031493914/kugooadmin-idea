package com.mvcweb.service.imp;

import com.mvcweb.model.PageInfoVo;
import com.mvcweb.util.ArrayUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class BaseService {
	private SimpleJdbcTemplate simpleJdbcTemplate;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.simpleJdbcTemplate = new SimpleJdbcTemplate(jdbcTemplate);
	}

	/**
	 * 批量更新
	 * 
	 * @param sql
	 * @param batchArgs
	 */
	public void batchUpdate(String sql, List<Object[]> batchArgs) {
		if (batchArgs.size() == 0) {
			LogFactory.getLog(this.getClass()).info(
					"batchUpdate sql:" + sql + ",size:0");
		} else {
			LogFactory.getLog(this.getClass()).info(
					"batchUpdate sql:" + sql + ",size:" + batchArgs.size());
			simpleJdbcTemplate.batchUpdate(sql, batchArgs);
		}
	}

	/**
	 * 执行单条更新操作的语句
	 * 
	 * @param sql
	 */
	public void update(String sql) {
		LogFactory.getLog(this.getClass()).info("update sql:" + sql);
		this.jdbcTemplate.execute(sql);
	}

	/**
	 * 用户insert、update、delete类型的sql语句
	 * 
	 * @param sql
	 * @param params
	 *            对象类型的数组，如果是list 则在调用发放前必须调用list.toArray()
	 */
	public void update(String sql, Object[] params) {
		LogFactory.getLog(this.getClass()).info(
				"update sql:" + sql + ",[" + ArrayUtil.join(params, ",") + "]");
		this.simpleJdbcTemplate.update(sql, params);
	}

	/**
	 * 修改用户密码
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public int updatePwd(String sql, Object[] params) {
		LogFactory.getLog(this.getClass()).info(
				"update sql:" + sql + ",[" + ArrayUtil.join(params, ",") + "]");
		return this.simpleJdbcTemplate.update(sql, params);
	}

	/**
	 * 返回单个实体对象
	 * 
	 * @param <T>
	 * @param sql
	 * @param params
	 * @return
	 */
	public <T> T getEntry(String sql, RowMapper<T> rowMapper, Object[] params) {
		List<T> list = getEntryList(sql, rowMapper, params);
		if (list.size() == 0)
			return null;
		return list.get(0);
	}

	/**
	 * 返回多个实体对象
	 * 
	 * @param <T>
	 * @param sql
	 * @param rowMapper
	 * @param params
	 * @return
	 */
	public <T> List<T> getEntryList(String sql, RowMapper<T> rowMapper,
			Object[] params) {
		LogFactory.getLog(this.getClass()).info(
				"query sql:" + sql + ",[" + ArrayUtil.join(params, ",") + "]");
		return jdbcTemplate.query(sql, params, rowMapper);
	}

	/**
	 * 获取查询单个字段的字符串数组
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<String> queryStringArray(String sql, Object[] params) {
		LogFactory.getLog(this.getClass()).info(
				"queryForList sql:" + sql + ",[" + ArrayUtil.join(params, ",")
						+ "]");
		return this.jdbcTemplate.queryForList(sql, String.class, params);
	}

	/**
	 * 获取查询单个字段的整数值数组
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<Integer> queryIntegerArray(String sql, Object[] params) {
		LogFactory.getLog(this.getClass()).info(
				"queryForList sql:" + sql + ",[" + ArrayUtil.join(params, ",")
						+ "]");
		return this.jdbcTemplate.queryForList(sql, Integer.class, params);
	}

	/**
	 * 查询单个实体，没有则返回null
	 * 
	 * @param sql
	 * @param params
	 *            对象类型的数组，如果是list 则在调用发放前必须调用list.toArray()
	 * @return
	 */
	public Map<String, Object> queryMap(String sql, Object[] params) {
		List<Map<String, Object>> list = queryListMap(sql, params);
		if (list.size() == 0)
			return null;
		return list.get(0);
	}

	/**
	 * 返回多个实体对象
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryListMap(String sql, Object[] params) {
		LogFactory.getLog(this.getClass()).info(
				"queryForList sql:" + sql + ",[" + ArrayUtil.join(params, ",")
						+ "]");
		return this.simpleJdbcTemplate.queryForList(sql, params);
	}

	/**
	 * 根据sql语句，获取返回的单个数字值
	 * 
	 * @param sql
	 *            类似SELECT count(*) FROM　TABLE
	 * @param params
	 *            对象类型的数组，如果是list 则在调用发放前必须调用list.toArray()
	 * @return 返回NULL或者其他
	 */
	public Long queryLong(String sql, Object[] params) {
		LogFactory.getLog(this.getClass()).info(
				"queryForLong sql:" + sql + ",[" + ArrayUtil.join(params, ",")
						+ "]");
		List<Long> list = this.jdbcTemplate.queryForList(sql, Long.class,
				params);
		if (list.size() == 0)
			return null;
		return list.get(0);// maybe null
	}

	/**
	 * 
	 * @param sql
	 * @param params
	 * @return 返回 NULL或者其他
	 */
	public Float queryFloat(String sql, Object[] params) {
		LogFactory.getLog(this.getClass()).info(
				"queryFloat sql:" + sql + ",[" + ArrayUtil.join(params, ",")
						+ "]");
		List<Float> list = this.jdbcTemplate.queryForList(sql, Float.class,
				params);
		if (list.size() == 0)
			return null;
		return list.get(0); // maybe null
	}

	/**
	 * 根据sql语句，获取返回的单个数字值
	 * 
	 * @param sql
	 *            类似SELECT count(*) FROM　TABLE
	 * @param params
	 *            对象类型的数组，如果是list 则在调用发放前必须调用list.toArray()
	 * @return 返回 NULL或者其他
	 */
	public Integer queryInteger(String sql, Object[] params) {
		System.out.println(
				"queryInteger sql:" + sql + ",[" + ArrayUtil.join(params, ",")
						+ "]");
		List<Integer> list = queryIntegerArray(sql, params);
		if (list.size() == 0)
			return null;
		return list.get(0);// maybe null
	}

	/**
	 * 保存一条记录，并返回生成的自增id
	 * 
	 * @param sql
	 * @param params
	 *            对象类型的数组，如果是list 则在调用发放前必须调用list.toArray()
	 * @return
	 */
	public Long saveAndGetId(final String sql, final Object[] params) {
		LogFactory.getLog(this.getClass()).info(
				"insert sql:" + sql + ",[" + ArrayUtil.join(params, ",") + "]");
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				PreparedStatement ps = conn.prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS);

				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
				return ps;
			}

		}, keyHolder);
		return keyHolder.getKey().longValue();
	}

	protected <T> List<T> getModelListAndTotal(String dataListSql,
											   String countSql, Map<String, Object> equalConditions,
											   Map<String, String> likeConditions, String orderby, String order,
											   RowMapper<T> rowMapper, PageInfoVo page, Map<String, Object> total) {
		StringBuilder queryListSql = new StringBuilder();
		StringBuilder queryCountSql = new StringBuilder();
		StringBuilder totalSql = new StringBuilder(
				(String) total.get("totalSql"));
		List<Object> paramsList = new ArrayList<Object>(equalConditions.size()
				+ likeConditions.size());
		queryListSql.append(dataListSql);
		queryCountSql.append(countSql);
		Iterator<Entry<String, Object>> entries = equalConditions.entrySet()
				.iterator();
		Entry<String, Object> entry;
		while (entries.hasNext()) {
			entry = entries.next();
			if (entry.getValue() != null) {
				queryListSql.append(" and ").append(entry.getKey())
						.append(" = ?");
				queryCountSql.append(" and ").append(entry.getKey())
						.append(" = ?");
				totalSql.append(" and ").append(entry.getKey()).append(" = ?");
				paramsList.add(entry.getValue());
			}
		}
		Iterator<Entry<String, String>> likeEntries = likeConditions.entrySet()
				.iterator();
		Entry<String, String> likeEntry;
		while (likeEntries.hasNext()) {
			likeEntry = likeEntries.next();
			if (StringUtils.isNotBlank(likeEntry.getValue())) {
				queryListSql.append(" and ").append(likeEntry.getKey())
						.append(" like ?");
				queryCountSql.append(" and ").append(likeEntry.getKey())
						.append(" like ?");
				totalSql.append(" and ").append(likeEntry.getKey())
						.append(" like ?");
				paramsList.add("%" + likeEntry.getValue() + "%");
			}
		}
		// 计算总页数
		page.setTotalSize(queryInteger(queryCountSql.toString(),
				paramsList.toArray()));
		if (page.getCurrentPage() > page.getTotalPage())
			page.setCurrentPage(page.getTotalPage());
		if (orderby != null && order != null) {
			queryListSql.append(" order by ").append(orderby).append(" ")
					.append(order);
		}
		queryListSql.append(" limit ").append(
				(page.getCurrentPage() - 1) * page.getPagesize());
		queryListSql.append(",").append(page.getPagesize());
		// 统计结果
		total.putAll(queryMap(totalSql.toString(), paramsList.toArray()));
		// 查询结果
		return getEntryList(queryListSql.toString(), rowMapper,
				paramsList.toArray());
	}

	/**
	 * 获取查询单个字段的单行值
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public String queryString(String sql, Object[] params) {
		List<String> list = queryStringArray(sql, params);
		if (list.size() == 0)
			return null;
		return list.get(0);
	}
	
	
}
