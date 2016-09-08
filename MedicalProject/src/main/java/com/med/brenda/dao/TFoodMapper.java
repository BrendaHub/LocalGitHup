package com.med.brenda.dao;

import com.med.brenda.model.TFood;

public interface TFoodMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TFood record);

    int insertSelective(TFood record);

    TFood selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TFood record);

    int updateByPrimaryKey(TFood record);
}