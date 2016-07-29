package com.med.brenda.dao;

import com.med.brenda.model.Sysxx;

public interface SysxxMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Sysxx record);

    int insertSelective(Sysxx record);

    Sysxx selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Sysxx record);

    int updateByPrimaryKey(Sysxx record);
}