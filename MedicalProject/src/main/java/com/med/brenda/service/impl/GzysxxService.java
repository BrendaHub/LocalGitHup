package com.med.brenda.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.med.brenda.dao.GzysxxMapper;
import com.med.brenda.model.Gzysxx;
import com.med.brenda.service.IGzysxxService;
@Service
@Repository
public class GzysxxService implements IGzysxxService {


	@Resource
	private GzysxxMapper gzysxxDao;
	@Override
	public long addGzysxx(Gzysxx gzysxx) {
		return gzysxxDao.insert(gzysxx);
	}

}
