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
    <title>系统设置</title>
	<script src="js/views/message.js"></script>
</head>
<body class="sendinstationmsg">
    <div class="htm-bread">
        <ol class="breadcrumb">
            <li>账号管理</li>
            <li>SDK使用方账号管理</li>
            <li class="active">添加SDK使用方账号</li>
        </ol>
    </div>

    <form action="addSdkUserSubmit" method="POST" id="form1">
        <div class="htm-msg">
            <div class="msg-title row">
                <div class="pull-left">添加用户</div>
            </div>
            <div class="form-group row">
                <div class="col-xs-3 text-center">
                    <label for="">姓名</label>
                </div>
                <div class="col-xs-4 text-left">
                    <input type="text" name="content" id="content" class="form-control" value="">
                </div>
                
            </div>
            <div class="form-group row">
                <div class="col-xs-3 text-center">
                    <label for="">登录名</label>
                </div>
                <div class="col-xs-4 text-left">
                    <input type="text" name="loginname" id="loginname" class="form-control" value="">
                </div>

            </div>
            <div class="form-group row">
                <div class="col-xs-3 text-center">
                    <label for="">登录密码</label>
                </div>
                <div class="col-xs-4 text-left">
                    <input type="text" name="loginpwd" id="loginpwd" class="form-control" value="">
                </div>

            </div>
        
        
                 <div class="form-group row">
              <div class="col-xs-3 text-center">
                    <label for="">系统角色</label>
                </div>
                <div class="col-xs-4 text-left">
                    SDK应用方
                    <input type="hidden" value="5" name="role"></input>
                </div>
        </div>

                
            </div>
        
        
       
        <div class="finish-btn row">
            <div class="col-xs-4 col-sm-5 col-md-5 col-lg-5"></div>
            <div class="col-xs-4 col-sm-2 col-md-2 col-lg-2">
                <button class="btn btn-primary btn-block" type="button" onclick="getOn()">完成</button>
            </div>
        </div>
    </form>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="www/js/fontchange.js"></script>
<script>

    function getOn(){
        var bool=true;
        if($('[name="content"]').val()==""){
            bool=false;
            alert("姓名不能为空");
        }
        if($('[name="loginname"]').val()==""){
            bool=false;
            alert("登录名不能为空");
        }
        if($('[name="loginpwd"]').val()=="") {
            bool = false;
            alert("登录密码不能为空");
        }
        if(bool){
            $("#form1").submit();
        }
    }


</script>
</body>
</html>