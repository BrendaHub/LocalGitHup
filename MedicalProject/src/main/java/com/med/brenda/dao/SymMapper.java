package com.med.brenda.dao;

import com.med.brenda.model.Sym;

public interface SymMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Sym record);

    int insertSelective(Sym record);

    Sym selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Sym record);

    int updateByPrimaryKey(Sym record);
}