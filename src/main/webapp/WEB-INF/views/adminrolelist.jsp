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
        <li>系统设置</li>
        <li>权限管理</li>
        <li class="active">用户权限管理</li>
    </ol>
</div>


<div class="htm-msg">

    <div class="htm-entry">


            <div class="row">
                <div class="col-xs-6 ">

                </div>
                <div class="pull-right">
                    <button type="button" class="btn btn-primary" onclick="createAdminUser()">创建角色</button>&nbsp;&nbsp;

                </div>
            </div>

    </div>

    <div class="htm-table" >
        <table class="table text-center table-bordered table-striped" id="htmtable">
            <tr>
                <td>ID</td>
                <td>名称</td>
                <td>登录名</td>
                <td>登录密码</td>
                <td>角色名称</td>
                <td>时间</td>
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
                        <c:if test="${listAdminUser.role=='1'}">管理员</c:if>
                        <c:if test="${listAdminUser.role=='2'}">厂家</c:if>
                        <c:if test="${listAdminUser.role=='3'}">销售商</c:if>
                        <c:if test="${listAdminUser.role=='4'}">SDK应用管理方</c:if>
                        <c:if test="${listAdminUser.role=='5'}">SDK应用使用方</c:if>
                    </td>
                    <td>
                            ${listAdminUser.createtime}

                    </td>
                </tr>
            </c:forEach>
        </table>

    </div>



</div>

<script type="text/javascript">

    function createAdminUser(){
        window.location.href="createaddAdminUser"
    }
</script>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="www/js/fontchange.js"></script>
</body>
</html>