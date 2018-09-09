<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<% String path = request.getContextPath();%>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>
        <title>问题详细-知乎</title>
        <link rel="icon" href="../images/logo.jpg" type="image/x-icon">
        
        <link rel="stylesheet" type="text/css" href="css/questions.css" />
        <link rel="stylesheet" type="text/css" href="css/personal.css" />
        <link rel="stylesheet" type="text/css" href="css/index.css" />
        <link rel="stylesheet" type="text/css" href="css/Font-Awesome-3.2.1/css/font-awesome.css" />
        <script type="text/javascript" src="js/jquery-3.2.0.min.js"></script>
        <script type="text/javascript" src="js/questions.js"></script>
        <script type="text/javascript" src="js/raiseQuestions.js"></script>
    </head>
    <script type="text/javascript">
        var path = "<%=path%>";
    </script>
    <body>
        <header>
            <div class="header-inner">
                <div class="peologo"></div>
                <div class="menu">
                    <a href="#">首页</a>
                    <a href="<%=path %>/page/statistics.jsp">统计</a>
                    <a href="<%=path %>/page/topicdynamics.jsp">话题</a>
                </div>
                <div class="search">
                    <form action="">
                        <input class="search-input1" type="text" placeholder="搜索你感兴趣的内容..."/>
                        <div class="searbtn"></div>
                    </form>
                </div>  
                <div class="btnwen">提问</div>             
                <div class="info">
                    <a href="#" class="userinfo">
                        <span class="icon-bell-alt icon-large"></span>
                    </a>
                    <a href="#" class="userinfo"> 
                        <span class="icon-comments icon-large"></span>
                    </a>
                    <a href="<%=path %>/page/personal.jsp" class="userinfo"><img src="${userInfo.imageUrl}" width="35" height="35"/></a>
                    <a href="javascript:location.href='<%=path %>/logoutServlet'" class="userinfo">退出</a>
                </div>   
            </div>
        </header>
          <!-- 知乎头部开始 -->
        <div class="header" hidden>
            <div class="wrap">
                <div class="col-2"><div class="logo"></div></div>
                <div class="col-6"><div class="search">
                    <form action="">
                        <input class="search-input" type="text" placeholder="搜索你感兴趣的内容"/>
                        <div class="searbtn"></div>
                    </form>
                </div></div>
                 <div class="col-2"><div class="menu">
                    <ul>
                        <li><a href="<%=path %>/page/topicdynamics.jsp">首页</a></li>
                        <li><a href="<%=path %>/page/topicdynamics.jsp">话题</a></li>
                        <li><a href="<%=path %>/page/statistics.jsp">统计</a></li>
                        <li><a href="#">消息</a></li>
                        <li><a href="<%=path %>/page/personal.jsp">我</a></li>
                    </ul>
                </div></div>
                
                <div class="info">
                    <a href="#" class="userinfo">
                        <span> ${quizDetail.userName }</span>
                        <img src="<%=path %>/images/userinfosmall.jpg" alt="${quizDetail.userName }" />
                    </a>
                    <ul class="down">
                        <li><a href="#">我的主页</a></li>
                        <li><a href="#">私信</a></li>
                        <li><a href="#">设置</a></li>
                        <li><a href="#">退出</a></li>
                    </ul>
                </div>
                <div class="btnwen btn" id="btn">提问</div>
            </div>
        </div>
        <!-- 知乎头部结束 -->
       <div class="main1">
           <input type="hidden" id="questionId" value="${quizDetail.questionId}">
           <div class="card1">
                <h1>${quizDetail.questionTitle }</h1>
                <h4>问题描述：</h4> 
                <div>${quizDetail.questionIntroduce }</div>
                <button><span class="icon-edit"></span> <a href="#answer">写回答</a></button>
           </div>
       </div>
       <div class="questions-list">
            <div class="list-header">
                <h4>
                    <span>${replyList.size() }</span>
                    <span>个回答</span>
                    <button>默认排序</button>
                </h4>
            </div>
            <c:forEach items="${replyList }" var="reply">
            	<div class="list-item">
	                <div class="answer-header">
	                    <div class="author">
	                        <img src="${reply.replyUserImage }" width="35" height="35"/>
	                        <div class="autorintru">
	                            <span class="authorintru-title">
	                                ${reply.replyUserName }
	                            </span>
	                            <span class="authorInfo-badge">
	                                <span>${reply.replyUserContent }</span>
	                            </span>
	                        </div>
	                    </div>
	                    <div class="shuoming">
	                        <span>${reply.zanCount}</span>个人赞同了该回答
	                    </div>
	                    <div class="answer-article">
	                        <p>${reply.replyContent }</p>
	                    </div>
	                </div>
	                <div class="zantong" data-replyId="${reply.replyId}">
	                    <span class="icon-sort-up">${reply.zanCount}</span>
	                    <span class="icon-sort-down" data="${reply.zanFlag}"></span>
	                </div>
	            </div>
            </c:forEach>
             <!-- 回答部分开始 -->
                  <div class="answer">
                    <div class="answer-header">
                        <div class="author">
                            <img src="${userInfo.imageUrl}" width="35" height="35"/>
                            <div class="autorintru">
                                <div class="authorintru-title">
                                    ${userInfo.userName }
                                </div>
                                <div class="authorInfo-badge">
                                    <span>${userInfo.userContent }</span>
                                    <!-- <button class="edit"><span class="icon-edit"></span>编辑话题经验</button> -->
                                </div>
                            </div>
                            <!-- <button class="plain">使用匿名身份回答</button> -->
                        </div>
                    </div>
                    <div class="answer-content">
                        <a name="answer"></a>
                      <textarea required="required" id="answer" rows="2" class="Input" placeholder="写回答..."></textarea>
                      <div class="answer-footer">
                          <button class="answer-tijiao">提交回答</button>
                          <div class="zhuanzai">允许规范转载</div>
                      </div>
                    </div>
                  </div>
                <!-- 回答部分结束 -->
       </div>

        <!-- 提问的弹出框 -->
        <div class="Modal-wrapper" id="Modal-wrapper">
            <div class="Modal-backdrop"></div>
            <div class="Modal-large">
                <div class="Modal-inner">
                    <span class="icon-remove guanbi"></span>
                    <h3 class="Modal-title">写下你的问题</h3>
                    <div class="Modal-subtitle">描述精确的问题更易得到解答</div>
                    <div class="Modal-content">
                        <div class="askquestion">
                            <div class="QuestionAsk-section">
                                <div class="asktitle">
                                    <textarea required="required" id="questionTitle" rows="2" class="Input" placeholder="问题标题"></textarea>
                                </div>
                            </div>
                            <div class="QuestionAsk-section">
                                <div class="asktitle">
                                    <select name="questionTopic" id="questionTopic" class="Input" style="color: #757575">
                                        <option value="">添加话题</option>
                                    </select>
                                </div>
                            </div>
                            <div class="QuestionAsk-section">
                                <div class="askhead">问题描述：</div>
                                <div class="asktitle">
                                    <textarea required="required" id="questionIntroduce" rows="2" class="Input" placeholder="问题背景、条件等详细信息"></textarea>
                                </div>
                            </div>
                            <%--<div class="QuestionAsk-section">
                                <div class="chec"><input type="checkbox" class="checkbox"/>匿名提问</div>
                            </div>--%>
                            <div class="tijiao">
                                <button class="tijiaobtn">提交问题</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 提问的弹出框结束 -->
    </body>
</html>