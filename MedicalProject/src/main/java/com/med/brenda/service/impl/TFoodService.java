package com.med.brenda.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.med.brenda.controller.common.Query;
import com.med.brenda.dao.TFoodMapper;
import com.med.brenda.model.Hzxx;
import com.med.brenda.model.TFood;
import com.med.brenda.service.ITFoodService;

@Service
@Repository
public class TFoodService implements ITFoodService {
	
	@Resource
	private TFoodMapper foodDao;
	@Override
	public int deleteByPrimaryKey(Long id) {
		return foodDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(TFood record) {
		return foodDao.insert(record);
	}

	@Override
	public int insertSelective(TFood record) {
		return foodDao.insertSelective(record);
	}

	@Override
	public TFood selectByPrimaryKey(Long id) {
		return foodDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(TFood record) {
		return foodDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TFood record) {
		return foodDao.updateByPrimaryKey(record);
	}

	@Override
	public int findListCount(Query query) {
		Map<String, Object> param = new HashMap<>();
		param.put("keyworld", query.getKeywords());
		return foodDao.findListCount(param);
	}

	@Override
	public List<TFood> findList(Query query) {
		Map<String, Object> param = new HashMap<>();
		param.put("keyworld", query.getKeywords());
		param.put("pageNo", query.getPageIndex());
		param.put("pageSize", query.getPageSize());
		return foodDao.findList(param);
	}

	
}
