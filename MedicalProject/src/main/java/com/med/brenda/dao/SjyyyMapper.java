package com.med.brenda.dao;

import com.med.brenda.model.Sjyyy;

public interface SjyyyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Sjyyy record);

    int insertSelective(Sjyyy record);

    Sjyyy selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Sjyyy record);

    int updateByPrimaryKey(Sjyyy record);
}