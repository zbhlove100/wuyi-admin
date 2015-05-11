package wuyi.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ComplaintRequest {
	
	private Long customerId;
	private String name;
	private String content;
	
	public ComplaintRequest() {
	}

	public ComplaintRequest( Long customerId, String name,
			String content) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.content = content;
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
	
}
