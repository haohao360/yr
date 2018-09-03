package util.httpclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import util.json.JsonUtil;
import framework.exception.ApplicationException;


public class HttpRequestUtil {
	
	public static final String GET = "GET";
	public static final String POST = "POST";
	/**
	 * 
	 * 该方法主要使用与发送短信，联合登录等通过http请求与第三方通信功能
	 * 
	 * */
	public static String sendHttpRequest(String url,Map<String,String> params,String type){
		CloseableHttpClient client = HttpClients.createDefault();
		HttpEntity entity = null;
		try{
			if(GET.equals(type)){
				HttpGet httpGet = new HttpGet(url);
				entity = client.execute(httpGet).getEntity();
			}else{
				HttpPost httpPost = new HttpPost(url);
				if(params != null&&params.size() > 0){
					List<NameValuePair> list = new ArrayList<NameValuePair>();
					for(String key:params.keySet()){
						BasicNameValuePair pair = new BasicNameValuePair(key,params.get(key));
						list.add(pair);
					}
					UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(list, "utf-8");
					httpPost.setEntity(formEntity);
				}
				entity = client.execute(httpPost).getEntity();
			}
			String result = entity != null?EntityUtils.toString(entity):null;
			return result;
		}catch(Exception e){
			throw new ApplicationException("inner http request err url="+url,e);
		}finally{
			try {
				EntityUtils.consume(entity);
			} catch (IOException e) {
				throw new ApplicationException(e);
			}
		}
	}
	
	/**
	 * request to get 
	 * */
	public static Object sendHttpGetRequest(String url){
		String result = sendHttpRequest(url,null,GET);
		return JsonUtil.parseJson(result, Object.class);
	}
	/**
	 * 
	 * */
	public static <T> T sendHttpGetRequest(String url, Class<T> clazz){
		String result = sendHttpRequest(url,null,GET);
		return JsonUtil.parseJson(result, clazz);
	}
	/**
	 * 
	 * */
	public static Object sendHttpPostRequest(String url, Map<String,String> params){
		String result = sendHttpRequest(url,null,POST);
		return JsonUtil.parseJson(result, Object.class);
	}
	/**
	 * 
	 * */
	public static <T> T sendHttpPostRequest(String url, Map<String,String> params, Class<T> clazz){
		String result = sendHttpRequest(url,null,POST);
		return JsonUtil.parseJson(result, clazz);
	}
	
}
