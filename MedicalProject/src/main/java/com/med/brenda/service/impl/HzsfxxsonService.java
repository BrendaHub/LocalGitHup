package com.med.brenda.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.med.brenda.dao.HzsfxxsonMapper;
import com.med.brenda.model.Hzsfxxson;
import com.med.brenda.service.IHzsfxxsonService;

@Service
@Repository
public class HzsfxxsonService implements IHzsfxxsonService {
	Logger logger = Logger.getLogger(HzsfxxsonService.class);

	@Resource
	private HzsfxxsonMapper HzsfxxsonDao;

	@Override
	public int deleteByPrimaryKey(Long ID) {
		return HzsfxxsonDao.deleteByPrimaryKey(ID);
	}

	@Override
	public int insert(Hzsfxxson record) {
		return HzsfxxsonDao.insert(record);
	}

	@Override
	public int insertSelective(Hzsfxxson record) {
		return HzsfxxsonDao.insertSelective(record);
	}

	@Override
	public Hzsfxxson selectByPrimaryKey(Long ID) {
		return HzsfxxsonDao.selectByPrimaryKey(ID);
	}

	@Override
	public int updateByPrimaryKeySelective(Hzsfxxson record) {
		return HzsfxxsonDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Hzsfxxson record) {
		return HzsfxxsonDao.updateByPrimaryKey(record);
	}

	@Override
	public List<Hzsfxxson> findListByFatherId(Long fatherid) {
		return HzsfxxsonDao.findListByFatherId(fatherid);
	}

}
