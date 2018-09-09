package com.zhihu.dao.pojo;

public class Thumbs {
	
	private Long id;        			//GUID
	private String questionId;         //GUID
	private String replyId;         //GUID
	private String userAcount;         //GUID
	private String createTime;         //GUID
	private String flag;        		//是否点赞 1：点赞 0：取消点赞

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getReplyId() {
		return replyId;
	}

	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}

	public String getUserAcount() {
		return userAcount;
	}

	public void setUserAcount(String userAcount) {
		this.userAcount = userAcount;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
}
