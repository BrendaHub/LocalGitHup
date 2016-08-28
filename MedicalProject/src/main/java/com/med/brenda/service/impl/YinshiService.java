package com.med.brenda.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.med.brenda.dao.HzsfxxMapper;
import com.med.brenda.dao.TnbYinshiMapper;
import com.med.brenda.model.Hzsfxx;
import com.med.brenda.model.TnbYinshi;
import com.med.brenda.service.IHzsfxxService;
import com.med.brenda.service.IYinshiService;
import com.med.brenda.util.GlobalVariables;

@Service
@Repository
public class YinshiService implements IYinshiService {

	@Resource
	private TnbYinshiMapper yinshiDao;
	@Resource
	private IHzsfxxService hzsfxxService;
	
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

	@Override
	public List<TnbYinshi> getYinshilistByDateRang(Long hzid, Long startDate, Long enddate) {
		//在s_hzsfxx 表中查询出当前患者在指定的时间区间内，饮食的记录条数
		List<Hzsfxx> hzsfxxList = hzsfxxService.findByListDateRang(hzid, GlobalVariables.YINSHI_ITEMCODE, startDate, enddate);
		if(hzsfxxList != null && hzsfxxList.size() > 0){
			Long[] fartherids = new Long[hzsfxxList.size()];
			int index = 0 ; 
			for(Iterator<Hzsfxx> it = hzsfxxList.iterator(); it.hasNext(); ){
				fartherids[index] = it.next().getID();
			}
			Map<String, Object> param = new HashMap<>();
			param.put("hzid", hzid);
			param.put("fartherids", fartherids);
			List<TnbYinshi> ysList = yinshiDao.getYinshiListByInFatherId(param);
			if(ysList != null && !ysList.isEmpty()){
				return ysList;
			}else{
				return null;
			}
		}else{
			return null;
		}
	}

}
