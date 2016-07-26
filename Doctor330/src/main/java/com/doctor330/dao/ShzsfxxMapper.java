package com.doctor330.dao;

import com.doctor330.model.Shzsfxx;

public interface ShzsfxxMapper {
    int deleteByPrimaryKey(Long ID);

    int insert(Shzsfxx record);

    int insertSelective(Shzsfxx record);

    Shzsfxx selectByPrimaryKey(Long ID);

    int updateByPrimaryKeySelective(Shzsfxx record);

    int updateByPrimaryKey(Shzsfxx record);
}