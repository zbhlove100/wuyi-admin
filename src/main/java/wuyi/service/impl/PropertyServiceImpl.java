package wuyi.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wuyi.dao.PropertyFeeDao;
import wuyi.dao.PropertyNoticeDao;
import wuyi.dao.PropertyRequestDao;
import wuyi.model.po.PropertyFee;
import wuyi.model.po.PropertyNotice;
import wuyi.model.po.PropertyRequest;
import wuyi.model.response.PagedData;
import wuyi.service.PropertyService;
import wuyi.util.BaseModelState;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
@Service
public class PropertyServiceImpl implements PropertyService{
	
	@Autowired
	PropertyFeeDao propertyFeeDao;
	@Autowired
	PropertyRequestDao propertyRequestDao;
	@Autowired
	PropertyNoticeDao propertyNoticeDao;
	
	@Override
	public List<PropertyFee> getCustomerPropertyFee(Long customerId) {
		// TODO Auto-generated method stub
		Search search = new Search(PropertyFee.class);
		
		search.addFilterEqual("customer.id", customerId);
		search.addFilterNotEqual("state", BaseModelState.DELETE);
		return propertyFeeDao.search(search);
	}

	@Override
	public List<PropertyRequest> getCustomerPropertyRequest(Long customerId) {
		// TODO Auto-generated method stub
		Search search = new Search(PropertyRequest.class);
		search.addFilterNotEqual("state", BaseModelState.DELETE);
		search.addFilterEqual("customer.id", customerId);
		return propertyRequestDao.search(search);
	}

	@Override
	public PagedData getPropertyNotice(String type, String content,
			String startDate, int page, int pagesize) {
		try {
			Date searchDate;
			
			Search search = new Search(PropertyNotice.class);
			search.addFilterNotEqual("state", BaseModelState.DELETE);
			if(null!=type&&!"".equals(type)){
				search.addFilterEqual("type", type);
			}
			if(null!=content&&!"".equals(content)){
				search.addFilterLike("content", "%"+content+"%");
			}
			if(null!=startDate&&!"".equals(startDate)){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				searchDate = sdf.parse(startDate);
			}else{
				Date now = new Date();
				searchDate = new Date(now.getTime()-1000*60*60*24*30);
			}
			search.addFilterGreaterOrEqual("createDate", searchDate);
			SearchResult searchResult = propertyNoticeDao.searchAndCount(search);
			PagedData result = new PagedData(searchResult.getTotalCount(),page,pagesize,searchResult.getResult());
			return result;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}

	@Override
	public PropertyNotice createPropertyNotice(PropertyNotice propertyNotice) {
		// TODO Auto-generated method stub
		if(propertyNotice.getId()!=null){
			propertyNotice.setId(null);
		}
		propertyNoticeDao.save(propertyNotice);
		return propertyNotice;
	}

	@Override
	public PropertyRequest createPropertyRequest(PropertyRequest propertyRequest) {
		// TODO Auto-generated method stub
		if(propertyRequest.getId()!=null){
			propertyRequest.setId(null);
		}
		propertyRequestDao.save(propertyRequest);
		return propertyRequest;
	}

	@Override
	public PropertyFee createPropertyFee(PropertyFee propertyFee) {
		// TODO Auto-generated method stub
		if(propertyFee.getId()!=null){
			propertyFee.setId(null);
		}
		propertyFeeDao.save(propertyFee);
		return propertyFee;
	}

	@Override
	public boolean deletePropertyNotice(Long propertyNoticeId) {
		// TODO Auto-generated method stub
		propertyNoticeDao.removeById(propertyNoticeId);
		return true;
	}

	@Override
	@Transactional
	public boolean deletePropertyRequest(Long propertyRequestId) {
		// TODO Auto-generated method stub
		PropertyRequest propertyRequest = propertyRequestDao.find(propertyRequestId);
		propertyRequest.setState(BaseModelState.DELETE);
		propertyRequestDao.update(propertyRequest);
		return true;
	}

	@Override
	@Transactional
	public boolean deletePropertyFee(Long propertyFeeId) {
		// TODO Auto-generated method stub
		propertyFeeDao.removeById(propertyFeeId);
		
		return true;
	}

}
