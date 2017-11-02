package com.srd.ljzd.controller.biz;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.srd.ljzd.entity.biz.BizMsg;
import com.srd.ljzd.service.biz.BizService;
import com.srd.ljzd.util.Constant;
import com.srd.ljzd.util.ErrorCode;
import com.srd.ljzd.util.ThirdPartChannelEnum;

@Controller
@RequestMapping("/biz")
public class BizController {

	@Autowired
	BizService bizService;
	
	@RequestMapping(value="/applyCompanyBizView.do")
	//请求工商信息子页面
	public String applyCompanyBizView(String companyName,boolean isDecode, HttpServletRequest request,Model model){
		
		if(companyName==null||"".equals(companyName.trim())){
			return "modules/company/detail/biz/biz";
		}
		if(isDecode){
			try {
				companyName = java.net.URLDecoder.decode(companyName,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		BizMsg company = bizService.getGongShangMsg(companyName,ThirdPartChannelEnum.ZHONG_SHU);
		
		if(company!=null&&company.getBrefCompany()!=null){
			int curPage=1;
			int pageSize = Constant.DETAULT_PAGE_SIZE;
			bizService.buildPage(company,curPage,pageSize,model);
			
			model.addAttribute("brefCompany", company.getBrefCompany());
		}
		
		
		return "modules/company/detail/biz/bizDetail";
	}
	
	//请求工商信息子页面
	//localhost:8080/ljzx/biz/applyCompanyBrefBizView.do?companyName=安徽山鹰纸业股份有限公司&deep=2&isDecode=true
	@RequestMapping(value="/applyCompanyBrefBizView.do")
	public String applyCompanyBrefBizView(String companyName,String deep,boolean isDecode, HttpServletRequest request,Model model){
		
		if(companyName==null||"".equals(companyName.trim())){
			return "modules/company/detail/biz/biz";
		}
		if(isDecode){
			try {
				companyName = java.net.URLDecoder.decode(companyName,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		BizMsg company = bizService.getGongShangMsg(companyName,ThirdPartChannelEnum.QI_CHA_CHA);
		
		if(company!=null&&company.getBrefCompany()!=null){
			model.addAttribute("brefCompany", company.getBrefCompany());
		}
		int curPage=1;
		int pageSize = Constant.DETAULT_PAGE_SIZE;
		bizService.buildPageBref(company,curPage,pageSize,model);
		model.addAttribute("deep", deep);
		model.addAttribute("companyName", companyName);
		return "modules/company/detail/brefCompanyBiz";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/guDongNextPage.do")
	public String guDongPage(String companyName,Integer curPage ,String channel,Integer pageSize ,HttpServletRequest request,Model model){
		try {
			companyName = URLDecoder.decode(companyName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		ThirdPartChannelEnum channelEnum = ThirdPartChannelEnum.ZHONG_SHU;
		if(channel!=null&&ThirdPartChannelEnum.QI_CHA_CHA.toString().equals(channel)){
			channelEnum = ThirdPartChannelEnum.QI_CHA_CHA;
		}
		BizMsg company = bizService.getCachedBizMsg(companyName,channelEnum);
		Map<String, Object> guDongPage = (Map<String, Object>)request.getSession().getAttribute("guDongPage");
		if(guDongPage==null){
			if(pageSize==null){
				pageSize = Constant.DETAULT_PAGE_SIZE;
			}
			guDongPage = bizService.initPage(curPage,pageSize, company.getShareholderArray());
			
		}else{
			guDongPage.put("curPage",curPage);
		}
		model.addAttribute("guDongArray", company.getShareholderArray());
		request.getSession().setAttribute("guDongPage", guDongPage);
		//model.addAttribute("company", company);
		return  "/modules/company/detail/biz/bizDetail/guDong";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/zhuYaoRenYuanNextPage.do")
	public String zhuYaoRenYuanPage(String companyName,Integer curPage ,Integer pageSize ,String channel,HttpServletRequest request,Model model){
		try {
			companyName = URLDecoder.decode(companyName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		ThirdPartChannelEnum channelEnum = ThirdPartChannelEnum.ZHONG_SHU;
		if(channel!=null&&ThirdPartChannelEnum.QI_CHA_CHA.toString().equals(channel)){
			channelEnum = ThirdPartChannelEnum.QI_CHA_CHA;
		}
		BizMsg company = bizService.getCachedBizMsg(companyName,channelEnum);
		Map<String, Object> page = (Map<String, Object>)request.getSession().getAttribute("zhuYaoRenYuanPage");
		if(page==null){
			if(pageSize==null){
				pageSize = Constant.DETAULT_PAGE_SIZE;
			}
			page = bizService.initPage(curPage,pageSize, company.getKeyPersonArray());
			
		}else{
			page.put("curPage",curPage);
		}
		model.addAttribute("zhuYaoRenYuanArray", company.getKeyPersonArray());
		request.getSession().setAttribute("zhuYaoRenYuanPage", page);
		//model.addAttribute("company", company);
		return  "/modules/company/detail/biz/bizDetail/zhuYaoRenYuan";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/fiLiationNextPage.do")
	public String fiLiationPage(String companyName,Integer curPage ,Integer pageSize ,String channel,HttpServletRequest request,Model model){
		try {
			companyName = URLDecoder.decode(companyName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		ThirdPartChannelEnum channelEnum = ThirdPartChannelEnum.ZHONG_SHU;
		if(channel!=null&&ThirdPartChannelEnum.QI_CHA_CHA.toString().equals(channel)){
			channelEnum = ThirdPartChannelEnum.QI_CHA_CHA;
		}
		BizMsg company = bizService.getCachedBizMsg(companyName,channelEnum);
		Map<String, Object> page = (Map<String, Object>)request.getSession().getAttribute("fiLiationPage");
		if(page==null){
			if(pageSize==null){
				pageSize = Constant.DETAULT_PAGE_SIZE;
			}
			page = bizService.initPage(curPage,pageSize, company.getFiLiationArray());
			
		}else{
			page.put("curPage",curPage);
		}
		model.addAttribute("fiLiationArray", company.getFiLiationArray());
		request.getSession().setAttribute("fiLiationPage", page);
		//model.addAttribute("company", company);
		return  "/modules/company/detail/biz/bizDetail/fiLiation";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/entInvNextPage.do")
	public String entInvPage(String companyName,Integer curPage ,Integer pageSize ,String channel,HttpServletRequest request,Model model){
		try {
			companyName = URLDecoder.decode(companyName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		ThirdPartChannelEnum channelEnum = ThirdPartChannelEnum.ZHONG_SHU;
		if(channel!=null&&ThirdPartChannelEnum.QI_CHA_CHA.toString().equals(channel)){
			channelEnum = ThirdPartChannelEnum.QI_CHA_CHA;
		}
		BizMsg company = bizService.getCachedBizMsg(companyName,channelEnum);
		Map<String, Object> page = (Map<String, Object>)request.getSession().getAttribute("entInvPage");
		if(page==null){
			if(pageSize==null){
				pageSize = Constant.DETAULT_PAGE_SIZE;
			}
			page = bizService.initPage(curPage,pageSize, company.getEntInvArray());
			
		}else{
			page.put("curPage",curPage);
		}
		model.addAttribute("entInvArray", company.getEntInvArray());
		request.getSession().setAttribute("entInvPage", page);
		//model.addAttribute("company", company);
		return  "/modules/company/detail/biz/bizDetail/entInv";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/shareSIMPAWNNextPage.do")
	public String shareSIMPAWNDPage(String companyName,Integer curPage ,Integer pageSize ,String channel,HttpServletRequest request,Model model){
		try {
			companyName = URLDecoder.decode(companyName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		ThirdPartChannelEnum channelEnum = ThirdPartChannelEnum.ZHONG_SHU;
		if(channel!=null&&ThirdPartChannelEnum.QI_CHA_CHA.toString().equals(channel)){
			channelEnum = ThirdPartChannelEnum.QI_CHA_CHA;
		}
		BizMsg company = bizService.getCachedBizMsg(companyName,channelEnum);
		Map<String, Object> page = (Map<String, Object>)request.getSession().getAttribute("shareSIMPAWNPage");
		if(page==null){
			if(pageSize==null){
				pageSize = Constant.DETAULT_PAGE_SIZE;
			}
			page = bizService.initPage(curPage,pageSize, company.getShareSIMPAWNArray());
			
		}else{
			page.put("curPage",curPage);
		}
		model.addAttribute("shareSIMPAWNArray", company.getShareSIMPAWNArray());
		request.getSession().setAttribute("shareSIMPAWNPage", page);
		//model.addAttribute("company", company);
		return  "/modules/company/detail/biz/bizDetail/shareSIMPAWN";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/dongChanDiYaNextPage.do")
	public String dongChanDiYaPage(String companyName,Integer curPage ,Integer pageSize ,String channel,HttpServletRequest request,Model model){
		try {
			companyName = URLDecoder.decode(companyName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		ThirdPartChannelEnum channelEnum = ThirdPartChannelEnum.ZHONG_SHU;
		if(channel!=null&&ThirdPartChannelEnum.QI_CHA_CHA.toString().equals(channel)){
			channelEnum = ThirdPartChannelEnum.QI_CHA_CHA;
		}
		BizMsg company = bizService.getCachedBizMsg(companyName,channelEnum);
		Map<String, Object> page = (Map<String, Object>)request.getSession().getAttribute("dongChanDiYaPage");
		if(page==null){
			if(pageSize==null){
				pageSize = Constant.DETAULT_PAGE_SIZE;
			}
			page = bizService.initPage(curPage,pageSize, company.getDongChanDiYaArray());
			
		}else{
			page.put("curPage",curPage);
		}
		model.addAttribute("dongChanDiYaArray", company.getDongChanDiYaArray());
		request.getSession().setAttribute("dongChanDiYaPage", page);
		//model.addAttribute("company", company);
		return  "/modules/company/detail/biz/bizDetail/dongChanDiYa";
	}
	@RequestMapping(value="/dongChanDiYaDetail.do")
	public String dongChanDiYaDetail(Integer index,String companyName,String channel,Model model){
		try {
			companyName = URLDecoder.decode(companyName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		ThirdPartChannelEnum channelEnum = ThirdPartChannelEnum.ZHONG_SHU;
		if(channel!=null&&ThirdPartChannelEnum.QI_CHA_CHA.toString().equals(channel)){
			channelEnum = ThirdPartChannelEnum.QI_CHA_CHA;
		}
		BizMsg company = bizService.getCachedBizMsg(companyName,channelEnum);
		
		if(index!=null&&company!=null&&company.getDongChanDiYaArray()!=null&&company.getDongChanDiYaArray().size()>index){
			model.addAttribute("detail", company.getDongChanDiYaArray().get(index));
		}
		return  "/modules/company/detail/biz/bizDetail/dongChanDiYaDetail";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/jingYingYiChangNextPage.do")
	public String jingYingYiChangPage(String companyName,Integer curPage ,Integer pageSize ,String channel,HttpServletRequest request,Model model){
		try {
			companyName = URLDecoder.decode(companyName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		ThirdPartChannelEnum channelEnum = ThirdPartChannelEnum.ZHONG_SHU;
		if(channel!=null&&ThirdPartChannelEnum.QI_CHA_CHA.toString().equals(channel)){
			channelEnum = ThirdPartChannelEnum.QI_CHA_CHA;
		}
		BizMsg company = bizService.getCachedBizMsg(companyName,channelEnum);
		Map<String, Object> page = (Map<String, Object>)request.getSession().getAttribute("jingYingYiChangPage");
		if(page==null){
			if(pageSize==null){
				pageSize = Constant.DETAULT_PAGE_SIZE;
			}
			page = bizService.initPage(curPage,pageSize, company.getJingYingYiChangArray());
			
		}else{
			page.put("curPage",curPage);
		}
		model.addAttribute("jingYingYiChangArray", company.getJingYingYiChangArray());
		request.getSession().setAttribute("jingYingYiChangPage", page);
		//model.addAttribute("company", company);
		return  "/modules/company/detail/biz/bizDetail/jingYingYiChang";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/administrativePenaltyNextPage.do")
	public String administrativePenaltyPage(String companyName,Integer curPage ,Integer pageSize ,String channel,HttpServletRequest request,Model model){
		try {
			companyName = URLDecoder.decode(companyName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		ThirdPartChannelEnum channelEnum = ThirdPartChannelEnum.ZHONG_SHU;
		if(channel!=null&&ThirdPartChannelEnum.QI_CHA_CHA.toString().equals(channel)){
			channelEnum = ThirdPartChannelEnum.QI_CHA_CHA;
		}
		BizMsg company = bizService.getCachedBizMsg(companyName,channelEnum);
		Map<String, Object> page = (Map<String, Object>)request.getSession().getAttribute("administrativePenaltyPage");
		if(page==null){
			if(pageSize==null){
				pageSize = Constant.DETAULT_PAGE_SIZE;
			}
			page = bizService.initPage(curPage,pageSize, company.getAdministrativePenaltyArray());
			
		}else{
			page.put("curPage",curPage);
		}
		model.addAttribute("administrativePenaltyArray", company.getAdministrativePenaltyArray());
		request.getSession().setAttribute("administrativePenaltyPage", page);
		//model.addAttribute("company", company);
		return  "/modules/company/detail/biz/bizDetail/administrativePenalty";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/brandNextPage.do")
	public String brandPage(String companyName,Integer curPage ,Integer pageSize ,String channel,HttpServletRequest request,Model model){
		try {
			companyName = URLDecoder.decode(companyName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		ThirdPartChannelEnum channelEnum = ThirdPartChannelEnum.ZHONG_SHU;
		if(channel!=null&&ThirdPartChannelEnum.QI_CHA_CHA.toString().equals(channel)){
			channelEnum = ThirdPartChannelEnum.QI_CHA_CHA;
		}
		BizMsg company = bizService.getCachedBizMsg(companyName,channelEnum);
		Map<String, Object> page = (Map<String, Object>)request.getSession().getAttribute("brandPage");
		if(page==null){
			if(pageSize==null){
				pageSize = Constant.DETAULT_PAGE_SIZE;
			}
			page = bizService.initPage(curPage,pageSize, company.getBrandArray());
			
		}else{
			page.put("curPage",curPage);
		}
		model.addAttribute("brandArray", company.getBrandArray());
		request.getSession().setAttribute("brandPage", page);
		//model.addAttribute("company", company);
		return  "/modules/company/detail/biz/bizDetail/brand";
	}

	/**
	 * 获取某一天的变更记录
	 */
	@RequestMapping(value="/getDateTable.do")
	public String getDateTable(String companyName, 
			@RequestParam("selectedCategory[]") ArrayList<String> selectedCategory,String channel,
			String dateKey
			,Model model){
		try {
			companyName = URLDecoder.decode(companyName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		ThirdPartChannelEnum channelEnum = ThirdPartChannelEnum.ZHONG_SHU;
		if(channel!=null&&ThirdPartChannelEnum.QI_CHA_CHA.toString().equals(channel)){
			channelEnum = ThirdPartChannelEnum.QI_CHA_CHA;
		}
		Map<String, Object> timeAxis = bizService.getCachedTimeAxis(companyName,channelEnum);
		//变更记录的变更事项列表
		Map<String,Integer> category =  bizService.getBianGengJiLuCachedCategory(companyName,channelEnum);
		
		Set<String> deSelectedCategory = null;
		//构造未被选中的类别 列表，如果为选中任何类别，则认为选择了全部类别
		if(selectedCategory!=null&&selectedCategory.size()>0&&!selectedCategory.contains("全部")){
			for(String cate : selectedCategory){
				category.remove(cate);
			}
			deSelectedCategory = category.keySet();
		}
		Map<String,List<HashMap<String,String>>> categoryMap = null;
		categoryMap = (Map<String,List<HashMap<String,String>>>)timeAxis.get(dateKey);
		if(deSelectedCategory!=null&&deSelectedCategory.size()>0){
			
			//此处应该遍历categoryMap，因为categoryMap中的数据量小，
			Iterator<Map.Entry<String,List<HashMap<String,String>>>> categoryMapIt = categoryMap.entrySet().iterator(); 
			String cateKey = null;
			while(categoryMapIt.hasNext()){
				cateKey = categoryMapIt.next().getKey();
				if(deSelectedCategory.contains(cateKey)){
					categoryMapIt.remove();
				}
			}
		}
		Iterator<Map.Entry<String,List<HashMap<String,String>>>> categoryMapIt2 = categoryMap.entrySet().iterator();
		Map.Entry<String,List<HashMap<String,String>>> cate = null;
		List<HashMap<String,String>> target = new ArrayList<HashMap<String,String>>();
		while(categoryMapIt2.hasNext()){
			cate = categoryMapIt2.next();
			if(cate!=null){
				target.addAll(cate.getValue());
			}
		}
		
		
		
		model.addAttribute("dateKey", dateKey);
		//model.addAttribute("category", categoryMap);
		model.addAttribute("targetList", target);
		return "/modules/company/detail/biz/bizDetail/bianGengJiLuDateTable";
	}


/**
 * 构造变更记录时间轴数据结构
 */
@RequestMapping(value="/buildTimeAxis.do")
public String buildTimeAxisDataStructure(String companyName, 
		@RequestParam("selectedCategory[]") ArrayList<String> selectedCategory,String channel,Model model){
	try {
		companyName = URLDecoder.decode(companyName, "UTF-8");
	} catch (UnsupportedEncodingException e) {
		
		e.printStackTrace();
	}
	ThirdPartChannelEnum channelEnum = ThirdPartChannelEnum.ZHONG_SHU;
	if(channel!=null&&ThirdPartChannelEnum.QI_CHA_CHA.toString().equals(channel)){
		channelEnum = ThirdPartChannelEnum.QI_CHA_CHA;
	}
	Map<String,Integer> sortedDate = bizService.getCachedSortedDate(companyName,channelEnum);
	Map<String, Object> timeAxis = bizService.getCachedTimeAxis(companyName,channelEnum);
	//变更记录的变更事项列表
	Map<String,Integer> category =  bizService.getBianGengJiLuCachedCategory(companyName,channelEnum);
	
	Set<String> deSelectedCategory = null;
	
	//如果选择了  其他
	if(selectedCategory!=null&&selectedCategory.contains("其他")){
		if(category!=null){
			for(String name : category.keySet()){
				if(name!=null&&!"".equals(name)&&!Constant.sortedName.contains(name)||"其他".equals(name)){
					selectedCategory.add(name);
				}
			}
		}
	}
	
	//构造未被选中的类别 列表，如果未选中任何类别，则认为选择了全部类别
	if(selectedCategory!=null&&selectedCategory.size()>0&&!selectedCategory.contains("全部")){
		for(String cate : selectedCategory){
			category.remove(cate);
		}
		deSelectedCategory = category.keySet();
	}
	
	if(deSelectedCategory!=null&&deSelectedCategory.size()>0){
		String key = null;
		Map<String,List<HashMap<String,String>>> categoryMap = null;
		
		Iterator<Map.Entry<String,Integer>> sortedDateIt = sortedDate.entrySet().iterator();
		Map.Entry<String,Integer> dateMap =null;
		while(sortedDateIt.hasNext()){
			
			dateMap = sortedDateIt.next();
			
			key =  dateMap.getKey();
			
			categoryMap = (Map<String,List<HashMap<String,String>>>)timeAxis.get(key);
			
			//此处应该遍历categoryMap，因为categoryMap中的数据量小，
			Iterator<Map.Entry<String,List<HashMap<String,String>>>> categoryMapIt = categoryMap.entrySet().iterator(); 
			String cateKey = null;
			while(categoryMapIt.hasNext()){
				cateKey = categoryMapIt.next().getKey();
				if(deSelectedCategory.contains(cateKey)){
					categoryMapIt.remove();
					dateMap.setValue(dateMap.getValue()-1);
				}
			}
			
			if(categoryMap.size()<=0){
				timeAxis.remove(key);
				sortedDateIt.remove();
			}
		}
	}
	
	model.addAttribute("bianGengJiLuTimeAxis", timeAxis);
	model.addAttribute("bianGengJiLuSortedDate", sortedDate);
	
	
	return "/modules/company/detail/biz/bizDetail/bianGengJiLuTimeAxis";
   }
/**
 * 获取被选择变更记录分页，
 */
@RequestMapping(value="/getPagingTable.do")
public String getDateTable(String companyName, 
		@RequestParam("selectedCategory[]") ArrayList<String> selectedCategory,
		Model model,Integer pageNo,Integer pageSize,String channel,HttpServletRequest request){
	try {
		companyName = URLDecoder.decode(companyName, "UTF-8");
	} catch (UnsupportedEncodingException e) {
		
		e.printStackTrace();
	}
	ThirdPartChannelEnum channelEnum = ThirdPartChannelEnum.ZHONG_SHU;
	if(channel!=null&&ThirdPartChannelEnum.QI_CHA_CHA.toString().equals(channel)){
		channelEnum = ThirdPartChannelEnum.QI_CHA_CHA;
	}
	BizMsg  company = bizService.getCachedBizMsg(companyName,channelEnum);
	
	ArrayList<HashMap<String,String>> bianGengJiLuArray = company.getBianGengJiLuArray();
	//变更记录的变更事项列表
	Map<String,Integer> category =  bizService.getBianGengJiLuCachedCategory(companyName,channelEnum);
	Set<String> deSelectedCategory = null;
	
	//如果选择了  其他
	if(selectedCategory!=null&&selectedCategory.contains("其他")){
		if(category!=null){
			for(String name : category.keySet()){
				if(name!=null&&!"".equals(name)&&!Constant.sortedName.contains(name)||"其他".equals(name)){
					selectedCategory.add(name);
				}
			}
		}
	}
	
	//构造未被选中的类别 列表，如果为选中任何类别，则认为选择了全部类别
	if(selectedCategory!=null&&selectedCategory.size()>0&&!selectedCategory.contains("全部")){
		for(String cate : selectedCategory){
			category.remove(cate);
		}
		deSelectedCategory = category.keySet();
	}
	if(deSelectedCategory!=null&&deSelectedCategory.size()>0){
		Iterator<HashMap<String, String>> it = bianGengJiLuArray.iterator();
		HashMap<String, String> record = null;
		String eventsCategoryName = null;
		while(it.hasNext()){
			record = it.next();
			eventsCategoryName = record.get("changedannouncement_events_category");
		
			if((eventsCategoryName==null||"null".equals(eventsCategoryName))&&!selectedCategory.contains("其他")
					|| deSelectedCategory.contains(eventsCategoryName)){
				it.remove();
			}
		}
	}
	
	if(pageSize==null){
		pageSize = Constant.DETAULT_PAGE_SIZE;
	}
	if(pageNo==null){
		pageNo=1;
	}
	Map<String, Object> bianGengJiLuPage = bizService.initPage(pageNo,pageSize, bianGengJiLuArray);

	request.getSession().setAttribute("bianGengJiLuPage", bianGengJiLuPage);
	model.addAttribute("bianGengJiLuPage", bianGengJiLuPage);
	model.addAttribute("bianGengJiLuArray", bianGengJiLuArray);
	
	return "/modules/company/detail/biz/bizDetail/bianGengJiLuPagingTable";
}

/**
 * 获取高管，法定代表人的变更次数
 * @param timeLimit  期限，timeLimit 为2，就是统计2年内的次数
 */
@RequestMapping(value="/getChangeRecordCount.do")
@ResponseBody
public Map<String,Object> getChangeRecordCount(String companyName,String channel,Integer timeLimit){
	
	try {
		companyName = URLDecoder.decode(companyName, "UTF-8");
	} catch (UnsupportedEncodingException e) {
		
		e.printStackTrace();
	}
	
	Map<String,Object> result = new HashMap<String, Object>();
	result.put("errorCode", ErrorCode.ER_UNKNOWN_EXCEPTION);
	
	ThirdPartChannelEnum channelEnum = ThirdPartChannelEnum.ZHONG_SHU;
	if(channel!=null&&ThirdPartChannelEnum.QI_CHA_CHA.toString().equals(channel)){
		channelEnum = ThirdPartChannelEnum.QI_CHA_CHA;
	}
	Map<String,Integer> countMap = bizService.getChageRecordCount(companyName,timeLimit, Arrays.asList("高管","法定代表人"),channelEnum);
	StringBuffer msg = null;
	if(countMap!=null){
		if(countMap.containsKey("法定代表人")){
			result.put("faRenCount", countMap.get("法定代表人"));
			if(countMap.get("法定代表人")>=2){
				msg = new StringBuffer("法定代表人");
			}
		}
		
		if(countMap.containsKey("高管")){
			result.put("gaoGuanCount", countMap.get("高管"));
			if(countMap.get("高管")>=2){
				if(msg==null){
					msg = new StringBuffer("高管");
				}else{
					msg.append("、高管");
				}
			}
		}
		if(msg!=null){
			msg.append("频繁变更，请关注。");
			result.put("msg",msg.toString());
		}
	}
	result.put("errorCode", ErrorCode.SUCCESS);
	return result;
}


}
