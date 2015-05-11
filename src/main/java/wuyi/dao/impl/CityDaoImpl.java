package wuyi.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import wuyi.dao.CityDao;
import wuyi.model.po.City;
import wuyi.util.BaseModelState;
@Repository
@Transactional
public class CityDaoImpl extends MyGenericDaoHibernate<City, Long>
							implements CityDao{

	public CityDaoImpl() {
		super(City.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<City> getAllCity() {
		// TODO Auto-generated method stub
		Criteria c =  getSession().createCriteria(City.class)
					.add(Restrictions.ne("state", BaseModelState.DELETE));
				
		return c.list();
	}

}
