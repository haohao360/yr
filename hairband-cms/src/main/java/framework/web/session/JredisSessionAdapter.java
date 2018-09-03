package framework.web.session;

import java.io.Serializable;

import framework.exception.ApplicationException;

public class JredisSessionAdapter extends AbstractSession{

	private static final ThreadLocal<Session> tl = new ThreadLocal<Session>();
	
	@Override
	public void set(Object key, Object value) {
		validate(key,value);
		if(getSession().getAttribute((String) key) != null){
			throw new ApplicationException("key is exist key="+(String)key);
		}
		getSession().setAttribute((String) key, value);
	}

	@Override
	public Object get(Object key) {
		validate(key,null);
		return getSession().getAttribute((String)key);
	}

	@Override
	public void delete(Object key) {
		validate(key,null);
		getSession().removeAttribute((String)key);
	}
	
	public static void setSession(Session session){
		if(tl.get() == null){
			tl.set(session);
		}else{
			throw new ApplicationException("set session into sessionadapter error session is exist");
		}
	}
	
	public static void destorySession(){
		tl.remove();
	}
	
	public static Session getSession(){
		return tl.get();
	}
	
	private void validate(Object key,Object value){
		if(!(key instanceof String)){
			throw new ClassCastException("cache key not cast String");
		}
		if(value != null&&!(value instanceof Serializable)){
			throw new ApplicationException("cache value must be implements Serializable");
		}
		if(tl.get() == null){
			throw new ApplicationException("JredisSession is disable");
		}
	}
	
	@Override
	public void replace(Object key,Object value){
		validate(key,value);
		getSession().setAttribute((String) key, value);
	}
	
}
