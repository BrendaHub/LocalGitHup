package com.med.brenda.dao;

import java.util.List;
import java.util.Map;

import com.med.brenda.model.TnbJcbfzsc;

public interface TnbJcbfzscMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TnbJcbfzsc record);

    int insertSelective(TnbJcbfzsc record);

    TnbJcbfzsc selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TnbJcbfzsc record);

    int updateByPrimaryKey(TnbJcbfzsc record);
    //根据患者ＩＤ， itemcode , 日期 查询出检查化验数据
    List<TnbJcbfzsc> findHzidItemCodeDate(Map<String,Object> param);
}