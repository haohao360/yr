package framework.exception;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class BusinessException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static ResourceBundle rb = ResourceBundle.getBundle("businessException");
	private Long errCode = 9999L;
	
	public static String getMessage(Long errCode,Object ...messages){
		if (errCode == null) {
			return null;
		}
		return MessageFormat.format(rb.getString(String.valueOf(errCode)), messages);
	}
	


	public BusinessException(String message){
		super(message);
	}
	
	public BusinessException(String message,Throwable t){
		super(message,t);
	}
	
	public BusinessException(Long errCode,Object ... messages){
		super(getMessage(errCode,messages));
		this.errCode = errCode;
	}
	
	public BusinessException(Long errCode,Throwable t,Object ...messages){
		super(getMessage(errCode,messages),t);
		this.errCode = errCode;
	}

	public Long getErrCode() {
		return errCode;
	}

	public void setErrCode(Long errCode) {
		this.errCode = errCode;
	}
	
	
}
