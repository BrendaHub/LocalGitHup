package com.med.brenda.dao;

import com.med.brenda.model.StnboYgdjb;

public interface StnboYgdjbMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StnboYgdjb record);

    int insertSelective(StnboYgdjb record);

    StnboYgdjb selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StnboYgdjb record);

    int updateByPrimaryKey(StnboYgdjb record);
}