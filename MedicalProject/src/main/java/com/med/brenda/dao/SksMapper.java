package com.med.brenda.dao;

import com.med.brenda.model.Sks;

public interface SksMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Sks record);

    int insertSelective(Sks record);

    Sks selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Sks record);

    int updateByPrimaryKey(Sks record);
}