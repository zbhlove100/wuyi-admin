package wuyi.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import wuyi.dao.FeedbackDao;
import wuyi.model.po.Feedback;
@Repository
@Transactional
public class FeedbackDaoImpl extends MyGenericDaoHibernate<Feedback, Long> implements FeedbackDao{

	public FeedbackDaoImpl() {
		super(Feedback.class);
		// TODO Auto-generated constructor stub
	}

}
