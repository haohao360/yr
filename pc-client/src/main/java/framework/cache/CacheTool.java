package framework.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import framework.exception.ApplicationException;
import framework.jredis.IJredis;

public class CacheTool implements Cache{

private String name;
	
	private int expire = 10;
	
	private IJredis redis;

	@Override
	public void clear() {
		validate();
	}

	@Override
	public void evict(Object key) {
		validate();
		if(key != null){
			redis.delete(key.toString());
		}
	}

	@Override
	public ValueWrapper get(Object key) {
		validate();
		if(key == null){
			return null;
		}
		Object obj = redis.getValue(key.toString());
		if(obj == null){
			return null;
		}
		return new SimpleValueWrapper(obj);
	}

	@Override
	public <T> T get(Object key, Class<T> type) {
		validate();
		if(key == null){
			return null;
		}
		return redis.getValue(key.toString(), type);
	}

	@Override
	public String getName() {
		validate();
		return this.name;
	}
	
	public void setName(String name){
		validate();
		this.name = name;
	}

	@Override
	public Object getNativeCache() {
		validate();
		return redis;
	}

	@Override
	public void put(Object key, Object value) {
		validate();
		if(key != null){
			redis.setValue(key.toString(), value, expire);
		}
	}

	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}

	public IJredis getRedis() {
		return redis;
	}

	public void setRedis(IJredis redis) {
		this.redis = redis;
	}
	
	private void validate(){
		if(this.redis == null){
			throw new ApplicationException("property redis is not exist");
		}
	}
}
