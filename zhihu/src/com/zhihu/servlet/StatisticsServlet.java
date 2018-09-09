package com.zhihu.servlet;

import com.alibaba.fastjson.JSONObject;
import com.zhihu.dao.pojo.User;
import com.zhihu.service.StatisticsService;
import com.zhihu.service.impl.StatisticsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/statisticsServlet")
public class StatisticsServlet extends HttpServlet {

    StatisticsService statisticsService = new StatisticsServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userInfo");
        Object[] objects = {user.getUserId()};
        PrintWriter out = response.getWriter();
        out.print(JSONObject.toJSONString(statisticsService.getStatisticsList(objects)));
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
