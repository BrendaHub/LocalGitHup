package com.med.brenda.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.med.brenda.model.TnbJcbfzsc;

public interface ITnbJcbfzscService {
	int deleteByPrimaryKey(Long id);

    int insert(TnbJcbfzsc record);

    int insertSelective(TnbJcbfzsc record);

    TnbJcbfzsc selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TnbJcbfzsc record);

    int updateByPrimaryKey(TnbJcbfzsc record);
    //添加检查、化验数据
    Long addInspectData(Long Hzid, String date, Long fatherid, JSONObject inspectData);
    //修改检查，化验数据
    boolean modifyInspectData(Long dataid , JSONObject inspectData);
    //根据患者ＩＤ， itemcode , 日期 查询出检查化验数据
    List<TnbJcbfzsc> findHzidItemCodeDate(Long hzid, String itemcode, String date);
    
}
