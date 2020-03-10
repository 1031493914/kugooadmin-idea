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
    <form action="searchOutNumList" method="post" class="form-inline">
        <div class="entry-line1 row">
            <div class="form-group col-xs-4">
                <label class="control-label">厂商名称</label>
                <select name="companycode"
                        id="companycode" class="form-control">
                    <c:forEach items="${devicecompanyList}" var="devicecompanyList">
                        <!--设备厂商代码0公版  1高德 2永泰 3驴电 4酷格  5本田-->
                        <option value="${devicecompanyList.code}">${devicecompanyList.name}</option>
                    </c:forEach>

                </select>

                <label class="control-label">批次</label>

                <select name="devicetype"
                        id="devicetype" class="form-control">
                    <c:forEach items="${devicetypeList}" var="devicetypeList">
                        <!--设备厂商代码0公版  1高德 2永泰 3驴电 4酷格  5本田-->
                        <option value="${devicetypeList.id}">${devicetypeList.pici}</option>
                    </c:forEach>
                </select>


                <input
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
            <td>厂商名称</td>
            <td>厂商代码</td>
            <td>批次</td>
            <td>出货量</td>
        </tr>
        <c:forEach items="${outnumdevicecompanyList}" var="outnumdevicecompanyList">
            <tr>
                <td>
                    <div id="divid">${outnumdevicecompanyList.id}</div>
                </td>

                <td>
                    <p>${outnumdevicecompanyList.name}</p>
                </td>
                <td>
                    <p>${outnumdevicecompanyList.code}</p>
                </td>
                <td>
                    <p>${outnumdevicecompanyList.pici}</p>
                </td>
                <td>
                    <p>${outnumdevicecompanyList.outnum}</p>
                </td>

            </tr>
        </c:forEach>
    </table>

</div>

<script type="text/javascript" src="js/jquery-1.8.1.min.js"></script>
<script src="www/js/fontchange.js"></script>

</body>
</html>