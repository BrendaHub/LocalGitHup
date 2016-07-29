package com.med.brenda.dao;

import com.med.brenda.model.Sgzysxx;

public interface SgzysxxMapper {
    int deleteByPrimaryKey(Long ID);

    int insert(Sgzysxx record);

    int insertSelective(Sgzysxx record);

    Sgzysxx selectByPrimaryKey(Long ID);

    int updateByPrimaryKeySelective(Sgzysxx record);

    int updateByPrimaryKey(Sgzysxx record);
}