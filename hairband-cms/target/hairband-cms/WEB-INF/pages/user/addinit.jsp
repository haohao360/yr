<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../commons/commons.jsp" %>
</head>
<body>
	<div class="easyui-panel" title="新增用户" style="width: 100%;height:100%;">
	
		<div style="padding:50px 0 50px 30%">
			<form id="addCmsUserForm" method="post" data-options="novalidate:true" action="${dynUrl }/user/add.htm">
				<table>
					<tr>
						<td>用户名:</td>
						<td><input class="easyui-textbox" type="text" name="userName" 
							 style="width: 120px;height: 25px;" data-options="required:true,validType:'length[4,20]'"></input></td>
					</tr>
					<tr>
						<td>真实姓名:</td>
						<td><input class="easyui-textbox" type="text" name="name"
							 style="width: 120px;height: 25px;" data-options="required:true,validType:'length[2,10]'"></input></td>
					</tr>
					<tr>
						<td>email:</td>
						<td><input class="easyui-textbox" type="text" name="email"
							 style="width: 120px;height: 25px;" data-options="required:true,validType:'email'"></input></td>
					</tr>
					<tr>
						<td>mobile:</td>
						<td><input class="easyui-textbox" type="text" name="mobile" style="width: 120px;height: 25px;"  data-options="required:true,validType:'mobile'"></input></td>
					</tr>
					<tr>
						<td>性别:</td>
						<td>
							<span><input type="radio" name="sex" value="1" checked="checked"/>男</span>
							<span><input type="radio" name="sex" value="2"/>女</span>
							<span><input type="radio" name="sex" value="9"/>保密</span>
						</td>
					</tr>
					<tr>
						<td>部门:</td>
						<td>
						<select class="easyui-combobox" id="addcms_dept" name="department" style="width:120px" data-options="mode:'remote',textField:'k',valueField:'k',url:'${dynUrl }/dict/query.htm?type=2'">
						</select></td>
					</tr>
					<tr>
						<td>角色:</td>
						<td>
							<input class="easyui-combobox" id="addcms_role" name="roleId" data-options="url:'${dynUrl }/role/rolenames.htm',method:'get',valueField:'v',textField:'k',panelHeight:'auto',required:true"  style="width: 120px"/>
						</td>
					</tr>
					<tr>
						<td>状态:</td>
						<td>
							<span><input type="radio" name="state" value="1" checked="checked"/>正常</span>
							<span><input type="radio" name="state" value="127"/>删除</span>
						</td>
					</tr>
					<tr>
						<td>
						</td>
						<td><h5 style="color: red;">温馨提示：初始密码为123456</h5></td>
					</tr>
				</table>
			</form>
			<div style="padding-left:70px">
				<a href="javascript:void(0)" class="btn btn-default" id="addcmsuser_submit">提交</a> 
				<a href="javascript:void(0)" class="btn btn-default" id="addcmsuser_clear">重置</a>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		(function($){
			$("#addcmsuser_submit").click(function(){
				 $('#addCmsUserForm').form('submit',{
					 onSubmit:function(){
						return $(this).form('enableValidation').form('validate');
					 },
					 success:function(data){
						 window.framework.dialog(data);
					 }
				 });
			});
			$("#addcmsuser_clear").click(function(){
				 $('#addCmsUserForm').form('clear');
			});
		})(jQuery);
	</script>
</body>
</html>