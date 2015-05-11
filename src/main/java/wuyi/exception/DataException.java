package wuyi.exception;

import java.io.Serializable;

public class DataException extends Exception implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1962081914828797482L;
	
	public DataException(){
		super();
	}
	
	public DataException(String message, Throwable cause){
		super(String.format("Some data error occur:%s", message), cause);
	}
	public DataException(Throwable cause){
		super(cause);
	}
	public DataException(String msg){
		super(String.format("Some data error occur:%s", msg));
	}

}
