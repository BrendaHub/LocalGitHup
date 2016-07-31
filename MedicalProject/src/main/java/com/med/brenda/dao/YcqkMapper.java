package com.med.brenda.dao;

import com.med.brenda.model.Ycqk;

public interface YcqkMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Ycqk record);

    int insertSelective(Ycqk record);

    Ycqk selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Ycqk record);

    int updateByPrimaryKey(Ycqk record);
}