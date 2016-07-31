package com.med.brenda.dao;

import com.med.brenda.model.Yszdms;

public interface YszdmsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Yszdms record);

    int insertSelective(Yszdms record);

    Yszdms selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Yszdms record);

    int updateByPrimaryKey(Yszdms record);
}