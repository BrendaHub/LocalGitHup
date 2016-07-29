package com.med.brenda.dao;

import com.med.brenda.model.StnboTangyou;

public interface StnboTangyouMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StnboTangyou record);

    int insertSelective(StnboTangyou record);

    StnboTangyou selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StnboTangyou record);

    int updateByPrimaryKey(StnboTangyou record);
}