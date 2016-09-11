package com.med.brenda.service.impl;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.med.brenda.controller.common.Query;
import com.med.brenda.dao.TYinshiMapper;
import com.med.brenda.model.TYinshi;
import com.med.brenda.service.ITYinshiService;

@Service
@Repository
public class TYinshiService implements ITYinshiService {
	
	@Resource
	private TYinshiMapper yinshiDao;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return yinshiDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(TYinshi record) {
		return yinshiDao.insert(record);
	}

	@Override
	public int insertSelective(TYinshi record) {
		return yinshiDao.insertSelective(record);
	}

	@Override
	public TYinshi selectByPrimaryKey(Long id) {
		return yinshiDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(TYinshi record) {
		return yinshiDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(TYinshi record) {
		return yinshiDao.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(TYinshi record) {
		return yinshiDao.updateByPrimaryKey(record);
	}

	@Override
	public int findListCount(Query query) {
		Map<String,Object> conditionMap = new HashMap<>();
		conditionMap.put("keyword", query.getKeywords());
		return yinshiDao.findListCount(conditionMap);
	}

	@Override
	public List<TYinshi> findList(Query query) {
		Map<String, Object> param = new HashMap<>();
		param.put("keyworld", query.getKeywords());
		param.put("pageNo", query.getPageIndex());
		param.put("pageSize", query.getPageSize());
		return yinshiDao.findList(param);
	}

}
