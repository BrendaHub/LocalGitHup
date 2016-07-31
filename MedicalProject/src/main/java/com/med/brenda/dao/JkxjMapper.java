package com.med.brenda.dao;

import com.med.brenda.model.Jkxj;

public interface JkxjMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Jkxj record);

    int insertSelective(Jkxj record);

    Jkxj selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Jkxj record);

    int updateByPrimaryKeyWithBLOBs(Jkxj record);

    int updateByPrimaryKey(Jkxj record);
}