package com.med.brenda.service;

import java.util.List;

import com.med.brenda.model.TnbYinshi;

public interface IYinshiService {
	int deleteByPrimaryKey(Long id);

	int insert(TnbYinshi record);

	int insertSelective(TnbYinshi record);

	TnbYinshi selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(TnbYinshi record);

	int updateByPrimaryKey(TnbYinshi record);
	
	List<TnbYinshi> getYinshiListByHzid_date(Long hzid, String itemcodeprefix, String date);
	
	List<TnbYinshi> getYinshilistByDateRang(Long hzid, Long startDate, Long enddate);
}
