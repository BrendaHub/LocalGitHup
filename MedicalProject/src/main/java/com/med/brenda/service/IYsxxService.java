package com.med.brenda.service;

import java.util.Map;

import com.med.brenda.model.Ysxx;

public interface IYsxxService {

	Ysxx ysLogon(String ysdlh, String yspwd);
	
	int deleteByPrimaryKey(Long id);

    int insert(Ysxx record);

    int insertSelective(Ysxx record);

    Ysxx selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Ysxx record);

    int updateByPrimaryKey(Ysxx record);
    
    Ysxx ysLogon(Map<String,String> map);
}
