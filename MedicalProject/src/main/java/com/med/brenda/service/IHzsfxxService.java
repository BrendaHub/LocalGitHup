package com.med.brenda.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.med.brenda.model.Hzsfxx;

/**
 * 患者随访信息表
 * <p>MedicalApp</p>
 * <p>Title: IHzsfxxService.java</p>
 * <p>Description:$</p>
 * <p>Package:com.med.brenda.service</p>
 * @author bgly
 * @date 2016年8月11日 下午7:09:21
 * @tag 
 * @encode
 */
public interface IHzsfxxService {

	
	/**
	 * 判断当前用户在hzsfxx表里面是否有基础数据
	 *  86562	1830		2016/8/11	015			血糖	66.png	1	015	
		86563	1830		2016/8/11	016			胰岛素	67.png	1	016	
		86564	1830		2016/8/11	021			饮食	68.png	1	021	
		86565	1830		2016/8/11	022			运动	69.png	1	022	
		86566	1830		2016/8/11	023			症状	70.png	1	023	
		86567	1830		2016/8/11	014	一般情况	睡眠	63.png	1	014001	
		86568	1830		2016/8/11	014	一般情况	体温	64.png	1	014002	
		86569	1830		2016/8/11	014	一般情况	血压	65.png	1	014003
		
		@param hzid 患者ID
		@param  sfdate 患者随访日期
	 */
	public boolean checkHzxfxxBaseDB(Long hzid, Long sfdate);
	
	public void addHzsfxxBeaseDB(List<Hzsfxx> sfs, Long hzid, Long date);
	
	Long findByHzidDataItemCode(Long hzid, Long date, String itemcode);
	
	Hzsfxx findByHzidDateTemp3(Long hzid, Long date, String temp3);
	
	 int deleteByPrimaryKey(Long ID);

    int insert(Hzsfxx record);

    int insertSelective(Hzsfxx record);

    Hzsfxx selectByPrimaryKey(Long ID);

    int updateByPrimaryKeySelective(Hzsfxx record);

    int updateByPrimaryKey(Hzsfxx record);
    
    void addGrowthData(Long hzid, String date, JSONArray array) throws Exception;
}
