package wuyi.model.DTO;

import java.util.Date;

import wuyi.model.po.Supplier;
import wuyi.util.WuyiBeanUtil;

public class SupplierDTO extends BaseDTO{

	private Long id;
	private String name;
	private String username;
	private String password;
	private String state;
	private Date createDate;
	public SupplierDTO() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
		// TODO Auto-generated method stub
		Supplier supplier = (Supplier)entity;
		WuyiBeanUtil.CopyPropertiesNotNull(supplier, this);
		
	}
	
	
}
