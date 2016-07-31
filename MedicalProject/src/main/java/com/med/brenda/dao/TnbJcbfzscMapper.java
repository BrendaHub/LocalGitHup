package com.med.brenda.dao;

import com.med.brenda.model.TnbJcbfzsc;

public interface TnbJcbfzscMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TnbJcbfzsc record);

    int insertSelective(TnbJcbfzsc record);

    TnbJcbfzsc selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TnbJcbfzsc record);

    int updateByPrimaryKey(TnbJcbfzsc record);
}