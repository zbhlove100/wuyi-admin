package wuyi.model.response;

import java.util.List;

public class ErrorResponse {
	private String code;
	private String message;
	private String description;
	private String moreInfo;
	private List<ErrorField> errors;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMoreInfo() {
		return moreInfo;
	}
	public void setMoreInfo(String moreInfo) {
		this.moreInfo = moreInfo;
	}
	public List<ErrorField> getErrors() {
		return errors;
	}
	public void setErrors(List<ErrorField> errors) {
		this.errors = errors;
	}
	
	
}
