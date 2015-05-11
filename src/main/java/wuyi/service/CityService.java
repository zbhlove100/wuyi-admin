package wuyi.service;

import java.util.List;

import wuyi.model.po.City;

public interface CityService{
	
	public City createCity(City city);
	
	public City updateCity(City city) throws Exception;
	
	public boolean deleteCity(Long cityId);
	
	public List<City> getAllCity();

}
