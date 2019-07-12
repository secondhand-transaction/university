<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<!-- 让IE采用最新的渲染模式 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- 移动端的页面宽度等于设备宽度，缩放比为1 -->
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebarDemo2.css">

<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/shop.css">

<!--动画CSS插件-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/animate.min.css">
<title>shop购物界面</title>


<!--  
<script type="text/javascript">  
			function a(){
				$.ajax({  
            		url:"cyxServlet",//servlet文件的名称
            		type:"GET",
            		success:function(e){  
                        alert("servlet调用成功！");  
                    }  
            			
            		
            	});
				
			}
        </script>
-->
</head>

<body >
<nav class="navbar navbar-default navbar-fixed-top navbar-inverse">
  <div class="container"> 
    <!-- 当浏览器大小下于一定程度后的响应式图标-->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
      <!--设置商标--> 
      <a class="navbar-brand" href="#">校园二手交易系统</a> <p>欢迎 ${sessionScope.user.user_name } </p></div>
      
    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav navbar-right">
      <li class="active"><a href="shop.do" >主页</a></li>
	   <li><a href="GetUserInfor.doHao" >我的信息</a></li>
	    <li><a href="HistoryGoods.doCui" >历史记录</a></li>
        <li><a href="cart.jsp" >我的购物车</a></li>
		<li><a href="out.do" >退出登陆</a></li>
      </ul>
    </div>
    <!-- /.navbar-collapse --> 
  </div>
  <!-- /.container-fluid --> 
  
</nav>
<div id="sidebar">
  <ul>
    <li id="prof" class="item"> <span class="glyphicon glyphicon-user"> </span>
      <div><a href="#">我</a> </div>
    </li>
    <li id="asset" class="item"> <span class="glyphicon glyphicon-usd"></span>
      <div> 资产 </div>
    </li>
    <li id="brand" class="item"> <span class="glyphicon glyphicon-heart"> </span>
      <div> 品牌 </div>
    </li>
    <li id="broadcast" class="item"> <span class="glyphicon glyphicon-bell"></span>
      <div> 直播 </div>
    </li>
    <li id="foot" class="item"> <span class="glyphicon glyphicon-eye-open"> </span>
      <div> 看过 </div>
    </li>
    <li id="calendar" class="item"> <span class="glyphicon glyphicon-time"></span>
      <div> 日历 </div>
    </li>
  </ul>
  <div id="closeBar">
    <span class="glyphicon glyphicon-remove"></span> 
  </div>
</div>

<div class="jumbotron wow slideInDown ">
  <h1>校园二手交易</h1>
  <p >让闲置游起来</p>
  <p><a class="btn btn-primary btn-lg" href="publish.jsp" role="button">发布商品</a></p>
  
  <div class="input-group col-sm-3"> 
  <form action="select.do" accept-charset="UTF-8" onsubmit="document.charset='UTF-8';" method="post" >
  <span class="input-group-btn ">
    <button class="btn btn-info" type="submit"> 搜 索 </button>
    </span>
    <input type="text" class="form-control" name="keyword" id="keyword" placeholder="Search for...">
    </form>
  </div>
  <!-- /input-group --> 
  
</div>


<div class="container wow slideInDown">
  <div class="row">
    <ul class="nav nav-tabs ">
      <li role="presentation"><a href="shop.do">首页</a></li>
      <li role="presentation"><a href="category.do?category_id=1" >书籍</a></li>
      <li role="presentation"><a href="category.do?category_id=2">数码</a></li>
      <li role="presentation"><a href="category.do?category_id=3">交通</a></li>
      <li role="presentation"><a href="category.do?category_id=4">运动</a></li>
      <li role="presentation"><a href="category.do?category_id=5">盆栽</a></li>
      <li role="presentation"><a href="category.do?category_id=6">杂货</a></li>
    </ul>
     
  </div>
 
  <h1 class="wow slideInDown" data-wow-duration="1.5s">人气精选<i>Hot:</i></h1>
 
</div>


<!--瀑布流布局开始-->

  <!-- 
<div id="main">
  
</div>
 -->

  <!--  
<div class="box">
<c:forEach items="${goods}" var="good" varStatus="gd">

    <div class="pic"> <img src="./images/b5.jpg"/> </div>
    <div class="foot_money "> <span>￥${good.price}</span> <a href="#" class="foot_a"> 
    <span class="glyphicon lyphicon glyphicon-heart-empty">
    <script>
document.write(parseInt(200*Math.random()));</script>
</span>
    </a> </div>
    <div class="foot_title ">
      <p>商品ID:${good.goods_id}<br/> 商品名称:${good.goods_name}<br/></p>
    </div>
    </c:forEach>
    
  </div>
-->






<div id="main">

		<c:forEach items="${goods}" var="good" varStatus="gd">
			<div class="box">

				<div class="pic">
					<a href="detail.do?goods_id=${good.goods_id}"><img
						src="./img/${good.goods_img}" /> </a>
				</div>

				<div class="foot_money "><i class="glyphicon glyphicon-tags"></i>
					<span id=price>￥${good.price}</span> <a href="#" class="foot_a">
						<span class="glyphicon lyphicon glyphicon-heart-empty"> <script>
document.write(parseInt(200*Math.random()));</script>
					</span>
					</a>
				</div>
				<div class="foot_title ">
					<p id=p1><i class="glyphicon glyphicon-file"></i>&nbsp;商品ID:${good.goods_id}</p>
					<p id=p2><i class="glyphicon glyphicon-list-alt"></i>&nbsp;
						商品名称:${good.goods_name}<br />
					</p>
				</div>
			</div>
		</c:forEach>

	</div>






<!--右侧边栏-->
<div class="right_go">
  <p class="pull-right"><a href="#top">回到顶部</a></p>
</div>
<!--秒杀时间js--> 
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/waterfall.js"/></script>
<script src="${pageContext.request.contextPath}/js/wow.min.js"></script>
<script src="${pageContext.request.contextPath}/js/modernizr.js"></script>
<script src="${pageContext.request.contextPath}/js/sidebarDemo2.js"></script>
<!-- <script>
  function GetRTime(){
       var EndTime= new Date('2018/06/28 00:00:00');//这里设置你预定的时间
       var NowTime = new Date();
       var t =EndTime.getTime() - NowTime.getTime();
       var d=Math.floor(t/1000/60/60%24);
       var h=Math.floor(t/1000/60/60%24);
       var m=Math.floor(t/1000/60%60);
       var s=Math.floor(t/1000%60);

       document.getElementById("t_d").innerHTML = d + "天";
       document.getElementById("t_h").innerHTML = h + "时";
       document.getElementById("t_m").innerHTML = m + "分";
       document.getElementById("t_s").innerHTML = s + "秒";
      
}
   setInterval(GetRTime,0);
  


</script>  -->
 <script>
    /*jshint expr: true */
    /*jslint evil: true */  
    window.jQuery || document.write('<script src="${pageContext.request.contextPath}/components/jquery/dist/jquery.min.js"><\/script>');
    </script>
<script>
$(function(){

new WOW().init();
})
</script>
</body>
</html>