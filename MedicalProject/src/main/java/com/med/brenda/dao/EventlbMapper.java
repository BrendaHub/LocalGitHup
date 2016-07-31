package com.med.brenda.dao;

import com.med.brenda.model.Eventlb;

public interface EventlbMapper {
    int deleteByPrimaryKey(Long ID);

    int insert(Eventlb record);

    int insertSelective(Eventlb record);

    Eventlb selectByPrimaryKey(Long ID);

    int updateByPrimaryKeySelective(Eventlb record);

    int updateByPrimaryKey(Eventlb record);
}