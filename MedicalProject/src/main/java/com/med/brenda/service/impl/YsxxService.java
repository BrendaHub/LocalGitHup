package com.med.brenda.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.med.brenda.dao.YsxxMapper;
import com.med.brenda.model.Ysxx;
import com.med.brenda.service.IYsxxService;

@Service
@Repository
public class YsxxService implements IYsxxService {


	@Resource
	private YsxxMapper ysxxDao;
	
	@Override
	public Ysxx ysLogon(String ysdlh, String yspwd) {
		Map<String,String> param = new HashMap<String,String>();
		param.put("dlh", ysdlh);
		param.put("password", yspwd);
		return ysxxDao.ysLogon(param);
	}

}
