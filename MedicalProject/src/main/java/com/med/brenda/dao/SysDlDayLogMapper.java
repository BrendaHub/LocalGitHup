package com.med.brenda.dao;

import java.util.List;
import java.util.Map;

import com.med.brenda.model.SysDlDayLog;

public interface SysDlDayLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysDlDayLog record);

    int insertSelective(SysDlDayLog record);

    SysDlDayLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysDlDayLog record);

    int updateByPrimaryKey(SysDlDayLog record);
    
    List<SysDlDayLog> selectList(Map<String, Object> p );
}