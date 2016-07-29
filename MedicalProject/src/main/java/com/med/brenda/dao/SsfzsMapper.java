package com.med.brenda.dao;

import com.med.brenda.model.Ssfzs;

public interface SsfzsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Ssfzs record);

    int insertSelective(Ssfzs record);

    Ssfzs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Ssfzs record);

    int updateByPrimaryKey(Ssfzs record);
}