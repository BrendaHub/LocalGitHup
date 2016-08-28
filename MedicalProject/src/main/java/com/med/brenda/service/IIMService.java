package com.med.brenda.service;

import java.util.List;

import com.med.brenda.model.Im;

public interface IIMService {
	int deleteByPrimaryKey(Long id);

    int insert(Im record);

    int insertSelective(Im record);

    Im selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Im record);

    int updateByPrimaryKey(Im record);
    
    List<Im> selectByYsid_Hzid(Long ysid, Long hzid);
}
