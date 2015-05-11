package wuyi.service;

import java.util.List;

import wuyi.model.po.Worker;
import wuyi.model.response.PagedData;

public interface WorkerService{

	public Worker getWorker(Long id);
	
	public List<Worker> getWorkerByLivingArea(Long livingAreaId);
	
	public PagedData searchWorker(int page, int pagesize,Long livingAreaId,String phone);
	
	public boolean createWorker(Worker worker) throws Exception;
	
	public boolean updateWorker(Worker worker);
	
	public boolean usernameExsit(String username);
}
