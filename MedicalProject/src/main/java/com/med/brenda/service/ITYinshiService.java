package com.med.brenda.service;

import java.util.List;
import java.util.Map;

import com.med.brenda.controller.common.Query;
import com.med.brenda.model.TYinshi;

public interface ITYinshiService {

    int deleteByPrimaryKey(Long id);

    int insert(TYinshi record);

    int insertSelective(TYinshi record);

    TYinshi selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TYinshi record);

    int updateByPrimaryKeyWithBLOBs(TYinshi record);

    int updateByPrimaryKey(TYinshi record);

    int findListCount(Query query);
    
    List<TYinshi> findList(Query query);
}
