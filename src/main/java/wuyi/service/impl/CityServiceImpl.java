package wuyi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wuyi.dao.CityDao;
import wuyi.exception.DataException;
import wuyi.model.po.City;
import wuyi.service.CityService;
import wuyi.util.BaseModelState;


@Service
public class CityServiceImpl extends GenericManagerImpl
								implements CityService{
	
	CityDao cityDao;
	
	@Autowired
	public CityDao getCityDao() {
		return cityDao;
	}

	public void setCityDao(CityDao cityDao) {
		this.cityDao = cityDao;
	}


	@Override
	public List<City> getAllCity() {
		// TODO Auto-generated method stub
		return cityDao.getAllCity();
	}

	@Override
	public City createCity(City city) {
		// TODO Auto-generated method stub
		if(city.getId()!=null){
			city.setId(null);
		}
		cityDao.save(city);
		return city;
	}

	@Override
	public City updateCity(City city) throws Exception {
		// TODO Auto-generated method stub
		if(city.getId()==null){
			throw new DataException("Can't update city with null!");
		}else{
			cityDao.save(city);
		}
		return city;
	}

	@Override
	public boolean deleteCity(Long cityId) {
		// TODO Auto-generated method stub
		City city = cityDao.find(cityId);
		if(city == null){
			return false;
		}else{
			city.setState(BaseModelState.DELETE);
			cityDao.update(city);
		}
		return true;
	}

}
