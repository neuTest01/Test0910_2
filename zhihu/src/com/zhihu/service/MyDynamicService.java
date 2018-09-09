package com.zhihu.service;

import java.util.List;

import com.zhihu.dao.pojo.MyDynamic;

public interface MyDynamicService {

	/**
	 * 查询我的动态
	 */
	List<MyDynamic> getMyDynamicList(int userID);
}
