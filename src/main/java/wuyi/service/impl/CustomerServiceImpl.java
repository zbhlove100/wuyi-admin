package wuyi.service.impl;


import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import wuyi.dao.CustomerDao;
import wuyi.dao.CustomerLevelDao;
import wuyi.dao.WorkerDao;
import wuyi.exception.DataException;
import wuyi.exception.WuyiException;
import wuyi.model.po.Customer;
import wuyi.model.po.CustomerLevel;
import wuyi.model.response.PagedData;
import wuyi.model.vo.customer.CustomerListVO;
import wuyi.service.CustomerLevelService;
import wuyi.service.CustomerService;
import wuyi.util.BaseModelState;
import wuyi.util.WuyiBeanUtil;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
@Service
@Transactional
public class CustomerServiceImpl extends GenericManagerImpl implements CustomerService{
	
	@Autowired
	private CustomerLevelService customerLevelService;
	
	private CustomerDao customerDao;
	@Autowired
	private CustomerLevelDao customerLevelDao;
	@Autowired
	private WorkerDao workerDao;
	
	
	public CustomerDao getCustomerDao() {
		return customerDao;
	}
	
	@Autowired
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	
	
	public CustomerLevelDao getCustomerLevelDao() {
		return customerLevelDao;
	}

	public void setCustomerLevelDao(CustomerLevelDao customerLevelDao) {
		this.customerLevelDao = customerLevelDao;
	}

	@Override
	public Customer getCustomer(Long id) throws DataException {
		Customer customer = customerDao.findActive(id);
		if(customer==null)
			throw new DataException("Can't find customer with this id!");
		return customer;
	}
	
	public Customer getCustomerByUid(String uid) throws Exception{
		Customer customer = customerDao.getCustomerByUid(uid);
		if(customer==null)
			throw new DataException("Can't find customer with this uid!");
		return customer;
	}
	
	public Customer getCustomerByPhone(String phone) throws Exception{
		Customer customer = customerDao.getCustomerByPhone(phone);
		if(customer==null)
			throw new DataException("Can't find customer with this phone!");
		return customer;
		
	}
	
	public PagedData searchCustomers(String name,String nickName,String phone,
								Date beforeDate,Date afterDate,Long cityId,Long livingAreaId,
								String customerState,int page,int pagesize){
		
//		Customer example = new Customer();
//		example.setName(name);
//		example.setNickName(nickName);
//		Filter filter = customerDao.getFilterFromExample(example);
		
		
		page= page==0?DEFAULT_PAGE:page; 
		pagesize = pagesize==0?DEFAULT_PAGESIZE:pagesize; 
		
		Search search = new Search(Customer.class);
		search.setFirstResult(0);
		search.setPage((page-1));
		search.setMaxResults(pagesize);
		search.addFilterNotEqual("state", BaseModelState.DELETE);
		if(name!=null&&!"".equals(name)){
			search.addFilterLike("name", "%"+name+"%");
		}
			
		if(nickName!=null&&!"".equals(nickName)){
			search.addFilterLike("nickname", "%"+nickName+"%");
			
		}
		if(phone!=null&&!"".equals(phone)){
			search.addFilterEqual("phone", phone);
			
		}
		if(cityId!=null){
			search.addFilterEqual("livingArea.city.id", cityId);
			
		}
		if(livingAreaId!=null){
			search.addFilterEqual("livingArea.id", livingAreaId);
			
		}
		if(customerState!=null&&!"".equals(customerState)){
			search.addFilterEqual("customerState", customerState);
			
		}
		
		if(beforeDate!=null){
			search.addFilterGreaterOrEqual("createDate", beforeDate);
			
		}
		
		if(afterDate!=null){
			search.addFilterLessOrEqual("createDate", beforeDate);
		}
		
		String orderField = "";
		if(orderField!=null&&!"".equals(orderField)){
			search.addSortDesc("id");
		}else{
			search.addSortDesc(orderField);
		}
		
		SearchResult searchResult = customerDao.searchAndCount(search);
		PagedData result = new PagedData();
		result.setTotal(searchResult.getTotalCount());
		result.setDatas(searchResult.getResult());
		result.setPage(page);
		result.setPagesize(pagesize);
		return result;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Customer createCustomer(Customer customer ,
									int customerLevel) throws Exception{
		

		CustomerLevel targetCustomerLevel = this.customerLevelDao.getCustomerLevel(customerLevel);
		if(existsPhone(customer.getPhone())){
			throw new DataException("Phone number exsit!");
		}else{
			Date createDate = new Date(System.currentTimeMillis());
			String uid = UUID.randomUUID().toString();
			String encodePassword = Base64.encodeBase64String(customer.getPassword().getBytes());
			customer.setUid(uid);
			customer.setPassword(encodePassword);
			customer.setCustomerLevel(targetCustomerLevel);
			customer.setCreateDate(createDate);
			customer.setState(BaseModelState.ACTIVE);
			customer.setCustomerState(BaseModelState.CUSTOM_REGISTER_REGISTERED);
			customer.setRole(BaseModelState.CUSTOM_ROLE_TEMP);
			if(customerDao.save(customer)){
				return customer;
			}else{
				throw new WuyiException("Create customer fail!");
			}
			
		}
		
		
		
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateCustomer(Customer customer) throws Exception{
		System.out.println(customer.getUid());
		String[] nullProperty = {"uid","phone","createDate","state"};
		WuyiBeanUtil.setPropertyNull(customer, nullProperty);
		System.out.println(customer.getUid());
		String encodePassword = Base64.encodeBase64String(customer.getPassword().getBytes());
		Date updateDate = new Date(System.currentTimeMillis());
		Customer persistCustomer = customerDao.find(customer.getId());
		
		WuyiBeanUtil.CopyPropertiesNotNull(customer, persistCustomer);
		persistCustomer.setPassword(encodePassword);
		persistCustomer.setUpdateDate(updateDate);
		customerDao.update(persistCustomer);
		return true;
	}
	
	public boolean DeleteCustomer(Long customerId){
		Date updateDate = new Date(System.currentTimeMillis());
		
		Customer customer = customerDao.find(customerId);
		customer.setState(BaseModelState.DELETE);
		customer.setUpdateDate(updateDate);
		customerDao.update(customer);
		customerDao.flush();
		return true;
		
	}

	public boolean existsPhone(String phone) {
		boolean result = false;
		Customer customer;
		try {
			customer = getCustomerByPhone(phone);
			if (customer == null) {
				result = false;
			} else {
				result = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	


	@Override
	@Transactional
	public Customer upgradeCustomer(Long id) throws Exception{
		// TODO Auto-generated method stub
		Customer customer = customerDao.find(id);
		CustomerLevel customerLevel = customer.getCustomerLevel();
		CustomerLevel nextCustomerLevel = customerLevelService.getNextLevel(customerLevel);
		if(nextCustomerLevel==null)
			throw new DataException("No higher level");
		if(nextCustomerLevel.getLevel()==customerLevel.getLevel()){
			throw new DataException("No higher level");
		}else{
			customer.setCustomerLevel(nextCustomerLevel);
			Date updateDate = new Date(System.currentTimeMillis());
			customer.setUpdateDate(updateDate);
			customerDao.flush();
			return customer;
			
		}
	}
	
	@Override
	@Transactional
	public Customer degradeCustomer(Long id) throws Exception{
		// TODO Auto-generated method stub
		Customer customer = customerDao.find(id);
		CustomerLevel customerLevel = customer.getCustomerLevel();
		CustomerLevel nextCustomerLevel = customerLevelService.getPreLevel(customerLevel);
		if(nextCustomerLevel==null)
			throw new DataException("No lower level");
		if(nextCustomerLevel.getLevel()==customerLevel.getLevel()){
			throw new DataException("No lower level");
		}else{
			customer.setCustomerLevel(nextCustomerLevel);
			Date updateDate = new Date(System.currentTimeMillis());
			customer.setUpdateDate(updateDate);
			customerDao.flush();
			return customer;
			
		}
	}

	@Override
	public CustomerLevel getCustomerLevel(Long id) {
		// TODO Auto-generated method stub
		Customer customer = customerDao.find(id);
		CustomerLevel customerLevel = customer.getCustomerLevel();
		customerLevel.getName();
		return customerLevel;
	}


	@Override
	@Transactional
	public PagedData searchCustomersVO(String name, String nickName,
			String phone, Date beforeDate, Date afterDate, Long cityId,
			Long livingAreaId, String customerState, int page, int pagesize) {
		page= page==0?DEFAULT_PAGE:page; 
		pagesize = pagesize==0?DEFAULT_PAGESIZE:pagesize; 
		
		List<CustomerListVO> customerListVOs = customerDao.searchCustomerListVO(name, nickName, phone, beforeDate, afterDate, cityId, livingAreaId, customerState, page, pagesize);
		Search search = new Search(Customer.class);
		search.addFilterNotEqual("state", BaseModelState.DELETE);
		
		if(name!=null&&!"".equals(name)){
			search.addFilterLike("name", "%"+name+"%");
		}
			
		if(nickName!=null&&!"".equals(nickName)){
			search.addFilterLike("nickname", "%"+nickName+"%");
			
		}
		if(phone!=null&&!"".equals(phone)){
			search.addFilterEqual("phone", phone);
			
		}
		if(cityId!=null){
			search.addFilterEqual("livingArea.city.id", cityId);
			
		}
		if(livingAreaId!=null){
			search.addFilterEqual("livingArea.id", livingAreaId);
			
		}
		if(customerState!=null&&!"".equals(customerState)){
			search.addFilterEqual("customerState", customerState);
			
		}
		if(beforeDate!=null){
			search.addFilterGreaterOrEqual("createDate", beforeDate);
			
		}
		
		if(afterDate!=null){
			search.addFilterLessOrEqual("createDate", beforeDate);
		}
		
		PagedData result = new PagedData();
		result.setTotal(customerDao.count(search));
		result.setDatas(customerListVOs);
		result.setPage(page);
		result.setPagesize(pagesize);
		return result;
	}

}
