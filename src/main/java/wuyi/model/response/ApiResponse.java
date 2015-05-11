package wuyi.model.response;

import java.util.Map;

public class ApiResponse {
	
	public static final String SUCCESS = "Success";
	
	public static final String ERROR = "Error";
	
	private String status;
	
	private String description;
	
	private Map data;
	
	public ApiResponse(){}
	
	public ApiResponse(String status,String description,Map data) {
		this.status = status;
		this.description = description;
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map getData() {
		return data;
	}

	public void setData(Map data) {
		this.data = data;
	}
	
	public static ApiResponse buildSuccessResponse(String description,Map data){
		return new ApiResponse(ApiResponse.SUCCESS, description, data);
	}
	
	public static ApiResponse buildErrorResponse(String description,Map data){
		return new ApiResponse(ApiResponse.ERROR, description, data);
	}
}
