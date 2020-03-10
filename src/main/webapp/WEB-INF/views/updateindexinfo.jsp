<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="f" uri="/tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<base href="<%=basePath%>">
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="component/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="www/css/index.css">
    <link rel="stylesheet" href="css/pintuer.css">
    <title>首页信息设置</title>
    <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
    <script src="www/js/fontchange.js"></script>
    <script src="lib/swfupload/swfupload.js"></script>
    <script src="lib/swfupload/fileupload.js"></script>
	<script src="js/layer/layer.js"></script>
	<script src="js/global.js"></script>
	<script src="js/views/updateGoods.js"></script>
</head>
<body class="updategoods">
    <div class="htm-bread">
        <ol class="breadcrumb">
            <li>首页</li>
            <li><a class="active">首页信息设置</a></li>
        </ol>
    </div>
    <form action="updateIndexSubmit" id="form1" method="post">
    <div class="htm-msg">
        <div class="msg-title">首页信息设置</div>
       
  
        <div class="form-group row">

            <div class="col-xs-2 text-center">
                <label>详情页轮播图片</label>
                <p>(图片尺寸640px*622px)</p>
            </div>
            <div class="col-xs-8 text-left" id="pictureInfoImgDiv2">
            <input id="img" type="button" value="点击上传" class="btn"> <br>
					<div class="button badge-corner" style="padding:0px;margin-right: 8px">
						<input type="hidden" name="romImg" value="${fn:length(romImgs) ge 0?romImgs[0].img:''}" index="0"> 
						<img alt="大图1" src="http://47.75.168.210:8888/${fn:length(romImgs) ge 0?romImgs[0].img:''}" id="imgss10" >
						<span class="badge bg-red-light"><span class="close"></span></span>
					</div>
					<div class="button badge-corner" style="padding:0px;margin-right: 8px">
						<input type="hidden" name="romImg" value="${fn:length(romImgs) ge 1?romImgs[1].img:''}" index="1"> 
						<img alt="大图2" src="http://47.75.168.210:8888/${fn:length(romImgs) ge 1?romImgs[1].img:''}" id="imgss11" > 
						<span class="badge bg-red-light"><span class="close"></span></span>
					</div>
					<div class="button badge-corner" style="padding:0px;margin-right: 8px">
						<input type="hidden" name="romImg" value="${fn:length(romImgs) ge 2?romImgs[2].img:''}" index="2">
						<img alt="大图3" src="http://47.75.168.210:8888/${fn:length(romImgs) ge 2?romImgs[2].img:''}" id="imgss12" >
						<span class="badge bg-red-light"><span class="close"></span></span>
					</div>
					
					<div class="button badge-corner" style="padding:0px;margin-right: 8px">
						<input type="hidden" name="romImg" value="${fn:length(romImgs) ge 3?romImgs[3].img:''}" index="3">
						<img alt="大图4" src="http://47.75.168.210:8888/${fn:length(romImgs) ge 3?romImgs[3].img:''}" id="imgss13" >
						<span class="badge bg-red-light"><span class="close"></span></span>
					</div>
					
            		<div class="button badge-corner" style="padding:0px;margin-right: 8px">
						<input type="hidden" name="romImg" value="${fn:length(romImgs) ge 4?romImgs[4].img:''}" index="4">
						<img alt="大图5" src="http://47.75.168.210:8888/${fn:length(romImgs) ge 4?romImgs[4].img:''}" id="imgss14" >
						<span class="badge bg-red-light"><span class="close"></span></span>
					</div>
            </div>
        </div>
      
       
        <div class="form-group row">

            <div class="col-xs-2 text-center">
                <label>大牌联手</label>
            </div>
            <div class="col-xs-8 text-left" id="pictureInfoImgDiv3">
            
            <input id="pictureInfo" type="button" value="点击上传" class="btn">
					<br> 
					 <div class="button badge-corner" style="padding:0px;margin-right: 5px">
						<input type="hidden" name="pictureInfoRomImg" value="${fn:length(pictureInfoRomImg) ge 0?pictureInfoRomImg[0].img:''}" index="1"> 
						<img alt="大图1" id="pictureInfoImg1" src="http://47.75.168.210:8888/${fn:length(pictureInfoRomImg) ge 0?pictureInfoRomImg[0].img:''}" > 
						<span class="badge bg-red-light"><span class="close"></span></span>
					</div>
					 <div class="button badge-corner" style="padding:0px;margin-right: 5px">
						<input type="hidden" name="pictureInfoRomImg" value="${fn:length(pictureInfoRomImg) ge 1?pictureInfoRomImg[1].img:''}" index="2"> 
						<img alt="大图2" id="pictureInfoImg2" src="http://47.75.168.210:8888/${fn:length(pictureInfoRomImg) ge 1?pictureInfoRomImg[1].img:''}" > 
						<span class="badge bg-red-light"><span class="close"></span></span>
					</div>
					<div class="button badge-corner" style="padding:0px;margin-right: 5px">
						<input type="hidden" name="pictureInfoRomImg" value="${fn:length(pictureInfoRomImg) ge 2?pictureInfoRomImg[2].img:''}" index="3"> 
						<img alt="大图3" id="pictureInfoImg3" src="http://47.75.168.210:8888/${fn:length(pictureInfoRomImg) ge 2?pictureInfoRomImg[2].img:''}" > 
						<span class="badge bg-red-light"><span class="close"></span></span>
					</div>
					<div class="button badge-corner" style="padding:0px;margin-right: 5px">
						<input type="hidden" name="pictureInfoRomImg" value="${fn:length(pictureInfoRomImg) ge 3?pictureInfoRomImg[3].img:''}" index="4"> 
						<img alt="大图4" id="pictureInfoImg4" src="http://47.75.168.210:8888/${fn:length(pictureInfoRomImg) ge 3?pictureInfoRomImg[3].img:''}" > 
						<span class="badge bg-red-light"><span class="close"></span></span>
					</div>
					<div class="button badge-corner" style="padding:0px;margin-right: 5px">
						<input type="hidden" name="pictureInfoRomImg" value="${fn:length(pictureInfoRomImg) ge 4?pictureInfoRomImg[4].img:''}" index="5"> 
						<img alt="大图5" id="pictureInfoImg5" src="http://47.75.168.210:8888/${fn:length(pictureInfoRomImg) ge 4?pictureInfoRomImg[4].img:''}" >
						<span class="badge bg-red-light"><span class="close"></span></span>
					</div>
      
            </div>
        </div>
    </div>

		<div class="htm-msg">
				<div class="htm-msg">
					<div class="msg-title">关于我们</div>
					<div class="row form-group">
						<div class="col-xs-2 text-center">
							<label>关于我们</label>
						</div>
						<div class="col-xs-4 text-left">
							<textarea class="form-control" style="width: 515px;height: 100px;" placeholder="请输入关于我们信息" name="about" >${indexsetting.about }</textarea>
						</div>
					</div>
					<div class="row form-group">
						<div class="col-xs-2 text-center">
							<label>商家合作加盟</label>
						</div>
						<div class="col-xs-4 text-left">
							<textarea class="form-control" style="width: 515px;height: 100px;" placeholder="请输入商家加盟信息" name="info">${indexsetting.info }</textarea>						
						</div>
					</div>
				</div>
		</div>
		<div class="text-center  bottom-button row">
        <div class="col-xs-4 col-sm-4 col-md-5 col-lg-5"></div>
        <div class="col-xs-4 col-sm-4 col-md-2 col-lg-2">
            <button class="btn btn-primary btn-block" type="submit" onclick="return validateSubmit();">完成</button>
        </div>
    </div>
		<br><br><br><br><br>
	</form>
	<script src="www/js/change.js"></script>
</body>
</html>