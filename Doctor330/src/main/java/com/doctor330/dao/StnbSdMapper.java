package com.doctor330.dao;

import com.doctor330.model.StnbSd;

public interface StnbSdMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StnbSd record);

    int insertSelective(StnbSd record);

    StnbSd selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StnbSd record);

    int updateByPrimaryKey(StnbSd record);
}