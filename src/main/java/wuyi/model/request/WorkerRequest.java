package wuyi.model.request;

import wuyi.model.po.Worker;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkerRequest extends BaseRequest{
	private Worker worker;

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}
	
	
}
