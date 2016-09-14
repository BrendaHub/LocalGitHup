package com.med.brenda.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.med.brenda.controller.common.Query;
import com.med.brenda.dao.TYinshiCatecoryMapper;
import com.med.brenda.model.TFood;
import com.med.brenda.model.TYinshiCatecory;
import com.med.brenda.service.ITYinshiCatecoryService;

@Service
@Repository
public class TYinshiCatecoryService implements ITYinshiCatecoryService {
	@Resource
	private TYinshiCatecoryMapper yinshiCatecoryDao;
	@Override
	public int deleteByPrimaryKey(Long id) {
		return yinshiCatecoryDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(TYinshiCatecory record) {
		return yinshiCatecoryDao.insert(record);
	}

	@Override
	public int insertSelective(TYinshiCatecory record) {
		return yinshiCatecoryDao.insertSelective(record);
	}

	@Override
	public TYinshiCatecory selectByPrimaryKey(Long id) {
		return yinshiCatecoryDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(TYinshiCatecory record) {
		return yinshiCatecoryDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TYinshiCatecory record) {
		return yinshiCatecoryDao.updateByPrimaryKey(record);
	}
	
	@Override
	public int findListCount(Query query) {
		Map<String, Object> param = new HashMap<>();
		param.put("keyworld", query.getKeywords());
		return yinshiCatecoryDao.findListCount(param);
	}

	@Override
	public List<TYinshiCatecory> findList(Query query) {
		Map<String, Object> param = new HashMap<>();
		param.put("keyworld", query.getKeywords());
		param.put("pageNo", query.getPageIndex());
		param.put("pageSize", query.getPageSize());
		return yinshiCatecoryDao.findList(param);
	}

}
