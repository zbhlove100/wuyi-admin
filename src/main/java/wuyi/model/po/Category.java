package wuyi.model.po;

// Generated May 5, 2015 9:16:55 PM by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Category generated by hbm2java
 */
@Entity
@Table(name = "category", catalog = "wuyitech")
public class Category implements java.io.Serializable {

	private Long ig;
	private String name;
	private String type;
	private String description;
	private String imageUrl;
	private String state;
	private Date createDate;
	private Date updateDate;
	private Set<PromotionCategoryRelation> promotionCategoryRelations = new HashSet<PromotionCategoryRelation>(
			0);
	private Set<CategoryRelation> categoryRelations = new HashSet<CategoryRelation>(
			0);
	private Set<SkuAttribute> skuAttributes = new HashSet<SkuAttribute>(0);
	private Set<GoodsCategoryRelation> goodsCategoryRelations = new HashSet<GoodsCategoryRelation>(
			0);

	public Category() {
	}

	public Category(String name, String type, String description,
			String imageUrl, String state, Date createDate, Date updateDate,
			Set<PromotionCategoryRelation> promotionCategoryRelations,
			Set<CategoryRelation> categoryRelations,
			Set<SkuAttribute> skuAttributes,
			Set<GoodsCategoryRelation> goodsCategoryRelations) {
		this.name = name;
		this.type = type;
		this.description = description;
		this.imageUrl = imageUrl;
		this.state = state;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.promotionCategoryRelations = promotionCategoryRelations;
		this.categoryRelations = categoryRelations;
		this.skuAttributes = skuAttributes;
		this.goodsCategoryRelations = goodsCategoryRelations;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ig", unique = true, nullable = false)
	public Long getIg() {
		return this.ig;
	}

	public void setIg(Long ig) {
		this.ig = ig;
	}

	@Column(name = "name", length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "type", length = 45)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "image_url")
	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date", length = 19)
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	public Set<PromotionCategoryRelation> getPromotionCategoryRelations() {
		return this.promotionCategoryRelations;
	}

	public void setPromotionCategoryRelations(
			Set<PromotionCategoryRelation> promotionCategoryRelations) {
		this.promotionCategoryRelations = promotionCategoryRelations;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	public Set<CategoryRelation> getCategoryRelations() {
		return this.categoryRelations;
	}

	public void setCategoryRelations(Set<CategoryRelation> categoryRelations) {
		this.categoryRelations = categoryRelations;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	public Set<SkuAttribute> getSkuAttributes() {
		return this.skuAttributes;
	}

	public void setSkuAttributes(Set<SkuAttribute> skuAttributes) {
		this.skuAttributes = skuAttributes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	public Set<GoodsCategoryRelation> getGoodsCategoryRelations() {
		return this.goodsCategoryRelations;
	}

	public void setGoodsCategoryRelations(
			Set<GoodsCategoryRelation> goodsCategoryRelations) {
		this.goodsCategoryRelations = goodsCategoryRelations;
	}

}