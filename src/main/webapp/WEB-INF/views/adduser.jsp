<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="f" uri="/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="edge">
<link rel="stylesheet"
	href="component/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="www/css/index.css">
<title>后台管理系统</title>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="component/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="www/js/fontchange.js"></script>
<script src="lib/swfupload/swfupload.js"></script>
<script src="lib/swfupload/fileupload.js"></script>
<script src="js/global.js"></script>

</head>
<body class="userInfo">
	<div class="htm-bread">
		<ol class="breadcrumb">
			<li>会员管理</li>
			<li class="active">新建会员</li>
		</ol>
	</div>


		<form action="addNewUserSubmit" method="post" id="addnewuserform"> 
		<input type="hidden" class="form-control" value="0"
						name="userid" >
		<div class="htm-msg">
			<div class="msg-title row">
				<div class="pull-left">新建会员</div>
			</div>
			<div class="form-group row">
				<div class="col-xs-3 text-center">
					<label for="">姓名</label>
				</div>
				<div class="col-xs-4 text-left">
					<input type="text" class="form-control" value=""
						name="name" data-validate="required:请输入姓名">
				</div>
			</div>
			<div class="form-group row">
				<div class="col-xs-3 text-center">
					<label for="">手机号</label>
				</div>
				<div class="col-xs-4 text-left">
					<input type="text" class="form-control" value=""
						name="mobile" data-validate="required:请输入手机号" />
				</div>
			</div>
			<div class="form-group row">
				<div class="col-xs-3 text-center">
					<label for="">密码</label>
				</div>
				<div class="col-xs-4 text-left">
					<input type="text" class="form-control" value=""
						name="pwd" data-validate="required:请输入密码" />
				</div>
			</div>

			<div class="form-group row">
				<div class="col-xs-3 text-center">
					<label for="">确认密码</label>
				</div>
				<div class="col-xs-4 text-left">
					<input type="text" class="form-control" value=""
						name="twopwd" data-validate="required:请输入确认密码" />
				</div>
			</div>
				<div class="form-group row">
				<div class="col-xs-3 text-center">
					<label for="">身份证号</label>
				</div>
				<div class="col-xs-4 text-left">
					<input type="text" class="form-control" value=""
						name="usernumber" data-validate="required:请输入身份证号" />
				</div>
			</div>

			<div class="form-group row">
				<div class="col-xs-3 text-center">
					<label for="">邮箱</label>
				</div>
				<div class="col-xs-4 text-left">
					<input type="text" class="form-control" value=""
						name="email" data-validate="required:请输营业时间" />
				</div>
			</div>


			<div class="form-group row">
				<div class="col-xs-3 text-center">
					<label for="">地址</label>
				</div>
				<div class="col-xs-4 text-left">
					<input type="text" class="form-control" value=""
						name="address" data-validate="required:请输入邮箱" />
				</div>
			</div>

			

				
		</div>
		<div class="finish-btn row bottom-button">
			<div class="col-xs-4 col-sm-4 col-md-5 col-lg-5"></div>
			<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
				<button class="btn btn-primary btn-block" type="button" id="usersubmit">完成</button>
			</div>
		</div>
		<br> <br> <br> <br> <br>
	</form>

</body>
<script type="text/javascript">


$("#usersubmit").click(function(){
		var flag=true;
		if ($('input[name="name"]').val() == "") {
			alert("姓名不能为空");
			flag= false;
		}else if ($('input[name="mobile"]').val() == "") {
			alert("手机号不能为空");
			flag= false;
		}else if ($('input[name="pwd"]').val() == "") {
			alert("密码不能为空");
			flag= false;
		}else if($('input[name="twopwd"]').val() == "") {
			alert("确认密码不能为空");
			flag= false;
		}else if($('input[name="usernumber"]').val() == "") {
			alert("身份证号不能为空");
			flag= false;
		}else if($('input[name="email"]').val() == "") {
			alert("邮箱不能为空");
			flag= false;
		}else if($('input[name="address"]').val() == "") {
			alert("地址不能为空");
			flag= false;
		}
		
		var pwd=$('input[name="pwd"]').val();
		var twopwd=$('input[name="twopwd"]').val();
	    if(pwd != twopwd){
	    	alert("两次密码不一致");
	    	flag=false;
	    }
		
		if(flag==true){
			$("#addnewuserform").submit();
		}

		
		
		
	})



</script>
</html>