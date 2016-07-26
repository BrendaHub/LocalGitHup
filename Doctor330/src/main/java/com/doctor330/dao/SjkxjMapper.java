package com.doctor330.dao;

import com.doctor330.model.Sjkxj;

public interface SjkxjMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Sjkxj record);

    int insertSelective(Sjkxj record);

    Sjkxj selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Sjkxj record);

    int updateByPrimaryKeyWithBLOBs(Sjkxj record);

    int updateByPrimaryKey(Sjkxj record);
}