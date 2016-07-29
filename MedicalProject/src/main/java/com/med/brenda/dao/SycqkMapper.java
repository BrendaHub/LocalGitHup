package com.med.brenda.dao;

import com.med.brenda.model.Sycqk;

public interface SycqkMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Sycqk record);

    int insertSelective(Sycqk record);

    Sycqk selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Sycqk record);

    int updateByPrimaryKey(Sycqk record);
}