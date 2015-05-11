package wuyi.service;

import java.util.List;

import wuyi.exception.DataException;
import wuyi.model.po.CustomerLevel;

public interface CustomerLevelService{
	
	public List<CustomerLevel> getAllCustomerLevel();
	
	public CustomerLevel createCustomerLevel(CustomerLevel customerLevel) throws Exception;
	
	public boolean deleteCustomerLevel(Long id) throws Exception;
	
	public CustomerLevel updateCustomerLevel(CustomerLevel customerLevel) throws Exception;
	
	public CustomerLevel getNextLevel(CustomerLevel customerLevel) throws DataException;
	
	public CustomerLevel getPreLevel(CustomerLevel customerLevel) throws DataException;
}
