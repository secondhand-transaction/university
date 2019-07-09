<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<!doctype html>
<%
	session.setAttribute("user_id", 123);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>回复消息</title>
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
        <li class=""> <a href="messagelist.html" class="ex0 active"> <i class="glyphicon glyphicon-pencil"></i> 用户留言 </a> </li>
           <li> <a href="#"> <i class="glyphicon glyphicon-barcode"></i> 管理员通知</a> </li>
        <li> <a href="#"> <i class="glyphicon glyphicon-euro"></i> 系统通知</a> </li>

      </ul>
    </div>
	<div> <a href="index.html"  data-toggle="collapse"> 
		<i class="glyphicon glyphicon-asterisk"></i>返回主页 </a> 
    </div>
  </div>
 					
					<div class="panel-footer">
						<form action="DingyanweiServlet" method="post">
						<div class="input-group">
							<input style="height:400px;width:800px;"id="btn-input" type="text" class="form-control input-md" //placeholder="Type your message here..." ///>
							
							<span class="input-group-btn">
								<button class="btn btn-success btn-md" id="btn-chat">Send</button>
							</span>
						</div>
						</form>
					</div>
				</div>
</div>

</body>
</html>
