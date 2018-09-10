package framework.web.session;

public interface ISession {
	
	public void set(Object key,Object value);
	
	public Object get(Object key);
	
	public void replace(Object key,Object value);

	public void delete(Object key);
}
