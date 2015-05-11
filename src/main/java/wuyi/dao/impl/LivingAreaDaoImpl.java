package wuyi.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import wuyi.dao.LivingAreaDao;
import wuyi.model.po.LivingArea;
@Repository
@Transactional
public class LivingAreaDaoImpl extends MyGenericDaoHibernate<LivingArea, Long>
								implements LivingAreaDao{

	public LivingAreaDaoImpl() {
		super(LivingArea.class);
		// TODO Auto-generated constructor stub
	}

}
