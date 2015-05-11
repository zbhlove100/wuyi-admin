//package com.wuyi.service.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//
//import com.wuyi.dao.UserDao;
//import com.wuyi.dao.UserDaoHibernate;
//import com.wuyi.model.User;
//
//@Service
//public class MyUserDetailsServiceImpl extends GenericManagerImpl<User, Long> implements UserService{
//	
//	@Autowired
//	@Qualifier("userDao")
//	private UserDao userDao;
//
//
//
//	public UserDao getUserDao() {
//		return userDao;
//	}
//
//
//
//	public void setUserDao(UserDao userDao) {
//		this.userDao = userDao;
//	}
//
//}
