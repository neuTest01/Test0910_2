package com.zhihu.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zhihu.dao.pojo.User;
import com.zhihu.service.LoginService;
import com.zhihu.service.impl.LoginServiceImpl;
import com.zhihu.util.MD5Util;

/**
 * 登录
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private LoginService loginService = new LoginServiceImpl();

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
		HttpSession session = request.getSession(true);
		
        String account = request.getParameter("account");//用户名
        String password = request.getParameter("password");//密码
        String code = request.getParameter("code"); //验证码
        String sessionCode = (String) session.getAttribute("code");//生成的图片验证码
		String ip = getRemoteIP(request);

        //将用户登录信息封装成一个User对象
	    User user = new User();
	    user.setUserAccount(account);
	    user.setUserPassword(MD5Util.getMD5(password));
	    user.setCode(code);
	    
        //判断验证码是否正确
		if (!sessionCode.equalsIgnoreCase(code)) {
			response.getWriter().println("<script>alert('验证码错误');history.back();</script>");
			return;
		}
         
        //根据用户名、密码获得用户信息
        User loginUser = loginService.Login(user);

        if(loginUser != null){
        	//用户名、密码正确,创建Cookie
        	Cookie c=new Cookie("users", account+"-"+password);
            //设置过期时间
            c.setMaxAge(600);
            //存储
            response.addCookie(c);
            //保存用户信息到session
            if(loginUser.getImageUrl()==null){
            	loginUser.setImageUrl("/zhihu/images/userinfosmall.jpg");
            }
			Object[] objects = {loginUser.getUserId(),ip};
			try {
				loginService.insertStatistics(objects);
				session.setAttribute("userInfo", loginUser);
				//重定向到话题列表页面
				response.sendRedirect("page/topicdynamics.jsp");
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }else{
        	//用户名、密码错误
        	response.getWriter().println("<script>alert('用户名或密码错误');history.back();</script>");
        }
	}

	public static String getRemoteIP(HttpServletRequest request) {

		String ip = request.getHeader("x-forwarded-for");

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip.equals("0:0:0:0:0:0:0:1")) {
			ip = "本地";
		}
		return ip;
	}
}
