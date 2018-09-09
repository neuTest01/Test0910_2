<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

   <%
      Map m = new HashMap();
      
      List<String> list1 = new ArrayList<>();
      list1.add("xxx");
      list1.add("eee");
      
      
      List<String> list2 = new ArrayList<>();
      list2.add("xxxx");
      list2.add("eeee");
      
      m.put("m1",list1);
      m.put("m2",list2);
      
      request.setAttribute("m",m);
      
   
   %>
   
  <%--  <c:forEach>
   </c:forEach> --%>

</body>
</html>