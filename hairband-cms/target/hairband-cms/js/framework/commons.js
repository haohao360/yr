/**
 * 
 */

(function($){
	Date.prototype.formatDate=function(fmt) {           
	    var o = {           
	    "M+" : this.getMonth()+1, //月份           
	    "d+" : this.getDate(), //日           
	    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时           
	    "H+" : this.getHours(), //小时           
	    "m+" : this.getMinutes(), //分           
	    "s+" : this.getSeconds(), //秒           
	    "q+" : Math.floor((this.getMonth()+3)/3), //季度           
	    "S" : this.getMilliseconds() //毫秒           
	    };           
	    var week = {           
	    "0" : "/u65e5",           
	    "1" : "/u4e00",           
	    "2" : "/u4e8c",           
	    "3" : "/u4e09",           
	    "4" : "/u56db",           
	    "5" : "/u4e94",           
	    "6" : "/u516d"          
	    };           
	    if(/(y+)/.test(fmt)){           
	        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));           
	    }           
	    if(/(E+)/.test(fmt)){           
	        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);           
	    }           
	    for(var k in o){           
	        if(new RegExp("("+ k +")").test(fmt)){           
	            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));           
	        }           
	    }           
	    return fmt;           
	} 
	var loading = new ol.loading();//初始化对象
	window.framework = {};
	$.extend(window.framework,window.parent.framework);
	$.extend(window.framework,{
		dialog:function(data,msg){
			try{
				data = jQuery.parseJSON(data);
				if(data.success){
					$.messager.alert('',msg||"操作成功");
				}else{
					$.messager.alert('',data.errMsg||msg);
				}
			}catch(e){
				$.messager.alert('',msg||data.errMsg);
			}
			
		},
		alert:function(message){
			$.messager.alert('',message);
		},
		startMask:function(){
			$.messager.progress({
		        msg: '数据处理中...'
		    }); 
		},
		closeMask:function(){
			 $.messager.progress('close');
		},
		confirm:function(message,fun){
			if($.isFunction(message)){
				$.messager.confirm("","",message);
			}else{
				$.messager.confirm("",message,fun);
			}
		},
		parseJSON:function(data){
			try{
				data = jQuery.parseJSON(data);
			}catch(e){
				
			}
			return data;
		},
		encodeUri:function(obj){
			return jQuery.param(obj);
		},
		areaFilter:function(id,len){
			var code = framework.areaCode;
			if(code&&code.length > len){
				code = framework.areaCode.substring(0,len);
			}
			var data = framework.area_pmap[id];
			var r = [];
			if(data){
				jQuery.each(data,function(index,d){
					var obj = {k:d.name,v:d.id};
					if(!len){
						r.push(obj);
					}else if(d.code.indexOf(code) == 0){
						r.push(obj);
					}
				});
			}
			return r;
		},
		htmlEncode:function(s){
			var div = document.createElement('div');  
		    div.appendChild(document.createTextNode(s));  
		    return div.innerHTML;
		},
		actEnum:{
			30:"团购",
			25:"限时抢购"
		}
	});
	
	$.ajaxSetup({
		cache:false,
		dataFilter:function(data,type){
			try{
				var json = jQuery.parseJSON(data);
				if(!json.success){
					if(json.errCode == 7){
						window.parent.location.href = window.framework.rootUrl;
					}
				}
			}catch(e){
			}
			return data;
		},
		error:function(xhq, ts, et){
			framework.closeMask();
		}
	});
	
	//扩展验证功能
    $.extend($.fn.validatebox.defaults.rules, {
        mobile: {
        	validator: function(value, param){
        		return value.length == 11;
        },
        	message: '请输入正确手机号'
        }
    });
    
   //自定义验证
  $.extend($.fn.validatebox.defaults.rules, {
	  phoneRex: {
	    validator: function(value){
	    var rex=/^1[3|5|7|8]+\d{9}$/;
	    var rex2=/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
	    if(rex.test(value)||rex2.test(value))
	    {
	      // alert('t'+value);
	      return true;
	    }else
	    {
	     //alert('false '+value);
	       return false;
	    }
	      
	    },
	    message: '请输入正确手机格式'
	  }
  });
  
  //把form表单序列化成对象
  jQuery.fn.serializeObj = function(){
	  var param = {};
	  jQuery.each(this.serializeArray(),function(i,obj){
		  param[obj.name] = obj.value;
	  });
	  return param;
  }
})(jQuery);

$(function() {
	$('.easyui-combobox').each(function(){
		var obj = $(this);
		var options = obj.combobox('options');
		if(options.mode == 'local'){
			obj.next('.combo').find('.textbox-text').bind('blur',function(){
				var value = obj.combobox('getValue');
				var data = obj.combobox('getData');
				if(data&&data.length == 0){
					
					obj.combobox("clear");
				}
				if(!value){
					obj.combobox("clear");		
				}
			});
		}else{
			obj.next('.combo').find('.textbox-text').bind('blur',function(){
				var value = obj.combobox('getValue');
				var text = obj.combobox('getText');
				var data = obj.combobox('getData');
				if(value == text){
					obj.combobox("clear");
				}
				if(!value){
					obj.combobox("clear");		
				}
			});
		}
	});
});