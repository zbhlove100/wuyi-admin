package wuyi.service.impl;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wuyi.dao.ComplaintDao;
import wuyi.dao.ComplaintHistoryDao;
import wuyi.dao.WorkerDao;
import wuyi.exception.DataException;
import wuyi.model.po.Complaint;
import wuyi.model.po.ComplaintHistory;
import wuyi.model.po.Customer;
import wuyi.model.po.Worker;
import wuyi.model.response.PagedData;
import wuyi.service.ComplaintService;
import wuyi.util.BaseModelState;
import wuyi.util.WuyiBeanUtil;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
@Service
public class ComplaintServiceImpl extends GenericManagerImpl implements ComplaintService{

	@Autowired
	ComplaintDao complaintDao;
	@Autowired
	WorkerDao workerDao;
	@Autowired
	ComplaintHistoryDao complaintHistoryDao;
	
	@Override
	public Complaint getComplaint(Long complaintId) throws Exception {
		// TODO Auto-generated method stub
		Complaint complaint = complaintDao.findActive(complaintId);
		if(complaint==null)
			throw new DataException("Can't find complaint with this id!");
		return complaint;
	}

	@Override
	@Transactional
	public Complaint createComplaint(Complaint complaint, Long customerId)
			throws Exception {
		Date createDate = new Date(System.currentTimeMillis());
		complaint.setCreateDate(createDate);
		complaint.setState(BaseModelState.ACTIVE);
		complaint.setProcessState(BaseModelState.COMPLAINT_PROCESS_REQUEST);
		Customer customer = new Customer();
		customer.setId(customerId);
		complaint.setCustomer(customer);
		complaintDao.save(complaint);
		
		return complaint;
	}

	@Override
	@Transactional
	public boolean deleteComplaint(Long complaintId) throws DataException {
		Complaint complaint = complaintDao.findActive(complaintId);
		if(complaint==null)
			throw new DataException("Can't find complaint with this id!");
		
		complaint.setState(BaseModelState.DELETE);
		complaintDao.flush();
		return true;
	}

	@Override
	@Transactional
	public PagedData searchComplaint(Long customerId,Date createDate, int page, int pagesize) {
		page= page==0?DEFAULT_PAGE:page; 
		pagesize = pagesize==0?DEFAULT_PAGESIZE:pagesize; 
		
		Search search = new Search(Complaint.class);
		search.setFirstResult(0);
		search.setPage((page-1));
		search.setMaxResults(pagesize);
		
		search.addFilterNotEqual("state", BaseModelState.DELETE);
		if(customerId!=null){
			search.addFilterEqual("customer.id", customerId);
		}
		if(createDate!=null){
			search.addFilterGreaterOrEqual("createDate", createDate);
		}
		
		SearchResult searchResult = complaintDao.searchAndCount(search);
		PagedData result = new PagedData();
		result.setTotal(searchResult.getTotalCount());
		result.setDatas(searchResult.getResult());
		result.setPage(page);
		result.setPagesize(pagesize);
		return result;
	}

	@Override
	@Transactional
	public boolean updateComplaint(Complaint complaint) throws Exception {
		String[] updateProperty = {"name","content"};
		
		Date updateDate = new Date(System.currentTimeMillis());
		Complaint persistComplaint = complaintDao.find(complaint.getId());
		
		WuyiBeanUtil.copySpecPropertiesNotNull(complaint, persistComplaint, Arrays.asList(updateProperty));
		complaintDao.update(persistComplaint);
		return true;
	}

	@Override
	@Transactional
	public int getComplaintNum(Long customerId) {
		// TODO Auto-generated method stub
		Search search = new Search(Customer.class);
		search.addFilterNotEqual("state", BaseModelState.DELETE);
		if(customerId!=null){
			search.addFilterEqual("customer.id", customerId);
		}
		
		return complaintDao.count(search);
	}

	@Override
	@Transactional
	public boolean acceptComplaint(Long complaintId, Long workerId,String comment)
			throws DataException {
		// TODO Auto-generated method stub
		return processComplaint(complaintId, workerId, comment, BaseModelState.COMPLAINT_PROCESS_ACCEPT);
	}
	
	@Override
	@Transactional
	public boolean resolveComplaint(Long complaintId, Long workerId,String comment)
			throws DataException {
		return processComplaint(complaintId, workerId, comment, BaseModelState.COMPLAINT_PROCESS_RESOLVE);
		
	}
	
	private boolean processComplaint(Long complaintId, Long workerId,String comment,String state) throws DataException{
		Date updateDate = new Date(System.currentTimeMillis());
		Complaint persistComplaint = complaintDao.find(complaintId);
		if(persistComplaint == null){
			throw new DataException(String.format("Can't find complaint with id:%s !", complaintId));
		}
		persistComplaint.setProcessState(state);
		Worker worker = workerDao.find(workerId);
		if(worker == null){
			throw new DataException(String.format("Can't find worker with id:%s !", complaintId));
		}
		ComplaintHistory complaintHistory = new ComplaintHistory();
		complaintHistory.setWorkerId(workerId);
		complaintHistory.setComment(comment);
		complaintHistory.setCurrentState(state);
		complaintHistory.setState(BaseModelState.ACTIVE);
		complaintHistory.setUpdateDate(updateDate);
		complaintHistory.setComplaint(persistComplaint);
		
		complaintHistoryDao.save(complaintHistory);
		complaintDao.flush();
		return true;
	}



}
