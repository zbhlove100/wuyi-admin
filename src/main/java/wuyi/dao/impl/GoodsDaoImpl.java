package wuyi.dao.impl;

import wuyi.dao.GoodsDao;
import wuyi.model.po.Goods;

public class GoodsDaoImpl extends MyGenericDaoHibernate<Goods, Long> implements GoodsDao{

	public GoodsDaoImpl() {
		super(Goods.class);
		// TODO Auto-generated constructor stub
	}

}
