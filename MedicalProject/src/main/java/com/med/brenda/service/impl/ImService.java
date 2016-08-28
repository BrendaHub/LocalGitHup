package com.med.brenda.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.med.brenda.dao.ImMapper;
import com.med.brenda.dao.MzMapper;
import com.med.brenda.model.Im;
import com.med.brenda.service.IIMService;
@Service
@Repository
public class ImService implements IIMService {
	@Resource
	private ImMapper imDao;
	@Override
	public int deleteByPrimaryKey(Long id) {
		return imDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Im record) {
		return imDao.insert(record);
	}

	@Override
	public int insertSelective(Im record) {
		return imDao.insertSelective(record);
	}

	@Override
	public Im selectByPrimaryKey(Long id) {
		return imDao.selectByPrimaryKey(id);
				
	}

	@Override
	public int updateByPrimaryKeySelective(Im record) {
		return imDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Im record) {
		return imDao.updateByPrimaryKey(record);
	}

	@Override
	public List<Im> selectByYsid_Hzid(Long ysid, Long hzid) {
		Map<String,Object> param = new HashMap<>();
		param.put("ysid", ysid);
		param.put("hzid", hzid);
		return imDao.selectByHzid_Ysid(param);
	}

}
