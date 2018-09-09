package com.zhihu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.zhihu.dao.pojo.MyDynamic;
import com.zhihu.dao.pojo.User;
import com.zhihu.service.MyDynamicService;
import com.zhihu.service.impl.MyDynamicServiceImpl;


@WebServlet("/MyDynamicServlet")
public class MyDynamicServlet extends HttpServlet{
	
	private static final long serialVersionUID = 7092111489797837904L;
	
	private MyDynamicService myDynamicService = new MyDynamicServiceImpl();

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
		if("getMyDynamicList".equals(statu)){
			getMyDynamicList(request,response,out);
		} 
		//else if("getFllowStatus".equals(statu)){
//			getFllowStatus(request,response,out);
//		} 
	}
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void getMyDynamicList(HttpServletRequest request,
			HttpServletResponse response,PrintWriter out){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("userInfo");
		int userID = user.getUserId();
		List<MyDynamic> list = new ArrayList<MyDynamic>();
		list = myDynamicService.getMyDynamicList(userID);
		out.print(JSONObject.toJSONString(list));
		out.flush();
		out.close();
	}
	
}
