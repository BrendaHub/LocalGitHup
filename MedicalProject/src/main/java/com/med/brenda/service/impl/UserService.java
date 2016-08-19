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
	
	@Resource
	private UserMapper userDao;

	public User getUserById(long userId) {
		return userDao.selectByPrimaryKey(userId);
	}

	public User getUserByUserNamePwd(User user) {
		return userDao.getUserByUserNamePwd(user);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return userDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(User record) {
		return userDao.insert(record);
	}

	@Override
	public int insertSelective(User record) {
		return userDao.insertSelective(record);
	}

	@Override
	public User selectByPrimaryKey(Long id) {
		return userDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		return userDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(User record) {
		return userDao.updateByPrimaryKey(record);
	}


}
