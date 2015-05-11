package wuyi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wuyi.dao.WorkerDao;
import wuyi.model.po.Worker;
import wuyi.model.response.PagedData;
import wuyi.service.WorkerService;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
@Service
@Transactional
public class WorkerServiceImpl extends GenericManagerImpl implements WorkerService{

	@Autowired
	WorkerDao workerDao;
	
	@Override
	public Worker getWorker(Long id) {
		return workerDao.findActive(id);
	};
	

	@Override
	public List<Worker> getWorkerByLivingArea(Long livingAreaId) {
		// TODO Auto-generated method stub
		Search search = new Search(Worker.class).addFilterEqual("livingArea.id", livingAreaId);
		return workerDao.search(search);
	}


	@Override
	@Transactional
	public PagedData searchWorker(int page, int pagesize,Long livingAreaId,String phone) {
		// TODO Auto-generated method stub
		
		page= page==0?DEFAULT_PAGE:page; 
		pagesize = pagesize==0?DEFAULT_PAGESIZE:pagesize; 
		
		PagedData result = new PagedData();
		Search search = new Search(Worker.class);
		search.setPage((page-1));
		search.setMaxResults(pagesize);
		
		if(phone!=null&&!"".equals(phone)){
			search.addFilterEqual("phone", phone);
		}
		if(livingAreaId!=null){
			search.addFilterEqual("livingArea.id", livingAreaId);
		}
		SearchResult sResult = workerDao.searchAndCount(search);
		result.setPage(page);
		result.setPagesize(pagesize);
		result.setTotal(sResult.getTotalCount());
		result.setDatas(sResult.getResult());
		return result;
	}

	@Override
	public boolean createWorker(Worker worker) throws Exception {
		// TODO Auto-generated method stub
		if(usernameExsit(worker.getUsername())){
			throw new Exception("Username exsit!");
		}else{
			return workerDao.save(worker);
		}
		
	}
	
	@Override
	public boolean updateWorker(Worker worker) {
		// TODO Auto-generated method stub
		 workerDao.update(worker);
		 return true;
	}
	
	@Override
	public boolean usernameExsit(String username) {
		// TODO Auto-generated method stub
		
		Worker worker;
		try {
			Search search = new Search(Worker.class).addFilterEqual("username", username);
			worker = workerDao.searchUnique(search);
			if(worker == null){
				return false;
			}else{
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

}
