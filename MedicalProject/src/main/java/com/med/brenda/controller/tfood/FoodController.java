package com.med.brenda.controller.tfood;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.med.brenda.controller.common.BaseController;
import com.med.brenda.controller.common.Query;
import com.med.brenda.model.TFood;
import com.med.brenda.model.TYinshi;
import com.med.brenda.model.TYinshiCatecory;
import com.med.brenda.service.ITFoodService;
import com.med.brenda.service.ITYinshiService;
import com.med.brenda.service.impl.TYinshiCatecoryService;
import com.med.brenda.util.GlobalVariables;

/**
 * 患者信息业务业
 * <p>MedicalApp</p>
 * <p>Title: FoodController.java</p>
 * <p>Description:$</p>
 * <p>Package:com.med.brenda.controller.tfood</p>
 * @author chenhj(brenda)
 * @date 2016年8月19日 下午1:55:06
 * @tag 
 * @encode
 */
@Controller 
@RequestMapping(value="/tfood")
public class FoodController extends BaseController {
	private Logger logger = Logger.getLogger(FoodController.class);
	
	@Autowired
	private ITFoodService foodService;
	@Autowired
	private TYinshiCatecoryService yinshiCategoryService;
	@Autowired
	private ITYinshiService yinshiService;
	
	/**
	 * 去食务指标页面
	 */
	@RequestMapping(value="/tofoodlsit")
	public ModelAndView toSysFx(HttpServletRequest request , String pageNo, String keyworld){
		System.out.println(">>>>>>>>>>>>>>>"+pageNo);
		System.out.println(">>>>>>>>>>keyworld>>>>>"+keyworld);
		//创建出一个查询对象
		Query query = new Query();
		if(StringUtils.isBlank(keyworld)){
			;
		}else{
			query.setKeywords(keyworld);
		}
		int count = foodService.findListCount(query);
		//初始化翻页参数
		initPageFlag(request, query, GlobalVariables.DEFAULT_PAGE_SIZE);
		int mode = count % query.getPageSize();
        int pageCount = count / query.getPageSize();
        if (mode > 0) {
            pageCount = pageCount + 1;
        }
		List<TFood> list = foodService.findList(query);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("pageCount", pageCount);
        resultMap.put("pageSize", query.getPageSize());
        resultMap.put("pageIndex", query.getPageNo());
        resultMap.put("pageTotal", count);
        resultMap.put("pageNo", query.getPageNo());
        resultMap.put("result", list);
        resultMap.put("keyworld", query.getKeywords());
		return new ModelAndView("food/list", resultMap);
	}
	
	//go添加新的食材
	@RequestMapping(value="/toAddFood")
	public ModelAndView toAddFood(HttpServletRequest request){
		return new ModelAndView("food/newfood");
	}
	
	//go饮食列表  
	@RequestMapping(value="/toyinshilist")
	public ModelAndView toyinshilist(HttpServletRequest request, String pageNo, String keyworld){
		System.out.println(">>>>>>>>>>>>>>>"+pageNo);
		System.out.println(">>>>>>>>>>keyworld>>>>>"+keyworld);
		//创建出一个查询对象
		Query query = new Query();
		if(StringUtils.isBlank(keyworld)){
			;
		}else{
			query.setKeywords(keyworld);
		}
		int count = yinshiService.findListCount(query);
		//初始化翻页参数
		initPageFlag(request, query, GlobalVariables.DEFAULT_PAGE_SIZE);
		int mode = count % query.getPageSize();
        int pageCount = count / query.getPageSize();
        if (mode > 0) {
            pageCount = pageCount + 1;
        }
		List<TYinshi> list = yinshiService.findList(query);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("pageCount", pageCount);
        resultMap.put("pageSize", query.getPageSize());
        resultMap.put("pageIndex", query.getPageNo());
        resultMap.put("pageTotal", count);
        resultMap.put("pageNo", query.getPageNo());
        resultMap.put("result", list);
        resultMap.put("keyworld", query.getKeywords());
		return new ModelAndView("food/food_list", resultMap);
	}
	//删除饮食
	@RequestMapping(value="/delFoodItem")
	@ResponseBody
	public String delFoodItem(HttpServletRequest request){
		String _id = StringUtils.isBlank(request.getParameter("id"))?"":request.getParameter("id");
		logger.info("id = "+ _id);
		Map<String, Object> resultMap =  new HashMap<>();
		int rowid = yinshiCategoryService.deleteByPrimaryKey(Long.parseLong(_id));
		if(rowid > 0){
			return "0";
		}else{
//				resultMap.put("message", "删除失败！");
//				return new ModelAndView("/tfood/toAddFoodCategory", resultMap);
			return "1";
		}
		
	}
	
	
	//go添加新的yinshi
	@RequestMapping(value="/toAddFoodItem")
	public ModelAndView toAddFoodItem(HttpServletRequest request){
		return new ModelAndView("food/newfooditem");
	}
	//添加新的yinshi
	@RequestMapping(value="/AddFoodItem")
	public ModelAndView AddFoodItem(HttpServletRequest request , TYinshi yinshi){
		logger.info("yinshi == "+ JSON.toJSONString(yinshi));
		Map<String, Object> resultMap =  new HashMap<>();
		int rowid = yinshiService.insert(yinshi);
		if(rowid > 0){
			return new ModelAndView("redirect:/tfood/toyinshilist?ff="+Math.random());
		}else{
			resultMap.put("message", "新增失败！");
			return new ModelAndView("/tfood/toAddFoodItem", resultMap);
		}
	}
	
	
	//go饮食分类列表
	@RequestMapping(value="/tofoodcategory")
	public ModelAndView tofoodcategory(HttpServletRequest request, String pageNo, String keyworld){
		System.out.println(">>>>>>>>>>>>>>>"+pageNo);
		System.out.println(">>>>>>>>>>keyworld>>>>>"+keyworld);
		//创建出一个查询对象
		Query query = new Query();
		if(StringUtils.isBlank(keyworld)){
			;
		}else{
			query.setKeywords(keyworld);
		}
		int count = yinshiCategoryService.findListCount(query);
		//初始化翻页参数
		initPageFlag(request, query, GlobalVariables.DEFAULT_PAGE_SIZE);
		int mode = count % query.getPageSize();
        int pageCount = count / query.getPageSize();
        if (mode > 0) {
            pageCount = pageCount + 1;
        }
		List<TYinshiCatecory> list = yinshiCategoryService.findList(query);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("pageCount", pageCount);
        resultMap.put("pageSize", query.getPageSize());
        resultMap.put("pageIndex", query.getPageNo());
        resultMap.put("pageTotal", count);
        resultMap.put("pageNo", query.getPageNo());
        resultMap.put("result", list);
        resultMap.put("keyworld", query.getKeywords());
		return new ModelAndView("food/food_category_list", resultMap);
	}
	
	//go添加新的食材
	@RequestMapping(value="/toAddFoodCategory")
	public ModelAndView toAddFoodCategory(HttpServletRequest request){
		return new ModelAndView("food/newfoodCategory");
	}
	
	//添加新的食材
	@RequestMapping(value="/AddFoodCategory")
	public ModelAndView AddFoodCategory(HttpServletRequest request, TYinshiCatecory food){
		food.setCreatetime(System.currentTimeMillis());
		food.setModifytime(System.currentTimeMillis());
		logger.info("food = "+ JSON.toJSONString(food));
		Map<String, Object> resultMap =  new HashMap<>();
		
		int rowid = yinshiCategoryService.insert(food);
		if(rowid > 0){
			return new ModelAndView("redirect:/tfood/tofoodcategory?ff="+Math.random());
		}else{
			resultMap.put("message", "新增失败！");
			return new ModelAndView("/tfood/toAddFoodCategory", resultMap);
		}
		
	}
	
	//删除饮食分类
	@RequestMapping(value="/delFoodCategory")
	@ResponseBody
	public String delFoodCategory(HttpServletRequest request){
		String _id = StringUtils.isBlank(request.getParameter("id"))?"":request.getParameter("id");
		logger.info("id = "+ _id);
		Map<String, Object> resultMap =  new HashMap<>();
		int rowid = yinshiCategoryService.deleteByPrimaryKey(Long.parseLong(_id));
		if(rowid > 0){
			return "0";
		}else{
//			resultMap.put("message", "删除失败！");
//			return new ModelAndView("/tfood/toAddFoodCategory", resultMap);
			return "1";
		}
		
	}
	
	//添加新的食材
	@RequestMapping(value="/AddFood")
	public ModelAndView AddFood(HttpServletRequest request, TFood food){
		logger.info("food = "+ JSON.toJSONString(food));
		Map<String, Object> resultMap =  new HashMap<>();
		int rowid = foodService.insert(food);
		if(rowid > 0){
			return new ModelAndView("redirect:/tfood/tofoodlsit?ff="+Math.random());
		}else{
			resultMap.put("message", "新增患者失败！");
			return new ModelAndView("/tfood/toAddFood", resultMap);
		}
		
	}
}
