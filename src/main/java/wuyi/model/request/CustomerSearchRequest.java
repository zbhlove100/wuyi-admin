package wuyi.model.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class CustomerSearchRequest {
	private String name;
	private String nickname;
	private String customerState;
	private String phone;
	private Date beforeDate;
	private Date afterDate;
	private Long cityId;
	private Long livingAreaId;
	private int page;
	private int pagesize;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getCustomerState() {
		return customerState;
	}
	public void setCustomerState(String customerState) {
		this.customerState = customerState;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getBeforeDate() {
		return beforeDate;
	}
	public void setBeforeDate(Date beforeDate) {
		this.beforeDate = beforeDate;
	}
	public Date getAfterDate() {
		return afterDate;
	}
	public void setAfterDate(Date afterDate) {
		this.afterDate = afterDate;
	}
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	public Long getLivingAreaId() {
		return livingAreaId;
	}
	public void setLivingAreaId(Long livingAreaId) {
		this.livingAreaId = livingAreaId;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	
	
}
