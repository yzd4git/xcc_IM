package com.zdh.core.demo;
import java.util.List; 
import java.util.Map; 
import java.util.regex.Matcher; 
import java.util.regex.Pattern; 

import org.apache.commons.lang.ArrayUtils; 
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.dao.DataAccessException; 
import org.springframework.jdbc.core.JdbcTemplate; 
import org.springframework.jdbc.core.RowMapper; 
import org.springframework.stereotype.Service;
/**
 * 
 * JdbcTemplate增加分页查询查询
 * 
 * 
 */
@Service("jdbcTemplateWithPaging")
public class JdbcTemplateWithPaging {

	private static final Logger logger = LoggerFactory.getLogger(JdbcTemplateWithPaging.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplateWithPaging() {
	}

	/**
	 * 分页查询
	 * 
	 * @param sql
	 *            查询的sql语句
	 * @param args
	 *            参数
	 * @param start
	 *            起始行
	 * @param limit
	 *            获取的行数
	 * @return
	 * @throws DataAccessException
	 */
	public List<Map<String, Object>> queryPage(String sql, Object[] args, int start, int limit) {
		if (start <= 0 && limit <= 0) {
			return (List<Map<String, Object>>) jdbcTemplate.queryForList(sql, args);
		}
		if (start <= 1) {
			sql = getLimitString(sql, false);
			args = ArrayUtils.add(args, args.length, limit);
		} else {
			sql = getLimitString(sql, true);
			args = ArrayUtils.add(args, args.length, start + limit);
			args = ArrayUtils.add(args, args.length, start);
		}

		logger.info("paging sql : \n" + sql);
		return (List<Map<String, Object>>) jdbcTemplate.queryForList(sql, args);
	}

	/**
	 * 分页查询
	 * 
	 * @param sql
	 *            查询的sql语句
	 * @param start
	 *            起始行
	 * @param limit
	 *            获取的行数
	 * @return
	 */
	public List<Map<String, Object>> queryPage(String sql, int start, int limit) {
		Object[] args = new Object[] {};
		return this.queryPage(sql, args, start, limit);
	}

	/**
	 * 分页查询
	 * 
	 * @param sql
	 *            查询的sql语句
	 * @param start
	 *            起始行
	 * @param limit
	 *            获取的行数
	 * 
	 * @param RowMapper
	 * @return
	 */
	public <T> List<T> queryPage(String sql, int start, int limit, RowMapper rowMapper) throws DataAccessException {
		if (start <= 0 && limit <= 0) {
			return jdbcTemplate.query(sql, rowMapper);
		}
		Object[] args = new Object[] {};
		if (start <= 1) {
			sql = getLimitString(sql, false);
			args = ArrayUtils.add(args, args.length, limit);
		} else {
			sql = getLimitString(sql, true);
			args = ArrayUtils.add(args, args.length, start + limit);
			args = ArrayUtils.add(args, args.length, start);
		}

		Pattern pattern = Pattern.compile("\\?");

		Matcher matcher = pattern.matcher(sql);

		for (int i = 0; i < args.length; i++, matcher = pattern.matcher(sql)) {
			sql = matcher.replaceFirst(args[i].toString());
		}

		logger.info("paging sql : \n" + sql);
		return jdbcTemplate.query(sql, rowMapper);
	}

	private String getLimitString(String sql, boolean hasOffset) {
		sql = sql.trim();
		boolean isForUpdate = false;
		if (sql.toLowerCase().endsWith(" for update")) {
			sql = sql.substring(0, sql.length() - 11);
			isForUpdate = true;
		}

		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
		if (hasOffset) {
			pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
		} else {
			pagingSelect.append("select * from ( ");
		}
		pagingSelect.append(sql);
		if (hasOffset) {
			pagingSelect.append(" ) row_ where rownum <= ?) where rownum_ > ?");
		} else {
			pagingSelect.append(" ) where rownum <= ?");
		}

		if (isForUpdate) {
			pagingSelect.append(" for update");
		}
		return pagingSelect.toString();
	}

}