<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="/tags"%>
<% 
	String path = request.getContextPath(); 
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-cn">

<head>
<base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <meta name="keywords" content="后台登录" />
    <title>电动车管理后台系统</title>
    <link href='css/login/style.css' rel="stylesheet" type="text/css" media="all">
    <script src="js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript">
    if (window != top){
    	top.location.href = location.href;
    }  
   
	var isSubmited = false;
	function check(){
		if(isSubmited) return false;//防止重复提交
		isSubmited = true;
		return true; 
	}
   
    </script>
   
</head>
<body>
 <%
    if (session.getAttribute("sessionAdminUser") != null) {
		
		response.sendRedirect("home");
	}
 %>


<div class="login-form">
		
			<h1>电动车管理后台系统</h1>
			<div class="login-top">
			<form  action="loginSubmit" method="post">
				<div class="login-ic">
					<i ></i>
					<input type="text"  name="loginname" value="" placeholder="登录账号" data-validate="required:请填写账号" />
					<div class="clear"> </div>
					<span id="spanname" ></span>
				</div>
				<div class="login-ic">
					<i class="icon"></i>
					<input type="password"  name="loginpwd" value="" placeholder="登录密码" data-validate="required:请填写密码,"/>
					<div class="clear"> </div>
					<span id="spanpwd" ></span>
				</div>
				<div class="login-ic login-imgpass">
					<i class="icon"></i>
					<input type="text"  name="logincode" placeholder="填写右侧的验证码" data-validate="required:请填写右侧的验证码"  onfocus="$(this).parent().find('label').remove();" />
					 <img width="100"
										height="32" class="passcode"
										style="height: 36px; cursor: pointer; margin-left:2%" id="captchaImage"
										src="CaptchaUtil" onclick="this.src=this.src+'?'" />
					<div class="clear"> </div>
					 <c:if test="${not empty errorMsg }">
                                        <label class="" style="color:red" >${errorMsg}</label>
                                </c:if>
				</div>
				<div class="log-bwn">
					<input type="submit"  value="登陆" onclick="$('[name=logincode]').parent().find('label').remove();" >
				</div>
				</form>
			</div>
			<p class="copy"></p>
</div>	





</body>
</html>