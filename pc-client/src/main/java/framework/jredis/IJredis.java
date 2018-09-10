package framework.jredis;

public interface IJredis {

	
public void setValue(String key,Object obj);
	
	public void setValue(String key,Object obj,long expire);
	
	public Object getValue(String key);
	
	public <T> T getValue(String key,Class<T> clazz);
	
	public boolean isExist(String key);
	
	public boolean delete(String key);
	
	public void expireAt(String key,long expire);
	
}
