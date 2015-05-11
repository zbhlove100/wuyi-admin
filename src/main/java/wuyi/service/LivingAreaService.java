package wuyi.service;

import java.util.Map;

import wuyi.model.po.LivingArea;

public interface LivingAreaService{
	public LivingArea createLivingArea(LivingArea livingArea);
	
	public LivingArea updateLivingArea(LivingArea livingArea);
	
	public boolean deleteLivingArea(Long livingAreaId);
	
	public Map searchLivingArea();

}
