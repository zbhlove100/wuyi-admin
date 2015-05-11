package wuyi.service.impl;

import java.util.Arrays;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wuyi.dao.CommentDao;
import wuyi.exception.DataException;
import wuyi.model.po.Comment;
import wuyi.model.po.Customer;
import wuyi.model.po.Goods;
import wuyi.model.po.PropertyRequest;
import wuyi.model.response.PagedData;
import wuyi.service.CommentService;
import wuyi.util.BaseModelState;
import wuyi.util.BaseModelType;
import wuyi.util.WuyiBeanUtil;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
@Service
public class CommentServiceImpl extends GenericManagerImpl implements CommentService{
	
	@Autowired
	CommentDao commentDao;

	@Override
	public Comment getComment(Long commentId) throws Exception {
		Comment comment = commentDao.findActive(commentId);
		if(comment==null)
			throw new DataException("Can't find comment with this id!");
		return comment;
	}

	@Override
	@Transactional
	public Comment createComment(Comment comment,Long customerId, Long propertyRequestId,
			Long goodsId) throws Exception {
		// TODO Auto-generated method stub
		int num = getCommentNum(customerId, propertyRequestId, goodsId);
		if(num>0){
			throw new DataException("Only can comment one time!");
		}
		
		Date createDate = new Date(System.currentTimeMillis());
		comment.setCreateDate(createDate);
		comment.setState(BaseModelState.ACTIVE);
		
		if(comment.getScores()<0){
			comment.setScores(0);
		}else if(comment.getScores()>5){
			comment.setScores(5);
		}
		Customer customer = new Customer();
		customer.setId(customerId);
		comment.setCustomer(customer);
		
		if(propertyRequestId!=null){
			createCommentWithPropertyRequestId(comment, propertyRequestId);
		}else if(goodsId!=null){
			createCommentWithGoodsId(comment, goodsId);
		}
		return comment;
	}
	
	
	private void createCommentWithPropertyRequestId(Comment comment, Long propertyRequestId){
		PropertyRequest propertyRequest = new PropertyRequest();
		propertyRequest.setId(propertyRequestId);
		comment.setPropertyRequest(propertyRequest);
		comment.setType(BaseModelType.COMMENT_TYPE_PROPERTY_REQUEST);
		
		
		commentDao.save(comment);
	}
	
	private void createCommentWithGoodsId(Comment comment, Long goodsId){
		Goods goods = new Goods();
		goods.setId(goodsId);
		comment.setGoods(goods);
		comment.setType(BaseModelType.COMMENT_TYPE_GOODS);
		commentDao.save(comment);
	}
	
	@Override
	@Transactional
	public boolean deleteComment(Long commentId) throws DataException {
		// TODO Auto-generated method stub
		Comment comment = commentDao.findActive(commentId);
		if(comment==null)
			throw new DataException("Can't find comment with this id!");
		
		comment.setState(BaseModelState.DELETE);
		commentDao.flush();
		return true;
	}

	@Override
	@Transactional
	public PagedData searchComment(String type,int scores,String state,Date createDate,
			Long customerId, Long propertyRequestId,
			Long goodsId, int page, int pagesize,String orderField) {
		
		page= page==0?DEFAULT_PAGE:page; 
		pagesize = pagesize==0?DEFAULT_PAGESIZE:pagesize; 
		
		Search search = new Search(Comment.class);
		search.setFirstResult(0);
		search.setPage((page-1));
		search.setMaxResults(pagesize);
		
		search.addFilterNotEqual("state", BaseModelState.DELETE);
		
		if(type!=null&&!"".equals(type)){
			search.addFilterEqual("type", type);
		}
		if(scores>0&&scores<6){
			search.addFilterEqual("scores", scores);
		}
		if(state!=null&&!"".equals(state)){
			search.addFilterEqual("state", state);
		}
		if(createDate!=null){
			search.addFilterGreaterOrEqual("createDate", createDate);
		}
		if(customerId!=null){
			search.addFilterEqual("customer.id", customerId);
		}
		if(propertyRequestId!=null){
			search.addFilterEqual("propertyRequest.id", propertyRequestId);
		}
		if(goodsId!=null){
			search.addFilterEqual("goods.id", goodsId);
		}
		if(orderField!=null&&!"".equals(orderField)){
			search.addSortDesc("id");
		}else{
			search.addSortDesc(orderField);
		}
		
		SearchResult searchResult = commentDao.searchAndCount(search);
		PagedData result = new PagedData();
		result.setTotal(searchResult.getTotalCount());
		result.setDatas(searchResult.getResult());
		result.setPage(page);
		result.setPagesize(pagesize);
		return result;
		
	}

	@Override
	@Transactional
	public boolean updateComment(Comment comment) throws Exception {
		// TODO Auto-generated method stub
		String[] updateProperty = {"content","image","scores"};
		
		Date updateDate = new Date(System.currentTimeMillis());
		Comment persistComment = commentDao.find(comment.getId());
		
		WuyiBeanUtil.copySpecPropertiesNotNull(comment, persistComment, Arrays.asList(updateProperty));
		
		WuyiBeanUtil.CopyPropertiesNotNull(comment, persistComment);
		persistComment.setUpdateDate(updateDate);
		commentDao.update(persistComment);
		return true;
	}

	@Override
	@Transactional
	public int getCommentNum(Long customerId, Long propertyRequestId,
			Long goodsId) {
		Search search = new Search(Comment.class);
		search.addFilterNotEqual("state", BaseModelState.DELETE);
		if(customerId!=null){
			search.addFilterEqual("customer.id", customerId);
		}
		if(propertyRequestId!=null){
			search.addFilterEqual("propertyRequest.id", propertyRequestId);
		}
		if(goodsId!=null){
			search.addFilterEqual("goods.id", goodsId);
		}
		
		return commentDao.count(search);
	}

	@Override
	@Transactional
	public boolean toggleComment(Long commentId) throws Exception {
		// TODO Auto-generated method stub
		Comment comment = commentDao.findActive(commentId);
		if(comment==null)
			throw new DataException("Can't find comment with this id!");
		if(comment.getState().equals(BaseModelState.HIDDEN)){
			comment.setState(BaseModelState.ACTIVE);
		}else if(comment.getState().equals(BaseModelState.ACTIVE)){
			comment.setState(BaseModelState.HIDDEN);
		}
		commentDao.flush();
		return true;
	}

}
