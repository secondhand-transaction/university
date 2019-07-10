<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>publish发布页面</title>
<!--自己的样式-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/backstage.css">
<!-- Bootstrap -->
<!--fileinput 样式-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/fileinput.css">
<link href="css/bootstrap.min.css" rel="stylesheet">
<!--动画CSS插件-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/animate.min.css">
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/fileinput.js"></script>
<script src="${pageContext.request.contextPath}/js/zh.js"></script>
<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<header class="wow fadeInRight">
		<img src="images/two.jpg" class="img-circle logo" alt="logo" />
		<div class="desc">校园二手交易系统</div>
	</header>
	<!-- /.container-fluid -->

	<div class="container-fluid">
		<div class="row ">
			<!--data-toggle collapse 折叠  ul class =collapse 初始折叠 -->

			<!-- 显示表格数据 -->

			<div class=" col-md-8 publish wow fadeInUp" id="right">
				<!-- 将数据appendTo tbody 定义id -->
					
					<form action="upload.doCui" enctype="multipart/form-data" method="post">
					<div class="form-group">
						<label class="col-sm-2">商品图片:</label>
						<div class="col-sm-7">
							<input name="img" type="file" class="file" />
						</div>
					</div>
					
						<br /> <br />

						<button type="submit"
							class="btn btn-primary  col-sm-2 col-md-offset-2" >发布</button>
						<button type="reset" class="btn btn-danger col-sm-2 "
							id="exp_delete_all_btn">重置</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
