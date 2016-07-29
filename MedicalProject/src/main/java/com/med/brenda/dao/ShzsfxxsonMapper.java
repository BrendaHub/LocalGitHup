package com.med.brenda.dao;

import com.med.brenda.model.Shzsfxxson;

public interface ShzsfxxsonMapper {
    int deleteByPrimaryKey(Long ID);

    int insert(Shzsfxxson record);

    int insertSelective(Shzsfxxson record);

    Shzsfxxson selectByPrimaryKey(Long ID);

    int updateByPrimaryKeySelective(Shzsfxxson record);

    int updateByPrimaryKey(Shzsfxxson record);
}