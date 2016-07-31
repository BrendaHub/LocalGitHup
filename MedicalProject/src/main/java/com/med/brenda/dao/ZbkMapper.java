package com.med.brenda.dao;

import com.med.brenda.model.Zbk;

public interface ZbkMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Zbk record);

    int insertSelective(Zbk record);

    Zbk selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Zbk record);

    int updateByPrimaryKey(Zbk record);
}