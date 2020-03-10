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
    <!-- 引入 ECharts 文件 -->
    <script src="echarts/echarts.js"></script>
    <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
    <title>后台管理系统</title>
</head>
<body class="goodscheap">
<div class="htm-entry">

</div>

<div class="htm-table">
    <table class="table text-center table-bordered table-striped">
        <tr>
            <td>ID</td>
            <td>手机厂商</td>
            <td>手机型号</td>
            <td>手机系统</td>
            <td>IMEI</td>
            <td>设备厂商代码</td>
            <td>创建时间</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${deviceList}" var="deviceList">
            <tr>
                <td>
                    <div id="divid">${deviceList.id}</div>
                </td>

                <td>
                    <p>${deviceList.phonecompany}</p>
                </td>
                <td>
                    <p>${deviceList.phonetype}</p>
                </td>
                <td>
                    <p>${deviceList.phonesystem}</p>
                </td>
                <td>
                    <p>${deviceList.imei}</p>
                </td>
                <td>
                    <p>${deviceList.companycode}</p>
                </td>
                <td>
                    <p>${deviceList.createtime}</p>
                </td>
                <td>
                    <button class="btn btn-default"
                            onclick="lookdetail(${deviceList.id})">查看轨迹
                    </button>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>
<div class="pagelist text-center">
    <f:page totalSize="${page.totalSize }" url="devicemanagelist"
            totalPage="${page.totalPage }" currentPage="${page.currentPage }"/>
    &nbsp;&nbsp;&nbsp;&nbsp;跳转到第<input class='form-control' type="text"
                                       style="width: auto; display: inline-block" size="2em">页&nbsp;&nbsp;
    <a href="devicemanagelist?cp="
       onclick="$(this)[0].href=$(this)[0].href+$(this).prev().val();">确定</a>
</div>
<script type="text/javascript" src="js/jquery-1.8.1.min.js"></script>
<script src="www/js/fontchange.js"></script>
<script type="text/javascript">
    function lookdetail(i) {
        //alert("即将开放")
        window.location.href = "mapinfo?userid=" + i;
    }


    function delteuser(userid) {
        if (window.confirm("确定删除嘛？")) {
            $.post("deleteUser", {
                'id': userid
            }, function (data) {
                if (data) {
                    alert("处理成功");
                    window.location.href = "usermanagelist";
                    return;
                } else {
                    alert("处理失败");
                }
            });// 后台上架处理
        }
    }
</script>
</body>
</html>