package wuyi.model.po;

// Generated May 5, 2015 9:16:55 PM by Hibernate Tools 4.3.1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ComplaintHistory generated by hbm2java
 */
@Entity
@Table(name = "complaint_history", catalog = "wuyitech")
public class ComplaintHistory implements java.io.Serializable {

	private Long id;
	private Complaint complaint;
	private String comment;
	private Date updateDate;
	private String currentState;
	private String state;
	private Long workerId;

	public ComplaintHistory() {
	}

	public ComplaintHistory(Complaint complaint, String comment,
			Date updateDate, String currentState, String state, Long workerId) {
		this.complaint = complaint;
		this.comment = comment;
		this.updateDate = updateDate;
		this.currentState = currentState;
		this.state = state;
		this.workerId = workerId;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "complaint_id")
	public Complaint getComplaint() {
		return this.complaint;
	}

	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}

	@Column(name = "comment", length = 500)
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date", length = 19)
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "current_state", length = 45)
	public String getCurrentState() {
		return this.currentState;
	}

	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}

	@Column(name = "state", length = 45)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "worker_id")
	public Long getWorkerId() {
		return this.workerId;
	}

	public void setWorkerId(Long workerId) {
		this.workerId = workerId;
	}

}
