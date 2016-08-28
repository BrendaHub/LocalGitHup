package com.med.brenda.dao;

import java.util.List;
import java.util.Map;

import com.med.brenda.model.TnbYinshi;

public interface TnbYinshiMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TnbYinshi record);

    int insertSelective(TnbYinshi record);

    TnbYinshi selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TnbYinshi record);

    int updateByPrimaryKey(TnbYinshi record);
    
    List<TnbYinshi> getYinshiListByHzid_date(Map<String, Object> param);
    
    List<TnbYinshi> getYinshiListByInFatherId(Map<String, Object> param);
}