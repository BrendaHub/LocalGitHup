package com.med.brenda.dao;

import com.med.brenda.model.Ysxx;

public interface YsxxMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Ysxx record);

    int insertSelective(Ysxx record);

    Ysxx selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Ysxx record);

    int updateByPrimaryKey(Ysxx record);
}