package com.med.brenda.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.med.brenda.controller.common.Query;
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
	@Override
	public long addHz(Hzxx hz) {
		return hzxxDao.insert(hz);
	}
	@Override
	public int updateByPrimaryKeySelective(Hzxx record) {
		return hzxxDao.updateByPrimaryKeySelective(record);
	}
	@Override
	public int deleteByPrimaryKey(Long ID) {
		return hzxxDao.deleteByPrimaryKey(ID);
	}
	@Override
	public int insert(Hzxx record) {
		return hzxxDao.insert(record);
	}
	@Override
	public int insertSelective(Hzxx record) {
		return hzxxDao.insertSelective(record);
	}
	@Override
	public Hzxx selectByPrimaryKey(Long ID) {
		return hzxxDao.selectByPrimaryKey(ID);
	}
	@Override
	public int updateByPrimaryKey(Hzxx record) {
		return hzxxDao.updateByPrimaryKey(record);
	}
	
	
	@Override
	public int findListCount(Query query) {
		Map<String,Object> conditionMap = new HashMap<>();
		conditionMap.put("keyworld", query.getKeywords());
		return hzxxDao.findListCount(conditionMap);
	}
	@Override
	public List<Hzxx> findList(Query query) {
		Map<String,Object> conditionMap = new HashMap<>();
		conditionMap.put("keyworld", query.getKeywords());
		conditionMap.put("pageNo", query.getPageIndex());
		conditionMap.put("pageSize", query.getPageSize());
		return hzxxDao.findList(conditionMap);
	}
	
	@Override
	public int findHzxxByTemp7Count(String temp7, Query query) {
		Map<String, Object> param = new HashMap<>();
		param.put("temp7", temp7);
		return hzxxDao.findHzxxByTemp7Count(param);
	}
	@Override
	public List<Hzxx> findHzxxByTemp7(String temp7, Query query) {
		Map<String, Object> param = new HashMap<>();
		param.put("temp7", temp7);
		param.put("pageNo", query.getPageIndex());
		param.put("pageSize", query.getPageSize());
		return hzxxDao.findHzxxByTemp7(param);
	}
	@Override
	public Hzxx findHzxxBySFZH(String sfzcode) {
		Map<String, Object> param = new HashMap<>();
		param.put("sfzh", sfzcode);
		List<Hzxx> list = hzxxDao.findHzxxBySFZH(param);
		if(list != null && list.size() > 0){
			return list.get(0);
		}else{
			return null;
		}
	}

}
