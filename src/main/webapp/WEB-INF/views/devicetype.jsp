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
        <li class="active">设备分布</li>
    </ol>
</div>


<div class="htm-msg">

    <div class="htm-entry">


            <div class="row">
                <!-- <div class="form-group col-xs-4">
					<label class="control-label">身份证号</label>
					 <input type="text"  name="usernumber"  id="usernumber" value="${usernumber}"  class="form-control" />

				</div> -->
                <div class="col-xs-6 ">

                </div>
                <div class="pull-right">
                    <button type="button" class="btn btn-primary" onclick="createDeviceType()">创建型号</button>&nbsp;&nbsp;

                </div>
            </div>

    </div>

    <div class="htm-table" >
        <table class="table text-center table-bordered table-striped" id="htmtable">
            <tr>
                <td>ID</td>
                <td>型号</td>
                <td>设备厂商</td>
                <td>批次</td>
                <td>时间</td>
            </tr>
            <c:forEach items="${listDeviceType}" var="listDeviceType">
                <tr>
                    <td>
                            ${listDeviceType.id}
                    </td>
                    <td>
                            ${listDeviceType.typecode}

                    </td>
                    <td>
                            ${listDeviceType.companycode}
                    </td>

                    <td>
                            ${listDeviceType.pici}

                    </td>
                    <td>
                            ${listDeviceType.create_time}

                    </td>
                </tr>
            </c:forEach>
        </table>

    </div>



</div>

<script type="text/javascript">

    function createDeviceType(){
        window.location.href="addDeviceType"
    }
</script>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="www/js/fontchange.js"></script>
</body>
</html>