package com.zhihu.dao.pojo;

import java.util.Date;

public class Reply{
	private int replyId;//主键
	private int replyQuestionId;//问题ID
	private int replySendPersonId;//发起人ID
	private int replyReceiveId;//接受者ID
	private int replyType;//回复类型（1：回答 2：评论）
	private String replyContent;//回答内容
	private int replySessionId;//会话ID
	private int replySessionFlag;//回复内标识（区分显示内容）
	private Date replyCreateTime;//创建时间
	
	private String replyUserName;
	private String replyUserContent;
	private String replyUserImage;
	
	private String zanCount;
	private String zanFlag;

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
	public String getReplyUserImage() {
		return replyUserImage;
	}
	public void setReplyUserImage(String replyUserImage) {
		this.replyUserImage = replyUserImage;
	}
	

	public String getZanCount() {
		return zanCount;
	}

	public void setZanCount(String zanCount) {
		this.zanCount = zanCount;
	}

	public String getZanFlag() {
		return zanFlag;
	}

	public void setZanFlag(String zanFlag) {
		this.zanFlag = zanFlag;
	}
}
