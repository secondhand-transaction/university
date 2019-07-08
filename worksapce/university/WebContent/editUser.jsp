<%@page import="entity.User"%>
<%@page import="dao.XuejinlongDao"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>edit</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/./css/bootstrap.min.css">

<script src="${pageContext.request.contextPath}/./js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath }/./js/bootstrap.min.js"></script>


</head>
<%@ include file="/header.jsp"%>
<%
    	XuejinlongDao xue = new XuejinlongDao();
    	int userId = Integer.valueOf(request.getParameter("userId"));
    	User user = xue.findUserById(userId);
    %>
<h3 id="p8">Edit user</h3>
<form
	action="${pageContext.request.contextPath}/servlet/ManagementServlet?op=editUser&userId=<%=user.getUser_id() %>"
	method="post">
	<table class="table table-striped" style="width: 50%;">
		<tr>
			<td id="p1">Name：</td>
			<td><input name="username" id="name" class="form-control"
				value="<%=user.getUser_name() %>" /></td>
		</tr>
		<tr>
			<td id="p2">Password：</td>
			<td><input name="password" id="password" class="form-control"
				value="<%=user.getPassword() %>" /></td>
		</tr> 
		<tr>
			<td id="p3">Telephone：</td>
			<td><input name="phone" id="phone" class="form-control"
				value="<%=user.getPhone() %>" /></td>
		</tr>
		<tr>
			<td id="p4">UserNumber：</td>
			<td><input name="UserNumber" class="form-control"
				value="<%=user.getUser_number() %>" /></td>
		</tr>
		<tr>
			<td id="p5">Email：</td>
			<td><input name="email" class="form-control"
				value="<%= user.getEmail() %>" /></td>
		</tr>
		<tr>
			<td id="p6">Status：</td>
			<td><input name="status" class="form-control"
				value="<%= user.getUser_status() %>" /></td>
		</tr>

	</table>
	<button type="submit" class="btn btn-info" id="p7">Edit</button>
</form>
</body>
</html>
