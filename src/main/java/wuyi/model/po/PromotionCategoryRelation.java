package wuyi.model.po;

// Generated May 5, 2015 9:16:55 PM by Hibernate Tools 4.3.1

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
 * PromotionCategoryRelation generated by hbm2java
 */
@Entity
@Table(name = "promotion_category_relation", catalog = "wuyitech")
public class PromotionCategoryRelation implements java.io.Serializable {

	private Long id;
	private Category category;
	private Promotion promotion;

	public PromotionCategoryRelation() {
	}

	public PromotionCategoryRelation(Category category, Promotion promotion) {
		this.category = category;
		this.promotion = promotion;
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
	@JoinColumn(name = "category_ig")
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "promotion_id")
	public Promotion getPromotion() {
		return this.promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

}
