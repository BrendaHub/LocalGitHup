package com.doctor330.dao;

import com.doctor330.model.Syszdms;

public interface SyszdmsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Syszdms record);

    int insertSelective(Syszdms record);

    Syszdms selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Syszdms record);

    int updateByPrimaryKey(Syszdms record);
}