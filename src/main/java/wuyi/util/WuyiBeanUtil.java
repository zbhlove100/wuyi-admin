package wuyi.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class WuyiBeanUtil {

	public static String[] getNullPropertyNames (Object source) {
	    final BeanWrapper src = new BeanWrapperImpl(source);
	    java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

	    Set<String> emptyNames = new HashSet<String>();
	    for(java.beans.PropertyDescriptor pd : pds) {
	        Object srcValue = src.getPropertyValue(pd.getName());
	        if (srcValue == null) emptyNames.add(pd.getName());
	    }
	    String[] result = new String[emptyNames.size()];
	    return emptyNames.toArray(result);
	}

	// then use Spring BeanUtils to copy and ignore null
	public static void CopyPropertiesNotNull(Object src, Object target) {
	    BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
	}
	
	public static void copySpecPropertiesNotNull(
		    final Object source,
		    final Object target,
		    final Iterable<String> properties){

		    final BeanWrapper src = new BeanWrapperImpl(source);
		    final BeanWrapper trg = new BeanWrapperImpl(target);

		    String[] nullPropertyNames =  getNullPropertyNames(src);
			List nullPropertyList = Arrays.asList(nullPropertyNames);
			
		    for(final String propertyName : properties){
		    	if(!nullPropertyList.contains(propertyName)){
		    		trg.setPropertyValue(
		 		            propertyName,
		 		            src.getPropertyValue(propertyName)
		 		        );
		    	}
		    }

		}
	
	public static <T> void setPropertyNull(T entity,String[] params) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		for(String field:params){
			PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(entity.getClass(), field);
			pd.getWriteMethod().invoke(entity, new Object[]{null});
			
		}
	}
	
}
