package com.doctor330.dao;

import com.doctor330.model.Shzxiaoxi;

public interface ShzxiaoxiMapper {
    int deleteByPrimaryKey(Long ID);

    int insert(Shzxiaoxi record);

    int insertSelective(Shzxiaoxi record);

    Shzxiaoxi selectByPrimaryKey(Long ID);

    int updateByPrimaryKeySelective(Shzxiaoxi record);

    int updateByPrimaryKey(Shzxiaoxi record);
}