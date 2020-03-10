<%@ page language="java" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="f" uri="/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
    <title>系统分布</title>
    <!-- 引入 ECharts 文件 -->
    <script src="echarts/echarts.js"></script>
</head>
<body class="sendinstationmsg">
<div class="htm-bread">
    <ol class="breadcrumb">
        <li>账号管理</li>
        <li>SDK使用方账号管理</li>
        <li class="active">SDK使用方列表</li>
    </ol>
</div>


<div class="htm-msg">

    <div class="htm-entry">


        <div class="row">
            <div class="col-xs-6 ">

            </div>
            <div class="pull-right">
                <button type="button" class="btn btn-primary" onclick="createSdkUser()">添加使用方</button>&nbsp;&nbsp;

            </div>
        </div>

    </div>

    <div class="htm-table">
        <table class="table text-center table-bordered table-striped" id="htmtable">
            <tr>
                <td>ID</td>
                <td>名称</td>
                <td>登录名</td>
                <td>登录密码</td>
                <td>角色名称</td>
                <td>状态</td>
                <td>时间</td>
                <td>操作</td>
            </tr>
            <c:forEach items="${listAdminUser}" var="listAdminUser">
                <tr>
                    <td>
                            ${listAdminUser.id}
                    </td>
                    <td>
                            ${listAdminUser.content}

                    </td>

                    <td>
                            ${listAdminUser.loginname}

                    </td>
                    <td>
                            ${listAdminUser.loginpwd}

                    </td>
                    <td>
                        SDK使用方
                    </td>
                    <td>
                        <c:if test="${listAdminUser.status==0}">
                            失效
                        </c:if>
                        <c:if test="${listAdminUser.status==1}">
                            正常
                        </c:if>
                    </td>
                    <td>
                            ${listAdminUser.createtime}

                    </td>
                    <td>
                        <c:if test="${listAdminUser.status==0}">
                            <button class="btn btn-default"
                                    onclick="operatorUser(${listAdminUser.id},1)">启用
                            </button>

                        </c:if>
                        <c:if test="${listAdminUser.status==1}">
                            <button class="btn btn-default"
                                    onclick="operatorUser(${listAdminUser.id},0)">封禁
                            </button>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>

    </div>


</div>

<script type="text/javascript">
    function operatorUser(userid,status){


        if (window.confirm("确定操作嘛？")) {
            $.post("operatorUser", {
                'id' : userid,
                'status':status
            }, function(data) {
                if (data) {
                    alert("处理成功");
                    window.location.href = "sdkuserlist";
                    return;
                }else{
                    alert("处理失败");
                }
            });// 后台上架处理
        }

    }

    function createSdkUser() {
        window.location.href = "createaddSdkUser"
    }
</script>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="www/js/fontchange.js"></script>
</body>
</html>