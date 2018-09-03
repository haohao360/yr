<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<div class="easyui-accordion" style="width:100%;height:100%">
	<div title="cms用户管理">
		<button onclick="javascript:window.framework.addTabs('${dynUrl}/user/initupdatepass.htm','修改密码')" type="button" class="btn btn-primary btn-xs btn-block">修改密码</button>
		<button onclick="javascript:window.framework.addTabs('${dynUrl}/user/initgetcms.htm','查询后台用户')" type="button" class="btn btn-primary btn-xs btn-block">查询后台用户</button>
		<button onclick="javascript:window.framework.addTabs('${dynUrl}/user/addinit.htm','新增后台用户')" type="button" class="btn btn-primary btn-xs btn-block">新增后台用户</button>
		<button onclick="javascript:window.framework.addTabs('${dynUrl}/role/roleinit.htm','角色管理')" type="button" class="btn btn-primary btn-xs btn-block">角色管理</button>
		<button onclick="javascript:window.framework.addTabs('${dynUrl}/area/initarea.htm','省市区管理')" type="button" class="btn btn-primary btn-xs btn-block">省市区管理</button>
		<button onclick="javascript:window.framework.addTabs('${dynUrl}/commons/imgmanager.htm','图片综合管理')" type="button" class="btn btn-primary btn-xs btn-block">图片综合管理</button>
	</div>
<%-- 	<div title="活动管理">
		<div class="easyui-accordion" style="width:100%;height:100%">
			<div title="详情页tip">
				<button onclick="javascript:window.framework.addTabs('${dynUrl}/detailtip/init.htm','详情页tip')" type="button" class="btn btn-primary btn-xs btn-block">详情页tip</button>
				<button onclick="javascript:window.framework.addTabs('${dynUrl}/detailtip/sel.htm','查询详情页tip')" type="button" class="btn btn-primary btn-xs btn-block">查询详情页tip</button>
			</div>
			<div title="团购/限时活动">
				<button onclick="javascript:window.framework.addTabs('${dynUrl}/tuan/add_page.htm','新建团购/限时活动')" type="button" class="btn btn-primary btn-xs btn-block">新建团购/限时活动</button>
				<button onclick="javascript:window.framework.addTabs('${dynUrl}/tuan/sel_page.htm','查询团购/限时活动')" type="button" class="btn btn-primary btn-xs btn-block">查询团购/限时活动</button>
				<button onclick="javascript:window.framework.addTabs('${dynUrl}/tuan/approval_page.htm','审批团购/限时活动')" type="button" class="btn btn-primary btn-xs btn-block">审批团购/限时活动</button>
			</div>
		</div>
	</div> --%>
	<div title="商品管理">
		<button onclick="javascript:window.framework.addTabs('${dynUrl}/goods/addinit.htm','商品录入')" type="button" class="btn btn-primary btn-xs btn-block">商品录入</button>
		<button onclick="javascript:window.framework.addTabs('${dynUrl}/goods/select.htm','查询商品')" type="button" class="btn btn-primary btn-xs btn-block">查询商品</button>
		<button onclick="javascript:window.framework.addTabs('${dynUrl}/goodsStock/init.htm','商品库存管理')" type="button" class="btn btn-primary btn-xs btn-block">商品库存管理</button>
	</div>
	<%-- <div title="评论管理">
		<button onclick="javascript:window.framework.addTabs('${dynUrl}/goodsComment/selectComments.htm','评论审核')" type="button" class="btn btn-primary btn-xs btn-block">审核评论</button>
	</div> --%>
	<div title="品类管理">
		<button onclick="javascript:window.framework.addTabs('${dynUrl}/commons/initpage.htm?path=category|selectCategory','查询品类')" type="button" class="btn btn-primary btn-xs btn-block">查询品类</button>
		<button onclick="javascript:window.framework.addTabs('${dynUrl}/commons/initpage.htm?path=category|selectGoodsCategory','查询商品品类关系')" type="button" class="btn btn-primary btn-xs btn-block">查询商品品类关系</button>
	</div>
	<%-- <div title="供应商管理">
		<button onclick="javascript:window.framework.addTabs('${dynUrl}/commons/initpage.htm?path=provider|selectProvider','查询供应商')" type="button" class="btn btn-primary btn-xs btn-block">查询供应商</button>
		<button onclick="javascript:window.framework.addTabs('${dynUrl}/provider/addinit.htm','增加供应商')" type="button" class="btn btn-primary btn-xs btn-block">新增供应商</button>
	</div> --%>
	<%-- <div title="品牌管理">
		<button onclick="javascript:window.framework.addTabs('${dynUrl}/brand/select.htm','查询品牌')" type="button" class="btn btn-primary btn-xs btn-block">查询品牌</button>
		<button onclick="javascript:window.framework.addTabs('${dynUrl}/brand/addinit.htm','增加品牌')" type="button" class="btn btn-primary btn-xs btn-block">增加品牌</button>
	</div> --%>
	<%-- <div title="亭子管理">
		<button onclick="javascript:window.framework.addTabs('${dynUrl}/pavilionInfo/select.htm','查询服务亭')" type="button" class="btn btn-primary btn-xs btn-block">查询服务亭</button>
		<button onclick="javascript:window.framework.addTabs('${dynUrl}/pavilionInfo/circum.htm','服务亭周边管理')" type="button" class="btn btn-primary btn-xs btn-block">服务亭周边管理</button>
	</div> --%>
	<div title="字典管理">
		<button onclick="javascript:window.framework.addTabs('${dynUrl}/commons/initpage.htm?path=dict|selectDict','查询字典')" type="button" class="btn btn-primary btn-xs btn-block">查询字典</button>
		<button onclick="javascript:window.framework.addTabs('${dynUrl}/dict/addinit.htm','增加字典')" type="button" class="btn btn-primary btn-xs btn-block">新增字典</button>
	</div>
	<%-- <div title="预付卡管理">
		<button onclick="javascript:window.framework.addTabs('${dynUrl}/ppc/getGen.htm','生成预卡')" type="button" class="btn btn-primary btn-xs btn-block">生成预付卡</button>
		<button onclick="javascript:window.framework.addTabs('${dynUrl}/ppc/getChk.htm','审核')" type="button" class="btn btn-primary btn-xs btn-block">审核</button>
		<button onclick="javascript:window.framework.addTabs('${dynUrl}/ppc/getQry.htm','查询')" type="button" class="btn btn-primary btn-xs btn-block">查询</button>
		<button onclick="javascript:window.framework.addTabs('${dynUrl}/ppc/statistics.htm','统计')" type="button" class="btn btn-primary btn-xs btn-block">统计</button>
	</div> --%>
   <%--  <div title="优惠券管理">
        <button onclick="javascript:window.framework.addTabs('${dynUrl}/coupon/add.htm','新增优惠券')" type="button" class="btn btn-primary btn-xs btn-block">新增优惠券</button>
        <button onclick="javascript:window.framework.addTabs('${dynUrl}/coupon/qry.htm','查询优惠券')" type="button" class="btn btn-primary btn-xs btn-block">查询及导出</button>
		<button onclick="javascript:window.framework.addTabs('${dynUrl}/coupon/export.htm','线下优惠券')" type="button" class="btn btn-primary btn-xs btn-block">线下优惠券</button>button
    </div> --%>
</div>
