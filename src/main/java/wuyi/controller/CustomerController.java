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

import wuyi.exception.DataException;
import wuyi.exception.WuyiException;
import wuyi.model.DTO.CustomerDTO;
import wuyi.model.DTO.CustomerLevelDTO;
import wuyi.model.po.Customer;
import wuyi.model.po.CustomerLevel;
import wuyi.model.request.CustomerRequest;
import wuyi.model.response.ApiResponse;
import wuyi.model.response.PagedData;
import wuyi.service.CommentService;
import wuyi.service.ComplaintService;
import wuyi.service.CustomerService;
import wuyi.service.FeedbackService;
import wuyi.util.BaseModelState;
import wuyi.util.validator.WuyiValidator;

@RestController
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	@Autowired
	CommentService commentService;
	@Autowired
	FeedbackService feedbackService;
	@Autowired
	ComplaintService complaintService;
	
	WuyiValidator<Customer> validator = new WuyiValidator<Customer>(Customer.class);
	
	@RequestMapping(value="/api/customers/search",method=RequestMethod.GET)
	public ApiResponse searchCustomer(@RequestParam(value = "name",required=false) String name,
			@RequestParam(value = "nickname",required=false) String nickname,
			@RequestParam(value = "phone",required=false) String phone,
			@RequestParam(value = "customerState",required=false) String customerState,
			@RequestParam(value = "beforeDate",required=false) Date beforeDate,
			@RequestParam(value = "afterDate",required=false) Date afterDate,
			@RequestParam(value = "cityId",required=false) Long cityId,
			@RequestParam(value = "livingAreaId",required=false) Long livingAreaId,
			@RequestParam(value = "orderField",required=false,defaultValue="id") String orderField,
			@RequestParam(value="page",defaultValue="1") int page,
			@RequestParam(value="pagesize",defaultValue="5") int pagesize){
		
		PagedData searchResult = customerService.searchCustomers(name, nickname, phone, beforeDate , afterDate, cityId, livingAreaId,customerState, page, pagesize);
		int total = searchResult.getTotal();
		
		List<Customer> customers = searchResult.getDatas();
		List<CustomerDTO> customerDTOs = new ArrayList<CustomerDTO>();
		for(Customer c:customers){
			CustomerDTO cdto = new CustomerDTO();
			cdto.createDTOfromPo(c);
			customerDTOs.add(cdto);
		}
		Map result = new HashMap();
		result.put("data", customerDTOs);
		result.put("total", total);	
		return ApiResponse.buildSuccessResponse("search customer result.", result);
		
	}
	
	@RequestMapping(value="/api/customerslist/search",method=RequestMethod.GET)
	public ApiResponse searchCustomerList(@RequestParam(value = "name",required=false) String name,
			@RequestParam(value = "nickname",required=false) String nickname,
			@RequestParam(value = "phone",required=false) String phone,
			@RequestParam(value = "customerState",required=false) String customerState,
			@RequestParam(value = "beforeDate",required=false) Date beforeDate,
			@RequestParam(value = "afterDate",required=false) Date afterDate,
			@RequestParam(value = "cityId",required=false) Long cityId,
			@RequestParam(value = "livingAreaId",required=false) Long livingAreaId,
			@RequestParam(value = "orderField",required=false,defaultValue="id") String orderField,
			@RequestParam(value="page",defaultValue="1") int page,
			@RequestParam(value="pagesize",defaultValue="5") int pagesize){
		
		PagedData searchResult = customerService.searchCustomersVO(name, nickname, phone, beforeDate , afterDate, cityId, livingAreaId,customerState, page, pagesize);
		int total = searchResult.getTotal();
		
		Map result = new HashMap();
		result.put("data", searchResult.getDatas());
		result.put("total", total);	
		return ApiResponse.buildSuccessResponse("search customer result.", result);
		
	}
	
	@RequestMapping(value="/api/customer/create",method=RequestMethod.POST)
	public ApiResponse createCustomer(@RequestBody CustomerRequest param) throws WuyiException{
		Customer customer = param.getCustomer();
		int level = param.getCustomerLevel();
		try {
			Customer resturnCustomer = customerService.createCustomer(customer,level);
			CustomerDTO cdto = new CustomerDTO();
			cdto.createDTOfromPo(resturnCustomer);
			Map result = new HashMap();
			result.put("customer", cdto);
			return ApiResponse.buildSuccessResponse("Create customer success", result);
		} catch (Exception e) {
			// TODO: handle exception
			throw new WuyiException("Get customer error!",e);
		}
			
	}
	
	@RequestMapping(value="/api/customer/{id}",method=RequestMethod.GET)
	public ApiResponse getCustomerById(@PathVariable(value="id") Long id) throws DataException, WuyiException{
		try {
			Customer customer = customerService.getCustomer(id);
			CustomerDTO cdto = new CustomerDTO();
			cdto.createDTOfromPo(customer);
			Map result = new HashMap();
			result.put("customer", cdto);
			
			return ApiResponse.buildSuccessResponse("Get customer result.", result);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WuyiException("Get customer error!",e);
		}
		
	}
	
	@RequestMapping(value="/api/customer/get",method=RequestMethod.GET)
	public ApiResponse getCustomerByPhone(@RequestParam(value="phone",required=false) String phone,
											@RequestParam(value="uid",required=false) String uid) throws Exception{
		
		if(phone==null&&uid==null){
			throw new DataException("Need at least one parameter!");
		}
		if(phone!=null&&uid!=null){
			throw new DataException("Accept one parameter at once!");
		}
		try {
			Customer customer = new Customer();
			Map result = new HashMap();
			if(phone!=null){
				customer = customerService.getCustomerByPhone(phone);
			}
			if(uid!=null){
				customer = customerService.getCustomerByUid(uid);
			}
			CustomerDTO cdto = new CustomerDTO();
			cdto.createDTOfromPo(customer);
			result.put("customer", cdto);
			return ApiResponse.buildSuccessResponse("Get customer result.", result);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WuyiException("Get customer fail",e);
		}
		
	}
	
	
	@RequestMapping(value="/api/customer/{id}/update",method=RequestMethod.POST)
	public ApiResponse updateCustomer(@PathVariable(value="id") Long id,@RequestBody CustomerRequest param) throws Exception{
		Customer customer = param.getCustomer();
		
		String[] requireField = {};
		validator.validateNoNull(customer, requireField);
			
		
		ApiResponse result = new ApiResponse();
		try {
			if(customerService.updateCustomer(customer)){
				return ApiResponse.buildSuccessResponse("search customer result.", null);
			}else{
				throw new WuyiException("Update customer fail");
			}
		} catch (RuntimeException e) {
			throw new WuyiException("Update customer fail",e);
		}
		
		
	}
	@RequestMapping(value="/api/customer/{id}/delete",method=RequestMethod.POST)
	public ApiResponse deleteCustomer(@PathVariable(value="id") Long id) throws Exception{
		Customer customer = customerService.getCustomer(id);
		
		try {
			if(customer!=null){
				customer.setState(BaseModelState.DELETE);
				if(customerService.DeleteCustomer(customer.getId())){
					
					return ApiResponse.buildSuccessResponse("delete user success!", null);
				}else{
					throw new WuyiException("Detele customer fail");
				}
			}else{
				throw new DataException("No customer with this id!");
			}
			
			
		} catch (RuntimeException e) {
			throw new WuyiException("Detele customer fail",e);
		}
		
		
	}
	
	@RequestMapping(value="/api/customer/{id}/upgrade",method=RequestMethod.POST)
	public ApiResponse upgradeCustomer(@PathVariable(value = "id") Long id) throws Exception{
		Customer customer = customerService.upgradeCustomer(id);
		
		CustomerDTO cdto = new CustomerDTO();
		cdto.createDTOfromPo(customer);
		CustomerLevelDTO cldto = new CustomerLevelDTO();
		cldto.createDTOfromPo(customer.getCustomerLevel());
		Map data = new HashMap();
		data.put("customer", cdto);
		data.put("customerLevel", cldto);
		return ApiResponse.buildSuccessResponse("Upgrade customer detail success!", data);
	}
	
	@RequestMapping(value="/api/customer/{id}/degrade",method=RequestMethod.POST)
	public ApiResponse degradeCustomer(@PathVariable(value = "id") Long id) throws Exception{
		Customer customer = customerService.degradeCustomer(id);
		CustomerDTO cdto = new CustomerDTO();
		cdto.createDTOfromPo(customer);
		CustomerLevelDTO cldto = new CustomerLevelDTO();
		cldto.createDTOfromPo(customer.getCustomerLevel());
		Map data = new HashMap();
		data.put("customer", cdto);
		data.put("customerLevel", cldto);
		return ApiResponse.buildSuccessResponse("Upgrade customer detail success!", data);
		
	}
	
	@RequestMapping(value="/api/customer/{id}/level",method=RequestMethod.GET)
	public ApiResponse getCustomerLevel(@PathVariable	(value = "id") Long id){
		CustomerLevel customerLevel = customerService.getCustomerLevel(id);
		CustomerLevelDTO cldto = new CustomerLevelDTO();
		cldto.createDTOfromPo(customerLevel);
		Map data = new HashMap();
		data.put("customerLevel", cldto);
		return ApiResponse.buildSuccessResponse("Get customer level success!", data);
	}
	
}
