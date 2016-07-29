package com.med.brenda.dao;

import com.med.brenda.model.StnbYinshi;

public interface StnbYinshiMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StnbYinshi record);

    int insertSelective(StnbYinshi record);

    StnbYinshi selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StnbYinshi record);

    int updateByPrimaryKey(StnbYinshi record);
}