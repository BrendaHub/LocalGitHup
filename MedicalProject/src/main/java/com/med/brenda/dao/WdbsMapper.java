package com.med.brenda.dao;

import com.med.brenda.model.Wdbs;

public interface WdbsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Wdbs record);

    int insertSelective(Wdbs record);

    Wdbs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Wdbs record);

    int updateByPrimaryKey(Wdbs record);
}