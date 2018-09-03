package util.framework;

import framework.exception.BusinessException;

public class BusinessExceptionUtil {

	public static BusinessException createBusinessException(String message){
		return new BusinessException(message);
	}
	
	public static BusinessException createBusinessException(Long errCode,Object ... messages){
		return new BusinessException(errCode, messages);
	}
	
	public static BusinessException createBusinessException(String message,Throwable t){
		return new BusinessException(message,t);
	}
	
	public static BusinessException createBusinessException(Long errCode,Throwable t,Object ...messages){
		return new BusinessException(errCode,t,messages);
	}
	
}
