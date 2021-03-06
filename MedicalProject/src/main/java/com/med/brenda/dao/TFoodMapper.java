package com.med.brenda.dao;

import java.util.List;
import java.util.Map;

import com.med.brenda.model.Hzxx;
import com.med.brenda.model.TFood;

public interface TFoodMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TFood record);

    int insertSelective(TFood record);

    TFood selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TFood record);

    int updateByPrimaryKey(TFood record);
    

    int findListCount(Map<String, Object> conditionMap);
    
    List<TFood> findList(Map<String, Object> conditionMap);
}