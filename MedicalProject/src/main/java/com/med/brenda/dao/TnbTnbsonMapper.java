package com.med.brenda.dao;

import java.util.List;
import java.util.Map;

import com.med.brenda.model.TnbTnbson;

public interface TnbTnbsonMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TnbTnbson record);

    int insertSelective(TnbTnbson record);

    TnbTnbson selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TnbTnbson record);

    int updateByPrimaryKey(TnbTnbson record);
    
    List<TnbTnbson> findFeedListByHzid_Date(Map<String, Object> param);
    
    int findFeedListByHzid_DateCount(Map<String, Object> param);
}