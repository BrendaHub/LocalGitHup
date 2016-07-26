package com.doctor330.dao;

import com.doctor330.model.Shzxx;

public interface ShzxxMapper {
    int deleteByPrimaryKey(Long ID);

    int insert(Shzxx record);

    int insertSelective(Shzxx record);

    Shzxx selectByPrimaryKey(Long ID);

    int updateByPrimaryKeySelective(Shzxx record);

    int updateByPrimaryKey(Shzxx record);
}