package com.med.brenda.dao;

import com.med.brenda.model.SysDlDayLog;

public interface SysDlDayLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysDlDayLog record);

    int insertSelective(SysDlDayLog record);

    SysDlDayLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysDlDayLog record);

    int updateByPrimaryKey(SysDlDayLog record);
}