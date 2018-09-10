package framework.cache;

import java.lang.reflect.Method;
import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class KeyGenerator implements org.springframework.cache.interceptor.KeyGenerator{

	private static ObjectMapper om = new ObjectMapper();
	private static Logger log = LoggerFactory.getLogger(KeyGenerator.class);

	@Override
	public Object generate(Object target, Method method, Object... params) {
		String key = null;
		StringBuilder sb = new StringBuilder(target.getClass().getName());
		sb.append("_");
		sb.append(method.getName());
		sb.append("_");
		try {
			sb.append(om.writeValueAsString(params));
			key = getMD5Str(sb.toString());
		} catch (Exception e) {
			log.error("cache key error:" + sb.toString(), e);
		}
		return key;
	}

	private static String getMD5Str(String strs) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = strs.getBytes();
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			log.error("cache key md5 error" + strs, e);
		}
		return null;
	}
}
