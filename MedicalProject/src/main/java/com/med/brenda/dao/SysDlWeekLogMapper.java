package com.med.brenda.dao;

import java.util.List;
import java.util.Map;

import com.med.brenda.model.SysDlWeekLog;

public interface SysDlWeekLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysDlWeekLog record);

    int insertSelective(SysDlWeekLog record);

    SysDlWeekLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysDlWeekLog record);

    int updateByPrimaryKey(SysDlWeekLog record);
    
    List<SysDlWeekLog> selectList(Map<String, Object> p );
}