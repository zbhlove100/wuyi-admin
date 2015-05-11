package wuyi.model.DTO;

import java.util.Date;

import wuyi.model.po.Customer;
import wuyi.util.WuyiBeanUtil;

public class CustomerDTO extends BaseDTO{

	private Long id;
	private Long customerLevelId;
	private Long livingAreaId;
	private String uid;
	private String name;
	private String nickName;
	private String phone;
	private String sex;
//	private String password;
	private String role;
	private Date createDate;
	private Date updateDate;
	private String state;
	private String imageUrl;
	private String customerState;
	
	
	public CustomerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getCustomerLevelId() {
		return customerLevelId;
	}


	public void setCustomerLevelId(Long customerLevelId) {
		this.customerLevelId = customerLevelId;
	}


	public Long getLivingAreaId() {
		return livingAreaId;
	}


	public void setLivingAreaId(Long livingAreaId) {
		this.livingAreaId = livingAreaId;
	}


	public String getUid() {
		return uid;
	}


	public void setUid(String uid) {
		this.uid = uid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getNickName() {
		return nickName;
	}


	public void setNickName(String nickName) {
		this.nickName = nickName;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public Date getUpdateDate() {
		return updateDate;
	}


	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public String getCustomerState() {
		return customerState;
	}


	public void setCustomerState(String customerState) {
		this.customerState = customerState;
	}


	@Override
	public void createDTOfromPo(Object entity) {
		// TODO Auto-generated method stub
		Customer customer = (Customer) entity;
		WuyiBeanUtil.CopyPropertiesNotNull(customer, this);
		if(customer.getCustomerLevel()!=null)
			setCustomerLevelId(customer.getCustomerLevel().getId());
		if(customer.getLivingArea()!=null)
			setLivingAreaId(customer.getLivingArea().getId());
	}
	
	
}
