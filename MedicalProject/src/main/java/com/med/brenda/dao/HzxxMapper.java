package com.med.brenda.dao;

import java.util.List;
import java.util.Map;

import com.med.brenda.model.Hzxx;

public interface HzxxMapper {
    int deleteByPrimaryKey(Long ID);

    int insert(Hzxx record);

    int insertSelective(Hzxx record);

    Hzxx selectByPrimaryKey(Long ID);

    int updateByPrimaryKeySelective(Hzxx record);

    int updateByPrimaryKey(Hzxx record);
    
    Hzxx hzLogon(Map<String,String> map);
    
    int findListCount(Map<String, Object> conditionMap);
    
    List<Hzxx> findList(Map<String, Object> conditionMap);
}