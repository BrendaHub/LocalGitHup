package com.med.brenda.dao;

import com.med.brenda.model.Smz;

public interface SmzMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Smz record);

    int insertSelective(Smz record);

    Smz selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Smz record);

    int updateByPrimaryKey(Smz record);
}