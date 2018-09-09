package com.zhihu.service.impl;

import java.sql.Connection;
import java.util.List;

import com.zhihu.dao.UserDao;
import com.zhihu.dao.pojo.User;
import com.zhihu.service.UserService;
import com.zhihu.util.JdbcUtils;

public class UserServiceImpl implements UserService {
	
	UserDao userDao = new UserDao();
	
	Connection conn = JdbcUtils.getInstance().getDateBase();
	@Override
	public List<User> getUserList() {
		
		List<User> list = userDao.getUserListToExport(conn);
		return list;
	}
}
