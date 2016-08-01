package com.med.brenda.service;

import com.med.brenda.model.Hzxx;

/**
 * 患者信息Service接口
 * <p>com.doctor330.medical</p>
 * <p>Title: IHzxxService.java</p>
 * <p>Description:$</p>
 * <p>Package:com.med.brenda.service</p>
 * @author chenhj(brenda)
 * @date 2016年8月1日 下午4:06:36
 * @tag 
 * @encode
 */
public interface IHzxxService {
	
	Hzxx findHzByHzID(Long id); 
}
