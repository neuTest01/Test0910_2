package com.zhihu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zhihu.dao.pojo.Page;
import com.zhihu.service.PageService;
import com.zhihu.service.impl.PageServiceImpl;

/**
 * Servlet implementation class paginationServlet
 */
@WebServlet("/paginationServlet")
public class PaginationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private PageService pageService = new PageServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaginationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int pageSize = Integer.parseInt(request.getParameter("pageSize")==null?"8":request.getParameter("pageSize"));
		int currentPage = Integer.parseInt(request.getParameter("currentPage")==null?"1":request.getParameter("currentPage"));
		Page p = new Page();
		p.setPageSize(pageSize);
		p.setCurrentPage(currentPage);
		p = pageService.getUserPage(p);
		request.setAttribute("page",p);
		request.getRequestDispatcher("page/pagination.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
