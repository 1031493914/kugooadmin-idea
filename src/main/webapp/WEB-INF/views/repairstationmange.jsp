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
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<link rel="stylesheet" href="component/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="www/css/index.css">
<link rel="stylesheet" href="css/pagelist.css">
<script src="js/global.js"></script>
<title>后台管理系统</title>
</head>
<body class="goodscheap">
	<div class="htm-entry">
		<form action="searchrepairstationList" method="post" class="form-inline">
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
				<td>维修店图片</td>
				<td>维修店名称</td>
				<td>维修点地址</td>
				<td>营业时间</td>
				<td>联系人名称</td>
				<td>联系人电话</td>
				<td>状态</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${repairstationList}" var="repairstationList">
				<tr>
					<td>
						<div id="divid">${repairstationList.id}</div>
					</td>

					<td>
						<p><img src="http://47.52.238.166:8888/${repairstationList.img}" alt="" width="30%"></p>
					</td>
					<td>
						<p>${repairstationList.name}</p>
					</td>
					<td>
						<p>${repairstationList.address}</p>
					</td>
					<td>
						<p>${repairstationList.buisnesstime}</p>
					</td>
					<td>
						<p>${repairstationList.connectname}</p>
					</td>
					<td>
						<p>${repairstationList.connectmobile}</p>
					</td>
					<td>
						<p><c:if test="${repairstationList.status==1}">
						正常</c:if><c:if test="${repairstationList.status==0}">
						无效</c:if></p>
					</td>
					<td>
					<p><c:if test="${repairstationList.status==1}">
						<button class="btn btn-default"
							onclick="repairstationstatus(${repairstationList.id},1)">下架</button>
						</c:if>
						<c:if test="${repairstationList.status==0}">
						<button class="btn btn-default"
							onclick="repairstationstatus(${repairstationList.id},0)">上架</button>
						</c:if>
					</p>	
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
	  function repairstationstatus(id,type) {
		  if(type==1){
			  if (window.confirm("确定下架嘛？")) {
					$.post("repairstationstatus", {
						'id' : id,
						'type':type
					}, function(data) {
						if (data) {
							alert("处理成功");
							window.location.href = "repairstationmanagelist";
							return;
						}else{
							alert("处理失败");
						}
					});// 后台上架处理
				}
		  }else{
			  if (window.confirm("确定上架嘛？")) {
					$.post("repairstationstatus", {
						'id' : id,
						'type':type
					}, function(data) {
						if (data) {
							alert("处理成功");
							window.location.href = "repairstationmanagelist";
							return;
						}else{
							alert("处理失败");
						}
					});// 后台上架处理
				}
		  }
			
		}

  </script>
</body>
</html>