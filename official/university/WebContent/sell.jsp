<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>卖过的物品界面</title>
<!--自己的样式-->
<link rel="stylesheet" type="text/css" href="css/backstage.css">
<!-- Bootstrap -->
<!--fileinput 样式-->
<link rel="stylesheet" type="text/css" href="css/fileinput.css">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/button.css" rel="stylesheet">
<link href="css/table.css" rel="stylesheet">
<!--动画CSS插件-->
<link rel="stylesheet" type="text/css" href="css/animate.min.css">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/fileinput.js"></script>
<script src="js/zh.js"></script>
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
			<div class="col-md-2 wow slideInDown" id="left">
				<a href="#systemSetting" data-toggle="collapse"> <i
					class="glyphicon glyphicon-cog"></i>个人功能 <span
					class="pull-right glyphicon glyphicon-chevron-down"></span><br />
				</a>
				<!--ul id  和 上面a 标签的id对应 所以能够弹出li -->
				<ul id="systemSetting"
					class="nav nav-pills nav-stacked collapse in ">
					<li><a href="#"><i class="glyphicon glyphicon-user"></i>个人信息</a></li>
					<li><a href="#"><i class="glyphicon glyphicon-home"></i>购物车</a></li>
					<li><a href="#"><i class="glyphicon glyphicon-edit"></i>发布产品</a></li>
				</ul>
				</br>
				<!--data-toggle collapse 折叠   collapse in 初始不折叠-->
				<div>
					<a href="#userSetting" data-toggle="collapse"> <i
						class="glyphicon glyphicon-fire"></i> 历史记录<span
						class="pull-right glyphicon glyphicon-chevron-down"></span>
					</a>
					<ul class="nav nav-pills nav-stacked collapse in " id="userSetting">
						<li><a href="HistoryGoods.doCui"><i
								class="glyphicon glyphicon-inbox"></i>作为买家</a></li>
						<li class="active"><a href=""> <i
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
						<li><a href="#publish" class="ex0 active"> <i
								class="glyphicon glyphicon-pencil"></i> 用户留言
						</a></li>
						<li><a href="#"> <i class="glyphicon glyphicon-barcode"></i>
								管理员通知
						</a></li>
						<li><a href="#"> <i class="glyphicon glyphicon-euro"></i>
								系统通知
						</a></li>

					</ul>
				</div>
				<div>
					<a href="index.html" data-toggle="collapse"> <i
						class="glyphicon glyphicon-asterisk"></i>返回主页
					</a>
				</div>
			</div>


			<div class=" col-md-8 publish wow fadeInUp" id="right">
				<table class="mt">
					<tbody>
						<tr>
							<td>商品名字</td>
							<td>商品描述</td>
							<td>价格</td>
							<td>状态</td>
							<td>修改</td>
							<td>取消</td>
						</tr>
						<c:forEach items="${ListGoods}" var="Listgoods">
							<tr>
								<td>${Listgoods.getGoods_name()}</td>
								<td>${Listgoods.getDescription()}</td>
								<td>${Listgoods.getPrice()}</td>
								<td><c:if test="${Listgoods.getGoods_status() eq '0' || Listgoods.goods_status eq '1'}">正常上架</c:if>
									<c:if test="${Listgoods.getGoods_status() eq '-3'}">被举报下架</c:if>
									<c:if test="${Listgoods.getGoods_status() eq '22'}">卖家未确认</c:if>
									<c:if test="${Listgoods.getGoods_status() eq '21'}">买家未确认</c:if>
									<c:if test="${Listgoods.getGoods_status() eq '2'}">都未确认</c:if>
									<c:if test="${Listgoods.getGoods_status() eq '3'}">已卖出</c:if>
									<c:if test="${Listgoods.getGoods_status() eq '-1'}">已取消</c:if>
								</td>
								<td><c:if
										test="${Listgoods.getGoods_status() eq '21'}">
										已确认
									</c:if><c:if
										test="${Listgoods.getGoods_status() eq '2'|| Listgoods.getGoods_status() eq '22'}">
										<form name="form1"  action="OrderServlet?GoodsID=${Listgoods.getGoods_id()}" method='post'>
											<button type="submit" name="submit" value="sellersure" >确认</button>
										</form>
									</c:if>
								<c:if test="${Listgoods.getGoods_status() eq '0'}">
										<form action="showgoods.doCui?goods_id=${Listgoods.goods_id}" method='post'>
										<input name="change" type="submit" value="修改"/>
										</form>
									</c:if></td>
								<td>
								<c:if
										test="${Listgoods.getGoods_status() eq '2'|| Listgoods.getGoods_status() eq '22'}">
										<form name="form1"  action="OrderServlet?GoodsID=${Listgoods.getGoods_id()}" method='post'>
											<button type="submit" name="submit" value="sellercancel" >取消</button>
										</form>
									</c:if>
								<c:if test="${Listgoods.getGoods_status() eq '0'}">
								         <form action="cancel.doCui?goods_id=${Listgoods.goods_id}" method='post'>
										<input name="cancel" type="submit" value="取消"/>
									</form>
									</c:if></td>
																	<td></td>
								<td></td>
																
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<ul class="pager">

					<li><a href="?start=0" class="href">start</a></li>
					<li><a href="?start=${pre}" class="href">pre</a></li>
					<li><a href="?start=${next}" class="href">next</a></li>
					<li><a href="?start=${last}" class="href">last</a></li>
				</ul>
			</div>
		</div>
	</div>
	</div>
	<script src="js/wow.min.js"></script>
</body>
</html>
