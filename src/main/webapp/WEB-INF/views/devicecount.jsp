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

    <title>系统分布</title>
    <!-- 引入 ECharts 文件 -->
    <script src="echarts/echarts.js"></script>
    <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
</head>
<body class="sendinstationmsg">
<div class="htm-bread">
    <ol class="breadcrumb">
        <li>系统分布</li>
        <li>设备管理</li>
        <li class="active">设备注册数据总览</li>
    </ol>
</div>


<div class="htm-msg">

    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="main" style="height:600px;"></div>

    <div class="htm-table" >
        <table class="table text-center table-bordered table-striped" id="htmtable">
            <tr>
                <td>ID</td>
                <td>设备数量</td>
                <td>日期</td>

            </tr>
            <c:forEach items="${deviceList}" var="deviceList">
                <tr>
                    <td>
                            ${deviceList.id}
                    </td>

                    <td>
                            ${deviceList.num}
                    </td>
                    <td>
                            ${deviceList.strdatetime}


                    </td>

                </tr>
            </c:forEach>
        </table>

    </div>



</div>



<script type="text/javascript">
    var dom = document.getElementById("main");
    var myChart = echarts.init(dom);

    var date1 = new Array();
    var date2 = new Array();
    var tb=document.getElementById('htmtable');
    var rows=tb.rows;
    for(var i=1;i<rows.length;i++){
        var cells=rows[i].cells;
        for(var j=1;j<cells.length;j++){
            if(j==1){
                date1[i-1]=tb.rows[i].cells[j].innerHTML
            }
            if(j==2){
                date2[i-1]=tb.rows[i].cells[j].innerHTML
            }
        }
    }


    var app = {};
    option = null;
    option = {
        xAxis: {
            type: 'category',
            data: date2,
            axisLabel:{     //加上这个强制显示
                interval: 0,
                rotate: 45,     //文字逆时针旋转45°
                textStyle: {    //文字样式
                    color: "black",
                    fontSize: 12,
                    fontFamily: 'Microsoft YaHei'
                }
            },
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            name: '销量',
            data: date1,
            type: 'line'
        }]
    };
    ;
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
</script>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="www/js/fontchange.js"></script>
</body>
</html>