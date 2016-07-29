package com.med.brenda.dao;

import com.med.brenda.model.Shzsssfjh;

public interface ShzsssfjhMapper {
    int deleteByPrimaryKey(Long ID);

    int insert(Shzsssfjh record);

    int insertSelective(Shzsssfjh record);

    Shzsssfjh selectByPrimaryKey(Long ID);

    int updateByPrimaryKeySelective(Shzsssfjh record);

    int updateByPrimaryKey(Shzsssfjh record);
}