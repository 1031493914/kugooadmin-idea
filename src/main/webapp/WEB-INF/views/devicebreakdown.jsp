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
        <li class="active">设备故障管理</li>
    </ol>
</div>


<div class="htm-msg">

    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="main" style="height:600px;"></div>

    <div class="htm-table" >
        <table class="table text-center table-bordered table-striped" id="htmtable">
            <tr>
                <td>ID</td>
                <td>故障数量</td>
                <td>故障名称</td>

            </tr>
            <c:forEach items="${listDeviceBreakDown}" var="listDeviceBreakDown">
                <tr>
                    <td>
                            ${listDeviceBreakDown.id}
                    </td>

                    <td>
                            ${listDeviceBreakDown.num}
                    </td>
                    <td>
                            ${listDeviceBreakDown.content}

                    </td>

                </tr>
            </c:forEach>
        </table>

    </div>



</div>

<script type="text/javascript">


    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
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

    // 指定图表的配置项和数据
    var option = {

        title: {
            text: '设备故障列表(数量)'
        },
        tooltip: {},
        legend: {
            data:['数量']
        },
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
        yAxis: {},
        series: [{
            name: '故障量',
            type: 'bar',
            data: date1
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="www/js/fontchange.js"></script>
</body>
</html>