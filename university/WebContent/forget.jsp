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
    <script src="${pageContext.request.contextPath}/js/html5shiv.js"></script>
    <script src="${pageContext.request.contextPath}/js/respond.min.js"></script>
    <![endif]-->
	
	 <style type="text/css">
    .error:before{
	content:"用户名或邮箱错误";
    }
    .error{
    margin-left: 25%;
    font-size:15px;
    color:red;
    }
	.ok:before{
	content:"邮件发送成功";
    }
    .ok{
    margin-left: 25%;
    font-size:15px;
    color:red;
    }
    </style>
	
</head>
<body>
<!-- 找回-->
<div class="container-fluid ">
<div class="row ">
<div class="form ">
<form class="form-horizontal wow slideInDown " action="${pageContext.request.contextPath}/WanghaoServlet?op=forgetPassword" method="post" onSubmit="return beforeSubmit(this);">
	<h1 class="col-sm-offset-3">校园二手交易系统-找回密码</h1>
	<span class=<%= request.getParameter("info") %>></span>
  <div class="form-group">
    <label for="inputuserNumber" class="col-sm-3 control-label ">用户名：</label>
    <div class="col-sm-7">

      <input type="text" class="form-control " id="userNumber" name="userNumber" placeholder="用户名">
    </div>
  </div>
  <div class="form-group">
    <label for="inputEmail" class="col-sm-3 control-label">邮箱：</label>
    <div class="col-sm-7">
      
      <input type="email" class="form-control" id="email" name="email" placeholder="邮箱">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-4 col-sm-5">
    
      <input class="form-control btn btn-danger" id="submit_btn"
											value="&nbsp;发&nbsp;送&nbsp;邮&nbsp;件&nbsp;"
											type="submit">
    </div>
  </div>
  </div>
</form>
</div>
  </div>
   <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>

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
	else if(form.email.value==''){
		alert('邮箱不能为空');
		form.email.focus();
		return false;
		}
    return true;
}
</script>