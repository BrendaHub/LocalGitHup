package com.med.brenda.service;

import java.util.Map;

import com.med.brenda.model.ＳmsＶerifＣode;

public interface ISmsVerifCodeService {
    int deleteByPrimaryKey(Long id);

    int insert(ＳmsＶerifＣode record);

    int insertSelective(ＳmsＶerifＣode record);

    ＳmsＶerifＣode selectByPrimaryKey(Long id);
    
    ＳmsＶerifＣode selectByBiTuiCode(String pariCode);

    int updateByPrimaryKeySelective(ＳmsＶerifＣode record);

    int updateByPrimaryKey(ＳmsＶerifＣode record);
}
