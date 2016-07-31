package com.med.brenda.dao;

import com.med.brenda.model.TnboYgdjb;

public interface TnboYgdjbMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TnboYgdjb record);

    int insertSelective(TnboYgdjb record);

    TnboYgdjb selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TnboYgdjb record);

    int updateByPrimaryKey(TnboYgdjb record);
}