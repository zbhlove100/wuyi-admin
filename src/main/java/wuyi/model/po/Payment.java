package wuyi.model.po;

// Generated May 5, 2015 9:16:55 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
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
 * Payment generated by hbm2java
 */
@Entity
@Table(name = "payment", catalog = "wuyitech")
public class Payment implements java.io.Serializable {

	private Long id;
	private OrderInfo orderInfo;
	private String payCode;
	private String payName;
	private String payDesc;
	private String paymentState;
	private String paymentMethod;
	private String paymentRelateCode;
	private BigDecimal goodsPrice;
	private BigDecimal discountPrice;
	private BigDecimal payPrice;
	private Date createDate;
	private Date updateDate;
	private String state;

	public Payment() {
	}

	public Payment(OrderInfo orderInfo, String payCode, String payName,
			String payDesc, String paymentState, String paymentMethod,
			String paymentRelateCode, BigDecimal goodsPrice,
			BigDecimal discountPrice, BigDecimal payPrice, Date createDate,
			Date updateDate, String state) {
		this.orderInfo = orderInfo;
		this.payCode = payCode;
		this.payName = payName;
		this.payDesc = payDesc;
		this.paymentState = paymentState;
		this.paymentMethod = paymentMethod;
		this.paymentRelateCode = paymentRelateCode;
		this.goodsPrice = goodsPrice;
		this.discountPrice = discountPrice;
		this.payPrice = payPrice;
		this.createDate = createDate;
		this.updateDate = updateDate;
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
	@JoinColumn(name = "order_info_id")
	public OrderInfo getOrderInfo() {
		return this.orderInfo;
	}

	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}

	@Column(name = "pay_code", length = 45)
	public String getPayCode() {
		return this.payCode;
	}

	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}

	@Column(name = "pay_name", length = 45)
	public String getPayName() {
		return this.payName;
	}

	public void setPayName(String payName) {
		this.payName = payName;
	}

	@Column(name = "pay_desc", length = 2000)
	public String getPayDesc() {
		return this.payDesc;
	}

	public void setPayDesc(String payDesc) {
		this.payDesc = payDesc;
	}

	@Column(name = "payment_state", length = 45)
	public String getPaymentState() {
		return this.paymentState;
	}

	public void setPaymentState(String paymentState) {
		this.paymentState = paymentState;
	}

	@Column(name = "payment_method", length = 45)
	public String getPaymentMethod() {
		return this.paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Column(name = "payment_relate_code")
	public String getPaymentRelateCode() {
		return this.paymentRelateCode;
	}

	public void setPaymentRelateCode(String paymentRelateCode) {
		this.paymentRelateCode = paymentRelateCode;
	}

	@Column(name = "goods_price", precision = 10)
	public BigDecimal getGoodsPrice() {
		return this.goodsPrice;
	}

	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	@Column(name = "discount_price", precision = 10)
	public BigDecimal getDiscountPrice() {
		return this.discountPrice;
	}

	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}

	@Column(name = "pay_price", precision = 10)
	public BigDecimal getPayPrice() {
		return this.payPrice;
	}

	public void setPayPrice(BigDecimal payPrice) {
		this.payPrice = payPrice;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", length = 19)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date", length = 19)
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "state", length = 45)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}