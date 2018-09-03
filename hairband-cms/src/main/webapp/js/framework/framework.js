/**
 * 全局js
 * */
(function($){
	$(document).ready(function(event){
		$("#menu_container a").click(function(){
			var href = $(this).attr("href");
			if(href.lastIndexOf(".htm") == href.length-4){
				href = href + "?html=html"
			}else{
				href = href + "&html=html"
			}
			var target = $(this).attr("target");
			if(target != '_blank'){
				$.get(href,function(html){
					$.parser.parse($("#west_container").html(html));
				});
			}
		});
		
		$.ajaxSetup({
			cache:false,
			dataFilter:function(data,type){
				try{
					var json = jQuery.parseJSON(data);
					if(!json.success){
						if(json.errCode == 7){
							window.location.href = window.framework.rootUrl;
						}
					}
				}catch(e){
				}
				return data;
			}
		});
		var curLoading = undefined;
		var tabs = $("#center_tabs");
		tabs.tabs({
			onBeforeClose:function(title,index){
				
			},
			onLoad:function(panel){
				
			},
			onSelect:function(title,index){
				
			},
			onContextMenu:function(e,title){
				$("#tabs_menu").menu("show",{top:e.pageY,left:e.pageX});
				e.preventDefault();
				e.stopPropagation();
			}
		});
		window.framework={
			staUrl:$("#staUrl").val(),
			rootUrl:$("#rootUrl").val(),
			dynUrl:$("#dynUrl").val(),
		//	uploadUrl:"http://img.365020.com:9080/imgupload/img/upload.htm?upload_type=",//图片上传url
		//	uploadPro:"http://img.365020.com:9080/imgupload",
		//	imgUrl:"http://img.365020.com",//图片url
		//	detailUploadUrl:"http://img.365020.com:9080/imgupload/goodsdescript/uploadimg.htm?upload_type=",//详情页图片上传url
		//	detailUrl:"http://www.365020.com/goods/getGoodsInfo.htm?id=",//前端详情地址
			uploadUrl:"http://img.hairband.com:9080/imgupload/img/upload.htm?upload_type=",//图片上传url
			uploadPro:"http://img.hairband.com:9080/imgupload",
			imgUrl:"http://img.hairband.com",//图片url
			detailUploadUrl:"http://img.hairband.com:9080/imgupload/goodsdescript/uploadimg.htm?upload_type=",//详情页图片上传url
			detailUrl:"http://hairband.com/goods/getGoodsInfo.htm?id=",//前端详情地址
			areaCode:undefined,
			token:undefined,
			pageNum:20,
			addTabs:function(href,title){
				if(href.lastIndexOf(".htm") == href.length-4){
					href = href + "?html=html&parent=parent"
				}else{
					href = href + "&html=html&parent=parent"
				}
				var tabs_selected = tabs.tabs("getTab",title);
				if(!tabs_selected){
					tabs.tabs("add",{
						content:"<iframe src="+href+" style='width:100%;height:1000px' frameborder='no' border='0' framespacing='0'></iframe>",
						title:title,
						closable:true
					});
				}else{
					tabs.tabs("select",title);
				}
			},
			addOtherTabs:function(href,title){
				var tabs_selected = tabs.tabs("getTab",title);
				if(!tabs_selected){
					tabs.tabs("add",{
						content:"<iframe src="+href+" style='width:100%;height:1000px' frameborder='no' border='0' framespacing='0'></iframe>",
						title:title,
						closable:true
					});
				}else{
					tabs.tabs("select",title);
				}
			},
			closeTabs:function(title){
				tabs.tabs("close",title);
			},
			flushTabs:function(href,title){
				tabs.tabs("close",title);
				tabs.tabs("add",{
					content:"<iframe src="+href+" style='width:100%;height:1000px' frameborder='no' border='0' framespacing='0'></iframe>",
					title:title,
					closable:true
				});
			},
			removeAll:function(){
				jQuery("#center_tabs.easyui-tabs .tabs li .tabs-title").each(function(index,li){
					tabs.tabs("close",jQuery(li).text());
				});
			},
			openWindowTab:function(href){
				window.open(href);
			}
		};
	});
})(jQuery)