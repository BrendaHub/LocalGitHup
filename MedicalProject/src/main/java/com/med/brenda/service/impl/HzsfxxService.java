package com.med.brenda.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.med.brenda.dao.HzsfxxMapper;
import com.med.brenda.model.Hzsfxx;
import com.med.brenda.service.IHzsfxxService;
@Service
@Repository
public class HzsfxxService implements IHzsfxxService {
	Logger logger = Logger.getLogger(HzsfxxService.class);
	
	@Resource
	private HzsfxxMapper hzsfxxDao;
	
	@Override
	public boolean checkHzxfxxBaseDB(Long hzid, Long sfdate) {
		Map<String , Object> param = new HashMap<>();
		param.put("hzid", hzid);
		param.put("sfdate", sfdate);
		List<Hzsfxx> hzsfxx = hzsfxxDao.findHzxfxxByDate_Hzid(param);
		if(hzsfxx != null && hzsfxx.size() > 0){
			if(hzsfxx.size() < 8){
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void addHzsfxxBeaseDB(List<Hzsfxx> sfs) {
		hzsfxxDao.batchInsert(sfs);
	}
	

}
