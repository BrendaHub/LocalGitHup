package com.med.brenda.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.med.brenda.dao.TnbYinshiMapper;
import com.med.brenda.model.TnbYinshi;
import com.med.brenda.service.IYinshiService;

@Service
@Repository
public class YinshiService implements IYinshiService {

	@Resource
	private TnbYinshiMapper yinshiDao;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return yinshiDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(TnbYinshi record) {
		return yinshiDao.insert(record);
	}

	@Override
	public int insertSelective(TnbYinshi record) {
		return yinshiDao.insertSelective(record);
	}

	@Override
	public TnbYinshi selectByPrimaryKey(Long id) {
		return yinshiDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(TnbYinshi record) {
		return yinshiDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TnbYinshi record) {
		return yinshiDao.updateByPrimaryKey(record);
	}

	@Override
	public List<TnbYinshi> getYinshiListByHzid_date(Long hzid, String itemcodeprefix, String date) {
		Map<String, Object> param = new HashMap<>();
		param.put("hzid", hzid);
		param.put("temp5", date);
		param.put("itemcode", itemcodeprefix);
		return yinshiDao.getYinshiListByHzid_date(param);
	}

}
