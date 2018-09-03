package util.framework;

import framework.exception.ApplicationException;
import framework.web.session.HttpSessionAdapter;
import framework.web.session.ISession;
import framework.web.session.JredisSessionAdapter;


/**
 *
 * 提供session功能
 * */
public class SessionUtil {

    private static ISession session = new JredisSessionAdapter();

    static{
        try{
            session.replace("ping","pang");
        }
        catch (Exception e){
            System.err.println("Using Http Session now...");
            session = new HttpSessionAdapter();
        }
    }


	/**
	 * 设置session缓存key,value
	 * 如果key已经存在抛applicationexception异常
	 * */
	public static void set(Object key, Object value) {
		session.set(key, value);
	}

	/**
	 * 通过key得到value 如果key不存在返回null
	 * 
	 * */
	public static Object get(Object key) {
		return session.get(key);
	}

	/**
	 * 根据key替换value 如果key不存在 新增
	 * */
	public static void replace(Object key, Object value) {
		session.replace(key, value);
	}
	
	
	/**
	 * 
	 * 根据key删除 value
	 * */
	public static void delete(Object key) {
		session.delete(key);
	}
	
	
}
