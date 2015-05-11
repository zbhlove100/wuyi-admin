package wuyi.model.request;

import wuyi.model.po.CustomerLevel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CustomerLevelRequest {
	private CustomerLevel customerLevel;

	public CustomerLevel getCustomerLevel() {
		return customerLevel;
	}

	public void setCustomerLevel(CustomerLevel customerLevel) {
		this.customerLevel = customerLevel;
	}
	
}
