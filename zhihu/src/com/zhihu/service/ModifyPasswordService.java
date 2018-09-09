package com.zhihu.service;

import java.sql.SQLException;

import com.zhihu.dao.pojo.User;

public interface ModifyPasswordService {

	User getPasswordByAccount(String account) throws SQLException;
	
	int modifyPassword(User user) throws SQLException;
	
}
