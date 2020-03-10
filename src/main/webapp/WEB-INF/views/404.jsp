<%@ page language="java" pageEncoding="utf-8"%>
<% 
	String path = request.getContextPath(); 
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	request.setAttribute("basePath",basePath);
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title></title> 
    <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
    <script src="js/default.js"></script>
</head>
<body>
<h2><span style="color: black;font-weight:bold;margin-right: 2em;">404</span><span style="color: black;">This resource no longer exists.</span></h2>
</body></html>