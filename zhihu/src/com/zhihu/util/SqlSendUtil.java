package com.zhihu.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqlSendUtil {

	private static Logger log = LoggerFactory.getLogger(SqlSendUtil.class);

	// 添加数据 更新数据 删除数据
	public static int update(Connection conn, String sql, Object param[]) throws SQLException {
		int result = 0;
		QueryRunner qRunner = new QueryRunner();
		result = qRunner.update(conn, sql, param);
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object selectOne(Connection conn, String sql, Object[] param,
			Class clazz) {
		QueryRunner qRunner = new QueryRunner();
		Object obj = null;
		try {
			obj = (Object) qRunner.query(conn, sql, new BeanHandler(clazz),
					param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("getCommAddrList error,e={}", e);
			e.printStackTrace();
		}
		return obj;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<?> selectList(Connection conn, String sql,
			Object[] param, Class clazz) {
		QueryRunner queryRunner = new QueryRunner();
		List<?> list = null;
		try {
			list = (List<?>) queryRunner.query(conn, sql, new BeanListHandler(
					clazz), param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	//查询表的数量
	public static Object selectCount(Connection conn, String sql) {
		QueryRunner qRunner = new QueryRunner();
		Object obj = null;
		try {
			obj = qRunner.query(conn, sql, new ScalarHandler());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("getCommAddrList error,e={}", e);
			e.printStackTrace();
		}
		
		return obj;
	}
		
	//批量处理数据操作
	public static int updateList(Connection conn, String sql,
			Object [][] param) throws SQLException{
		int [] c = null;
		QueryRunner queryRunner = new QueryRunner();
		c = queryRunner.batch(conn, sql, param);
		for(int i = 0 ; i < c.length ; i++){
			if(c[i] == 0){
				return 0;
			}
		}
		return 1;
	}

}
