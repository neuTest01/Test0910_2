package com.zhihu.service;

import java.util.List;

import com.zhihu.dao.pojo.Reply;

public interface ReplyService {
	/**
	 * 回答问题
	 */
	int insertReply(Object param[]);
	
	
	/**
	 * 查询回复列表
	 * @param obj
	 * @return
	 */
	List<Reply> getReplyList(Object[] obj);
}
