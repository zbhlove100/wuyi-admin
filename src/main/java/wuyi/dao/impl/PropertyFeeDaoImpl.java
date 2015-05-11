package wuyi.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import wuyi.dao.PropertyFeeDao;
import wuyi.model.po.PropertyFee;
@Repository
@Transactional
public class PropertyFeeDaoImpl extends MyGenericDaoHibernate<PropertyFee, Long>
										implements PropertyFeeDao{

	public PropertyFeeDaoImpl() {
		super(PropertyFee.class);
		// TODO Auto-generated constructor stub
	}

}
