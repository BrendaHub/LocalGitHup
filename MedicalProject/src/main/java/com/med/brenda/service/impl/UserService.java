package com.med.brenda.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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

	public User getUserByUserNamePwd(String username, String passwd) {
		System.out.println(username +"   "+ passwd);
		Map<String, Object> param = new HashMap<>();
		param.put("username", username);
		param.put("passwd", passwd);
		List<User> list = userDao.getUserByUserNamePwd(param);
		if(list != null && list.size() > 0){
			User user = list.get(0);
			return user;
		}else{
			return null;
		}
		
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
