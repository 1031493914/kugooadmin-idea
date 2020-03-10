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
    <script src="js/jquery-3.1.1.min.js"></script>
    <title>系统设置</title>
	<script src="js/views/message.js"></script>
    <script src="js/layer/layer.js"></script>



</head>
<body class="sendinstationmsg">
    <div class="htm-bread">
        <ol class="breadcrumb">
            <li>系统设备</li>
            <li>运营管理</li>
            <li class="active">创建动态</li>
        </ol>
    </div>

    <form action="addAdminNewsSubmit" method="POST" id="form1">
        <input type="hidden" name="id" value="${adminNews.id}" />
        <div class="htm-msg">
            <div class="msg-title row">
                <div class="pull-left">添加动态</div>
            </div>
            <div class="form-group row">
                <div class="col-xs-3 text-center">
                    <label for="">动态标题</label>
                </div>
                <div class="col-xs-4 text-left">
                    <input type="text" name="pushtitle" id="pushtitle" class="form-control" value="${adminNews.title}">
                </div>
                
            </div>
            <div class="form-group row">
                <div class="col-xs-3 text-center">
                    <label for="">动态内容</label>
                </div>
                <div class="col-xs-4 text-left">
                    <input type="text" name="pushcontent" id="pushcontent" class="form-control" value="${adminNews.content}">
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
<script>

    function getOn(){
        var bool=true;
        var pushtitle=$('[name="pushtitle"]').val();
        var pushcontent=$('[name="pushcontent"]').val();
        var id=$('[name="id"]').val();
        if(pushtitle==""){
            bool=false;
            alert("推送标题不能为空");
        }
        if(pushcontent==""){
            bool=false;
            alert("推送内容不能为空");
        }

        if(bool){
            if(id == ""){
                $("#form1").submit();
            }else{
                $("#form1").attr('action',"updateAdminNewsSubmit");    //通过jquery为action属性赋值
                $("#form1").submit();
            }

        }
    }


</script>
</body>
</html>