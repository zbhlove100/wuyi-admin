package wuyi.service;

import wuyi.exception.DataException;
import wuyi.model.po.Feedback;
import wuyi.model.response.PagedData;

public interface FeedbackService {

	public Feedback getFeedback(Long feedbackId) throws Exception;
	
	public Feedback createFeedback(Feedback feedback,Long customerId) throws Exception;
	
	public boolean deleteFeedback(Long feedbackId) throws DataException;
	
	public PagedData searchFeedback(Long customerId,int page,int pagesize);

	public boolean updateFeedback(Feedback feedback) throws Exception;
	
	public int getFeedbackNum(Long customerId);
}
