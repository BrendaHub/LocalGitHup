package com.doctor330.dao;

import com.doctor330.model.Sim;

public interface SimMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Sim record);

    int insertSelective(Sim record);

    Sim selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Sim record);

    int updateByPrimaryKey(Sim record);
}