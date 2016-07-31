package com.med.brenda.dao;

import com.med.brenda.model.Jzs;

public interface JzsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Jzs record);

    int insertSelective(Jzs record);

    Jzs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Jzs record);

    int updateByPrimaryKey(Jzs record);
}