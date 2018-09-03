<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../commons/commons.jsp" %>
</head>
<body>
	<div style="padding:10px">
		<form class="form-inline" role="form" id="selectCmsUserForm" method="post" data-options="novalidate:true" action="${dynUrl }/user/infos.htm">
		  <div class="form-group">
		    <label>姓名：</label>
		    <input type="text"  style="width: 120px;height: 25px;" class="easyui-textbox" id="selectCmsUserForm_name" placeholder="输入用户真实姓名">
		  </div>
		  <div class="form-group">
		      <label>电话：</label>
		      <input class="easyui-textbox" type="text" style="width: 120px;height: 25px;"  id="selectCmsUserForm_mobile" placeholder="输入用户手机号">
		  </div>
		  <div class="form-group">
		    <label>部门：</label>
		    <select class="easyui-combobox" id="selectCmsUserForm__department" data-options="mode:'remote',textField:'k',valueField:'k',url:'${dynUrl }/dict/query.htm?type=2'" style="width:120px">
			</select>
		  </div>
		  <div class="form-group">
		    <label>角色：</label>
		    <input class="easyui-combobox" id="selectCmsUserForm__roleid" data-options="url:'${dynUrl }/role/rolenames.htm',method:'get',valueField:'v',textField:'k',panelHeight:'auto'"  style="width: 120px"/>
		  </div>
		  <button type="submit" class="btn btn-default" id="selectCmsUserSubmit" onclick="javascript:return false">查询</button>
		</form>
	</div>
	<div id="selectCmsUserPagation">
	</div> 
	<div id="updateCmsUserDiv" style="width:300px;height:300px;" class="easyui-window" title="修改cms用户信息" data-options="modal:true,closed:true">
		<form id="updateCmsUserForm" method="post" data-options="novalidate:true" action="${dynUrl }/user/update.htm" style="padding-left:30px;padding-top:30px">
				<input type="hidden" name="id"/>
				<table>
					<tr>
						<td>用户名:</td>
						<td><input class="easyui-textbox" type="text" style="width: 120px;height: 25px;"  name="userName" readonly="readonly"></input></td>
					</tr>
					<tr>
						<td>真实姓名:</td>
						<td><input class="easyui-textbox" type="text" style="width: 120px;height: 25px;"  name="name"
							data-options="required:true,validType:'length[2,10]'"></input></td>
					</tr>
					<tr>
						<td>email:</td>
						<td><input class="easyui-textbox" type="text" name="email" style="width: 120px;height: 25px;" 
							data-options="required:true,validType:'email'"></input></td>
					</tr>
					<tr>
						<td>mobile:</td>
						<td><input class="easyui-textbox" name="mobile" data-options="required:true,validType:'mobile'"></input></td>
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
						<td><select class="easyui-combobox" name="department" style="width:120px" data-options="mode:'remote',textField:'k',valueField:'k',url:'${dynUrl }/dict/query.htm?type=2'">
						</select></td>
					</tr>
					<tr>
						<td>角色:</td>
						<td>
							<input class="easyui-combobox" name="roleId" data-options="url:'${dynUrl }/role/rolenames.htm',method:'get',valueField:'v',textField:'k',panelHeight:'auto',required:true"  style="width: 120px"/>
						</td>
					</tr>
					<tr>
						<td>状态:</td>
						<td>
							<span><input type="radio" name="state" value="1" checked="checked"/>正常</span>
							<span><input type="radio" name="state" value="127"/>删除</span>
						</td>
					</tr>
				</table>
				<div style="padding-left:70px">
					<a href="javascript:void(0)" class="btn btn-default" id="updatecmsuser_submit">提交</a> 
					<a href="javascript:void(0)" class="btn btn-default" id="updatecmsuser_clear">重置</a>
				</div>
			</form>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$("#updatecmsuser_submit").click(function(){
				$('#updateCmsUserForm').form('submit',{
					 onSubmit:function(){
						return $(this).form('enableValidation').form('validate');
					 },
					 success:function(data){
						 $('#updateCmsUserForm').form('clear');
						 $("#updateCmsUserDiv").window("close");
						 $('#selectCmsUserPagation').datagrid("reload");
						 window.framework.dialog(data);
					 }
				 });
			});
			
			$("#updatecmsuser_clear").click(function(){
				 $('#updateCmsUserForm').form('clear');
			});
			
			$("#selectCmsUserForm__roleid").combobox({});
			$('#selectCmsUserPagation').datagrid({ 
		        title:'cms用户列表', 
		        pageSize:framework.pageNum,
		        pageList:[20,30,40,50],
		        method: 'post',
		        border: true, 
		        url:'${dynUrl}/user/infos.htm', 
		        pagination:true, 
		        fitColumns:true,
		        singleSelect:true,
		        rownumbers:true,
		        columns:[[{
		        	field:"userName",title:"用户名",width:50
		        },{
		        	field:"name",title:"姓名",width:50
		        },{
		        	field:"sex",title:"性别",formatter:function(value){
		        		if(value == 1){
		        			return "男";
		        		}
		        		if(value == 2){
		        			return "女";
		        		}
		        		if(value == 9){
		        			return "保密";
		        		}
		        	},width:50
		        },{
		        	field:"mobile",title:"电话",width:50
		        },{
		        	field:"email",title:"email",width:50
		        },{
		        	field:"department",title:"部门",width:100
		        },{
		        	field:"roleName",title:"角色名称",width:150
		        }]],
		        onBeforeLoad: function (param) {
		        	param.name = $.trim($("#selectCmsUserForm_name").val());
		        	param.mobile = $.trim($("#selectCmsUserForm_mobile").val());
		        	param.department = $.trim($("#selectCmsUserForm__department").combobox("getText"));
		        	param.roleId = $("#selectCmsUserForm__roleid").combobox("getValue");
		        },
		        onLoadSuccess: function () {
		            
		        },
		        onLoadError: function () {
		            
		        },
		        onClickCell: function (rowIndex, field, value) {
		            
		        },
		        
		        onDblClickRow: function (rowIndex, rowData) {
		        	if(rowData.roleName == 'root'){
		        		return;
		        	}
		        	$("#updateCmsUserForm").form("load",rowData);
		        	$("#updateCmsUserDiv").window("open");
		        },
		        onClickRow:function(rowIndex,rowData){
		        	
		        },
		        loadFilter:function(data){
		        	if(!data.success){
		        		framework.dialog(data);
		        		return [];
		        	}else{
		        		var result = {
			        			total:data.result.total,
			        			rows:data.result.entry,
			        	};
			        	return result;
		        	}
		        }
		    });
			
			$("#selectCmsUserSubmit").click(function(){
				$("#selectCmsUserForm").form("submit",{
					onSubmit:function(){
						$('#selectCmsUserPagation').datagrid("reload");
						return false;
					}
				});
			});
			
			
		});
		
	</script>
</body>
</html>