package com.med.brenda.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.med.brenda.dao.MzMapper;
import com.med.brenda.dao.YsxxMapper;
import com.med.brenda.model.Mz;
import com.med.brenda.service.IMzService;

@Service
@Repository
public class MzService implements IMzService {

	@Resource
	private MzMapper mzDao;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return mzDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Mz record) {
		return mzDao.insert(record);
	}

	@Override
	public int insertSelective(Mz record) {
		return mzDao.insertSelective(record);
	}

	@Override
	public Mz selectByPrimaryKey(Long id) {
		return mzDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Mz record) {
		return mzDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Mz record) {
		return mzDao.updateByPrimaryKey(record);
	}

	@Override
	public List<Mz> selectByYsid(Long ysid) {
		Map<String,Object> param = new HashMap<>();
		param.put("ysid", ysid);
		return mzDao.selectByYsid(param);
	}

}
