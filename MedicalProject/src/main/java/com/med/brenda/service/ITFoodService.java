package com.med.brenda.service;

import java.util.List;

import com.med.brenda.controller.common.Query;
import com.med.brenda.model.Hzxx;
import com.med.brenda.model.TFood;

public interface ITFoodService {

    int deleteByPrimaryKey(Long id);

    int insert(TFood record);

    int insertSelective(TFood record);

    TFood selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TFood record);

    int updateByPrimaryKey(TFood record);
    

	int findListCount(Query query);
	
	List<TFood> findList(Query query);
}
