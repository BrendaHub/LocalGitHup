package com.med.brenda.service.impl;

import java.util.HashMap;
import java.util.Map;

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
	public Hzxx hzLogon(String sfzh, String password) {
		Map<String,String> param = new HashMap<String,String>();
		param.put("sfzh", sfzh);
		param.put("password", password);
		return hzxxDao.hzLogon(param);
	}

}
