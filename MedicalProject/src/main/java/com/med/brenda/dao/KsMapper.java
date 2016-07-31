package com.med.brenda.dao;

import com.med.brenda.model.Ks;

public interface KsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Ks record);

    int insertSelective(Ks record);

    Ks selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Ks record);

    int updateByPrimaryKey(Ks record);
}