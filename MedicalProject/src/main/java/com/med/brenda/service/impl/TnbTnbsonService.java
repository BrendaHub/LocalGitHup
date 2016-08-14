package com.med.brenda.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.med.brenda.dao.TnbTnbsonMapper;
import com.med.brenda.model.TnbTnbson;
import com.med.brenda.service.ITnbTnbsonService;


@Service
@Repository
public class TnbTnbsonService implements ITnbTnbsonService {

	@Resource
	private TnbTnbsonMapper tnbsonDao;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return tnbsonDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(TnbTnbson record) {
		return tnbsonDao.insert(record);
	}

	@Override
	public int insertSelective(TnbTnbson record) {
		return tnbsonDao.insertSelective(record);
	}

	@Override
	public TnbTnbson selectByPrimaryKey(Long id) {
		return tnbsonDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(TnbTnbson record) {
		return tnbsonDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TnbTnbson record) {
		return tnbsonDao.updateByPrimaryKey(record);
	}

	@Override
	public List<TnbTnbson> findFeedList(Long hzid, String itemcode, String date) {
		Map<String,Object> param = new HashMap<>();
		param.put("hzid", hzid);
		param.put("date", date);
		param.put("itemcode", itemcode);
		return tnbsonDao.findFeedListByHzid_Date(param);
	}

}
