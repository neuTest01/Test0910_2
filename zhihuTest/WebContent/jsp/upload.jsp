<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<!-- 这个是接收服务器发回来的内容，EL表达式 -->
    ${path}
       <form action="UploadTestServlet" enctype="multipart/form-data" id="loginform" name="loginform" method="post">
            选择图片：<input type="file" name="filename"/>
            <input id="subid" name="subid" type="submit" value="提交">
        </form>
</body>
</html>