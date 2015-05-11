package wuyi.model.DTO;

import java.util.Date;

import wuyi.model.po.Feedback;
import wuyi.util.WuyiBeanUtil;

public class FeedbackDTO extends BaseDTO{
	private Long id;
	private Long customerId;
	private String name;
	private String content;
	private String state;
	private Date createDate;
	public FeedbackDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public void createDTOfromPo(Object entity) {
		Feedback feedback = (Feedback)entity;
		WuyiBeanUtil.CopyPropertiesNotNull(feedback, this);
		setCustomerId(feedback.getCustomer().getId());
		
	}
	
	
}
