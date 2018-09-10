package framework.web;

import java.io.Serializable;

public class ResResult<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private T result;
	
    private boolean isSuccess = true;
	private long errCode;
	private String errMsg = "";
	
	public ResResult(){
		super();
	}
	
	public ResResult(T result){
		this.result = result;
	}
	
	public ResResult(long errCode){
		this.setSuccess(false);
		this.setErrCode(errCode);
	}
	
	public ResResult(long errCode,String message){
		this.setSuccess(false);
		this.setErrCode(errCode);
		this.setErrMsg(message);
	}
	
	public ResResult(String message){
		this.setSuccess(false);
		this.setErrCode(9999);
		this.setErrMsg(message);
	}
	
	
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public long getErrCode() {
		return errCode;
	}
	public void setErrCode(long errCode) {
		this.errCode = errCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

}
