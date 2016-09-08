package com.med.brenda.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.med.brenda.dao.TActivityMapper;
import com.med.brenda.model.TActivity;
import com.med.brenda.service.IActivityService;
@Service
@Repository
public class ActivityService implements IActivityService {
	@Resource
	private TActivityMapper activityDao;
	@Override
	public int deleteByPrimaryKey(Long id) {
		return activityDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(TActivity record) {
		return activityDao.insert(record);
	}

	@Override
	public int insertSelective(TActivity record) {
		return activityDao.insertSelective(record);
	}

	@Override
	public TActivity selectByPrimaryKey(Long id) {
		return activityDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(TActivity record) {
		return activityDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TActivity record) {
		return activityDao.updateByPrimaryKey(record);
	}

}
