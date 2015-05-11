package wuyi.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import wuyi.dao.WorkerDao;
import wuyi.model.po.Worker;
import wuyi.util.BaseModelState;
@Repository
@Transactional
public class WorkerDaoImpl extends MyGenericDaoHibernate<Worker, Long>implements WorkerDao{

	public WorkerDaoImpl() {
		super(Worker.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Worker> getWorkerByRole(String role) {
		
		Criteria criteria = getSession().createCriteria(Worker.class)
		.add(Restrictions.ne("state", BaseModelState.DELETE))
		.add(Restrictions.eq("role", role));
		
		List workers = criteria.list();
		return workers;
	}

	@Override
	public List<Worker> getWorkerByLocation(String location) {
		// TODO Auto-generated method stub
		Criteria criteria = getSession().createCriteria(Worker.class)
				.add(Restrictions.ne("state", BaseModelState.DELETE))
				.add(Restrictions.eq("location", location));
				
				List workers = criteria.list();
				return workers;
	}


}
