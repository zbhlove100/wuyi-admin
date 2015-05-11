package wuyi.exception;

public class WuyiException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6898699339211461942L;
	
	public WuyiException(){
		super();
	}
	
	public WuyiException(String message, Throwable cause){
		super(String.format("Some interal error occur:%s", message), cause);
	}
	public WuyiException(Throwable cause){
		super(cause);
	}
	public WuyiException(String msg){
		super(String.format("Some interal error occur:%s", msg));
	}
}
