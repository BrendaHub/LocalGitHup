package com.doctor330.dao;

import com.doctor330.model.Sycqk;

public interface SycqkMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Sycqk record);

    int insertSelective(Sycqk record);

    Sycqk selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Sycqk record);

    int updateByPrimaryKey(Sycqk record);
}