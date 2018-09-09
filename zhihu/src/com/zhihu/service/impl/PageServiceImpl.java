package com.zhihu.service.impl;

import com.zhihu.dao.FileDao;
import com.zhihu.dao.UserDao;
import com.zhihu.dao.pojo.Page;
import com.zhihu.dao.pojo.User;
import com.zhihu.service.PageService;
import com.zhihu.util.JdbcUtils;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Administrator on 2017-04-19.
 */
public class PageServiceImpl implements PageService{

    Connection conn = JdbcUtils.getInstance().getDateBase();
    UserDao userDao = new UserDao();
	@Override
	public Page<User> getUserPage(Page p) {
		
		Page<User> page = new Page<User>();
		List<User> list = userDao.getUserByPage(conn, p.getCurrentPage(), p.getPageSize());
		int count = Integer.parseInt(String.valueOf(userDao.total(conn)));
		int totalPage = count%p.getPageSize()>0?count/p.getPageSize()+1:count/p.getPageSize();
		page.setCurrentPage(p.getCurrentPage());
		page.setPageSize(p.getPageSize());
		page.setTotalPage(totalPage);
		page.setList(list);
		return page;
		
	}
    
}
