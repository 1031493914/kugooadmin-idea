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
    <meta http-equiv="X-UA-Compatible" content="edge">
    <link rel="stylesheet" href="component/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="www/css/index.css">
    <title>后台管理系统</title>
	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="component/bootstrap/dist/js/bootstrap.min.js"></script>
	<script src="www/js/fontchange.js"></script>

</head>
<body class="userInfo">
    <div class="htm-bread">
        <ol class="breadcrumb">
            <li>报修</li>
            <li>报修管理</li>
            <li class="active">报修详情 </li>
        </ol>
    </div>

    <div class="htm-msg">
		<div class="msg-title row">
			<div class="pull-left">报修信息</div>
			<div class="pull-right">
					
			</div>
		</div>
		<div class="form-group row">
            <div class="col-xs-3 text-center">
                <label >报修人姓名:</label>
            </div>
            <div class="col-xs-4 text-left">
               ${rs.name}
            </div>
        </div>
        <div class="form-group row">
            <div class="col-xs-3 text-center">
                <label >报修人手机：</label>
            </div>
            <div class="col-xs-4 text-left">
              ${rs.mobile}
            </div>
        </div>
        <div class="form-group row">
            <div class="col-xs-3 text-center">
                <label >车型</label>
            </div>
            <div class="col-xs-4 text-left">
             	 ${rs.cartype}
            </div>
        </div>
        <div class="form-group row">
            <div class="col-xs-3 text-center">
                <label >报修店名</label>
            </div>
            <div class="col-xs-4 text-left">
                ${rs.repairstationname}
            </div>
        </div>
        		<div class="form-group row">
            <div class="col-xs-3 text-center">
                <label >问题描述</label>
            </div>
            <div class="col-xs-4 text-left">
               ${rs.content}
            </div>
        </div>
       		<div class="form-group row">
            <div class="col-xs-3 text-center">
                <label >问题图片</label>
            </div>
            <div class="col-xs-4 text-left">
            <c:forEach items="${listimg}" var="listimg">
                <img src="${listimg}" alt="" width="30%">
                </c:forEach>
                
            </div>
        </div>
           		<div class="form-group row">
            <div class="col-xs-3 text-center">
                <label >维修状态</label>
            </div>
            <div class="col-xs-4 text-left">
           <p><c:if test="${rs.status==1}">
						已处理</c:if><c:if test="${rs.status==0}">
						未处理</c:if></p>
            </div>
        </div>
        <div class="form-group row">
            <div class="col-xs-3 text-center">
                <label >创建时间</label>
            </div>
            <div class="col-xs-4 text-left">
               ${rs.createtime}
            </div>
        </div>
   
     
    </div>

    	<div class="finish-btn row">
    	
    			<div class="row finish-btn bottom-button">
					<div class='col-xs-3 col-sm-4 col-md-4 col-lg-4'></div>
					<div class='col-xs-6 col-sm-4 col-md-4 col-lg-4 row'>
						<div class='col-xs-5'>
							<button type="button" onclick="dealrepair(${rs.id})"
								class='btn btn-primary btn-block'>处&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;理</button>
						</div>
						<div class='col-xs-1'></div>
						<div class='col-xs-5'>
						<a href="javascript:history.back();"
				class="btn btn-primary btn-block">返 回 </a>
						</div>
					</div>

				</div>
    	
    	
	</div>
    <!--end -addoneitem-modal--->

    <script type="text/javascript">
  function dealrepair(id) {
		if (window.confirm("维修完成？")) {
			$.post("dealrepair", {
				'id' : id
			}, function(data) {
				if (data) {
					alert("处理成功");
					window.location.href = "getRepairInfo?repairid="+id;
					return;
				}else{
					alert("处理失败");
				}
			});// 后台上架处理
		}
	}

	
  </script>
   
   

    <script type="text/javascript">
    var imageServerUrl='http://101.37.69.236:8888/';
	var defaultImage='adminimg.jpg';
    $(document).ready(function() {
    	$("img[src]").each(function (i) {
    		if($(this).attr("src").trim()==''||$(this).attr("src").trim()==imageServerUrl){
    			$(this).attr("src",imageServerUrl+defaultImage);
    		};
    		$(this).attr("width","100px");
			$(this).attr("height","100px");
    	})
    });
        $(function () {
            $("#Upgrade").click(function () {
                $("#Upgrade-modal").modal({
                    backdrop : true,
                    keyboard : false
                })
            });
            $("#BuyPoints").click(function () {
                $("#BuyPoints-modal").modal({
                    backdrop : true,
                    keyboard : false
                })
            });

        })
    </script>
</body>
</html>