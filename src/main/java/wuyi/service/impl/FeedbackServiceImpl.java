package wuyi.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wuyi.dao.FeedbackDao;
import wuyi.exception.DataException;
import wuyi.model.po.Customer;
import wuyi.model.po.Feedback;
import wuyi.model.response.PagedData;
import wuyi.service.FeedbackService;
import wuyi.util.BaseModelState;
import wuyi.util.WuyiBeanUtil;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
@Service
public class FeedbackServiceImpl extends GenericManagerImpl implements FeedbackService{

	@Autowired
	FeedbackDao feedbackDao;
	
	@Override
	public Feedback getFeedback(Long feedbackId) throws Exception {
		Feedback feedback = feedbackDao.findActive(feedbackId);
		if(feedback==null)
			throw new DataException("Can't find feedback with this id!");
		return feedback;
	}

	@Override
	@Transactional
	public Feedback createFeedback(Feedback feedback, Long customerId)
			throws Exception {
		// TODO Auto-generated method stub
		
		Date createDate = new Date(System.currentTimeMillis());
		feedback.setCreateDate(createDate);
		feedback.setState(BaseModelState.ACTIVE);
		Customer customer = new Customer();
		customer.setId(customerId);
		feedback.setCustomer(customer);
		feedbackDao.save(feedback);
		return feedback;
	}

	@Override
	@Transactional
	public boolean deleteFeedback(Long feedbackId) throws DataException {
		Feedback feedback = feedbackDao.findActive(feedbackId);
		if(feedback==null)
			throw new DataException("Can't find feedback with this id!");
		
		feedback.setState(BaseModelState.DELETE);
		feedbackDao.flush();
		return true;
	}

	@Override
	@Transactional
	public PagedData searchFeedback(Long customerId, int page, int pagesize) {
		
		page= page==0?DEFAULT_PAGE:page; 
		pagesize = pagesize==0?DEFAULT_PAGESIZE:pagesize; 
		
		Search search = new Search(Feedback.class);
		search.setFirstResult(0);
		search.setPage((page-1));
		search.setMaxResults(pagesize);
		search.addFilterNotEqual("state", BaseModelState.DELETE);
		if(customerId!=null){
			search.addFilterEqual("customer.id", customerId);
		}
		
		
		SearchResult searchResult = feedbackDao.searchAndCount(search);
		PagedData result = new PagedData();
		result.setTotal(searchResult.getTotalCount());
		result.setDatas(searchResult.getResult());
		result.setPage(page);
		result.setPagesize(pagesize);
		return result;
	}

	@Override
	@Transactional
	public boolean updateFeedback(Feedback feedback) throws Exception {
		String[] nullProperty = {"name","createDate","customer","state"};
		WuyiBeanUtil.setPropertyNull(feedback, nullProperty);
		
		Date updateDate = new Date(System.currentTimeMillis());
		Feedback persistFeedback = feedbackDao.find(feedback.getId());
		
		WuyiBeanUtil.CopyPropertiesNotNull(feedback, persistFeedback);
		feedbackDao.update(persistFeedback);
		return true;
	}

	@Override
	@Transactional
	public int getFeedbackNum(Long customerId) {
		Search search = new Search(Customer.class);
		if(customerId!=null){
			search.addFilterEqual("customer.id", customerId);
		}
		search.addFilterNotEqual("state", BaseModelState.DELETE);
		return feedbackDao.count(search);
	}

}
