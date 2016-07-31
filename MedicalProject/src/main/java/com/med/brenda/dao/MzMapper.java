package com.med.brenda.dao;

import com.med.brenda.model.Mz;

public interface MzMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Mz record);

    int insertSelective(Mz record);

    Mz selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Mz record);

    int updateByPrimaryKey(Mz record);
}