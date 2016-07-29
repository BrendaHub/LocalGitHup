package com.med.brenda.dao;

import com.med.brenda.model.Swdbs;

public interface SwdbsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Swdbs record);

    int insertSelective(Swdbs record);

    Swdbs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Swdbs record);

    int updateByPrimaryKey(Swdbs record);
}