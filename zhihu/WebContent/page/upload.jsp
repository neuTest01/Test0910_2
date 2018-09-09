<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<% String path = request.getContextPath();%>
    <head>
        <meta charset="utf-8">
        <title>servlet3.0上传</title>
        <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>
        <link rel="icon" href="<%=path %>/images/logo.jpg" type="image/x-icon">
        <link rel="stylesheet" type="text/css" href="<%=path %>/css/personal.css" />
        <link rel="stylesheet" type="text/css" href="<%=path %>/css/index.css" />
        <link rel="stylesheet" type="text/css" href="<%=path %>/css/Font-Awesome-3.2.1/css/font-awesome.css" />
        <script type="text/javascript" src="<%=path %>/js/jquery-3.2.0.min.js"></script>

    </head>
    <script type="text/javascript">
    	var path = "<%=path%>";
        var userId = "${userInfo.userId}";
    	var userName = "${userInfo.userName}";
    	var userContent = "${userInfo.userContent}";
    	var userImgUrl = "${userInfo.imageUrl}";
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
                    <form action="<%=path %>/page/topicdynamics.jsp" method="post">
                        <input class="search-input1" name="searchContent" type="text" placeholder="搜索你感兴趣的内容"/>
                        <input type="submit" value="" class="searbtn"/>
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

                </div>   
            </div>
        </header>
            <!-- 知乎头部开始 -->
        <div class="header" hidden>
            <div class="wrap">
                <div class="col-2"><div class="logo"></div></div>
                <div class="col-6"><div class="search">

                </div></div>
                 <div class="col-2"><div class="menu">
                    <ul>
                        <li><a href="<%=path %>/page/topicdynamics.jsp">首页</a></li>
                        <li><a href="<%=path %>/page/topicdynamics.jsp">话题</a></li>
                        <li><a href="#">发现</a></li>
                        <li><a href="#">消息</a></li>
                        <li><a href="#">我</a></li>
                    </ul>
                </div></div>
                
                <div class="info">
                    <a href="#" class="userinfo">
                        <span>${userInfo.userName }</span>
                        <img src="${userInfo.imageUrl}" alt="${userInfo.userName }" />
                    </a>
                    <ul class="down">
                        <li><a href="#">我的主页</a></li>
                        <li><a href="#">私信</a></li>
                        <li><a href="#">设置</a></li>
                        <li><a href="#">退出</a></li>
                    </ul>
                </div>
                <div class="btnwen btn">提问</div>
            </div>
        </div>
        <!-- 知乎头部结束 -->
        <div class="main">
        <fieldset>
           <form action="<%=path%>/ImgUploadServlet" method="post" enctype="multipart/form-data">
                 <span>上传文件：</span>
                 <input type="file" name="file">
                 <br>
                 <input type="submit" value="上传">
            </form>
        </fieldset>    
            <div id="updisplay">
            	
            </div>
	
        </div>

    </body>

</html>