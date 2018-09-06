package net.hairband.commons.businessconstant;

public class BusinessContant {
	/**
	 * 
	 * 用户常量
	 * 
	 * */
	public static final String USER = "const_user";
	
	public static final String COOKIE_CART = "cartCookie";
	/**
	 * 
	 * 订单状态 状态 1 未确认 2 确认 3 处理中 4 已发货 5 确认收货 6 退货中 7 退货完成
	 * */
	public static final int ORDERSTATUS_1 = 1;
	public static final int ORDERSTATUS_2 = 2;
	public static final int ORDERSTATUS_3 = 3;
	public static final int ORDERSTATUS_4 = 4;
	public static final int ORDERSTATUS_5 = 5;
	public static final int ORDERSTATUS_6 = 6;
	public static final int ORDERSTATUS_7 = 7;
	/**
	 * 
	 * 订单支付状态 状态 1未付款 2 已付款
	 * */
	public static final int ORDERISPAID_1 = 1;
	public static final int ORDERISPAID_2 = 2;
	/**
	 * 用户收货地址是否正常 1 正常 127 删除
	 */
	public static final int USERADDRESS_ISDEL_NORMAL = 1;
	public static final int USERADDRESS_ISDEL_DEL = 127;
	/**
	 * 用户是否默认 0 否 1 默认
	 */
	public static final int USERADDRESS_ISDEFAULT_NO = 0;
	public static final int USERADDRESS_ISDEFAULT_YES = 1;
	/**
	 * 分隔符
	 */
	public static final String SEPARATOR_S = "~";
	public static final String SEPARATOR_E = "^";
	public static final String SEPARATOR_3 = "#";
	/**
	 * 购物车最大数量
	 */
	public static final int SHOPCART_MAX = 30;
	/**
	 * 排序
	 */
	public static final String ORDERBY_ASC = "asc";
	public static final String ORDERBY_DESC = "desc";

	/**
	 * 
	 * 商品限购范围
	 * */

	public static final int GOODLIMITRANGE_NO = 0;
	public static final int GOODLIMITRANGE_PROVINCE = 1;
	public static final int GOODLIMITRANGE_CITY = 2;
	public static final int GOODLIMITRANGE_DISTRICT = 3;
	public static final int GOODLIMITRANGE_COMMUNITY = 4;
	public static final int GOODLIMITRANGE_PAVILION = 5;
	
	public static final int AMOUNTORDER_SUCCESS = 1; // 成功
	public static final int AMOUNTORDER_FAILED = 0; // 未成功
	/**
	 * 充值卡状态
	 */
	public static final Byte RECHARGECARD_CARDSTATUS_DISABLE = 3;
	public static final String AMOUNTLOG_CARDREMARK = "充值卡";
public static final String AMOUNTLOG_LINE = "-" ;  
}
