package com.doctor330.dao;

import com.doctor330.model.Shzsfxxson;

public interface ShzsfxxsonMapper {
    int deleteByPrimaryKey(Long ID);

    int insert(Shzsfxxson record);

    int insertSelective(Shzsfxxson record);

    Shzsfxxson selectByPrimaryKey(Long ID);

    int updateByPrimaryKeySelective(Shzsfxxson record);

    int updateByPrimaryKey(Shzsfxxson record);
}