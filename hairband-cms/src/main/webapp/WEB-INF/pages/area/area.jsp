<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../commons/commons.jsp" %>
</head>
<body>
	<button onclick="javascript:framework.openWindowTab('${dynUrl }/area/areajs.htm')" class="btn btn-primary btn-block">生成区域js</button>
	<div id="area_tree" class="ztree"></div>
	<div id="area_window" class="easyui-window" data-options="top:100,closed:true,width:400,height:400,modal:true,closable:true" title="新增子节点">
		<form id="area_form" action="${dynUrl }/area/insertarea.htm" class="easyui-form" method="post" style="padding:50 50 50 50">
			<input type="hidden" name="pid"/>
			<div class="form-group">
					<label for="code">code</label>
					<input type="text" name="code" class="easyui-textbox" readonly="readonly" data-options="required:true"  style="width: 120px;height: 25px;"/>
			</div>
			<div class="form-group">
					<label for="name">名称</label>
					<input type="text" name="name" class="easyui-textbox" data-options="required:true"  style="width: 120px;height: 25px;"/>
			</div>
			<div style="padding-left:80px">
				<button class="btn btn-primary" id="area_form_submit" onclick="return false;">提交</button>
				<button class="btn btn-primary" id="area_form_reset" onclick="return false;" style="margin-left:20px">重置</button>
			</div>
		</form>
	</div>
	<script type="text/javascript">
	(function($){
		$(document).ready(function(){
			
			var roleArr;
			// get the current user's role_area.
			$.post(window.framework.dynUrl+"/role/roarea.htm",{},function(data){
				if(data.success){
					roleArr = data.result.split(",");	
				}else{
					framework.alert(data.errMsg);
				}								
			},"json");
			
			var area_setting = {
					async:{
						dataType:"json",
						enable:true,
						url:window.framework.dynUrl+"/area/areas.htm",
						type:'post',
						autoParam:['pid'],
						dataFilter:function(treeId, parentNode, responseData){
							var result = [];
							if(responseData.success){
								$.each(responseData.result,function(i,d){
									var map = {};
									map.id = "area_tree_"+d.id;
									map.pid = d.id;
									map.code = d.code;
									map.name = d.code+":"+d.name;
									map.isParent = true;
									result.push(map);
								});
							}
							return result;
						}
					},
					callback: {
						onExpand: function(event, treeId, treeNode){
							var code = treeNode.code;
							if (code != undefined) {
								var len = code.length;
									//先截取3
									var province = new Array();//放省
									var alldata = new Array();//放所选的城市里的亭子
									var strId_len=code.length;
									
									if(code == 1 && roleArr[0] != 1 ){
										var length = roleArr.length;
										for (var j = 0 ; j < length; j++) {
											province.push(roleArr[j].substring(0,3));
										}
										$.post(window.framework.dynUrl+"/area/areas.htm",{"pid":treeNode.pid},function(data){
											var data = eval("(" + data + ")");
											var ary = data.result;
											for (var i = 0; i < ary.length; i++) {
												for(var c = 0 ; c < province.length ; c++){
													if (ary[i].code == province[c]){
														break;
													}
													if (c == (province.length - 1)) {
														$("a[title*='" + ary[i].code + "']").parent().remove();
													}
												}	
											}
										});
									}
									var ch = treeNode.children;
									var chl = ch.length;
									if(chl>0){
										for(var k = 0 ; k < chl ; k++){
											strId_len = ch[k].code.length;
										}
									}
									//strId_len = ch[0].code.length;
									 if(strId_len>=5 && strId_len< 8){
									
									var city = new Array();//放市集
									var length = roleArr.length;
									for (var j = 0 ; j < length; j++) {
										if (roleArr[j].length >3 && roleArr[j].length < 8 ) {
											city.push(roleArr[j]);
										}
									}
									$.post(window.framework.dynUrl+"/area/areas.htm",{"pid":treeNode.pid},function(data){
										//console.log(data);
										var data = eval("(" + data + ")");
										var ary = data.result;
										//console.log(ary);
										for (var i = 0; i < ary.length; i++) {
											for(var c = 0 ; c < city.length ; c++){
												if (ary[i].code == city[c]){
													break;
												}
												if (c == (city.length - 1)) {
													$("a[title*='" + ary[i].code + "']").parent().remove();
												}
											}	
										}
									});
									
								}
								
							}
						},
						beforeAsync:function(treeId, treeNode){
							
						},
						onRightClick:function(event,treeId,treeNode){
							var codeLength = treeNode.name.split(":")[0].length;
							if(codeLength==15){
								rightClick("pavilion",treeNode,treeId);
							}else if(codeLength==9 || codeLength==12){
								rightClick("area",treeNode,treeId);
							}
							
						},
						beforeRename:function(treeId, treeNode, newName, isCancel){
							if(!isCancel){
								var pIndex = treeNode.getParentNode().name.indexOf(":");
								if(pIndex < 2){
									framework.alert("上级节点必须要有code才能设置子节点code");
									return false;
								}
								var index = treeNode.name.indexOf(":");
								if(index != 0){
									framework.alert("code设置后，无法修改，如果要修改，联系数据库管理员");
									return false;
								}
								index = newName.indexOf(":");
								var code = newName.substring(0,index);
								var pCode = treeNode.getParentNode().name.substring(0,pIndex);
								if(code.indexOf(pCode) != 0){
									framework.alert("子节点code开头必须包含父节点code");
									return false;
								}
								if(pCode.length == 1&&code.length == 2){
									return true;
								}else if(code.length == pCode.length+3){
									return true;
								}else{
									framework.alert("子节点code开头必须包含父节点code,并且除省编码外其余编码在父编码后追加3位编码");
								}
								return false;
							}
						},
						onRename:function(event, treeId, treeNode, isCancel){
							if(!isCancel){
								var index = treeNode.name.indexOf(":");
								var code = treeNode.name.substring(0,index);
								var name = treeNode.name.substring(index+1);
								framework.startMask();
								$.post(window.framework.dynUrl+"/area/updatearea.htm",{
									id:treeNode.pid,
									pid:treeNode.getParentNode().pid,
									name:name,
									code:code
								},function(data){
									if(data.success){
										
									}else{
										var tree = $.fn.zTree.getZTreeObj(treeId);
										tree.reAsyncChildNodes(treeNode.getParentNode(), "refresh");
										framework.alert(data.errMsg);
									}
									framework.closeMask();
								},"json");
							}
						}
					},
					edit:{
						enable:true,
						showRenameBtn:false,
						showRemoveBtn:false
					}
				};
				
				$.fn.zTree.init($("#area_tree"), area_setting, [{id:'area_tree_0',pid:0,name:'省市区管理树',isParent:true}]);
		});
	})(jQuery);
	
	function rightClick(name,treeNode,treeId) {
		var form = $("#"+name+"_form");
		var index = $.trim(treeNode.name).indexOf(":");
		var code = $.trim(treeNode.name).substring(0,index);
		//先清数据，再查数据
		form.form("clear");
		queryPavilion(name,code,form);
		
		if(index <= 0){
			framework.alert("上级节点必须要有code才能新增子节点");
			return;
		}
		var len = code.length;
		var codeStr ; 
		$.ajax({
			   type: "post",
			   url: framework.dynUrl + "/area/childrenCode.htm?pid="+treeNode.pid,
			   cache:false,		   
			   success: function(data){
				   var result = (JSON.parse(data)).result;
				   if(len == 9){
					   if(result == null){
						   codeStr=code+"001";
					   }else{
						   codeStr = parseInt(result.code)+1;
					   }
				   }else if(len == 12){
					   if(result == null){
						   codeStr=code+"001";
					   }else{
						   codeStr = parseInt(result.code)+1;
					   }
				   }
				   form.form("load",{pid:treeNode.pid,code:codeStr});
				   $("#"+name+"_window").window("open");
			  }
			});
		
		$("#"+name+"_form_submit").one("click",function(){
			form.form("submit",{
				onSubmit:function(param){
					if($(this).form("validate")){
						framework.startMask();
						return true;
					}
					return false;
				},
				success:function(data){
					data = framework.parseJSON(data);
					if(data.success){
						var tree = $.fn.zTree.getZTreeObj(treeId);
						tree.reAsyncChildNodes(treeNode, "refresh");
						framework.alert("操作成功");
						form.form("clear");
						$("#"+name+"_window").window("close");
					}else{
						framework.alert(data.errMsg);
					}
					framework.closeMask();
				}
			});
		});
		$("#"+name+"_form_reset").one("click",function(){
			form.form("clear");
		});
		
		
	}
	function queryPavilion(name,code,form) {
		$.ajax({
			   type: "post",
			   url: framework.dynUrl + "/pavilionInfo/selectPavilionByCode.htm?code="+code,
			   success: function(data){
				   var result = (JSON.parse(data)).result;
				   if(result==null){
					   $("#"+name+"_window").prev().find(".panel-title").html("新增亭子信息");
				   }else{
					   $("#"+name+"_window").prev().find(".panel-title").html("修改亭子信息");
				   }
				   form.form('load', result);
			  }
		});
		
	}
	</script>
</body>
</html>