package net.hairband.pc.client.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

public class Constants {
	/**
	 * session保存的key
	 */
	public static final String SESSION_USER_INFO = "user";
	public static final String SESSION_USER_ID = "userId";
	public static final String SESSION_PROVINCEID = "provinceId";
	public static final String SESSION_CITYID = "cityId";
	public static final String SESSION_CITYCODE = "cityCode";
    public static final String SESSION_ORDER_OWNER = "order_owner";
	
	/**
	 * 活动类型
	 */
	public static final int SESSION_GROUPON = 30;
	public static final int SESSION_FLASHSALE = 25;
	
	/**
	 * 默认的省市（北京）
	 */
	public static final int DEFAULT_PROVINCEID = 2;
	public static final int DEFAULT_CITYID = 52;
	public static final String DEFAULT_CITYCODE = "111100";
	
	/**
	 * Cookie
	 */
	public static final String COOKIE_CART = "cartCookie"; //Cookie中的购物车信息id
	public static final String COOKIE_DOMAIN = "365020.com";
	public static final String COOKIE_LAST_USER = "lastUser";
	
	/**
	 * 商品分类id
	 */
	public static final int SC_ID = 4; //蔬菜集
	public static final int RL_ID = 3; //肉类集
	public static final int SG_ID = 17; //水果集
	public static final int LY_ID = 4; //粮油集
	
	/**
	 * 用户类型：1 普通用户   2  服务厅  
	 */
	public static final int USER_TPYE_COMMON = 1;   //普通用户
	public static final int USER_TPYE_PAVILION = 2; //服务厅 
	
	/**
	 * 图片验证key
	 * */
	public static final String IMG_VALIDATE = "IMG_VALIDATE";
	
	/**
	 * 手机验证码的位数
	 */
	public static final int MOBILE_CODE_COUNT = 6;
	
	/**
	 *送货方式
	 */
	public static final int DELIVERY_TYPE_SELF = 1; //到亭自提
	public static final int DELIVERY_TYPE_HOME = 2; //送货上门
	
	/**
	 * 支付方式
	 */
	public static final int PAY_TYPE_ALIPAY = 0;  //支付宝支付
	public static final int PAY_TYPE_ACCOUNT = 1; //余额支付
	public static final int PAY_TYPE_CARD = 2;    //银行卡支付
	
	/**
	 * 普通用户的地址个数
	 */
	public static final int ADDRESS_MAX_COUNT = 3;
	
	/**
	 * 排序
	 */
	public static final String ORDERBY_ASC = "asc";
	public static final String ORDERBY_DESC = "desc";
	
	/**
	 * 服务器路径
	 */
	public static final String HOST = "http://test.365020.com";
	public static final String IMGHOSTDEL ="http://img.365020.com:9080/imgupload/delete.htm";
	public static final String PATH = "/";
	public static final String SERVER_URL = HOST;
	public static final String SERVICE_URL = "http://localhost:8080/pc-servlet";
	public static final String SERVICE_URL_SHOPCART = SERVICE_URL + "/shopCartService.ser";
	public static final String SERVICE_URL_USER = SERVICE_URL + "/userService.ser";
	public static final String SERVICE_URL_USERADDRESS = SERVICE_URL + "/userAddressService.ser";
	public static final String SERVICE_URL_AREA = SERVICE_URL + "/areaService.ser";
	public static final String SERVICE_URL_GOODS = SERVICE_URL + "/goodsService.ser";
	public static final String SERVICE_URL_CATEGORY = SERVICE_URL + "/categoryService.ser";
	public static final String SERVICE_URL_ORDER = SERVICE_URL + "/orderService.ser";
	public static final String SERVICE_URL_AMOUNTLOG = SERVICE_URL + "/amountLogService.ser";
	public static final String SERVICE_URL_AMOUNTORDER = SERVICE_URL + "/amountOrderService.ser";
	public static final String SERVICE_URL_PAVILION = SERVICE_URL + "/pavilionInfoService.ser";
	public static final String SERVICE_URL_MYFAVORITE = SERVICE_URL + "/myFavoriteService.ser";
	public static final String SERVICE_URL_SMSSENDER = SERVICE_URL + "/smsSenderSvc.ser";
	public static final String SERVICE_URL_TOPN = SERVICE_URL + "/topNService.ser";
	public static final String SERVICE_URL_BROADCAST = SERVICE_URL + "/broadcastService.ser";
	public static final String SERVICE_URL_COUPON = SERVICE_URL + "/couponService.ser";
	public static final String SERVICE_URL_Dynpage = SERVICE_URL + "/dynpageService.ser";
	
	/**
	 * 我的手拉手中的菜单id，用于选中哪个菜单
	 */
	public static final String MENU_ID = "menu_id";
	public static final String MENU_MY_SLS = "my_sls_page";       //个人信息
	public static final String MENU_USER_INFO = "user_info";       //个人信息
	public static final String MENU_ACCOUNT_SAFE = "account_safe"; //账户安全
	public static final String MENU_USER_ADDRESS = "user_address"; //收货地址
	public static final String MENU_MY_FAVORITE = "my_favorite";   //我的关注
	public static final String MENU_MY_WALLET = "my_wallet";       //我的钱包
	public static final String MENU_ACCOUNT_RECHARGE = "account_recharge";//资金充值
	public static final String MENU_FEEDBACK = "feedback";  //意见反馈
	public static final String MENU_MY_ORDER = "my_order";  //我的订单
	public static final String MENU_DS_ORDER = "ds_order";  //代收订单
	public static final String MENU_DG_ORDER = "dg_order";  //代购订单
	public static final String MENU_P_ORDER = "p_order";  //代购订单
	public static final String MENU_USER_REGIST = "p_regist";       //个人信息
	public static final String MENU_MY_COUPON = "my_coupon";   //我的优惠券
	
	/**
	 * 支付宝api和参数
	 */
	public static final String ALIAPY_URL = "http://www.365020.com:8888/luoyang/alipayapi.jsp";
	//public static final String ALIAPY_URL = "http://www.365020.com/alipay/alipayapi.jsp";
	public static final String ALIAPY_PARAM_TRADENO = "WIDout_trade_no"; //订单号
	public static final String ALIAPY_PARAM_SUBJECT = "WIDsubject"; //订单名称
	public static final String ALIAPY_PARAM_TOTALFEE = "WIDtotal_fee"; //付款金额
	public static final String ALIAPY_PARAM_BODY = "WIDbody"; //订单描述
//	public static final String ALIAPY_PARAM_RETURNURL = "WIDreturn_url"; //支付宝返回的return_url
//	public static final String ALIAPY_PARAM_NOTIFYURL = "WIDnotify_url"; //支付宝返回的notify_url
//	public static final String ALIAPY_PARAM_RETURNURL_VALUE = Constants.SERVER_URL + "/alipay/return_url.htm";
//	public static final String ALIAPY_PARAM_NOTIFYURL_VALUE = Constants.SERVER_URL + "/alipay/notify_url.htm";
	
	/**
	 * 订单前缀，用于支付宝返回时区分充值和订单付款
	 */
	public static final String ALIAPY_PREFIX_SPLIT = "_";
	public static final String ALIAPY_PREFIX_ORDER = "ORDER_";
	public static final String ALIAPY_PREFIX_RECHARGE = "RECHARGE_";
	
	/**
	 * 商品列表每页显示的商品数
	 */
	public static final int GOODS_ACTIVITY_NUM_PAGE = 9;
	public static final int GOODS_NUM_PAGE = 12;
	public static final int ORDER_NUM_PAGE = 5;
	public static final int WALLETS_NUM_PAGE = 10;
	
	/**
	 * 将字符串由iso-8859-1转为UTF8编码，主要处理通过url传递中文字符串
	 * @param param
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String parseUTF8(String param) throws UnsupportedEncodingException{
		if(param != null && !param.trim().equals("")){
			CharsetEncoder encoder = Charset.forName("iso-8859-1").newEncoder();
			if(encoder.canEncode(param)){
				return new String(param.getBytes("iso-8859-1"),"UTF-8");
			}
		}
		return param;
	}
	
	/**
	 * 转义字符串中的html特殊字符
	 * @param str
	 * @return
	 */
	public static String escape4Html(String str){
		String escapeStr = "";
		for(int index=0; index<str.length(); index++ ){
			char c = str.charAt(index);
			switch(c){
				case '<':
					escapeStr = escapeStr + "&lt;";
					break;
				case '>':
					escapeStr = escapeStr + "&gt;";
					break;
				case '&':
					escapeStr = escapeStr + "&amp;";
					break;
//				case '"':
//					escapeStr = escapeStr + "\\\"";
//					break;	
				default:
					escapeStr = escapeStr + c;
			}
		}
		return escapeStr;
}
}
