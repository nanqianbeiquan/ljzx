package com.srd.ljzd.controller.monitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.srd.ljzd.entity.client.ClientAccount;
import com.srd.ljzd.entity.company.CompanyInfo;
import com.srd.ljzd.entity.monitor.MonitorCompany;
import com.srd.ljzd.entity.monitor.MonitorRelationCompany;
import com.srd.ljzd.entity.monitor.MonitorRelationPerson;
import com.srd.ljzd.entity.relationCompanyAndPerson.RelationCompanyAndPerson;
import com.srd.ljzd.exception.SessionOverdueException;
import com.srd.ljzd.service.company.CompanyInfoService;
import com.srd.ljzd.service.monitor.MonitorCompanyService;
import com.srd.ljzd.service.monitor.MonitorRelationCompanyService;
import com.srd.ljzd.service.monitor.MonitorRelationPersonService;
import com.srd.ljzd.service.relationCompanyAndPerson.AddRelationCompanyAndPersonService;
import com.srd.ljzd.util.ErrorCode;
import com.srd.ljzd.util.Page;
import com.srd.ljzd.util.PageUtil;
import com.srd.ljzd.util.StringTool;


/**   
 * 版权所有：2017
 * 项目名称：ljzx   
 *
 * 类描述：添加关联企业，关联人Controller;
 * 类名称：com.srd.ljzd.controller.monitor.AddRelationCompanyAndPersonController     
 * 创建人：裴子辉
 * 创建时间：2017年1月13日 下午5:48:14   
 * 修改人：
 * 修改时间：2017年1月13日 下午5:48:14   
 * 修改备注：   
 * @version   V1.3    
 */
@Controller
@RequestMapping("/addRelationCtl") 
public class AddRelationCompanyAndPersonController {
	@Autowired
	AddRelationCompanyAndPersonService addRelationCompanyAndPersonService;
	
	@Autowired
	CompanyInfoService companyInfoService;
	
	@Autowired
	MonitorCompanyService monitorCompanyService;
	@Autowired
	MonitorRelationPersonService monitorRelationPersonService;
	
	@Autowired
	MonitorRelationCompanyService monitorRelationCompanyService;
	
	protected static Logger logger = LogManager.getLogger(AddRelationCompanyAndPersonController.class.getName());
	
	@RequestMapping("/toRelationPage")
	public String toRelationPage(HttpServletRequest request, HttpServletResponse response, Model model){
	
		Map paraMap = new HashMap();
		String companyName = request.getParameter("companyName");
		model.addAttribute("companyName", companyName);
		return "/modules/company/detail/news/news";
	}
	
	
	
	/**
	* @Title: getRelationCompany
	* @Description: TODO(获取关联公司)
	* @param  @param request
	* @param  @param response
	* @param  @return  
	* @return ModelAndView    返回类型
	* @author zihui.pei  
	* @throws
	* @date 2017年1月16日 上午10:01:29
	*/
	@RequestMapping("/getRelationCompany")
	public ModelAndView getRelationCompany(String monitorId,HttpServletRequest request, HttpServletResponse response){
	
		ModelAndView model = new ModelAndView();
		String companyName = request.getParameter("companyName");
		int currentPageNo = 1;
		if(StringUtils.isNotEmpty(request.getParameter("currentPageNo"))){
			currentPageNo = Integer.valueOf(request.getParameter("currentPageNo"));
		}
		int pageSize = 10;
		if(StringUtils.isNotEmpty(request.getParameter("pageSize"))){
			pageSize = Integer.valueOf(request.getParameter("pageSize"));
		}
		Map<String, List<RelationCompanyAndPerson>> resultMap =addRelationCompanyAndPersonService.queryAllRelationsList(companyName);
		
		List<RelationCompanyAndPerson> investmentCompanyList = new ArrayList<RelationCompanyAndPerson>();// 股东  自然人股东
		List<RelationCompanyAndPerson>  stockholderCorporationLegalList = new ArrayList<RelationCompanyAndPerson>();// 股东  法人股东
		
		investmentCompanyList= resultMap.get("investmentCompanyList");
		stockholderCorporationLegalList= resultMap.get("stockholderCorporationLegalList");

		List<RelationCompanyAndPerson> returnLits = new ArrayList<RelationCompanyAndPerson>();
		List<RelationCompanyAndPerson> companyInfoList = resultMap.get("stockholderCorporationLegalList");
		List<String>  companyNameList = null;
		if(StringUtils.isNotEmpty(monitorId)){
			//查询已经关联公司名称
			companyNameList = monitorRelationCompanyService.queryRelationCompanyNameByMonitorId(monitorId);
		}
		RelationCompanyAndPerson record = null;
		if ((currentPageNo * pageSize >= companyInfoList.size())) {
			for (int i = (currentPageNo - 1) * pageSize; i < companyInfoList
					.size(); i++) {
				record = companyInfoList.get(i);
				if(companyNameList!=null&&companyNameList.contains(record.getCompanyNameInProperties())){
					record.setExistFlag(true);
				}
				returnLits.add(record);
			}
		} else {
			for (int i = 0; i < 10; i++) {
				record = companyInfoList.get((currentPageNo - 1)
						* pageSize + i);
				if(companyNameList!=null&&companyNameList.contains(record.getCompanyNameInProperties())){
					record.setExistFlag(true);
				}
				returnLits.add(record);
			}
		}

		Integer i = new Integer(companyInfoList.size());
		long totalCount = i.longValue();

		Page<RelationCompanyAndPerson> retValue = (new PageUtil()).createPage(pageSize,
				totalCount, currentPageNo);
		retValue.setResults(returnLits);

		model.addObject("stockHolderPage", retValue);
		
		
		
		model.addObject("stockholderCorporationLegalList",resultMap.get("stockholderCorporationLegalList"));
		model.addObject("investmentCompanyList",resultMap.get("investmentCompanyList"));
		
		model.setViewName("/modules/monitor/monitorEvents/companyModePage");
		model.addObject("companyName", companyName);
		model.addObject("resultMap", resultMap);
		return model;
	}
	

	/**
	* @Title: getRelationPerson
	* @Description: TODO(获取关联人物)
	* @param  @param request
	* @param  @param response
	* @param  @return  
	* @return ModelAndView    返回类型
	* @author zihui.pei  
	* @throws
	* @date 2017年1月13日 下午5:53:22
	*/
	@RequestMapping("/getRelationPerson")
	public String getRelationPerson(HttpServletRequest request, HttpServletResponse response, Model model){
		String monitorId = request.getParameter("monitorId");
		String companyName = request.getParameter("companyName");
		
		Map<String, List<RelationCompanyAndPerson>> resultMap = addRelationCompanyAndPersonService.queryAllRelationsList(companyName);
		
		model.addAttribute("stockholderList", resultMap.get("stockholderList"));
		model.addAttribute("stockholderCorporationLegalList", resultMap.get("stockholderCorporationLegalList"));
		model.addAttribute("corporationLegalPersonList", resultMap.get("corporationLegalPersonList"));
		model.addAttribute("managerList", resultMap.get("managerList"));
		model.addAttribute("investmentCompanyList", resultMap.get("investmentCompanyList"));
		
		//取得已经加入监控的关系自然人
		List<MonitorRelationPerson> mrpList = monitorRelationPersonService.getMonitorRelationPersonList(monitorId);
		
		model.addAttribute("mrpList", mrpList);
		model.addAttribute("personNameStrs", getPersonNameStrs(mrpList));
		model.addAttribute("companyName", companyName);
		model.addAttribute("resultMap", resultMap);
		
		return "/modules/monitor/monitorEvents/personModePage";
	}
	
	@RequestMapping("/getInvestmentCompanyList")
	public ModelAndView getInvestmentCompanyList(String monitorId,HttpServletRequest request, HttpServletResponse response){
	
		ModelAndView model = new ModelAndView();
		String companyName = request.getParameter("companyName");
		int currentPageNo = 1;
		if(StringUtils.isNotEmpty(request.getParameter("currentPageNo"))){
			currentPageNo = Integer.valueOf(request.getParameter("currentPageNo"));
		}
		int pageSize = 10;
		if(StringUtils.isNotEmpty(request.getParameter("pageSize"))){
			pageSize = Integer.valueOf(request.getParameter("pageSize"));
		}
		Map<String, List<RelationCompanyAndPerson>> resultMap =addRelationCompanyAndPersonService.queryAllRelationsList(companyName);
		
		model.addObject("stockholderList",resultMap.get("stockholderList"));
		model.addObject("stockholderCorporationLegalList",resultMap.get("stockholderCorporationLegalList"));
		model.addObject("corporationLegalPersonList",resultMap.get("corporationLegalPersonList"));
		model.addObject("managerList",resultMap.get("managerList"));
		model.addObject("investmentCompanyList",resultMap.get("investmentCompanyList"));

		List<RelationCompanyAndPerson> returnLits = new ArrayList<RelationCompanyAndPerson>();
		List<RelationCompanyAndPerson> companyInfoList = resultMap.get("investmentCompanyList");
	    
		List<String>  companyNameList = null;
		if(StringUtils.isNotEmpty(monitorId)){
			//查询已经关联公司名称
			companyNameList = monitorRelationCompanyService.queryRelationCompanyNameByMonitorId(monitorId);
		}
		RelationCompanyAndPerson record = null;
		if ((currentPageNo * pageSize >= companyInfoList.size())) {
			for (int i = (currentPageNo - 1) * pageSize; i < companyInfoList
					.size(); i++) {
				record = companyInfoList.get(i);
				if(companyNameList!=null&&companyNameList.contains(record.getCompanyNameInProperties())){
					record.setExistFlag(true);
				}
				returnLits.add(record);
			}
		} else {
			for (int i = 0; i < 10; i++) {
				
				record = companyInfoList.get((currentPageNo - 1)
						* pageSize + i);
				if(companyNameList!=null&&companyNameList.contains(record.getCompanyNameInProperties())){
					record.setExistFlag(true);
				}
				returnLits.add(record);
			}
		}

		Integer i = new Integer(companyInfoList.size());
		long totalCount = i.longValue();

		Page<RelationCompanyAndPerson> retValue = (new PageUtil()).createPage(pageSize,totalCount, currentPageNo);
		retValue.setResults(returnLits);
		model.addObject("vestpage", retValue);
		//  投资公司； investmentCompanyList
		model.setViewName("/modules/monitor/monitorEvents/investmentCompany");
		model.addObject("companyName", companyName);
		model.addObject("resultMap", resultMap);
		return model;
	}
	
	
	@RequestMapping("/getStockHolderCompanyList")
	public ModelAndView getStockHolderCompanyList(String monitorId,HttpServletRequest request, HttpServletResponse response){
	
		ModelAndView model = new ModelAndView();
		String companyName = request.getParameter("companyName");
		int currentPageNo = 1;
		if(StringUtils.isNotEmpty(request.getParameter("currentPageNo"))){
			currentPageNo = Integer.valueOf(request.getParameter("currentPageNo"));
		}
		int pageSize = 10;
		if(StringUtils.isNotEmpty(request.getParameter("pageSize"))){
			pageSize = Integer.valueOf(request.getParameter("pageSize"));
		}
		Map<String, List<RelationCompanyAndPerson>> resultMap =addRelationCompanyAndPersonService.queryAllRelationsList(companyName);
		
		model.addObject("stockholderList",resultMap.get("stockholderList"));
		model.addObject("stockholderCorporationLegalList",resultMap.get("stockholderCorporationLegalList"));
		model.addObject("corporationLegalPersonList",resultMap.get("corporationLegalPersonList"));
		model.addObject("managerList",resultMap.get("managerList"));
		model.addObject("investmentCompanyList",resultMap.get("investmentCompanyList"));


		List<RelationCompanyAndPerson> returnLits = new ArrayList<RelationCompanyAndPerson>();
		List<RelationCompanyAndPerson> companyInfoList = resultMap.get("stockholderCorporationLegalList");
		List<String>  companyNameList = null;
		if(StringUtils.isNotEmpty(monitorId)){
			//查询已经关联公司名称
			companyNameList = monitorRelationCompanyService.queryRelationCompanyNameByMonitorId(monitorId);
		}
		RelationCompanyAndPerson record = null;
		if ((currentPageNo * pageSize >= companyInfoList.size())) {
			for (int i = (currentPageNo - 1) * pageSize; i < companyInfoList
					.size(); i++) {
				record = companyInfoList.get(i);
				if(companyNameList!=null&&companyNameList.contains(record.getCompanyNameInProperties())){
					record.setExistFlag(true);
				}
				returnLits.add(record);
			}
		} else {
			for (int i = 0; i < 10; i++) {
				record = companyInfoList.get((currentPageNo - 1)
						* pageSize + i);
				if(companyNameList!=null&&companyNameList.contains(record.getCompanyNameInProperties())){
					record.setExistFlag(true);
				}
				returnLits.add(record);
			}
		}

		Integer i = new Integer(companyInfoList.size());
		long totalCount = i.longValue();

		Page<RelationCompanyAndPerson> retValue = (new PageUtil()).createPage(pageSize,
				totalCount, currentPageNo);
		retValue.setResults(returnLits);

		model.addObject("stockHolderPage", retValue);
		
		
		//  投资公司； investmentCompanyList
		model.setViewName("/modules/monitor/monitorEvents/stockHolderCompany");
		model.addObject("companyName", companyName);
		model.addObject("resultMap", resultMap);
		return model;
	}
	
	//
	
	/**
	 * 
	 * @Title: search4Company
	 * @Description: 检索公司信息
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return ModelAndView
	 * @author jiang.zhou
	 * @throws
	 * @date 下午4:14:01
	 */
	@RequestMapping("/search4Company1.do")
	public ModelAndView search4Company1(String monitorId,HttpServletRequest request,
			HttpServletResponse response) {

		String keyword = request.getParameter("keyword");
		String companyName=  request.getParameter("companyName"); // 主体公司；
		ModelAndView model = new ModelAndView();

		// 从request中获取dto数据
		int currentPageNo = 1;
		if (!StringUtils.isEmpty(request.getParameter("currentPageNo"))) {
			currentPageNo = Integer.valueOf(request
					.getParameter("currentPageNo"));
		}
		int pageSize = 10;
		if (!StringUtils.isEmpty(request.getParameter("pageSize"))) {
			pageSize = Integer.valueOf(request.getParameter("pageSize"));
		}

		Map<String, Object> map = new HashMap<String ,Object>();
		
		List<CompanyInfo> returnLits = new ArrayList<CompanyInfo>();
		List<CompanyInfo> companyInfoList = new ArrayList<CompanyInfo>();

		if(currentPageNo>1){
			map =  companyInfoService.getCompanyInfoListFromCacheByNum(keyword, 60);
			if(map==null){
				map = companyInfoService.queryCompanyInfoListUnion(keyword, 60);
			}
		}else{
			map = companyInfoService.queryCompanyInfoListUnion(keyword, 60);
		}
		companyInfoList = (List<CompanyInfo>) map.get("companyInfoList");
		List<String>  companyNameList = null;
		if(StringUtils.isNotEmpty(monitorId)){
			//查询已经关联公司名称
			companyNameList = monitorRelationCompanyService.queryRelationCompanyNameByMonitorId(monitorId);
		}
		CompanyInfo record = null;
		if ((currentPageNo * pageSize >= companyInfoList.size())) {
			for (int i = (currentPageNo - 1) * pageSize; i < companyInfoList
					.size(); i++) {
				record = companyInfoList.get(i);
				if(companyNameList!=null&&companyNameList.contains(record.getRegisterInfo().getEnterpriseName())){
					record.setExistFlag(true);
				}
				
				returnLits.add(record);
			}
		} else {
			for (int i = 0; i < 10; i++) {
				record =companyInfoList.get((currentPageNo - 1)
						* pageSize + i);
				if(companyNameList!=null&&companyNameList.contains(record.getRegisterInfo().getEnterpriseName())){
					record.setExistFlag(true);
				}
				returnLits.add(record);
			}
		}

		Integer i = new Integer(companyInfoList.size());
		long totalCount = i.longValue();

		Page<CompanyInfo> retValue = (new PageUtil()).createPage(pageSize,totalCount, currentPageNo);
		retValue.setResults(returnLits);
		
		ClientAccount account = (ClientAccount) request.getSession().getAttribute("account");
		if(account==null){
			throw new SessionOverdueException(ErrorCode.ER_SESSION_OVERDUE, "登录会话过期", null, this.getClass());
		}
		if(account!=null){
			setAddedFlag(returnLits,companyName,account.getAccountId());
		}
		
		model.addObject("page", retValue);
		model.addObject("total", map.get("total"));
		
//		model.setViewName("/modules/monitor/monitorCompany/CompanyInfoList");
		model.setViewName("/modules/monitor/monitorEvents/eventCompanyInfoList");

		return model;
	}
	
	
	/**
	* @Title: setAddedFlag
	* @Description: TODO(shez )
	* @param  @param returnLits  
	* @return void    返回类型
	* @author zihui.pei  
	* @throws
	* @date 2017年1月22日 下午4:50:02
	*/
	private void setAddedFlag(List<CompanyInfo> returnLits,String companyName,String accountId) {
		
		if(returnLits!=null&&!returnLits.isEmpty()){
			List<MonitorRelationCompany> companyList =this.getReaCompanyListOfAllGroup(companyName,accountId);
			if(companyList!=null&&!companyList.isEmpty()){
				for(CompanyInfo c:returnLits){
					for(MonitorRelationCompany mrc :companyList){
						if(c.getRegisterInfo().getEnterpriseName().equals(mrc.getCompanyName())){
							c.setMonitorFlag("1");
						}
					}
				}
			}
		}
	}

	
	
	/**
	* @Title: getPersonNameStrs
	* @Description: TODO(获取名字名称)
	* @param  @param list
	* @param  @return  
	* @return String    返回类型
	* @author zihui.pei  
	* @throws
	* @date 2017年1月22日 上午10:33:15
	*/
	public String getPersonNameStrs(List<MonitorRelationPerson> list){
		StringBuffer sb = new StringBuffer("");
		if(list!=null&&!list.isEmpty()){
			for(MonitorRelationPerson mrp:list){
//				sb.append("'"+mrp.getName()+"'"+",");
				sb.append(mrp.getName()+",");
			}
		}
		if(sb.lastIndexOf(",")>0){
			String result = sb.substring(0, sb.lastIndexOf(","));
			return result;
		}else{
			return "";
		}
		
	}
	
	
	/**
	* @Title: getReaCompanyListOfAllGroup
	* @Description: TODO(获取)
	* @param  @param mainCompanyName
	* @param  @param accountId
	* @param  @return  
	* @return List<MonitorRelationCompany>    返回类型
	* @author zihui.pei  
	* @throws
	* @date 2017年1月22日 下午4:47:43
	*/
	public List<MonitorRelationCompany> getReaCompanyListOfAllGroup(String mainCompanyName ,String accountId){
		// 全部分组Id
		MonitorCompany monitorMainCompany = monitorCompanyService.getMonitorCompany(accountId, mainCompanyName);
		String monitorId = monitorMainCompany.getMonitorID();
//		MonitorRelationGroup group = monitorRelGroupService.getMonitorRelationGroup(monitorId,"全部");
//	    if(group==null){
//	    	return null;
//	    }
//		String groupId=group.getGroupID();
//	    try {
//	    	List<MonitorRelationCompany> companyListOfAllGroup = monitorRelComService.getReaCompanyList(groupId);
//	    	return companyListOfAllGroup;
//		} catch (Exception e) {
//			logger.error("eee==="+e);
//		}		
		return null;
	}

}
