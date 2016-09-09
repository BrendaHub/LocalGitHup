package com.med.brenda.service;

import com.med.brenda.model.TActivity;

public interface IActivityService {

    int deleteByPrimaryKey(Long id);

    int insert(TActivity record);

    int insertSelective(TActivity record);

    TActivity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TActivity record);

    int updateByPrimaryKey(TActivity record);
    
    int findActivityByPhone(String _phone);
}
