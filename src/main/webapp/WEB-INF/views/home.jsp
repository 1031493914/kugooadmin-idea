<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title>管理后台</title>
<link rel="stylesheet" href="www/font-face/css/font-awesome.min.css" />
<link rel="stylesheet"
	href="component/bootstrap/dist/css/bootstrap.min.css" />
<link rel="stylesheet" href="www/src/AdminLTE.min.css" />
<link rel="stylesheet" href="www/src/skin-green.min.css" />
<link rel="stylesheet" href="www/css/index.css">
<script src="js/jquery-3.1.1.min.js"></script>
<script src="component/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="www/js/app.js"></script>
<script src="www/js/index.js"></script>

<style>
.storage-btn {
	opacity: 1 !important;
	cursor: auto !important;
	background: transparent !important;
}

.storage-btn:hover {
	color: white !important;
}

:root a {
	text-decoration: none;
}

.row {
	margin-left: 0;
	margin-right: 0;
}

.alert-order-msg,.alert-after-msg {
	position: fixed;
	z-index: 999999;
	height: 125px;
	width: 350px;
	right: -350px;
	background: url('images/alert-bg.png') no-repeat;
	background-size: 350px 125px;
	transition: 1s ease-out;
	color: black;
}

.alert-order-msg {
	bottom: 10px;
}

.alert-after-msg {
	bottom: 140px;
}

.alert-order-msg .alert-title,.alert-after-msg .alert-title {
	color: black;
	font-size: 20px;
	margin-bottom: 7%;
}

.close-order,.close-after {
	background-color: white;
	border: solid 1px #337AB7;
	color: #337AB7;
}
</style>


</head>
<body class="hold-transition skin-green sidebar-mini"
	style='overflow-y: hidden;'>
	<div class="etc wrapper ">
		<header class="main-header" style='position: fixed; width: 100%'>
			<!-- Logo -->
			<a href="home" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
				<span class="logo-mini"><b>KUGOO</b></span> <!-- logo for regular state and mobile devices -->
				<span class="logo-lg">管理后台</span>
			</a>
			<!-- Header Navbar -->
			<nav class="navbar navbar-static-top" role="navigation"
				style="padding-right: 2%">
				<!-- Sidebar toggle button-->
				<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
					role="button"> <span class="sr-only">Toggle navigation</span>
				</a>

				<div class="navbar-custom-menu">
					<ul class="nav navbar-nav">

						<!-- User Account: style can be found in dropdown.less -->
						<li class="dropdown user user-menu"><a href="home"
							class="dropdown-toggle " data-toggle="dropdown">

								${sessionAdminUser.content}<span class="caret"></span>
						</a>
							<ul class="dropdown-menu">

								<!-- Menu Footer-->
								<li class="user-footer"><a href="loginout"
									class="btn btn-default">退出登陆</a></li>
							</ul></li>
						<!-- Control Sidebar Toggle Button -->

					</ul>
				</div>

			</nav>

		</header>
		<!--end--截止到这是导航项-->
		<!-- 总仓管理 -->

		<aside class="main-sidebar" style='position: fixed'>
			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar">
				<!-- Sidebar Menu -->
				<ul class="sidebar-menu">
					<!--<li class="header">HEADER</li>-->




					<c:if test="${sessionAdminUser.role==1 ||sessionAdminUser.role==2 || sessionAdminUser.role==3}">

						<li class=" treeview"><a href=""> <i class="fa fa-th"></i>
							<span>首页管理</span> <span class="pull-right-container"> <i
									class="fa fa-angle-left pull-right"></i>
						</span>
						</a>

							<ul class="treeview-menu">
								<li><a id="merchandise-control" target="right"
									   href="indexdata">数据概览</a></li>

							</ul>
						</li>
						<li class=" treeview"><a href=""> <i class="fa fa-th"></i>
							<span>设备管理</span> <span class="pull-right-container"> <i
									class="fa fa-angle-left pull-right"></i>
						</span>
						</a>

							<ul class="treeview-menu">
								<li><a id="merchandise-control" target="right"
									   href="devicecount">设备激活数据总览</a></li>
								<li><a id="merchandise-control" target="right"
									   href="devicemanagelist">设备/APP列表</a></li>
								<li><a id="merchandise-control" target="right"
									   href="devicearea">设备/APP分布</a></li>
								<li><a id="merchandise-control" target="right"
									   href="devicebreakdown">故障管理</a></li>
								<li><a id="merchandise-control" target="right"
									   href="devicetype">型号管理</a></li>
								<li><a id="merchandise-control" target="right"
									   href="deviceseller">销售商管理</a></li>
								<li><a id="merchandise-control" target="right"
									   href="deviceCompanyList">设备厂商管理</a></li>
								<li><a id="merchandise-control" target="right"
									   href="outnum">厂商出货量查询</a></li>
							</ul>
						</li>
						<li class=" treeview"><a href="#"> <i class="fa fa-list"></i>
							<span>参数管理</span> <span class="pull-right-container"> <i
									class="fa fa-angle-left pull-right"></i>
						</span>
						</a>
							<ul class="treeview-menu">
								<li><a id="emit-control" target="right"
									   href="devicesetting">参数管理</a></li>
							</ul>
						</li>
						<li class=" treeview"><a href="#"> <i class="fa fa-list"></i>
							<span>运营管理</span> <span class="pull-right-container"> <i
									class="fa fa-angle-left pull-right"></i>
						</span>
						</a>
							<ul class="treeview-menu">
								<li><a id="emit-control" target="right"
									   href="operationmanagelist">动态发布</a></li>
							</ul>
						</li>
					</c:if>

					<c:if test="${sessionAdminUser.role==1}">
						<li class="treeview"><a href="#"> <i class="fa fa-list"></i>
							<span>推送管理</span> <span class="pull-right-container"> <i
									class="fa fa-angle-left pull-right"></i>
                            </span>
						</a>
							<ul class="treeview-menu">
								<li><a id="emit-control" target="right"
									   href="pushlist">推送管理</a></li>
							</ul>
						</li>
                        <li class=" treeview"><a href="#"> <i class="fa fa-list"></i>
                            <span>系统设置</span> <span class="pull-right-container"> <i
                                    class="fa fa-angle-left pull-right"></i>
                            </span>
                        </a>
                            <ul class="treeview-menu">
                                <li><a id="emit-control" target="right"
                                       href="adminrolelist">权限管理</a></li>
                            </ul>
                        </li>
					</c:if>

					<c:if test="${sessionAdminUser.role==4}">

						<li class="treeview"><a href="#"> <i class="fa fa-list"></i>
							<span>应用管理</span> <span class="pull-right-container"> <i
									class="fa fa-angle-left pull-right"></i>
                            </span>
						</a>
							<ul class="treeview-menu">
								<li><a id="emit-control" target="right"
									   href="adminapplist">应用管理</a></li>
							</ul>
						</li>

						<li class="treeview"><a href="#"> <i class="fa fa-list"></i>
							<span>账号管理</span> <span class="pull-right-container"> <i
									class="fa fa-angle-left pull-right"></i>
                            </span>
						</a>
							<ul class="treeview-menu">
								<li><a id="emit-control" target="right"
									   href="sdkuserlist">SDK使用方账号管理</a></li>
							</ul>
						</li>

					</c:if>

					<c:if test="${sessionAdminUser.role==5}">

						<li class="treeview"><a href="#"> <i class="fa fa-list"></i>
							<span>应用管理</span> <span class="pull-right-container"> <i
									class="fa fa-angle-left pull-right"></i>
                            </span>
						</a>
							<ul class="treeview-menu">
								<li><a id="emit-control" target="right"
									   href="sdkapplist">应用管理</a></li>
							</ul>
						</li>


						</li>

					</c:if>
				</ul>
				<!-- /.sidebar -->
		</aside>
		<div id="text" class="content-htm content-wrapper"
			style="height: 100%">
			<div class='visible-xs seat-xs hidden-sm'></div>
			<div class=' visible-sm seat-sm hidden-md'></div>
			<div class='visible-md seat-md hidden-lg'></div>
			<div class='visible-lg '>
				<br>
			</div>
			<c:if test="${sessionAdminUser.role==1 ||sessionAdminUser.role==2 || sessionAdminUser.role==3}">
				<iframe scrolling='auto' id="right" name="right" width="100%"
						style="border: none;" src="indexdata"></iframe>
			</c:if>
			<c:if test="${sessionAdminUser.role==4}">
				<iframe scrolling='auto' id="right" name="right" width="100%"
						style="border: none;" src="adminapplist"></iframe>
			</c:if>
			<c:if test="${sessionAdminUser.role==5}">
				<iframe scrolling='auto' id="right" name="right" width="100%"
						style="border: none;" src="sdkapplist"></iframe>
			</c:if>

		</div>

		<div class='main-header'
			style='position: fixed; bottom: 0; width: 100%; height: 20px; color: white'>
			<a href="#" class="logo"
				style='z-index: -1; background-color: #222D32'> <!-- mini logo for sidebar mini 50x50 pixels -->
				<span class="logo-mini"></span> <!-- logo for regular state and mobile devices -->
				<span class="logo-lg"></span>
			</a>
			<nav class="navbar navbar-static-top static-bottom text-center"
				role="navigation">
				温 馨 提 示：推 荐 使 用 chrome 浏览器 &nbsp; &nbsp;&nbsp; ——— <a
					style='color: white'
					href='http://sw.bos.baidu.com/sw-search-sp/software/1c5131aea1842/ChromeStandalone_56.0.2924.87_Setup.exe
		 	'>点
					击下 载 </a>
			</nav>
		</div>
	</div>
	<script>
		function iFrameHeight() {
			var ifm = $("iframe");

			//var h = document.body.clientHeight - 20;//110是框架中其他块儿的
			var a = $(window).height();

			if (ifm != null) {
				ifm.height(a);
				console.log(a);
				console.log(ifm);

			}
		}
		$(window).resize(iFrameHeight);
		$(document).ready(iFrameHeight);
	</script>

</body>
</html>