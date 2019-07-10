<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<%@ include file="/header.jsp"%>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>user list</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/./css/bootstrap.min.css">

<script src="${pageContext.request.contextPath}/./js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath }/./js/bootstrap.min.js"></script>

<h3>list undercarrige</h3>

 <div style="padding: 70px 550px 10px">
    	<%-- <form   method="post" action="${pageContext.request.contextPath }/servlet/ManagementServlet?op=searchUser" class="form-inline"  id="searchform">
        	<div class="input-group">
           <input type="text" placeholder="input your userId" class="form-control" id="search" name="userId" class="form-control">
            <span class="input-group-btn">
                  <input type="submit" value="search" class="btn btn-default">
            </span>
        </div>
    </form> --%>
    <script>
        function mySubmit(flag){
            return flag;
        }
        $("#searchform").submit(function () {
            var val=$("#search").val();
            if(val==''){
                alert("please input right");
                return mySubmit(false);
            }
        })
    </script>
</div>

	<table class="table table-striped" style="width: 70%;">
		<tr>
		    <th>goodsId</th>
			<th>userId</th>
			<th>goodsName</th>
			<th>description</th>
			<th>goodStatus</th>
			<th>categoryId</th>
			<th>price</th>
		</tr>
		<c:forEach items="${xiajia}" var="b" varStatus="vs">
		
				<tr>
			    <td id="id">${b.goods_id}</td>
				<td id="username">${b.user_id}</td>
				<td id="password">${b.goods_name}</td>
				<td id="phone">${b.description}</td>
				<td id="userNumber">${b.goods_status}</td>
				<td id="email">${b.category_id}</td>
				<td id="status">${b.price}</td> 
				<%-- <td>
					<button type="submit" class="btn btn-danger"
						onclick="javascript:window.location.href='${pageContext.request.contextPath}/servlet/ManagementServlet?op=xiaJia&goodsId=${b.goods_id}'">undercarriage</button>
					<button type="submit" class="btn btn-info"
						onclick="javascript:window.location.href='${pageContext.request.contextPath}/servlet/ManagementServlet?op=recover&goodsId=${b.goods_id}'">recover</button>
				
				</td> --%>
				</tr>
		</c:forEach>
	</table>
</body>
</html>
