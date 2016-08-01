package com.med.brenda.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.med.brenda.dao.HzxxMapper;
import com.med.brenda.model.Hzxx;
import com.med.brenda.service.IHzxxService;
@Service
@Repository
public class HzxxService implements IHzxxService {
	@Resource
	private HzxxMapper hzxxDao;
	public Hzxx findHzByHzID(Long id) {
		return hzxxDao.selectByPrimaryKey(id);
	}

}
