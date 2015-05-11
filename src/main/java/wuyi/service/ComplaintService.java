package wuyi.service;

import java.util.Date;

import wuyi.exception.DataException;
import wuyi.model.po.Complaint;
import wuyi.model.response.PagedData;

public interface ComplaintService {

	public Complaint getComplaint(Long complaintId) throws Exception;
	
	public Complaint createComplaint(Complaint complaint,Long customerId) throws Exception;
	
	public boolean deleteComplaint(Long complaintId) throws DataException;
	
	
	public PagedData searchComplaint(Long customerId,Date createDate, int page,int pagesize);

	public boolean updateComplaint(Complaint complaint) throws Exception;
	
	public int getComplaintNum(Long customerId);

	boolean acceptComplaint(Long complaintId, Long workerId, String comment)
			throws DataException;
	
	boolean resolveComplaint(Long complaintId, Long workerId, String comment)
			throws DataException;
	
	
}
