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
 * CustomerCollectGoods generated by hbm2java
 */
@Entity
@Table(name = "customer_collect_goods", catalog = "wuyitech")
public class CustomerCollectGoods implements java.io.Serializable {

	private Long id;
	private Customer customer;
	private GoodsSku goodsSku;
	private String state;
	private Date createDate;

	public CustomerCollectGoods() {
	}

	public CustomerCollectGoods(Customer customer, GoodsSku goodsSku,
			String state, Date createDate) {
		this.customer = customer;
		this.goodsSku = goodsSku;
		this.state = state;
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
	@JoinColumn(name = "customer_id")
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "goods_sku_id")
	public GoodsSku getGoodsSku() {
		return this.goodsSku;
	}

	public void setGoodsSku(GoodsSku goodsSku) {
		this.goodsSku = goodsSku;
	}

	@Column(name = "state", length = 45)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
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
