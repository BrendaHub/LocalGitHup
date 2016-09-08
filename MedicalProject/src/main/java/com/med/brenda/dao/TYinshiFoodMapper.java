package com.med.brenda.dao;

import com.med.brenda.model.TYinshiFood;

public interface TYinshiFoodMapper {
    int insert(TYinshiFood record);

    int insertSelective(TYinshiFood record);
}