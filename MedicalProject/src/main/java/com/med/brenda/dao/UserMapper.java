package com.med.brenda.dao;

import java.util.List;
import java.util.Map;

import com.med.brenda.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> getUserByUserNamePwd(Map<String, Object> param);
}