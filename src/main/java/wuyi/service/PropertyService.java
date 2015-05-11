package wuyi.service;

import java.util.List;

import wuyi.model.po.PropertyFee;
import wuyi.model.po.PropertyNotice;
import wuyi.model.po.PropertyRequest;
import wuyi.model.response.PagedData;

public interface PropertyService{

	public List<PropertyFee> getCustomerPropertyFee(Long customerId);
	
	public List<PropertyRequest> getCustomerPropertyRequest(Long customerId);
	
	public PagedData getPropertyNotice(String type,
													String content,
													String startDate,
													int page,
													int pagesize);
	
	public PropertyNotice createPropertyNotice(PropertyNotice propertyNotice);
	
	public PropertyRequest createPropertyRequest(PropertyRequest propertyRequest);
	
	public PropertyFee createPropertyFee(PropertyFee propertyFee);

	public boolean deletePropertyNotice(Long propertyNoticeId);
	
	public boolean deletePropertyRequest(Long propertyRequestId);
	
	public boolean deletePropertyFee(Long propertyFeeId);
}
