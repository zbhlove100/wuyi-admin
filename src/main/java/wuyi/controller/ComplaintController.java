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
import wuyi.model.DTO.ComplaintDTO;
import wuyi.model.po.Complaint;
import wuyi.model.request.ComplaintProcessRequest;
import wuyi.model.request.ComplaintRequest;
import wuyi.model.response.ApiResponse;
import wuyi.model.response.PagedData;
import wuyi.service.ComplaintService;
import wuyi.util.validator.WuyiValidator;

@RestController
public class ComplaintController {

	@Autowired
	ComplaintService complaintService;
	
	WuyiValidator<Complaint> validator = new WuyiValidator<Complaint>(Complaint.class);
	WuyiValidator<ComplaintRequest> complaintRequestValidator = new WuyiValidator<ComplaintRequest>(ComplaintRequest.class);
	WuyiValidator<ComplaintProcessRequest> complaintProcessRequestValidator = new WuyiValidator<ComplaintProcessRequest>(ComplaintProcessRequest.class);

	@RequestMapping(value="/api/complaints/search",method=RequestMethod.GET)
	public ApiResponse getcomplaints(@RequestParam(value = "createDate",required=false) Date createDate,
			@RequestParam(value = "customerId",required=false) Long customerId,
			@RequestParam(value="page",defaultValue="1") int page,
			@RequestParam(value="pagesize",defaultValue="5") int pagesize){
		PagedData searchResult = complaintService.searchComplaint(customerId, createDate, page, pagesize);
		
		List<Complaint> complaints = searchResult.getDatas();
		List<ComplaintDTO> complaintDTOs = new ArrayList<ComplaintDTO>();
		for(Complaint c:complaints){
			ComplaintDTO cdto = new ComplaintDTO();
			cdto.createDTOfromPo(c);
			complaintDTOs.add(cdto);
		}
		
		Map data = new HashMap();
		data.put("complaint", complaintDTOs);
		data.put("total", searchResult.getTotal());
		return ApiResponse.buildSuccessResponse("Search complaints result!", data);
	}
	
	@RequestMapping(value="/api/complaint/create",method=RequestMethod.POST)
	public ApiResponse createComplaint(@RequestBody ComplaintRequest param) throws Exception{
		String[] requiredParam = {"customerId","name","content"}; 
		complaintRequestValidator.validateNoNull(param, requiredParam);
		
		Long customerId = param.getCustomerId();
		Complaint complaint = new Complaint();
		complaint.setContent(param.getContent());
		complaint.setName(param.getName());
		try {
			Complaint resturnComplaint = complaintService.createComplaint(complaint, customerId);
			ComplaintDTO cdto = new ComplaintDTO();
			cdto.createDTOfromPo(complaint);
			Map result = new HashMap();
			result.put("", cdto);
			return ApiResponse.buildSuccessResponse("Create complaint success", result);
		} catch (Exception e) {
			// TODO: handle exception
			throw new WuyiException("Get complaint error!",e);
		}
			
	}
	@RequestMapping(value="/api/complaint/{id}",method=RequestMethod.GET)
	public ApiResponse getComplaintById(@PathVariable(value="id") Long id) throws Exception{
		Complaint complaint;
		try {
			complaint = complaintService.getComplaint(id);
			ComplaintDTO cdto = new ComplaintDTO();
			cdto.createDTOfromPo(complaint);
			Map result = new HashMap();
			result.put("complaint", cdto);
			
			return ApiResponse.buildSuccessResponse("Get complaint result.", result);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WuyiException("Get complaint error!",e);
		}
		
	}
	
	@RequestMapping(value="/api/complaint/{id}/delete",method=RequestMethod.POST)
		
	public ApiResponse deleteComplaint(@PathVariable(value="id") Long id) throws WuyiException{
		try {
			boolean result = complaintService.deleteComplaint(id);
			if(result){
				return ApiResponse.buildSuccessResponse("delete complaint success", null);
			}else{
				throw new WuyiException("delete complaint error!");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new WuyiException("delete complaint error!",e);
		}


	}
	
	
	@RequestMapping(value="/api/complaint/{id}/accept",method=RequestMethod.POST)
	public ApiResponse acceptComplaint(@PathVariable(value="id") Long id,
							@RequestBody ComplaintProcessRequest complaintProcessRequest) throws WuyiException{
		try {
			String[] requiredParam = {"complaintId","workerId"}; 
			complaintProcessRequestValidator.validateNoNull(complaintProcessRequest, requiredParam);
			Long complaintId = complaintProcessRequest.getComplaintId();
			Long workerId = complaintProcessRequest.getWorkerId();
			boolean result = complaintService.acceptComplaint(complaintId, workerId, complaintProcessRequest.getComment());
			if(result){
				return ApiResponse.buildSuccessResponse("resolve complaint success", null);
			}else{
				throw new WuyiException("resolve complaint error!");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new WuyiException("resolve complaint error!",e);
		}


	}
	@RequestMapping(value="/api/complaint/{id}/resolve",method=RequestMethod.POST)
	public ApiResponse resolveComplaint(@PathVariable(value="id") Long id,
									@RequestBody ComplaintProcessRequest complaintProcessRequest) throws WuyiException{
		try {
			String[] requiredParam = {"workerId"}; 
			complaintProcessRequestValidator.validateNoNull(complaintProcessRequest, requiredParam);
			Long complaintId = complaintProcessRequest.getComplaintId();
			Long workerId = complaintProcessRequest.getWorkerId();
			boolean result = complaintService.resolveComplaint(id, workerId, complaintProcessRequest.getComment());
			if(result){
				return ApiResponse.buildSuccessResponse("resolve complaint success", null);
			}else{
				throw new WuyiException("resolve complaint error!");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new WuyiException("resolve complaint error!",e);
		}
	}
}
