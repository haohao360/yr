package util.framework;

public class BASE64Util {
	
	@SuppressWarnings("restriction")
	public static String encryptBASE64(byte[] key) throws Exception{
		 return (new sun.misc.BASE64Encoder()).encodeBuffer(key);  
	}
	
	@SuppressWarnings("restriction")
	public static  String decryptBASE64(String s ) {		
		if (s == null) return null;		
		try {
			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
			byte[] b = decoder.decodeBuffer(s);
			return new String(b);
		} catch (Exception e) {
			return null;
		}		
	}
}
