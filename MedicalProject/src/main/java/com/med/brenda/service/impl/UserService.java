package com.med.brenda.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.med.brenda.dao.UserMapper;
import com.med.brenda.model.User;
import com.med.brenda.service.IUserService;

@Service
@Repository
public class UserService implements IUserService {
	
	@Autowired
	private UserMapper userDao;

	public User getUserById(long userId) {
		return userDao.selectByPrimaryKey(userId);
	}

}
