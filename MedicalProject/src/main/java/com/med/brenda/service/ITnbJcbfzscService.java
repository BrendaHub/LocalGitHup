package com.med.brenda.service;

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
}
