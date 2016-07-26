package com.doctor330.dao;

import com.doctor330.model.StnbJcbfzsc;

public interface StnbJcbfzscMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StnbJcbfzsc record);

    int insertSelective(StnbJcbfzsc record);

    StnbJcbfzsc selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StnbJcbfzsc record);

    int updateByPrimaryKey(StnbJcbfzsc record);
}