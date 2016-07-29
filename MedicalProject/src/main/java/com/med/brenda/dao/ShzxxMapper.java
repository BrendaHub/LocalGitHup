package com.med.brenda.dao;

import com.med.brenda.model.Shzxx;

public interface ShzxxMapper {
    int deleteByPrimaryKey(Long ID);

    int insert(Shzxx record);

    int insertSelective(Shzxx record);

    Shzxx selectByPrimaryKey(Long ID);

    int updateByPrimaryKeySelective(Shzxx record);

    int updateByPrimaryKey(Shzxx record);
}