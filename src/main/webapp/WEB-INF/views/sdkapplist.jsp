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
    <title>应用管理</title>
    <!-- 引入 ECharts 文件 -->
    <script src="echarts/echarts.js"></script>
</head>
<body class="sendinstationmsg">
<div class="htm-bread">
    <ol class="breadcrumb">
        <li>系统设置</li>
        <li>应用管理</li>
        <li class="active">用户应用管理</li>
    </ol>
</div>


<div class="htm-msg">

    <div class="htm-entry">


            <div class="row">
                <div class="col-xs-6 ">

                </div>
                <div class="pull-right">
                    <button type="button" class="btn btn-primary" onclick="createSdkApp(0)">创建应用</button>&nbsp;&nbsp;

                </div>
            </div>

    </div>

    <div class="htm-table" >
        <table class="table text-center table-bordered table-striped" id="htmtable">
            <tr>
                <td>ID</td>
                <td>应用名称</td>
                <td>应用包名</td>
                <td>应用APPEKY</td>
                <td>状态</td>
                <td>创建时间</td>
                <td>操作</td>
            </tr>
            <c:forEach items="${listSdkApp}" var="listSdkApp">
                <tr>
                    <td>
                            ${listSdkApp.id}
                    </td>
                    <td>
                            ${listSdkApp.appname}

                    </td>

                    <td>
                            ${listSdkApp.packagename}

                    </td>
                    <td>
                            ${listSdkApp.appkey}

                    </td>
                    <td>
                        <p>
                            <c:if test="${listSdkApp.status==-1}">编辑</c:if>
                            <c:if test="${listSdkApp.status==0}">待审核</c:if>
                            <c:if test="${listSdkApp.status==1}">审核通过</c:if>
                            <c:if test="${listSdkApp.status==2}">审核不通过</c:if>
                            <c:if test="${listSdkApp.status==3}">封禁</c:if>
                        </p>
                    </td>
                    <td>
                            ${listSdkApp.createtime}

                    </td>

                    <td>

                        <c:if test="${listSdkApp.status==-1}">
                        <button class="btn btn-default"
                                onclick="operatorapp(${listSdkApp.id},0)">提交审核
                        </button>

                    </c:if>
                        <c:if test="${listSdkApp.status==0}">
                            <button class="btn btn-default"
                                    onclick="createSdkApp(${listSdkApp.id})">编辑
                            </button>

                        </c:if>
                        <c:if test="${listSdkApp.status==1}">
                            <button class="btn btn-default"
                                    onclick="operatorapp(${listSdkApp.id},4)">删除
                            </button>

                        </c:if>
                        <c:if test="${listSdkApp.status==2}">
                            <button class="btn btn-default"
                                    onclick="createSdkApp(${listSdkApp.id})">编辑
                            </button>

                        </c:if>

                    </td>
                </tr>
            </c:forEach>
        </table>

    </div>



</div>

<script type="text/javascript">

    function operatorapp(appid,status){


        if (window.confirm("确定操作嘛？")) {
            $.post("updateAppStatus", {
                'id' : appid,
                'status':status
            }, function(data) {
                if (data) {
                    alert("处理成功");
                    window.location.href = "sdkapplist";
                    return;
                }else{
                    alert("处理失败");
                }
            });// 后台上架处理
        }

    }

    function createSdkApp(id){

        window.location.href = "addSdkApp?appid="+id;

    }
</script>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="www/js/fontchange.js"></script>
</body>
</html>