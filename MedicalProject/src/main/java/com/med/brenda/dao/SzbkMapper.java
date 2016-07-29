package com.med.brenda.dao;

import com.med.brenda.model.Szbk;

public interface SzbkMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Szbk record);

    int insertSelective(Szbk record);

    Szbk selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Szbk record);

    int updateByPrimaryKey(Szbk record);
}