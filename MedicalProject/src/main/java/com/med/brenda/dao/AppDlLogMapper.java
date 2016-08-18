package com.med.brenda.dao;

import com.med.brenda.model.AppDlLog;

public interface AppDlLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AppDlLog record);

    int insertSelective(AppDlLog record);

    AppDlLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppDlLog record);

    int updateByPrimaryKey(AppDlLog record);
}