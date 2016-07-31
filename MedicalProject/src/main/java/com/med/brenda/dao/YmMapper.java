package com.med.brenda.dao;

import com.med.brenda.model.Ym;

public interface YmMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Ym record);

    int insertSelective(Ym record);

    Ym selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Ym record);

    int updateByPrimaryKey(Ym record);
}