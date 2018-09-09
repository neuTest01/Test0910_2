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

import com.alibaba.fastjson.JSONObject;
import com.zhihu.dao.pojo.User;
import com.zhihu.service.UserDeleteService;
import com.zhihu.service.impl.UserDeleteServiceImpl;
@WebServlet("/userDeleteServlet")
public class UserDeleteServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private UserDeleteService userDeleteService = new UserDeleteServiceImpl();
	public UserDeleteServlet(){
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userIds = request.getParameter("userIds");

		userDeleteService.DeleteUserInfo(userIds);
        PrintWriter out = response.getWriter();
        out.print(JSONObject.toJSONString(""));
        out.flush();
        out.close();
	}
	
}
