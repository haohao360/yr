package framework.exception;

public class ApplicationException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7635069169630665522L;

	public ApplicationException(){
		super();
	}
	
	public ApplicationException(Throwable t){
		super(t);
	}
	
	public ApplicationException(String message){
		super(message);
	}
	
	public ApplicationException(String message,Throwable t){
		super(message,t);
	}
}
