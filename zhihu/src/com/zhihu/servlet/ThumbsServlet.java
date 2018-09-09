package com.zhihu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zhihu.dao.pojo.User;
import com.zhihu.service.ThumbsService;
import com.zhihu.service.impl.ThumbsServiceImpl;


@WebServlet("/ThumbsServlet")
public class ThumbsServlet extends HttpServlet{
	
	private static final long serialVersionUID = 7092111489797837904L;
	
	private ThumbsService thumbsService = new ThumbsServiceImpl();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//向客户端返回的内容类型html,utf_8
		response.setContentType("text/html;charset=utf-8");
		//获取一个打印流PrintWriter对象
		PrintWriter out = response.getWriter();
		//设置客户端请求的编码格式为utf-8
		request.setCharacterEncoding("utf-8");
		//获取请求标识
		String statu = request.getParameter("q");
		if("ThumbsUp".equals(statu)){
			ThumbsUp(request,response,out);
		} else if("getFllowStatus".equals(statu)){
			getFllowStatus(request,response,out);
		} 
	}
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	//用户点赞按钮方法
	protected void ThumbsUp(HttpServletRequest request,
			HttpServletResponse response,PrintWriter out){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("userInfo");
		int userAccount = user.getUserId();
		String questionId = request.getParameter("questionId");
		String replyId = request.getParameter("replyId");
		String flag = request.getParameter("flag");
		Object[] obj = {questionId,userAccount,replyId,flag};
		//查询下该用户是否关注过
		int count = thumbsService.getFllowNumber(obj);
		//判断，如果用户没有关注，则新增一条记录，如果用户有关注过，通过flag来判断用户的动作。
		if(count>0){
			thumbsService.updateFlag(new Object[]{flag,questionId,userAccount,replyId});
		}else{
			thumbsService.saveFllowInfo(obj);
		}
	}
	//获取用户点赞
	protected Map getFllowStatus(HttpServletRequest request,
			HttpServletResponse response,PrintWriter out){
		Map map = new HashMap();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("userInfo");
		int userAccount = user.getUserId();
		String questionId = request.getParameter("questionId");
		String flag = "2";//2代表从未点过赞
		Object[] obj = {userAccount,questionId};
		//查询下该用户是否关注过
		int count = thumbsService.getFllowNumber(obj);
		//判断，如果用户没有关注，则新增一条记录，如果用户有关注过，通过flag来判断用户的动作。
		if(count>0){
			flag=thumbsService.getFllowFlag(obj);
		}
		map.put("flag", flag);
		
		//查询问题的总赞个数
		int counts = thumbsService.getFllowNumbers(obj);
		map.put("counts", counts);
		
		//查询问题的回复总数
		int replayCounts = thumbsService.getReplayNumbers(obj);
		map.put("replayCounts", replayCounts);
		
		return map;
	}
	
		
}
