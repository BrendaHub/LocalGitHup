package com.med.brenda.dao;

import com.med.brenda.model.Fhzflag;

public interface FhzflagMapper {
    int deleteByPrimaryKey(Long ID);

    int insert(Fhzflag record);

    int insertSelective(Fhzflag record);

    Fhzflag selectByPrimaryKey(Long ID);

    int updateByPrimaryKeySelective(Fhzflag record);

    int updateByPrimaryKey(Fhzflag record);
}