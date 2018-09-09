<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<% String path = request.getContextPath();
   String content = request.getParameter("searchContent");
%>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>
    <title>登录统计</title>
    <link rel="stylesheet" type="text/css" href="<%=path %>/css/personal.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/css/index.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/css/Font-Awesome-3.2.1/css/font-awesome.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/css/topicdynamics.css" />
    <script type="text/javascript" src="<%=path %>/js/jquery-3.2.0.min.js"></script>
    <script type="text/javascript" src="<%=path %>/js/echarts.min.js"></script>
    <script type="text/javascript" src="<%=path %>/js/statistics.js"></script>
    <script type="text/javascript" src="<%=path %>/js/raiseQuestions.js"></script>
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
                <a href="<%=path %>/page/login.jsp" class="userinfo">退出</a>
            </div>
        </div>
    </header>
    <!-- 知乎头部开始 -->
    <div class="header" hidden>
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
                        <li><a href="<%=path %>/page/topicdynamics.jsp">话题</a></li>
                        <li><a href="<%=path %>/page/statistics.jsp">统计</a></li>
                        <li><a href="#">消息</a></li>
                        <li><a href="<%=path %>/page/personal.jsp">我</a></li>
                    </ul>
                </div>
            </div>

            <div class="info">
                <a href="<%=path %>/page/personal.jsp" class="userinfo">
                    <span class="name"> ${userInfo.userName }</span>
                    <img src="${userInfo.imageUrl}" alt="${userInfo.userName }" width="35" height="35"/>
                </a>
                <a href="<%=path %>/page/login.jsp" class="userinfo">退出</a>
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
    <div class="content">
        <div id="canvasContainer" style="width: 100%;height: 600px;"></div>
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
