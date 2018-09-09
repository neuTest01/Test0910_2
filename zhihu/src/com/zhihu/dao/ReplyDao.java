package com.zhihu.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.zhihu.dao.pojo.Reply;
import com.zhihu.util.SqlSendUtil;

public class ReplyDao {

	public int insertReply(Connection conn, Object param[]) throws SQLException{
		String sql = "insert into reply_user (REPLY_ID,reply_question_id,reply_type, reply_send_person_id, reply_content, reply_create_time) values(REPLYSEQUENCES.nextval,?,?,?,?,sysdate)";
		SqlSendUtil.update(conn, sql, param);
		return 1;
	}
	
	public List<Reply> getReplyList(Connection conn, Object param[]){
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT ");
		sb.append("r.reply_id AS replyId,r.reply_question_id AS replyQuestionId,");
		sb.append("r.reply_type AS replyType,r.reply_send_person_id AS replySendPersonId,");
		sb.append("r.reply_content AS replyContent,r.reply_receive_id AS replyReceiveId,");
		sb.append("r.reply_create_time AS replyCreateTime,u.user_name AS replyUserName,");
		sb.append("u.user_content AS replyUserContent,f.file_url as replyUserImage,"); 
		sb.append("(select count(*) from thumbs where reply_id = r.reply_id and flag = 1) as zanCount, ");
		sb.append("(select count(*) from thumbs where reply_id = r.reply_id and flag = 1 and user_acount = ?) as zanFlag ");
		sb.append("FROM reply_user  r ");
		sb.append("LEFT JOIN USER_ACCOUNT u ON r.reply_send_person_id = u.user_id LEFT JOIN FILETABLE f ON u.user_image_id = f.file_id ");
		sb.append("WHERE r.reply_question_id = ? order by r.reply_create_time desc");
		System.out.println(sb.toString());
		return (List<Reply>)SqlSendUtil.selectList(conn, sb.toString(), param, Reply.class);
	}
}
