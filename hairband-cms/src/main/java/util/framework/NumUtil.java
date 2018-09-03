package util.framework;

public class NumUtil {

	public static Integer toInteger(Object obj){
		if(obj == null){
			return null;
		}
		if(obj instanceof Integer){
			return (Integer)obj;
		}
		return Integer.parseInt(obj.toString().trim());
	}
	
	public static Float toFloat(Object obj){
		if(obj == null){
			return null;
		}
		if(obj instanceof Float){
			return (Float)obj;
		}
		return Float.parseFloat(obj.toString().trim());
	}
	
	public static Double toDouble(Object obj){
		if(obj == null){
			return null;
		}
		if(obj instanceof Double){
			return (Double)obj;
		}
		return Double.parseDouble(obj.toString().trim());
	}
}
