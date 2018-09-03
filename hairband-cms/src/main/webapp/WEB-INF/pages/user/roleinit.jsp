<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<html>
<head>
<%@include file="../commons/commons.jsp" %>

</head>
<body>
<div>
	<form role="form" class="form-inline" style="padding-left:50px">
		<div class="form-group">
		<br/>
			<label>角色名：</label>
			<input type="text" style="width: 120px;height: 25px;"  class="form-control" placeholder="输入角色名称" id="roleinit_roleName"/>
			<button class="btn btn-default" id="roleinit_button">查询</button>
		</div>
	</form>
	<div id="roleinit_datagrid" style="width:100%">
	</div>
	
	<div id="roleinit_window" class="easyui-window" data-options="modal:true,closed:true,width:500,height:500">
		<ul id="roleinit_tree" class="ztree"></ul>
	</div>
	
	<div id="wnd_role_region" class="easyui-window" data-options="modal:true,closed:true,width:500,height:500">
		<ul id="tr_role_region" class="ztree"></ul>
	</div>
</div>
<script type="text/javascript">
(function($){
	$(document).ready(function(){
		var areas = [];
		hasArea = function(a){
			var b = false;
			$.each(areas,function(i,aa){
				if(a == aa){
					b = true;
				}
			});
			return b;
		}
		replaceArea = function(code){
			var b = true;
			$.each(areas,function(i,a){
				if(a){
					if(a.indexOf(code) == 0){
						delete areas[i];
					}
					if(code.indexOf(a) == 0){
						b = false;
						framework.alert("已经选择了父权限，无法选择子权限");
					}
				}
			});
			if(b){
				areas.push(code);
			}
			return b;
		}
		recursiveNode = function(node,checked){
			node.children&&$.each(node.children,function(i,n){
				recursiveNode(n,checked);
				ztree.checkNode(n,checked,false,false);
			});
		}
		removeArea = function(code){
			$.each(areas,function(i,a){
				if(code == a){
					delete areas[i];
				}
			});
		}
		$("#roleinit_button").click(function(event){
			$("#roleinit_datagrid").datagrid("reload");
			return false;
		});
		var _did;
		$('#wnd_role_region').window({
			onBeforeClose : function(){					
				framework.startMask();
				var codeS ;
				var codeArr = new Array();
				for (var i = 0,a; i < areas.length; i++) {
					a = areas[i];
					if(a){
						codeArr.push(a);
					}
				}
				codeS = codeArr.join(",");
				var id = _did;					
				$.post(window.framework.dynUrl+"/role/newarea.htm",{rid:id,codearea:codeS},function(data){
					if(data.success){	
						window.framework.alert("操作成功!");
					}else{
					}
					framework.closeMask();
				},"json");							
			}
		});
				
			var roleinit_setting={
				async:{
					dataType:"json",
					enable:true,
					url:window.framework.dynUrl+"/role/getnodebypar.htm",
					type:'post',
					autoParam:['fid','rid'],
					dataFilter:function(treeId, parentNode, responseData){
						var result = []
						if(responseData.success){
							$.each(responseData.result,function(i,d){
								var map = {};
								map.id = "roleinit_tree_"+d.id;
								map.fid = d.id;
								map.rid = parentNode.rid;
								map.name = d.name;
								map.isParent = !d.isLeaf;
								map.checked = d.isUse;
								result.push(map);
							});
						}
						return result;
					}
				},				
				callback: {
					onExpand: function(event, treeId, treeNode){
						
					},
					beforeAsync:function(treeId, treeNode){
						
					},
					onCheck:function(event, treeId, treeNode){
						framework.startMask();
						$.post(window.framework.dynUrl+"/role/updateRoleFun.htm",{rid:treeNode.rid,fid:treeNode.fid,isUse:treeNode.checked},function(data){
							if(data.success){
								
							}else{
								framework.alert(data.errMsg);
							}
							framework.closeMask();
						},"json");
					}
				},
				check:{
					enable:true
				}
			};
			
			window.roleinit={};
			roleinit.funManager=function(id,name){
				$.fn.zTree.init($("#roleinit_tree"), roleinit_setting, [{id:'roleinit_tree_0',name:'功能树',isParent:true,fid:0,rid:id,nocheck:true}]);
				$("#roleinit_window").window({title:"角色“"+name+"”的功能管理",closed:false,top:100});
			};
			var childrens = [];
			var parentNode;
			var role_region_setting = {					
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
									map.isUse = true;
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
							$.each(treeNode.children,function(i,node){
								if(hasArea(node.code)){
									ztree.checkNode(node,true,false,false);	
								}
							});
						},
						beforeAsync:function(treeId, treeNode){
							
						},
						onCheck:function(event, treeId, treeNode){	
							var code = treeNode.code;
							if(treeNode.checked){							
								if(!replaceArea(code)){
									ztree.checkNode(treeNode,false,false,false);	
								}else{
									recursiveNode(treeNode,false);
								}
							}else{
								removeArea(code);
							}
						}						
					},
					check:{
						enable:true
					}
				};
			
			roleinit.ChargeMgr = function(id,name){
					$.fn.zTree.init($("#tr_role_region"),role_region_setting,[{id:'area_tree_0',name:'功能树',isParent:true,pid:0,nocheck:true}]);
					var zTree = ztree = $.fn.zTree.getZTreeObj("tr_role_region");
					zTree.setting.check.chkboxType = { "Y" : "", "N" : "" };
					$("#wnd_role_region").window({title:"角色“" + name + "”的管辖区域",closed:false,top:100});	
					$.get(window.framework.dynUrl+"/role/selectroarea.htm",{id:id},function(data){
						areas = [];
						if(data.success){
							if(data.result){
								$.each(data.result.split(","),function(i,d){
									areas.push(d);
								});
							}
						}else{
							framework.alert(data.errMsg);
						}								
					},"json");
					_did = id;
				};
	
			var lastIndex;
			var datagrid = $("#roleinit_datagrid");
			datagrid.datagrid({
				title:"角色管理",
				pagination:true,
				pageSize:framework.pageNum,
				rownumbers:true,
				pageList:[20,30,40,50],
				fitColumns:true,
				method:"post",
				singleSelect:true,
				url:"${dynUrl}/user/roles.htm",
				columns:[[{
					field:"roleName",title:"角色名称",editor:{type:'text'},width:"200px"
				},{
					field:"createTime",title:"创建时间",width:"200px"
				},{
					field:"isUse",title:"是否启用",width:"200px",formatter:function(value){if(value)return '启用';return '禁用'},editor:{type:'combobox', options:{valueField: 'isUse',
						textField: 'key',
						data: [{
							isUse: true,
							key: '启用'
						},{
							isUse: false,
							key: '禁用'
						}]}}
				},{
					title:"功能管理",
					width:"200px",
					field:"field",
					formatter:function(value,rowData,rowIndex){
						if(rowData.id > 0){
							return "<button class='btn btn-primary' onclick='roleinit.funManager("+rowData.id+",\""+rowData.roleName+"\")'>功能管理</button>";
						}
						return "";
					}
				},{
					title:"管辖地区",width:400, field:"",
					formatter:function(value,rowData,rowIndex){
						return "<button class='btn btn-primary' onclick='roleinit.ChargeMgr("+rowData.id+",\""+rowData.roleName+"\")'>管辖地区</button>";
					}
				}]],
				toolbar:[{
						iconCls: 'icon-add',
						text:"新增角色",
						handler: function(){
							if(lastIndex == undefined){
								datagrid.datagrid("insertRow",{index:0,row:{isUse:true,id:-1}});
								datagrid.datagrid("beginEdit",0);
							}else{
								datagrid.datagrid("endEdit",lastIndex);
								datagrid.datagrid("insertRow",{index:0,row:{isUse:true,id:-1}});
								datagrid.datagrid("beginEdit",0);
							}						
						}
					},{
						iconCls: 'icon-add',
						text:"保存角色",
						handler: function(){
							if(lastIndex != undefined){
								datagrid.datagrid("endEdit",lastIndex);	
							}else{
								framework.alert("没有改变的项目");
							}
						}
					}],
				onBeforeLoad:function(params){
					params.roleName = $.trim($("#roleinit_roleName").val());
				},
				onDblClickRow : function(rowIndex,rowData){
					if(lastIndex != undefined){
						datagrid.datagrid("endEdit",lastIndex);
					}else{
						datagrid.datagrid("beginEdit",rowIndex);
					}
				},
				onBeforeEdit:function(rowIndex, rowData){
					lastIndex = rowIndex;
				},
				onAfterEdit:function(rowIndex, rowData, changes){
					if(lastIndex == undefined){
						return;
					}
					framework.startMask();
					lastIndex = undefined;
					if(rowData.id < 0){
						if(changes.roleName&&changes.roleName.length >= 4&&changes.roleName.length < 20){
							$.post(window.framework.dynUrl+"/user/addrole.htm",{roleName:changes.roleName,isUse:changes.isUse},function(data){
								if(data.success){
									datagrid.datagrid("reload");
								}else{
									window.framework.alert(data.errMsg||"操作失败");
									datagrid.datagrid("deleteRow",rowIndex);
								}
								framework.closeMask();
							},"json");
						}else{
							window.framework.dialog(null,"非法角色名，角色名长度4到20个字符");
							datagrid.datagrid("deleteRow",rowIndex);
							framework.closeMask();
						}
					}else{
						if(!$.isEmptyObject(changes)){
							$.post(window.framework.dynUrl+"/user/updaterole.htm",{id:rowData.id,roleName:changes.roleName||rowData.roleName,isUse:changes.isUse||rowData.isUse},function(data){
								if(data.success){
									datagrid.datagrid("reload");
								}else{
									window.framework.alert("操作失败");
									datagrid.datagrid("reload");
								}
								framework.closeMask();
							},"json");
						}else{
							framework.closeMask();
						}
					}
				},
				onCancelEdit:function(rowIndex, rowData){
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
		});
	})(jQuery);
</script>
</body>
</html>