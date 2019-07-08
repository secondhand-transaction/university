<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<head>
<meta charset="utf-8">
 <!-- 让IE采用最新的渲染模式 -->  
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- 移动端的页面宽度等于设备宽度，缩放比为1 -->  
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>regist注册页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/register.css">
<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<!--动画CSS插件-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/animate.min.css">

<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
	
</head>
<body>
<!-- 注册-->
<header class="
wow fadeInRight
"> <img src="images/two.jpg" class="img-circle logo"
		alt="logo" />
  <div class="desc">欢迎注册</div>
</header>
<div class="container-fluid">
  <div class="row wow slideInDown">
    <form class="form-horizontal" action="${pageContext.request.contextPath}/servlet/WanghaoServlet?op=signup" method="post"  onSubmit="return beforeSubmit(this);">
      <div class="form-group">
        <label for="inputExpCom" class="col-sm-12  ">账号：</label>
        <div class="col-sm-11 ">
          <input type="text" class="form-control" id="userNumber"
							name="userNumber" placeholder="请输入用户名" onblur="checkUsername();"/>
          <span
							class="help-block" style="color:#FFA500 "></span> 
		</div>
		<label for="inputExpCom" class="col-sm-12  ">昵称：</label>
        <div class="col-sm-11 ">
          <input type="text" class="form-control" id="userName"
							name="userName" placeholder="请输入用户名">
          <span
							class="help-block"></span> 
		</div>
        <label for="inputExpCom" class="col-sm-12 ">密码：</label>
        <div class="col-sm-11">
            <input type="password" class="form-control" id="passwordnoMd5"
								placeholder="请输入密码">
		    <input type='hidden' name='password' id='password' value=''/>
            <span
								class="help-block"></span> 
		</div>
        <label for="inputExpCom" class="col-sm-12 ">确认密码：</label>
        <div class="col-sm-11">
              <input type="password" class="form-control" id="repassword"
									placeholder="确认密码">
              <span class="help-block"></span> 
		</div>
        <label for="inputEmail1" class="col-sm-12 ">邮箱:</label>
        <div class="col-sm-11">
                <input type="email" class="form-control" id="email" name="email"
										placeholder="邮箱">
                <span class="help-block"></span> 
		</div>
		<label for="inputPhone" class="col-sm-12 ">手机号:</label>
        <div class="col-sm-11">
                <input type="text" class="form-control" id="phone" name="phone"
										placeholder="手机号">
                <span class="help-block"></span> 
		</div>
		
        <label for="submit" class="col-sm-12 "></label>
                <input class="form-control btn btn-warning col-sm-12" id="submit_btn"
											value="立&nbsp;&nbsp;即&nbsp;&nbsp;注&nbsp;&nbsp;册"
											type="submit">
      </div>
    </form>
  </div>
</div>
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script> 
<script src="${pageContext.request.contextPath}/js/MD5.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script> 
<script src="${pageContext.request.contextPath}/js/wow.min.js"></script>

<script type="text/javascript">
    //为用户名文本框添加这个方法，在文本框失去焦点以后运行
    //就是当用户填写完用户名后，进行下一步填写的时候，异步执行这个方法
    function checkUsername(){
        //获取用户名文本框对象
        var username = document.getElementsByName("userNumber")[0].value;
        //获取提示信息span对象
        var span1 = document.getElementsByTagName("span")[0];
        if(username == ""){
            span1.innerHTML = "用户名不能为空！";
            return;
        }
        
        //创建请求对象
        var xmlHttp = new XMLHttpRequest();
        
        //设置请求状态变化时触发的事件
        xmlHttp.onreadystatechange = function(){
            //如果请求状态码为4，说明请求已经完成，响应已经就绪
            if(xmlHttp.readyState == 4){
                //响应状态码为200，响应完成
                if(xmlHttp.status == 200){
                    //获取服务器返回的信息
                    var data = xmlHttp.responseText;
                    //判断返回的信息，输出响应的提示信息
                    if(data == 0){
                        //如果返回0，说明用户名不重复，可以使用
                        span1.innerHTML = "用户名可以使用！";
                    }else{
                        //说明用户名重复，不能使用
                        span1.innerHTML = "用户名已经被注册！";
                    }
                }
            }
        }
            //创建连接
            xmlHttp.open("GET","${pageContext.request.contextPath}/servlet/WanghaoServlet?op=nameAvaliable&&userNumber="+username,true);
            
            //发送请求
            xmlHttp.send();
        
    }
</script>

<script>
$(function(){

new WOW().init();
})
</script>
</body>
<script type="text/javascript">
		//账号
		$('input').eq(0).focus(function() {
			if ($(this).val().length == 0) {
				$(this).next("span").text("请输入用户名");
				$(this).next("span").css("color", '#FFA500');
			}
		});
		//昵称
		$('input').eq(1).focus(function() {
			if ($(this).val().length == 0) {
				$(this).next("span").text("请输入昵称");
				$(this).next("span").css("color", '#FFA500');
			}
		});
		//密码
		$('input').eq(2).focus(function() {
			if ($(this).val().length == 0) {
				$(this).next("span").text("请输入密码");
				$(this).next("span").css("color", '#FFA500');
			}
		});
		//确认密码
		$('input').eq(4).focus(function() {
			if ($(this).val().length == 0) {
				$(this).next("span").text("请再次输入密码");
				$(this).next("span").css("color", '#FFA500');
			}
		});
		//邮箱
		$('input').eq(5).focus(function() {
			if ($(this).val().length == 0) {
				$(this).next("span").text("请输入邮箱");
				$(this).next("span").css("color", '#FFA500');
			}
		});
		//手机号
		$('input').eq(6).focus(function() {
			if ($(this).val().length == 0) {
				$(this).next("span").text("请输入手机号");
				$(this).next("span").css("color", '#FFA500');
			}
		});

		//input各种判断
		//用户名：
		$('input').eq(0).blur(function() {
			if ($(this).val().length == 0) {
				$(this).next("span").text("");
				$(this).next("span").css("color", '#FFA500');
			} else if ($(this).val().length > 3 && $(this).val().length < 6) {
				$(this).next("span").text("长度只能在4-20个字符之间");
				$(this).next("span").css("color", 'red');
			} else {
				$(this).next("span").text("");
			}

		});
		//昵称
		$('input').eq(1).blur(function() {
			if ($(this).val().length == 0) {
				$(this).next("span").text("");
				$(this).next("span").css("color", '#FFA500');
			}else {
				$(this).next("span").text("");
			}

		});
		//密码：
		$('input').eq(2).blur(function() {
			if ($(this).val().length == 0) {
				$(this).next("span").text("");
				$(this).next("span").css("color", '#FFA500');
			} else if ($(this).val().length > 3 && $(this).val().length < 6) {
				$(this).next("span").text("长度只能在6-20个字符之间");
				$(this).next("span").css("color", 'red');
			} else {
				$(this).next("span").text("");
			}

		});
		//确认密码：
		$('input').eq(4).blur(function() {
			if ($(this).val().length == 0) {
				$(this).next("span").text("");
				$(this).next("span").css("color", '#FFA500');
			} else if ($(this).val() != $('input').eq(2).val()) {
				$(this).next("span").text("两次密码不匹配");
				$(this).next("span").css("color", 'red');
			} else {
				$(this).next("span").text("");
			}

		});
		//邮箱
		$('input').eq(5).blur(function() {
			if ($(this).val().length == 0) {
				$(this).next("span").text("");
				$(this).next("span").css("color", '#FFA500');
			} else if ($(this).val().length > 6 && $(this).val().length < 10) {
				$(this).next("span").text("长度只能在6-20个字符之间");
				$(this).next("span").css("color", 'red');
			} else {
				$(this).next("span").text("");
			}

		});
	    //手机号
		$('input').eq(6).blur(function() {
			if ($(this).val().length == 0) {
				$(this).next("span").text("");
				$(this).next("span").css("color", '#FFA500');
			} else if ($(this).val().length != 9) {
				$(this).next("span").text("长度只能为9字符");
				$(this).next("span").css("color", 'red');
			} else {
				$(this).next("span").text("");
			}

		});
	
		
		//		提交按钮
		
		$("#submit_btn").click(function(e) {
			var passwordnoMd5 = $("#passwordnoMd5").val();
			console.log("没有加密之前的是："+passwordnoMd5);
            //var password = $("#password").val();
			//console.log("没有加密之前的是："+password);
	        var md5password = $.md5(passwordnoMd5);
			console.log("没有加密之前的是："+md5password);
	        //password.value = md5password;
	        
	        var t=document.getElementById("password");
	        t.value=md5password;
			var password = $("#password").val();
			console.log("没有加密之前的是："+password);
			for (var j = 0; j < 7; j++) {
				if ($('input').eq(j).val().length == 0) {
					$('input').eq(j).focus();
					if (j == 6) {
						$('input').eq(j).next("span").text("此处不能为空");
						$('input').eq(j).next("span").css("color", '#FFA500');
						e.preventDefault();
						return;
					}

					$('input').eq(j).next("span").text("此处不能为空");
					$('input').eq(j).next("span").css("color", '#FFA500');
					e.preventDefault();
					return;
				}
			}
		});
		
		/*
		function beforeSubmit(form){
	        for (var j = 0; j < 5; j++) {
				if ($('input').eq(j).val().length == 0) {
					$('input').eq(j).focus();
					if (j == 4) {
						$('input').eq(j).next("span").text("此处不能为空");
						$('input').eq(j).next("span").css("color", '#FFA500');
						e.preventDefault();
						return false;
					}

					$('input').eq(j).next("span").text("此处不能为空");
					$('input').eq(j).next("span").css("color", '#FFA500');
					e.preventDefault();
					return false;
				}
			}
            var passwordnoMd5 = $("#passwordnoMd5").val();
            var password = $("#password").val();
	        var md5password = $.md5(passwordnoMd5);
	        form.password.value = md5password;
            return true;
            }*/
	</script>