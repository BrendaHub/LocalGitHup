package com.med.brenda.service;

import java.util.List;

import com.med.brenda.controller.common.Query;
import com.med.brenda.model.Hzxx;

/**
 * 患者信息Service接口
 * <p>
 * com.doctor330.medical
 * </p>
 * <p>
 * Title: IHzxxService.java
 * </p>
 * <p>
 * Description:$
 * </p>
 * <p>
 * Package:com.med.brenda.service
 * </p>
 * 
 * @author chenhj(brenda)
 * @date 2016年8月1日 下午4:06:36
 * @tag
 * @encode
 */
public interface IHzxxService {

	int deleteByPrimaryKey(Long ID);

	int insert(Hzxx record);

	int insertSelective(Hzxx record);

	Hzxx selectByPrimaryKey(Long ID);

	int updateByPrimaryKeySelective(Hzxx record);

	int updateByPrimaryKey(Hzxx record);

	Hzxx findHzByHzID(Long id);

	Hzxx hzLogon(String sfzh, String password);

	long addHz(Hzxx hz);
	
	int findListCount(Query query);
	
	List<Hzxx> findList(Query query);
	//根据temp7字段的值查询出参加第二次科研的用户信息, temp7为1时表示参于，否则不参与；
	int findHzxxByTemp7Count(String temp7, Query query);
	List<Hzxx> findHzxxByTemp7(String temp7, Query query);
}
