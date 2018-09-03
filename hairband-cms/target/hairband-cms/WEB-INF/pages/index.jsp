<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>车贷后台管理系统</title>
<style type="text/css">
	* { margin: 0px; padding: 0px;}
	html, body { height: 100%; font-family: Microsoft Yahei;}
	#con { position: relative; width: 100%; height: 100%; background:url('/hairband-cms/image/login.jpg') no-repeat center center;}
	#con span{position: absolute; left: 50%; top: 50%; width: 445px; height: 175px; margin-left: -93px; margin-top: -45px;}
	#con #log_fm { position: absolute; left: 50%; top: 50%; width: 445px; height: 175px; margin-left: -223px; margin-top: -18px;}
	#con #log_fm [type=text] { position: absolute; left: 182px; margin-top: 5px; height: 35px; width: 256px; border: 0px; outline: none; line-height: 35px;}
	#con #log_fm [type=password] { position: absolute; left: 182px; margin-top: 29px; height: 35px; width: 256px; border: 0px; outline: none; line-height: 35px;}
	#con #log_fm [type=submit] { position: absolute; left: 115px; top: 120px; height: 50px; width: 215px; opacity: 0; filter: alpha(opacity=0); cursor: pointer;}
</style>
</head>
<body>
	<div id="con">
		<span style="color:red">${errMsg }</span>
		<form id="log_fm" action="/hairband-cms/user/login.htm" method="post">
			<input id="inp_txt" type="text" name="username" style="border:1px solid red"/><br/>
			<input type="password" name="password" style="border:1px solid red"/><br/>
			<br/>
			<input type="submit" value="提交" />
		</form>
	</div>
</body>
</html>