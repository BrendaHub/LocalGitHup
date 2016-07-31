package com.med.brenda.dao;

import com.med.brenda.model.Sfjhmb;

public interface SfjhmbMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Sfjhmb record);

    int insertSelective(Sfjhmb record);

    Sfjhmb selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Sfjhmb record);

    int updateByPrimaryKey(Sfjhmb record);
}