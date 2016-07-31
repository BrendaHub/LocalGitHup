package com.med.brenda.dao;

import com.med.brenda.model.TnbPj;

public interface TnbPjMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TnbPj record);

    int insertSelective(TnbPj record);

    TnbPj selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TnbPj record);

    int updateByPrimaryKey(TnbPj record);
}