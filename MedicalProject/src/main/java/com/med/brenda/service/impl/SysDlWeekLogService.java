package com.med.brenda.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.med.brenda.dao.SysDlWeekLogMapper;
import com.med.brenda.model.SysDlWeekLog;
import com.med.brenda.service.ISysDlWeekLogService;

@Service
@Repository
public class SysDlWeekLogService implements ISysDlWeekLogService {

	Logger logger = Logger.getLogger(SysDlWeekLogService.class);

	@Resource
	private SysDlWeekLogMapper weeklogDao;
	@Override
	public int deleteByPrimaryKey(Long id) {
		return weeklogDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SysDlWeekLog record) {
		return weeklogDao.insert(record);
	}

	@Override
	public int insertSelective(SysDlWeekLog record) {
		return weeklogDao.insertSelective(record);
	}

	@Override
	public SysDlWeekLog selectByPrimaryKey(Long id) {
		return weeklogDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SysDlWeekLog record) {
		return weeklogDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SysDlWeekLog record) {
		return weeklogDao.updateByPrimaryKey(record);
	}

}
