package com.med.brenda.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.med.brenda.dao.HzsfxxMapper;
import com.med.brenda.model.Hzsfxx;
import com.med.brenda.service.IHzsfxxService;
import com.med.brenda.util.CommonUtils;
@Service
@Repository
public class HzsfxxService implements IHzsfxxService {
	Logger logger = Logger.getLogger(HzsfxxService.class);
	
	@Resource
	private HzsfxxMapper hzsfxxDao;
	
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
	public void addHzsfxxBeaseDB(List<Hzsfxx> sfs, Long hzid) {
		if(sfs == null) {
			sfs = new ArrayList<>();
		}
		//初始化每一个用户在每一天的基础数据
		Hzsfxx h01 = new Hzsfxx();
		h01.setHZID(hzid);
		h01.setSFDATE(CommonUtils.getTimeInMillisBy00_00_00());
		h01.setITEMCODE("015");
		h01.setITEMTYPE("");
		h01.setITEMNAME("血糖");
		h01.setTEMP1("66.png");
		h01.setTEMP2("1");
		h01.setTEMP3("015");
		Hzsfxx h02 = new Hzsfxx();
		h02.setHZID(hzid);
		h02.setSFDATE(CommonUtils.getTimeInMillisBy00_00_00());
		h02.setITEMCODE("016");
		h02.setITEMTYPE("");
		h02.setITEMNAME("胰岛素");
		h02.setTEMP1("67.png");
		h02.setTEMP2("1");
		h02.setTEMP3("016");
		Hzsfxx h03 = new Hzsfxx();
		h03.setHZID(hzid);
		h03.setSFDATE(CommonUtils.getTimeInMillisBy00_00_00());
		h03.setITEMCODE("021");
		h03.setITEMTYPE("");
		h03.setITEMNAME("饮食");
		h03.setTEMP1("68.png");
		h03.setTEMP2("1");
		h03.setTEMP3("021");
		Hzsfxx h04 = new Hzsfxx();
		h04.setHZID(hzid);
		h04.setSFDATE(CommonUtils.getTimeInMillisBy00_00_00());
		h04.setITEMCODE("022");
		h04.setITEMTYPE("");
		h04.setITEMNAME("运动");
		h04.setTEMP1("69.png");
		h04.setTEMP2("1");
		h04.setTEMP3("022");
		Hzsfxx h05 = new Hzsfxx();
		h05.setHZID(hzid);
		h05.setSFDATE(CommonUtils.getTimeInMillisBy00_00_00());
		h05.setITEMCODE("023");
		h05.setITEMTYPE("");
		h05.setITEMNAME("症状");
		h05.setTEMP1("70.png");
		h05.setTEMP2("1");
		h05.setTEMP3("023");
		Hzsfxx h06 = new Hzsfxx();
		h06.setHZID(hzid);
		h06.setSFDATE(CommonUtils.getTimeInMillisBy00_00_00());
		h06.setITEMCODE("014");
		h06.setITEMTYPE("一般情况");
		h06.setITEMNAME("睡眠");
		h06.setTEMP1("63.png");
		h06.setTEMP2("1");
		h06.setTEMP3("014001");
		Hzsfxx h07 = new Hzsfxx();
		h07.setHZID(hzid);
		h07.setSFDATE(CommonUtils.getTimeInMillisBy00_00_00());
		h07.setITEMCODE("014");
		h07.setITEMTYPE("一般情况");
		h07.setITEMNAME("体温");
		h07.setTEMP1("64.png");
		h07.setTEMP2("1");
		h07.setTEMP3("014002");
		Hzsfxx h08 = new Hzsfxx();
		h08.setHZID(hzid);
		h08.setSFDATE(CommonUtils.getTimeInMillisBy00_00_00());
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
	

}
