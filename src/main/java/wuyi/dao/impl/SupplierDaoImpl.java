package wuyi.dao.impl;

import wuyi.dao.SupplierDao;
import wuyi.model.po.Supplier;

public class SupplierDaoImpl extends MyGenericDaoHibernate<Supplier, Long> implements SupplierDao{

	public SupplierDaoImpl() {
		super(Supplier.class);
		// TODO Auto-generated constructor stub
	}

}
