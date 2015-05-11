package wuyi.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import wuyi.exception.WuyiException;
import wuyi.model.DTO.CommentDTO;
import wuyi.model.po.Comment;
import wuyi.model.request.CommentRequest;
import wuyi.model.response.ApiResponse;
import wuyi.model.response.PagedData;
import wuyi.service.CommentService;
import wuyi.util.validator.WuyiValidator;

@RestController
public class CommentController {

	@Autowired
	CommentService commentService;
	
	WuyiValidator<Comment> validator = new WuyiValidator<Comment>(Comment.class);
	WuyiValidator<CommentRequest> commentRequestValidator = new WuyiValidator<CommentRequest>(CommentRequest.class);
	
	@RequestMapping(value="/api/comments/search",method=RequestMethod.GET)
	public ApiResponse getCustomerComments(@RequestParam(value = "type",required=false) String type,
			@RequestParam(value = "scores",required=false,defaultValue="-1") int scores,
			@RequestParam(value = "state",required=false,defaultValue="") String state,
			@RequestParam(value = "createDate",required=false) Date createDate,
			@RequestParam(value = "customerId",required=false) Long customerId,
			@RequestParam(value = "goodsId",required=false) Long goodsId,
			@RequestParam(value = "propertyRequestId",required=false) Long propertyRequestId,
			@RequestParam(value = "orderField",required=false) String orderField,
			@RequestParam(value="page",defaultValue="1") int page,
			@RequestParam(value="pagesize",defaultValue="5") int pagesize){
		PagedData searchResult = commentService.searchComment(type, scores, state, createDate, customerId, propertyRequestId, goodsId, page, pagesize, orderField);
		Map data = new HashMap();
		
		List<Comment> comments = searchResult.getDatas();
		List<CommentDTO> commentDTOs = new ArrayList<CommentDTO>();
		for(Comment c:comments){
			CommentDTO cdto = new CommentDTO();
			cdto.createDTOfromPo(c);
			commentDTOs.add(cdto);
		}
		
		data.put("comments", commentDTOs);
		data.put("total", searchResult.getTotal());
		return ApiResponse.buildSuccessResponse("Search comments result!", data);
	}
	
	@RequestMapping(value="/api/comment/create",method=RequestMethod.POST)
	public ApiResponse createComment(@RequestBody CommentRequest param) throws Exception{
		String[] requiredParam = {"customerId","scores","content"}; 
		String[] requiredNotAllNullParam = {"propertyRequestId","goodsId"};
		commentRequestValidator.validateNoNull(param, requiredParam);
		commentRequestValidator.validateNoAllNull(param, requiredNotAllNullParam);
		
		Long customerId = param.getCustomerId();
		Long propertyRequestId = param.getPropertyRequestId();
		Long goodsId = param.getGoodsId();
		Comment comment = new Comment();
		comment.setContent(param.getContent());
		comment.setImages(param.getImages());
		comment.setScores(param.getScores());
		comment.setType(param.getType());
		try {
			Comment resturnComment = commentService.createComment(comment,customerId, propertyRequestId, goodsId);
			Map result = new HashMap();
			result.put("customer", resturnComment);
			return ApiResponse.buildSuccessResponse("Create comment success", result);
		} catch (Exception e) {
			// TODO: handle exception
			throw new WuyiException("Get comment error!",e);
		}
			
	}
	
	@RequestMapping(value="/api/comment/{id}",method=RequestMethod.GET)
	public ApiResponse getCommentById(@PathVariable(value="id") Long id) throws Exception{
		Comment comment;
		try {
			comment = commentService.getComment(id);
			CommentDTO cdto = new CommentDTO();
			cdto.createDTOfromPo(comment);
			Map result = new HashMap();
			result.put("comment", cdto);
			
			return ApiResponse.buildSuccessResponse("search comment result.", result);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WuyiException("Get comment error!",e);
		}
		
	}
	@RequestMapping(value="/api/comment/{id}/update",method=RequestMethod.POST)
	public ApiResponse updateComment(@PathVariable(value="id") Long id
								,@RequestBody CommentRequest param) throws WuyiException{
		Comment comment = new Comment();
		comment.setId(id);
		comment.setContent(param.getContent());
		comment.setImages(param.getImages());
		comment.setScores(param.getScores());
		try {
			boolean result = commentService.updateComment(comment);
			if(result){
				return ApiResponse.buildSuccessResponse("Create comment success", null);
			}else{
				throw new WuyiException("Update comment error!");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new WuyiException("Update comment error!",e);
		}
			
	}
	
	@RequestMapping(value="/api/comment/{id}/delete",method=RequestMethod.POST)
	public ApiResponse deleteComment(@PathVariable(value="id") Long id) throws WuyiException{
		try {
			boolean result = commentService.deleteComment(id);
			if(result){
				return ApiResponse.buildSuccessResponse("delete comment success", null);
			}else{
				throw new WuyiException("delete comment error!");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new WuyiException("delete comment error!",e);
		}


	}
	
	@RequestMapping(value="/api/comment/{id}/toggle",method=RequestMethod.POST)
	public ApiResponse toggleComment(@PathVariable(value="id") Long id) throws WuyiException{
		try {
			boolean result = commentService.toggleComment(id);
			if(result){
				return ApiResponse.buildSuccessResponse("toggle comment success", null);
			}else{
				throw new WuyiException("toggle comment error!");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new WuyiException("toggle comment error!",e);
		}


	}
	
}
