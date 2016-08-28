package com.med.brenda.dao;

import java.util.List;
import java.util.Map;

import com.med.brenda.model.Hzsfxx;

public interface HzsfxxMapper {
    int deleteByPrimaryKey(Long ID);

    int insert(Hzsfxx record);

    int insertSelective(Hzsfxx record);

    Hzsfxx selectByPrimaryKey(Long ID);

    int updateByPrimaryKeySelective(Hzsfxx record);

    int updateByPrimaryKey(Hzsfxx record);
    
    List<Hzsfxx> findHzxfxxByDate_Hzid(Map<String,Object> param);
    
    void batchInsert(List<Hzsfxx> hzsfxxs);
    
    Hzsfxx findByHzidDataItemCode(Map<String,Object> param);
    
    Hzsfxx findByHzidDateTemp3(Map<String, Object> param);
    
    List<Hzsfxx> findByListDateRang(Map<String, Object> param);
    
    List<Hzsfxx> findListDateRangByTemp3(Map<String, Object> param);
    
}