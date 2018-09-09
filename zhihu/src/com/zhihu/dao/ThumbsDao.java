package com.zhihu.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.zhihu.dao.pojo.Thumbs;
import com.zhihu.util.SqlSendUtil;

public class ThumbsDao {
	//查询当前用户是否对当前问题进行点赞
	public int getFllowNumber(Connection conn,Object param[]) throws SQLException{
		String sql = "SELECT COUNT(*) FROM thumbs s WHERE 1=1 AND s.question_id = "+param[0]+" AND s.user_acount = "+param[1]+" and s.reply_id = "+param[2];
		System.out.println(sql);
		return Integer.parseInt(SqlSendUtil.selectCount(conn, sql).toString()) ;
	}
	
	//查询问题有多少个赞
	public int getFllowNumbers(Connection conn,Object param[]) throws SQLException{
		String sql = "SELECT COUNT(*) FROM thumbs s WHERE 1=1 AND s.question_id =  "+param[0];
		return (int) SqlSendUtil.selectCount(conn, sql);
	}
	
	//查询问题有多少个评论
	public int getReplayNumbers(Connection conn,Object[] param) throws SQLException{
		String sql = "SELECT COUNT(*) FROM reply_user r WHERE r.reply_question_id =  "+param[0];
		return (int) SqlSendUtil.selectCount(conn, sql);
	}
	
	//保存点赞信息
	public int saveFllowInfo (Connection conn,Object[] param) throws SQLException{
		String sql = "INSERT INTO thumbs (question_id,user_acount,reply_id,CREATE_time,flag) values(?,?,?,sysdate,?)";
		SqlSendUtil.update(conn, sql, param);
		return 1;
	}
	
	//更新点赞信息
	public int updateFllowInfo (Connection conn,Object[] param) throws SQLException{
		String sql = "UPDATE thumbs s SET s.flag = ? where s.question_id = ? AND s.user_acount = ? and s.reply_id = ?";
		SqlSendUtil.update(conn, sql, param);
		return 1;
	}
	
	//获得某个用户针对谋篇文章的点赞值
	public String getFllowFlag(Connection conn,Object[] param) throws SQLException{
		String sql = "SELECT flag from thumbs where question_id =? AND user_acount=?";
		String flag = "2";
		Thumbs thumbs = new Thumbs();
		try{
			thumbs=(Thumbs) SqlSendUtil.selectOne(conn, sql, param, thumbs.getClass());
			return thumbs.getFlag();
		}catch(Exception e){
			
		}
		return flag;
	}
}
