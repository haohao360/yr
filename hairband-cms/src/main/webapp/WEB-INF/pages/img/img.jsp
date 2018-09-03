<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../commons/commons.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div class="easyui-tabs">
		<div title="图片批量上传">
			<div class="easyui-tabs">
				<div title="商品图">
					<button class="btn btn-primary btn-block" id="selectImgs">选择图片</button>
					<div id="imgs_view">
						
					</div>
				</div>
				<div title="logo">
					<button class="btn btn-primary btn-block" id="selectLogo">选择图片</button>
					<div id="logo_view">
						
					</div>
				</div>
				<div title="资质图">
					<button class="btn btn-primary btn-block" id="selectLicence">选择图片</button>
					<div id="licence_view">
						
					</div>
				</div>
			</div>
		</div>
		<div title="图片管理">
			<div class="easyui-tabs">
				<div title="商品图">
					<div><input class="easyui-datebox" id="img_input"/><button class="btn btn-primary" id="img_button">查询</button></div>
					<div id="view_img"></div>
				</div>
				<div title="logo">
					<div><input class="easyui-datebox" id="logo_input"/><button class="btn btn-primary" id="logo_button">查询</button></div>
					<div id="view_logo"></div>
				</div>
				<div title="资质图">
					<div><input class="easyui-datebox" id="licence_input"/><button class="btn btn-primary" id="licence_button">查询</button></div>
					<div id="view_licence"></div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			deleteFile = function(path,obj){
				$.messager.confirm("","确定删除？",function(r){
					if(r){
						framework.startMask();
						$.post(framework.uploadPro+"/delete.htm?token="+framework.token,{uri:path},function(d){
							if(d.success){
								$(obj).parent("div").remove();
							}else{
								farmework.alert(d.errMsg);
							}
							framework.closeMask();
						},"json");
					}
				});
			}
			$("#logo_button").click(function(){
				var date = $("#logo_input").datebox("getValue");
				$.post(framework.uploadPro+"/list.htm?token="+framework.token,{date:date,prifix:"logo"},function(d){
					if(d.success){
						var paths = d.result;
						var div = $('<div>'); 
						$.each(paths,function(i,path){
							var div2 = $('<div style="float:left">');
							div.append(div2);
							div2.append('<img class="img-thumbnail" src="'+framework.imgUrl+path+'">');
							div2.append('<input class="form-control" value="'+path+'"/>');
							div2.append('<button class="btn btn-danger btn-block" onclick="deleteFile(\''+path+'\',this)">删除</button>');
						});
						$("#view_logo").empty();
						$("#view_logo").prepend(div);
					}else{
						framework.alert(d.errMsg);
					}
				},"json");
			});
			$("#img_button").click(function(){
				var date = $("#img_input").datebox("getValue");
				$.post(framework.uploadPro+"/list.htm?token="+framework.token,{date:date,prifix:"product"},function(d){
					if(d.success){
						var paths = d.result;
						var div = $('<div>'); 
						$.each(paths,function(i,path){
							var div2 = $('<div style="float:left">');
							div.append(div2);
							div2.append('<img class="img-thumbnail" src="'+framework.imgUrl+path+'">');
							div2.append('<input class="form-control" value="'+path+'"/>');
							div2.append('<button class="btn btn-danger btn-block" onclick="deleteFile(\''+path+'\',this)">删除</button>');
						});
						$("#view_img").empty();
						$("#view_img").prepend(div);
					}else{
						framework.alert(d.errMsg);
					}
				},"json");
			});
			$("#licence_button").click(function(){
				var date = $("#licence_input").datebox("getValue");
				$.post(framework.uploadPro+"/list.htm?token="+framework.token,{date:date,prifix:"licence"},function(d){
					if(d.success){
						var paths = d.result;
						var div = $('<div>'); 
						$.each(paths,function(i,path){
							var div2 = $('<div style="float:left">');
							div.append(div2);
							div2.append('<img class="img-thumbnail" src="'+framework.imgUrl+path+'">');
							div2.append('<input class="form-control" value="'+path+'"/>');
							div2.append('<button class="btn btn-danger btn-block" onclick="deleteFile(\''+path+'\',this)">删除</button>');
						});
						$("#view_licence").empty();
						$("#view_licence").prepend(div);
					}else{
						framework.alert(d.errMsg);
					}
				},"json");
			});
			$("#selectImgs").ajaxUploadPrompt({
				url : window.framework.uploadUrl+"product&token="+framework.token,
				multiple:'multiple',
				beforeSend:function () {
					window.framework.startMask();
				},
				onprogress:function (e) {
				},
				error:function (e) {
					framework.alert(e);
				},
				success:function (data) {
					try{
						data = $.parseJSON(data);
						if(data.success){
							var paths = data.result;
							var div = $('<div>');
							$.each(paths,function(i,path){
								var div2 = $('<div style="float:left">');
								div.append(div2);
								div2.append('<img class="img-thumbnail" src="'+framework.imgUrl+path+'">');
								div2.append('<input class="form-control" value="'+path+'"/>');
								div2.append('<button class="btn btn-danger btn-block" onclick="deleteFile(\''+path+'\',this)">删除</button>');
							});
							$("#imgs_view").prepend(div);
						}else{
							window.framework.alert(data.errMsg);
						}
					}catch(e){
						framework.alert(data);
					}
					framework.closeMask();
				}
			});
			$("#selectLogo").ajaxUploadPrompt({
				url : window.framework.uploadUrl+"logo&token="+framework.token,
				multiple:'multiple',
				beforeSend:function () {
					window.framework.startMask();
				},
				onprogress:function (e) {
				},
				error:function (e) {
					framework.alert(e);
				},
				success:function (data) {
					try{
						data = $.parseJSON(data);
						if(data.success){
							var paths = data.result;
							var div = $('<div>');
							$.each(paths,function(i,path){
								var div2 = $('<div style="float:left">');
								div.append(div2);
								div2.append('<img class="img-thumbnail" src="'+framework.imgUrl+path+'">');
								div2.append('<input class="form-control" value="'+path+'"/>');
								div2.append('<button class="btn btn-danger btn-block" onclick="deleteFile(\''+path+'\',this)">删除</button>');
							});
							$("#logo_view").prepend(div);
						}else{
							window.framework.alert(data.errMsg);
						}
					}catch(e){
						framework.alert(data);
					}
					framework.closeMask();
				}
			});
			$("#selectLicence").ajaxUploadPrompt({
				url : window.framework.uploadUrl+"licence&token="+framework.token,
				multiple:'multiple',
				beforeSend:function () {
					window.framework.startMask();
				},
				onprogress:function (e) {
				},
				error:function (e) {
					framework.alert(e);
				},
				success:function (data) {
					try{
						data = $.parseJSON(data);
						if(data.success){
							var paths = data.result;
							var div = $('<div>');
							$.each(paths,function(i,path){
								var div2 = $('<div style="float:left">');
								div.append(div2);
								div2.append('<img class="img-thumbnail" src="'+framework.imgUrl+path+'">');
								div2.append('<input class="form-control" value="'+path+'"/>');
								div2.append('<button class="btn btn-danger btn-block" onclick="deleteFile(\''+path+'\',this)">删除</button>');
							});
							$("#licence_view").prepend(div);
						}else{
							window.framework.alert(data.errMsg);
						}
					}catch(e){
						framework.alert(data);
					}
					framework.closeMask();
				}
			});
		});				
	</script>
</body>
</html>