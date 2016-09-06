package com.med.brenda.dao;

import java.util.List;
import java.util.Map;

import com.med.brenda.model.Hzxx;
import com.med.brenda.model.Ysxx;

public interface YsxxMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Ysxx record);

    int insertSelective(Ysxx record);

    Ysxx selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Ysxx record);

    int updateByPrimaryKey(Ysxx record);
    
    Ysxx ysLogon(Map<String,String> map);
    
    //根据患者ＩＤ 和 医生所属类型（1、肿瘤科； 2､内分泌科），查询出医生列表 
    List<Ysxx> findYsxxByHzidAndType(Map<String, Object> param);
}