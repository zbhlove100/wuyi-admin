package wuyi.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ComplaintProcessRequest {

	private Long complaintId;
	private Long workerId;
	private String comment;
	public ComplaintProcessRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ComplaintProcessRequest(Long complaintId, Long workerId,
			String comment) {
		super();
		this.complaintId = complaintId;
		this.workerId = workerId;
		this.comment = comment;
	}
	public Long getComplaintId() {
		return complaintId;
	}
	public void setComplaintId(Long complaintId) {
		this.complaintId = complaintId;
	}
	public Long getWorkerId() {
		return workerId;
	}
	public void setWorkerId(Long workerId) {
		this.workerId = workerId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
