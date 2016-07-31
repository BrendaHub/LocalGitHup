package com.med.brenda.dao;

import com.med.brenda.model.Gzysxx;

public interface GzysxxMapper {
    int deleteByPrimaryKey(Long ID);

    int insert(Gzysxx record);

    int insertSelective(Gzysxx record);

    Gzysxx selectByPrimaryKey(Long ID);

    int updateByPrimaryKeySelective(Gzysxx record);

    int updateByPrimaryKey(Gzysxx record);
}