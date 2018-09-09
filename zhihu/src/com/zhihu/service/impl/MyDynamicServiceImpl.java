package com.zhihu.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zhihu.dao.MyDynamicDao;
import com.zhihu.dao.QuestionDao;
import com.zhihu.dao.pojo.MyDynamic;
import com.zhihu.service.MyDynamicService;
import com.zhihu.util.JdbcUtils;

public class MyDynamicServiceImpl implements MyDynamicService {
	
	MyDynamicDao myDynamicDao = new MyDynamicDao();
	Connection conn = JdbcUtils.getInstance().getDateBase();
	
	@Override
	public List<MyDynamic> getMyDynamicList(int userID) {
		List<MyDynamic> list = new ArrayList<MyDynamic>();
		try {
			list= myDynamicDao.getMyDynamicList(conn, userID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			list=null;
			e.printStackTrace();
		}
		return list;
	}

}
