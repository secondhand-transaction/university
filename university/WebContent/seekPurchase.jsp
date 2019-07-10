<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="dao.XutaigeDao" %>
<%@page import="entity.Category" %>
<%@page import="java.util.List" %>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>publish发布页面</title>
<!--自己的样式-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/backstage.css">
<!-- Bootstrap -->
<!--fileinput 样式-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/fileinput.css">
<link href="css/bootstrap.min.css" rel="stylesheet">
<!--动画CSS插件-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/animate.min.css">
   <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/fileinput.js"></script>
<script src="${pageContext.request.contextPath}/js/zh.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap-select.min.js"></script>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-select.min.css">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-select.css">



<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
</head>
<%
	XutaigeDao dao = new XutaigeDao();
	List<Category> categorys = dao.getCatagorys();
	for(Category c : categorys) {
		System.out.println(c.getCategory_name());
	}
	request.getSession().setAttribute("cs", categorys); 

%>

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
<ul id="systemSetting"
					class="nav nav-pills nav-stacked collapse in ">
					<li><a href="GetUserInfor.doHao"><i class="glyphicon glyphicon-user"></i>个人信息</a></li>
					<li><li ><a href="cart.jsp" class="ex0 active"><i
								class="glyphicon glyphicon-home"></i>购物车</a></li>
						<li><a href="publish.jsp"><i
								class="glyphicon glyphicon-edit"></i>发布产品</a></li>
						<li class="active"><a href="seekPurchase.jsp"> <i
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
						<li><a href="HistoryGoods.doCui" class="ex0 active"> <i
								class="glyphicon glyphicon-inbox"></i>作为买家
						</a></li>
						<li><a href="HistortSeller.doCui"> <i
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

 
    
    <!-- 显示表格数据 -->
    
    <div class=" col-md-8 publish wow fadeInUp" id="right"><!-- 将数据appendTo tbody 定义id -->
      <form class="form-horizontal" action="${pageContext.request.contextPath}/SeekPurchaseServlet?userId=1"
				method="post">
        <div class="form-group">
          <label  class="col-sm-2">商品名称:</label>
          <div class="col-sm-3">
            <input type="text" class="form-control" id="title" name="queryName" >
            <span	class="help-block"></span> </div>
        </div>
        <div class="form-group">
          <label  class="col-sm-2">商品分类:</label>
          <div class="col-sm-3">
          	<select name="categoryId" class="selectpicker" >
				<c:forEach items="${cs}" var="c">
    				<option value="${c.category_id}">${c.category_name}</option>
    			</c:forEach> 
    		</select>
            <span	class="help-block"></span> </div>
        </div>
<!--         <div class="form-group">
          <label  class="col-sm-2">商品详情:</label>
          <div class="col-sm-7">
            <textarea rows="3" cols="70" name="note"></textarea>
            <span	class="help-block"></span> </div>
        </div>
        <div class="form-group">
          <label  class="col-sm-2">商品图片:</label>
          <div class="col-sm-10"> -->

        <!-- 发布人ID 隐藏-->
        <button type="submit"  class="btn btn-primary  col-sm-2 col-md-offset-2" id="expaddbtn">发布</button>
        
        <button type="reset" class="btn btn-danger col-sm-2 " id="exp_delete_all_btn">重置</button>
          </div>
        </div>
        
      </form>
    </div>
  </div>
</div>
</div>
<script src="js/wow.min.js"></script>
<script>
$(function(){

new WOW().init();
})
</script>
<script>
$("#file_upload").fileinput({

                language: 'zh', //设置语言

                uploadUrl:"http://127.0.0.1/", //上传的地址

               allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀

               //uploadExtraData:{"id": 1, "fileName":'123.mp3'},

                uploadAsync: true, //默认异步上传

                showUpload:true, //是否显示上传按钮

                showRemove :true, //显示移除按钮

                showPreview :true, //是否显示预览

                showCaption:true,//是否显示标题

                browseClass:"btn btn-primary", //按钮样式    

               dropZoneEnabled: true,//是否显示拖拽区域

               //minImageWidth: 50, //图片的最小宽度

               //minImageHeight: 50,//图片的最小高度

               //maxImageWidth: 1000,//图片的最大宽度

               //maxImageHeight: 1000,//图片的最大高度

                //maxFileSize:0,//单位为kb，如果为0表示不限制文件大小

               //minFileCount: 0,

                maxFileCount:10, //表示允许同时上传的最大文件个数

                enctype:'multipart/form-data',

               validateInitialCount:true,

                previewFileIcon: "<iclass='glyphicon glyphicon-king'></i>",

               msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",

           }).on("fileuploaded", function (event, data, previewId, index){
 //上传图片后的回调函数，可以在这做一些处理
                 

});
</script>
</body>
