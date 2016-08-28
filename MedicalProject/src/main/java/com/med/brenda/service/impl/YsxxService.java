package com.med.brenda.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.med.brenda.dao.YsxxMapper;
import com.med.brenda.model.Ysxx;
import com.med.brenda.service.IYsxxService;

@Service
@Repository
public class YsxxService implements IYsxxService {


	@Resource
	private YsxxMapper ysxxDao;
	
	@Override
	public Ysxx ysLogon(String ysdlh, String yspwd) {
		Map<String,String> param = new HashMap<String,String>();
		param.put("dlh", ysdlh);
		param.put("password", yspwd);
		return ysxxDao.ysLogon(param);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return ysxxDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Ysxx record) {
		return ysxxDao.insert(record);
	}

	@Override
	public int insertSelective(Ysxx record) {
		return ysxxDao.insertSelective(record);
	}

	@Override
	public Ysxx selectByPrimaryKey(Long id) {
		return ysxxDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Ysxx record) {
		return ysxxDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Ysxx record) {
		return ysxxDao.updateByPrimaryKey(record);
	}

	@Override
	public Ysxx ysLogon(Map<String, String> map) {
		return ysxxDao.ysLogon(map);
	}

}
