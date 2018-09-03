<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE div PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../commons/commons.jsp" %>
</head>
<body>
<div style="width:70%;padding-left:10%;padding-top:30px">
	<form action="${dynUrl }/user/updatepass.htm" role="form" id="updatepass_form" method="post">
		<div class="form-group">
			<div class="input-group">
				<div class="input-group-addon">旧密码</div>
				<input class="form-control easyui-validatebox" type="password" name="oldpass" data-options="required:true"/>
			</div>
		</div>
		<div class="form-group">
			<div class="input-group">
				<div class="input-group-addon">新密码</div>
				<input class="form-control easyui-validatebox" type="password" name="newpass" id="newpass" data-options="required:true,validType:'length[6,20]'"/>
			</div>
		</div>
		<div class="form-group">
			<div class="input-group">
				<div class="input-group-addon">再次输入密码</div>
				<input class="form-control easyui-validatebox" type="password" required="true" data-options="required:true,validType:'rePassword'"/>
			</div>
		</div>
	</form>
	<button class="btn btn-primary" id="updatepass_button">提交</button>
	<button class="btn btn-primary" id="updatepass_clear">重置</button>
</div>
<script type="text/javascript">
	(function($){
		$(document).ready(function(){
			$("#updatepass_button").click(function(){
				$("#updatepass_form").form("submit",{
					 onSubmit:function(){
						return $(this).form('validate');
					 },
					 success:function(data){
						 $("#updatepass_form").form("clear");
						 window.framework.dialog(data);
					 }
				});
			});
			$("#updatepass_clear").click(function(){
				$("#updatepass_form").form("clear");
			});
		});
	})(jQuery);
	
	   //自定义验证
	  $.extend($.fn.validatebox.defaults.rules, {
	  rePassword: {
	    validator: function(newpass2){
	    var newpass = $("#newpass").val();
	    if(newpass==newpass2)
	    {
	      return true;
	    }else
	    {
	       return false;
	    }
	      
	    },
	    message: '两次输入密码不一致'
	  }
	});
</script>
</body>
</html>