package com.med.brenda.dao;

import com.med.brenda.model.StnbSd;

public interface StnbSdMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StnbSd record);

    int insertSelective(StnbSd record);

    StnbSd selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StnbSd record);

    int updateByPrimaryKey(StnbSd record);
}