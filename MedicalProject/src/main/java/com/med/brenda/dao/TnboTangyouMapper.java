package com.med.brenda.dao;

import com.med.brenda.model.TnboTangyou;

public interface TnboTangyouMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TnboTangyou record);

    int insertSelective(TnboTangyou record);

    TnboTangyou selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TnboTangyou record);

    int updateByPrimaryKey(TnboTangyou record);
}