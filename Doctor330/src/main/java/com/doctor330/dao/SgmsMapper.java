package com.doctor330.dao;

import com.doctor330.model.Sgms;

public interface SgmsMapper {
    int deleteByPrimaryKey(Long ID);

    int insert(Sgms record);

    int insertSelective(Sgms record);

    Sgms selectByPrimaryKey(Long ID);

    int updateByPrimaryKeySelective(Sgms record);

    int updateByPrimaryKey(Sgms record);
}