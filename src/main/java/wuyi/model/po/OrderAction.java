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
 * OrderAction generated by hbm2java
 */
@Entity
@Table(name = "order_action", catalog = "wuyitech")
public class OrderAction implements java.io.Serializable {

	private Long id;
	private SubOrderInfo subOrderInfo;
	private String actionUserRole;
	private String actionUserName;
	private Long actionUserId;
	private String actionMessage;
	private Date createDate;
	private String state;

	public OrderAction() {
	}

	public OrderAction(SubOrderInfo subOrderInfo, String actionUserRole,
			String actionUserName, Long actionUserId, String actionMessage,
			Date createDate, String state) {
		this.subOrderInfo = subOrderInfo;
		this.actionUserRole = actionUserRole;
		this.actionUserName = actionUserName;
		this.actionUserId = actionUserId;
		this.actionMessage = actionMessage;
		this.createDate = createDate;
		this.state = state;
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
	@JoinColumn(name = "sub_order_info_id")
	public SubOrderInfo getSubOrderInfo() {
		return this.subOrderInfo;
	}

	public void setSubOrderInfo(SubOrderInfo subOrderInfo) {
		this.subOrderInfo = subOrderInfo;
	}

	@Column(name = "action_user_role", length = 45)
	public String getActionUserRole() {
		return this.actionUserRole;
	}

	public void setActionUserRole(String actionUserRole) {
		this.actionUserRole = actionUserRole;
	}

	@Column(name = "action_user_name", length = 45)
	public String getActionUserName() {
		return this.actionUserName;
	}

	public void setActionUserName(String actionUserName) {
		this.actionUserName = actionUserName;
	}

	@Column(name = "action_user_id")
	public Long getActionUserId() {
		return this.actionUserId;
	}

	public void setActionUserId(Long actionUserId) {
		this.actionUserId = actionUserId;
	}

	@Column(name = "action_message", length = 500)
	public String getActionMessage() {
		return this.actionMessage;
	}

	public void setActionMessage(String actionMessage) {
		this.actionMessage = actionMessage;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", length = 19)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "state", length = 45)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}