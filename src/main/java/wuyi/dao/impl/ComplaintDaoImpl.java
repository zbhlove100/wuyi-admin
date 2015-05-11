package wuyi.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import wuyi.dao.ComplaintDao;
import wuyi.model.po.Complaint;
@Repository
@Transactional
public class ComplaintDaoImpl extends MyGenericDaoHibernate<Complaint, Long> implements ComplaintDao{

	public ComplaintDaoImpl() {
		super(Complaint.class);
		// TODO Auto-generated constructor stub
	}

}
