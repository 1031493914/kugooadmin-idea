<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="f" uri="/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<link rel="stylesheet"
	href="component/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="www/css/index.css">
<link rel="stylesheet" href="css/pagelist.css">
<title>后台管理系统</title>
</head>
<body class="goodscheap">
	<div class="htm-entry">
		<form action="searchDeviceList"  id="myform" method="post" class="form-inline">
           <!-- <div class="entry-line1 row">
                <div class="form-group  col-xs-4">
                    <label for="tradename" class="control-label">会员姓名</label>
                    <input type="text" name=name id="name" value="${name}"  class="form-control" />
                </div>
                <div class="form-group  col-xs-4">
                    <label class="control-label">会员编号</label>
                    <input type="text"  name="usercode"  id="usercode" value="${usercode}"  class="form-control" />
                </div>
				<div class="form-group col-xs-4">
					<label class="control-label">会员手机号</label>
					<input type="text"  name="mobile"  id="mobile" value="${mobile}"  class="form-control" />
                
				</div>
			</div> --> 

            <div class="row">
				<!-- <div class="form-group col-xs-4">
					<label class="control-label">身份证号</label>
					 <input type="text"  name="usernumber"  id="usernumber" value="${usernumber}"  class="form-control" />
                
				</div> -->
				<div class="col-xs-6 ">
                    <label class="control-label">时间范围</label> 

                    <input class="form-control datainp wicon" id="createtime1"
                           type="text" name='createtime1' placeholder="开始日期"
                           value="${createtime1}"> - <input
                        class="form-control datainp wicon" id="createtime2" type="text"
                        name='createtime2' placeholder="结束日期" value="${createtime2}">

                </div>
                <div class="pull-right">
                    <button type="submit" class="btn btn-primary" >查询</button>&nbsp;&nbsp;
                    <button type="button" class=" btn btn-danger" onclick="$('input').val('');$('select').val('all');return false;">重置</button>
                </div>
            </div>
            
        </form>

	</div>
	<!-- <div class="htm-operation row">
        		<div class="pull-right">
            		<a onclick="orderexportExcel()" class="btn btn-success"id="save">会员导出</a>
        			<a href="adduser" class="btn btn-success"id="save">新建会员</a>
        		</div>
    </div> -->
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
							onclick="lookdetail(${deviceList.id})">查看轨迹</button>
					</td>
				</tr>
			</c:forEach>
		</table>

	</div>
	<div class="pagelist text-center">
		<f:page totalSize="${page.totalSize }" url="devicemanagelist"
			totalPage="${page.totalPage }" currentPage="${page.currentPage }" />
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
	  window.location.href = "mapinfo?userid="+i;
  }
  
  
  function delteuser(userid){
	  if (window.confirm("确定删除嘛？")) {
			$.post("deleteUser", {
				'id' : userid
			}, function(data) {
				if (data) {
					alert("处理成功");
					window.location.href = "usermanagelist";
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