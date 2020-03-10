<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="f" uri="/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<link rel="stylesheet"
	href="component/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="www/css/index.css">
<link rel="stylesheet" href="css/pagelist.css">
<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
<script src="lib/swfupload/swfupload.js"></script>
<script src="lib/swfupload/fileupload.js"></script>
<script src="js/global.js"></script>
<title>后台管理系统</title>
</head>
<body class="goodscheap">
	<div class="htm-operation row">
		<div class="pull-left">用户绑定车辆</div>
	</div>
	<div class="htm-table">
		<table class="table text-center table-bordered table-striped">
			<tr>
				<td>用户ID</td>
				<td>车辆ID</td>
				<td>车名称</td>
				<td>蓝牙状态</td>
				<td>连接状态</td>
				<td>车辆状态</td>
				<td>创建时间</td>
			</tr>
			<c:forEach items="${usercarList}" var="usercarList">
				<tr>
					<td>
						<div id="divid">${usercarList.userid}</div>
					</td>

					<td>
						<p>${usercarList.carid}</p>
					</td>
					<td>
						<p>${usercarList.carname}</p>
					</td>
					<td>
						<p><c:if test="${usercarList.bluestatus==0}">
						 未搜索到
						</c:if>
						<c:if test="${usercarList.bluestatus==1}">
						 已搜索到
						</c:if>
						</p>
					</td>
					<td>
						<p><c:if test="${usercarList.status==0}">
						 未连接
						</c:if>
						<c:if test="${usercarList.status==1}">
						已连接
						</c:if>
						<c:if test="${usercarList.status==2}">
						已失效
						</c:if>
						</p>
					</td>
					<td>
						<p><c:if test="${usercarList.type==0}">
						 当前车辆
						</c:if>
						<c:if test="${usercarList.type==1}">
						未使用车辆
						</c:if>
						
						</p>
					</td>
					<td>
						<p>${usercarList.createtime}</p>
					</td>

					
				</tr>
			</c:forEach>
		</table>

	</div>
	    	<div class="finish-btn row">
    	
    			<div class="row finish-btn bottom-button">
					<div class='col-xs-3 col-sm-4 col-md-4 col-lg-4'></div>
					<div class='col-xs-6 col-sm-4 col-md-4 col-lg-4 row'>
						<div class='col-xs-1'></div>
						<div class='col-xs-5'>
						<a href="javascript:history.back();"
				class="btn btn-primary btn-block">返 回 </a>
						</div>
					</div>

				</div>
    	
    	
	</div>
	<script type="text/javascript" src="js/jquery-1.8.1.min.js"></script>
	<script src="www/js/fontchange.js"></script>
	<script type="text/javascript">

		
  </script>
</body>
</html>