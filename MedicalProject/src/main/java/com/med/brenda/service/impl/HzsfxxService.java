package com.med.brenda.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.med.brenda.dao.HzsfxxMapper;
import com.med.brenda.dao.HzsfxxsonMapper;
import com.med.brenda.model.Hzsfxx;
import com.med.brenda.model.Hzsfxxson;
import com.med.brenda.model.Hzxx;
import com.med.brenda.service.IHzsfxxService;
import com.med.brenda.util.CommonUtils;
@Service
@Repository
public class HzsfxxService implements IHzsfxxService {
	Logger logger = Logger.getLogger(HzsfxxService.class);
	
	@Resource
	private HzsfxxMapper hzsfxxDao;
	@Resource
	private HzsfxxsonMapper hzsfxxsonDao;
	
	@Override
	public boolean checkHzxfxxBaseDB(Long hzid, Long sfdate) {
		Map<String , Object> param = new HashMap<>();
		param.put("hzid", hzid);
		param.put("sfdate", sfdate);
		List<Hzsfxx> hzsfxx = hzsfxxDao.findHzxfxxByDate_Hzid(param);
		if(hzsfxx != null && hzsfxx.size() > 0){
			if(hzsfxx.size() < 8){
				return false;
			}else{
				return true;
			}
		}else{
			return false;
		}
	}
	
	
	
	@Override
	public void addHzsfxxBeaseDB(List<Hzsfxx> sfs, Long hzid ,Long date) {
		if(sfs == null) {
			sfs = new ArrayList<>();
		}
		//初始化每一个用户在每一天的基础数据
		Hzsfxx h01 = new Hzsfxx();
		h01.setHZID(hzid);
		h01.setSFDATE(date);//CommonUtils.getTimeInMillisBy00_00_00());
		h01.setITEMCODE("015");
		h01.setITEMTYPE("");
		h01.setITEMNAME("血糖");
		h01.setTEMP1("66.png");
		h01.setTEMP2("1");
		h01.setTEMP3("015");
		Hzsfxx h02 = new Hzsfxx();
		h02.setHZID(hzid);
		h02.setSFDATE(date);//(CommonUtils.getTimeInMillisBy00_00_00());
		h02.setITEMCODE("016");
		h02.setITEMTYPE("");
		h02.setITEMNAME("胰岛素");
		h02.setTEMP1("67.png");
		h02.setTEMP2("1");
		h02.setTEMP3("016");
		Hzsfxx h03 = new Hzsfxx();
		h03.setHZID(hzid);
		h03.setSFDATE(date);//(CommonUtils.getTimeInMillisBy00_00_00());
		h03.setITEMCODE("021");
		h03.setITEMTYPE("");
		h03.setITEMNAME("饮食");
		h03.setTEMP1("68.png");
		h03.setTEMP2("1");
		h03.setTEMP3("021");
		Hzsfxx h04 = new Hzsfxx();
		h04.setHZID(hzid);
		h04.setSFDATE(date);//(CommonUtils.getTimeInMillisBy00_00_00());
		h04.setITEMCODE("022");
		h04.setITEMTYPE("");
		h04.setITEMNAME("运动");
		h04.setTEMP1("69.png");
		h04.setTEMP2("1");
		h04.setTEMP3("022");
		Hzsfxx h05 = new Hzsfxx();
		h05.setHZID(hzid);
		h05.setSFDATE(date);//(CommonUtils.getTimeInMillisBy00_00_00());
		h05.setITEMCODE("023");
		h05.setITEMTYPE("");
		h05.setITEMNAME("症状");
		h05.setTEMP1("70.png");
		h05.setTEMP2("1");
		h05.setTEMP3("023");
		Hzsfxx h06 = new Hzsfxx();
		h06.setHZID(hzid);
		h06.setSFDATE(date);//(CommonUtils.getTimeInMillisBy00_00_00());
		h06.setITEMCODE("014");
		h06.setITEMTYPE("一般情况");
		h06.setITEMNAME("睡眠");
		h06.setTEMP1("63.png");
		h06.setTEMP2("1");
		h06.setTEMP3("014001");
		Hzsfxx h07 = new Hzsfxx();
		h07.setHZID(hzid);
		h07.setSFDATE(date);//(CommonUtils.getTimeInMillisBy00_00_00());
		h07.setITEMCODE("014");
		h07.setITEMTYPE("一般情况");
		h07.setITEMNAME("体温");
		h07.setTEMP1("64.png");
		h07.setTEMP2("1");
		h07.setTEMP3("014002");
		Hzsfxx h08 = new Hzsfxx();
		h08.setHZID(hzid);
		h08.setSFDATE(date);//(CommonUtils.getTimeInMillisBy00_00_00());
		h08.setITEMCODE("014");
		h08.setITEMTYPE("一般情况");
		h08.setITEMNAME("血压");
		h08.setTEMP1("65.png");
		h08.setTEMP2("1");
		h08.setTEMP3("014003");
		sfs.add(h01);
		sfs.add(h02);
		sfs.add(h03);
		sfs.add(h04);
		sfs.add(h05);
		sfs.add(h06);
		sfs.add(h07);
		sfs.add(h08);
		hzsfxxDao.batchInsert(sfs);
	}

	@Override
	public int deleteByPrimaryKey(Long ID) {
		return hzsfxxDao.deleteByPrimaryKey(ID);
	}

	@Override
	public int insert(Hzsfxx record) {
		return hzsfxxDao.insert(record);
	}

	@Override
	public int insertSelective(Hzsfxx record) {
		return hzsfxxDao.insertSelective(record);
	}

	@Override
	public Hzsfxx selectByPrimaryKey(Long ID) {
		return hzsfxxDao.selectByPrimaryKey(ID);
	}

	@Override
	public int updateByPrimaryKeySelective(Hzsfxx record) {
		return hzsfxxDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Hzsfxx record) {
		return hzsfxxDao.updateByPrimaryKey(record);
	}



	@Override
	public Long findByHzidDataItemCode(Long hzid, Long date, String itemcode) {
		Map<String , Object> param = new HashMap<>();
		param.put("hzid", hzid);
		param.put("date", date);
		param.put("itemcode", itemcode);
		Hzsfxx hzsfxx = hzsfxxDao.findByHzidDataItemCode(param);
		return hzsfxx!=null?hzsfxx.getID():null;
	}



	@Override
	public Hzsfxx findByHzidDateTemp3(Long hzid, Long date, String temp3) {
		Map<String,Object> param = new HashMap<>();
		param.put("hzid", hzid);
		param.put("date", date);
		param.put("itemcode", temp3);
		Hzsfxx hzsfxx = hzsfxxDao.findByHzidDateTemp3(param);
		return hzsfxx;
	}


	/**
	 * 添加生长数据
	 */
	@Override
	public void addGrowthData(Long hzid, String date, JSONArray array) throws Exception {
		List<Hzsfxx> list = new ArrayList<>();
		if(!array.isEmpty()){
			for(Iterator it = array.iterator(); it.hasNext();){
				JSONObject jsono = (JSONObject)it.next();
				Hzsfxx hzsfxx = new Hzsfxx();
				hzsfxx.setHZID(hzid);
				Long longTime = CommonUtils.getTimeInMillisByDate(date);
				hzsfxx.setSFDATE(longTime);
				hzsfxx.setITEMTYPE("生长数据");
				String itemcode = jsono.getString("itemcode");
				String itemcode_prifex = itemcode.substring(0,3);
				hzsfxx.setITEMCODE(itemcode_prifex);
				hzsfxx.setTEMP1(jsono.getString("img"));//图片
				hzsfxx.setTEMP3(itemcode);//itemcode全
				if("001001".equals(itemcode)){
					if(StringUtils.isBlank(jsono.getString("height"))){
						continue;
					}
					hzsfxx.setITEMVALUE(jsono.getString("height"));//身高
				}else if("001002".equals(itemcode)){
					if(StringUtils.isBlank(jsono.getString("weight"))){
						continue;
					}
					hzsfxx.setITEMVALUE(jsono.getString("weight"));//体重
				}else if("001003".equals(itemcode)){
					if(StringUtils.isBlank(jsono.getString("headsize"))){
						continue;
					}
					hzsfxx.setITEMVALUE(jsono.getString("headsize"));//头围
				}else if("001004".equals(itemcode)){
					if(StringUtils.isBlank(jsono.getString("bust"))){
						continue;
					}
					hzsfxx.setITEMVALUE(jsono.getString("bust"));//胸围
				}else if("001005".equals(itemcode)){
					if(StringUtils.isBlank(jsono.getString("armsize"))){
						continue;
					}
					hzsfxx.setITEMVALUE(jsono.getString("armsize"));//上臂围
				}else if("001006".equals(itemcode)){
					if(StringUtils.isBlank(jsono.getString("triceps"))){
						continue;
					}
					hzsfxx.setITEMVALUE(jsono.getString("triceps"));//肱三头肌皮褶厚度
				}
				hzsfxx.setTEMP4(date);//修改时间
				hzsfxx.setTEMP5(date);//添加时间
				
				list.add(hzsfxx);
			}
		}
		if(list != null && list.size() > 0 ){
			hzsfxxDao.batchInsert(list);
		}
	}
	

	//修改某项生长数据
	/**
	 * 
	 * @param dataid  记录ID
	 * @param itemvalue 修改后的值
	 * @return
	 */
	public boolean modifyGrowthData(Long dataid, String itemvalue){
		try{
			Hzsfxx hzsfxx = hzsfxxDao.selectByPrimaryKey(dataid);
			if(hzsfxx != null){
				hzsfxx.setITEMVALUE(itemvalue);
				int rowid = hzsfxxDao.updateByPrimaryKeySelective(hzsfxx);
				
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}


	/**
	 * 添加患者的用药记录
	 * {"drugname":"用药名称","drugtime":"12:23","drugdose":"223","drugchannel":"用药途径"}
	 */
	@Override
	public Long addUserDrug(Long hzid, Long date, JSONObject userDrug) {
		Hzsfxx hzsfxx = new Hzsfxx();
		hzsfxx.setHZID(hzid);
		hzsfxx.setSFDATE(date);
		hzsfxx.setITEMCODE("003");
		hzsfxx.setITEMNAME(CommonUtils.getBloodSugarByItemCode("003"));
		hzsfxx.setITEMNAME(userDrug.getString("drugname"));//用药名称
		hzsfxx.setYWNAME(userDrug.getString("drugname"));//用药名称
		hzsfxx.setYYTIME(userDrug.getString("drugtime"));//用药时间
		hzsfxx.setYWJL(userDrug.getString("drugdose"));//用药剂量
		hzsfxx.setYYTJ(userDrug.getString("drugchannel"));//用药途径
		hzsfxx.setTEMP1("3.png");//图片
		hzsfxx.setTEMP2("2");
		hzsfxx.setTEMP3("003");
		hzsfxx.setTEMP4(CommonUtils.transferLongToDate(date));//修改时间
		hzsfxx.setTEMP5(CommonUtils.transferLongToDate(date));//添加时间
		
		
		int rowid = hzsfxxDao.insert(hzsfxx);
		if(rowid > 0 ){
			return hzsfxx.getID();
		}else{
			return 0l;
		}
	}

	
	/**
	 * 修改患者的用户记录
	 * {"drugname":"用药名称","drugtime":"12:23","drugdose":"223","drugchannel":"用药途径"}
	 */
	@Override
	public boolean modifyUserDrug(Long dataid, JSONObject userDrug) {
		Hzsfxx hzsfxx = hzsfxxDao.selectByPrimaryKey(dataid);
		hzsfxx.setITEMNAME(userDrug.getString("drugname"));//用药名称
		hzsfxx.setYWNAME(userDrug.getString("drugname"));//用药名称
		hzsfxx.setYYTIME(userDrug.getString("drugtime"));//用药时间
		hzsfxx.setYWJL(userDrug.getString("drugdose"));//用药剂量
		hzsfxx.setYYTJ(userDrug.getString("drugchannel"));//用药途径
		hzsfxx.setTEMP1("3.png");//图片
		hzsfxx.setTEMP2("2");
		hzsfxx.setTEMP3("003");
		hzsfxx.setTEMP4(CommonUtils.getCurDate());//修改时间
		int rowid = hzsfxxDao.updateByPrimaryKeySelective(hzsfxx);
		if(rowid > 0 ){
			return true;
		}else{
			return false;
		}
	}


	/**
	 * 在患者随访表中插入检查化验的记录基础数据
	 */
	@Override
	public Long addInspectData(Hzxx hzxx, String date) {
		Hzsfxx hzsfxx = new Hzsfxx();
		hzsfxx.setHZID(hzxx.getID());
		hzsfxx.setHZNAME(hzxx.getHZNAME());
		try {
			hzsfxx.setSFDATE(CommonUtils.getTimeInMillisByDate(date));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		hzsfxx.setITEMCODE("018");
		hzsfxx.setITEMNAME(CommonUtils.getBloodSugarByItemCode("018"));
		hzsfxx.setTEMP1("71.png");
		hzsfxx.setTEMP2("1");
		hzsfxx.setTEMP3("018");
		hzsfxx.setTEMP4(date);
		hzsfxx.setTEMP5(date);
		int rowid = hzsfxxDao.insert(hzsfxx);
		if(rowid > 0 ){
			return hzsfxx.getID();
		}else{
			return null;
		}
	}


	/**
	 * 患者添加某天的费用记录，在hzsfxx表中
	 */
	@Override
	public Long addCost(Hzxx hzxx, String date, String cost, String desc, String[] images) {
		Hzsfxx hzsfxx = new Hzsfxx();
		hzsfxx.setHZID(hzxx.getID());
		hzsfxx.setHZNAME(hzxx.getHZNAME());
		hzsfxx.setSFDATE(CommonUtils.getTimeInMillisByDate(date));
		hzsfxx.setITEMCODE("012");
		hzsfxx.setITEMNAME(CommonUtils.getBloodSugarByItemCode("012"));
		hzsfxx.setITEMVALUE(cost);
		hzsfxx.setCONTENT(desc);
		hzsfxx.setFYGJ(cost);
		hzsfxx.setTEMP1("12.png");
		hzsfxx.setTEMP2("2");
		hzsfxx.setTEMP3("012");
		hzsfxx.setTEMP4(date);
		hzsfxx.setTEMP5(date);
		int rowid = hzsfxxDao.insert(hzsfxx);
		if(rowid > 0 ){
			Long _id = hzsfxx.getID();
			//先删除关联的图片
			hzsfxxsonDao.se
			//保存图片，到 hzsfxxson表中
			Hzsfxxson hzsfxxson = null;//保存费用关联的图片
			if(images != null && images.length > 0 ){
				for(String img : images){
					hzsfxxson = new Hzsfxxson();
					
				}
			}
		}
		return null;
	}
	
}
