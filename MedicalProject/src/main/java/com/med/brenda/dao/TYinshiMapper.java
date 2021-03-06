package com.med.brenda.dao;

import java.util.List;
import java.util.Map;

import com.med.brenda.model.TYinshi;
import com.med.brenda.model.TYinshiCatecory;

public interface TYinshiMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TYinshi record);

    int insertSelective(TYinshi record);

    TYinshi selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TYinshi record);

    int updateByPrimaryKeyWithBLOBs(TYinshi record);

    int updateByPrimaryKey(TYinshi record);
    
    int findListCount(Map<String, Object> conditionMap);
    
    List<TYinshi> findList(Map<String, Object> conditionMap);
}