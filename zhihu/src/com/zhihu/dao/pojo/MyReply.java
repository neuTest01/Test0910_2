package com.zhihu.dao.pojo;

import java.util.Date;
import java.util.List;

public class MyReply{
	private int replyId;//主键
	private int replyQuestionId;//问题ID
	private int replySendPersonId;//发起人ID
	private int replyReceiveId;//接受者ID
	private int replyType;//回复类型（1：回答 2：评论）
	private String replyContent;//回答内容
	private int replySessionId;//会话ID
	private int replySessionFlag;//回复内标识（区分显示内容）
	private Date replyCreateTime;//创建时间
	
	private String replyUserName;	//回复用户名
	private String replyUserContent;//
	private String replyZanCount;	//回复赞
	private String replyZanFlag;	//回复赞值
	
	private Integer questionId;        //问题ID
	private String questionTitle;      //问题标题
	private String questionIntroduce;  //问题说明
	private String questionType;       //类型（1：问题 2：文章）
	private Integer questionRaiseId;  //问题发起者ID
	private String userName;			//提问者名称
	private Date questionCreateTime;  //问题创建时间
	private Integer questionTopic;		//问题话题code
	private String codeName;			//话题名称
	private String commentCount;            //评论数量
	private int questionZanCount;		//问题赞
	private String questionTypeFY;		//问题类型翻译
	private List<MyReply> list;			//回复的list

	public List<MyReply> getList() {
		return list;
	}
	public void setList(List<MyReply> list) {
		this.list = list;
	}
	public int getReplyId() {
		return replyId;
	}
	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}
	public int getReplyQuestionId() {
		return replyQuestionId;
	}
	public void setReplyQuestionId(int replyQuestionId) {
		this.replyQuestionId = replyQuestionId;
	}
	public int getReplySendPersonId() {
		return replySendPersonId;
	}
	public void setReplySendPersonId(int replySendPersonId) {
		this.replySendPersonId = replySendPersonId;
	}
	public int getReplyReceiveId() {
		return replyReceiveId;
	}
	public void setReplyReceiveId(int replyReceiveId) {
		this.replyReceiveId = replyReceiveId;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public int getReplySessionId() {
		return replySessionId;
	}
	public void setReplySessionId(int replySessionId) {
		this.replySessionId = replySessionId;
	}
	public int getReplySessionFlag() {
		return replySessionFlag;
	}
	public void setReplySessionFlag(int replySessionFlag) {
		this.replySessionFlag = replySessionFlag;
	}
	public Date getReplyCreateTime() {
		return replyCreateTime;
	}
	public void setReplyCreateTime(Date replyCreateTime) {
		this.replyCreateTime = replyCreateTime;
	}
	public int getReplyType() {
		return replyType;
	}
	public void setReplyType(int replyType) {
		this.replyType = replyType;
	}
	public String getReplyUserName() {
		return replyUserName;
	}
	public void setReplyUserName(String replyUserName) {
		this.replyUserName = replyUserName;
	}
	public String getReplyUserContent() {
		return replyUserContent;
	}
	public void setReplyUserContent(String replyUserContent) {
		this.replyUserContent = replyUserContent;
	}
	public String getReplyZanCount() {
		return replyZanCount;
	}
	public void setReplyZanCount(String replyZanCount) {
		this.replyZanCount = replyZanCount;
	}
	public String getReplyZanFlag() {
		return replyZanFlag;
	}
	public void setReplyZanFlag(String replyZanFlag) {
		this.replyZanFlag = replyZanFlag;
	}
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	public String getQuestionTitle() {
		return questionTitle;
	}
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	public String getQuestionIntroduce() {
		return questionIntroduce;
	}
	public void setQuestionIntroduce(String questionIntroduce) {
		this.questionIntroduce = questionIntroduce;
	}
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	public Integer getQuestionRaiseId() {
		return questionRaiseId;
	}
	public void setQuestionRaiseId(Integer questionRaiseId) {
		this.questionRaiseId = questionRaiseId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getQuestionCreateTime() {
		return questionCreateTime;
	}
	public void setQuestionCreateTime(Date questionCreateTime) {
		this.questionCreateTime = questionCreateTime;
	}
	public Integer getQuestionTopic() {
		return questionTopic;
	}
	public void setQuestionTopic(Integer questionTopic) {
		this.questionTopic = questionTopic;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(String commentCount) {
		this.commentCount = commentCount;
	}
	public int getQuestionZanCount() {
		return questionZanCount;
	}
	public void setQuestionZanCount(int questionZanCount) {
		this.questionZanCount = questionZanCount;
	}
	public String getQuestionTypeFY() {
		return questionTypeFY;
	}
	public void setQuestionTypeFY(String questionTypeFY) {
		this.questionTypeFY = questionTypeFY;
	}
	
}
