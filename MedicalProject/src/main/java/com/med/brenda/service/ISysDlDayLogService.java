package com.med.brenda.service;

import java.util.List;

import com.med.brenda.model.SysDlDayLog;

public interface ISysDlDayLogService {
	int deleteByPrimaryKey(Long id);

	int insert(SysDlDayLog record);

	int insertSelective(SysDlDayLog record);

	SysDlDayLog selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(SysDlDayLog record);

	int updateByPrimaryKey(SysDlDayLog record);
	
	List<SysDlDayLog> selectList(int pageIndex, int pageSize);
}
