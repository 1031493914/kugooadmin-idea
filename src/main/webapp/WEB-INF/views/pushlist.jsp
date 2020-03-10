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
        <li>推送管理</li>
        <li class="active">推送管理</li>
    </ol>
</div>


<div class="htm-msg">

    <div class="htm-entry">


            <div class="row">
                <div class="col-xs-6 ">

                </div>
                <div class="pull-right">
                    <button type="button" class="btn btn-primary" onclick="createAdminPush()">发送推送</button>&nbsp;&nbsp;

                </div>
            </div>

    </div>

    <div class="htm-table" >
        <table class="table text-center table-bordered table-striped" id="htmtable">
            <tr>
                <td>ID</td>
                <td>推送标题</td>
                <td>推送内容</td>
                <td>推送时间</td>
                <td>推送平台</td>
                <td>是否成功</td>
            </tr>
            <c:forEach items="${listAdminPush}" var="listAdminPush">
                <tr>
                    <td>
                            ${listAdminPush.id}
                    </td>
                    <td>
                            ${listAdminPush.push_title}

                    </td>

                    <td>
                            ${listAdminPush.push_content}

                    </td>
                    <td>
                            ${listAdminPush.create_time}

                    </td>
                    <td>
                        <c:if test="${listAdminPush.push_platform=='0'}">全平台</c:if>
                        <c:if test="${listAdminPush.push_platform=='1'}">IOS</c:if>
                        <c:if test="${listAdminPush.push_platform=='2'}">ANDROID</c:if>

                    </td>
                    <td>
                        <c:if test="${listAdminPush.is_success=='0'}">成功</c:if>
                        <c:if test="${listAdminPush.is_success=='1'}">失败</c:if>

                    </td>
                </tr>
            </c:forEach>
        </table>

    </div>



</div>

<script type="text/javascript">

    function createAdminPush(){
        window.location.href="createaddAdminPush"
    }
</script>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="www/js/fontchange.js"></script>
</body>
</html>