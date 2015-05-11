package wuyi.util.validator;

import java.lang.reflect.InvocationTargetException;

import wuyi.exception.DataException;

public class WuyiValidator<T> {
	protected Class<T> validateClass;

	public WuyiValidator(final Class<T> validateClass) {
        this.validateClass = validateClass;
    }
	public void validateNoNull(T entity,String[] params) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, DataException{
		boolean result = true;
		StringBuffer misparams = new StringBuffer("Required field miss:");
		for(String field:params){
			String first = field.substring(0,1);
			String left = field.substring(1);
			String methodName = String.format("get%s%s", first.toUpperCase(),left);
			Object methodresult = entity.getClass().getMethod(methodName, new Class[]{}).invoke(entity, new Object[]{});
			if(null == methodresult ){
				misparams.append(field);
				misparams.append("|");
				result=false;
			}
				
			
		}
		if(!result){
			throw new DataException(misparams.toString());
		}
	}
	
	public void validateNoAllNull(T entity,String[] params) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, DataException{
		boolean result = false;
		StringBuffer misparams = new StringBuffer("Fields can't all null:");
		if(params.length == 0)
			result = true;
		
		for(String field:params){
			String first = field.substring(0,1);
			String left = field.substring(1);
			String methodName = String.format("get%s%s", first.toUpperCase(),left);
			Object methodresult = entity.getClass().getMethod(methodName, new Class[]{}).invoke(entity, new Object[]{});
			misparams.append(field);
			misparams.append("|");
			if(null != methodresult ){
				result=true;
			}
				
			
		}
		if(!result){
			if(!result){
				throw new DataException(misparams.toString());
			}
		}
	}
	
}
