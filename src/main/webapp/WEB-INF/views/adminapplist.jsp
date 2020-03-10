<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="f" uri="/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="renderer" content="webkit">
    <link rel="stylesheet"
          href="component/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="www/css/index.css">
    <link rel="stylesheet" href="css/pagelist.css">
    <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
    <script src="lib/swfupload/swfupload.js"></script>
    <script src="lib/swfupload/fileupload.js"></script>
    <script src="js/global.js"></script>
    <title>应用管理</title>
</head>
<body class="goodscheap">
<div class="htm-entry">
    <form action="searchAppList" method="post" class="form-inline">
        <div class="entry-line1 row">
            <div class="form-group col-xs-4">
                <label class="control-label">应用名称</label> <input type="text"
                                                                 name="appname" class="form-control"/> <input
                    type="submit"
                    class="btn btn-primary" value="查询"/>
            </div>
        </div>
    </form>

</div>
<div class="htm-operation row">
    <div class="pull-left">
    </div>
</div>
<div class="htm-table">
    <table class="table text-center table-bordered table-striped">
        <tr>
            <td>ID</td>
            <td>应用名称</td>
            <td>应用APPKEY</td>
            <td>应用包名</td>
            <td>状态</td>
            <td>用户名称</td>
            <td>创建时间</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${listSdkApp}" var="listSdkApp">
            <tr>
                <td>
                    <div id="divid">${listSdkApp.id}</div>
                </td>

                <td>
                    <p>${listSdkApp.appname}</p>
                </td>
                <td>
                    <p>${listSdkApp.appkey}</p>
                </td>
                <td>
                    <p>${listSdkApp.packagename}</p>
                </td>
                <td>
                    <p>
                        <c:if test="${listSdkApp.status==0}">待审核</c:if>
                        <c:if test="${listSdkApp.status==1}">审核通过</c:if>
                        <c:if test="${listSdkApp.status==2}">审核不通过</c:if>
                        <c:if test="${listSdkApp.status==3}">封禁</c:if>
                    </p>
                </td>
                <td>
                    <p>${listSdkApp.userid}</p>
                </td>

                <td>
                    <p>${listSdkApp.createtime}</p>
                </td>

                <td>
                    <c:if test="${listSdkApp.status==0}">
                        <button class="btn btn-default"
                                onclick="operatorapp(${listSdkApp.id},1)">通过
                        </button>
                        <button class="btn btn-default"
                                onclick="operatorapp(${listSdkApp.id},2)">不通过
                        </button>
                    </c:if>
                    <c:if test="${listSdkApp.status==1}">
                        <button class="btn btn-default"
                                onclick="operatorapp(${listSdkApp.id},3)">封禁
                        </button>

                    </c:if>
                    <c:if test="${listSdkApp.status==3}">
                        <button class="btn btn-default"
                                onclick="operatorapp(${listSdkApp.id},1)">启用
                        </button>

                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>
<!--<div class="pagelist text-center">
    <f:page totalSize="${page.totalSize }" url="shopsManage"
            totalPage="${page.totalPage }" currentPage="${page.currentPage }"/>
    &nbsp;&nbsp;&nbsp;&nbsp;跳转到第<input class='form-control' type="text"
                                       style="width: auto; display: inline-block" size="2em">页&nbsp;&nbsp;
    <a href="shopsManage?cp="
       onclick="$(this)[0].href=$(this)[0].href+$(this).prev().val();">确定</a>
</div>-->
<script type="text/javascript" src="js/jquery-1.8.1.min.js"></script>
<script src="www/js/fontchange.js"></script>
<script type="text/javascript">
    function operatorapp(appid,status){


        if (window.confirm("确定操作嘛？")) {
            $.post("updateAppStatus", {
                'id' : appid,
                'status':status
            }, function(data) {
                if (data) {
                    alert("处理成功");
                    window.location.href = "adminapplist";
                    return;
                }else{
                    alert("处理失败");
                }
            });// 后台上架处理
        }

    }

</script>
</body>
</html>