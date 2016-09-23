package com.med.brenda.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.med.brenda.dao.ＳmsＶerifＣodeMapper;
import com.med.brenda.model.ＳmsＶerifＣode;
import com.med.brenda.service.ISmsVerifCodeService;

@Service
@Repository
public class SmsVerifCodeService implements ISmsVerifCodeService {

	@Resource
	private ＳmsＶerifＣodeMapper svcDao;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return svcDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ＳmsＶerifＣode record) {
		return svcDao.insert(record);
	}

	@Override
	public int insertSelective(ＳmsＶerifＣode record) {
		return svcDao.insertSelective(record);
	}

	@Override
	public ＳmsＶerifＣode selectByPrimaryKey(Long id) {
		return svcDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ＳmsＶerifＣode record) {
		return svcDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ＳmsＶerifＣode record) {
		return svcDao.updateByPrimaryKey(record);
	}

	@Override
	public ＳmsＶerifＣode selectByBiTuiCode(String pariCode) {
		Map<String,Object> param = new HashMap<>();
		param.put("paircode", pariCode);
		
		List<ＳmsＶerifＣode> list = svcDao.selectByBiTuiCode(param);
		if(list != null && list.size() >0){
			ＳmsＶerifＣode svc = list.get(0);
			if(svc != null){
				return svc;
			}else{
				return null;
			}
		}else{
			return null;
		}
	}

}
