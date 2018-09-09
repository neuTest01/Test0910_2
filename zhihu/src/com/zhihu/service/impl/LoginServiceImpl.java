package com.zhihu.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import com.zhihu.dao.UserDao;
import com.zhihu.dao.pojo.User;
import com.zhihu.service.LoginService;
import com.zhihu.util.JdbcUtils;

public class LoginServiceImpl implements LoginService{

	UserDao userDao = new UserDao();
	
	Connection conn = JdbcUtils.getInstance().getDateBase();
	
	/*
	 * 注册功能
	 * 先验证用户名是否存在 存在 1 不存在 0
	 */
	
	@Override
	public int register(User user) throws SQLException{
		int result = 0;
		if(testUserAcc(user) == 0){
			try {
				result = userDao.insertUser(conn, user);
			} catch (SQLException e) {
				conn.rollback();
				throw e;
			}
		}
		
		return result;
	}
	
    /*
     * 登录功能
     */
	
	@Override
	public User Login(User user){
		User user2 = null;
		try{
			conn = JdbcUtils.getInstance().getDateBase();
			user2 = userDao.selectUserInfor(conn, user);
		}catch(Exception e){
			e.printStackTrace();
		}
		return user2;
	}

	/*
	 * 验证用户名是否存在 存在 1 不存在 0
	 */
	//这个方法没有关闭数据库连接
	@Override
	public int testUserAcc(User user){
		User userBefore = new User();
		userBefore.setUserAccount(user.getUserAccount());
		User user2 = null;
		try{
			conn = JdbcUtils.getInstance().getDateBase();
			user2 = userDao.selectUserInfor(conn, userBefore);
		}catch(Exception e){
			e.printStackTrace();
		}
	    if(user2 == null){
	    	return 0;
	    }else{
	    	return 1;
	    }
	}

	/**
	 * 修改用户信息
	 * @throws SQLException 
	 */
	@Override
	public void modifyUserInfo(User user) throws SQLException {
		try{
			conn = JdbcUtils.getInstance().getDateBase();
			userDao.updateUser(conn, user);
			userDao.updateAttention(conn,user);
		} catch (SQLException e){
			conn.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void insertStatistics(Object[] object) throws SQLException{
		userDao.insertStatistics(conn,object);
	}
}
