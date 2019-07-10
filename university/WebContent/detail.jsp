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

<link rel="stylesheet" href="css/bootstrap-theme.css">
<link rel="stylesheet" href="css/sidebarDemo2.css">

<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/shop.css">

<!--动画CSS插件-->
<link rel="stylesheet" type="text/css" href="css/animate.min.css">
<title>详细信息</title>

<style>
.location {
	position: relative;
	left: 50px;
	margin: 0 auto;
}

.location1 {
	position: relative;
	left: 300px;
	top: -250px;
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
	<div id="sidebar">
		<ul>
			<li id="prof" class="item"><span
				class="glyphicon glyphicon-user"> </span>
				<div>
					<a href="#">我</a>
				</div></li>
			<li id="asset" class="item"><span
				class="glyphicon glyphicon-usd"></span>
				<div>资产</div></li>
			<li id="brand" class="item"><span
				class="glyphicon glyphicon-heart"> </span>
				<div>品牌</div></li>
			<li id="broadcast" class="item"><span
				class="glyphicon glyphicon-bell"></span>
				<div>直播</div></li>
			<li id="foot" class="item"><span
				class="glyphicon glyphicon-eye-open"> </span>
				<div>看过</div></li>
			<li id="calendar" class="item"><span
				class="glyphicon glyphicon-time"></span>
				<div>日历</div></li>
		</ul>
		<div id="closeBar">
			<span class="glyphicon glyphicon-remove"></span>
		</div>
	</div>

	<div class="jumbotron wow slideInDown ">
		<h1>校园二手交易</h1>
		<p>让闲置游起来</p>
	</div>




	<div class="location">

		<c:forEach items="${goods}" var="good" varStatus="gd">
			<div class="box">

				<div class="pic">
					<img src="./img/${good.goods_img}" />
				</div>

				<div class="foot_title ">
					<%--       <p>商品ID:${good.goods_id}</p> --%>
					<%--       <p>商品名称:${good.goods_name}<br/></p> --%>
					<p>
						<i class="glyphicon glyphicon-user"></i>&nbsp;卖家ID:${good.user_id}
						<br />
					</p>
					<form action="OrderServlet?ID=${good.user_id}" method='post'>
						<i class="glyphicon glyphicon-th-list"></i>&nbsp;
						<button name="submit" value="record" type="submit">查看卖家</button>
					</form>
					
						<i class="glyphicon glyphicon-th-list"></i>&nbsp;
						<a href="GoToMessage.doHe?id=${good.user_id}">联系卖家</a>
						
					
				</div>

				<div class=location1>
					<p>
						<i class="glyphicon glyphicon-file"></i>&nbsp;商品ID:${good.goods_id}
					</p>
					<p>
						<i class="glyphicon glyphicon-list-alt"></i>&nbsp;商品名称:${good.goods_name}<br />
					</p>
					<div class="foot_money ">
						<span><i class="glyphicon glyphicon-tag"></i>价格:￥${good.price}</span><br />
					</div>
					<p>
						<i class="glyphicon glyphicon-pencil"></i>&nbsp;描述:${good.description}<br />
					</p>
					<i class="glyphicon glyphicon-heart"></i>&nbsp;
					<c:choose>
						<c:when test="${sessionScope.counts eq 5 }">购物车达上限(5)</br>
						</c:when>
						<c:when test="${good.goods_status eq 1 }">被举报下架</br>
						</c:when>
						<c:when test="${good.goods_status eq 2 }">正在交易</br>
						</c:when>
						<c:when test="${good.goods_status eq 21 }">正在交易</br>
						</c:when>
						<c:when test="${good.goods_status eq 22 }">正在交易</br>
						</c:when>
						<c:when test="${good.goods_status eq 3 }">已交易完成</br>
						</c:when>
						<c:otherwise >
							<form name="form1" target="submitFrame"
								action="OrderServlet?goodsid=${good.goods_id}&PRICE=${good.price}"
								method='post'>
								<button type="submit" name="submit" value="join"
									onclick="myFunction()">
									加入购物车</br>
								</button>
							</form>
							<iframe src="" name="submitFrame" width="0" height="0"></iframe>
							<form method="post" action="jubao.do?goods_id=${good.goods_id}">
						<i class="glyphicon glyphicon-envelope"></i>&nbsp;<input
							type="submit" value="举报" />
						</form>
						</c:otherwise>
					</c:choose>
					
						<!-- <form action="#"> -->
						<!-- <p><i class="glyphicon glyphicon-star-empty"></i>&nbsp;评价:</p><input type="text"><input type="submit" value="提交"> -->
						<!-- </form> -->
				</div>

			</div>
		</c:forEach>
	</div>
	<div class="right_go">
		<p class="pull-right">
			<a href="shop.do">回到首页</a>
		</p>
	</div>
	<script type="text/javascript">
		//多行注释
		/* 我是多行注释！
		 我需要隐藏，
		 否则会报错哦！*/

		//在页面中显示文字
		//页面中弹出提示框
		function myFunction() {
			alert('加入成功!')
		}

		//单行注释
		//我是单行注释，我也要隐藏起来！
	</script>
</body>
</html>