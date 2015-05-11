package wuyi.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import wuyi.dao.CustomerDao;
import wuyi.model.po.Customer;
import wuyi.model.vo.customer.CustomerListVO;
@Repository
@Transactional
public class CustomerDaoImpl extends MyGenericDaoHibernate<Customer, Long> implements CustomerDao{

	public CustomerDaoImpl() {
		super(Customer.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Customer getCustomerByUid(String uid) throws Exception {
		// TODO Auto-generated method stub
		Customer customer;
		List customers = getSession().createCriteria(Customer.class)
					.add(Restrictions.eq("uid", uid))
					.add(Restrictions.ne("state", "Delete"))
					.addOrder(Order.desc("createDate")).list();
		
		if(customers == null || customers.isEmpty()){
			throw new Exception("No customer find by uid!");
		}else{
			return (Customer) customers.get(0);
		}
		
	}

	@Override
	public Customer getCustomerByPhone(String phone) throws Exception {
		// TODO Auto-generated method stub
		Customer customer;
		List customers = getSession().createCriteria(Customer.class)
					.add(Restrictions.eq("phone", phone))
					.add(Restrictions.ne("state", "Delete"))
					.addOrder(Order.desc("createDate")).list();
		
		if(customers == null || customers.isEmpty()){
			throw new Exception("No customer find by phone!");
		}else{
			return (Customer) customers.get(0);
		}
	}

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		Query query = getSession().createQuery(" from Customer where state != 'Delete' order by updateDate");
		return query.list();
	}



	@Override
	public Customer getCustomerByName(String name) throws Exception {
		// TODO Auto-generated method stub
		Customer customer;
		List customers = getSession().createCriteria(Customer.class)
					.add(Restrictions.eq("name", name))
					.add(Restrictions.ne("state", "Delete"))
					.addOrder(Order.desc("createDate")).list();
		
		if(customers == null || customers.isEmpty()){
			throw new Exception("No customer find by name!");
		}else{
			return (Customer) customers.get(0);
		}
	}

	@Override
	public List<CustomerListVO> searchCustomerListVO(String name,
			String nickName, String phone, Date beforeDate, Date afterDate,
			Long cityId, Long livingAreaId, String customerState, int page,
			int pagesize) {
		StringBuffer sb = new StringBuffer();
		sb.append("select new com.wuyi.model.vo.customer.CustomerListVO(C.id,C.name,C.phone,"
				+ "C.createDate,"
				+ "(select count(*) from Comment comment where comment.customer.id = C.id) as commentNum,"
				+ "(select count(*) from OrderInfo order where order.customer.id = C.id) as orderNum, "
				+ "(select count(*) from Complaint complaint where complaint.customer.id = C.id) as complaintNum,"
				+ "(select count(*) from Feedback feedback where feedback.customer.id = C.id) as feedbackNum,"
				+ "(select count(*) from PropertyRequest propertyRequest where propertyRequest.customer.id = C.id) as propertyRequestNum "
				+ ") from Customer C \n"
				+ "where state!='Delete' ");
		if(name!=null&&!"".equals(name)){
			sb.append("\n and C.name like '%"+name+"%'");
		}
			
		if(nickName!=null&&!"".equals(nickName)){
			sb.append("\n and C.name like '%"+nickName+"%'");
		}
		if(phone!=null&&!"".equals(phone)){
			sb.append("\n and C.name = '"+phone+"'");
		}
		if(cityId!=null){
			sb.append("\n and C.city.id = '"+cityId+"'");
		}
		if(livingAreaId!=null){
			sb.append("\n and C.livingArea = '"+livingAreaId+"'");
		}
		if(customerState!=null&&!"".equals(customerState)){
			sb.append("\n and C.customerState = '"+customerState+"'");
		}
		if(beforeDate!=null){
			sb.append("\n and C.createDate >= "+beforeDate);
		}
		if(afterDate!=null){
			sb.append("\n and C.createDate <= "+afterDate);
		}
		String orderField = "";
		if(orderField!=null&&!"".equals(orderField)){
			sb.append("\n  order by C."+orderField);
		}else{
			sb.append("\n  order by C.id");
		}
		int offset = (page-1)*pagesize;
		Query query = getSession().createQuery(sb.toString());
		query.setFirstResult(offset);
		query.setMaxResults(pagesize);
		// TODO Auto-generated method stub
		return query.list();
	}

//	@Override
//	public SearchResult searchCustomerByName(String name,int page,int pagesize) {
//		Search search = new Search(Customer.class);
//		search.addFilterLike("name", "%"+name+"%");
//		search.setPage(page);
//		search.setMaxResults(pagesize);
//		
//		return searchAndCount(search);
//	}

	
	
	

}
