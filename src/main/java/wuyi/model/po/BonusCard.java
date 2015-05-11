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
 * BonusCard generated by hbm2java
 */
@Entity
@Table(name = "bonus_card", catalog = "wuyitech")
public class BonusCard implements java.io.Serializable {

	private Long id;
	private BonusCardType bonusCardType;
	private Customer customer;
	private String cardSn;
	private Date createDate;

	public BonusCard() {
	}

	public BonusCard(BonusCardType bonusCardType, Customer customer,
			String cardSn, Date createDate) {
		this.bonusCardType = bonusCardType;
		this.customer = customer;
		this.cardSn = cardSn;
		this.createDate = createDate;
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
	@JoinColumn(name = "bonus_card_type_id")
	public BonusCardType getBonusCardType() {
		return this.bonusCardType;
	}

	public void setBonusCardType(BonusCardType bonusCardType) {
		this.bonusCardType = bonusCardType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Column(name = "card_sn", length = 45)
	public String getCardSn() {
		return this.cardSn;
	}

	public void setCardSn(String cardSn) {
		this.cardSn = cardSn;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", length = 19)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}