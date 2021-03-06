package com.med.brenda.dao;

import java.util.List;
import java.util.Map;

import com.med.brenda.model.TFood;
import com.med.brenda.model.TYinshiCatecory;

public interface TYinshiCatecoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TYinshiCatecory record);

    int insertSelective(TYinshiCatecory record);

    TYinshiCatecory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TYinshiCatecory record);

    int updateByPrimaryKey(TYinshiCatecory record);
    
    int findListCount(Map<String, Object> conditionMap);
    
    List<TYinshiCatecory> findList(Map<String, Object> conditionMap);
}