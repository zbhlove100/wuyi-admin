package wuyi.dao;

import java.util.List;

import wuyi.model.po.City;

public interface CityDao extends MyGenericDao<City, Long>{

	public List<City> getAllCity();
	
}
