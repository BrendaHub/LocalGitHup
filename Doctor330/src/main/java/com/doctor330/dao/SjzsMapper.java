package com.doctor330.dao;

import com.doctor330.model.Sjzs;

public interface SjzsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Sjzs record);

    int insertSelective(Sjzs record);

    Sjzs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Sjzs record);

    int updateByPrimaryKey(Sjzs record);
}