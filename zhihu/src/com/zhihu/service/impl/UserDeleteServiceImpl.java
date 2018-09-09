package com.zhihu.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.zhihu.dao.UserDeleteDao;
import com.zhihu.service.UserDeleteService;
import com.zhihu.util.JdbcUtils;

public class UserDeleteServiceImpl  implements UserDeleteService{

	Connection conn = JdbcUtils.getInstance().getDateBase();
	UserDeleteDao userDeleteDao = new UserDeleteDao();
	@Override
	public int DeleteUserInfo(String param) {
		
		int row = 0;
		try {
			row = userDeleteDao.DeleteUserInfo(conn, param);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;

	}

}
