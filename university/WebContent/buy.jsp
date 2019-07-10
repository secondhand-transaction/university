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
<title>买过的物品界面</title>
<!--自己的样式-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/backstage.css">
<!-- Bootstrap -->
<!--fileinput 样式-->
<link rel="stylesheet" type="text/css" href="css/fileinput.css">
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/button.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/table.css" rel="stylesheet">
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
			<div class="col-md-2 wow slideInDown" id="left">
				<a href="#systemSetting" data-toggle="collapse"> <i
					class="glyphicon glyphicon-cog"></i>个人功能 <span
					class="pull-right glyphicon glyphicon-chevron-down"></span><br />
				</a>
				<!--ul id  和 上面a 标签的id对应 所以能够弹出li -->
				<ul id="systemSetting"
					class="nav nav-pills nav-stacked collapse in ">
					<li><a href="GetUserInfor.doHao"><i class="glyphicon glyphicon-user"></i>个人信息</a></li>
					<li><li ><a href="cart.jsp" class="ex0 active"><i
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
						<li class="active"><a href="HistoryGoods.doCui" class="ex0 active"> <i
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


			<div class=" col-md-8 publish wow fadeInUp" id="right">
				<table class="mt">
					<tbody>
						<tr>
							<td>商品名字</td>
							<td>商品描述</td>
							<td>卖家</td>
							<td>价格</td>
							<td>状态</td>
							<td>确认</td>
							<td>取消</td>
						</tr>
						<c:forEach items="${ListGoods}" var="Listgoods">
							<tr>
								<td>${Listgoods.getGoods().getGoods_name()}</td>
								<td>${Listgoods.getGoods().getDescription()}</td>
								<td>${Listgoods.getGoods().getUser_id()}</td>
								<td>${Listgoods.getGoods().getPrice()}</td>
								<td><c:if
										test="${Listgoods.getOrderItem().getOrderItem_status() eq '3'}">已完成</c:if>
									<c:if
										test="${Listgoods.getOrderItem().getOrderItem_status() eq '-1'}">卖家已取消</c:if>
									<c:if
										test="${Listgoods.getOrderItem().getOrderItem_status() eq '-2'}">买家已取消</c:if>
									<c:if
										test="${Listgoods.getOrderItem().getOrderItem_status() eq '2'}">卖家未确认</c:if>
									<c:if
										test="${Listgoods.getOrderItem().getOrderItem_status() eq '1'}">买家未确认</c:if>
									<c:if
										test="${Listgoods.getOrderItem().getOrderItem_status() eq '0'}">都未确认</c:if>
								</td>
								<td><c:if
										test="${Listgoods.getOrderItem().getOrderItem_status() eq '2'}">
										已确认
									</c:if>
									<c:if
										test="${Listgoods.getOrderItem().getOrderItem_status() eq '4'}">
										已评价
									</c:if>
									<c:if
										test="${Listgoods.getOrderItem().getOrderItem_status() eq '3'}">
										<a href="comment.jsp?goods_id=${Listgoods.getGoods().getGoods_id()}" >评价</a>
									</c:if>
					
								<c:if
										test="${Listgoods.getOrderItem().getOrderItem_status() eq '0'|| Listgoods.getOrderItem().getOrderItem_status() eq '1'}">
										<form name="form1"  action="OrderServlet?OrderItemID=${Listgoods.getOrderItem().getOrderItem()}&GoodsID=${Listgoods.getGoods().getGoods_id()}" method='post'>
											<button type="submit" name="submit" value="buyersure">确认</button>
										</form>
									</c:if></td>
								<td><c:if
										test="${Listgoods.getOrderItem().getOrderItem_status() eq '0'|| Listgoods.getOrderItem().getOrderItem_status() eq '1'}">
										<form name="form1"  action="OrderServlet?OrderItemID=${Listgoods.getOrderItem().getOrderItem()}&GoodsID=${Listgoods.getGoods().getGoods_id()}&ReceiverID=${Listgoods.getGoods().getUser_id()}" method='post'>
											<button type="submit" name="submit" value="buyercancel">取消</button>
										</form>
									</c:if></td>
									
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
	<script>
		$(function() {

			new WOW().init();
		})
	</script>
	<script type="text/javascript">
		function myFunction() {
			alert('成功确定!')
		}
	</script>

</body>
</html>
