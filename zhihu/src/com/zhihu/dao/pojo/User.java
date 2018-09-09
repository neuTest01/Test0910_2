package com.zhihu.dao.pojo;

import java.util.Date;

public class User {

	private Integer userId;				//主键id
	private String userName;			//姓名
	private Integer userGender;			//性别
    private String userContent;			//简介（短）
    private String userAddress;			//地址
    private String userIndustry;		//所在行业
    private String userOccupation;		//职业经历
    private String userEducation;		//教育经历
    private String userBirthday;		//出生年月
    private String userIntroduce;		//个人简介（长）
    private Integer userImageId;		//个人头像id
    private Integer userCoverId;		//封面照片id
	private String userAccount;			//账号
	private String userPassword;		//密码
	private Date userCreateTime;		//创建时间
	private String userEmail;           //用户邮箱
	private String userPhone;           //用户手机号
	
	private String code;//验证码
	private String imageUrl;//头像路径
	private String[] topicList; //用户关注的话题
    
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getUserGender() {
		return userGender;
	}
	public void setUserGender(Integer userGender) {
		this.userGender = userGender;
	}
	public String getUserContent() {
		return userContent;
	}
	public void setUserContent(String userContent) {
		this.userContent = userContent;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserIndustry() {
		return userIndustry;
	}
	public void setUserIndustry(String userIndustry) {
		this.userIndustry = userIndustry;
	}
	public Integer getUserImageId() {
		return userImageId;
	}
	public void setUserImageId(Integer userImageId) {
		this.userImageId = userImageId;
	}
	public Integer getUserCoverId() {
		return userCoverId;
	}
	public void setUserCoverId(Integer userCoverId) {
		this.userCoverId = userCoverId;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public Date getUserCreateTime() {
		return userCreateTime;
	}
	public void setUserCreateTime(Date userCreateTime) {
		this.userCreateTime = userCreateTime;
	}
	public String getUserOccupation() {
		return userOccupation;
	}
	public void setUserOccupation(String userOccupation) {
		this.userOccupation = userOccupation;
	}
	public String getUserEducation() {
		return userEducation;
	}
	public void setUserEducation(String userEducation) {
		this.userEducation = userEducation;
	}
	public String getUserBirthday() {
		return userBirthday;
	}
	public void setUserBirthday(String userBirthday) {
		this.userBirthday = userBirthday;
	}
	public String getUserIntroduce() {
		return userIntroduce;
	}
	public void setUserIntroduce(String userIntroduce) {
		this.userIntroduce = userIntroduce;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String[] getTopicList() {
		return topicList;
	}
	public void setTopicList(String[] topicList) {
		this.topicList = topicList;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName
				+ ", userGender=" + userGender + ", userContent=" + userContent
				+ ", userAddress=" + userAddress + ", userIndustry="
				+ userIndustry + ", userOccupation=" + userOccupation
				+ ", userEducation=" + userEducation + ", userBirthday="
				+ userBirthday + ", userIntroduce=" + userIntroduce
				+ ", userImageId=" + userImageId + ", userCoverId="
				+ userCoverId + ", userAccount=" + userAccount
				+ ", userPassword=" + userPassword + ", userCreateTime="
				+ userCreateTime + ", code=" + code + "]";
	}
    
}
