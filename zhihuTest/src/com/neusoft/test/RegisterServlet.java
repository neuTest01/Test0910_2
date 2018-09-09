package com.neusoft.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.fileter.Session1;
import com.neusoft.fileter.Session2;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/html;charset=utf-8");


//		 request.setAttribute("aa", "aaa");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
//		String name=request.getParameter("name");
//		request.setAttribute("aa", "bbb");
//		request.removeAttribute("aa");
		 request.getSession().setAttribute("sessionbindinglistener", new Session2(username));
		 request.getSession().setAttribute("sessionactivelistener", new Session1(username));
		 
		response.getWriter().append("Served at: "+username+password).append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
