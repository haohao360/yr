package util.json;

import java.io.ByteArrayOutputStream;
import com.fasterxml.jackson.databind.ObjectMapper;

import framework.exception.ApplicationException;

public class JsonUtil {

	private static final ObjectMapper om = new ObjectMapper();
	
	public static <T> T parseJson(String json,Class<T> clazz){
		try {
			return om.readValue(json, clazz);
		} catch (Exception e) {
			throw new ApplicationException("parse json err",e);
		}
	}
	
	public static String parseObject(Object obj){
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			om.writeValue(out, obj);
		} catch (Exception e) {
			throw new ApplicationException("parse object err",e);
		}
		return out.toString();
	}
	
	
}
