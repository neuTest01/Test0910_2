<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<% String path = request.getContextPath();
%>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>
        <title>分页</title>
        <link rel="icon" href="<%=path %>/images/logo.jpg" type="image/x-icon">
        <link rel="stylesheet" type="text/css" href="<%=path %>/css/index.css" />
        <link rel="stylesheet" type="text/css" href="<%=path %>/css/topicdynamics.css" />
        <link rel="stylesheet" type="text/css" href="<%=path %>/css/Font-Awesome-3.2.1/css/font-awesome.css" />
        <script type="text/javascript" src="<%=path %>/js/jquery-3.2.0.min.js"></script>
        <style type="text/css">
        	.page_table td{
        		border-left:1px solid #0664c3;
        		border-top:1px solid #0664c3;
        		padding:5px;
        		
        	}
        	.page_table{
        		width:100%;
        		border-collapse: collapse;
        		border-right:1px solid #0664c3;
        		border-bottom:1px solid #0664c3;
        	}
        	.page-bar{
        		padding:10px;
        		text-align: right;
        		color:#0664c3;
        	}
        	.page-bar a:link,.page-bar a:visited{
        		color:#0664c3;
        	}
        	
        </style>
    </head>
    
    <script type="text/javascript">
    	var path = "<%=path%>";
    	
    	function checkAll(checkall) {    
             arr = document.getElementsByName('userId' );   
             if (checkall.checked == true) {   
                 for(i=0;i<arr.length;i++){   
                     arr[i].checked = true;
                 }  
                 }else{  
                     for(i=0;i<arr.length;i++){   
                         if((arr[i]).checked==false){  
                             arr[i].checked = true;  
                         }else  
                         {arr[i].checked = false; }  
                     }  
                 }  
    	 }   
		 function deleteUser(){
			 var userIds = new Array() ;
			 arr = document.getElementsByName('userId' );
			 for(i=0;i<arr.length;i++){
				 if(arr[i].checked==true){
					userIds+=arr[i].value+",";
				 }
			 }
			 
			 if(userIds==null ||userIds==""){
				 alert("请您选择要删除的记录");
			 }else{
				 userIds = userIds.substring(0,userIds.length-1);
				 if(confirm("确定要删除您选中的数据么？")){
					 $.ajax({
					        url:path+"/userDeleteServlet",
					        data:{userIds:userIds},
					        type:"POST",
					        success:function(data){
					        	window.location.reload(); 
					        },
					        error:function(data){
					        }
					    });
				 }
			 }
		 }
    </script>
    <body>
    <!-- 知乎头部开始 -->
        <div class="header">
            <div class="wrap">
                <div class="col-2" style="width:auto;"><div class="logo"></div></div>
                <div class="col-6" style="width:auto;"><div class="search">
                    <form action="">
                        <input class="search-input" id="search-content" type="text" placeholder="搜索你感兴趣的内容"/>
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
            	<a href="javascript:void(0);" style="display:block;float:right;font-size:18px;" onclick="deleteUser()">删除</a>
            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	<a href="<%=path%>/ExportExcelServlet" style="display:block;float:right;font-size:18px;">导出</a>
            	
            	<table class="page_table">
            		<tr>
            			<td align="center"><input type="checkbox" id="checkall" name="checkall" onclick="checkAll(checkall)" >全选</td>
            			<td>账号</td>
            			<td>姓名</td>
            			<td>住址</td>
            			<td>出生年月</td>
            			<td>教育经历</td>
            			<td>简介</td>
            		</tr>
            		<c:forEach var="user" items="${page.list}" varStatus="status">
            			
            			<tr <c:if test="${status.count%2==0}">bgcolor="#ddd"</c:if> >
            				<td align="center">
            				<input type="checkbox" id="${user.userId}" name="userId" value ="${user.userId}" 
            					onclick="checkOne(${user.userId})">
            					&nbsp;&nbsp;&nbsp;&nbsp;${ status.index + 1}
								</td>
	            			<td>${count}${user.userAccount}</td>
	            			<td>${user.userName}</td>
	            			<td>${user.userAddress}</td>
	            			<td>${user.userBirthday}</td>
	            			<td>${user.userEducation}</td>
	            			<td>${user.userContent}</td>
	            		</tr>
            		</c:forEach>
            	</table>
            	<div class="page-bar" >
	            	<c:choose> 
						<c:when test="${page.currentPage==1&&page.currentPage!=page.totalPage}">   
					     	<a>首页</a>
	            		 	<a>上一页</a>
	            		 	<span>共${page.totalPage }页</span>
	            		 	<span>第${page.currentPage }页</span>2222
	            		 	<a href="<%=path%>/paginationServlet?currentPage=${page.currentPage+1}">下一页</a>
	            		 	<a href="<%=path%>/paginationServlet?currentPage=${page.totalPage}">尾页</a>
					  	</c:when> 
					  	<c:when test="${page.currentPage==page.totalPage&&page.currentPage!=1 }">   
					    	<a href="<%=path%>/paginationServlet?currentPage=1">首页</a>
	            		 	<a href="<%=path%>/paginationServlet?currentPage=${page.currentPage-1}">上一页</a>
	            		 	<span>共${page.totalPage }页</span>
	            		 	<span>第${page.currentPage }页</span>
	            		 	<a>下一页</a>
	            		 	<a>尾页</a>  
					  	</c:when> 
					  	<c:when test="${page.currentPage==page.totalPage&&page.currentPage==1 }">
					  		<a>首页</a>
	            		 	<a>上一页</a>
	            		 	<span>共${page.totalPage }页</span>
	            		 	<span>第${page.currentPage }页</span>
	            		 	<a>下一页</a>
	            		 	<a>尾页</a>   
					  	</c:when> 
					  	<c:otherwise>   
					    	<a href="<%=path%>/paginationServlet?currentPage=1">首页</a>
	            		 	<a href="<%=path%>/paginationServlet?currentPage=${page.currentPage-1}">上一页</a>
	            		 	<span>共${page.totalPage }页</span>
	            		 	<span>第${page.currentPage }页</span>
	            		 	<a href="<%=path%>/paginationServlet?currentPage=${page.currentPage+1}">下一页</a>
	            		 	<a href="<%=path%>/paginationServlet?currentPage=${page.totalPage}">尾页</a>
					  	</c:otherwise> 
					</c:choose> 
            	</div>
            </div>
        </div>
        <!-- 知乎内容主体部分结束 -->
        
    </body>
</html>