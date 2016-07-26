package com.doctor330.dao;

import com.doctor330.model.Sks;

public interface SksMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Sks record);

    int insertSelective(Sks record);

    Sks selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Sks record);

    int updateByPrimaryKey(Sks record);
}