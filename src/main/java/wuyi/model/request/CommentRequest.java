package wuyi.model.request;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CommentRequest {
	private Long customerId;
	private Long goodsId;
	private Long propertyRequestId;
	private String type;
	private String content;
	private String images;
	private Integer scores;
	
	
	public CommentRequest() {
	}

	public CommentRequest(Long customerId, Long goodsId,
			Long propertyRequestId, String type, String content, String images,
			Integer scores) {
		super();
		this.customerId = customerId;
		this.goodsId = goodsId;
		this.propertyRequestId = propertyRequestId;
		this.type = type;
		this.content = content;
		this.images = images;
		this.scores = scores;
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
	
	
}
