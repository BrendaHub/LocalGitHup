package com.med.brenda.dao;

import com.med.brenda.model.Sgms;

public interface SgmsMapper {
    int deleteByPrimaryKey(Long ID);

    int insert(Sgms record);

    int insertSelective(Sgms record);

    Sgms selectByPrimaryKey(Long ID);

    int updateByPrimaryKeySelective(Sgms record);

    int updateByPrimaryKey(Sgms record);
}