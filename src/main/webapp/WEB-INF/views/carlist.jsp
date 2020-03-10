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
<title>车型管理</title>
</head>
<body class="goodscheap">
	<div class="htm-entry">
		<form action="searchshopList" method="post" class="form-inline">
			<div class="entry-line1 row">
				<div class="form-group col-xs-4">
					<label class="control-label">车型名称</label> <input type="text"
						name="mobile" class="form-control" /> <input type="submit"
						class="btn btn-primary" value="查询" />
				</div>
			</div>
		</form>

	</div>
			<div class="htm-operation row">
			<div class="pull-left">
			</div>
		</div>
	<div class="htm-table">
		<table class="table text-center table-bordered table-striped">
			<tr>
				<td>ID</td>
				<td>车名称</td>
				<td>车型号</td>
				<td>车型图片</td>
				<td>蓝牙ID</td>
				<td>创建时间</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${carList}" var="carList">
				<tr>
					<td>
						<div id="divid">${carList.id}</div>
					</td>

					<td>
						<p>${carList.name}</p>
					</td>
					<td>
						<p>${carList.type}</p>
					</td>
					<td>
						<p>${carList.img}</p>
					</td>
					<td>
						<p>${carList.blueid}</p>
					</td>
					<td>
						<p>${carList.createtime}</p>
					</td>
			
					<td>
						<button class="btn btn-default"
							onclick="lookdetail(${carListo.id})">删除</button>
					</td>
				</tr>
			</c:forEach>
		</table>

	</div>
	<div class="pagelist text-center">
		<f:page totalSize="${page.totalSize }" url="shopsManage"
			totalPage="${page.totalPage }" currentPage="${page.currentPage }" />
		&nbsp;&nbsp;&nbsp;&nbsp;跳转到第<input class='form-control' type="text"
			style="width: auto; display: inline-block" size="2em">页&nbsp;&nbsp;
		<a href="shopsManage?cp="
			onclick="$(this)[0].href=$(this)[0].href+$(this).prev().val();">确定</a>
	</div>
	<script type="text/javascript" src="js/jquery-1.8.1.min.js"></script>
	<script src="www/js/fontchange.js"></script>
	<script type="text/javascript">

		
  </script>
</body>
</html>