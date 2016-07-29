package com.med.brenda.dao;

import com.med.brenda.model.StnbMzson;

public interface StnbMzsonMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StnbMzson record);

    int insertSelective(StnbMzson record);

    StnbMzson selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StnbMzson record);

    int updateByPrimaryKey(StnbMzson record);
}