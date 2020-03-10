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
<title>后台管理系统</title>
</head>
<body class="goodscheap">
	<div class="htm-entry">
		<form action="searchshopList" method="post" class="form-inline">
			<div class="entry-line1 row">
				<div class="form-group col-xs-4">
					<label class="control-label">手机号</label> <input type="text"
						name="mobile" class="form-control" /> <input type="submit"
						class="btn btn-primary" value="查询" />
				</div>
			</div>


		</form>

	</div>
	<div class="htm-table">
		<table class="table text-center table-bordered table-striped">
			<tr>
				<td>ID</td>
				<td>手机号</td>
				<td>车型</td>
				<td>报修人</td>
				<td>报修店名</td>
				<td>状态</td>
				<td>创建时间</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${repairstationCustomerList}" var="repairstationCustomerList">
				<tr>
					<td>
						<div id="divid">${repairstationCustomerList.id}</div>
					</td>

					<td>
						<p>${repairstationCustomerList.mobile}</p>
					</td>
					<td>
						<p>${repairstationCustomerList.cartype}</p>
					</td>
					<td>
						<p>${repairstationCustomerList.name}</p>
					</td>
					<td>
						<p>${repairstationCustomerList.repairstationname}</p>
					</td>
					<td>
						<p><c:if test="${repairstationCustomerList.status==1}">
						已处理</c:if><c:if test="${repairstationCustomerList.status==0}">
						未处理</c:if></p>
					</td>
					<td>
						<p>${repairstationCustomerList.createtime}</p>
					</td>
					<td>
						<button class="btn btn-default"
							onclick="lookdetail(${repairstationCustomerList.id})">查看基本信息</button>
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
  function lookdetail(i) {
	  window.location.href = "getRepairInfo?repairid="+i;
  }
  </script>
</body>
</html>