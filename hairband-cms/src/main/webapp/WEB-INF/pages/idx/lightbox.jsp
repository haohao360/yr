<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../commons/commons.jsp" %>
<title>Insert title here</title>
</head>
<body>
<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="addBroacast()">新增轮播图</a>
<div id="selectBroadCastPagation">
</div>
<div class="easyui-window" title="新增轮播图" data-options="closed:true,modal:true,width:500,height:600" id="addBroadcastWindow" >
		<div>
			<form id="addbroadcastForm" method="post" data-options="novalidate:true" action="${dynUrl }/idx/add.htm">
				<div class="form-group">
				<label style="width:70px">连接url：</label>
					<input class="easyui-textbox" type="text" style="width: 200px;height: 25px;"  name="url"></input>
				</div>	
				<div class="form-group">
				<label style="width:70px">排序：</label>
					<input class="easyui-numberbox"  type="text" style="width: 120px;height: 25px;"  name="sort" data-options=" prompt:'请输入数字',required:true,min:0,precision:0"></input>
				</div>
				<div class="form-group">
				<label style="width:70px">渠道：</label>
					<span><input type="radio" name="channelId" value="1" checked="checked"/>PC</span>
					<span><input type="radio" name="channelId" value="2"/>手机</span>
				</div>
 				<div class="form-group">
 					<label style="width:70px">图片：</label>
 					<img alt="上传图片" src="${staUrl }/image/default.jpg" style="width:300px;height:300px" id="add_img"/>
 					<input class="form-control" type="text" style="width: 120px;height: 25px;"  name="imgUrl" readonly="readonly"/>
 					<a href="javascript:void(0)" class="btn btn-primary picture btn-block" >上传图片</a>
 				</div>
				<div>
					<a href="javascript:void(0)" class="btn btn-primary btn-block" id="addbroadcast_submit">提交</a> 
				</div>
			</form>
		</div>
	</div>
	<div id="updateBroadcastDiv" style="width:500px;height:600px;" class="easyui-window" title="修改轮播图信息" data-options="modal:true,closed:true">
		<form id="updateBroadcastForm" method="post" data-options="novalidate:true" action="${dynUrl }/idx/update.htm">
			<input type="hidden" name="id"/>
			<table>
				<tr>
					<td>连接url：</td>
					<td><input class="easyui-textbox" type="text" style="width: 200px;height: 25px;"  name="url"/></td>
				</tr>
				<tr>
					<td>排序：</td>
					<td><input class="easyui-textbox st" type="text" style="width: 120px;height: 25px;"  name="sort" data-options="required:true,min:0,precision:0"/></td>
				</tr>
				<tr>
					<td>是否启用:</td>
					<td>
						<span><input type="radio" name="isUse" value="1" checked="checked"/>启用</span>
						<span><input type="radio" name="isUse" value="0"/>停用</span>
					</td>
				</tr>
				<tr>
					<td>状态:</td>
					<td>
						<span><input type="radio" name="isDel" value="1" checked="checked"/>正常</span>
						<span><input type="radio" name="isDel" value="127"/>删除</span>
					</td>
				</tr>
			</table>
			<div class="form-group">
				<label>图片：</label>
				<img alt="上传图片" src="${staUrl }/image/default.jpg" id="update_img" style="width:300px;height:300px"/>
				<input class="form-control" type="text" name="imgUrl" style="width: 120px;height: 25px;"  readonly="readonly"/>
				<a href="javascript:void(0)" class="btn btn-primary picture btn-block" >上传图片</a>
			</div>
			<div>
				<a href="javascript:void(0)" class="btn btn-primary btn-block" id="updatebroadcast_submit">提交</a> 
			</div>
		</form>
	</div>
	<script type="text/javascript">
		(function($){
			addBroacast = function(){
				$("#addbroadcastForm").form("reset");
				$("#addBroadcastWindow").window("open");
			};
			$("#updatebroadcast_submit").click(function(){
				$('#updateBroadcastForm').form('submit',{
					 onSubmit:function(){
						return $(this).form('enableValidation').form('validate');
					 },
					 success:function(data){
						 $('#updateBroadcastForm').form('clear');
						 $("#updateBroadcastDiv").window("close");
						 $('#selectBroadCastPagation').datagrid("reload");
						 window.framework.dialog(data);
					 }
				 });
			});
			
			$('#selectBroadCastPagation').datagrid({ 
		        pageSize:framework.pageNum,
		        method: 'post',
		        toolbar:"#tb",
		        border: true, 
		        url:'${dynUrl}/idx/broadcastSearch.htm', 
		        pagination:true, 
		        fitColumns:true,
		        singleSelect:true,
		        rownumbers:true,
		        columns:[[{
		        	field:"url",title:"连接",formatter:function(value){
		        	return	"<a href='"+value+"' target='_blank'>"+value+"</a>";
		        	},width:100
		        },{
		        	field:"imgUrl",title:"轮播图连接",formatter:function(value){
		        		var imgUrl = window.framework.imgUrl;
		        		return	"<a href='"+imgUrl+value+"' target='_blank'>轮播图连接</a>";	
		        	},width:100
		        },{
		        	field:"channelId",title:"渠道",formatter:function(value){
		        		if(value == 1){
		        			return	"PC";	
		        		}else{
		        			return	"手机";	
		        		}
		        	},width:100
		        },{
		        	field:"createtime",title:"创建时间",width:100
		        },{
		        	field:"sort",title:"排序",width:100
		        },{
		        	field:"isUse",title:"是否启用",formatter:function(value){
		        		if(value == 1){
		        			return	"启用";	
		        		}else{
		        			return	"停用";	
		        		}
		        	},width:50
		        }]],
		        onBeforeLoad: function (param) {
		        },
		        onLoadSuccess: function (data) {
		        	
		        },
		        onLoadError: function () {
		            
		        },
		        onClickCell: function (rowIndex, field, value) {
		            
		        },
		        onDblClickRow: function (rowIndex, rowData) {
		        	$("#updateBroadcastForm").form("load",rowData);
		        	var $img = $('#update_img');
					var _url = window.framework.imgUrl + rowData.imgUrl;						
					$img.attr("src",_url);
		        	$("#updateBroadcastDiv").window("open");
		        },
		        onClickRow:function(rowIndex,rowData){
		        	
		        },
		        loadFilter:function(data){
		        	if(!data.success){
		        		framework.dialog(data);
		        		return {};
		        	}else{
		        		var result = {
			        			total:data.result.total,
			        			rows:data.result.entry,
			        	};
			        	return result;
		        	}
		        }
		    });
			
			$('.picture').each(function(){
				var obj = $(this);
				obj.ajaxUploadPrompt({
					url : window.framework.uploadUrl+"product&token="+framework.token,
					beforeSend : function () {
						framework.startMask();
					},
					onprogress : function (e) {
					},
					error : function (e) {
						console.log(e);
					},
					success : function (data) {
						try{ 
							data = $.parseJSON(data);
							if(data.success){
								var div = obj.parent("div");
								div.children("img").attr("src",framework.imgUrl+data.result[0]);
								div.children("input").val(data.result[0]);
							}else{
								framework.alert(data.errMsg);
							}
						}catch(e){
							framework.alert(data);
						}
						framework.closeMask();
					}
				});
			})
			$("#addbroadcast_submit").click(function(){
				$('#addbroadcastForm').form('submit',
					{
					 onSubmit:function(){
						return $(this).form('enableValidation').form('validate');
					 },
					 error : function (e) {
							console.log(e);
					},
					 success:function(data){
						 window.framework.dialog(data);
						 data = $.parseJSON(data);
						 if (data.success) {
							 $("#addBroadcastWindow").window("close");
							 $("#add_img").attr("src","${staUrl }/image/default.jpg");
							 $('#selectBroadCastPagation').datagrid("reload");
						 }else{
							 window.framework.dialog(data);
						 }
					 }
				 });
			});
		})(jQuery);
	</script>
</body>
</html>