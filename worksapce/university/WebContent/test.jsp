<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
			<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
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
