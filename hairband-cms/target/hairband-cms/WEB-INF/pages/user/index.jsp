<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${staUrl }/resources/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="${staUrl }/resources/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${staUrl }/resources/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${staUrl }/resources/loading/loading.css">
<link rel="stylesheet" type="text/css" href="${staUrl }/resources/ztree/css/zTreeStyle/zTreeStyle.css">
<link rel="stylesheet" type="text/css" href="${staUrl }/css/commons/default.css">
<script type="text/javascript" src="${staUrl }/resources/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${staUrl }/resources/ajaxupload/jquery.ajaxupload.js"></script>
<script type="text/javascript" src="${staUrl }/resources/ztree/js/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="${staUrl }/resources/loading/jquery.browser.min.js"></script>
<script type="text/javascript" src="${staUrl }/resources/loading/jquery.bgiframe.min.js"></script>
<script type="text/javascript" src="${staUrl }/resources/loading/loading.js"></script>
<script type="text/javascript" src="${staUrl }/resources/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${staUrl }/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${staUrl }/js/framework/framework.js"></script>
<script type="text/javascript" src="${staUrl }/js/framework/jquery.cookie.js"></script>
</head>
<body class="easyui-layout">
	<input type="hidden" value="${staUrl }" id="staUrl"/>
	<input type="hidden" value="${rootUrl }" id="rootUrl"/>
	<input type="hidden" value="${dynUrl }" id="dynUrl"/>
	<input type="hidden" value="${imgUrl }" id="imgUrl"/>
	<div data-options="region:'north',border:false">
		<div class="easyui-panel" id="menu_container">
			<!-- <a href="#" class="easyui-linkbutton" data-options="plain:true" target="_blank"></a> -->
			<a href="${dynUrl }/develop/initNavigater.htm" class="easyui-linkbutton" data-options="plain:true" onclick="javascript:return false;">程序管理</a> 
			<a href="${dynUrl }/user/navigation.htm" class="easyui-linkbutton" data-options="plain:true" onclick="javascript:return false;">后台管理</a>
			<a href="${dynUrl }/member/initNavigater.htm" class="easyui-linkbutton" data-options="plain:true" onclick="javascript:return false;">前台管理</a>
			<a href="${dynUrl }/user/unlogin.htm?html=html" class="easyui-linkbutton" data-options="plain:true">退出系统</a>
			<div id="#region" style="width:120px;float:right;">				
				<select id="cc" class="easyui-combobox" name="region" data-options="width:120,valueField:'v',textField:'k',onSelect:function(record){
					$.messager.confirm('','修改管理区域将关闭所有页面，是否继续',function(r){
						if(r){
							$.messager.progress({ msg: '数据处理中...'});
							$.post(framework.dynUrl+'/user/changeareacode.htm',{code:record.v},function(d){
								if(d.success){
									framework.removeAll();
									framework.areaCode = record.v;
								}else{
									$('#cc').combobox('setValue',framework.areaCode);
									$.messager.alert(d.errMsg);
								}
								$.messager.progress('close');
							},'json'); 
						}else{
							$('#cc').combobox('setValue',framework.areaCode);
						}
					});
				}"></select>							
			</div>
		</div>
	</div>
	<div data-options="region:'west',split:true,title:'导航栏'" style="width:150px;" id="west_container">
	</div>
	<div data-options="region:'south',border:false" style="height:10px;background:#A9FACD;padding:10px;">
	</div>
	<div data-options="region:'center',title:'工作台'">
		<div class="easyui-tabs" id="center_tabs">
		</div>
	</div>
	<div id="global_window" title="" class="easyui-window" data-options="width:300,height:150,closed:true,collapsible:false,minimizable:false,maximizable:false,closable:false,modal:true">
		<h4 style="color:red">区域权限不存在，无法访问</h4>
	</div>
	<div class="easyui-menu" id="tabs_menu" data-options="onClick:function(item){window.framework.removeAll();}">
		<div>关闭所有标签页</div>
	</div>
	<div id="reLogin" class="easyui-window" data-options="closed:true,modal:true,title:'重新登陆',width:400,height:300">
		<div role="form">
			<div class="form-group">
				<label>用户名：</label>
				<input class="form-control" type="text" id="username"/>
			</div>
			<div class="form-group">
				<label>密码：</label>
				<input class="form-control" type="password" id="password"/>
			</div>
			<button class="btn btn-primary btn-block" id="reLogin_btn">登陆</button>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
		//	$.messager.progress({msg:'初始化处理中...'});
			$("#menu_container").find("a")[1].click();
			console.log("@@"+$("#dynUrl").val());
			$.post(framework.dynUrl+"/area/curUsrArea.htm",function(d){
				console.log(d.success&&d.result.length > 0);
				if(d.success&&d.result.length > 0){
					$("#cc").combobox("loadData",d.result);
					$("#cc").combobox("setValue",d.result[0].v);
					framework.areaCode = d.result[0].v;
					framework.areaList = d.result;
				}else{
					$("#global_window").window("open");
				}
				$.messager.progress('close');
			},"json");
			$("#reLogin_btn").click(function(){
				$.messager.progress({msg:'登陆处理中...'});
				$.post(framework.dynUrl+"/user/ajaxLogin.htm",{username:$.trim($("#username").val()),password:$.trim($("#password").val())},function(d){
					if(d.success){
						framework.reLoginWin("close");
					}else{
						$.messager.alert('',d.errMsg);
					}
					$.messager.progress('close');
				},'json');
			});
			framework.token = $.cookie("cms-token");
			framework.reLoginWin = $("#reLogin");
		});
	</script>
</body>
</html>
