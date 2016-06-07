package com.zdh.core.base.dao;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.zdh.core.base.util.QueryResult;

public interface BaseDao<T> {

	public int save(T entity) ;

	public int update(T entity);

	public int delete(T entity);

	public int delete(Serializable id);

	public int deleteAll();

	public int batchSave(List<T> list);

	public int batchUpdate(List<T> list);

	public int batchDelete(List<T> list);

	public T findById(Serializable id);
	
	public T findByProperty(Class<T> T , Map<String,String> proMap) ;
	
	public List<T> findAllBySql (Class<T> T,String sql) ;
	
	public List<T> findAllByProperty (Class<T> T , Map<String,String> proMap) ;

	public List<T> findAll();

	public QueryResult<T> findByPage(int pageNo, int pageSize);

	public QueryResult<T> findByPage(int pageNo, int pageSize,
			Map<String, String> where);

	public QueryResult<T> findByPage(int pageNo, int pageSize,
			LinkedHashMap<String, String> orderby);
 
	public QueryResult<T> findByPage(int pageNo, int pageSize,
			Map<String, String> where, LinkedHashMap<String, String> orderby);
	public int  executeBySql (String sql) ;
	
	public Integer queryForInt(String sql) ;

	public List<Map<String,Object>> queryForMapListBySql(String sql);

	public T queryForSql ( Class<T> T,String sql );//根据SQL语句查询对象
	
	public Map<String, Object> findForMapBySql(String sql);
	
}
