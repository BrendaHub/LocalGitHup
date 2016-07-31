package com.med.brenda.dao;

import com.med.brenda.model.Hzsssfjh;

public interface HzsssfjhMapper {
    int deleteByPrimaryKey(Long ID);

    int insert(Hzsssfjh record);

    int insertSelective(Hzsssfjh record);

    Hzsssfjh selectByPrimaryKey(Long ID);

    int updateByPrimaryKeySelective(Hzsssfjh record);

    int updateByPrimaryKey(Hzsssfjh record);
}