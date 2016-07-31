package com.med.brenda.dao;

import com.med.brenda.model.Hzsfxx;

public interface HzsfxxMapper {
    int deleteByPrimaryKey(Long ID);

    int insert(Hzsfxx record);

    int insertSelective(Hzsfxx record);

    Hzsfxx selectByPrimaryKey(Long ID);

    int updateByPrimaryKeySelective(Hzsfxx record);

    int updateByPrimaryKey(Hzsfxx record);
}