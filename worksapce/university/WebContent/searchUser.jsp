<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>网上书城</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/./css/bootstrap.min.css">

<script src="${pageContext.request.contextPath}/./js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath }/./js/bootstrap.min.js"></script>

</head>
<%@ include file="/header.jsp"%>
<h3>Query User</h3>

	<table class="table table-striped" style="width: 70%;">
		<tr>
			<th>userId</th>
			<th>Name</th>
			<th>Password</th>
			<th>Telephone</th>
			<th>userNumber</th>
			<th>Email</th>
			<th>status</th>
			<th>operate</th>
		</tr>
				<td id="id">${aa.user_id}</td>
				<td id="username">${aa.user_name}</td>
				<td id="password">${aa.password}</td>
				<td id="phone">${aa.phone}</td>
				<td id="address">${aa.user_number}</td>
				<td id="email">${aa.email}</td>
				<td>${aa.user_status }</td>
				<td>
					<button type="submit" class="btn btn-info"
						onclick="javascript:window.location.href='${pageContext.request.contextPath}/editUser.jsp?userId=${aa.user_id}'">Edit</button>
					<button type="submit" class="btn btn-danger"
						onclick="javascript:window.location.href='${pageContext.request.contextPath}/servlet/ManagementServlet?op=delUser&userId=${aa.user_id}'">Delete</button>
				</td>
			</tr>
	</table>
</body>
</html>
