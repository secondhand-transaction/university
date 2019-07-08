<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>管理用户</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/./css/bootstrap.min.css">

<script src="${pageContext.request.contextPath}/./js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath }/./js/bootstrap.min.js"></script>



</head>
<%@ include file="/header.jsp"%>
<h3 id="p8">Add User</h3>
<form
	action="${pageContext.request.contextPath}/servlet/ManagementServlet?op=addUser"
	method="post">
	<table class="table table-striped" style="width: 50%;">
		<tr>
			<td id="p1">UserId：</td>
			<td>
				 <input name="id"
				class="form-control" placeholder="Please input your UserId"
				aria-describedby="sizing-addon1">
			</td>
		</tr>
		<tr>
			<td id="p2">UserName：</td>
			<td>
			 <input name="username"
				class="form-control" placeholder="Please input your name"
				aria-describedby="sizing-addon1">
			</td>
		</tr>
		<tr>
			<td id="p3">Password：</td>
			<td>
			 <input
				name="password" class="form-control"
				placeholder="Please input your password"
				aria-describedby="sizing-addon1">
			</td>
		</tr>
		<tr>
			<td id="p4">Telephone：</td>
			<td>
				<input name="phone"
				class="form-control" placeholder="Please input your telephone"
				aria-describedby="sizing-addon1">
			</td>
		</tr>
		<tr>
			<td id="p5">UserNumber：</td>
			<td>
				 <input name="UserNumber"
				class="form-control" placeholder="Please input your UserNumber"
				aria-describedby="sizing-addon1">
			</td>
		</tr>
		<tr>
			<td id="p6">Email：</td>
			<td>
			 <input name="email"
				class="form-control" placeholder="Please input your email"
				aria-describedby="sizing-addon1">
			</td>
		</tr>
		
		<tr>
			<td id="p7">UserStatus：</td>
			<td>
			 <input name="status"
				class="form-control" placeholder="Please input your UserStatus"
				aria-describedby="sizing-addon1">
			</td>
		</tr>
	</table>
	<button type="submit" class="btn btn-info" id="p8">add</button>
</form>
</body>
</html>
