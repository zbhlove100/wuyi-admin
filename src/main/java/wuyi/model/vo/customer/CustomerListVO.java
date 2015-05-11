package wuyi.model.vo.customer;

import java.util.Date;

public class CustomerListVO {
	private Long id;
	private String name;
	private String phone;
	private Date createdDate;
	private Long commentNum;
	private Long orderNum;
	private Long complaintNum;
	private Long feedbackNum;
	private Long propertyRequestNum;
	
	
	public CustomerListVO(Long id, String name, String phone, Date createdDate,
			Long commentNum, Long orderNum, Long complaintNum,
			Long feedbackNum, Long propertyRequestNum) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.createdDate = createdDate;
		this.commentNum = commentNum;
		this.orderNum = orderNum;
		this.complaintNum = complaintNum;
		this.feedbackNum = feedbackNum;
		this.propertyRequestNum = propertyRequestNum;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public Long getCommentNum() {
		return commentNum;
	}


	public void setCommentNum(Long commentNum) {
		this.commentNum = commentNum;
	}


	public Long getOrderNum() {
		return orderNum;
	}


	public void setOrderNum(Long orderNum) {
		this.orderNum = orderNum;
	}


	public Long getComplaintNum() {
		return complaintNum;
	}


	public void setComplaintNum(Long complaintNum) {
		this.complaintNum = complaintNum;
	}


	public Long getFeedbackNum() {
		return feedbackNum;
	}


	public void setFeedbackNum(Long feedbackNum) {
		this.feedbackNum = feedbackNum;
	}


	public Long getPropertyRequestNum() {
		return propertyRequestNum;
	}


	public void setPropertyRequestNum(Long propertyRequestNum) {
		this.propertyRequestNum = propertyRequestNum;
	}
	
}
