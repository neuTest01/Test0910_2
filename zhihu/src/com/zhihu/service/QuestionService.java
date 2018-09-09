package com.zhihu.service;

import java.util.List;

import com.zhihu.dao.pojo.MyReply;
import com.zhihu.dao.pojo.QuestionInfo;
import com.zhihu.dao.pojo.Topic;

public interface QuestionService {
	
	/**
	 * 获取首页问题列表
	 * @return
	 */
	List<QuestionInfo> getHomePageList ();

	/**
	 * 新增提问
	 */
	int insertQuestionInfo(Object param[]);

	/**
	 * 关注话题
	 * @param obj
	 */
	void attentionTopic(Object obj[]);

	/**
	 * 查询用户关注的话题
	 * @return 
	 */
	List<Topic> getTopicList(Object obj[]);
	
	/**
	 * 根据话题查询问题列表
	 * @return
	 */
	List<QuestionInfo> getTopicQuestionList(String topciId,String content);
	
	/**
	 * 获取问题详情
	 * @param obj
	 * @return
	 */
	QuestionInfo getQuizDetail(Object[] obj);
	
	/**
	 * 获取我提问的问题
	 * @param obj
	 * @return
	 */
	List<QuestionInfo> getMyQuestion(int userID);
	
	/**
	 * 获取我回答过的问题
	 * @param obj
	 * @return
	 */
	List<MyReply> getReplyQuestion(int userID);
	
	/**
	 * 获取我回答过的回复
	 * @param obj
	 * @return
	 */
	List<MyReply> getMyReply(int userID);
}
