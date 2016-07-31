package com.med.brenda.dao;

import com.med.brenda.model.Sfzs;

public interface SfzsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Sfzs record);

    int insertSelective(Sfzs record);

    Sfzs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Sfzs record);

    int updateByPrimaryKey(Sfzs record);
}