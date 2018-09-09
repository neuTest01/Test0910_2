package com.zhihu.service;

import java.util.List;
public interface ThumbsService {
	


	/**
	 * 查询是否点赞
	 */
	int getFllowNumber(Object param[]);
	/**
	 * 查询点赞个数
	 */
	int getFllowNumbers(Object param[]);
	/**
	 * 获得问题评论数量
	 */
	int getReplayNumbers(Object param[]);
	/**
	 * 获取用户的点赞信息
	 */
	String getFllowFlag(Object param[]);
	/**
	 * 更新点赞flag
	 */
	void updateFlag(Object param[]);
	/**
	 * 保存点赞信息
	 */
	void saveFllowInfo(Object param[]);

}
