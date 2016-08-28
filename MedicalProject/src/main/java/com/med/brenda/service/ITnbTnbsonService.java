package com.med.brenda.service;

import java.util.List;

import com.med.brenda.model.TnbTnbson;
import com.med.brenda.model.TnbYinshi;

public interface ITnbTnbsonService {
	int deleteByPrimaryKey(Long id);

	int insert(TnbTnbson record);

	int insertSelective(TnbTnbson record);

	TnbTnbson selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(TnbTnbson record);

	int updateByPrimaryKey(TnbTnbson record);
	
	List<TnbTnbson> findFeedList(Long hzid, String itemcode, String date);
	int findByHzidDate(Long hzid, Long date, String itemcode);
	
	List<TnbTnbson> getTnbsonlistByDateRang(Long hzid,String itemcode, Long startDate, Long enddate);
}
