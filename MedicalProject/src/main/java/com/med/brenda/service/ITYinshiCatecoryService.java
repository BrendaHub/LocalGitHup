package com.med.brenda.service;

import java.util.List;

import com.med.brenda.controller.common.Query;
import com.med.brenda.model.TFood;
import com.med.brenda.model.TYinshiCatecory;

public interface ITYinshiCatecoryService {

    int deleteByPrimaryKey(Long id);

    int insert(TYinshiCatecory record);

    int insertSelective(TYinshiCatecory record);

    TYinshiCatecory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TYinshiCatecory record);

    int updateByPrimaryKey(TYinshiCatecory record);

	int findListCount(Query query);
	
	List<TYinshiCatecory> findList(Query query);
}
