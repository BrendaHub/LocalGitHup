package com.med.brenda.service;

import com.med.brenda.model.SysDlWeekLog;

public interface ISysDlWeekLogService {
	int deleteByPrimaryKey(Long id);

	int insert(SysDlWeekLog record);

	int insertSelective(SysDlWeekLog record);

	SysDlWeekLog selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(SysDlWeekLog record);

	int updateByPrimaryKey(SysDlWeekLog record);
}
