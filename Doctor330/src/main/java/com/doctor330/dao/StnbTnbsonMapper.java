package com.doctor330.dao;

import com.doctor330.model.StnbTnbson;

public interface StnbTnbsonMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StnbTnbson record);

    int insertSelective(StnbTnbson record);

    StnbTnbson selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StnbTnbson record);

    int updateByPrimaryKey(StnbTnbson record);
}