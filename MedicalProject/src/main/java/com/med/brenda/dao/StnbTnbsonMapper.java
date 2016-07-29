package com.med.brenda.dao;

import com.med.brenda.model.StnbTnbson;

public interface StnbTnbsonMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StnbTnbson record);

    int insertSelective(StnbTnbson record);

    StnbTnbson selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StnbTnbson record);

    int updateByPrimaryKey(StnbTnbson record);
}