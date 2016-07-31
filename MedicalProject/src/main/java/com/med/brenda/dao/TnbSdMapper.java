package com.med.brenda.dao;

import com.med.brenda.model.TnbSd;

public interface TnbSdMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TnbSd record);

    int insertSelective(TnbSd record);

    TnbSd selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TnbSd record);

    int updateByPrimaryKey(TnbSd record);
}