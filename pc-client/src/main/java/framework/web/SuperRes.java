package framework.web;

import java.io.Serializable;

import framework.exception.BusinessException;

/**
 * 
 * 统一控制层ajax请求结果集，便于全局做处理
 * ajax控制层请求需继承此类
 * 
 * */
public class SuperRes implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private boolean isSuccess = true;
	private long errCode;
	private String errMsg = "";
	
	public SuperRes(){
		super();
	}
	
	public SuperRes(long errCode){
		this.setSuccess(false);
		this.setErrCode(errCode);
		this.setErrMsg(BusinessException.getMessage(errCode));
	}
	
	public SuperRes(long errCode,Object ...messages){
		this.setSuccess(false);
		this.setErrCode(errCode);
		this.setErrMsg(BusinessException.getMessage(errCode,messages));
	}
	
	public SuperRes(String message){
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
	
}
