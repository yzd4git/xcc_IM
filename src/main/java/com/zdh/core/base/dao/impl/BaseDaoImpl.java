package com.zdh.core.base.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zdh.core.base.dao.BaseDao;
import com.zdh.core.base.util.QueryResult;
import com.zdh.core.util.TableNameConvertUtil;

/**
 * 使用场景
 * 
 * 数据库是Oracle 主键列名为id 主键由程序提供（这里用的是UUID） 实体类名和数据库表名一致 比如：类名Product
 * 表名product、PRODUCT、Produc 都可以 t_product 不可以
 */
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class BaseDaoImpl<T> implements BaseDao<T> {
	private static final Log log = LogFactory.getLog(BaseDaoImpl.class);
	/** 设置一些操作的常量 */
	public static final String SQL_INSERT = "insert";
	public static final String SQL_UPDATE = "update";
	public static final String SQL_DELETE = "delete";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		ParameterizedType type = (ParameterizedType) getClass()
				.getGenericSuperclass();
		entityClass = (Class<T>) type.getActualTypeArguments()[0];
		log.debug("Dao实现类是：" + entityClass.getName());
	}

	@Override
	public int save(T entity ) {
		String sql = this.makeSql(SQL_INSERT);
		Object[] args = this.setArgs(entity, SQL_INSERT);
		int[] argTypes = this.setArgTypes(entity, SQL_INSERT);
		int flag = this.jdbcTemplate.update(sql.toString(), args, argTypes);
		return flag ;
	}

	@Override
	public int update(T entity) {
		String sql = this.makeSql(SQL_UPDATE);
		Object[] args = this.setArgs(entity, SQL_UPDATE);
		int[] argTypes = this.setArgTypes(entity, SQL_UPDATE);
		int flag = jdbcTemplate.update(sql, args, argTypes);
		return flag ;
	}

	@Override
	public int delete(T entity) {
		String sql = this.makeSql(SQL_DELETE);
		Object[] args = this.setArgs(entity, SQL_DELETE);
		int[] argTypes = this.setArgTypes(entity, SQL_DELETE);
		int flag = jdbcTemplate.update(sql, args, argTypes);
		return flag ;
	}

	@Override
	public int delete(Serializable id) {
		String sql = " DELETE FROM " + entityClass.getSimpleName()
				+ " WHERE id=?";
		int flag = jdbcTemplate.update(sql, id);
		return flag ;
	}

	@Override
	public int deleteAll() {
		String sql = " TRUNCATE TABLE " + entityClass.getSimpleName();
		int flag = jdbcTemplate.update(sql);
		return flag ;
	}

	/**
	 * 未完成
	 */
	@Override
	public int batchSave(List<T> list) {
		return 0 ;
	}

	/**
	 * 未完成
	 */
	@Override
	public int batchUpdate(List<T> list) {
		return 0 ;
	}

	/**
	 * 未完成
	 */
	@Override
	public int batchDelete(List<T> list) {

		return 0 ;
	}

	@Override
	public T findById(Serializable id) {
		String sql = "SELECT * FROM " + entityClass.getSimpleName()
				+ " WHERE id=?";
		RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(entityClass);
		return jdbcTemplate.query(sql, rowMapper, id).get(0);
	}
	@Override
	public T findByProperty (Class<T> T , Map<String,String> proMap) {
		Field[] fields = T.getDeclaredFields();
		fields.getClass().getFields() ;
		String sql = "SELECT * FROM " + "t" + TableNameConvertUtil.convertName(T.getSimpleName()) + " WHERE " ;
		StringBuffer where = new StringBuffer() ;
		Map map = new HashMap<String,String> () ;
		int[] argTypes = new int[fields.length];
		for (int i = 0; argTypes != null && i < argTypes.length; i++) {
			fields[i].setAccessible(true); // 暴力反射
			map.put(fields[i].getName(), fields[i].getType().getName()) ;
		}
		for (String key : proMap.keySet()) {  
			String pro = (String) map.get(key) ;
			if ( pro != null) {
				if (pro.equals("java.lang.Integer")) {
					where.append("`"+key+"`" + "=" + proMap.get(key) + " , ") ;
				} else if (pro.equals("java.lang.Float")) {
					where.append("`"+key+"`" + "=" + proMap.get(key) + " , ") ;
				} else if (pro.equals("java.lang.Long")) {
					where.append("`"+key+"`" + "=" + proMap.get(key) + " , ") ;
				} else if (pro.equals("java.lang.Double")) {
					where.append("`"+key+"`" + "='" + proMap.get(key) + "' , ") ;
				} else if (pro.equals("java.lang.String")) {
					where.append("`"+key+"`" + "='" + proMap.get(key) + "' , ") ;
				} else if (pro.equals("java.util.Date")) {
					where.append("`"+key+"`" + "='" + proMap.get(key) + "' , ") ;
				} else if (pro.equals("int")) {
					where.append("`"+key+"`" + "=" + proMap.get(key) + " , ") ;
				} else if (pro.equals("double")) {
					where.append("`"+key+"`" + "=" + proMap.get(key) + " , ") ;
				} else if (pro.equals("float")) {
					where.append("`"+key+"`" + "=" + proMap.get(key) + " , ") ;
				} else if (pro.equals("long")) {
					where.append("`"+key+"`" + "=" + proMap.get(key) + " , ") ;
				} else {
					where.append("`"+key+"`" + "='" + proMap.get(key) + "' , ") ;
				}
			}
		} 
		String w = where.substring(0, where.length() - 2) ;
		sql += w ;
		RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(T);
		List<T> li = jdbcTemplate.query(sql, rowMapper) ;
		if (li.size() > 0) {
			return jdbcTemplate.query(sql, rowMapper).get(0);
		} else {
			return null ;
		}
		
	}
	
	@Override
	public List<T> findAllByProperty (Class<T> T , Map<String,String> proMap) {
		Field[] fields = T.getDeclaredFields();
		fields.getClass().getFields() ;
		String sql = "SELECT * FROM " + "t" + TableNameConvertUtil.convertName(T.getSimpleName()) + " WHERE " ;
		StringBuffer where = new StringBuffer() ;
		Map map = new HashMap<String,String> () ;
		int[] argTypes = new int[fields.length];
		for (int i = 0; argTypes != null && i < argTypes.length; i++) {
			fields[i].setAccessible(true); // 暴力反射
			map.put(fields[i].getName(), fields[i].getType().getName()) ;
		}
		for (String key : proMap.keySet()) {  
			String pro = (String) map.get(key) ;
			if ( pro != null) {
				if (pro.equals("java.lang.Integer")) {
					where.append("`"+key+"`" + "=" + proMap.get(key) + " , ") ;
				} else if (pro.equals("java.lang.Float")) {
					where.append("`"+key+"`" + "=" + proMap.get(key) + " , ") ;
				} else if (pro.equals("java.lang.Long")) {
					where.append("`"+key+"`" + "=" + proMap.get(key) + " , ") ;
				} else if (pro.equals("java.lang.Double")) {
					where.append("`"+key+"`" + "='" + proMap.get(key) + "' , ") ;
				} else if (pro.equals("java.lang.String")) {
					where.append("`"+key+"`" + "='" + proMap.get(key) + "' , ") ;
				} else if (pro.equals("java.util.Date")) {
					where.append("`"+key+"`" + "='" + proMap.get(key) + "' , ") ;
				} else if (pro.equals("int")) {
					where.append("`"+key+"`" + "=" + proMap.get(key) + " , ") ;
				} else if (pro.equals("double")) {
					where.append("`"+key+"`" + "=" + proMap.get(key) + " , ") ;
				} else if (pro.equals("float")) {
					where.append("`"+key+"`" + "=" + proMap.get(key) + " , ") ;
				} else if (pro.equals("long")) {
					where.append("`"+key+"`" + "=" + proMap.get(key) + " , ") ;
				} else {
					where.append("`"+key+"`" + "='" + proMap.get(key) + "' , ") ;
				}
			}
		} 
		String w = where.substring(0, where.length() - 2) ;
		sql += w ;
		RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(T);
		return jdbcTemplate.query(sql, rowMapper) ;
	}

	@Override
	public List<T> findAllBySql (Class<T> T,String sql) {
		RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(T);
		return jdbcTemplate.query(sql, rowMapper) ;
	}
	
	@Override
	public List<T> findAll() {
		String sql = "SELECT * FROM " + entityClass.getSimpleName();
		RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(entityClass);
		return jdbcTemplate.query(sql, rowMapper);
	}
 
	@Override
	public QueryResult<T> findByPage(int pageNo, int pageSize) {
		List<T> list = this.find(pageNo, pageSize, null, null);
		Integer totalRow = this.count(null);
		return new QueryResult<T>(list, totalRow);
	}

	@Override
	public QueryResult<T> findByPage(int pageNo, int pageSize,
			Map<String, String> where) {
		List<T> list = this.find(pageNo, pageSize, where, null);
		int totalRow = this.count(where);
		return new QueryResult<T>(list, totalRow);
	}

	@Override
	public QueryResult<T> findByPage(int pageNo, int pageSize,
			LinkedHashMap<String, String> orderby) {
		List<T> list = this.find(pageNo, pageSize, null, orderby);
		int totalRow = this.count(null);
		return new QueryResult<T>(list, totalRow);
	}

	@Override
	public QueryResult<T> findByPage(int pageNo, int pageSize,
			Map<String, String> where, LinkedHashMap<String, String> orderby) {
		List<T> list = this.find(pageNo, pageSize, where, orderby);
		int totalRow = this.count(where);
		return new QueryResult<T>(list, totalRow);
	}

	// 组装SQL
	private String makeSql(String sqlFlag) {
		StringBuffer sql = new StringBuffer();
		Field[] fields = entityClass.getDeclaredFields();
		String profix = entityClass.getSimpleName() ;
		if (profix.startsWith("System")) {
			profix = "" ;
		} else {
			profix = "t_" ;
		}
		if (sqlFlag.equals(SQL_INSERT)) {
			sql.append(" INSERT INTO "
					+ profix // 加入表的前缀
					+ TableNameConvertUtil.convertName(entityClass
							.getSimpleName()));
			sql.append("(");
			for (int i = 0; fields != null && i < fields.length; i++) {
				fields[i].setAccessible(true); // 暴力反射
				String column = TableNameConvertUtil.convertName(fields[i]
						.getName());
				sql.append(column).append(",");
			}
			sql = sql.deleteCharAt(sql.length() - 1);
			sql.append(") VALUES (");
			for (int i = 0; fields != null && i < fields.length; i++) {
				sql.append("?,");
			}
			sql = sql.deleteCharAt(sql.length() - 1);
			sql.append(")");
		} else if (sqlFlag.equals(SQL_UPDATE)) {
			sql.append(" UPDATE " + entityClass.getSimpleName() + " SET ");
			for (int i = 0; fields != null && i < fields.length; i++) {
				fields[i].setAccessible(true); // 暴力反射
				String column = fields[i].getName();
				if (column.equals("id")) { // id 代表主键
					continue;
				}
				sql.append(column).append("=").append("?,");
			}
			sql = sql.deleteCharAt(sql.length() - 1);
			sql.append(" WHERE id=?");
		} else if (sqlFlag.equals(SQL_DELETE)) {
			sql.append(" DELETE FROM " + entityClass.getSimpleName()
					+ " WHERE id=?");
		}
		log.debug("SQL=" + sql);
		return sql.toString();

	}

	// 设置参数
	private Object[] setArgs(T entity, String sqlFlag) {
		Field[] fields = entityClass.getDeclaredFields();
		if (sqlFlag.equals(SQL_INSERT)) {
			Object[] args = new Object[fields.length];
			for (int i = 0; args != null && i < args.length; i++) {
				try {
					fields[i].setAccessible(true); // 暴力反射
					args[i] = fields[i].get(entity);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return args;
		} else if (sqlFlag.equals(SQL_UPDATE)) {
			Object[] tempArr = new Object[fields.length];
			for (int i = 0; tempArr != null && i < tempArr.length; i++) {
				try {
					fields[i].setAccessible(true); // 暴力反射
					tempArr[i] = fields[i].get(entity);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			Object[] args = new Object[fields.length];
			System.arraycopy(tempArr, 1, args, 0, tempArr.length - 1); // 数组拷贝
			args[args.length - 1] = tempArr[0];
			return args;
		} else if (sqlFlag.equals(SQL_DELETE)) {
			Object[] args = new Object[1]; // 长度是1
			fields[0].setAccessible(true); // 暴力反射
			try {
				args[0] = fields[0].get(entity);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return args;
		}
		return null;

	}

	// 设置参数类型（写的不全，只是一些常用的）
	private int[] setArgTypes(T entity, String sqlFlag) {
		log.error(entity.toString()) ;
		Field[] fields = entityClass.getDeclaredFields();
		if (sqlFlag.equals(SQL_INSERT)) {
			int[] argTypes = new int[fields.length];
			try {
				for (int i = 0; argTypes != null && i < argTypes.length; i++) {
					fields[i].setAccessible(true); // 暴力反射
//					log.error(fields[i].get(entity));
					if (fields[i].get(entity) == null) {
						argTypes[i] = Types.NULL;
					} else if (fields[i].get(entity).getClass().getName()
							.equals("java.lang.String")) {
						argTypes[i] = Types.VARCHAR;
					} else if (fields[i].get(entity).getClass().getName()
							.equals("java.lang.Double")) {
						argTypes[i] = Types.DECIMAL;
					} else if (fields[i].get(entity).getClass().getName()
							.equals("java.lang.Integer")) {
						argTypes[i] = Types.INTEGER;
					} else if (fields[i].get(entity).getClass().getName()
							.equals("java.util.Date")) {
						argTypes[i] = Types.TIMESTAMP;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return argTypes;
		} else if (sqlFlag.equals(SQL_UPDATE)) {
			int[] tempArgTypes = new int[fields.length];
			int[] argTypes = new int[fields.length];
			try {
				for (int i = 0; tempArgTypes != null && i < tempArgTypes.length; i++) {
					fields[i].setAccessible(true); // 暴力反射
					if (fields[i].get(entity).getClass().getName()
							.equals("java.lang.String")) {
						tempArgTypes[i] = Types.VARCHAR;
					} else if (fields[i].get(entity).getClass().getName()
							.equals("java.lang.Double")) {
						tempArgTypes[i] = Types.DECIMAL;
					} else if (fields[i].get(entity).getClass().getName()
							.equals("java.lang.Integer")) {
						tempArgTypes[i] = Types.INTEGER;
					} else if (fields[i].get(entity).getClass().getName()
							.equals("java.util.Date")) {
						tempArgTypes[i] = Types.DATE;
					}
				}
				System.arraycopy(tempArgTypes, 1, argTypes, 0,
						tempArgTypes.length - 1); // 数组拷贝
				argTypes[argTypes.length - 1] = tempArgTypes[0];

			} catch (Exception e) {
				e.printStackTrace();
			}
			return argTypes;

		} else if (sqlFlag.equals(SQL_DELETE)) {
			int[] argTypes = new int[1]; // 长度是1
			try {
				fields[0].setAccessible(true); // 暴力反射
				if (fields[0].get(entity).getClass().getName()
						.equals("java.lang.String")) {
					argTypes[0] = Types.VARCHAR;
				} else if (fields[0].get(entity).getClass().getName()
						.equals("java.lang.Integer")) {
					argTypes[0] = Types.INTEGER;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return argTypes;
		}
		return null;
	}

	private List<T> find(int pageNo, int pageSize, Map<String, String> where,
			LinkedHashMap<String, String> orderby) {
		// where 与 order by 要写在select * from table 的后面，而不是where rownum<=? )
		// where rn>=?的后面
		StringBuffer sql = new StringBuffer(" SELECT * FROM  ")
				.append(TableNameConvertUtil.addExt(entityClass.getSimpleName())).append(TableNameConvertUtil.convertName(entityClass.getSimpleName())) ;
		if (where != null && where.size() > 0) {
			sql.append(" WHERE "); // 注意不是where
			for (Map.Entry<String, String> me : where.entrySet()) {
				String columnName = me.getKey();
				String columnValue = me.getValue();
				sql.append(columnName).append(" IN('").append(columnValue)
						.append("')").append(" OR "); // 没有考虑or的情况
			}
			int endIndex = sql.lastIndexOf("OR");
			if (endIndex > 0) {
				sql = new StringBuffer(sql.substring(0, endIndex));
			}
		}
		if (orderby != null && orderby.size() > 0) {
			sql.append(" ORDER BY ");
			for (Map.Entry<String, String> me : orderby.entrySet()) {
				String columnName = me.getKey();
				String columnValue = me.getValue();
				sql.append(columnName).append(" ").append(columnValue)
						.append(",");
			}
			sql = sql.deleteCharAt(sql.length() - 1);
		}
		sql.append(" limit ? , ? ");
		log.debug("SQL=" + sql);
		Object[] args = {(pageNo - 1) * pageSize , pageNo * pageSize};
		RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(entityClass);
		return jdbcTemplate.query(sql.toString().toLowerCase(), args, rowMapper);
	}

	private Integer count(Map<String, String> where) {
		StringBuffer sql = new StringBuffer(" SELECT count(0) FROM  ")
				.append(TableNameConvertUtil.addExt(entityClass.getSimpleName())).append(TableNameConvertUtil.convertName(entityClass.getSimpleName())) ;
		if (where != null && where.size() > 0) {
			sql.append(" WHERE ");
			for (Map.Entry<String, String> me : where.entrySet()) {
				String columnName = me.getKey();
				String columnValue = me.getValue();
				sql.append(columnName).append(" IN('").append(columnValue)
					.append("')").append(" OR "); // 没有考虑or的情况
			}
			int endIndex = sql.lastIndexOf("OR");
			if (endIndex > 0) {
				sql = new StringBuffer(sql.substring(0, endIndex));
			}
		}
		log.debug("SQL=" + sql);
		Integer flag = jdbcTemplate.queryForObject(sql.toString().toLowerCase() , Integer.class);
		return flag ;
	}

	@Override
	public int executeBySql(String sql) {
		int flag = 0;
		try {
			flag = jdbcTemplate.update(sql);
			return flag;
		} catch (Exception e) {
			log.error("MESSAGE:" + e.getMessage());
			log.error("SQL:" + sql);
			return flag;
		}

	}
    @Override
	public Integer queryForInt(String sql) {
		int flag = 0;
		try {
			flag = jdbcTemplate.queryForObject(sql, Integer.class) ;
			return flag;
		} catch (Exception e) {
			log.error("MESSAGE:" + e.getMessage());
			log.error("SQL:" + sql);
			return flag;
		}
	}
    @Override
	public List<Map<String, Object>> queryForMapListBySql(String sql) {
		List<Map<String, Object>> list = null;
		
		  try {
			list = jdbcTemplate.queryForList(sql) ;
		} catch (Exception e) {
			log.error("MESSAGE:" + e.getMessage());
			log.error("SQL:" + sql);
		}
		  return list;
	}

	@Override
	public T queryForSql(Class<T> T, String sql) {
		RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(T);
		return jdbcTemplate.queryForObject(sql, rowMapper);
		
	}
	@Override
	public Map<String, Object> findForMapBySql(String sql){
		
		return jdbcTemplate.queryForMap(sql);
	}
	
}