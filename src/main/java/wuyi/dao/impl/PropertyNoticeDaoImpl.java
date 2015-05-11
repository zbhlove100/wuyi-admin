package wuyi.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import wuyi.dao.PropertyNoticeDao;
import wuyi.model.po.PropertyNotice;
@Repository
@Transactional
public class PropertyNoticeDaoImpl extends MyGenericDaoHibernate<PropertyNotice, Long>
											implements PropertyNoticeDao{

	public PropertyNoticeDaoImpl() {
		super(PropertyNotice.class);
		// TODO Auto-generated constructor stub
	}

}
