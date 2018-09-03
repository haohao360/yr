<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<div class="easyui-accordion" style="width:100%;height:100%">
	<div title="会员管理">
		<button onclick="javascript:window.framework.addTabs('${dynUrl}/member/search.htm','会员维护')" type="button" class="btn btn-primary btn-xs btn-block">会员维护</button>
	</div>
<%-- 	<div title="订单管理">
		<button onclick="javascript:window.framework.addTabs('${dynUrl}/order/search.htm','订单查询')" type="button" class="btn btn-primary btn-xs btn-block">订单查询</button>
		<button onclick="javascript:window.framework.addTabs('${dynUrl}/order/initexport.htm','导出订单')" type="button" class="btn btn-primary btn-xs btn-block">导出订单</button>
		<button onclick="javascript:window.framework.addTabs('${dynUrl}/order/backorder.htm','后台下单')" type="button" class="btn btn-primary btn-xs btn-block">后台下单</button>
		<button onclick="javascript:window.framework.addTabs('${dynUrl}/order/refund.htm','退换货')" type="button" class="btn btn-primary btn-xs btn-block">退换货</button>
	</div> --%>
	
	<div title="首页管理">
		<button onclick="javascript:window.framework.addTabs('${dynUrl}/idx/lightbox.htm','轮播图管理')" type="button" class="btn btn-primary btn-xs btn-block">轮播图管理</button>
		<%-- <button onclick="javascript:window.framework.addTabs('${dynUrl}/idx/topn.htm','人气商品(TopN)')" type="button" class="btn btn-primary btn-xs btn-block">人气商品</button> --%>
		<%-- <button onclick="javascript:window.framework.addTabs('${dynUrl}/dynpage/selpage.htm','查询首页活动')" type="button" class="btn btn-primary btn-xs btn-block">查询首页活动</button>
		<button onclick="javascript:window.framework.addTabs('${dynUrl}/dynpage/addpage.htm','新增首页活动')" type="button" class="btn btn-primary btn-xs btn-block">新增首页活动</button> --%>
	</div>
</div>
