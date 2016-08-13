package com.med.brenda.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.med.brenda.dao.HzflagMapper;
import com.med.brenda.model.Hzflag;
import com.med.brenda.service.IHzFlagService;
@Service
@Repository
public class HzFlagService implements IHzFlagService {
	Logger logger = Logger.getLogger(HzsfxxService.class);
	
	@Resource
	private HzflagMapper hzflagDao;
	@Override
	public int deleteByPrimaryKey(Long ID) {
		return hzflagDao.deleteByPrimaryKey(ID);
	}

	@Override
	public int insert(Hzflag record) {
		return hzflagDao.insert(record);
	}

	@Override
	public int insertSelective(Hzflag record) {
		return hzflagDao.insertSelective(record);
	}

	@Override
	public Hzflag selectByPrimaryKey(Long ID) {
		return hzflagDao.selectByPrimaryKey(ID);
	}

	@Override
	public int updateByPrimaryKeySelective(Hzflag record) {
		return hzflagDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Hzflag record) {
		return hzflagDao.updateByPrimaryKey(record);
	}

}
