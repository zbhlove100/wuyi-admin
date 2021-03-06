package wuyi.model.po;

// Generated May 5, 2015 9:16:55 PM by Hibernate Tools 4.3.1

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
 * SkuAttribute generated by hbm2java
 */
@Entity
@Table(name = "sku_attribute", catalog = "wuyitech")
public class SkuAttribute implements java.io.Serializable {

	private Long id;
	private Category category;
	private String attributeName;
	private Integer attributeShowType;
	private String description;
	private Set<GoodsSkuAttributeRelation> goodsSkuAttributeRelations = new HashSet<GoodsSkuAttributeRelation>(
			0);

	public SkuAttribute() {
	}

	public SkuAttribute(Category category) {
		this.category = category;
	}

	public SkuAttribute(Category category, String attributeName,
			Integer attributeShowType, String description,
			Set<GoodsSkuAttributeRelation> goodsSkuAttributeRelations) {
		this.category = category;
		this.attributeName = attributeName;
		this.attributeShowType = attributeShowType;
		this.description = description;
		this.goodsSkuAttributeRelations = goodsSkuAttributeRelations;
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
	@JoinColumn(name = "category_ig", nullable = false)
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Column(name = "attribute_name")
	public String getAttributeName() {
		return this.attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	@Column(name = "attribute_show_type")
	public Integer getAttributeShowType() {
		return this.attributeShowType;
	}

	public void setAttributeShowType(Integer attributeShowType) {
		this.attributeShowType = attributeShowType;
	}

	@Column(name = "description", length = 500)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "skuAttribute")
	public Set<GoodsSkuAttributeRelation> getGoodsSkuAttributeRelations() {
		return this.goodsSkuAttributeRelations;
	}

	public void setGoodsSkuAttributeRelations(
			Set<GoodsSkuAttributeRelation> goodsSkuAttributeRelations) {
		this.goodsSkuAttributeRelations = goodsSkuAttributeRelations;
	}

}
