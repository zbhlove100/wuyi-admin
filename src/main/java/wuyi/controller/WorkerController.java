package wuyi.controller;

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
import wuyi.model.po.Worker;
import wuyi.model.request.WorkerRequest;
import wuyi.model.response.ApiResponse;
import wuyi.model.response.PagedData;
import wuyi.service.WorkerService;
import wuyi.util.BaseModelState;

@RestController
public class WorkerController {
	
	@Autowired
	WorkerService workerService;
	
	@RequestMapping(value="/api/worker/create",method=RequestMethod.POST)
	public ApiResponse createWorker(@RequestBody WorkerRequest param){
		Worker worker = param.getWorker();
		try {
			workerService.createWorker(worker);
			return ApiResponse.buildSuccessResponse("Create worker success!", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ApiResponse.buildSuccessResponse(e.getMessage(), null);
		}
	}
	@RequestMapping(value="/api/worker/delete",method=RequestMethod.POST)
	public ApiResponse deleteWorker(@RequestBody WorkerRequest param){
		Worker worker = param.getWorker();
		worker.setState(BaseModelState.DELETE);
		workerService.updateWorker(worker);
		
		return ApiResponse.buildSuccessResponse("Delete worker success!", null);
	}
	@RequestMapping(value="/api/worker/update",method=RequestMethod.POST)
	public ApiResponse updateWorker(@RequestBody WorkerRequest param){
		Worker worker = param.getWorker();
		try {
			workerService.updateWorker(worker);
			return ApiResponse.buildSuccessResponse("Update worker success", null);
		} catch (Exception e) {
			// TODO: handle exception
			return ApiResponse.buildErrorResponse("Update worker error!", null);
		}
		
	}
	
	@RequestMapping(value="/api/worker/{id}",method=RequestMethod.GET)
	public ApiResponse updateWorker(@PathVariable(value="id") Long id) throws DataException{
		try {
			Worker worker = workerService.getWorker(id);
			Map result = new HashMap();
			result.put("data", worker);
			result.put("total", 1);
			return ApiResponse.buildSuccessResponse("Update worker success", result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DataException("worker error");
		}
		
	}
	
	@RequestMapping(value="/api/worker/search",method=RequestMethod.POST)
	public ApiResponse getWorkerByPage(@RequestParam(value = "livingAreaId",required=false) Long livingAreaId,
				@RequestParam(value = "phone",required=false) String phone,
				@RequestParam(value="page",defaultValue="1") int page,
				@RequestParam(value="pagesize",defaultValue="5") int pagesize) throws DataException{
		try {
			PagedData workerData = workerService.searchWorker(page, pagesize,livingAreaId,phone);
			int total = workerData.getTotal();
			List<Worker> workers = workerData.getDatas();		
			if(workers == null || workers.size() == 0){
				return ApiResponse.buildErrorResponse("Get workers error!", null);
			}else{
				Map result = new HashMap();
				result.put("data", workers);
				result.put("total", total);
				return ApiResponse.buildSuccessResponse("Get workers success", result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataException("worker error");
		}
		
		
	}
}
