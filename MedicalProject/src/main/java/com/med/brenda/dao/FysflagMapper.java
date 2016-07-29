package com.med.brenda.dao;

import com.med.brenda.model.Fysflag;

public interface FysflagMapper {
    int deleteByPrimaryKey(Long ID);

    int insert(Fysflag record);

    int insertSelective(Fysflag record);

    Fysflag selectByPrimaryKey(Long ID);

    int updateByPrimaryKeySelective(Fysflag record);

    int updateByPrimaryKey(Fysflag record);
}