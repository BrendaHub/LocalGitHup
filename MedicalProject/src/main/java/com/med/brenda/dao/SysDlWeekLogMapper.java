package com.med.brenda.dao;

import com.med.brenda.model.SysDlWeekLog;

public interface SysDlWeekLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysDlWeekLog record);

    int insertSelective(SysDlWeekLog record);

    SysDlWeekLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysDlWeekLog record);

    int updateByPrimaryKey(SysDlWeekLog record);
}