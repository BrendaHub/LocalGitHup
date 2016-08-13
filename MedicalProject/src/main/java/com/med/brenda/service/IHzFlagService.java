package com.med.brenda.service;

import com.med.brenda.model.Hzflag;

public interface IHzFlagService {
	int deleteByPrimaryKey(Long ID);

	int insert(Hzflag record);

	int insertSelective(Hzflag record);

	Hzflag selectByPrimaryKey(Long ID);

	int updateByPrimaryKeySelective(Hzflag record);

	int updateByPrimaryKey(Hzflag record);
}
