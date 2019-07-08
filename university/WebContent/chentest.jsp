<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
</head>

			<title>加入按钮</title> 
			<p>${sessionScope.counts}</p>
			<c:choose>
				<c:when test="${sessionScope.counts eq 5 }">购物车达上限(5)</c:when>
				<c:otherwise>
					<form name="form1" target="submitFrame" action="OrderServlet?"
						method='post'>
						<button type="submit" name="submit" value="join" onclick="myFunction()" >加入购物车</button>
					</form>
					<iframe src="" name="submitFrame" width="0" height="0"></iframe>
				</c:otherwise>
			</c:choose>
<a href="cart.jsp">cart</a>
</br>
<form name="form1" target="submitFrame" action="OrderServlet?"
						method='post'>
<button type="submit" name="submit" value="sellersure" onclick="myFunction()" >卖家确认</button>
<button type="submit" name="submit" value="sellercancel" onclick="myFunction()" >卖家取消</button>
</form>
</br>
<form name="form1" target="submitFrame" action="OrderServlet?"
						method='post'>
<button type="submit" name="submit" value="buyersure" onclick="myFunction()" >买家确认</button>
<button type="submit" name="submit" value="buyercancel" onclick="myFunction()" >买家取消</button>
</form>
<script src="js/wow.min.js"></script>
<script type="text/javascript">
	//多行注释
	/* 我是多行注释！
	 我需要隐藏，
	 否则会报错哦！*/

	//在页面中显示文字
	//页面中弹出提示框
	function myFunction() {
		alert('加入成功!')
	}

	//单行注释
	//我是单行注释，我也要隐藏起来！
</script>
<script>
	$(function() {

		new WOW().init();
	})
</script>
</body>
</html>
