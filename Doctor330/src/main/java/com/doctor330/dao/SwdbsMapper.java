package com.doctor330.dao;

import com.doctor330.model.Swdbs;

public interface SwdbsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Swdbs record);

    int insertSelective(Swdbs record);

    Swdbs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Swdbs record);

    int updateByPrimaryKey(Swdbs record);
}