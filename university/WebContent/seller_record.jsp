<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">


<!-- 让IE采用最新的渲染模式 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- 移动端的页面宽度等于设备宽度，缩放比为1 -->
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebarDemo2.css">

<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/shop.css">

<!--动画CSS插件-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/animate.min.css">
<title>卖家记录</title>

<style>
.location {
	position: relative;
	left: 50px;
	margin: 0 auto;
}

.location1 {
	position: relative;
	left: 300px;
	top: -300px;
	padding-left: 68px;
	font-weight: bold;
	font-size: 17px;
}
</style>

</head>
<body>

	<nav class="navbar navbar-default navbar-fixed-top navbar-inverse">
		<div class="container">
			<!-- 当浏览器大小下于一定程度后的响应式图标-->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<!--设置商标-->
				<a class="navbar-brand" href="#">校园二手交易系统</a>
				<p>欢迎 ${sessionScope.user.user_name }</p>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="shop.do">主页</a></li>
					<li><a href="GetUserInfor.doHao">我的信息</a></li>
					<li><a href="HistoryGoods.doCui">历史记录</a></li>
					<li><a href="cart.jsp">我的购物车</a></li>
					<li><a href="out.do">退出登陆</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->

	</nav>


	<div class="jumbotron wow slideInDown ">
		<h1>校园二手交易</h1>
		<p>让闲置游起来</p>
	</div>


	<div id="main">
		<div></div>
		<c:forEach items="${ListGoods}" var="Listgoods" varStatus="gd">
			<c:if test="${Listgoods.getGoods_status() eq '0' || Listgoods.getGoods_status() eq '4' }">
				<div class="box">
					<div class="pic" style="background-color: #FFF5EE">
						<a href="detail.do?goods_id=${Listgoods.goods_id}"><img
							src="./img/${Listgoods.goods_img}" /> </a>
					</div>

					<div class="foot_money ">
						<span id=price>￥${Listgoods.price}</span> <a href="#"
							class="foot_a"> <span
							class="glyphicon lyphicon glyphicon-heart-empty"> <script>
								document.write(parseInt(200 * Math.random()));
							</script>
						</span>
						</a>
					</div>
					<div class="foot_title ">
						<p id=p1>商品ID:${Listgoods.goods_id}</p>
						<p id=p2>
							商品名称:${Listgoods.goods_name}<br />
						</p>
					</div>
				</div>
			</c:if>
			<c:if
				test="${Listgoods.getGoods_status() eq '3' }">
				<div class="box">

					<div class="pic" style="background-color: #B0C4DE">
						 <a href="detail.do?goods_id=${Listgoods.goods_id}"><img
								src="./img/${Listgoods.goods_img}" /> </a>						
																		
					</div>
					<div class="foot_money ">
						<span id=price>￥${Listgoods.price}</span> <a href="#"
							class="foot_a"> <span
							class="glyphicon lyphicon glyphicon-heart-empty"> <script>
								document.write(parseInt(200 * Math.random()));
							</script>
						</span>
							
						</a>
					</div>
											<div class="foot_title ">
							<p id=p1>商品ID:${Listgoods.goods_id}</p>
								<p id=p2>商品名称:${Listgoods.goods_name}</p>
								<p id=p2>商品评价:${Listgoods.evaluation}</p>	
						</div>
				</div>
			</c:if>
		</c:forEach>

	</div>


	<div class="right_go">
		<p class="pull-right">
			<a href="shop.do">回到首页</a>
		</p>
	</div>

</body>
</html>