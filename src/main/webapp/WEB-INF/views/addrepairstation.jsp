<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="f" uri="/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="edge">
<link rel="stylesheet"
	href="component/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="www/css/index.css">
<link rel="stylesheet" href="css/pintuer.css">
<title>添加报修点</title>
<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
<script src="lib/swfupload/swfupload.js"></script>
<script src="lib/swfupload/fileupload.js"></script>
<script src="js/global.js"></script>
</head>
<body class="addMagazine">
	<div class="htm-bread">
		<ol class="breadcrumb">
			<li>维修店管理</li>
			<li>新增维修店</li>
			<li class="active">增加内容</li>
		</ol>
	</div>
	<form action="addRepairStationSubmit" method="post">
		<div class="htm-msg">
			<div class="msg-title row">
				<div class="pull-left">增加内容</div>
			</div>
			<div class="form-group row">
				<input type="hidden" name="repairstationid" value="${magazine.id }">
				<div class="col-xs-3 text-center">
					<label for="">报修点名称</label>
				</div>
				<div class="col-xs-4 text-left">
					<input type="text" class="form-control" value="${magazine.title }"
						name="name" data-validate="required:请输入名称">
				</div>
			</div>
			<div class="form-group row">
				<div class="col-xs-3 text-center">
					<label for="">报修点地址</label>
				</div>
				<div class="col-xs-4 text-left">
					<input type="text" class="form-control" value="${magazine.author }"
						name="address" data-validate="required:请输入作者" />
				</div>
			</div>
			<div class="form-group row">
				<div class="col-xs-3 text-center">
					<label for="">报修点图片</label>
					<p>图片尺寸要求:152px*98px</p>
				</div>
				<div class="col-xs-4 text-left">
					<input id="imgInput" type="button" value="点击上传" class="btn"
						name="bigPicture">
					<div class="field">
						<div class="button badge-corner" style="padding: 0px">
							<input type="hidden" name="img" value="${magazine.img }">
							<img alt="报修点图片" src="${imageServerUrl}${magazine.img }"
								id="imgss0"> <span class="badge bg-red-light"> <span
								class="close"></span>
							</span>
						</div>
					</div>
				</div>
			</div>

			<div class="form-group row">
				<div class="col-xs-3 text-center">
					<label for="">报修点经度</label>
				</div>
				<div class="col-xs-4 text-left">
					<input type="text" class="form-control" value="${magazine.author }"
						name="lng" data-validate="required:报修点经度" />
				</div>
			</div>
			<div class="form-group row">
				<div class="col-xs-3 text-center">
					<label for="">报修点纬度</label>
				</div>
				<div class="col-xs-4 text-left">
					<input type="text" class="form-control" value="${magazine.author }"
						name="lat" data-validate="required:报修点纬度" />
				</div>
			</div>

			<div class="form-group row">
				<div class="col-xs-3 text-center">
					<label for="">营业时间</label>
				</div>
				<div class="col-xs-4 text-left">
					<input type="text" class="form-control" value="${magazine.author }"
						name="buisnesstime" data-validate="required:请输营业时间" />
				</div>
			</div>


			<div class="form-group row">
				<div class="col-xs-3 text-center">
					<label for="">报修点邮箱</label>
				</div>
				<div class="col-xs-4 text-left">
					<input type="text" class="form-control" value="${magazine.author }"
						name="email" data-validate="required:请输入邮箱" />
				</div>
			</div>

			<div class="form-group row">
				<div class="col-xs-3 text-center">
					<label for="">联系人名称</label>
				</div>
				<div class="col-xs-4 text-left">
					<input type="text" class="form-control" value="${magazine.author }"
						name="connectname" data-validate="required:请输入联系人名称" />
				</div>
			</div>

			<div class="form-group row">
				<div class="col-xs-3 text-center">
					<label for="">联系人电话</label>
				</div>
				<div class="col-xs-4 text-left">
					<input type="text" class="form-control" value="${magazine.author }"
						name="connectmobile" data-validate="required:请输入联系人电话" />
				</div>
			</div>

				<div class="form-group row">
				<div class="col-xs-3 text-center">
					<label for="">详情页图片</label>
					<p>图片尺寸要求:640px*622px</p>
				</div>
				<div class="col-xs-4 text-left">
					<input id="img" type="button" value="点击上传" class="btn"> <br>
					
					<div class="button badge-corner" style="padding:0px;margin-right: 8px">
						<input type="hidden" name="romImgWidth" value="${romImgs[0].width}">
						<input type="hidden" name="romImgHeight" value="${romImgs[0].height}">
						<input type="hidden" name="romImg" value="${fn:length(romImgs) ge 0?romImgs[0].img:''}" index="0"> 
						<img alt="大图1" src="${imageServerUrl}${fn:length(romImgs) ge 0?romImgs[0].img:''}" id="imgss10" >
						<span class="badge bg-red-light"><span class="close"></span></span>
					</div>
					<div class="button badge-corner" style="padding:0px;margin-right: 8px">
						<input type="hidden" name="romImgWidth" value="${romImgs[1].width}">
						<input type="hidden" name="romImgHeight" value="${romImgs[1].height}">
						<input type="hidden" name="romImg" value="${fn:length(romImgs) ge 1?romImgs[1].img:''}" index="1"> 
						<img alt="大图2" src="${imageServerUrl}${fn:length(romImgs) ge 1?romImgs[1].img:''}" id="imgss11" > 
						<span class="badge bg-red-light"><span class="close"></span></span>
					</div>
					<div class="button badge-corner" style="padding:0px;margin-right: 8px">
						<input type="hidden" name="romImgWidth" value="${romImgs[2].width}">
						<input type="hidden" name="romImgHeight" value="${romImgs[2].height}">
						<input type="hidden" name="romImg" value="${fn:length(romImgs) ge 2?romImgs[2].img:''}" index="2">
						<img alt="大图3" src="${imageServerUrl}${fn:length(romImgs) ge 2?romImgs[2].img:''}" id="imgss12" >
						<span class="badge bg-red-light"><span class="close"></span></span>
					</div>
				</div>
			</div>
		</div>
		<div class="finish-btn row bottom-button">
			<div class="col-xs-4 col-sm-4 col-md-5 col-lg-5"></div>
			<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
				<button class="btn btn-primary btn-block" type="submit">完成</button>
			</div>
		</div>
		<br> <br> <br> <br> <br>
	</form>

	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="www/js/fontchange.js"></script>
	<script src="www/js/change.js"></script>
	<script type="text/javascript">
	
	// 杂志图片flash上传
	$(function() {
		new FileUpload({
			button_placeholder_id : "imgInput",
			post_params : {
				width : 152,
				height : 98
			},
			onSuccess : function(file, data) {
				if (data.error != 0) {
					alert(data.msg);
					return;
				}
				alert("data.path---"+data.path);
				$('input[name="img"]').val(data.path);
				$('#imgss0').attr('src', imageServerUrl + data.path);
				$('#imgss0').next('.bg-red-light').removeClass("hidden");
			}
		});
		$('[name="bigPictureimg"]').parent().find(".bg-red-light").each(function() {
			$(this).click(function() {
				$(this).prev("img").attr('src', imageServerUrl + defaultImage);
				$(this).prev("img").prev().val("");
			});
		});

		

		$(".bg-red-light").each(function() {
			$(this).click(function() {
				$(this).addClass("hidden");
			});
		});
		
		
		
		//商品轮播页图片
		new FileUpload({
			button_placeholder_id : "img",
			post_params: {width : 640,height :622},
			onStart: function(filename){
			},
			onSuccess: function(file,data){
				if(data.error != 0){
					alert(data.msg);
					return;
				}
				$('input[name="romImg"][value=""]').each(function (i) {
					if(i==0){
						$(this).val(data.path);
						$(this).next("img").attr('src',imageServerUrl+data.path);
						$(this).prev().val(data.height);
						$(this).prev().prev().val(data.width);
						$(this).next().next().removeClass("hidden");
					}
				});
			}
		});
		$('[name="romImg"]').parent().parent().find(".bg-red-light").each(function () {
			$(this).click(function () {
				$(this).prev("img").attr('src',imageServerUrl+defaultImage);
				$(this).prev("img").prev().val("");
				$(this).prev("img").prev().prev().val("");
				$(this).prev("img").prev().prev().prev().val("");
			});
		});
	})
	
	
	
	
	
	</script>
</body>
</html>