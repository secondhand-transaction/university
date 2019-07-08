<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>user management</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!-- bootstrap v3.3.6 css -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/./js/vendor/jquery-1.12.0.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/./css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/./css/bootstrap-theme.min.css">
<!-- bootstrap js -->
<script src="${pageContext.request.contextPath}/./js/bootstrap.min.js"></script>


</head>

<body>

	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false" id="p11">Goods Management<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a
							href="${pageContext.request.contextPath}/servlet/ManagementServlet?op=listBeiJuBao" id="p12">List reported
						     </a></li>
						<li><a
							href="${pageContext.request.contextPath}/servlet/ManagementServlet?op=listXiaJia" id="p13">List undercarriage
						    </a></li>
						<li><a
							href="${pageContext.request.contextPath}/servlet/ManagementServlet?op=listNormal" id="p12">List Normal
						     </a></li>
					</ul></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false" id="p14">User Management <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a
							href="${pageContext.request.contextPath}/addUser.jsp" id="p8">Add
								User</a></li>
						<li><a
							href="${pageContext.request.contextPath}/servlet/ManagementServlet?op=listUsers" id="p9">Query
								User</a></li>
					</ul></li>
					
				<%-- <li><a
					href="${pageContext.request.contextPath}/servlet/ManageServlet?op=listOverdue" id="p10">Overdue
						Readers</a></li>
				</li> --%>
				
			<%-- 	<li><a
					href="${pageContext.request.contextPath}/servlet/ManageServlet?op=listJubao" id="p11">Goods Management
					    </a></li>
				</li>  --%>
				
			</ul>
			
			
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>

	<center>
</html>
