package com.med.brenda.dao;

import com.med.brenda.model.TnbYwfa;

public interface TnbYwfaMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TnbYwfa record);

    int insertSelective(TnbYwfa record);

    TnbYwfa selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TnbYwfa record);

    int updateByPrimaryKey(TnbYwfa record);
}