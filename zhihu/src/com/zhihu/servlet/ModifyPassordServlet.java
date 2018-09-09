package com.zhihu.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zhihu.dao.pojo.User;
import com.zhihu.service.ModifyPasswordService;
import com.zhihu.service.impl.ModifyPasswordServiceImpl;
import com.zhihu.util.MD5Util;

/**
 * 登录
 */
@WebServlet("/modifyPasswordServlet")
public class ModifyPassordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private ModifyPasswordService modifyPassordService = new ModifyPasswordServiceImpl();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String account = request.getParameter("account");//用户名
        String oldPassword = request.getParameter("oldPassword");//密码
        String newPassword = request.getParameter("newPassword"); //验证码
		
        try {
        	
			User user = modifyPassordService.getPasswordByAccount(account);
			//判断用户是否存在
			if(user.getUserPassword()==null||"".equals(user.getUserPassword())){
				//返回2表示用户不存在
				response.getWriter().print("2");
				return;
			}
			//判断原密码是否正确
			if(!MD5Util.getMD5(oldPassword).equals(user.getUserPassword())){
				//返回1表示原密码错误
				response.getWriter().print("1");
				return;
			}
			user.setUserAccount(account);
			user.setUserPassword(MD5Util.getMD5(newPassword));
			modifyPassordService.modifyPassword(user);
			//返回0表示修改成功
			response.getWriter().print("0");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
