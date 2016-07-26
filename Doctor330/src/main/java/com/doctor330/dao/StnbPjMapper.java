package com.doctor330.dao;

import com.doctor330.model.StnbPj;

public interface StnbPjMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StnbPj record);

    int insertSelective(StnbPj record);

    StnbPj selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StnbPj record);

    int updateByPrimaryKey(StnbPj record);
}