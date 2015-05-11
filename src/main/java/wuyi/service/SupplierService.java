package wuyi.service;

import java.util.Date;

import wuyi.exception.DataException;
import wuyi.model.po.Customer;
import wuyi.model.po.Supplier;
import wuyi.model.response.PagedData;

public interface SupplierService{

	public PagedData searchCustomers(String name,String nickName,String phone,
			Date beforeDate,Date afterDate,Long cityId,Long livingAreaId,
			String supplierState,int page,int pagesize);

public PagedData searchSupplier(String name,String nickName,String phone,
Date beforeDate,Date afterDate,Long cityId,Long livingAreaId,
String supplierState,int page,int pagesize);


public Customer createSupplier(Supplier supplier) throws Exception;

public boolean updateSupplier(Supplier supplier) throws DataException, Exception;


public boolean DeleteSupplier(Long supplierId);

}
