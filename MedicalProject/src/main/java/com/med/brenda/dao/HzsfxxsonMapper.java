package com.med.brenda.dao;

import java.util.List;

import com.med.brenda.model.Hzsfxxson;

public interface HzsfxxsonMapper {
    int deleteByPrimaryKey(Long ID);

    int insert(Hzsfxxson record);

    int insertSelective(Hzsfxxson record);

    Hzsfxxson selectByPrimaryKey(Long ID);

    int updateByPrimaryKeySelective(Hzsfxxson record);

    int updateByPrimaryKey(Hzsfxxson record);
    
    List<Hzsfxxson> findListByFatherId(Long fatherid);
}