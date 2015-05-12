package wuyi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import wuyi.model.po.CustomerLevel;
import wuyi.model.request.CustomerLevelRequest;
import wuyi.model.response.ApiResponse;
import wuyi.service.CustomerLevelService;

@RestController
public class CustomerLevelController {
	
	@Autowired
	CustomerLevelService customerLevelService;
	
	@RequestMapping(value="/api/customerlevel/create",method=RequestMethod.POST)
	public ApiResponse createCustomerLevel(@RequestBody CustomerLevelRequest customerLevelRequest){
		CustomerLevel customerLevel = customerLevelRequest.getCustomerLevel();
		try {
			customerLevelService.createCustomerLevel(customerLevel);
			Map data = new HashMap();
			data.put("CustomerLevel", customerLevel);
			return ApiResponse.buildSuccessResponse("Create customer level success!", data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ApiResponse.buildErrorResponse("Create customer level success!", null);
		}
		
	}
	
	@RequestMapping(value="/api/customerlevel/delete",method=RequestMethod.DELETE)
	public ApiResponse deleteCustomerLevel(@RequestParam(value="id") Long id){
		
		try {
			if(customerLevelService.deleteCustomerLevel(id)){
				return ApiResponse.buildSuccessResponse("Delete customer level success!", null);
			}else{
				return ApiResponse.buildErrorResponse("Delete customer level success!", null);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ApiResponse.buildErrorResponse("Delete customer level success!", null);
		}
		
	}
	
	@RequestMapping(value="/api/customerlevel/update",method=RequestMethod.POST)
	public ApiResponse updateCustomerLevel(@RequestBody CustomerLevelRequest customerLevelRequest){
		CustomerLevel customerLevel = customerLevelRequest.getCustomerLevel();
		try {
			customerLevelService.updateCustomerLevel(customerLevel);
			
			return ApiResponse.buildSuccessResponse("Update customer level success!", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ApiResponse.buildErrorResponse("Update customer level success!", null);
		}
		
	}
	
	@RequestMapping(value="/api/customerlevel/search",method=RequestMethod.POST)
	public ApiResponse searchCustomerLevel(@RequestBody CustomerLevelRequest customerLevelRequest){
		CustomerLevel customerLevel = customerLevelRequest.getCustomerLevel();
		try {
			//customerLevelService.updateCustomerLevel(customerLevel);
			Map data = new HashMap();
			data.put("CustomerLevel", customerLevel);
			return ApiResponse.buildSuccessResponse("Update customer level success!", data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ApiResponse.buildErrorResponse("Update customer level success!", null);
		}
		
	}
	
	@RequestMapping(value="/api/customerlevel/getall",method=RequestMethod.GET)
	public ApiResponse getCustomerLevel(){
		try {
			List<CustomerLevel> customerLevels = customerLevelService.getAllCustomerLevel();
			Map data = new HashMap();
			data.put("CustomerLevels", customerLevels);
			return ApiResponse.buildSuccessResponse("Get all customer level success!", data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ApiResponse.buildErrorResponse("Get all customer level success!", null);
		}
		
	}
}
