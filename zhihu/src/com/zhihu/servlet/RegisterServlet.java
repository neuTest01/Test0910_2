package com.zhihu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhihu.dao.pojo.User;
import com.zhihu.service.LoginService;
import com.zhihu.service.impl.LoginServiceImpl;
import com.zhihu.util.MD5Util;


/**
 * 注册servlet
 */
@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	private LoginService loginService = new LoginServiceImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws 

ServletException, IOException {
    	doPost(request, response);
	}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws 

ServletException, IOException {
    	
    	PrintWriter out = response.getWriter();
    	
    	//通过request对象获取表单提交的数据
        String account = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmpassword");
        String name = request.getParameter("name");
        
        //手机、邮箱正则表达式校验
        Pattern phone = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$"); 
        Pattern mail = Pattern.compile("^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w{2,3}){1,3})$");
        Matcher p = phone.matcher(account);
        Matcher m = mail.matcher(account);
        if(!p.matches()&&!m.matches()){
        	out.println("<script>alert('请填写正确的手机号或邮箱');history.back();</script>");
        	return;
        }
        //判断两次密码是否一致
        if(!password.equals(confirmPassword)){
        	out.println("<script>alert('两次密码不一致');history.back();</script>");
        	return;
        }
        
        
        
        //将用户注册信息封装成一个User对象
        User user = new User();
        user.setUserAccount(account);
        user.setUserPassword(MD5Util.getMD5(password));
        user.setUserName(name);
        
        int result;
        try {
        	//执行注册方法，返回值0表示账号已存在，返回值1表示注册成功
        	result = loginService.register(user);
        	if(result==0){
        		out.println("<script>alert('用户名已存在');history.back();</script>");
        		return;
        	}
        	//注册成功，request转发回登录页面
        	request.getRequestDispatcher("page/login.jsp").forward(request,response);
		} catch (SQLException e) {
			//注册失败
			e.printStackTrace();
			result = 0;
			out.println("<script>alert('注册失败');</script>");     	
			out.flush();
			out.close();
		}
    }
}
