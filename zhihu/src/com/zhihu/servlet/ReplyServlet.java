package com.zhihu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zhihu.dao.pojo.User;
import com.zhihu.service.ReplyService;
import com.zhihu.service.impl.ReplyServiceImpl;

/**
 * Servlet implementation class ReplyServlet
 */
@WebServlet("/ReplyServlet")
public class ReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyServlet() {
        super();
    }

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
		//向客户端返回的内容类型html,utf_8
		response.setContentType("text/html;charset=utf-8");
		//获取一个打印流PrintWriter对象
		PrintWriter out = response.getWriter();
		//设置客户端请求的编码格式为utf-8
		request.setCharacterEncoding("utf-8");
		//获取请求参数
		String statu = request.getParameter("q");
		if("anwser".equals(statu)){
			giveAnswer(request,response,out);
		}
		
	}
	protected void giveAnswer(HttpServletRequest request, HttpServletResponse response, PrintWriter out) {
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("userInfo");
		
		//获取表单信息
		int sendPersonId = user.getUserId();//回答者ID
		String replyContent = request.getParameter("myReply");//回答内容
		String questionId=request.getParameter("questionId");

		Object[] objs = { questionId, 1 ,sendPersonId, replyContent};
		
		ReplyService replyService = new ReplyServiceImpl();

		String msg = "";
		try {
			int row = replyService.insertReply(objs);
			if( row > 0 ){
				msg = "回答成功";
			} else {
				msg = "回答失败";
			}
		} catch (Exception e) {
			msg = "回答失败";
			e.printStackTrace();
		} finally {
			out.print(msg);
			out.flush();
			out.close();
		}
		
	}

}
