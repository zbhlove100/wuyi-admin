package wuyi.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import wuyi.dao.CustomerLevelDao;
import wuyi.model.po.CustomerLevel;
import wuyi.util.BaseModelState;
@Repository
@Transactional
public class CustomerLevelDaoImpl extends MyGenericDaoHibernate<CustomerLevel, Long> implements CustomerLevelDao{

	public CustomerLevelDaoImpl() {
		super(CustomerLevel.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public CustomerLevel getCustomerLevel(int level) throws Exception {
		// TODO Auto-generated method stub
		List<CustomerLevel> customerLevels = getSession().createCriteria(CustomerLevel.class)
												.add(Restrictions.ne("state", BaseModelState.DELETE))
												.add(Restrictions.eq("level", level))
												.list();
		if(customerLevels == null||customerLevels.size()==0){
			throw new Exception("customer level error!");
		}else{
			return customerLevels.get(0);
		}
	}

	@Override
	public List<CustomerLevel> getAllCustomerLevels() {
		// TODO Auto-generated method stub
		return getSession().createCriteria(CustomerLevel.class)
				.add(Restrictions.ne("state", BaseModelState.DELETE))
				.list();
	}

}
