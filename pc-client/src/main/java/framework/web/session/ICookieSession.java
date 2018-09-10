package framework.web.session;

public interface ICookieSession extends ISession{

	public void setC(String key,String value);
	
	public void setC(String key, String value, String domain, Integer expiry);
	
	public String getC(String key);
	
	public <T>T getC(String key,Class<T> clazz);
	
	public void replaceC(String key,String value);

	public void deleteC(String key);
	
	
}
