package com.med.brenda.service;

import java.util.List;

import com.med.brenda.model.User;

/**
 * 用户Service接口
 * <p>
 * com.doctor330.medical
 * </p>
 * <p>
 * Title: IUserService.java
 * </p>
 * <p>
 * Description:$
 * </p>
 * <p>
 * Package:com.med.brenda.service
 * </p>
 * 
 * @author chenhj(brenda)
 * @date 2016年8月1日 下午4:06:07
 * @tag
 * @encode
 */
public interface IUserService {
	int deleteByPrimaryKey(Long id);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	User getUserByUserNamePwd(String userName, String passwd);

	User getUserById(long userId);
}
