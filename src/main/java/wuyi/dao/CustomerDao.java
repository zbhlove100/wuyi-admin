package wuyi.dao;

import java.util.Date;
import java.util.List;

import wuyi.model.po.Customer;
import wuyi.model.vo.customer.CustomerListVO;

public interface CustomerDao extends MyGenericDao<Customer, Long> {
	
	public Customer getCustomerByName(String name) throws Exception;

	public Customer getCustomerByUid(String uid) throws Exception;
	
	public Customer getCustomerByPhone(String phone) throws Exception;
	
	public List<Customer> getAllCustomers();
	
	public List<CustomerListVO> searchCustomerListVO(String name,String nickName,String phone,
			Date beforeDate,Date afterDate,Long cityId,Long livingAreaId,
			String customerState,int page,int pagesize);
	
}
