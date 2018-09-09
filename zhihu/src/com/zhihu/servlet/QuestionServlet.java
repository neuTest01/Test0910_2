package com.zhihu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONArray;
import com.zhihu.dao.pojo.MyReply;
import com.zhihu.dao.pojo.QuestionInfo;
import com.zhihu.dao.pojo.Reply;
import com.zhihu.dao.pojo.Topic;
import com.zhihu.dao.pojo.User;
import com.zhihu.service.QuestionService;
import com.zhihu.service.ReplyService;
import com.zhihu.service.impl.QuestionServiceImpl;
import com.zhihu.service.impl.ReplyServiceImpl;

@WebServlet("/questionServlet")
public class QuestionServlet extends HttpServlet{
	
	private static final long serialVersionUID = 7092111489797837904L;
	
	private QuestionService questionService = new QuestionServiceImpl();
	private ReplyService replyService = new ReplyServiceImpl();

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
		if("askQuestion".equals(statu)){
			doAskQuestion(request,response,out);
		} else if("delQuestion".equals(statu)){
			doDelQuestion(request,response,out);
		} else if("getHomePageList".equals(statu)){
			getHomePageList();
		} else if("attentionTopic".equals(statu)){
			attentionTopic(request,response,out);
		} else if("getTopicList".equals(statu)){
			getTopicList(request,response,out);
		} else if("getTopicQuestionList".equals(statu)){
			getTopicQuestionList(request,response,out);
		} else if("getQuizDetail".equals(statu)){
			getQuizDetail(request,response);
		} else if("getAllTopic".equals(statu)){
            getAllTopic(out);
		}else if("getMyQuestion".equals(statu)){
			getMyQuestion(request,response,out);
		}else if("getMyReply".equals(statu)){
			getMyReply(request,response,out);
		}
		
	}
	
	protected void doAskQuestion(HttpServletRequest request,
			HttpServletResponse response,PrintWriter out) {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("userInfo");
		//获取请求参数
		String questionIntroduce = request.getParameter("questionIntroduce");
		String questionType = request.getParameter("questionType");
		String questionTitle = request.getParameter("questionTitle");
		String questionTopic = request.getParameter("questionTopic");
		Integer questionRaiseId = user.getUserId();
		Object[] objs = {questionTitle, questionIntroduce, questionType, questionRaiseId,questionTopic};
		String msg = "";
		//QuestionService questionService = new QuestionServiceImpl();
		int row = questionService.insertQuestionInfo(objs);
		if( row > 0 ){
			msg = "提问成功";
		} else {
			msg = "提问失败";
		}
		out.write(msg);
		out.flush();
		out.close();
	}
	
	protected void doDelQuestion(HttpServletRequest request,
			HttpServletResponse response,PrintWriter out) {
		//获取请求参数
		String questionId = request.getParameter("questionId");
		Object[] objs = {questionId};
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	/**
	 * 获取首页问题列表
	 * @return
	 */
	protected List<QuestionInfo> getHomePageList(){
		return questionService.getHomePageList();
	}
	
	/**
	 * 查询问题详情
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void getQuizDetail(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException{
		String quizId = request.getParameter("quizId");
		User user = (User) request.getSession().getAttribute("userInfo");
		Object[] obj = {quizId};
		Object[] param = {user.getUserAccount(),quizId};
		request.setAttribute("quizDetail", questionService.getQuizDetail(obj));
		request.setAttribute("replyList", replyService.getReplyList(param));
		request.getRequestDispatcher("/page/questions.jsp").forward(request, response);
	}
	/**
	 * 关注话题接口
	 */
	protected void attentionTopic(HttpServletRequest request,
			HttpServletResponse response,PrintWriter out){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("userInfo");
		//获取入参
		String topicId = request.getParameter("topicId");
		Integer userId = user.getUserId();
		Object[] obj = {topicId, userId};
		try {
			questionService.attentionTopic(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询用户关注的话题
	 * @return
	 */
	protected void getTopicList(HttpServletRequest request,
			HttpServletResponse response,PrintWriter out){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("userInfo");
		//获取入参
		Integer userId = user.getUserId();
		Object[] obj = {userId};
		try {	
			List<Topic> list = questionService.getTopicList(obj);
			out.println(JSONArray.toJSON(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			out.flush();
			out.close();
		}
	}
	
	/**
	 * 根据话题查询问题列表
	 */
	protected void getTopicQuestionList(HttpServletRequest request,
			HttpServletResponse response,PrintWriter out){
		String topciId = request.getParameter("topciId");
		String content = request.getParameter("content");
		List<QuestionInfo> list = questionService.getTopicQuestionList(topciId,content);
		out.println(JSONArray.toJSON(list));
		out.flush();
		out.close();
	}

    /**
     * 查询所有话题
     */

    private void getAllTopic(PrintWriter out){
        Object[] obj = {};
        try {
            List<Topic> list = questionService.getTopicList(obj);
            out.println(JSONArray.toJSON(list));
            out.flush();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            out.close();
        }
    }
    
    

    /**
     * 查询我提问的问题
     */

    private void getMyQuestion(HttpServletRequest request,
			HttpServletResponse response,PrintWriter out){

    	HttpSession session = request.getSession();
    	User user = (User)session.getAttribute("userInfo");
    	int userID = user.getUserId();
    	try{
    		List<QuestionInfo> myQuestionList = new ArrayList<QuestionInfo>();
    		myQuestionList = questionService.getMyQuestion(userID);
    		out.println(JSONArray.toJSON(myQuestionList));
            out.flush();
    	}catch(Exception e){
    		 e.printStackTrace();
    	}finally {
            out.close();
        }
    }
    
    /**
     * 查询我回答的问题
     */

    private void getMyReply(HttpServletRequest request,
			HttpServletResponse response,PrintWriter out){
    	HttpSession session = request.getSession();
    	User user = (User)session.getAttribute("userInfo");
    	int userID = user.getUserId();
    	try{
    		List<MyReply> questionList = new ArrayList<MyReply>();
    		List<MyReply> replyList = new ArrayList<MyReply>();
    		questionList = questionService.getReplyQuestion(userID);
    		replyList = questionService.getMyReply(userID);
    		for(int i=0;i<questionList.size();i++){
    			int questionId=questionList.get(i).getQuestionId();
    			String questionTitle = questionList.get(i).getQuestionTitle();
    			String questionIntroduce = questionList.get(i).getQuestionIntroduce();
    			String questionType = questionList.get(i).getQuestionType();
    			int questionRaiseId = questionList.get(i).getQuestionRaiseId();
    			Date questionCreateTime = questionList.get(i).getQuestionCreateTime();
    			List<MyReply> listReplyLS = new ArrayList<MyReply>();
    			for(int j=0;j<replyList.size();j++){
    				if(questionId==replyList.get(j).getReplyQuestionId()){
    					listReplyLS.add(replyList.get(j));
    				}
    			}
    			questionList.get(i).setList(listReplyLS);
    		}
    		out.println(JSONArray.toJSON(questionList));
            out.flush();
    	}catch(Exception e){
    		 e.printStackTrace();
    	}finally {
            out.close();
        }
    }
    
    
    
}
