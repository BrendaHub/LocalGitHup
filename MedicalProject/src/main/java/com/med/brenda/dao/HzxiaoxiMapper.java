package com.med.brenda.dao;

import com.med.brenda.model.Hzxiaoxi;

public interface HzxiaoxiMapper {
    int deleteByPrimaryKey(Long ID);

    int insert(Hzxiaoxi record);

    int insertSelective(Hzxiaoxi record);

    Hzxiaoxi selectByPrimaryKey(Long ID);

    int updateByPrimaryKeySelective(Hzxiaoxi record);

    int updateByPrimaryKey(Hzxiaoxi record);
}