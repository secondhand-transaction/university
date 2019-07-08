<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">

<link rel="stylesheet" type="text/css" href="css/shop.css">
<title>详细信息</title>
</head>
<body>
<div id="main">
  
 <c:forEach items="${goods}" var="good" varStatus="gd">
  <div class="box">
  
    <div class="pic"><img src="./images/b5.jpg"/> 
    </div>
    <div class="foot_money "> <span>￥${good.price}</span>  </div>
    <div class="foot_title ">
      <p>商品ID:${good.goods_id}</p>
      <p>商品名称:${good.goods_name}<br/></p>
      <p><a href="#">卖家:${good.user_id}</a> <br/></p>
      <form method="post" action="jubao.do?goods_id=${good.goods_id}" >
    <input type="submit" value="举报" />
</form>

    </div>
    </div>
    
  </c:forEach>
</div>
</body>
</html>