package com.med.brenda.controller.hzxx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.med.brenda.controller.common.BaseController;
import com.med.brenda.controller.common.Query;
import com.med.brenda.model.Hzxx;
import com.med.brenda.service.IHzxxService;
import com.med.brenda.util.GlobalVariables;

/**
 * 患者信息业务业
 * <p>MedicalApp</p>
 * <p>Title: HzxxController.java</p>
 * <p>Description:$</p>
 * <p>Package:com.med.brenda.controller.hzxx</p>
 * @author bgly
 * @date 2016年8月19日 下午1:55:06
 * @tag 
 * @encode
 */
@Controller 
@RequestMapping(value="/HZXX")
public class HzxxController extends BaseController {
	
	private Logger logger = Logger.getLogger(HzxxController.class);
	
	@Autowired
	private IHzxxService hzxxService;

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView hzlist(HttpServletRequest request){
		//创建出一个查询对象
		Query query = new Query();
		int count = hzxxService.findListCount(query);
		//初始化翻页参数
		initPageFlag(request, query, GlobalVariables.DEFAULT_PAGE_SIZE);
		int mode = count % query.getPageSize();
        int pageCount = count / query.getPageSize();
        if (mode > 0) {
            pageCount = pageCount + 1;
        }
		List<Hzxx> list = hzxxService.findList(query);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("pageCount", pageCount);
        resultMap.put("pageSize", query.getPageSize());
        resultMap.put("pageIndex", query.getPageIndex());
        resultMap.put("pageTotal", count);
        resultMap.put("result", list);
		return new ModelAndView("Node/index",resultMap);
	}
}
