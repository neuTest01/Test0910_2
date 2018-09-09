<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% String path = request.getContextPath();
   String content = request.getParameter("searchContent");
%>
<%
	//el表达式
	String account="";
	String password="";
	HttpSession sen = request.getSession();
	//取出Cookie
	Cookie [] c=request.getCookies();
	for(int i=0;i<c.length;i++){
	    if(c[i].getName().equals("users")){
	        //存着数据（用户名+密码）
	        account=c[i].getValue().split("-")[0];
	        password=c[i].getValue().split("-")[1];
	        
	        //再一次的存起来（备用）
	        sen.setAttribute("account",account);
	        sen.setAttribute("password", password);
	    }
	}
%>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>
        <title>话题动态-知乎</title>
        <link rel="icon" href="<%=path %>/images/logo.jpg" type="image/x-icon">
        <link rel="stylesheet" type="text/css" href="<%=path %>/css/index.css" />
        <link rel="stylesheet" type="text/css" href="<%=path %>/css/topicdynamics.css" />
        <link rel="stylesheet" type="text/css" href="<%=path %>/css/Font-Awesome-3.2.1/css/font-awesome.css" />
        <script type="text/javascript" src="<%=path %>/js/jquery-3.2.0.min.js"></script>
        <script type="text/javascript" src="<%=path %>/js/topicdynamics.js"></script>
        <script type="text/javascript" src="../js/raiseQuestions.js"></script>
    </head>
    <script type="text/javascript">
    	var path = "<%=path%>";
    </script>
    <body>
    <!-- 知乎头部开始 -->
        <div class="header">
            <div class="wrap">
                <div class="col-2" style="width:auto;"><div class="logo"></div></div>
                <div class="col-6" style="width:auto;"><div class="search">
                    <form action="">
                        <input class="search-input" id="search-content" value="<%=content==null?"":content%>" type="text" placeholder="搜索你感兴趣的内容"/>
                        <div class="searbtn"></div>
                    </form>
                </div></div>
                <div class="col-2" style="width:25%;">
	                <div class="menu">
	                    <ul>
	                        <li><a href="#">首页</a></li>
	                        <li><a href="#">话题</a></li>
	                        <li><a href="<%=path %>/page/statistics.jsp">统计</a></li>
	                        <li><a href="<%=path %>/page/personal.jsp">我</a></li>
	                    </ul>
	                </div>
	            </div>
                
                <div class="info">
                    <a href="<%=path %>/page/personal.jsp" class="userinfo">
                        <span class="name"> ${userInfo.userName }</span>
                        <img src="${userInfo.imageUrl}" alt="${userInfo.userName }" width="35" height="35"/>
                    </a>
                    <a href="javascript:location.href='<%=path %>/logoutServlet'" class="userinfo">退出</a>
                    <ul class="down">
                        <li><a href="#">我的主页</a></li>
                        <li><a href="#">私信</a></li>
                        <li><a href="#">设置</a></li>
                        <li><a href="#">退出</a></li>
                    </ul>
                </div>
                <div class="btn phone-width">提问</div>
            </div>
        </div>
        <!-- 知乎头部结束 -->
        <!-- 知乎内容主体部分开始 -->
        <div class="content">
            <div class="contentmain">
            <!-- 知乎右半部分开始 -->
                <div class="right">
                    <div class="topics-plaza">
                        <a href="#" class="zg-btn-blue">
                        进入话题广场
                        </a>
                        <a href="#" class="text">
                        来这里发现更多有趣话题
                        </a>
                    </div>
                    <div class="topic-recommend">
                        <div class="topic-recommend-head">
                            <h2 class="topic-recommend-name">其他人关注的话题</h2>
                            <a href="#">
                            换一换
                            </a>
                        </div>
                        <div class="topic-recommend-list">
                            <div class="listone">
                                <div class="listimg"></div>
                                <div class="listwenzi">
                                    <h3><a href="#" class="tiaozhuan">动漫</a></h3>
                                    <a href="#" class="guanzhu">关注</a>
                                </div>
                            </div>

                        </div>
                        <div class="topic-recommend-list">
                            <div class="listone">
                                <div class="listimg"></div>
                                <div class="listwenzi">
                                    <h3><a href="#" class="tiaozhuan">动漫</a></h3>
                                    <a href="#" class="guanzhu">关注</a>
                                </div>
                            </div>

                        </div>
                        <div class="topic-recommend-list">
                            <div class="listone">
                                <div class="listimg"></div>
                                <div class="listwenzi">
                                    <h3><a href="#" class="tiaozhuan">动漫</a></h3>
                                    <a href="#" class="guanzhu">关注</a>
                                </div>
                            </div>

                        </div>
                    </div>

                </div>
                <!-- 知乎右半部分结束 -->
                <!-- 知乎左半部分开始 -->
                <div class="contentmain-left">
                    <div class="lefttltle">
                        <i class="tilteimg"></i>
                        <span>已关注的动态话题</span>
                        <a href="#">共关注<span id="count"></span>个话题</a>
                    </div>
                    <ul class="topic"></ul>
                    <div class="quizContainer" id="quizContainer"></div>
                </div>
                <!-- 知乎左半部分结束 -->
            </div>
        </div>
        <!-- 知乎内容主体部分结束 -->
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