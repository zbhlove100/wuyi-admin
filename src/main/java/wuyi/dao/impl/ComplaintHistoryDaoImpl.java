package wuyi.dao.impl;

import org.springframework.stereotype.Repository;

import wuyi.dao.ComplaintHistoryDao;
import wuyi.model.po.ComplaintHistory;
@Repository
public class ComplaintHistoryDaoImpl extends MyGenericDaoHibernate<ComplaintHistory, Long> implements ComplaintHistoryDao{

	public ComplaintHistoryDaoImpl() {
		super(ComplaintHistory.class);
		// TODO Auto-generated constructor stub
	}

}
