package framework.jredis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;
import framework.exception.ApplicationException;

public class DefaultJredis implements IJredis{

private Logger log = LoggerFactory.getLogger(DefaultJredis.class);
	
	private RedisTemplate<String, Object> redisTemplate;
	
	private StringRedisSerializer strSerializer = new StringRedisSerializer();
	
	private JdkSerializationRedisSerializer objSerializer = new JdkSerializationRedisSerializer();
	
	private JedisPoolConfig poolConfig;
	
	private String host;
	
	private Integer port;
	
	private int timeout = 10000;
	
	private boolean isStrict = false;
	
	@Override
	public void setValue(final String key, final Object obj) {
		init();
		try{
			redisTemplate.execute(new RedisCallback<Object>() {

				@Override
				public Object doInRedis(RedisConnection connection)
						throws DataAccessException {
					connection.set(strSerializer.serialize(key), objSerializer.serialize(obj));
					return null;
				}
			});
		}catch(Exception e){
			log.error("redis error",e);
			if(isStrict){
				throw new ApplicationException(e);
			}
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getValue(final String key, Class<T> clazz) {
		init();
		try{
			Object obj = redisTemplate.execute(new RedisCallback<Object>() {
				@Override
				public Object doInRedis(RedisConnection connection)
						throws DataAccessException {
					return objSerializer.deserialize(connection.get(strSerializer.serialize(key)));
				}
			});
			if(obj == null){
				return null;
			}
			return (T)obj;
		}catch(Exception e){
			log.error("redis error",e);
			if(isStrict){
				throw new ApplicationException(e);
			}
		}
		return null;
	}

	@Override
	public boolean isExist(final String key) {
		init();
		try{
			return redisTemplate.execute(new RedisCallback<Boolean>() {
				@Override
				public Boolean doInRedis(RedisConnection connection)
						throws DataAccessException {
					Boolean b = connection.exists(strSerializer.serialize(key));
					return b == null?false:b;
				}
			});
		}catch(Exception e){
			log.error("redis error",e);
			if(isStrict){
				throw new ApplicationException(e);
			}
		}
		return false;
	}

	@Override
	public boolean delete(final String key) {
		init();
		try{
			return redisTemplate.execute(new RedisCallback<Boolean>() {
				@Override
				public Boolean doInRedis(RedisConnection connection)
						throws DataAccessException {
					long l = connection.del(strSerializer.serialize(key));
					if(l > 0){
						return true;
					}
					return false;
				}
			});
		}catch(Exception e){
			log.error("redis error",e);
			if(isStrict){
				throw new ApplicationException(e);
			}
		}
		return false;
	}

	public JedisPoolConfig getPoolConfig() {
		return poolConfig;
	}

	public void setPoolConfig(JedisPoolConfig poolConfig) {
		this.poolConfig = poolConfig;
	}
	
	public void init(){
		if(this.redisTemplate == null){
			synchronized (this) {
				if(this.redisTemplate == null){
					JedisConnectionFactory factory = new JedisConnectionFactory();
					if(this.poolConfig != null){
						factory.setPoolConfig(this.poolConfig);
					}
					factory.setHostName(this.host);
					factory.setPort(this.port);
					factory.setTimeout(this.timeout);
					factory.afterPropertiesSet();
					this.redisTemplate = new RedisTemplate<String, Object>();
					this.redisTemplate.setConnectionFactory(factory);
					this.redisTemplate.afterPropertiesSet();
				}
			}
		}
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	@Override
	public void setValue(final String key, final Object obj,final long expire) {
		init();
		try{
			redisTemplate.execute(new RedisCallback<Object>() {
				@Override
				public Object doInRedis(RedisConnection connection)
						throws DataAccessException {
					connection.setEx(strSerializer.serialize(key), expire, objSerializer.serialize(obj));
					return null;
				}
			});
		}catch(Exception e){
			log.error("redis error",e);
			if(isStrict){
				throw new ApplicationException(e);
			}
		}
	}

	@Override
	public Object getValue(final String key) {
		init();
		try{
			return redisTemplate.execute(new RedisCallback<Object>() {
				@Override
				public Object doInRedis(RedisConnection connection)
						throws DataAccessException {
					return objSerializer.deserialize(connection.get(strSerializer.serialize(key)));
				}
			});
		}catch(Exception e){
			log.error("redis error",e);
			if(isStrict){
				throw new ApplicationException(e);
			}
		}
		return null;
	}

	@Override
	public void expireAt(final String key, final long expire) {
		init();
		try{
			redisTemplate.execute(new RedisCallback<Object>() {
				@Override
				public Object doInRedis(RedisConnection connection)
						throws DataAccessException {
					connection.expire(strSerializer.serialize(key),expire);
					return null;
				}
			});
		}catch(Exception e){
			log.error("redis error",e);
			if(isStrict){
				throw new ApplicationException(e);
			}
		}
		
	}

	public boolean isStrict() {
		return isStrict;
	}

	public void setStrict(boolean isStrict) {
		this.isStrict = isStrict;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
}
