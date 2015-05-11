package wuyi.model.DTO;

import java.util.Date;

import wuyi.model.po.Complaint;
import wuyi.util.WuyiBeanUtil;

public class ComplaintDTO extends BaseDTO{
	private Long id;
	private Long customerId;
	private String name;
	private String content;
	private Date createDate;
	private String state;
	private String processState;
	
	
	
	public ComplaintDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ComplaintDTO(Long id, Long customerId, String name, String content,
			Date createDate, String state, String processState) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.name = name;
		this.content = content;
		this.createDate = createDate;
		this.state = state;
		this.processState = processState;
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


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getProcessState() {
		return processState;
	}


	public void setProcessState(String processState) {
		this.processState = processState;
	}


	@Override
	public  void createDTOfromPo(Object entity) {
		Complaint complaint = (Complaint)entity;
		WuyiBeanUtil.CopyPropertiesNotNull(complaint, this);
		setCustomerId(complaint.getCustomer().getId());
	}


}
