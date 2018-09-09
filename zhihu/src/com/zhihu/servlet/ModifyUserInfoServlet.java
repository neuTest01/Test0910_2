package com.zhihu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zhihu.dao.pojo.User;
import com.zhihu.service.LoginService;
import com.zhihu.service.impl.LoginServiceImpl;

/**
 * 修改用户信息servlet
 */
@WebServlet("/modifyUserInfoServlet")
public class ModifyUserInfoServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private LoginService loginService = new LoginServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("userInfo");
		//获取入参信息
		Integer userId = user.getUserId();
		Integer userGender = Integer.valueOf(request.getParameter("userGender"));
		String userContent = request.getParameter("userContent");
		String userAddress = request.getParameter("userAddress");
		String userIndustry = request.getParameter("userIndustry");
		String userOccupation = request.getParameter("userOccupation");
		String userEducation = request.getParameter("userEducation");
		String userBirthday = request.getParameter("userBirthday");
		String userIntroduce = request.getParameter("userIntroduce");
		String userPhone =  request.getParameter("userPhone");
		String userEmail= request.getParameter("userEmail");
		String[] topicList = request.getParameter("topicList").split(",");
		/*Integer userImageId = Integer.valueOf(request.getParameter("userImageId"));
		Integer userCoverId = Integer.valueOf(request.getParameter("userCoverId"));*/
		
		//将修改的用户信息封装成一个User对象
		User u = new User();
		u.setUserId(userId);
		u.setUserGender(userGender);
		u.setUserContent(userContent);
		u.setUserAddress(userAddress);
		u.setUserIndustry(userIndustry);
		u.setUserOccupation(userOccupation);
		u.setUserEducation(userEducation);
		u.setUserBirthday(userBirthday);
		u.setUserIntroduce(userIntroduce);
		u.setTopicList(topicList);
		u.setUserPhone(userPhone);
		u.setUserEmail(userEmail);
		/*u.setUserImageId(userImageId);
		u.setUserCoverId(userCoverId);*/
		
		User queryUser = new User();
		queryUser.setUserId(userId);
		
		try {
			loginService.modifyUserInfo(u);
			session.setAttribute("userInfo", loginService.Login(queryUser));
			PrintWriter out = response.getWriter();
			out.write("修改成功");
			out.flush();
			out.close();
		} catch (Exception e) {
			PrintWriter out = response.getWriter();
			out.write("修改失败");
			out.flush();
			out.close();
			e.printStackTrace();
		}
		
	}
	
}
