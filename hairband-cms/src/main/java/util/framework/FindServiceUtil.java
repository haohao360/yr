package util.framework;

import framework.context.ApplicationContext;
import framework.remote.hessian.SimpleHessianClient;


/**
 * @author 
 * <p>服务查找工具类，主要用于控制层查找服务</p>
 * 服务查找类
 * 
 * */
public class FindServiceUtil {
	
	private static SimpleHessianClient client = new SimpleHessianClient();

	/**
	 * 通过服务接口对象查找服务
	 * */
	public static <T> T findService(Class<T> clazz){
		return ApplicationContext.getApplicationContext().getBean(clazz);
	}
	
	/**
	 * 通过服务名称查找服务，class作为类型验证
	 * */
	public static <T> T findService(String name,Class<T> clazz){
		return ApplicationContext.getApplicationContext().getBean(name, clazz);
	}
	
	/**
	 * 通过服务名称查找服务
	 * */
	public static Object findService(String name){
		return ApplicationContext.getApplicationContext().getBean(name);
	}
	
	/**
	 * 
	 * 如果在spring context上下文中没有配置服务，使用这个方法可以创建远程接口
	 * 
	 * */
	public static <T> T findRemoteService(String url,Class<T> clazz){
		return client.findRemoteSer(url, clazz);
	}
	
}
