<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<div class="easyui-accordion" style="width:100%;height:100%">
	<div title="solr管理">
		<button onclick="javascript:window.framework.addOtherTabs('http://cms.365020.com:9080/solr','solr')" type="button" class="btn btn-primary btn-xs btn-block">solr</button>
	</div>
	<div title="组件管理">
		<button onclick="javascript:window.framework.addTabs('${dynUrl}/programManage/initInterFaceTest.htm','查询接口')" type="button" class="btn btn-primary btn-xs btn-block">接口查询</button>
	</div>
	<div title="dao管理">
	</div>
	<div title="dto管理">
		
	</div>
	<div title="功能管理">
		<button onclick="javascript:window.framework.addTabs('${dynUrl}/function/search.htm','功能预览')" type="button" class="btn btn-primary btn-xs btn-block">功能预览</button>
	</div>
	<div title="服务管理">
		<button onclick="javascript:window.framework.addTabs('${dynUrl}/commons/initpage.htm?path=programManage|uploadexample','上传图片example')" type="button" class="btn btn-primary btn-xs btn-block">上传图片example</button>
	</div>
	<div title="工具类管理">
		
	</div>
</div>
