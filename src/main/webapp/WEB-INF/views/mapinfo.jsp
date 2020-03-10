<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="f" uri="/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="edge">
<link rel="stylesheet"
	href="component/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="www/css/index.css">
<title>后台管理系统</title>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="component/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="www/js/fontchange.js"></script>
<script src="lib/swfupload/swfupload.js"></script>
<script src="lib/swfupload/fileupload.js"></script>
<script src="js/global.js"></script>
<style type="text/css">
body, html, #allmap {
	width: 100%;
	height: 100%;
	overflow: hidden;
	margin: 0;
	font-family: "微软雅黑";
}
</style>
<script type="text/javascript"
	src="//api.map.baidu.com/api?v=2.0&ak=PyLa5E6K6BEGI0euwz1s9dWc"></script>

</head>
<body class="userInfo">
	<div class="htm-bread">
		<ol class="breadcrumb">
			<li>设备管理</li>
			<li class="active">历史轨迹</li>
		</ol>
	</div>


	<div id="allmap"></div>

</body>
<script type="text/javascript">
	//百度地图API功能
	var map = new BMap.Map("allmap");
	var myP1 = new BMap.Point(116.380967, 39.913285); //起点
	var myP2 = new BMap.Point(116.424374, 39.914668); //终点
	var myIcon = new BMap.Icon("/jsdemo/img/Mario.png", new BMap.Size(32, 70),
			{ //小车图片
				imageOffset : new BMap.Size(0, 0)
			//图片的偏移量。为了是图片底部中心对准坐标点。
			});
	var driving2 = new BMap.DrivingRoute(map, {
		renderOptions : {
			map : map,
			autoViewport : true
		}
	}); //驾车实例
	driving2.search(myP1, myP2); //显示一条公交线路
</script>
</html>