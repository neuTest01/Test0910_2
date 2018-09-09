package com.zhihu.service;

import java.sql.SQLException;
import com.zhihu.dao.pojo.User;

public interface LoginService {

	int register(User user)  throws SQLException;
	
	User Login(User user);
	
	int testUserAcc(User user);

	/**
	 * 修改用户信息
	 * @param user
	 */
	void modifyUserInfo(User user) throws SQLException;

	void insertStatistics(Object[] object) throws SQLException;
}
