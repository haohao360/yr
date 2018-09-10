package framework.web;

public class ResBo <T> extends SuperRes{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private T result;
	
	public ResBo(){
		super();
	}
	
	public ResBo(T result){
		this.setResult(result);
	}
	
	public ResBo(long errCode){
		super(errCode);
	}
	
	public ResBo(long errCode,Object ...messages){
		super(errCode,messages);
	}
	
	public ResBo(String message){
		super(message);
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
}
