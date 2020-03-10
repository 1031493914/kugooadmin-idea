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
        <li>运营管理</li>
        <li class="active">运营动态管理</li>
    </ol>
</div>


<div class="htm-msg">

    <div class="htm-entry">


        <div class="row">
            <div class="col-xs-6 ">

            </div>
            <div class="pull-right">
                <button type="button" class="btn btn-primary" onclick="createAdminPush()">发布动态</button>&nbsp;&nbsp;

            </div>
        </div>

    </div>

    <div class="htm-table">
        <table class="table text-center table-bordered table-striped" id="htmtable">
            <tr>
                <td>ID</td>
                <td>动态标题</td>
                <td>动态内容</td>
                <td>推送时间</td>
                <td>是否已发送</td>
                <td>操作</td>
            </tr>
            <c:forEach items="${listAdminNews}" var="listAdminNews">
                <tr>
                    <td>
                            ${listAdminNews.id}
                    </td>
                    <td>
                            ${listAdminNews.title}

                    </td>

                    <td>
                            ${listAdminNews.content}

                    </td>
                    <td>
                            ${listAdminNews.create_time}

                    </td>

                    <td>
                        <c:if test="${listAdminNews.status=='0'}">编辑</c:if>
                        <c:if test="${listAdminNews.status=='1'}">已发布</c:if>
                        <c:if test="${listAdminNews.status=='2'}">已删除</c:if>
                    </td>
                    <td>
                        <c:if test="${listAdminNews.status=='0'}">
                            <button type="button" class="btn btn-primary" onclick="updatenews(${listAdminNews.id})">编辑</button>
                            <button type="button" class="btn btn-primary" onclick="pushnews(${listAdminNews.id})">发布</button>
                            <button type="button" class="btn btn-primary" onclick="deletenews(${listAdminNews.id})">删除</button>

                        </c:if>
                        <c:if test="${listAdminNews.status=='1'}">

                            <button type="button" class="btn btn-primary" onclick="deletenews(${listAdminNews.id})">删除</button>

                        </c:if>
                        <c:if test="${listAdminNews.status=='2'}">

                            <button type="button" class="btn btn-primary" onclick="updatenews(${listAdminNews.id})">编辑</button>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>

    </div>


</div>

<script type="text/javascript">

    function createAdminPush() {
        window.location.href = "createaddAdminNews";
    }


    function updatenews(id) {
        window.location.href = "updateAdminNews?id="+id;
    }


    function pushnews(id) {
        if (window.confirm("确定要发布动态吗？")) {
            $.ajax({
                type: "post",
                data: {
                    'id' : id,
                    'status':1
                },
                url: "updateAdminNewStatus",
                success: function (data) {
                    if (data.error == 0) {
                        alert('发布完成');
                        window.location.href = "operationmanagelist";
                        return;
                    }
                },
                complete: function () {
                    alert('发布完成');
                },
                error: function (data) {
                    alert('发布失败');
                    console.info("error: " + data.responseText);
                }
            })




        }
    }


    function deletenews(id) {
        if (window.confirm("确定要删除动态吗？")) {
            $.ajax({
                type: "post",
                data: {
                    'id': id,
                    'status': 2
                },
                url: "updateAdminNewStatus",
                success: function (data) {
                    if (data.error == 0) {
                        alert('删除完成');
                        window.location.href = "operationmanagelist";
                        return;
                    }
                },
                complete: function () {
                    alert('删除完成');
                },
                error: function (data) {
                    alert('删除失败');
                    console.info("error: " + data.responseText);
                }
            })
        }
    }

</script>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="www/js/fontchange.js"></script>
</body>
</html>