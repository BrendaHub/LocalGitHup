package com.med.brenda.dao;

import com.med.brenda.model.Syszdms;

public interface SyszdmsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Syszdms record);

    int insertSelective(Syszdms record);

    Syszdms selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Syszdms record);

    int updateByPrimaryKey(Syszdms record);
}