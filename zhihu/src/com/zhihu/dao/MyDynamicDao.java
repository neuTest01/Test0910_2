package com.zhihu.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.zhihu.dao.pojo.MyDynamic;
import com.zhihu.util.SqlSendUtil;

public class MyDynamicDao {
	//查询当前用户是否对当前问题进行点赞
	public List<MyDynamic> getMyDynamicList(Connection conn,int userID) throws SQLException{
		String sql = "SELECT mc.questionID, mc.questionTitle, mc.introduce, mc.content,"
				+ "mc.createTime,mc.typeFY,mc.type FROM mydynamic mc WHERE mc.sendID =  "+userID
				+ " ORDER BY mc.createTime DESC";
		return (List<MyDynamic>) SqlSendUtil.selectList(conn, sql, null, MyDynamic.class);
		 
	}

}
