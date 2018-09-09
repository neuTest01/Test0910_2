package com.zhihu.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.zhihu.util.SqlSendUtil;

public class UserDeleteDao {
	
	public int DeleteUserInfo(Connection conn, String param) throws SQLException{
		String sql = "DELETE FROM USER_ACCOUNT WHERE USER_ID in ("+param+")";
		System.out.println(sql);
		SqlSendUtil.update(conn, sql, null);
		return 1;
	}
}
