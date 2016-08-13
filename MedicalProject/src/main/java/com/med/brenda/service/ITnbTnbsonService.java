package com.med.brenda.service;

import java.util.List;

import com.med.brenda.model.TnbTnbson;

public interface ITnbTnbsonService {
	int deleteByPrimaryKey(Long id);

	int insert(TnbTnbson record);

	int insertSelective(TnbTnbson record);

	TnbTnbson selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(TnbTnbson record);

	int updateByPrimaryKey(TnbTnbson record);
	
	List<TnbTnbson> findFeedList(Long hzid, String itemcodeprefix, String date);
}
