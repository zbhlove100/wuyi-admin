package wuyi.model.DTO;

import java.util.Date;

import wuyi.model.po.Comment;
import wuyi.util.WuyiBeanUtil;

public class CommentDTO extends BaseDTO{
	private Long id;
	private Long customerId;
	private Long goodsId;
	private Long propertyRequestId;
	private String type;
	private String content;
	private String images;
	private Integer scores;
	private String state;
	private Date createDate;
	private Date updateDate;
	
	public CommentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommentDTO(Long id, Long customerId, Long goodsId,
			Long propertyRequestId, String type, String content, String images,
			Integer scores, String state, Date createDate, Date updateDate) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.goodsId = goodsId;
		this.propertyRequestId = propertyRequestId;
		this.type = type;
		this.content = content;
		this.images = images;
		this.scores = scores;
		this.state = state;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public Long getPropertyRequestId() {
		return propertyRequestId;
	}

	public void setPropertyRequestId(Long propertyRequestId) {
		this.propertyRequestId = propertyRequestId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Integer getScores() {
		return scores;
	}

	public void setScores(Integer scores) {
		this.scores = scores;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public void createDTOfromPo(Object entity) {
		Comment comment = (Comment) entity;
		WuyiBeanUtil.CopyPropertiesNotNull(comment, this);
		setCustomerId(comment.getCustomer().getId());
		if(comment.getGoods()!=null)
			setGoodsId(comment.getGoods().getId());
		if(comment.getPropertyRequest()!=null)
			setPropertyRequestId(comment.getPropertyRequest().getId());
		
	}
	
	
}
