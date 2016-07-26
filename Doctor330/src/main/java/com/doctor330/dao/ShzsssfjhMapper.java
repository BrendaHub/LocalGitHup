package com.doctor330.dao;

import com.doctor330.model.Shzsssfjh;

public interface ShzsssfjhMapper {
    int deleteByPrimaryKey(Long ID);

    int insert(Shzsssfjh record);

    int insertSelective(Shzsssfjh record);

    Shzsssfjh selectByPrimaryKey(Long ID);

    int updateByPrimaryKeySelective(Shzsssfjh record);

    int updateByPrimaryKey(Shzsssfjh record);
}