package com.doctor330.dao;

import com.doctor330.model.StnbMzson;

public interface StnbMzsonMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StnbMzson record);

    int insertSelective(StnbMzson record);

    StnbMzson selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StnbMzson record);

    int updateByPrimaryKey(StnbMzson record);
}