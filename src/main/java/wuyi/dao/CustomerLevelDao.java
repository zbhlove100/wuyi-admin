package wuyi.dao;

import java.util.List;

import wuyi.model.po.CustomerLevel;

public interface CustomerLevelDao extends MyGenericDao<CustomerLevel, Long>{
	
	public CustomerLevel getCustomerLevel(int level) throws Exception;
	
	public List<CustomerLevel> getAllCustomerLevels();
	
}
