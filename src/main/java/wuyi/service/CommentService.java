package wuyi.service;

import java.util.Date;

import wuyi.exception.DataException;
import wuyi.model.po.Comment;
import wuyi.model.response.PagedData;

public interface CommentService {
	
	public Comment getComment(Long commentId) throws Exception;
	
	public Comment createComment(Comment comment,Long customerId,Long propertyRequestId,Long goodsId) throws Exception;
	
	public boolean deleteComment(Long commentId) throws DataException;
	
	public PagedData searchComment(String type,int scores,String state,
						Date createDate,Long customerId,Long propertyRequestId,
						Long goodsId,int page,int pagesize,String orderField);
	
	public boolean toggleComment(Long commentId) throws Exception;

	public boolean updateComment(Comment comment) throws Exception;
	
	public int getCommentNum(Long customerId,Long propertyRequestId,Long goodsId);
}
