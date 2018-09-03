package framework.web.session;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import framework.exception.ApplicationException;

public class HttpSessionAdapter extends AbstractSession{

	private static final ThreadLocal<HttpSession> tl = new ThreadLocal<HttpSession>();
	
	public static void setHttpSession(HttpSession session){
		tl.set(session);
	}
	
	public static void destoryHttpSession(){
		tl.set(null);
	}

	@Override
	public void set(Object key, Object value) {
		validate(key,value);
		if(tl.get().getAttribute((String) key) != null){
			throw new ApplicationException("key is exist key="+(String)key);
		}
		tl.get().setAttribute((String)key, value);
	}

	@Override
	public Object get(Object key) {
		validate(key,null);
		return tl.get().getAttribute((String)key);
	}

	@Override
	public void delete(Object key) {
		validate(key,null);
		tl.get().removeAttribute((String)key);
	}
	
	@Override
	public void replace(Object key,Object value){
		validate(key,value);
		tl.get().setAttribute((String) key, value);
	}
	
	private void validate(Object key,Object value){
		if(!(key instanceof String)){
			throw new ClassCastException("cache key not cast String");
		}
		if(value != null&&!(value instanceof Serializable)){
			throw new ApplicationException("cache value must be implements Serializable");
		}
		if(tl.get() == null){
			throw new ApplicationException("HttpSession is disable");
		}
	}
}
