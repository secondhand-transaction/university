<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
			<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
			<title>购物车</title> <!--自己的样式-->
			<link rel="stylesheet" type="text/css" href="css/backstage.css">
				<!-- Bootstrap -->
				<!--fileinput 样式-->
				<link rel="stylesheet" type="text/css" href="css/fileinput.css">
					<link href="css/bootstrap.min.css" rel="stylesheet">
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
<style>
div {
	border: 1px no red
}
</style>
<body>
	<header class="wow fadeInRight"> <img src="images/two.jpg"
		class="img-circle logo" alt="logo" />
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
					<li><li class="active"><a href="#" class="ex0 active"><i
								class="glyphicon glyphicon-home"></i>购物车</a></li>
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
						<li><a href="#publish" class="ex0 active"> <i
								class="glyphicon glyphicon-inbox"></i>作为买家
						</a></li>
						<li><a href="#"> <i class="glyphicon glyphicon-gift"></i>
								作为卖家
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
						<li><a href="#publish"> <i
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


			<!-- 显示表格数据 -->

			<div class=" col-md-8 publish wow fadeInUp" id="right">
				<!-- 将数据appendTo tbody 定义id -->
				
				<%-- <form class="form-horizontal" action="${APP_PATH}/user/regist"
					method="post"> --%>
					<div class="form-group">
						<div class="col-sm-6">
							<h4>你加入购物车的物品如下：</h4>
						</div>
					</div>
					</br>
					</br>
					<div class="form-group">
						<div class="col-sm-2">
							<label>物品ID</label>
						</div>
						<label class="col-sm-5">物品名称</label>
						<div class="col-sm-2">
							<label>价格</label>
						</div>
						<div class="col-sm-2"></div>
					</div>
					</br>
					 <c:forEach items="${sessionScope.carts}" var="c" varStatus="st">
						<div class="form-group">
						<div class="col-sm-2">
							<label>${c.goods_id}</label>
						</div>
						<label class="col-sm-5">${c.name}</label>
						<div class="col-sm-2">
							<label>${c.price}</label>
						</div>
						
						<div class="col-sm-3">
						<form action="OrderServlet?GoodsID=${c.goods_id}" method='post'>
							<button type="submit" name="submit" value="deleteOne" class="btn col-sm-4 col-md-offset-3"
								id="expaddbtn">移除</button>	
						</form>						
						</div>					
					</div>
                    </br>
															
					 </c:forEach> 
					</br> </br>
					<form action="OrderServlet?" method='post'>
					<div class="form-group">
						<div class="col-sm-2"></div>
						<div class="col-sm-5">
						
							<button type="submit" name="submit"
								class="btn btn-primary  col-sm-4 col-md-offset-0" id="expaddbtn"
								value="generate">生成订单</button>
						</div>
						<div class="col-sm-4">
							<button type="submit" name="submit"
								class="btn btn-danger  col-sm-4 col-md-offset-2 "
								id="exp_delete_all_btn" value="deleteAll">清空</button>
						</div>
					</div>
					</form>
					</br> </br>
					<div class="form-group">
						<a href="test.jsp">fanhui</a>
					</div>
			</div>
			</form>
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
</body>
</html>
