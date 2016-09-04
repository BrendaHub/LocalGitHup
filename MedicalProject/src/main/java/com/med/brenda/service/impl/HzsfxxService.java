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
import com.med.brenda.dao.TnbTnbsonMapper;
import com.med.brenda.dao.TnbYinshiMapper;
import com.med.brenda.model.Hzsfxx;
import com.med.brenda.model.Hzsfxxson;
import com.med.brenda.model.Hzxx;
import com.med.brenda.model.TnbTnbson;
import com.med.brenda.model.TnbYinshi;
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
	@Resource
	private TnbTnbsonMapper tnbsonDao;
	@Resource
	private TnbYinshiMapper yinshiDao;
	
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
	 * 修改患者的用药记录
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
		try {
			hzsfxx.setSFDATE(CommonUtils.getTimeInMillisByDate(date));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
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
			//先删除关联的图片,通过fatherID 查询记录并删除
			List<Hzsfxxson> list = hzsfxxsonDao.findListByFatherId(hzsfxx.getID());
			if(list != null && list.size() > 0){
				for(Hzsfxxson hzsfxxson : list){
					hzsfxxsonDao.deleteByPrimaryKey(hzsfxxson.getID());
				}
			}
			//保存图片，到 hzsfxxson表中
			Hzsfxxson hzsfxxson = null;//保存费用关联的图片
			if(images != null && images.length > 0 ){
				for(String img : images){
					hzsfxxson = new Hzsfxxson();
					hzsfxxson.setFATHERID(hzsfxx.getID());
					hzsfxxson.setIMAGEURL(img);
					hzsfxxson.setTEMP4(date);//修改时间
					hzsfxxson.setTEMP5(date);//添加时间
				}
			}
			if(hzsfxxson != null){
				rowid = hzsfxxsonDao.insert(hzsfxxson);
				if(rowid > 0){
					return hzsfxx.getID();
				}else{
					return null;
				}
			}
		}
		return null;
	}
	
	/**
	 * 获取某个患者某天的所有随访数据，参数说明：
	 * @param hzid  患者ID
	 * @param date 需要查询的对应日期
	 * @param mon 需要查询对应的月份
	 */
	@Override
	public String getCurrentDateTNB(Long hzid, String date) throws Exception {
		JSONObject result = new JSONObject();
		/**
		 * 当前接口需要做的事情有：
		 * 1、处理 date 前10，后 10 日期的数据 
		 */
		JSONArray _Mon = new JSONArray();//?
		JSONObject _jsonMon = new JSONObject();
		try {
			_jsonMon.put("Mon", CommonUtils.parseLongDatetoJson(CommonUtils.getTimeInMillisByDate(date)).get("Mon"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		_Mon.add(_jsonMon);
		List<Long> list = CommonUtils.get_10Date(date);
		JSONArray _date = new JSONArray();//？？
		JSONArray _datalist = new JSONArray();
		if(list != null && list.size() > 0 ){//判断根据传入的时间取到的前后各10天的数据是否存在，如果存在，偏历
			for(Iterator it = list.iterator(); it.hasNext(); ){//遍历日期
				JSONObject date_data = new JSONObject();
				//标记这一天的小图标
				StringBuilder sb = new StringBuilder();
				Long _long = (Long)it.next();
				JSONObject _dJ = CommonUtils.parseLongDatetoJson(_long);
				logger.debug("这一天的日期为: " + _long + " 转为日期格式 为：" + CommonUtils.transferLongToDate(_long));
				//第一个日期 Long 转换成 date
				Map<String, Object> param1 = new HashMap<>();
				param1.put("hzid", hzid);
				param1.put("sfdate", _long);
				List<Hzsfxx> listsfxx = hzsfxxDao.findHzxfxxByDate_Hzid(param1);//根据用户ＩＤ ， 和日期查询出当前患者的随访信息
				for(Iterator it1 = listsfxx.iterator(); it1.hasNext(); ){//遍历集合
					JSONObject hzdata = new JSONObject();
					Hzsfxx hzsfxx = (Hzsfxx)it1.next();
					//添加一个判断逻辑，如是果当前日期显示_dataList数据，否则不显示指日期的数据
					logger.debug("===================================================================");
					logger.debug("hzsfxx.getSFDate = " + hzsfxx.getSFDATE());
					logger.debug("date = " + date);
					logger.debug("转换后的long类型的数据后的值为： " + CommonUtils.getTimeInMillisByDate(date));
					logger.debug("===================================================================");
					if (hzsfxx.getSFDATE().longValue() == CommonUtils.getTimeInMillisByDate(date).longValue()){//遍历的内容时当前日期的内容
						hzdata.put("Iconrul", hzsfxx.getTEMP1());
						hzdata.put("Title", hzsfxx.getITEMNAME());
						hzdata.put("Datetime", CommonUtils.transferLongToDate(_long));
						hzdata.put("Id", hzsfxx.getID());
						hzdata.put("Type", hzsfxx.getTEMP2());
						hzdata.put("Zdorcode", hzsfxx.getITEMCODE());
						StringBuilder contents = new StringBuilder();
						if("015".equals(hzsfxx.getITEMCODE())){//血糖
							Map<String, Object> p = new HashMap<>();
							p.put("hzid", hzid);
							p.put("date", CommonUtils.transferLongToDate(_long));
							p.put("itemcode", hzsfxx.getITEMCODE());
							List<TnbTnbson> tnb1  = tnbsonDao.findFeedListByHzid_Date(p);
							if(tnb1!=null&&tnb1.size()>0){
								sb.append(hzsfxx.getTEMP1());
								sb.append(",");
								int index = 0 ; 
								for(index = 0 ; index < tnb1.size(); index ++){
									TnbTnbson tnbs = (TnbTnbson)tnb1.get(index);
									contents.append(tnbs.getItemvalue());
									contents.append(";");
									contents.append(tnbs.getItemcode());
									contents.append(";");
									contents.append(tnbs.getFatherid());
									contents.append(";");
									contents.append(tnbs.getTemp1());
									if(index != tnb1.size() -1){
										contents.append("|");
									}
								}
								contents.append((hzsfxx.getITEMVALUE() == null||"null".equals(hzsfxx.getITEMVALUE()))?"":hzsfxx.getITEMVALUE());
								hzdata.put("Content", contents.toString());
								_datalist.add(hzdata);
								continue;
							}else{
								contents.append((hzsfxx.getITEMVALUE() == null||"null".equals(hzsfxx.getITEMVALUE()))?"":hzsfxx.getITEMVALUE());
								hzdata.put("Content", contents.toString());
								_datalist.add(hzdata);
								continue;
							}
						}
						if("016".equals(hzsfxx.getITEMCODE())){//胰岛素
							Map<String, Object> p = new HashMap<>();
							p.put("hzid", hzid);
							p.put("date", CommonUtils.transferLongToDate(_long));
							p.put("itemcode", hzsfxx.getITEMCODE());
							List<TnbTnbson> tnb1  = tnbsonDao.findFeedListByHzid_Date(p);
							if(tnb1!=null&&tnb1.size()>0){
								sb.append(hzsfxx.getTEMP1());
								sb.append(",");
								int index = 0 ; 
								for(index = 0 ; index < tnb1.size(); index ++){
									TnbTnbson tnbs = (TnbTnbson)tnb1.get(index);
									contents.append(tnbs.getItemcode());
									contents.append(";");
									contents.append(tnbs.getYds());
									contents.append(";");
									contents.append(tnbs.getYdsjl()+"U");
									contents.append(";");
									contents.append("二甲双胍 "+tnbs.getYdsejsg()+"片");
									contents.append(";");
									contents.append(tnbs.getFatherid());
									if(index != tnb1.size() -1){
										contents.append("|");
									}
								}
								contents.append((hzsfxx.getITEMVALUE() == null||"null".equals(hzsfxx.getITEMVALUE()))?"":hzsfxx.getITEMVALUE());
								hzdata.put("Content", contents.toString());
								_datalist.add(hzdata);
								continue;
							}else{
								contents.append((hzsfxx.getITEMVALUE() == null||"null".equals(hzsfxx.getITEMVALUE()))?"":hzsfxx.getITEMVALUE());
								hzdata.put("Content", contents.toString());
								_datalist.add(hzdata);
								continue;
							}
						}
						if("022".equals(hzsfxx.getITEMCODE())){//运动
							Map<String, Object> p = new HashMap<>();
							p.put("hzid", hzid);
							p.put("date", CommonUtils.transferLongToDate(_long));
							p.put("itemcode", hzsfxx.getITEMCODE());
							List<TnbTnbson> tnb1  = tnbsonDao.findFeedListByHzid_Date(p);
							if(tnb1!=null&&tnb1.size()>0){
								sb.append(hzsfxx.getTEMP1());
								sb.append(",");
								TnbTnbson tnbs = (TnbTnbson)tnb1.get(0);
								String ydlx = tnbs.getYdlx().trim();
								if("0".equals(ydlx)){
									contents.append("低强度运动  ");
								}else if("1".equals(ydlx)){
									contents.append("轻中强度运动  ");
								}else if("2".equals(ydlx)){
									contents.append("中强度运动  ");
								}else if("3".equals(ydlx)){
									contents.append("高重强度运动  ");
								}
								contents.append(tnbs.getYdcxsj());
								contents.append((hzsfxx.getITEMVALUE() == null||"null".equals(hzsfxx.getITEMVALUE()))?"":hzsfxx.getITEMVALUE());
								hzdata.put("Content", contents.toString());
								_datalist.add(hzdata);
								continue;
							}else{
								contents.append((hzsfxx.getITEMVALUE() == null||"null".equals(hzsfxx.getITEMVALUE()))?"":hzsfxx.getITEMVALUE());
								hzdata.put("Content", contents.toString());
								_datalist.add(hzdata);
								continue;
							}
						}
						if("023".equals(hzsfxx.getITEMCODE())){//症状
							Map<String, Object> p = new HashMap<>();
							p.put("hzid", hzid);
							p.put("date", CommonUtils.transferLongToDate(_long));
							p.put("itemcode", hzsfxx.getITEMCODE());
							List<TnbTnbson> tnb1  = tnbsonDao.findFeedListByHzid_Date(p);
							if(tnb1!=null&&tnb1.size()>0){
								sb.append(hzsfxx.getTEMP1());
								sb.append(",");
								TnbTnbson tnbs = (TnbTnbson)tnb1.get(0);
								String zzdndy = tnbs.getZzdndy();
								String zzyn = tnbs.getZzyn();
								String zzxz = tnbs.getZzxs();
								String zzfl = tnbs.getZzfl();
								String tmp = zzdndy+zzyn+zzxz+zzfl;
								if(tmp.indexOf("1") != -1){
									contents.append("糖尿病症状  ");
								}
								String zzqt = tnbs.getZzqt();
								if(zzqt.indexOf("1") != -1){
									contents.append("低血糖症状");
								}
								contents.append((hzsfxx.getITEMVALUE() == null||"null".equals(hzsfxx.getITEMVALUE()))?"":hzsfxx.getITEMVALUE());
								hzdata.put("Content", contents.toString());
								_datalist.add(hzdata);
								continue;
							}else{
								contents.append((hzsfxx.getITEMVALUE() == null||"null".equals(hzsfxx.getITEMVALUE()))?"":hzsfxx.getITEMVALUE());
								hzdata.put("Content", contents.toString());
								_datalist.add(hzdata);
								continue;
							}
						}
						if("021".equals(hzsfxx.getITEMCODE())){//饮食
							Map<String, Object> p = new HashMap<>();
							p.put("hzid", hzid);
							p.put("temp5", CommonUtils.transferLongToDate(_long));
							p.put("itemcode", hzsfxx.getITEMCODE());
							List<TnbYinshi> yinshis = yinshiDao.getYinshiListByHzid_date(p);
							if(yinshis!=null&&yinshis.size()>0){
								sb.append(hzsfxx.getTEMP1());
								sb.append(",");
								contents.append("目标:1435千卡 当前:511.29千卡");
								contents.append(hzsfxx.getITEMVALUE());
								hzdata.put("Content", contents.toString());
								_datalist.add(hzdata);
								continue;
							}else{
								contents.append((hzsfxx.getITEMVALUE() == null||"null".equals(hzsfxx.getITEMVALUE()))?"":hzsfxx.getITEMVALUE());
								hzdata.put("Content", contents.toString());
								_datalist.add(hzdata);
								continue;
							}
						}
						sb.append(hzsfxx.getTEMP1());
						sb.append(",");
						
						contents.append((hzsfxx.getITEMVALUE() == null||"null".equals(hzsfxx.getITEMVALUE()))?"":hzsfxx.getITEMVALUE());
						hzdata.put("Content", contents.toString());
						_datalist.add(hzdata);
					}else{//如果不是当前日期时，就只查询出当前用户的_date数据内容
						if("015".equals(hzsfxx.getITEMCODE())){//血糖
							Map<String, Object> p = new HashMap<>();
							p.put("hzid", hzid);
							p.put("date", CommonUtils.transferLongToDate(_long));
							p.put("itemcode", hzsfxx.getITEMCODE());
							List<TnbTnbson> tnb1  = tnbsonDao.findFeedListByHzid_Date(p);
							if(tnb1!=null&&tnb1.size()>0){
								sb.append(hzsfxx.getTEMP1());
								sb.append(",");
								continue;
							}else{
								continue;
							}
						}
						if("016".equals(hzsfxx.getITEMCODE())){//胰岛素
							Map<String, Object> p = new HashMap<>();
							p.put("hzid", hzid);
							p.put("date", CommonUtils.transferLongToDate(_long));
							p.put("itemcode", hzsfxx.getITEMCODE());
							List<TnbTnbson> tnb1  = tnbsonDao.findFeedListByHzid_Date(p);
							if(tnb1!=null&&tnb1.size()>0){
								sb.append(hzsfxx.getTEMP1());
								sb.append(",");
								int index = 0 ; 
								
								continue;
							}else{
								continue;
							}
						}
						if("022".equals(hzsfxx.getITEMCODE())){//运动
							Map<String, Object> p = new HashMap<>();
							p.put("hzid", hzid);
							p.put("date", CommonUtils.transferLongToDate(_long));
							p.put("itemcode", hzsfxx.getITEMCODE());
							List<TnbTnbson> tnb1  = tnbsonDao.findFeedListByHzid_Date(p);
							if(tnb1!=null&&tnb1.size()>0){
								sb.append(hzsfxx.getTEMP1());
								sb.append(",");
								
								continue;
							}else{
								continue;
							}
						}
						if("023".equals(hzsfxx.getITEMCODE())){//症状
							Map<String, Object> p = new HashMap<>();
							p.put("hzid", hzid);
							p.put("date", CommonUtils.transferLongToDate(_long));
							p.put("itemcode", hzsfxx.getITEMCODE());
							List<TnbTnbson> tnb1  = tnbsonDao.findFeedListByHzid_Date(p);
							if(tnb1!=null&&tnb1.size()>0){
								sb.append(hzsfxx.getTEMP1());
								sb.append(",");
								
								continue;
							}else{
								continue;
							}
						}
						if("021".equals(hzsfxx.getITEMCODE())){//饮食
							Map<String, Object> p = new HashMap<>();
							p.put("hzid", hzid);
							p.put("temp5", CommonUtils.transferLongToDate(_long));
							p.put("itemcode", hzsfxx.getITEMCODE());
							List<TnbYinshi> yinshis = yinshiDao.getYinshiListByHzid_date(p);
							if(yinshis!=null&&yinshis.size()>0){
								sb.append(hzsfxx.getTEMP1());
								sb.append(",");
							
								continue;
							}else{
								continue;
							}
						}
						sb.append(hzsfxx.getTEMP1());
						sb.append(",");
						
					}//结束非当前日期的数据遍历
					
				}//遍历不同日期的数据结束。
				date_data.put("Day", _dJ.get("Day"));
				date_data.put("Week", _dJ.get("Week"));
				date_data.put("Iconurl", sb.toString());
				date_data.put("Id", CommonUtils.transferLongToDate(_long));
				_date.add(date_data);
			}//遍历当前日期的前后各10天的数据结束。
		}
		//拼接后半数据， 
		result.put("_st", 1);
		result.put("_msg", "调用成功");
		JSONObject sb = new JSONObject();
		sb.put("_mon", _Mon);
		sb.put("_date", _date);
		sb.put("_datalist", _datalist);
		result.put("_data", sb);
		return result.toJSONString();
	}



	@Override
	public List<Hzsfxx> findByListDateRang(Long hzid, String itemcode, Long startDate, Long endDate) {
		Map<String, Object> param = new HashMap<>();
		param.put("hzid", hzid);
		param.put("itemcode", itemcode);
		param.put("startdate", startDate);
		param.put("enddate", endDate);
		return hzsfxxDao.findByListDateRang(param);
	}



	@Override
	public List<Hzsfxx> findListDateRangByTemp3(Long hzid, String temp3, Long startDate, Long endDate) {
		Map<String, Object> param = new HashMap<>();
		param.put("hzid", hzid);
		param.put("itemcode", temp3);
		param.put("startdate", startDate);
		param.put("enddate", endDate);
		return hzsfxxDao.findListDateRangByTemp3(param);
	}

	
}
