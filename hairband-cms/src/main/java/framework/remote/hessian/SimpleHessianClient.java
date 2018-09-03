package framework.remote.hessian;

import java.net.MalformedURLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.caucho.hessian.client.HessianProxyFactory;

import framework.exception.ApplicationException;

public class SimpleHessianClient {
	
	private static Map<Class<?>,Object> remoteMap = new ConcurrentHashMap<Class<?>, Object>();

	@SuppressWarnings("unchecked")
	public <T> T findRemoteSer(String url,Class<T> clazz){
		Object obj = remoteMap.get(clazz);
		if(obj == null){
			synchronized (remoteMap) {
				obj = createRemoteObj(url,clazz);
			}
		}
		return (T) obj;
	}
	
	private Object createRemoteObj(String url,Class<?> clazz){
		Object obj = remoteMap.get(clazz);
		if(obj == null){
			HessianProxyFactory hpf = new HessianProxyFactory();
			hpf.setSerializerFactory(new ExtendSerializerFactory());
			try {
				obj = hpf.create(clazz, url);
			} catch (MalformedURLException e) {
				throw new ApplicationException("hessian client create failed class"+clazz.getName(),e);
			}
			remoteMap.put(clazz, obj);
		}
		return obj;
	}
	
}
