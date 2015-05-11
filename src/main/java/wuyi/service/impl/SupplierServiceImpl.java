package wuyi.service.impl;

import java.util.Date;

import wuyi.exception.DataException;
import wuyi.model.po.Customer;
import wuyi.model.po.Supplier;
import wuyi.model.response.PagedData;
import wuyi.service.SupplierService;

public class SupplierServiceImpl extends GenericManagerImpl implements SupplierService{

	@Override
	public PagedData searchCustomers(String name, String nickName,
			String phone, Date beforeDate, Date afterDate, Long cityId,
			Long livingAreaId, String supplierState, int page, int pagesize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagedData searchSupplier(String name, String nickName, String phone,
			Date beforeDate, Date afterDate, Long cityId, Long livingAreaId,
			String supplierState, int page, int pagesize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer createSupplier(Supplier supplier) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateSupplier(Supplier supplier) throws DataException,
			Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean DeleteSupplier(Long supplierId) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
