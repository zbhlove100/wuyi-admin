package wuyi.model.DTO;

import wuyi.model.po.CustomerLevel;
import wuyi.util.WuyiBeanUtil;

public class CustomerLevelDTO extends BaseDTO{

	private Long id;
	private Integer level;
	private String name;
	private String state;
	public CustomerLevelDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public void createDTOfromPo(Object entity) {
		// TODO Auto-generated method stub
		CustomerLevel customerLevel = (CustomerLevel) entity;
		WuyiBeanUtil.CopyPropertiesNotNull(customerLevel, this);
	}
	
	
}
