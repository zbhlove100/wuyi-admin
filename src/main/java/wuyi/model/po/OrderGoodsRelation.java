package wuyi.model.po;

// Generated May 5, 2015 9:16:55 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * OrderGoodsRelation generated by hbm2java
 */
@Entity
@Table(name = "order_goods_relation", catalog = "wuyitech")
public class OrderGoodsRelation implements java.io.Serializable {

	private Long id;
	private GoodsSku goodsSku;
	private SubOrderInfo subOrderInfo;
	private Integer number;
	private BigDecimal price;

	public OrderGoodsRelation() {
	}

	public OrderGoodsRelation(SubOrderInfo subOrderInfo) {
		this.subOrderInfo = subOrderInfo;
	}

	public OrderGoodsRelation(GoodsSku goodsSku, SubOrderInfo subOrderInfo,
			Integer number, BigDecimal price) {
		this.goodsSku = goodsSku;
		this.subOrderInfo = subOrderInfo;
		this.number = number;
		this.price = price;
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
	@JoinColumn(name = "goods_sku_id")
	public GoodsSku getGoodsSku() {
		return this.goodsSku;
	}

	public void setGoodsSku(GoodsSku goodsSku) {
		this.goodsSku = goodsSku;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sub_order_info_id", nullable = false)
	public SubOrderInfo getSubOrderInfo() {
		return this.subOrderInfo;
	}

	public void setSubOrderInfo(SubOrderInfo subOrderInfo) {
		this.subOrderInfo = subOrderInfo;
	}

	@Column(name = "number")
	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Column(name = "price", precision = 10)
	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}