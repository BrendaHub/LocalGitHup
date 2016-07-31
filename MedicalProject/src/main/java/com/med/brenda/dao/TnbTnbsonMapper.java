package com.med.brenda.dao;

import com.med.brenda.model.TnbTnbson;

public interface TnbTnbsonMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TnbTnbson record);

    int insertSelective(TnbTnbson record);

    TnbTnbson selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TnbTnbson record);

    int updateByPrimaryKey(TnbTnbson record);
}