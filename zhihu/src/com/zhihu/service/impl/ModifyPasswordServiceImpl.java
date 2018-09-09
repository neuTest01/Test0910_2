package com.zhihu.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.zhihu.dao.UserDao;
import com.zhihu.dao.pojo.User;
import com.zhihu.service.ModifyPasswordService;
import com.zhihu.util.JdbcUtils;

public class ModifyPasswordServiceImpl implements ModifyPasswordService {
	
	UserDao userDao = new UserDao();
	Connection conn = JdbcUtils.getInstance().getDateBase();
	
	@Override
	public int modifyPassword(User user) throws SQLException {
		int result = userDao.modifyPassword(conn, user);
		return result;
	}

	@Override
	public User getPasswordByAccount(String account) throws SQLException {
		User user = userDao.getPasswordByAccount(conn, account);
		return user;
	}

}
