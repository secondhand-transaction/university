<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>修改密码</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<!--动画CSS插件-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/animate.min.css">
<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
</head>
<body onload="checkUsername();">
<!-- 登录-->
<div class="container-fluid ">
<div class="row ">
<div class="form ">
<form class="form-horizontal wow slideInDown " action="${pageContext.request.contextPath}/WanghaoServlet?op=changepassword" method="post" onSubmit="return beforeSubmit(this);">
	<h1 class="col-sm-offset-3">校园二手交易系统-修改密码</h1>
	<span class="help-block" style="color:red;margin-left: 25%;font-size:15px; "></span>
  <div class="form-group">
    <label for="inputpassword" class="col-sm-3 control-label ">新密码：</label>
    <div class="col-sm-7">
     <input type="password" class="form-control" id="passwordnoMd5"
								placeholder="新密码">
		    <input type='hidden' name='password' id='password' value=''/>
    <span class="help-block"></span>
	</div>
  </div>
  <div class="form-group">
    <label for="inputExpCom" class="col-sm-3 control-label">确认密码:</label>
    <div class="col-sm-7">
      <input type="password" class="form-control"id="repassword" placeholder="确认密码">
   <span class="help-block"></span>     
   </div>
  </div>
  <input type='hidden' name='userid' id='userid' value=''/>
  <div class="form-group">
    <div class="col-sm-offset-4 col-sm-5">
    
      <input class="form-control btn btn-danger" id="submit_btn"
											value="修&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;改"
											type="submit">
    </div>
  </div>
  </div>
</form>
</div>
  </div>
   <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/MD5.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/wow.min.js"></script>
<script>
$(function(){

new WOW().init();
})
</script>

<script type="text/javascript">

       function getQueryVariable(variable)
       {
       var query = window.location.search.substring(1);
       var vars = query.split("&");
       for (var i=0;i<vars.length;i++) {
               var pair = vars[i].split("=");
               if(pair[0] == variable){return pair[1];}
       }
       return(false);
       }


    //为用户名文本框添加这个方法，在文本框失去焦点以后运行
    //就是当用户填写完用户名后，进行下一步填写的时候，异步执行这个方法
    function checkUsername(){
        //获取用户名文本框对象
		var userId = getQueryVariable("id");
		var PIN = getQueryVariable("key");
        //获取提示信息span对象
        var span1 = document.getElementsByTagName("span")[0];
        
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
                        //如果返回0，说明匹配，可以修改
                    }else{
                        //说明用户名不匹配，不能修改
                        span1.innerHTML = "错误，用户不能修改密码！";
						document.getElementById("submit_btn").disabled=true;
                    }
                }
            }
        }
            //创建连接
            xmlHttp.open("GET","${pageContext.request.contextPath}/WanghaoServlet?op=canChange&&id="+userId+"&&key="+PIN,true);
            
            //发送请求
            xmlHttp.send();
        
    }
</script>

<script type="text/javascript">
$('input').eq(0).focus(function() {
			if ($(this).val().length == 0) {
				$(this).next("span").text("请输入密码");
				$(this).next("span").css("color", '#FFA500');
			}
		});
		//确认密码
		$('input').eq(2).focus(function() {
			if ($(this).val().length == 0) {
				$(this).next("span").text("请再次输入密码");
				$(this).next("span").css("color", '#FFA500');
			}
		});

//密码：
		$('input').eq(0).blur(function() {
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
$('input').eq(2).blur(function() {
			if ($(this).val().length == 0) {
				$(this).next("span").text("");
				$(this).next("span").css("color", '#FFA500');
			} else if ($(this).val() != $('input').eq(0).val()) {
				$(this).next("span").text("两次密码不匹配");
				$(this).next("span").css("color", 'red');
			} else {
				$(this).next("span").text("");
			}

		});
		//		提交按钮
		
		$("#submit_btn").click(function(e) {
			var passwordnoMd5 = $("#passwordnoMd5").val();
			console.log("没有加密之前的是："+passwordnoMd5);
            var password = $("#password").val();
			console.log("没有加密之前的是："+password);
	        var md5password = $.md5(passwordnoMd5);
			console.log("没有加密之前的是："+md5password);
	        password.value = md5password;
	        
	        var t=document.getElementById("password");
	        t.value=md5password;
			var u=document.getElementById("userid");
	        u.value=getQueryVariable("id");
			
			var password = $("#password").val();
			console.log("没有加密之前的是："+password);
			var userid = $("#userid").val();
			console.log("没有加密之前的是："+userid);
			
			for (var j = 0; j < 4; j++) {
				if ($('input').eq(j).val().length == 0) {
					$('input').eq(j).focus();
					if (j == 3) {
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
</script>
</body>