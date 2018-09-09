<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html lang="zh-cn">
<% String path = request.getContextPath();%>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>
	<title>登录</title>
	<link rel="stylesheet" href="../css/login.css">
	<link rel="stylesheet" href="../css/index.css">
	<script src="../js/particles.js"></script>
	<script src="../js/app.js"></script>
</head>
<body onload="init()">
	<div id="particles"></div>
	<div class="form">		
	<form name="loginForm" validate="validate" action="<%=path %>/loginServlet" method="post">	
			<table class="main-container">
				<tr>
					<td colspan="2">
						<h1 class="logo-big">知乎</h1>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<h2 class="subtitle">与世界分享你的知识、经验和见解</h2>
					</td>
				</tr>
				
				<tr>
					<td colspan="2">
						<div class="group-inputs">
							<input type="text" class="input-wrapper" id="username" placeholder="手机号或邮箱" required="required" autofocus>
							<input type="password" class="input-wrapper border" id="password" placeholder="密码" required="required">
							<input type="text" class="input-wrapper border no-border-b" id="captcha" placeholder="验证码" required="required">
							<!-- <img src="../images/captcha.gif" alt="验证码" class="captcha" width="100" height="38"> -->
							<img src="../codeServlet" alt="验证码" class="captcha" width="100" height="38" onclick="javascript:this.src='../codeServlet?'+ new Date().getTime()">
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<!-- <button class="sign" onclick="login()">登录</button> -->
						<input class="sign" type="submit" value="登录"></input>
					</td>
				</tr>
				<tr>
					<td>
						<a href="register.html"  class="nav-slider right-side">注册</a>
					</td>
					<td>
						<a href="#" class="nav-slider left-side" onclick="updatePassword()">修改密码</a>
					</td>
				</tr>
			</table>
		<footer>
			<span>© 2017 知乎</span>
		</footer>
		</form>
	</div>
	<div class="cover" id="cover"  onclick="hide()"></div>
	<div class="model-container">
		<div class="model">
			<div class="model-header">
				<h3>修改密码</h3>
			</div>
			<div class="model-body">
				<form action="" name="updatePasswordForm">
					<div class="group-inputs">
						<div class="form-group">
							<div class="col-3 text-r">
								<label for="account" class="mr-10">账号：</label>
							</div>
							<div class="col-6">
								<input type="text" class="form-console" id="account" placeholder="账号" required="required">
							</div>	
						</div>
						<div class="form-group">
							<div class="col-3 text-r">
								<label for="oldPassword" class="mr-10">原密码：</label>
							</div>
							<div class="col-6">
								<input type="password" class="form-console border" id="oldPassword" placeholder="原密码" required="required">
							</div>
						</div>
						<div class="form-group">
							<div class="col-3 text-r">
								<label for="newPassword" class="mr-10">新密码：</label>
							</div>
							<div class="col-6">
								<input type="password" class="form-console border" id="newPassword" placeholder="新密码" required="required">
							</div>
						</div>
						<div class="form-group">
							<div class="col-3 text-r">
								<label for="confirmPassword" class="mr-10">确认新密码：</label>
							</div>
							<div class="col-6">
								<input type="text" class="form-console border" id="confirmPassword" placeholder="确认新密码" required="required">
							</div>
						</div>
						<div class="form-group">
							<div class="col-6 col-offset-2">
								<button class="btn-confirm" onclick="doUpdatePassword()">确认修改</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">

		function login(){

			var username = document.getElementById("username").value;
			var password = document.getElementById("password").value;
			var captcha = document.getElementById("captcha").value;

			if(!username){
				alert("请输入用户名");
				return;
			}
			if(!password){
				alert("请输入密码");
				return;
			}
			if(!captcha){
				alert("请输入验证码");
				return;
			}
			if(password.length<6){
				alert("密码最小长度应大于6位");
				return;
			}
			if(password.length>128){
				alert("密码最大长度应小于128位");
				return;
			}

			window.location.href="topicdynamics.html";
		}

		function doUpdatePassword(){
			
			var account = document.getElementById("account").value;
			var oldPassword = document.getElementById("oldPassword").value;
			var newPassword = document.getElementById("newPassword").value;
			var confirmPassword = document.getElementById("confirmPassword").value;

			if(!account){
				alert("请输入账号");
				return;
			}
			if(!oldPassword){
				alert("请输入原始密码");
				return;
			}
			if(!newPassword){
				alert("请输入新密码");
				return;
			}
			if(!confirmPassword){
				alert("请输入确认密码");
				return;
			}
			if(newPassword!=confirmPassword){
				alert("确认密码应与新密码一致");
				return;
			}
			if(newPassword.length<6){
				alert("密码最小长度应大于6位");
				return;
			}
			if(newPassword.length>128){
				alert("密码最大长度应小于128位");
				return;
			}
		}
	</script>
</body>
</html>