<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<%@ include file="/header.jsp"%>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>user list</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/./css/bootstrap.min.css">

<script src="${pageContext.request.contextPath}/./js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath }/./js/bootstrap.min.js"></script>

<h3>list auditedUsers</h3>

 <div style="padding: 70px 550px 10px">
    	<%-- <form   method="post" action="${pageContext.request.contextPath }/servlet/ManagementServlet?op=searchUser" class="form-inline"  id="searchform">
        	<div class="input-group">
           <input type="text" placeholder="input your userId" class="form-control" id="search" name="userId" class="form-control">
            <span class="input-group-btn">
                  <input type="submit" value="search" class="btn btn-default">
            </span>
        </div>
    </form> --%>
    <script>
        function mySubmit(flag){
            return flag;
        }
        $("#searchform").submit(function () {
            var val=$("#search").val();
            if(val==''){
                alert("please input right");
                return mySubmit(false);
            }
        })
    </script>
</div>

	<table class="table table-striped" style="width: 70%;">
		<tr>
		    <th>userId</th>
			<th>userName</th>
			<th>Password</th>
			<th>Telephone</th>
			<th>userNumber</th>
			<th>Email</th>
			<th>Status</th>
			<!-- <th>Operate</th> -->
		</tr>
		<c:forEach items="${audited}" var="b" varStatus="vs">
		
				<tr>
			    <td id="id">${b.user_id}</td>
				<td id="username">${b.user_name}</td>
				<td id="password">${b.password}</td>
				<td id="phone">${b.phone}</td>
				<td id="userNumber">${b.user_number}</td>
				<td id="email">${b.email}</td>
				<td id="status">${b.user_status}</td> 
				<%-- <td>
					<button type="submit" class="btn btn-info"
						onclick="javascript:window.location.href='${pageContext.request.contextPath}/editUser.jsp?userId=${b.user_id}'">Edit</button>
					<button type="submit" class="btn btn-danger"
						onclick="javascript:window.location.href='${pageContext.request.contextPath}/servlet/ManagementServlet?op=delUser&userId=${b.user_id}'">Delete</button>
				</td> --%>
				</tr>
		</c:forEach>
	</table>
</body>
</html>
