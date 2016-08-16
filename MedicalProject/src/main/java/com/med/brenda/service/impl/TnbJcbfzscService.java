package com.med.brenda.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.med.brenda.dao.HzsfxxMapper;
import com.med.brenda.dao.TnbJcbfzscMapper;
import com.med.brenda.model.TnbJcbfzsc;
import com.med.brenda.service.ITnbJcbfzscService;

@Service
@Repository
public class TnbJcbfzscService implements ITnbJcbfzscService {
	Logger logger = Logger.getLogger(TnbJcbfzscService.class);

	@Resource
	private TnbJcbfzscMapper tnbJcbfzscDao;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return tnbJcbfzscDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(TnbJcbfzsc record) {
		return tnbJcbfzscDao.insert(record);
	}

	@Override
	public int insertSelective(TnbJcbfzsc record) {
		return tnbJcbfzscDao.insertSelective(record);
	}

	@Override
	public TnbJcbfzsc selectByPrimaryKey(Long id) {
		return tnbJcbfzscDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(TnbJcbfzsc record) {
		return tnbJcbfzscDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TnbJcbfzsc record) {
		return tnbJcbfzscDao.updateByPrimaryKey(record);
	}

	/**
	 * inspectData 检查化验数据：
	 * {"ch2h":"0","hbac":"1","nt":"2","ntt":"3","ndb":"4","xt":"5","yds":"6",
	 * "ct":"7","rs":"8","xqpzc":"9","cssxpzj":"10","jgwx1":"11","jgwx2":"12","jgwx3":"13",
	 * "jgwx4":"14","jgwx5":"15","szs1":"16","szs4":"17","szs5":"18","szs3":"19","szs2":"20",
	 * "sgn1":"21","sgn2":"22","xz1":"23","xz2":"24","xz3":"25","xz4":"26","xz5":"27",
	 * "imageurl":"","datarq":"20160303"}
	 */
	@Override
	public Long addInspectData(Long Hzid, String date, Long fatherid, JSONObject inspectData) {
		//添加hzsfxx表，基础数据
		TnbJcbfzsc jcb = new TnbJcbfzsc();
		jcb.setFatherid(fatherid);
		jcb.setHzid(Hzid);
		jcb.setItemcode("018");
		jcb.setItemname("门诊检查/化验");
		//解析数据
		if(inspectData != null && !inspectData.isEmpty()){
			jcb.setCh2h(inspectData.getString("ch2h"));
			jcb.setHbac(inspectData.getString("hbac"));
			jcb.setNt(inspectData.getString("nt"));
			jcb.setNtt(inspectData.getString("ntt"));
			jcb.setNdb(inspectData.getString("ndb"));
			jcb.setXt(inspectData.getString("xt"));
			jcb.setYds(inspectData.getString("yds"));
			jcb.setCt(inspectData.getString("ct"));
			jcb.setRs(inspectData.getString("rs"));
			jcb.setXqpzc(inspectData.getString("xqpzc"));
			jcb.setCssxpzj(inspectData.getString("cssxpzj"));
			jcb.setJgwx1(inspectData.getString("jgwx1"));
			jcb.setJgwx2(inspectData.getString("jgwx2"));
			jcb.setJgwx3(inspectData.getString("jgwx3"));
			jcb.setJgwx4(inspectData.getString("jgwx4"));
			jcb.setJgwx5(inspectData.getString("jgwx5"));
			jcb.setSzs1(inspectData.getString("szs1"));
			jcb.setSzs4(inspectData.getString("szs4"));
			jcb.setSzs5(inspectData.getString("szs5"));
			jcb.setSzs3(inspectData.getString("szs3"));
			jcb.setSzs2(inspectData.getString("szs2"));
			jcb.setSgn1(inspectData.getString("sgn1"));
			jcb.setSgn2(inspectData.getString("sgn2"));
			jcb.setXz1(inspectData.getString("xz1"));
			/**
			 * inspectData 检查化验数据：
			 * {"ch2h":"0","hbac":"1","nt":"2","ntt":"3","ndb":"4","xt":"5","yds":"6",
			 * "ct":"7","rs":"8","xqpzc":"9","cssxpzj":"10","jgwx1":"11","jgwx2":"12","jgwx3":"13",
			 * "jgwx4":"14","jgwx5":"15","szs1":"16","szs4":"17","szs5":"18","szs3":"19","szs2":"20",
			 * "sgn1":"21","sgn2":"22","xz1":"23","xz2":"24","xz3":"25","xz4":"26","xz5":"27",
			 * "imageurl":"","datarq":"20160303"}
			 */
			jcb.setXz2(inspectData.getString("xz2"));
			jcb.setXz3(inspectData.getString("xz3"));
			jcb.setXz4(inspectData.getString("xz4"));
			jcb.setXz5(inspectData.getString("xz5"));
			jcb.setTemp1(inspectData.getString("imageurl"));
			jcb.setDatarq(inspectData.getString("datarq"));//添加日期
			jcb.setTemp4(inspectData.getString("datarq"));//修改日期
		}
		int rowid = tnbJcbfzscDao.insert(jcb);
		if(rowid > 0 ){
			return jcb.getId();
		}else{
			return null;
		}
	}
}
