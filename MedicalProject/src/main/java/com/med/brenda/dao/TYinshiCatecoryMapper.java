package com.med.brenda.dao;

import com.med.brenda.model.TYinshiCatecory;

public interface TYinshiCatecoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TYinshiCatecory record);

    int insertSelective(TYinshiCatecory record);

    TYinshiCatecory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TYinshiCatecory record);

    int updateByPrimaryKey(TYinshiCatecory record);
}