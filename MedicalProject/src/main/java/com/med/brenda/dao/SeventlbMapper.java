package com.med.brenda.dao;

import com.med.brenda.model.Seventlb;

public interface SeventlbMapper {
    int deleteByPrimaryKey(Long ID);

    int insert(Seventlb record);

    int insertSelective(Seventlb record);

    Seventlb selectByPrimaryKey(Long ID);

    int updateByPrimaryKeySelective(Seventlb record);

    int updateByPrimaryKey(Seventlb record);
}