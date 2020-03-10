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
    <li>系统分布</li>
    <li>设备管理</li>
    <li class="active">设备厂商管理</li>
    </ol>
</div>


<div class="htm-msg">

    <div class="htm-entry">


            <div class="row">
                <div class="col-xs-6 ">

                </div>
                <div class="pull-right">
                    <button type="button" class="btn btn-primary" onclick="createDeviceCompany()">创建厂商</button>&nbsp;&nbsp;

                </div>
            </div>

    </div>

    <div class="htm-table" >
        <table class="table text-center table-bordered table-striped" id="htmtable">
            <tr>
                <td>ID</td>
                <td>厂商名称</td>
                <td>厂商代码</td>
                <td>登录名</td>
                <td>登录密码</td>
                <td>时间</td>
            </tr>
            <c:forEach items="${devicecompanyList}" var="devicecompanyList">
                <tr>
                    <td>
                            ${devicecompanyList.id}
                    </td>
                    <td>
                            ${devicecompanyList.name}

                    </td>

                    <td>
                            ${devicecompanyList.code}

                    </td>
                    <td>
                            ${devicecompanyList.loginname}

                    </td>
                    <td>
                            ${devicecompanyList.loginpwd}
                    </td>
                    <td>
                            ${devicecompanyList.create_time}

                    </td>
                </tr>
            </c:forEach>
        </table>

    </div>



</div>

<script type="text/javascript">

    function createDeviceCompany(){
        window.location.href="createDeviceCompany"
    }
</script>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="www/js/fontchange.js"></script>
</body>
</html>