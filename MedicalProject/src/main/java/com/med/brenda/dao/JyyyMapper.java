package com.med.brenda.dao;

import com.med.brenda.model.Jyyy;

public interface JyyyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Jyyy record);

    int insertSelective(Jyyy record);

    Jyyy selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Jyyy record);

    int updateByPrimaryKey(Jyyy record);
}