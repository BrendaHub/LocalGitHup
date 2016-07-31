package com.med.brenda.dao;

import com.med.brenda.model.Gms;

public interface GmsMapper {
    int deleteByPrimaryKey(Long ID);

    int insert(Gms record);

    int insertSelective(Gms record);

    Gms selectByPrimaryKey(Long ID);

    int updateByPrimaryKeySelective(Gms record);

    int updateByPrimaryKey(Gms record);
}