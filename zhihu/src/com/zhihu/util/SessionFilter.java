package com.zhihu.util;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Administrator on 2017-04-25.
 */
public class SessionFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("userInfo");
        String url = request.getRequestURI();
        if(url.indexOf("login.jsp")!=-1
            ||url.indexOf("register.jsp")!=-1
            ||url.indexOf("/css/")!=-1
            ||url.indexOf("/js/")!=-1
            ||url.indexOf("/images/")!=-1
            ||obj!=null
            ||url.indexOf("codeServlet")!=-1
            ||url.indexOf("loginServlet")!=-1
            ||url.indexOf("registerServlet")!=-1
            ||url.indexOf("modifyPasswordServlet")!=-1){
            chain.doFilter(req,resp);
        }else{
            response.sendRedirect(request.getContextPath()+"/page/login.jsp");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
