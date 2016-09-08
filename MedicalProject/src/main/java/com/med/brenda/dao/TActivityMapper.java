package com.med.brenda.dao;

import com.med.brenda.model.TActivity;

public interface TActivityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TActivity record);

    int insertSelective(TActivity record);

    TActivity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TActivity record);

    int updateByPrimaryKey(TActivity record);
}