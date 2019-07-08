<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%
	session.setAttribute("user_id",2);
%>
<body>
<a href="HistoryGoods.doCui">到buyer</a>
<a href="chentest.jsp">去添加</a>
	<br>
	<br>
	<a href="query.do">这是一个查询请求，调用Servlet中的query方法</a>
	<br>
	<br>
	<a href="delete.do">这是一个删除请求，调用Servlet中的delete方法</a>
	
</body>
</html>