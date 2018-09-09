package com.zhihu.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.zhihu.dao.ReplyDao;
import com.zhihu.dao.pojo.Reply;
import com.zhihu.service.ReplyService;
import com.zhihu.util.JdbcUtils;

public class ReplyServiceImpl implements ReplyService{
	
	ReplyDao replyDao = new ReplyDao();
	Connection conn = JdbcUtils.getInstance().getDateBase();
	
	/**
	 * 回答提问
	 */
	public int insertReply(Object param[]) {
		int row = 0;
		try {
			row = replyDao.insertReply(conn, param);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}

	@Override
	public List<Reply> getReplyList(Object[] obj) {
		return replyDao.getReplyList(conn, obj);
	}
	
	
}
