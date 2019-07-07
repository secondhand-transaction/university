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
<body>
<header class="wow fadeInRight"> <img src="images/two.jpg" class="img-circle logo"
		alt="logo" />
  <div class="desc">校园二手交易系统</div>
</header>
<!-- /.container-fluid -->

<div class="container-fluid">
  <div class="row ">
  <!--data-toggle collapse 折叠  ul class =collapse 初始折叠 -->
    <div class="col-md-2 wow slideInDown" id="left"> <a href="#systemSetting"  data-toggle="collapse"> <i class="glyphicon glyphicon-cog"></i>后台管理 <span class="pull-right glyphicon glyphicon-chevron-down"></span><br/> </a> 
      <!--ul id  和 上面a 标签的id对应 所以能够弹出li -->
      <ul id="systemSetting" class="nav nav-pills nav-stacked collapse ">
        <li><a href="#"><i class="glyphicon glyphicon-user"></i>用户管理</a></li>
        <li><a href="#"><i class="glyphicon glyphicon-home"></i>地址管理</a></li>
        <li><a href="#"><i class="glyphicon glyphicon-globe"></i>商品管理</a></li>
        <li><a href="#"><i class="glyphicon glyphicon-edit"></i>图片管理</a></li>
        <li><a href="#"><i class="glyphicon glyphicon-eye-open"></i>日志管理</a></li>
      </ul>
    </br>
     <!--data-toggle collapse 折叠   collapse in 初始不折叠-->
        <div> <a href="#userSetting"  data-toggle="collapse"> 
        <i class="glyphicon glyphicon-fire"></i> 买家中心<span class="pull-right glyphicon glyphicon-chevron-down"></span> </a> 
      <ul class="nav nav-pills nav-stacked collapse in " id="userSetting">
        <li> <a href="#publish" class="ex0 active"> <i class="glyphicon glyphicon-inbox"></i> 我的购物车</a> </li>
           <li> <a href="#"> <i class="glyphicon glyphicon-gift"></i> 已买到的商品</a> </li>
        <li> <a href="#"> <i class="glyphicon glyphicon-heart"></i> 我的收藏</a> </li>
           <li> <a href="#"> <i class="glyphicon glyphicon-user"></i> 个人资料</a> </li>

      </ul>
    </div>
      </br>
    <!--data-toggle collapse 折叠   collapse in 初始不折叠-->
        <div> <a href="#sellSetting"  data-toggle="collapse"> <i class="glyphicon glyphicon-asterisk"></i> 卖家中心<span class="pull-right glyphicon glyphicon-chevron-down"></span> </a> 
      <ul class="nav nav-pills nav-stacked collapse in " id="sellSetting">
        <li class="active"> <a href="#publish" class="ex0 active"> <i class="glyphicon glyphicon-pencil"></i> 发布商品 </a> </li>
           <li> <a href="#"> <i class="glyphicon glyphicon-barcode"></i> 出售中的商品</a> </li>
        <li> <a href="#"> <i class="glyphicon glyphicon-euro"></i> 卖家设置</a> </li>

      </ul>
    </div>
      
  </div>
 
    
    <!-- 显示表格数据 -->
    
    <div class=" col-md-8 publish wow fadeInUp" id="right"><!-- 将数据appendTo tbody 定义id -->
      <form class="form-horizontal" 
				method="post" id="form">
        <div class="form-group">
          <label  class="col-sm-2">商品名称:</label>
          <div class="col-sm-3">
            <input type="text" class="form-control" id="title" name="title" >
            <span	class="help-block"></span> </div>
        </div>
        <div class="form-group">
          <label  class="col-sm-2">商品价格:</label>
          <div class="col-sm-3">
            <input type="text" class="form-control" id="title2" name="title2" >
            <span	class="help-block"></span> </div>
        </div>
        
        <div class="form-group">
          <label  class="col-sm-2" >商品详情:</label>
          <div class="col-sm-7">
            <textarea id="title3" name="title3" rows="3" cols="70" name="note"></textarea>
            <span	class="help-block"></span> </div>
        </div>
        
<!--          <div class="form-group">
          <label  class="col-sm-2">商品图片:</label>
          <div class="col-sm-10">
      <input id="file_upload" type="file" class="file" />
          </div>
        </div>-->
        

					<div id="ShowDiv" name="ShowDiv"></div>
       
        <br/>
        <br/>
					<button class="btn btn-primary  col-sm-2 col-md-offset-2"
						id="ShowButton" onclick="openResult()">发布</button>

					<button type="reset" class="btn btn-danger col-sm-2 "
						id="exp_delete_all_btn">重置</button>
      </form>
    </div>
  </div>
</div>
</div>
<script>

		$(document).ready(function() {

			$('#exp_delete_all_btn').click(function() {
				console.log("success");
				$("input[type='text']").attr('value', "");
				$("textarea[name='title3']").attr('value', "");

			});
		});

		function openResult() { /* 绑定事件 */
			$.ajax({
				type : "POST", //传数据的方式
				url : "publish", //servlet地址
				data : $('#form').serialize(), //传的数据  form表单 里面的数据
				success : function(result) { //传数据成功之后的操作   result是servlet传过来的数据  这个函数对result进行处理，让它显示在 输入框中
					console.log(result);
				}
			});

		}



</script>
</body>
</html>
    