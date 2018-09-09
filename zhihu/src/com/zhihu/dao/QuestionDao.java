package com.zhihu.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.zhihu.dao.pojo.MyReply;
import com.zhihu.dao.pojo.QuestionInfo;
import com.zhihu.dao.pojo.Topic;
import com.zhihu.dao.pojo.User;
import com.zhihu.util.SqlSendUtil;

public class QuestionDao {
	
	public int insertQuestionInfo(Connection conn, Object param[]) throws SQLException{
		String sql = "insert into question (QUESTION_ID,question_title, question_introduce, question_type, question_raise_id, question_create_time, question_topic) values(QUESTIONSQUENCES.nextval,?,?,?,?,sysdate,?)";
		SqlSendUtil.update(conn, sql, param);
		return 1;
	}

	//查询首页问题列表
	public List<QuestionInfo> getHomePageList(Connection conn) {
		String sql = "SELECT q.question_id questionId, q.question_title questionTitle, q.question_introduce questionIntroduce, q.question_type questionType, q.question_raise_id questionRaiseId, USER_ACCOUNT.user_name userName, q.question_create_time questionCreateTime, q.question_topic questionTopic, tc.code_name codeName FROM question q, tb_code tc, USER_ACCOUNT WHERE 1 = 1 AND q.question_topic = tc.code_value AND q.question_raise_id = USER_ACCOUNT.user_id ORDER BY q.question_create_time DESC ";
		List<QuestionInfo> list = (List<QuestionInfo>) SqlSendUtil.selectList(conn, sql, null, QuestionInfo.class);
		return list;
	}

	//关注话题
	public void attentionTopic(Connection conn, Object obj[]) throws SQLException {
		String sql = "insert into attention (user_id, topic_id, creat_time) values(?,?,sysdate)";
		SqlSendUtil.update(conn, sql, obj);
	}

	//查询用户关注的话题
	public List<Topic> getTopicList(Connection conn, Object obj[]) {

		StringBuffer sql = new StringBuffer();

		if(obj.length>0){
			sql.append("SELECT a.topic_id AS topicId, tc.code_name AS topicName FROM attention a, tb_code tc WHERE 1 = 1 AND a.topic_id = tc.code_value AND tc.code_kind = 1 AND a.user_id = ? ");
		} else {
		    sql.append("SELECT code_value AS topicId, code_name AS topicName FROM tb_code where code_kind = 1");
        }
		System.out.println(sql.toString());
		List<Topic> list = (List<Topic>) SqlSendUtil.selectList(conn, sql.toString(), obj, Topic.class);
		return list;
	}

	//根据话题查询问题列表
	public List<QuestionInfo> getTopicQuestionList(Connection conn, String topciId,String content) {
		StringBuffer sql = new StringBuffer("SELECT q.question_id questionId, q.question_title questionTitle, q.question_introduce questionIntroduce, q.question_type questionType, q.question_raise_id questionRaiseId, u.user_name userName, q.question_create_time questionCreateTime, q.question_topic questionTopic, tc.code_name codeName,\n" +
                "\t(SELECT count(*) from thumbs where question_id = q.question_id and flag=1) as zanCount ,\n" +
                "u.user_content as userContent, "+
                "\t(SELECT COUNT(*) from reply_user where reply_question_id = q.question_id) as commentCount FROM question q, tb_code tc, USER_ACCOUNT u WHERE 1 = 1 AND q.question_topic = tc.code_value AND q.question_raise_id = u.user_id ");
		if(topciId != null && topciId != ""){
			sql.append(" AND q.question_topic = "+topciId);
		}
		if(content != null && content != ""){
			sql.append(" AND (q.question_introduce like '%"+content+"%' or question_title like'%"+content+"%')");
		}
		sql.append(" ORDER BY q.question_create_time DESC");
		System.out.println(sql.toString());
		List<QuestionInfo> list = (List<QuestionInfo>) SqlSendUtil.selectList(conn, sql.toString(), null, QuestionInfo.class);
		return list;
	}

	public QuestionInfo getQuizDetail(Connection conn, Object obj[]){
		
		StringBuffer sbsql = new StringBuffer();
		sbsql.append("SELECT q.question_id questionId, q.question_title questionTitle, q.question_introduce questionIntroduce, q.question_type questionType, q.question_raise_id questionRaiseId, USER_ACCOUNT.user_name userName, q.question_create_time questionCreateTime, q.question_topic questionTopic, tc.code_name codeName FROM question q, tb_code tc, USER_ACCOUNT WHERE 1 = 1 ");
		sbsql.append("AND q.question_topic = tc.code_value AND q.question_id = ?");
		String sql = sbsql.toString();
		System.out.println(sql);
		
		return (QuestionInfo)SqlSendUtil.selectOne(conn, sql, obj, QuestionInfo.class);
	}
	//获取我的问题列表
	public List<QuestionInfo> getMyQuestion(Connection conn, int userID){
		Object[] obj = new Object[1];
		obj[0]=userID;
		String sql = "SELECT qn.question_id AS questionId, qn.question_title AS questionTitle, "
				+ "qn.question_introduce AS questionIntroduce, qn.question_type AS questionType,"
				+ "qn.question_create_time AS questionCreateTime,qn.question_topic AS questionTopic,"
				+ "qn.question_raise_id AS questionRaiseId FROM question qn WHERE qn.question_raise_id = ? "
				+ "AND qn.question_type = 1 ORDER BY qn.question_create_time DESC";

		List<QuestionInfo> list = (List<QuestionInfo>) SqlSendUtil.selectList(conn, sql, obj,MyReply.class);

		return list;
	}
	
	//获取我的回答过问题的问题列表
	public List<MyReply> getMyReplyQuestion(Connection conn, int userID){
		Object[] obj = {userID,userID};
		String sql = "SELECT qn.question_id AS questionId,qn.question_title AS questionTitle,"
				+ "qn.question_introduce AS questionIntroduce,qn.question_type AS questionType,"
				+ "CASE qn.question_type WHEN 1 THEN '问题' WHEN 2 THEN '文章' END AS questionTypeFY,"
				+ "qn.question_raise_id AS questionRaiseId,ur.user_name AS userName,"
				+ "qn.question_create_time AS questionCreateTime,qn.question_topic AS questionTopic "
				+ "FROM question qn, USER_ACCOUNT ur WHERE qn.question_id IN (SELECT DISTINCT (r.reply_question_id)"
				+ "FROM reply_user r WHERE r.reply_send_person_id = ?) AND qn.question_type = 1 AND ur.user_id = ?";

		List<MyReply> list = (List<MyReply>) SqlSendUtil.selectList(conn, sql, obj,MyReply.class);
		return list;
	}
	
	//获取我的回答过问题的回复
	public List<MyReply> getMyReply(Connection conn, int userID){
		Object[] obj = new Object[1];
		obj[0]=userID;
		String sql = "SELECT r.reply_id AS replyId, r.reply_question_id AS replyQuestionId, "
				+ "r.reply_type AS replyType, r.reply_send_person_id AS replySendPersonId,"
				+ "r.reply_receive_id AS replyReceiveId,r.reply_content AS replyContent,"
				+ "r.reply_create_time AS replyCreateTime FROM reply_user r WHERE "
				+ "r.reply_send_person_id = ? AND r.reply_type = 1";

		List<MyReply> list = (List<MyReply>) SqlSendUtil.selectList(conn, sql, obj,MyReply.class);
		return list;
	}
	
}
