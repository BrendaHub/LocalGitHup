package com.med.brenda.dao;

import java.util.List;
import java.util.Map;

import com.med.brenda.model.Im;

public interface ImMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Im record);

    int insertSelective(Im record);

    Im selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Im record);

    int updateByPrimaryKey(Im record);
    
    List<Im> selectByHzid_Ysid(Map<String,Object> param);
}