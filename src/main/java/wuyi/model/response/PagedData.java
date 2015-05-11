package wuyi.model.response;

import java.util.List;

public class PagedData {
	private int total;
	private int page;
	private int pagesize;
	private List datas;
	
	public PagedData(int total, int page, int pagesize, List datas) {
		super();
		this.total = total;
		this.page = page;
		this.pagesize = pagesize;
		this.datas = datas;
	}
	public PagedData() {
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public List getDatas() {
		return datas;
	}
	public void setDatas(List datas) {
		this.datas = datas;
	}
}
