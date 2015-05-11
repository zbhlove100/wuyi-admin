package wuyi.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import wuyi.dao.PropertyRequestDao;
import wuyi.model.po.PropertyRequest;
@Repository
@Transactional
public class PropertyRequestDaoImpl extends MyGenericDaoHibernate<PropertyRequest, Long>
									implements PropertyRequestDao{


	public PropertyRequestDaoImpl() {
		super(PropertyRequest.class);
		// TODO Auto-generated constructor stub
	}


}
