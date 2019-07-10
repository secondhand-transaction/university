<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>个人资料</title>
<!--自己的样式-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/backstage.css">
<!-- Bootstrap -->
<!--fileinput 样式-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/fileinput.css">
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<!--动画CSS插件-->
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/animate.min.css">
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/fileinput.js"></script>
<script src="${pageContext.request.contextPath}/js/zh.js"></script>
<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
<style>
</style>
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
			<div class="col-md-2 wow slideInDown" id="left">
				<a href="#systemSetting" data-toggle="collapse"> <i
					class="glyphicon glyphicon-cog"></i>个人功能 <span
					class="pull-right glyphicon glyphicon-chevron-down"></span><br />
				</a>
				<!--ul id  和 上面a 标签的id对应 所以能够弹出li -->
<ul id="systemSetting"
					class="nav nav-pills nav-stacked collapse in ">
					<li class="active"><a href="GetUserInfor.doHao"><i class="glyphicon glyphicon-user"></i>个人信息</a></li>
					<li ><a href="cart.jsp" class="ex0 active"><i
								class="glyphicon glyphicon-home"></i>购物车</a></li>
						<li><a href="publish.jsp"><i
								class="glyphicon glyphicon-edit"></i>发布产品</a></li>
						<li><a href="seekPurchase.jsp"> <i
								class="glyphicon glyphicon-book"></i> 我要求购
						</a></li>
				</ul>
				</br>
				<!--data-toggle collapse 折叠   collapse in 初始不折叠-->
				<div>
					<a href="#userSetting" data-toggle="collapse"> <i
						class="glyphicon glyphicon-fire"></i> 历史记录<span
						class="pull-right glyphicon glyphicon-chevron-down"></span>
					</a>
					<ul class="nav nav-pills nav-stacked collapse in " id="userSetting">
						<li><a href="HistoryGoods.doCui" class="ex0 active"> <i
								class="glyphicon glyphicon-inbox"></i>作为买家
						</a></li>
						<li><a href="HistorySeller.doCui"> <i
								class="glyphicon glyphicon-gift"></i> 作为卖家
						</a></li>

					</ul>
				</div>
				</br>
				<!--data-toggle collapse 折叠   collapse in 初始不折叠-->
				<div>
					<a href="#sellSetting" data-toggle="collapse"> <i
						class="glyphicon glyphicon-asterisk"></i> 信息中心<span
						class="pull-right glyphicon glyphicon-chevron-down"></span>
					</a>
					<ul class="nav nav-pills nav-stacked collapse in " id="sellSetting">						
						<li><a href="GoToMessagelist.doHe" class="ex0 active"> <i
								class="glyphicon glyphicon-pencil"></i> 留言
						</a></li>													
						<li><a href="GoToMessagelistSystem.doHe"> <i class="glyphicon glyphicon-euro"></i>
								系统通知
						</a></li>

					</ul>
				</div>
				<div>
					<a href="shop.do"> 返回主页
					</a>
				</div>
			</div>

	
			<!-- 显示表格数据 -->
			<div class=" col-md-8 publish wow fadeInUp" id="right">
				<!-- 将数据appendTo tbody 定义id -->
				<form class="form-horizontal" action="PublishInfor.doHao" method="post">
					<div class="form-group">
						<label class="col-sm-2">我的昵称:</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="title" name="user_name" value="${requestScope.user.user_name}">
							<span class="help-block"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2">我的邮箱:</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="title2" name="email" value="${requestScope.user.email}">
							<span class="help-block"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2">我的电话:</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="title3"name="phone" value="${requestScope.user.phone}">
							<span class="help-block"></span>
						</div>
					</div>
					<button class="btn btn-primary  col-sm-2 col-md-offset-2"
						id="ShowButton" onclick="openResult()">修改</button>
					<button type="reset" class="btn btn-danger col-sm-2 "
						id="exp_delete_all_btn">重置</button>
				</form>
			</div>
		</div>
	</div>

</body>
</html>
