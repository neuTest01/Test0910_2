package com.zhihu.service;

import com.zhihu.dao.pojo.Page;
import com.zhihu.dao.pojo.User;

public interface PageService {

	Page<User> getUserPage(Page page);
	
}
