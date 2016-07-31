package com.med.brenda.dao;

import com.med.brenda.model.Im;

public interface ImMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Im record);

    int insertSelective(Im record);

    Im selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Im record);

    int updateByPrimaryKey(Im record);
}