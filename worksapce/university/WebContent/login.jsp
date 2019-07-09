<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>校园二手交易系统登陆</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<!--动画CSS插件-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/animate.min.css">
<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
	<style type="text/css">
    a {text-decoration:none;}
	.regist:hover {color:blue;}
    </style>
    <style type="text/css">
    .error:before{
	content:"密码或者用户名错误";
    }
    .error{
    margin-left: 25%;
    font-size:15px;
    color:red;
    }
    </style>
</head>
<body>
<!-- 登录-->
<div class="container-fluid ">
<div class="row ">
<div class="form ">
<form class="form-horizontal wow slideInDown " action="${pageContext.request.contextPath}/servlet/WanghaoServlet?op=login" method="post" onSubmit="return beforeSubmit(this);">
	<h1 class="col-sm-offset-3">校园二手交易系统</h1>
	<span class=<%= request.getParameter("info") %>></span>
  <div class="form-group">
    <label for="userNumber" class="col-sm-3 control-label ">用户名：</label>
    <div class="col-sm-7">

      <input type="text" class="form-control " id="userNumber" name="userNumber" placeholder="用户名">
    </div>
  </div>
  <div class="form-group">
    <label for="password" class="col-sm-3 control-label">密码:</label>
    <div class="col-sm-7">
      
      <input type="password" class="form-control" id="passwordnoMd5" placeholder="密码">
	  <input type='hidden' name='password' id='password' value=''/>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-3 col-sm-8">
      <div class="checkbox">
        <a href="${pageContext.request.contextPath}/regist.jsp" style="float: left"> <span class="regist">注册</span></a> 
		<a  class="col-sm-offset-7" href="${pageContext.request.contextPath}/forget.jsp"><span class="regist">忘记密码?</span></a> </div>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-4 col-sm-5">
    
      <input class="form-control btn btn-danger" id="submit_btn"
											value="登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;陆"
											type="submit">
    </div>
  </div>
  </div>
</form>
</div>
  </div>
  
  </body>
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/MD5.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/wow.min.js"></script>
<script>
$(function(){

new WOW().init();
})
</script>
<script  type="text/javascript">
function beforeSubmit(form){
	if(form.userNumber.value==''){
		alert('用户名不能为空');
		form.userNumber.focus();
		return false;
		}
	else if(form.passwordnoMd5.value==''){
		alert('密码不能为空');
		form.passwordnoMd5.focus();
		return false;
		}
    var passwordnoMd5 = $("#passwordnoMd5").val();
    var password = $("#password").val();
	var md5password = $.md5(passwordnoMd5);
	form.password.value = md5password;
    return true;
}
</script>
