/**   
* @Title: MonitorRelationCompanyController.java 
* @Package com.srd.ljzd.controller.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年5月4日 上午10:20:52 
* @version V1.0   
*/
package com.srd.ljzd.controller.monitor;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.srd.ljzd.controller.base.BaseController;
import com.srd.ljzd.dto.monitor.MonitorComEventDTO;
import com.srd.ljzd.entity.biz.BizMsg;
import com.srd.ljzd.entity.client.ClientAccount;
import com.srd.ljzd.entity.common.ResultBean;
import com.srd.ljzd.entity.company.CompanyInfo;
import com.srd.ljzd.entity.monitor.BaseDimension;
import com.srd.ljzd.entity.monitor.MonitorCompany;
import com.srd.ljzd.entity.monitor.MonitorCompanyEvent;
import com.srd.ljzd.entity.monitor.MonitorCompanyEventState;
import com.srd.ljzd.entity.monitor.MonitorRelationCompany;
import com.srd.ljzd.entity.monitor.Penalty;
import com.srd.ljzd.exception.SessionOverdueException;
import com.srd.ljzd.service.biz.BizService;
import com.srd.ljzd.service.client.ClientAccountService;
import com.srd.ljzd.service.company.CompanyInfoService;
import com.srd.ljzd.service.company.CompanyUsedNameService;
import com.srd.ljzd.service.log.OperationLogService;
import com.srd.ljzd.service.monitor.MonitorCompanyEventService;
import com.srd.ljzd.service.monitor.MonitorCompanyEventStateService;
import com.srd.ljzd.service.monitor.MonitorCompanyService;
import com.srd.ljzd.service.monitor.MonitorRelationCompanyService;
import com.srd.ljzd.service.monitor.MonitorUserConfigureService;
import com.srd.ljzd.util.Constant;
import com.srd.ljzd.util.DateUtils;
import com.srd.ljzd.util.ErrorCode;
import com.srd.ljzd.util.LoggerUtils;
import com.srd.ljzd.util.Page;
import com.srd.ljzd.util.StringUtils;
import com.srd.ljzd.util.ThirdPartChannelEnum;

/** 
 * @ClassName: MonitorRelationCompanyController
 * @Description: 动态监控关系企业Controller
 * @author shiyong
 * @date 2017年5月4日 上午10:20:52
 *  
 */
@Controller
@RequestMapping("/monitorRelationCompany")
public class MonitorRelationCompanyController extends BaseController{
	
	@Autowired
	private CompanyInfoService companyInfoService;
	
	@Autowired
	private MonitorRelationCompanyService monitorRelationCompanyService;
	
	@Autowired
	private MonitorCompanyService monitorCompanyService;
	
	@Autowired
	private MonitorCompanyEventService monitorCompanyEventService;
	
	@Autowired
	private MonitorCompanyEventStateService monitorComEveStateService;
	
	@Autowired
	private MonitorUserConfigureService monitorUserConfigureService;
	
	@Autowired
	private CompanyUsedNameService companyUsedNameService;
	
	@Autowired
	private ClientAccountService clientAccountService;
	
	@Autowired
	private MonitorCompanyEventService monitorComEventService;
	
	@Autowired
	private BizService bizService;
	
	@Autowired
	private OperationLogService operationLogService;
	
	SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
	
	SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/** 
	* @Title: addMonitorRelationCompany 
	* @Description: 添加关系企业
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月25日 下午4:57:36
	*/
	@ResponseBody
	@RequestMapping("/addMonitorRelationCompany")
	public ResultBean addMonitorRelationCompany(HttpServletRequest request, HttpServletResponse response) {
		ResultBean result = new ResultBean();
		
		ClientAccount account = (ClientAccount) request.getSession().getAttribute("account");
		
		if(account==null){
			throw new SessionOverdueException(ErrorCode.ER_SESSION_OVERDUE, "登录会话过期", null, this.getClass());
		}
		
		String accountId = account.getAccountId();
		
		result = clientAccountService.canMonitorCompany(accountId);
		
		if(!"1".equals(result.getResultCode())){
			String monitorId = request.getParameter("monitorId");
			String companyName = request.getParameter("companyName").trim();
			String province = "";
			String companyId = null;
			StringBuffer reNamedMsgBug = null;
			try {
				companyName = URLDecoder.decode(companyName, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				LoggerUtils.error(e.getMessage(), e);
			}
			
			//获取关系企业/个人全部分组
			MonitorRelationCompany monitorRelationCompany = monitorRelationCompanyService.getMonitorRelationCompany(monitorId, companyName);
			
			if(null != monitorRelationCompany){
				result.setResultCode("1");
				result.setResultMsg("重复加入该公司");
			}else{
				CompanyInfo company = companyInfoService.buildCompanyInfoFromMultiChannel(companyName);
				
				if(null != company && null != company.getRegisterInfo()){
					String dataName = company.getRegisterInfo().getEnterpriseName().trim();
					companyId = company.getRegisterInfo().getCompanyId();
					if(StringUtils.isEmpty(dataName)){
						result.setResultCode("4");
						result.setResultMsg("该企业不存在!");
						return result;
					}
                    province = company.getRegisterInfo().getProvince().trim();
					if(StringUtils.isEmpty(province)){
						result.setResultCode("4");
						result.setResultMsg("没有查询到企业省份信息，不能添加!");
						return result;
					}
					if(!companyName.equals(dataName)){
						
						reNamedMsgBug = new StringBuffer("该企业已更名为：");
						reNamedMsgBug.append(dataName);
						monitorRelationCompany = monitorRelationCompanyService.getMonitorRelationCompany(monitorId, dataName);
						if(null != monitorRelationCompany){
							reNamedMsgBug.append("，更名后企业加入失败，原因为：重复加入该公司!");
							result.setResultCode("2");
							result.setResultMsg(reNamedMsgBug.toString());
							return result;
						}
						
						//保存企业曾用名
						companyUsedNameService.saveCompanyUsedName(companyName);
						
						companyName = dataName;
					}
				}else{
					result.setResultCode("4");
					result.setResultMsg("此企业名称不全或有误,请搜索该企业全称后再添加!");
					return result;
				}
				
				monitorRelationCompany = new MonitorRelationCompany();
				monitorRelationCompany.setMonitorId(monitorId);
				monitorRelationCompany.setAccountID(accountId);
				monitorRelationCompany.setCompanyId(companyId);
				monitorRelationCompany.setCompanyName(companyName);
				
				if("工商总局".equals(province)){
					monitorRelationCompany.setProvince("北京市");
				}else{
					monitorRelationCompany.setProvince(province);
				}
				
				monitorRelationCompany.setAreaName(province);
				
				monitorRelationCompany.setEventLevel("");
				monitorRelationCompany.setNewEventFlag("");
				monitorRelationCompany.setNormalEventNum(0);
				monitorRelationCompany.setAttentionEventNum(0);
				monitorRelationCompany.setWarnEventNum(0);
				monitorRelationCompany.setRiskSituation("");
				monitorRelationCompany.setRiskLabel("");
				monitorRelationCompany.setCompanyBackgroundIndex(0);
				monitorRelationCompany.setJudicialLitigationIndex(0);
				monitorRelationCompany.setOperateExceptionIndex(0);
				monitorRelationCompany.setNegativeNewsIndex(0);
				monitorRelationCompany.setDevelopmentPotentialIndex(0);
				monitorRelationCompany.setRenameStatus("0");
				
				Calendar cal = Calendar.getInstance();
				monitorRelationCompany.setMonitorDate(cal.getTime());
				monitorRelationCompany.setCreateTime(cal.getTime());
				cal.add(Calendar.YEAR, -1);
				monitorRelationCompany.setInfoShowDate(cal.getTime());
				monitorRelationCompany.setDeleteFlag("0");
				
				result = monitorRelationCompanyService.saveMonitorRelationCompany(monitorRelationCompany);
				
				if("0".equals(result.getResultCode())){
					//添加成功后减去总加入家数
					clientAccountService.reduceMonitorNum(accountId);
					
					if(reNamedMsgBug!=null){
						result.setResultCode("2");
						reNamedMsgBug.append("，更名后企业加入成功！监控详情将于次日展示");
						result.setResultMsg(reNamedMsgBug.toString());
					}
				}
				//保存用户使用记录
				MonitorCompany monitorCompany = monitorCompanyService.queryMonitorCompanyById(monitorId);
				operationLogService.save(account.getAccountId(),account.getAccountName()
		        		,"新增动态监控","新增方式 新增关联企业 企业名称 "+monitorCompany.getCompanyName()+" 关联企业名称 "+companyName,DateUtils.getCurrentDateStr(DateUtils.DEFAULT_PATTERN));
			}
		}
		
		return result;
	}
	
	/** 
	* @Title: deleteMonitorRelationCompanys 
	* @Description: 删除关系企业
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月26日 上午11:01:07
	*/
	@ResponseBody
	@RequestMapping("/deleteMonitorRelationCompanys")
	public ResultBean deleteMonitorRelationCompanys(HttpServletRequest request, HttpServletResponse response) {
		String relationCompanyIds = request.getParameter("relationCompanyIds");
		
		List<String> relationCompanyIdList = new ArrayList<String>();

		if (StringUtils.isNotEmpty(relationCompanyIds)) {
			String[] idArr = relationCompanyIds.split(",");
			relationCompanyIdList = Arrays.asList(idArr);
		}
		
		ResultBean resultBean = monitorRelationCompanyService.deleteMonitorRelationCompanys(relationCompanyIdList);
	
		return resultBean;
	}
	
	/** 
	* @Title: getMonitorRelationCompanyPage 
	* @Description: 获取关系企业分页
	* @param @param request
	* @param @param response
	* @param @param model
	* @param @return 设定文件 
	* @return String 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月24日 上午10:39:07
	*/
	@RequestMapping("/getMonitorRelationCompanyPage")
	public String getMonitorRelationCompanyPage(HttpServletRequest request, HttpServletResponse response, Model model) {
		String monitorId = request.getParameter("monitorId");
		
		int currentPageNo = 1;
		if (StringUtils.isNotEmpty(request.getParameter("currentPageNo"))) {
			currentPageNo = Integer.valueOf(request.getParameter("currentPageNo"));
		}
		int pageSize = 6;
		if (StringUtils.isNotEmpty(request.getParameter("pageSize"))) {
			pageSize = Integer.valueOf(request.getParameter("pageSize"));
		}
		
		Page<MonitorRelationCompany> page = monitorRelationCompanyService.getMonitorRelationCompanyPage(monitorId, currentPageNo, pageSize);
		
		model.addAttribute("page", page);
		
		Calendar cal = Calendar.getInstance();
		
		String today = sdfDate.format(cal.getTime()) + " 00:00:00.0";
		
		model.addAttribute("today", today);
		
		return "/modules/monitor/monitorCompany/monitorRelationCompanyList";
	}
	@RequestMapping("/getMonitorRelationCompanyPageWithJSON")
	@ResponseBody
	public Object getMonitorRelationCompanyPageWithJSON(HttpServletRequest request, HttpServletResponse response, Model model) {
		String monitorId = request.getParameter("monitorId");
		
		int currentPageNo = 1;
		if (StringUtils.isNotEmpty(request.getParameter("currentPageNo"))) {
			currentPageNo = Integer.valueOf(request.getParameter("currentPageNo"));
		}
		int pageSize = 6;
		if (StringUtils.isNotEmpty(request.getParameter("pageSize"))) {
			pageSize = Integer.valueOf(request.getParameter("pageSize"));
		}
		
		Page<MonitorRelationCompany> page = monitorRelationCompanyService.getMonitorRelationCompanyPage(monitorId, currentPageNo, pageSize);
		
		return page;
	}
	/** 
	* @Title: viewMonitorRelationCompanySimpleDetail 
	* @Description: 查看关系企业基本信息
	* @param @param request
	* @param @param response
	* @param @param model
	* @param @return 设定文件 
	* @return String 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月24日 下午5:09:56
	*/
	@RequestMapping(value="/viewMonitorRelationCompanySimpleDetail")
	public String viewMonitorRelationCompanySimpleDetail(HttpServletRequest request, HttpServletResponse response, Model model){
		String companyName = request.getParameter("companyName");
		String deep = request.getParameter("deep");
		
		BizMsg company = bizService.getGongShangMsg(companyName,ThirdPartChannelEnum.QI_CHA_CHA);
		
		if(company!=null&&company.getBrefCompany()!=null){
			model.addAttribute("brefCompany", company.getBrefCompany());
		}
		
		int curPage=1;
		int pageSize = Constant.DETAULT_PAGE_SIZE;
		
		bizService.buildPageBref(company,curPage,pageSize,model);
		
		model.addAttribute("companyName", companyName);
		model.addAttribute("deep",deep);
		
		return "/modules/monitor/monitorCompany/monitorRelationCompanySimpleDetail";
	}
	
	/** 
	* @Title: viewMonitorRelationCompanyDetail 
	* @Description: 跳转到关联公司详情页
	* @param @param request
	* @param @param response
	* @param @param model
	* @param @return 设定文件 
	* @return String 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月24日 下午1:41:33
	*/
	@RequestMapping("viewMonitorRelationCompanyDetail")
	public String viewMonitorRelationCompanyDetail(HttpServletRequest request, HttpServletResponse response, Model model){
		String id = request.getParameter("id");
		String deep = request.getParameter("deep");
		
		MonitorRelationCompany monitorRelationCompany = monitorRelationCompanyService.getMonitorRelationCompanyById(id);
		
		CompanyInfo companyInfo = companyInfoService.getCompanyRegisterInfo(monitorRelationCompany.getCompanyName());
		
		model.addAttribute("monitorRelationCompany",monitorRelationCompany);
		model.addAttribute("companyInfo",companyInfo);
		model.addAttribute("deep",deep);
		
		//保存用户使用记录
		MonitorCompany monitorCompany = monitorCompanyService.queryMonitorCompanyById(monitorRelationCompany.getMonitorId());
    	ClientAccount account = super.getClientAccountFromSession(request);
		operationLogService.save(account.getAccountId(),account.getAccountName()
        		,"查看企业详情","企业名称 "+monitorCompany.getCompanyName()+" 关联企业名称 "+monitorRelationCompany.getCompanyName(),DateUtils.getCurrentDateStr(DateUtils.DEFAULT_PATTERN));
		
		return "/modules/monitor/monitorCompany/monitorRelationCompanyDetail";
	}
	
	/** 
	* @Title: getMonitorRelationCompanyRisk 
	* @Description: 获取关系企业信息
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月24日 下午1:26:11
	*/
	@RequestMapping("/getMonitorRelationCompanyRisk")
	@ResponseBody
	public ResultBean getMonitorRelationCompanyRisk(HttpServletRequest request,HttpServletResponse response){
		ResultBean resultBean = new ResultBean();
		
		String id=request.getParameter("id");
		
		MonitorRelationCompany monitorRelationCompany = monitorRelationCompanyService.getMonitorRelationCompanyById(id);
		
		if(null == monitorRelationCompany){
			resultBean.setResultCode("1");
			resultBean.setResultMsg("获取关系企业信息失败！");
		}else{
			resultBean.setResultCode("0");
			resultBean.setResultMsg("获取关系企业信息成功！");
			resultBean.setResultData(monitorRelationCompany);
		}
		return resultBean;
		
	}
	
	@ResponseBody
	@RequestMapping("/getMonitorRelationCompanyEventStatus")
	public ResultBean getMonitorRelationCompanyEventStatus(String monitorId,HttpServletRequest request,HttpServletResponse response){
		ResultBean resultBean = new ResultBean();
		
		String id = request.getParameter("id");
		
		String accountId = getAccountIdFromSession(request);
	    
	    MonitorRelationCompany monitorRelationCompany = monitorRelationCompanyService.getMonitorRelationCompanyById(id);
		
		if(null == monitorRelationCompany){
			resultBean.setResultCode("1");
			resultBean.setResultMsg("当前公司刚刚加入动态监控没有数据显示！");
		}else{
			List category = monitorCompanyEventService.queryAllEventCategory(monitorRelationCompany.getCompanyName(), monitorRelationCompany.getInfoShowDate());
			
			Map<String,Object> categoryMap = monitorCompanyEventService.queryUnReadEventCategoryNumList(accountId, monitorRelationCompany.getId(), monitorRelationCompany.getCompanyName(), monitorRelationCompany.getInfoShowDate());
			
			if(category!=null&&category.size()>0){
				for(Object cate : category){
					if(!categoryMap.containsKey((String)cate)){
						categoryMap.put((String)cate, 0);//0表示有数据，大于零则代表有未读数
					}
				}
			}
			
			resultBean.setResultCode("0");
			resultBean.setResultMsg("查询成功！");
			resultBean.setResultData(categoryMap);
		}
		
		return resultBean;
	}
	
	/** 
	* @Title: getRelationCompanyEventList 
	* @Description: 获取动态监控关联企业事件列表
	* @param @param request
	* @param @param response
	* @param @param model
	* @param @return 设定文件 
	* @return String 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月24日 下午3:00:12
	*/
	@RequestMapping("/getRelationCompanyEventList")
	public String getRelationCompanyEventList(HttpServletRequest request,HttpServletResponse response, Model model){
		
		String id = request.getParameter("id");
		String eventSubType = request.getParameter("eventSubType");
		String beginDate = request.getParameter("beginDate");
		String dueDate = request.getParameter("dueDate");
		String typeListStr = request.getParameter("typeListString");
		String eventIdListStr = request.getParameter("eventIdListStr");
		String readStatus = request.getParameter("readStatus");
		
		MonitorRelationCompany monitorRelationCompany = monitorRelationCompanyService.getMonitorRelationCompanyById(id);
		
		MonitorComEventDTO monitorComEventDTO=new MonitorComEventDTO();
		monitorComEventDTO.setEventSubType(request.getParameter("eventSubType"));
		monitorComEventDTO.setCompanyName(monitorRelationCompany.getCompanyName());
		monitorComEventDTO.setBeginDate(beginDate);
		monitorComEventDTO.setDueDate(dueDate);
		monitorComEventDTO.setMonitorDate(sdfDate.format(monitorRelationCompany.getMonitorDate()));
		monitorComEventDTO.setInfoShowDate(sdfDate.format(monitorRelationCompany.getInfoShowDate()));
		
		int currentPageNo = 1;
		
		List<String> typeList=new ArrayList<String>();
		List<String> eventIdList=new ArrayList<String>();
		if(!StringUtils.isEmpty(typeListStr)){
			String [] typeArr=typeListStr.split(",");
			typeList=Arrays.asList(typeArr);
		}
		if(!StringUtils.isEmpty(eventIdListStr)){
			String [] idArr=eventIdListStr.split(",");
			eventIdList=Arrays.asList(idArr);
		}
		//如果evetnId列表不为空那么就需要进行更新
		if(eventIdList!=null&&eventIdList.size()>0){
			//更新已读数据
			MonitorCompanyEventState monitorCompanyEventState=new MonitorCompanyEventState();
			monitorCompanyEventState.setAccountId(monitorRelationCompany.getAccountID());
			monitorCompanyEventState.setState("1");
			monitorCompanyEventState.setCompanyId(id);
			monitorComEveStateService.addMonitorComEveState(monitorCompanyEventState, eventIdList);
		}
        
        String today = DateUtils.getCurrentDateStr();
        List<String> NO_CONTENTDATE_TYPE_LIST = Arrays.asList("06","07","10","27","28");
		
		//列表页面上显示时间的才进行时间的筛选
		if(!NO_CONTENTDATE_TYPE_LIST.contains(eventSubType)){
			if(beginDate!=null&&!"".equals(beginDate.trim())){
				monitorComEventDTO.setBeginDate(beginDate);
			}
			if(dueDate!=null&&!"".equals(dueDate.trim())){
				monitorComEventDTO.setDueDate(dueDate);
			}
		}
		
		if(!StringUtils.isEmpty(request.getParameter("currentPageNo"))){
			currentPageNo = Integer.valueOf(request.getParameter("currentPageNo"));
		}
		int pageSize = 6;
		if(!StringUtils.isEmpty(request.getParameter("pageSize"))){
			pageSize = Integer.valueOf(request.getParameter("pageSize"));
		}
	
		Page<Object> returnPage=new Page<Object>(); 
		StringBuffer buf=new StringBuffer();
		Page<MonitorCompanyEvent> page=null;
		List<MonitorCompanyEvent> eveList = null;
		List<String> typeConf = monitorUserConfigureService.getUserAttentionEventClass(monitorRelationCompany.getAccountID());
		if(typeConf.contains(monitorComEventDTO.getEventSubType())){
			monitorComEventDTO.setTypeList(typeList);
			monitorComEventDTO.setReadStatus(readStatus);
			monitorComEventDTO.setMonitorCompanyId(id);
			page= monitorCompanyEventService.getMonitorEveList(monitorComEventDTO,currentPageNo,pageSize);
			if(page!=null){
				eveList=page.getResults();
			}
		}
		
		Map<String,MonitorCompanyEvent> paramEventsMap = new HashMap<String, MonitorCompanyEvent>();
		
		//List targetList = new ArrayList();
        if(eveList!=null&&eveList.size()>0){
        	
        	List resultList= new ArrayList();
        	List tempList = null;
        	for(MonitorCompanyEvent event:eveList){
        		//调用大数据的接口查看公司事件的详情 组合成分页Page对象进行返回
        		if(event!=null
        				&&!StringUtils.isEmpty(event.getEventID())
        				&&!StringUtils.isEmpty(event.getEventID().trim())){
        			paramEventsMap = new HashMap<String, MonitorCompanyEvent>();
        			
        			if("1".equals(event.getEventLevel())){
						event.setEventLevel("一般");
					}else if("2".equals(event.getEventLevel())){
						event.setEventLevel("异常");
					}else if("3".equals(event.getEventLevel())){
						event.setEventLevel("严重");
					}
					
        			paramEventsMap.put(event.getEventID().trim(), event);
        			tempList= monitorCompanyEventService.getMonitorComEveDetailsList(paramEventsMap,monitorComEventDTO.getEventSubType(),false);
        			if(tempList!=null&&tempList.size()>0){
        				resultList.addAll(tempList);
        			}
        		}
        	}
    		for(int i=0;i<resultList.size();i++){
    			
    			//获取对应的eventId
				String eventId=((BaseDimension)resultList.get(i)).getEventId();
				//查询对应的事件是否已经读
				MonitorCompanyEventState eventState=new MonitorCompanyEventState();
				eventState.setAccountId(monitorRelationCompany.getAccountID());
				eventState.setCompanyId(id);
				eventState.setEventId(eventId);
				MonitorCompanyEventState result=monitorComEveStateService.getReadedEvent(eventState);
				if(result==null){
					//未读 （0：未读，1：已读）
					((BaseDimension)resultList.get(i)).setStatus("0");
				}else{
					//已读
					((BaseDimension)resultList.get(i)).setStatus("1");
				}
				if("19".equals(eventSubType)||"18".equals(eventSubType)){
					//获取胜败诉
					Penalty penalty = (Penalty)resultList.get(i);
					Map<String, Object>  target = monitorComEventService.getMoneyPlaintiffDefendant(penalty.getJudgmentId(), monitorRelationCompany.getCompanyName());
					if(target!=null){
						penalty.setAmount((String)target.get("amount"));
						penalty.setResult((String)target.get("result"));
					}
				}
				if(i<resultList.size()-1){
					buf.append(((BaseDimension)resultList.get(i)).getEventId());
					buf.append(",");
				}else{
					buf.append(((BaseDimension)resultList.get(i)).getEventId());
				}
				
			}
    		returnPage.setCurrentPageNo(page.getCurrentPageNo());
    		returnPage.setPageSize(page.getPageSize());
    		returnPage.setRecordSum(page.getRecordSum());
    		returnPage.setResults(resultList);
        }
        
    	model.addAttribute("updateNum",eventIdList.size());
		model.addAttribute("eventIdList",buf.toString());
		model.addAttribute("eventSubType", eventSubType);
		model.addAttribute("page", returnPage);
		
		return "/modules/monitor/monitorCompany/monitorRelationCompanyEventList";
	}
	
	/** 
	* @Title: getMonitorDetails4Sfws 
	* @Description: 获取司法文书详情
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return Object 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月31日 下午6:12:11
	*/
	@ResponseBody
	@RequestMapping("/getMonitorDetails4Sfws.do")
	public Object getMonitorDetails4Sfws(HttpServletRequest request,HttpServletResponse response){
		//获取对应的index 获取对应的页数直接返回不需要进行类型区分
		String judgmentId=request.getParameter("judgmentId");
		String companyName=request.getParameter("companyName");
		
		try {
			companyName = URLDecoder.decode(companyName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LoggerUtils.error(e.getMessage(), e);
		}
		
		Penalty penalty = monitorCompanyEventService.getMonitorComSfEveDetails(judgmentId,companyName);
		
		return penalty;
	}
	
	/** 
	* @Title: getMonitorComEventDetails 
	* @Description: 获取事件详情
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return Object 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月31日 下午6:14:24
	*/
	@ResponseBody
	@RequestMapping("/getReaComEventDetails.do")
	public Object getMonitorComEventDetails(HttpServletRequest request,HttpServletResponse response){
		
		//获取对应的index 获取对应的页数直接返回不需要进行类型区分
		String eventId=request.getParameter("eventId");
		String eventSubType=request.getParameter("eventSubType");

		Map paramEventsMap = new HashMap<String, MonitorCompanyEvent>();
		MonitorCompanyEvent monitorCompanyEvent=new MonitorCompanyEvent();
		paramEventsMap.put(eventId, monitorCompanyEvent);
		List tempList = monitorCompanyEventService.getMonitorComEveDetailsList(paramEventsMap,eventSubType,true);
		
		Map<String,Object> resultMap=new HashMap<String,Object>();
		resultMap.put("eventSubType", eventSubType);
		resultMap.put("resultObj", tempList.get(0));
		return resultMap;
	}
	
}
