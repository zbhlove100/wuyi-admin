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
import wuyi.model.DTO.FeedbackDTO;
import wuyi.model.po.Feedback;
import wuyi.model.request.FeedbackRequest;
import wuyi.model.response.ApiResponse;
import wuyi.model.response.PagedData;
import wuyi.service.FeedbackService;
import wuyi.util.validator.WuyiValidator;

@RestController
public class FeedbackController {

	@Autowired
	FeedbackService feedbackService;
	
	WuyiValidator<Feedback> validator = new WuyiValidator<Feedback>(Feedback.class);
	WuyiValidator<FeedbackRequest> feedbackRequestValidator = new WuyiValidator<FeedbackRequest>(FeedbackRequest.class);
	
	@RequestMapping(value="/api/feedbacks/search",method=RequestMethod.GET)
	public ApiResponse getFeedbacks(@RequestParam(value = "createDate",required=false) Date createDate,
			@RequestParam(value = "customerId",required=false) Long customerId,
			@RequestParam(value="page",defaultValue="1") int page,
			@RequestParam(value="pagesize",defaultValue="5") int pagesize){
		PagedData searchResult = feedbackService.searchFeedback(customerId, page, pagesize);
		
		List<Feedback> feedbacks = searchResult.getDatas();
		List<FeedbackDTO> feedbackDTOs = new ArrayList<FeedbackDTO>();
		for(Feedback c:feedbacks){
			FeedbackDTO cdto = new FeedbackDTO();
			cdto.createDTOfromPo(c);
			feedbackDTOs.add(cdto);
		}
		
		Map data = new HashMap();
		data.put("feedbacks", feedbackDTOs);
		data.put("total", searchResult.getTotal());
		return ApiResponse.buildSuccessResponse("Search feedbacks result!", data);
	}
	
	@RequestMapping(value="/api/feedback/create",method=RequestMethod.POST)
	public ApiResponse createFeedback(@RequestBody FeedbackRequest param) throws Exception{
		String[] requiredParam = {"customerId","name","content"}; 
		feedbackRequestValidator.validateNoNull(param, requiredParam);
		
		Long customerId = param.getCustomerId();
		Feedback feedback = new Feedback();
		feedback.setContent(param.getContent());
		feedback.setName(param.getName());
		try {
			Feedback resturnFeedback = feedbackService.createFeedback(feedback, customerId);
			FeedbackDTO cdto = new FeedbackDTO();
			cdto.createDTOfromPo(resturnFeedback);
			Map result = new HashMap();
			result.put("feedback", cdto);
			return ApiResponse.buildSuccessResponse("Create feedback success", result);
		} catch (Exception e) {
			// TODO: handle exception
			throw new WuyiException("Create feedback error!",e);
		}
			
	}
	
	@RequestMapping(value="/api/feedback/{id}",method=RequestMethod.GET)
	public ApiResponse getFeedbackById(@PathVariable(value="id") Long id) throws Exception{
		try {
			Feedback feedback = feedbackService.getFeedback(id);
			FeedbackDTO cdto = new FeedbackDTO();
			cdto.createDTOfromPo(feedback);
			Map result = new HashMap();
			result.put("feedback", cdto);
			
			return ApiResponse.buildSuccessResponse("Get feedback result.", result);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WuyiException("Get feedback error!",e);
		}
		
	}
	@RequestMapping(value="/api/feedback/{id}/update",method=RequestMethod.POST)
	public ApiResponse updateFeedback(@PathVariable(value="id") Long id
								,@RequestBody FeedbackRequest param) throws Exception{
		String[] requiredParam = {"name","content"}; 
		feedbackRequestValidator.validateNoNull(param, requiredParam);
		Feedback feedback = new Feedback();
		feedback.setId(id);
		feedback.setContent(param.getContent());
		feedback.setName(param.getName());
		try {
			boolean result = feedbackService.updateFeedback(feedback);
			if(result){
				return ApiResponse.buildSuccessResponse("Update feedback success", null);
			}else{
				throw new WuyiException("Update feedback error!");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new WuyiException("Update feedback error!",e);
		}
			
	}
	
	@RequestMapping(value="/api/feedback/{id}/delete",method=RequestMethod.POST)
	public ApiResponse deleteFeedback(@PathVariable(value="id") Long id) throws WuyiException{
		try {
			boolean result = feedbackService.deleteFeedback(id);
			if(result){
				return ApiResponse.buildSuccessResponse("delete feedback success", null);
			}else{
				throw new WuyiException("delete feedback error!");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new WuyiException("delete feedback error!",e);
		}


	}
}
