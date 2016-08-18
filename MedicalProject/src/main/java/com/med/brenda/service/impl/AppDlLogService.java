package com.med.brenda.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.med.brenda.dao.AppDlLogMapper;
import com.med.brenda.dao.GzysxxMapper;
import com.med.brenda.model.AppDlLog;
import com.med.brenda.service.IAppDlLogService;
@Service
@Repository
public class AppDlLogService implements IAppDlLogService {
	@Resource
	private AppDlLogMapper appDlLogDao;
	@Override
	public int deleteByPrimaryKey(Long id) {
		return appDlLogDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(AppDlLog record) {
		return appDlLogDao.insert(record);
	}

	@Override
	public int insertSelective(AppDlLog record) {
		return appDlLogDao.insertSelective(record);
	}

	@Override
	public AppDlLog selectByPrimaryKey(Long id) {
		return appDlLogDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(AppDlLog record) {
		return appDlLogDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AppDlLog record) {
		return appDlLogDao.updateByPrimaryKey(record);
	}

}
