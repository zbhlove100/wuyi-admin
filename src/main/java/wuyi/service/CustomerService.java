package wuyi.service;

import java.util.Date;

import wuyi.exception.DataException;
import wuyi.model.po.Customer;
import wuyi.model.po.CustomerLevel;
import wuyi.model.response.PagedData;

public interface CustomerService{
	public Customer getCustomer(Long id) throws DataException;
	
	public Customer getCustomerByUid(String uid) throws Exception;
	
	public Customer getCustomerByPhone(String phone) throws Exception;
	
	public PagedData searchCustomers(String name,String nickName,String phone,
								Date beforeDate,Date afterDate,Long cityId,Long livingAreaId,
								String customerState,int page,int pagesize);
	
	public PagedData searchCustomersVO(String name,String nickName,String phone,
			Date beforeDate,Date afterDate,Long cityId,Long livingAreaId,
			String customerState,int page,int pagesize);

	
	public Customer createCustomer(Customer customer
									,int customerLevel) throws Exception;

	public boolean updateCustomer(Customer customer) throws DataException, Exception;
	
	public boolean existsPhone(String phone);
	
	public boolean DeleteCustomer(Long customerId);
	
	public CustomerLevel getCustomerLevel(Long id);
	
	public Customer upgradeCustomer(Long id) throws Exception;
	
	public Customer degradeCustomer(Long id) throws Exception;
	
}
