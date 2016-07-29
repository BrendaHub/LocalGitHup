package com.med.brenda.dao;

import com.med.brenda.model.StnbYwfa;

public interface StnbYwfaMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StnbYwfa record);

    int insertSelective(StnbYwfa record);

    StnbYwfa selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StnbYwfa record);

    int updateByPrimaryKey(StnbYwfa record);
}