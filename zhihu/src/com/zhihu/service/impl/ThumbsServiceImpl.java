package com.zhihu.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.zhihu.dao.ThumbsDao;
import com.zhihu.service.ThumbsService;
import com.zhihu.util.JdbcUtils;


public class ThumbsServiceImpl implements ThumbsService {
	ThumbsDao thumbsDao = new ThumbsDao();
	Connection conn = JdbcUtils.getInstance().getDateBase();

	@Override
	public int getFllowNumber(Object[] param) {
		int i = 0;
		try {
			i= thumbsDao.getFllowNumber(conn, param);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public String getFllowFlag(Object[] param) {
		String flag = "2";
		try {
			flag = thumbsDao.getFllowFlag(conn, param);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public void updateFlag(Object[] param) {
		try {
			thumbsDao.updateFllowInfo(conn, param);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void saveFllowInfo(Object[] param) {
		try {
			thumbsDao.saveFllowInfo(conn, param);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public int getFllowNumbers(Object[] param) {
		int count = 0;
		try {
			count=thumbsDao.getFllowNumbers(conn, param);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int getReplayNumbers(Object[] param) {
		int count = 0;
		try {
			count=thumbsDao.getReplayNumbers(conn, param);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
}
