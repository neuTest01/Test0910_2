<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">

function aa(){
	var name  = document.getElementById("username").value;
//步骤一:创建异步对象
var ajax = new XMLHttpRequest();
//步骤二:设置请求的url参数,参数一是请求的类型,参数二是请求的url,可以带参数,动态的传递参数starName到服务端
ajax.open('get','../AjaxLoginServlet?username='+name);
//步骤三:发送请求
ajax.send();
//步骤四:注册事件 onreadystatechange 状态改变就会调用
ajax.onreadystatechange = function () {
   if (ajax.readyState==4 &&ajax.status==200) {
    //步骤五 如果能够进到这个判断 说明 数据 完美的回来了,并且请求的页面是存在的
    var responsevalue=ajax.responseText;
　　　　console.log(responsevalue);//输入相应的内容
			if(responsevalue=='0'){
				
				location.href = "main.jsp";
				
			}else{
				
				eval(ajax.responseText);
				
			}
  　　}
}
}
</script>
</head>
<body>
<input type="text" name="username"  id="username">
<input  type="button" value="提交" onclick="aa()">
</body>
</html>