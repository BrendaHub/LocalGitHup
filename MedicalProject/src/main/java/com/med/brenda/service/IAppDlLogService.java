package com.med.brenda.service;

import com.med.brenda.model.AppDlLog;

public interface IAppDlLogService {
	int deleteByPrimaryKey(Long id);

	int insert(AppDlLog record);

	int insertSelective(AppDlLog record);

	AppDlLog selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(AppDlLog record);

	int updateByPrimaryKey(AppDlLog record);
}
