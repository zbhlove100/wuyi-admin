package wuyi.dao;

import java.util.List;

import wuyi.model.po.Worker;

public interface WorkerDao extends MyGenericDao<Worker, Long>{
	public List<Worker> getWorkerByRole(String role);
	
	public List<Worker> getWorkerByLocation(String location);
	
}
