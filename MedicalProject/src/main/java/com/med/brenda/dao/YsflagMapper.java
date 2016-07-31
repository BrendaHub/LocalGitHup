package com.med.brenda.dao;

import com.med.brenda.model.Ysflag;

public interface YsflagMapper {
    int deleteByPrimaryKey(Long ID);

    int insert(Ysflag record);

    int insertSelective(Ysflag record);

    Ysflag selectByPrimaryKey(Long ID);

    int updateByPrimaryKeySelective(Ysflag record);

    int updateByPrimaryKey(Ysflag record);
}