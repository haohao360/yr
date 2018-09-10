package framework.web.session;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;

import framework.exception.ApplicationException;

public class CookieSessionAdapter extends JredisSessionAdapter implements ICookieSession{
	
	private static final ThreadLocal<Map<String,Cookie>> cookies = new ThreadLocal<Map<String,Cookie>>();
	private static final ThreadLocal<Boolean> isFlush = new ThreadLocal<Boolean>();
	
	public static void setCookieSession(Cookie[] cookies){
		CookieSessionAdapter.isFlush.set(false);
		if(CookieSessionAdapter.cookies.get() == null){
			Map<String,Cookie> map = new HashMap<String,Cookie>();
			if(cookies != null&&cookies.length > 0){
				for(Cookie cookie:cookies){
					map.put(cookie.getName(), cookie);
				}
			}
			CookieSessionAdapter.cookies.set(map);
		}else{
			throw new ApplicationException("set cookiesession into  error session is exist");
		}
	}
	
	public static Cookie[] getCookieSession(){
		Map<String,Cookie> map = CookieSessionAdapter.cookies.get();
		return map.values().toArray(new Cookie[map.size()]);
	}
	
	public static void destoryCookieSession(){
		CookieSessionAdapter.isFlush.remove();
		CookieSessionAdapter.cookies.remove();
	}

	@Override
	public void setC(String key, String value) {
		setC(key,value,null,null);
	}

	@Override
	public String getC(String key) {
		Cookie cookie = CookieSessionAdapter.cookies.get().get(key);
		if(cookie != null){
			return cookie.getValue();
		}
		return null;
	}

	@Override
	public void replaceC(String key, String value) {
		setC(key,value);
	}

	@Override
	public void deleteC(String key) {
		Cookie cookie = CookieSessionAdapter.cookies.get().get(key);
		if(cookie != null){
			cookie.setMaxAge(0);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getC(String key, Class<T> clazz) {
		String value = getC(key);
		if(value == null){
			return null;
		}
		if(String.class.equals(clazz)){
			return (T) value;
		}
		if(boolean.class.equals(clazz)||Boolean.class.equals(clazz)){
			return (T) Boolean.valueOf(value);
		}
		if(double.class.equals(clazz)||Double.class.equals(clazz)){
			return (T) Double.valueOf(value);
		}
		if(float.class.equals(clazz)||Float.class.equals(clazz)){
			return (T) Float.valueOf(value);
		}
		if(int.class.equals(clazz)||Integer.class.equals(clazz)){
			return (T) Integer.valueOf(value);
		}
		if(long.class.equals(clazz)||Long.class.equals(clazz)){
			return (T) Long.valueOf(value);
		}
		throw new ApplicationException("value support only String Integer Float Double Long long double float int");
	}

	@Override
	public void setC(String key, String value, String domain, Integer expiry) {
		Cookie cookie = new Cookie(key, value);
		if(domain != null){
			cookie.setDomain(domain);
		}
		cookie.setHttpOnly(true);
		if(expiry != null){
			cookie.setMaxAge(expiry);
		}
		CookieSessionAdapter.cookies.get().put(key, cookie);
		CookieSessionAdapter.isFlush.set(true);
	}

}
