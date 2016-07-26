package com.doctor330.dao;

import com.doctor330.model.Ssfjhmb;

public interface SsfjhmbMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Ssfjhmb record);

    int insertSelective(Ssfjhmb record);

    Ssfjhmb selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Ssfjhmb record);

    int updateByPrimaryKey(Ssfjhmb record);
}