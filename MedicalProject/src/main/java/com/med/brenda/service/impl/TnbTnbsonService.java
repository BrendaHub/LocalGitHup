package com.med.brenda.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.med.brenda.dao.TnbTnbsonMapper;
import com.med.brenda.model.Hzsfxx;
import com.med.brenda.model.TnbTnbson;
import com.med.brenda.model.TnbYinshi;
import com.med.brenda.service.IHzsfxxService;
import com.med.brenda.service.ITnbTnbsonService;
import com.med.brenda.util.GlobalVariables;


@Service
@Repository
public class TnbTnbsonService implements ITnbTnbsonService {

	@Resource
	private TnbTnbsonMapper tnbsonDao;
	@Resource
	private IHzsfxxService hzsfxxService;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return tnbsonDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(TnbTnbson record) {
		return tnbsonDao.insert(record);
	}

	@Override
	public int insertSelective(TnbTnbson record) {
		return tnbsonDao.insertSelective(record);
	}

	@Override
	public TnbTnbson selectByPrimaryKey(Long id) {
		return tnbsonDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(TnbTnbson record) {
		return tnbsonDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TnbTnbson record) {
		return tnbsonDao.updateByPrimaryKey(record);
	}

	@Override
	public List<TnbTnbson> findFeedList(Long hzid, String itemcode, String date) {
		Map<String,Object> param = new HashMap<>();
		param.put("hzid", hzid);
		param.put("date", date);
		param.put("itemcode", itemcode);
		return tnbsonDao.findFeedListByHzid_Date(param);
	}

	@Override
	public int findByHzidDate(Long hzid, Long date, String itemcode) {
		Map<String, Object> param = new HashMap<>();
		param.put("hzid", hzid);
		param.put("date", date);
		param.put("itemcode", itemcode);
		return tnbsonDao.findFeedListByHzid_DateCount(param);
	}

	@Override
	public List<TnbTnbson> getTnbsonlistByDateRang(Long hzid,String itemcode, Long startDate, Long enddate) {
		//在s_hzsfxx 表中查询出当前患者在指定的时间区间内，饮食的记录条数
		List<Hzsfxx> hzsfxxList = hzsfxxService.findByListDateRang(hzid, itemcode, startDate, enddate);
		if(hzsfxxList != null && hzsfxxList.size() > 0){
			Long[] fartherids = new Long[hzsfxxList.size()];
			int index = 0 ; 
			for(Iterator<Hzsfxx> it = hzsfxxList.iterator(); it.hasNext(); ){
				fartherids[index] = it.next().getID();
				index++;
			}
			Map<String, Object> param = new HashMap<>();
			param.put("hzid", hzid);
			param.put("fartherids", fartherids);
			List<TnbTnbson> ysList = tnbsonDao.getTnbsonListByInFatherId(param);
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
