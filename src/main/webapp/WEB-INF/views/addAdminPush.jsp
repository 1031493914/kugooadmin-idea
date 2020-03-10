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
            <li>推送管理</li>
            <li class="active">创建推送</li>
        </ol>
    </div>

    <form action="addAdminPushSubmit" method="POST" id="form1">
        <div class="htm-msg">
            <div class="msg-title row">
                <div class="pull-left">添加推送</div>
            </div>
            <div class="form-group row">
                <div class="col-xs-3 text-center">
                    <label for="">推送标题</label>
                </div>
                <div class="col-xs-4 text-left">
                    <input type="text" name="pushtitle" id="pushtitle" class="form-control" value="">
                </div>
                
            </div>
            <div class="form-group row">
                <div class="col-xs-3 text-center">
                    <label for="">推送内容</label>
                </div>
                <div class="col-xs-4 text-left">
                    <input type="text" name="pushcontent" id="pushcontent" class="form-control" value="">
                </div>

            </div>

        
            <div class="form-group row">
              <div class="col-xs-3 text-center">
                    <label for="">推送平台</label>
                </div>
                <div class="col-xs-4 text-left">
                    <select name="pushplatform"
                            id="pushplatform" class="form-control">

                        <option value="0" ${status=="0"?'selected':''}>全平台</option>
                        <option value="1" ${status=="1"?'selected':''}>IOS</option>
                     <option value="2" ${status=="2"?'selected':''}>ANDROID</option>
                    </select>
                </div>
        </div>

                
            </div>
        
        
       
        <div class="finish-btn row">
            <div class="col-xs-4 col-sm-5 col-md-5 col-lg-5"></div>
            <div class="col-xs-4 col-sm-2 col-md-2 col-lg-2">
                <button class="btn btn-primary btn-block" type="button" onclick="getOn()" id="submit">完成</button>
            </div>
        </div>
    </form>
<script>

    function getOn(){
        var bool=true;
        var pushtitle=$('[name="pushtitle"]').val();
        var pushcontent=$('[name="pushcontent"]').val();
        var pushplatform=$('[name="pushplatform"]').val();
        if(pushtitle==""){
            bool=false;
            alert("推送标题不能为空");
        }
        if(pushcontent==""){
            bool=false;
            alert("推送内容不能为空");
        }
        if(pushplatform=="") {
            bool = false;
            alert("推送平台不能为空");
        }
        if(bool){
            //ajax
            var index =1;
            if (window.confirm("确定要发送推送吗？")) {
                $.ajax({
                    type: "post",
                    data: {
                        'pushtitle' : pushtitle,
                        'pushcontent' : pushcontent,
                        'pushplatform':pushplatform
                    },
                    url: "addAdminPush",
                    beforeSend: function () {
                        // 禁用按钮防止重复提交
                        $("#submit").attr({ disabled: "disabled" });
                        index=layer.msg('正在推送中，请稍候。。。。');
                    },
                    success: function (data) {
                        if (data.error == 0) {
                            layer.close(index);
                            alert('发送完成');
                            window.location.href = "pushlist";
                            return;
                        }
                    },
                    complete: function () {
                        layer.close(index);

                    },
                    error: function (data) {
                        layer.close(index);
                        alert('发送失败');
                        console.info("error: " + data.responseText);
                    }
                })




            }
        }
    }


</script>
</body>
</html>