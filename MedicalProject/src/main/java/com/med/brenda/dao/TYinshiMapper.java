package com.med.brenda.dao;

import com.med.brenda.model.TYinshi;

public interface TYinshiMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TYinshi record);

    int insertSelective(TYinshi record);

    TYinshi selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TYinshi record);

    int updateByPrimaryKeyWithBLOBs(TYinshi record);

    int updateByPrimaryKey(TYinshi record);
}