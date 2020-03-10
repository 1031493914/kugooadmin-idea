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
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="edge">
    <link rel="stylesheet" href="component/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="www/css/index.css">
    <link rel="stylesheet" href="lib/jedate/jedate.css">
    <link rel="stylesheet" href="css/pagelist.css">
    <title>管理后台</title>
</head>
<body class="phonemsg">
    <!-- <div class="htm-entry">
        <form action="websiteMsg" id="myform" method="post" class="form-inline">
            <div class="row">
                <div class=" col-xs-4  form-group">
                    <label>消息标题</label>
                    <input type="text" name="title" value="${param.title}" class="form-control">
                </div>
                <div class=" col-xs-6 form-group">
                    <label>发送时间</label>
					<input class="form-control datainp wicon" id="createtime1"
                           type="text" name='createtime1' placeholder="开始日期"
                           value="${param.createtime1}"> - <input
                        class="form-control datainp wicon" id="createtime2" type="text"
                        name='createtime2' placeholder="结束日期" value="${param.createtime2}">
                </div>
                <div class="pull-right">
                    <button type="submit" class="btn btn-primary">查询</button>
                    <button class="btn btn-danger" onclick="$('#myform input').val('');return false;">重置</button>&nbsp;
                </div>
            </div>
        </form>
    </div> -->

    <div class="htm-operation row">
        <div class="pull-right">
            <a href="getusermessage" class="btn btn-success">发送消息</a>
        </div>
    </div>

    <div class="htm-table">
        <table class="table text-center table-bordered table-striped">
            <tr>
                <td>消息标题</td>
                <td>消息内容</td>
                <td>发送时间</td>
            </tr>
            <c:forEach var="userMessageList" items="${userMessageList}">
            <tr>
                <td>${userMessageList.title}</td>
                <td>${userMessageList.message}</td>
                <td>
                <fmt:formatDate value="${userMessageList.createtime}"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
           
            </tr>
            </c:forEach>
        </table>
    </div>
    <div class="pagelist text-center">
				<f:page totalSize="${page.totalSize }" url="websiteMsg" totalPage="${page.totalPage }" currentPage="${page.currentPage }" />
				&nbsp;&nbsp;&nbsp;&nbsp;跳转到第<input class='form-control' type="text" style="width:auto;display:inline-block" size="2em">页&nbsp;&nbsp;
				<a href="orderManage?cp=" onclick="$(this)[0].href=$(this)[0].href+$(this).prev().val();" >确定</a>
</div>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="www/js/fontchange.js"></script>
<script type="text/javascript" src="js/page_post.js"></script>
<script src="lib/jedate/jquery.jedate.min.js"></script>
<script src='js/datetime.js'></script>
</body>
</html>