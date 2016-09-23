package com.med.brenda.dao;

import java.util.List;
import java.util.Map;

import com.med.brenda.model.ＳmsＶerifＣode;

public interface ＳmsＶerifＣodeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ＳmsＶerifＣode record);

    int insertSelective(ＳmsＶerifＣode record);

    ＳmsＶerifＣode selectByPrimaryKey(Long id);
    
    List<ＳmsＶerifＣode> selectByBiTuiCode(Map<String, Object> conditionMap);

    int updateByPrimaryKeySelective(ＳmsＶerifＣode record);

    int updateByPrimaryKey(ＳmsＶerifＣode record);
}