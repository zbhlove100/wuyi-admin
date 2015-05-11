package wuyi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wuyi.dao.CustomerLevelDao;
import wuyi.exception.DataException;
import wuyi.exception.WuyiException;
import wuyi.model.po.CustomerLevel;
import wuyi.service.CustomerLevelService;
import wuyi.util.BaseModelState;
import wuyi.util.WuyiBeanUtil;
@Service
public class CustomerLevelServiceImpl extends GenericManagerImpl
												implements CustomerLevelService{

	@Autowired
	CustomerLevelDao customerLevelDao;
	
	@Override
	public List<CustomerLevel> getAllCustomerLevel() {
		return customerLevelDao.getAllCustomerLevels();
	}

	@Override
	public CustomerLevel createCustomerLevel(CustomerLevel customerLevel)
			throws Exception {
		int level = customerLevel.getLevel();
		if(existLevel(level)){
			throw new DataException("Level already exist!");
		}else{
			if(customerLevelDao.save(customerLevel)){
				return customerLevel;
			}else{
				throw new WuyiException("Create customerLevel fail!");
			}
		}
		
	}

	@Override
	public boolean deleteCustomerLevel(Long id) throws Exception {
		// TODO Auto-generated method stub
		CustomerLevel customerLevel = customerLevelDao.find(id);
		customerLevel.setState(BaseModelState.DELETE);
		customerLevelDao.flush();
		return true;
	}

	@Override
	@Transactional
	public CustomerLevel updateCustomerLevel(CustomerLevel customerLevel)
			throws Exception {
		if(customerLevel.getId()==null){
			throw new DataException("No customer level to update!");
		}
		if(customerLevel.getLevel()==null){
			throw new DataException("Can't update customer level number!");
		}
		CustomerLevel persistCustomerLevel = customerLevelDao.find(customerLevel.getId());
		WuyiBeanUtil.CopyPropertiesNotNull(customerLevel, persistCustomerLevel);
		customerLevelDao.update(persistCustomerLevel);
		return customerLevel;
	}

	@Override
	public CustomerLevel getNextLevel(CustomerLevel customerLevel) throws DataException {
		// TODO Auto-generated method stub
		int level = customerLevel.getLevel();
		level = level + 1;
		try {
			CustomerLevel nextCustomerLevel = customerLevelDao.getCustomerLevel(level);
			return nextCustomerLevel;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DataException("No higher customer level!");
		}
	}

	@Override
	public CustomerLevel getPreLevel(CustomerLevel customerLevel) throws DataException {
		int level = customerLevel.getLevel();
		level = level -1;
		if(level<0)
			return null;
		try {
			CustomerLevel preCustomerLevel = customerLevelDao.getCustomerLevel(level);
			return preCustomerLevel;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DataException("No higher customer level!");
		}
	}
	
	private boolean existLevel(int level){
		boolean result = false;
		try {
			customerLevelDao.getCustomerLevel(level);
			result = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
