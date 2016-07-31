package com.med.brenda.dao;

import com.med.brenda.model.TnbMzson;

public interface TnbMzsonMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TnbMzson record);

    int insertSelective(TnbMzson record);

    TnbMzson selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TnbMzson record);

    int updateByPrimaryKey(TnbMzson record);
}