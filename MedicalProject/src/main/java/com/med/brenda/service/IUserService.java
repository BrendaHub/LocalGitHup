package com.med.brenda.service;

import com.med.brenda.model.User;

/**
 * 用户Service接口
 * <p>com.doctor330.medical</p>
 * <p>Title: IUserService.java</p>
 * <p>Description:$</p>
 * <p>Package:com.med.brenda.service</p>
 * @author chenhj(brenda)
 * @date 2016年8月1日 下午4:06:07
 * @tag 
 * @encode
 */ 
public interface IUserService {
	public User getUserById(long userId);
	public User getUserByUserNamePwd(User user);
}
