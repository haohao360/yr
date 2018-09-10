package util.framework;

public class StrUtil {

	public static String toString(Object obj){
		if(obj == null){
			return null;
		}
		return obj.toString().trim();
	}
}
