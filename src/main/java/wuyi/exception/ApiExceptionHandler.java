package wuyi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import wuyi.model.response.ErrorResponse;

@ControllerAdvice  
public class ApiExceptionHandler {  
  
    @ExceptionHandler(DataException.class)  
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)  
    @ResponseBody  
    public ErrorResponse handleInvalidDataError(DataException ex) {  
    	ErrorResponse er = new ErrorResponse();
    	er.setCode("123");
    	er.setMessage(ex.getMessage());
    	if(ex.getCause()!=null)
    		er.setDescription(ex.getCause().getMessage());
        return er;  
    }  
    
    @ExceptionHandler(WuyiException.class)  
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)  
    @ResponseBody  
    public ErrorResponse handleWuyiRequestError(WuyiException ex) {  
    	ErrorResponse er = new ErrorResponse();
    	er.setCode("222");
    	er.setMessage(ex.getMessage());
    	if(ex.getCause()!=null)
    		er.setDescription(ex.getCause().getMessage());
        return er; 
    }  
    
    @ExceptionHandler(RuntimeException.class)  
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)  
    @ResponseBody  
    public ErrorResponse handleUnexpectedServerError(RuntimeException ex) {  
    	ex.printStackTrace();
    	ErrorResponse er = new ErrorResponse();
    	er.setCode("333");
    	er.setMessage(ex.getMessage());
    	if(ex.getCause()!=null)
    		er.setDescription(ex.getCause().getMessage());
        return er; 
    }  
    
    @ExceptionHandler(Exception.class)  
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)  
    @ResponseBody  
    public String handleInteralServerError(Exception ex) {  
        return ex.getMessage();  
    } 
    
}  
