package com.med.brenda.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.med.brenda.dao.SysDlDayLogMapper;
import com.med.brenda.model.SysDlDayLog;
import com.med.brenda.service.ISysDlDayLogService;

@Service
@Repository
public class SysDlDayLogService implements ISysDlDayLogService {
	
	Logger logger = Logger.getLogger(SysDlDayLogService.class);

	@Resource
	private SysDlDayLogMapper daylogDao;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return daylogDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SysDlDayLog record) {
		return daylogDao.insert(record);
	}

	@Override
	public int insertSelective(SysDlDayLog record) {
		return daylogDao.insertSelective(record);
	}

	@Override
	public SysDlDayLog selectByPrimaryKey(Long id) {
		return daylogDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SysDlDayLog record) {
		return daylogDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SysDlDayLog record) {
		return daylogDao.updateByPrimaryKey(record);
	}

	@Override
	public List<SysDlDayLog> selectList(int pageIndex, int pageSize) {
		Map<String,Object> p = new HashMap<>();
		p.put("pageNo", pageIndex);
		p.put("pageSize", pageSize);
		List<SysDlDayLog> list = daylogDao.selectList(p);
		return list;
	}

}
