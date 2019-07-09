<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<%
	session.setAttribute("user_id", 1);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>消息列表</title>
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
<header class="wow fadeInRight"> <img src="images/two.jpg" class="img-circle logo"
		alt="logo" />
  <div class="desc">校园二手交易系统</div>
</header>
<!-- /.container-fluid -->

<div class="container-fluid">
  <div class="row ">
  <!--data-toggle collapse 折叠  ul class =collapse 初始折叠 -->
    <div class="col-md-2 wow slideInDown" id="left"> 
	<a href="#systemSetting"  data-toggle="collapse">
	<i class="glyphicon glyphicon-cog"></i>个人功能 <span class="pull-right glyphicon glyphicon-chevron-down"></span><br/> </a> 
      <!--ul id  和 上面a 标签的id对应 所以能够弹出li -->
      <ul id="systemSetting" class="nav nav-pills nav-stacked collapse in ">
        <li><a href="#"><i class="glyphicon glyphicon-user"></i>个人信息</a></li>
        <li><a href="#"><i class="glyphicon glyphicon-home"></i>购物车</a></li>
        <li><a href="#"><i class="glyphicon glyphicon-edit"></i>发布产品</a></li>
      </ul>
    </br>
     <!--data-toggle collapse 折叠   collapse in 初始不折叠-->
        <div> <a href="#userSetting"  data-toggle="collapse"> 
        <i class="glyphicon glyphicon-fire"></i> 历史记录<span class="pull-right glyphicon glyphicon-chevron-down"></span> </a> 
      <ul class="nav nav-pills nav-stacked collapse in " id="userSetting">
        <li> <a href="#publish" class="ex0 active"> <i class="glyphicon glyphicon-inbox"></i>作为买家</a> </li>
           <li> <a href="#"> <i class="glyphicon glyphicon-gift"></i> 作为卖家</a> </li>

      </ul>
    </div>
      </br>
    <!--data-toggle collapse 折叠   collapse in 初始不折叠-->
        <div> <a href="#sellSetting"  data-toggle="collapse"> 
		<i class="glyphicon glyphicon-asterisk"></i> 信息中心<span class="pull-right glyphicon glyphicon-chevron-down"></span> </a> 
      <ul class="nav nav-pills nav-stacked collapse in " id="sellSetting">
        <li class="active"> <a href="GoToMessagelist.doHe" class="ex0 active"> <i class="glyphicon glyphicon-pencil"></i> 用户留言 </a> </li>
           <li> <a href="#"> <i class="glyphicon glyphicon-barcode"></i> 管理员通知</a> </li>
        <li> <a href="#"> <i class="glyphicon glyphicon-euro"></i> 系统通知</a> </li>

      </ul>
    </div>
	<div> <a href="index.html"  data-toggle="collapse"> 
		<i class="glyphicon glyphicon-asterisk"></i>返回主页 </a> 
    </div>
  </div>
 
    
    <div class=" col-md-8 publish wow fadeInUp" id="right">
				<table class="mt">
					<tbody>
						<tr>
							<td>发送方</td>
							<td>消息条数</td>
							<td>最后消息发送时间</td>
							<td>状态</td>
							<td>回复</td>

						</tr>
							<c:forEach items="${messageList}" var="message">
							<tr>
								<td>${message.getSeller_name()}</td>
								<td>${message.getInformation_num}</td>
								<td>${message.getDate}</td>
								<td><c:if test="${message.getStatus eq '1'}">已读</c:if>
									<c:if test="${message.getStatus eq '0'}">未读</c:if>
								</td>
								<td><a href="GoToMessage.doHe?id=${message.getSeller_id()}">回复</a></td>
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


</body>
</html>
