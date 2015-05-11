package wuyi.model.po;

// Generated May 5, 2015 9:16:55 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * RewardAccount generated by hbm2java
 */
@Entity
@Table(name = "reward_account", catalog = "wuyitech")
public class RewardAccount implements java.io.Serializable {

	private Integer id;
	private Customer customer;
	private BigDecimal total;
	private Set<RewardHistory> rewardHistories = new HashSet<RewardHistory>(0);

	public RewardAccount() {
	}

	public RewardAccount(Customer customer, BigDecimal total,
			Set<RewardHistory> rewardHistories) {
		this.customer = customer;
		this.total = total;
		this.rewardHistories = rewardHistories;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Column(name = "total", precision = 10)
	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rewardAccount")
	public Set<RewardHistory> getRewardHistories() {
		return this.rewardHistories;
	}

	public void setRewardHistories(Set<RewardHistory> rewardHistories) {
		this.rewardHistories = rewardHistories;
	}

}