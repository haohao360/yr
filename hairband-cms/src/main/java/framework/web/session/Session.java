package framework.web.session;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import framework.exception.ApplicationException;

public class Session implements Serializable{

	private static final long serialVersionUID = 1L;
	private boolean isFlush;
	public Map<String,Object> attribute = new HashMap<String,Object>();
	private String token;
	public boolean isFlush() {
		return isFlush;
	}
	public void setFlush(boolean isFlush) {
		this.isFlush = isFlush;
	}
	public Object getAttribute(String key) {
		return this.attribute.get(key);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String key,Class<T> clazz) {
		Object obj = this.attribute.get(key);
		if(obj == null){
			return null;
		}
		return (T)obj;
	}
	
	public void setAttribute(String key,Object value) {
		this.isFlush = true;
		if(!(value instanceof Serializable)){
			throw new ApplicationException("session attribute is not implements Serializable class:"+value.getClass().getName());
		}
		this.attribute.put(key, value);
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.isFlush = true;
		this.token = token;
	}
	
	public void removeAttribute(String key){
		this.attribute.remove(key);
	}
	
}
