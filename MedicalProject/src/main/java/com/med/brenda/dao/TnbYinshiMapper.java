package com.med.brenda.dao;

import com.med.brenda.model.TnbYinshi;

public interface TnbYinshiMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TnbYinshi record);

    int insertSelective(TnbYinshi record);

    TnbYinshi selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TnbYinshi record);

    int updateByPrimaryKey(TnbYinshi record);
}