package com.zhihu.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.zhihu.dao.QuestionDao;
import com.zhihu.dao.pojo.MyReply;
import com.zhihu.dao.pojo.QuestionInfo;
import com.zhihu.dao.pojo.Topic;
import com.zhihu.service.QuestionService;
import com.zhihu.util.JdbcUtils;

public class QuestionServiceImpl implements QuestionService{
	
	QuestionDao questionDao = new QuestionDao();
	Connection conn = JdbcUtils.getInstance().getDateBase();
	
	/**
	 * 新增提问
	 */
	public int insertQuestionInfo(Object param[]) {
		int row = 0;
		try {
			row = questionDao.insertQuestionInfo(conn, param);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}

	/**
	 * 获取首页问题列表
	 */
	@Override
	public List<QuestionInfo> getHomePageList() {
		return questionDao.getHomePageList(conn);
	}

	/***
	 * 关注话题
	 */
	@Override
	public void attentionTopic(Object obj[]) {
		try {
			questionDao.attentionTopic(conn, obj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询用户关注的话题
	 * @return 
	 */
	@Override
	public List<Topic> getTopicList(Object obj[]) {
			return questionDao.getTopicList(conn, obj);
	}
	
	/**
	 * 根据话题查询问题列表
	 */
	@Override
	public List<QuestionInfo> getTopicQuestionList(String topciId,String content) {
		return questionDao.getTopicQuestionList(conn, topciId,content);
	}

	@Override
	public QuestionInfo getQuizDetail(Object[] obj) {
		
		return questionDao.getQuizDetail(conn, obj);
	}
	
	//获取我的问题列表
	@Override
	public List<QuestionInfo> getMyQuestion(int userID) {
		
		return (List<QuestionInfo>) questionDao.getMyQuestion(conn, userID);
	}
	
	
	//获取我的回答过的问题的问题列表
	@Override
	public List<MyReply> getReplyQuestion(int userID) {
		
		return (List<MyReply>) questionDao.getMyReplyQuestion(conn, userID);
	}
	//获取我的回答过的问题的回复
	@Override
	public List<MyReply> getMyReply(int userID) {
		
		return (List<MyReply>) questionDao.getMyReply(conn, userID);
	}
}
