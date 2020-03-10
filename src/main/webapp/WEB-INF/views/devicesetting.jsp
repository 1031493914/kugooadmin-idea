<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="f" uri="/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="edge">
    <link rel="stylesheet" href="component/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="www/css/index.css">
    <title>系统参数设置</title>
	<script src="js/views/message.js"></script>
</head>
<body class="sendinstationmsg">
    <div class="htm-bread">
        <ol class="breadcrumb">
            <li>参数管理</li>
            <li>参数管理</li>
            <li class="active">参数设置</li>
        </ol>
    </div>

    <form action="SystemSettingSubmit" method="POST">
        <div class="htm-msg">
            <div class="msg-title row">
                <div class="pull-left">参数设置</div>
            </div>
            <div class="form-group row">
                <div class="col-xs-3 text-center">
                    <label for="">静止频率（秒）</label>
                </div>
                <div class="col-xs-4 text-left">
                    <input type="text" name="settingon" id="settingon" class="form-control" value="${settingon }">
                </div>
                
            </div>
        
        
                 <div class="form-group row">
              <div class="col-xs-3 text-center">
                    <label for="">运动频率（秒）</label>
                </div>
                <div class="col-xs-4 text-left">
                    <input type="text" name="settingoff" id="settingoff" class="form-control" value="${settingoff }">
                </div>
        </div>


            <div class="form-group row">
                <div class="col-xs-3 text-center">
                    <label for="">故障频率（秒）</label>
                </div>
                <div class="col-xs-4 text-left">
                    <input type="text" name="settingbreakdown" id="settingbreakdown" class="form-control" value="${settingbreakdown }">
                </div>
            </div>
                
            </div>
        
        
       
        <div class="finish-btn row">
            <div class="col-xs-4 col-sm-5 col-md-5 col-lg-5"></div>
            <div class="col-xs-4 col-sm-2 col-md-2 col-lg-2">
                <button class="btn btn-primary btn-block" type="submit">完成</button>
            </div>
        </div>
    </form>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="www/js/fontchange.js"></script>
</body>
</html>