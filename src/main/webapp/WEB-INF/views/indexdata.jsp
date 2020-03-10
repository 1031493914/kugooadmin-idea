<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="f" uri="/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
    <script src="js/jquery-3.1.1.min.js"></script>
    <title>系统设置</title>
	<script src="js/views/message.js"></script>
    <link rel="stylesheet" href="www/src/AdminLTE.min.css" />
    <link rel="stylesheet" href="www/src/skin-green.min.css" />
    <link rel="stylesheet" href="www/css/index.css">
    <link rel="stylesheet" href="www/layui/css/layui.css">
    <script src="js/jquery-3.1.1.min.js"></script>
    <script src="component/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="www/js/app.js"></script>
    <script src="www/js/index.js"></script>
    <link rel="stylesheet" href="www/font-face/css/font-awesome.min.css" />
    <link rel="stylesheet"
          href="component/bootstrap/dist/css/bootstrap.min.css" />
    <link rel="stylesheet" href="www/layui/css/modules/layer/default/layer.css">

</head>
<body class="sendinstationmsg">
    <div class="htm-bread">
        <ol class="breadcrumb">
            <li>首页管理</li>
            <li>数据概览</li>
            <li class="active">数据概览</li>
        </ol>
    </div>

    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>数据概览</legend>
    </fieldset>

    <div style="padding: 20px; background-color: #F2F2F2;">
        <div class="layui-row layui-col-space15">
            <div class="layui-col-md12">

                <div class="layui-card">
                    <div class="layui-card-header" style="border-left: 2px solid #ed5150">会员数据</div>
                    <div class="layui-card-body" style="padding:0;">
                        <div class="layui-row layui-col-space5">
                            <li class="layui-col-xs4" style="border-right:1px solid #cccbcb">
                                <div   style="text-align:center;padding:30px 0;">
                                    <p> 当日</p>
                                    <h2>${daynnum}</h2>
                                </div>
                            </li>
                            <li class="layui-col-xs4" style="border-right:1px solid #cccbcb">
                                <div   style="text-align:center;padding:30px 0;">
                                    <p> 当周</p>
                                    <h2>${weeknum}</h2>
                                </div>
                            </li>
                            <li class="layui-col-xs4">
                                <div   style="text-align:center;padding:30px 0;">
                                    <p> 当月</p>
                                    <h2>${monthnum}</h2>
                                </div>
                            </li>
                        </div>
                    </div>
                </div>


            </div>
            <div class="layui-col-md6">
                <div class="layui-card">
                    <div class="layui-card-header">总设备量</div>
                    <div class="layui-card-body">

                        <div class="layui-row layui-col-space5">
                            <li class="layui-col-xs6" style="border-right:1px solid #cccbcb">
                                <div   style="text-align:center;padding:30px 0;">
                                    <p> APP下载量</p>
                                    <h2>${allappnum}</h2>
                                </div>
                            </li>
                            <li class="layui-col-xs6" >
                                <div   style="text-align:center;padding:30px 0;">
                                    <p>  设备数</p>
                                    <h2>${allnum}</h2>
                                </div>
                            </li>

                        </div>


                    </div>
                </div>
            </div>
            <div class="layui-col-md6">
                <div class="layui-card">
                    <div class="layui-card-header">当前故障设备</div>
                    <div class="layui-card-body">
                        <div style="text-align:center;padding:30px 0;">
                            设备数<br>
                            <h2> ${breakdownnum}</h2>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>