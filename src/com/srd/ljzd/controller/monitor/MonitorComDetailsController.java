package com.srd.ljzd.controller.monitor;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
import com.srd.ljzd.entity.monitor.Penalty;
import com.srd.ljzd.exception.SessionOverdueException;
import com.srd.ljzd.service.biz.BizService;
import com.srd.ljzd.service.company.CompanyInfoService;
import com.srd.ljzd.service.log.OperationLogService;
import com.srd.ljzd.service.monitor.MonitorCompanyEventStateService;
import com.srd.ljzd.service.monitor.MonitorCompanyEventService;
import com.srd.ljzd.service.monitor.MonitorCompanyService;
import com.srd.ljzd.service.monitor.MonitorGroupCompanyService;
import com.srd.ljzd.service.monitor.MonitorPersonEventService;
import com.srd.ljzd.service.monitor.MonitorRelationCompanyService;
import com.srd.ljzd.service.monitor.MonitorRelationPersonService;
import com.srd.ljzd.service.monitor.MonitorUserConfigureService;
import com.srd.ljzd.util.Constant;
import com.srd.ljzd.util.DateUtils;
import com.srd.ljzd.util.ErrorCode;
import com.srd.ljzd.util.LoggerUtils;
import com.srd.ljzd.util.Page;
import com.srd.ljzd.util.PageUtil;
import com.srd.ljzd.util.StringUtils;

/**
 * 
 * 版权所有： 项目名称：lengjingzd2.0
 *
 * 类描述：动态监控详情页面控制层 类名称：com.srd.ljzd.controller.monitor 创建人：jiang.zhou
 * 创建时间：20162016年11月1日上午11:43:54 修改人： 修改时间： 修改备注：
 * 
 * @version V2.0
 */
@Controller
@RequestMapping("/monitorComRisk")
public class MonitorComDetailsController extends BaseController{
	
	protected static Logger log = LogManager.getLogger(MonitorComDetailsController.class.getName());

	@Autowired
	MonitorCompanyService monitorCompanyService;

	@Autowired
	CompanyInfoService companyInfoService;

	@Autowired
	MonitorCompanyEventService monitorComEventService;
	@Autowired
	MonitorGroupCompanyService monitorGroupCompanyService;

	@Autowired
	MonitorRelationCompanyService monitorRelComService;

	@Autowired
	MonitorRelationPersonService monitorRelPersonService;
	@Autowired
	MonitorPersonEventService monitorPersonEventService;
	@Autowired
	RedisTemplate<String, Map<String, Object>> redisTemplate;
	@Autowired
	MonitorUserConfigureService monitorUserConfigureService;
	@Autowired
	MonitorCompanyEventStateService monitorComEveStateService;
	@Autowired
	private OperationLogService operationLogService;
	@Autowired
	BizService bizService;

	private final static List<String> NO_CONTENTDATE_TYPE_LIST = Arrays.asList("06","07","10","27","28");
	

	/**
	 * 
	 * @Title: getMonitorComEvents
	 * @Description: 监控事件情况 以html的形式进行返回 参数的处理比较复杂日期参数可以选择七天之内 十五天之内
	 *               或者自定义事件段【自定义事件段开始日期必须在监控日期之后】
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return ModelAndView
	 * @author jiang.zhou
	 * @throws
	 * @date 下午4:55:05
	 */
	@RequestMapping("/monitorComEventsDetails.do")
	public ModelAndView getMonitorComEvents(String eventSubType,String companyName,String monitorDate,String infoShowDate,HttpServletRequest request,
			HttpServletResponse response) {
		try {
			companyName = URLDecoder.decode(companyName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		
		ModelAndView model = new ModelAndView();

		MonitorComEventDTO monitorComEventDTO = new MonitorComEventDTO();

		ClientAccount loginAccount = (ClientAccount) request.getSession().getAttribute(
				"account");
		String monitorId=request.getParameter("monitorId");
		//获取监控企业信息
		MonitorCompany monitorCompany = monitorCompanyService.queryMonitorCompanyById(monitorId);
		
		String accountId = monitorCompany.getClientAccount().getAccountId();
		monitorComEventDTO
				.setEventSubType(eventSubType);
		monitorComEventDTO.setCompanyName(companyName);
		//monitorComEventDTO.setDueDate(request.getParameter("dueDate"));
		monitorComEventDTO.setMonitorDate(monitorDate);
		monitorComEventDTO
				.setInfoShowDate(infoShowDate);

		
	
		//获取前端的收集的eventId 以及 typeList
		
		String typeListStr=request.getParameter("typeListString");
		String eventIdListStr=request.getParameter("eventIdListStr");
		String readStatus=request.getParameter("readStatus");
		
		String preEventSubType=request.getParameter("preEventSubType");
		String riskId=request.getParameter("riskId");
		
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
			monitorCompanyEventState.setAccountId(accountId);
			monitorCompanyEventState.setState("1");
			monitorCompanyEventState.setCompanyId(monitorId);
			monitorComEveStateService.addMonitorComEveState(monitorCompanyEventState, eventIdList);
		}
		
		int currentPageNo = 1;
		String today = DateUtils.getCurrentDateStr();
		String beginDate = request.getParameter("beginDate");
		String dueDate = request.getParameter("dueDate");
		
		//列表页面上显示时间的才进行时间的筛选
		if(!NO_CONTENTDATE_TYPE_LIST.contains(eventSubType)){
			if(beginDate!=null&&!"".equals(beginDate.trim())){
				monitorComEventDTO.setBeginDate(beginDate);
			}
			if(dueDate!=null&&!"".equals(dueDate.trim())){
				monitorComEventDTO.setDueDate(dueDate);
			}
		}
		
		if (!StringUtils.isEmpty(request.getParameter("currentPageNo"))) {
			currentPageNo = Integer.valueOf(request
					.getParameter("currentPageNo"));
		}
		int pageSize = 6;
		if (!StringUtils.isEmpty(request.getParameter("pageSize"))) {
			pageSize = Integer.valueOf(request.getParameter("pageSize"));
		}

		Page<Object> returnPage = new Page<Object>();
		StringBuffer buf=new StringBuffer();
		Page<MonitorCompanyEvent> page = null;
		List<MonitorCompanyEvent> eveList = null;
		List<String> typeConf = monitorUserConfigureService
				.getUserAttentionEventClass(accountId);
		if (typeConf.contains(monitorComEventDTO.getEventSubType())) {
			monitorComEventDTO.setTypeList(typeList);
			monitorComEventDTO.setReadStatus(readStatus);
			monitorComEventDTO.setMonitorCompanyId(monitorId);
			page = monitorComEventService.getMonitorEveList(monitorComEventDTO,
					currentPageNo, pageSize);
			if (page != null) {
				eveList = page.getResults();
			}
		}

		Map<String, MonitorCompanyEvent> paramEventsMap = new HashMap<String, MonitorCompanyEvent>();

		// List targetList = new ArrayList();
		if (eveList != null && eveList.size() > 0) {

			List resultList = new ArrayList();
			List tempList = null;
			for (MonitorCompanyEvent event : eveList) {
				// 调用大数据的接口查看公司事件的详情 组合成分页Page对象进行返回
				if (event != null && !StringUtils.isEmpty(event.getEventID())
						&& !StringUtils.isEmpty(event.getEventID().trim())) {
					paramEventsMap = new HashMap<String, MonitorCompanyEvent>();
					
					if("1".equals(event.getEventLevel())){
						event.setEventLevel("一般");
					}else if("2".equals(event.getEventLevel())){
						event.setEventLevel("异常");
					}else if("3".equals(event.getEventLevel())){
						event.setEventLevel("严重");
					}
					
					paramEventsMap.put(event.getEventID().trim(), event);

					tempList = monitorComEventService
							.getMonitorComEveDetailsList(paramEventsMap,
									monitorComEventDTO.getEventSubType(), false);
					if (tempList != null && tempList.size() > 0) {
						resultList.addAll(tempList);
					}
				}
			}
			
			for (int i = 0; i < resultList.size(); i++) {
				//获取对应的eventId
				String eventId=((BaseDimension)resultList.get(i)).getEventId();
				//查询对应的事件是否已经读
				MonitorCompanyEventState eventState=new MonitorCompanyEventState();
				eventState.setAccountId(accountId);
				eventState.setCompanyId(monitorId);
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
					Map<String, Object>  target = monitorComEventService.getMoneyPlaintiffDefendant(penalty.getJudgmentId(), companyName);
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
		model.addObject("updateNum",eventIdList.size());
		model.addObject("eventIdList",buf.toString());
		model.addObject("eventSubType", eventSubType);
		model.addObject("page", returnPage);
		model.setViewName("/modules/monitor/monitorCompany/monitorComEventList");

		return model;
	}

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
	@RequestMapping("/search4Company.do")
	public ModelAndView search4Company(HttpServletRequest request,
			HttpServletResponse response) {

		String keyword = request.getParameter("keyword");
		
		try {
			if(keyword!=null&&!"".equals(keyword)){
				keyword = URLDecoder.decode(keyword, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
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

		Map<String, Object> map = companyInfoService.queryCompanyInfoListUnion(
				keyword, 60);
		List<CompanyInfo> returnLits = new ArrayList<CompanyInfo>();
		List<CompanyInfo> companyInfoList = new ArrayList<CompanyInfo>();
		companyInfoList = (List<CompanyInfo>) map.get("companyInfoList");

		if ((currentPageNo * pageSize >= companyInfoList.size())) {
			for (int i = (currentPageNo - 1) * pageSize; i < companyInfoList
					.size(); i++) {
				returnLits.add(companyInfoList.get(i));
			}
		} else {
			for (int i = 0; i < 10; i++) {
				returnLits.add(companyInfoList.get((currentPageNo - 1)
						* pageSize + i));
			}
		}

		Integer i = new Integer(companyInfoList.size());
		long totalCount = i.longValue();

		Page<CompanyInfo> retValue = (new PageUtil()).createPage(pageSize, totalCount, currentPageNo);
		retValue.setResults(returnLits);

		model.addObject("page", retValue);
		model.addObject("total", map.get("total"));
		model.setViewName("/modules/monitor/monitorCompany/CompanyInfoList");

		return model;
	}

	@ResponseBody
	@RequestMapping("/cancelMonitor.do")
	public ResultBean cancelMonitor(HttpServletRequest request,
			HttpServletResponse response) {

		ClientAccount account = (ClientAccount) request.getSession().getAttribute("account");

		String companyName = request.getParameter("companyName");
		
		try {
			companyName = URLDecoder.decode(companyName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LoggerUtils.error("", e);
		}
		
		MonitorCompany monitorCompany = new MonitorCompany();
		monitorCompany.setClientAccount(account);
		monitorCompany.setCompanyName(companyName);

		ResultBean result = monitorCompanyService.deleteMonitorCompany(monitorCompany);

		return result;
	}

	/**
	 * 
	 * @Title: getMonitorComSxEventDetails
	 * @Description: 获取司法事件详情
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return Object
	 * @author jiang.zhou
	 * @throws
	 * @date 上午11:04:50
	 */
	@ResponseBody
	@RequestMapping("/getMonitorComEventDetails.do")
	public Object getMonitorComEventDetails(HttpServletRequest request,
			HttpServletResponse response) {

		// 获取对应的index 获取对应的页数直接返回不需要进行类型区分
		String eventId = request.getParameter("eventId");
		String eventSubType = request.getParameter("eventSubType");

		Map paramEventsMap = new HashMap<String, MonitorCompanyEvent>();
		MonitorCompanyEvent monitorCompanyEvent = new MonitorCompanyEvent();
		paramEventsMap.put(eventId, monitorCompanyEvent);
		List tempList = monitorComEventService.getMonitorComEveDetailsList(
				paramEventsMap, eventSubType, true);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("eventSubType", eventSubType);
		resultMap.put("resultObj", tempList.get(0));
		return resultMap;

	}

	/**
	 * 
	 * @Title: getMonitorComSxEventDetails
	 * @Description: 获取司法事件详情
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return Object
	 * @author jiang.zhou
	 * @throws
	 * @date 上午11:04:50
	 */
	@ResponseBody
	@RequestMapping("/getMonitorDetails4Sfws.do")
	public Object getMonitorDetails4Sfws(HttpServletRequest request,
			HttpServletResponse response) {

		// 获取对应的index 获取对应的页数直接返回不需要进行类型区分
		String judgmentId = request.getParameter("judgmentId");
		String companyName = request.getParameter("companyName");
		
		try {
			companyName = URLDecoder.decode(companyName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		
		Penalty penalty = monitorComEventService.getMonitorComSfEveDetails(
				judgmentId, companyName);
		return penalty;

	}
	
	@RequestMapping("/toSiFaQuanJingtuView.do")
	public String toSiFaQuanJingtu(String compName,String deep,Model model,HttpServletRequest request,HttpServletResponse response){
		try {
			compName = URLDecoder.decode(compName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LoggerUtils.error(e.getMessage(), e);
		}
		model.addAttribute("companyName", compName);
		model.addAttribute("deep", deep);
		
		//保存用户使用记录
    	ClientAccount account = super.getClientAccountFromSession(request);
		operationLogService.save(account.getAccountId(),account.getAccountName()
        		,"查看司法全景图","企业名称 "+compName,DateUtils.getCurrentDateStr(DateUtils.DEFAULT_PATTERN));
		
		return "/modules/monitor/monitorCompany/siFaQuanJingtu";
	}
	
	@RequestMapping("/isHasFaDingDaiBiaoRen.do")
	@ResponseBody
	public Object isHasFaDingDaiBiaoRen(String companyName,Model model,HttpServletRequest request,HttpServletResponse response){
		try {
			companyName = URLDecoder.decode(companyName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		Map<String,Object> map = new HashMap<String,Object>();
		
		BizMsg company = bizService.getOriginalGongShangMsg(companyName);
		
		boolean isEmpty = (company==null)
				 ||( (company.getfRPositionArray()==null||company.getfRPositionArray().size()==0)
						 &&(company.getfRInvArray()==null||company.getfRInvArray().size()==0) );
		map.put("flag", !isEmpty);
		if(!isEmpty){
			map.put("fRInvArray", company.getfRInvArray());
			map.put("fRPositionArray", company.getfRPositionArray());
			redisTemplate.delete(companyName+"_bizFRMsg");
			redisTemplate.opsForValue().set(companyName+"_bizFRMsg", map, 2*60*60, TimeUnit.SECONDS);//2小时
		}
		return map;
	}
	
	@RequestMapping("/toFaDingDaiBiaoRenView.do")
	public String toFaDingDaiBiaoRen(String companyName,String deep,Model model,HttpServletRequest request,HttpServletResponse response){
		try {
			companyName = URLDecoder.decode(companyName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		ArrayList<HashMap<String,String>> fRInvArray = null;
		ArrayList<HashMap<String,String>> fRPositionArray = null;
		
		Map<String,Object> map = (Map<String,Object>) redisTemplate.opsForValue().get(companyName+"_bizFRMsg");
		if(map!=null){
			fRInvArray = (ArrayList<HashMap<String,String>>)map.get("fRInvArray");
			fRPositionArray = (ArrayList<HashMap<String,String>>)map.get("fRPositionArray");
		}else{
			BizMsg company = bizService.getOriginalGongShangMsg(companyName);
			fRInvArray = company.getfRInvArray();
			fRPositionArray = company.getfRPositionArray();
			
			map.put("fRInvArray", company.getfRInvArray());
			map.put("fRPositionArray", company.getfRPositionArray());
			redisTemplate.delete(companyName+"_bizFRMsg");
			redisTemplate.opsForValue().set(companyName+"_bizFRMsg", map, 2*60*60, TimeUnit.SECONDS);//2小时
		}
			
		if(fRPositionArray!=null&&fRPositionArray.size()>0){
			Map<String,Object> fRPositionPage = bizService.initPage(0,Constant.DETAULT_PAGE_SIZE, fRPositionArray);
			request.getSession().setAttribute("fRPositionPage", fRPositionPage);
			model.addAttribute("fRPositionArray", fRPositionArray);
		}
		if(fRInvArray!=null&&fRInvArray.size()>0){
			Map<String,Object> fRInvPage = bizService.initPage(0, Constant.DETAULT_PAGE_SIZE, fRInvArray);
			request.getSession().setAttribute("fRInvPage", fRInvPage);
			model.addAttribute("fRInvArray", fRInvArray);		
		}
		model.addAttribute("companyName", companyName);
		model.addAttribute("deep", deep);
		
		//保存用户使用记录
    	ClientAccount account = super.getClientAccountFromSession(request);
		operationLogService.save(account.getAccountId(),account.getAccountName()
        		,"查看法人代表信息","企业名称 "+companyName,DateUtils.getCurrentDateStr(DateUtils.DEFAULT_PATTERN));
		
		return "/modules/monitor/monitorCompany/faDingDaiBiaoRen";
	}
	
	@RequestMapping("/tofRInvNextPage.do")
	public String tofRInvNext(String companyName,Integer curPage ,Integer pageSize ,String deep,Model model,HttpServletRequest request,HttpServletResponse response){
		try {
			companyName = URLDecoder.decode(companyName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		ArrayList<HashMap<String,String>> fRInvArray = null;
		Map<String,Object> map = (Map<String,Object>) redisTemplate.opsForValue().get(companyName+"_bizFRMsg");
		if(map!=null){
			fRInvArray = (ArrayList<HashMap<String,String>>)map.get("fRInvArray");
		}else{
			BizMsg company = bizService.getOriginalGongShangMsg(companyName);
			fRInvArray = company.getfRInvArray();
			map.put("fRInvArray", company.getfRInvArray());
			map.put("fRPositionArray", company.getfRPositionArray());
			redisTemplate.delete(companyName+"_bizFRMsg");
			redisTemplate.opsForValue().set(companyName+"_bizFRMsg", map, 2*60*60, TimeUnit.SECONDS);//2小时
		}
		
		Map<String, Object> fRInvPage = (Map<String, Object>)request.getSession().getAttribute("fRInvPage");
		if(fRInvPage==null){
			if(pageSize==null){
				pageSize = Constant.DETAULT_PAGE_SIZE;
			}
			fRInvPage = bizService.initPage(curPage,pageSize, fRInvArray);
			
		}else{
			fRInvPage.put("curPage",curPage);
		}
		if(fRInvArray!=null&&fRInvArray.size()>0){
			request.getSession().setAttribute("fRInvPage", fRInvPage);
			model.addAttribute("fRInvArray", fRInvArray);		
		}
		model.addAttribute("companyName", companyName);
		model.addAttribute("deep", deep);
		return "/modules/monitor/monitorCompany/fRInv";
	}
	
	@RequestMapping("/tofRPositionNextPage.do")
	public String tofRPositionNext(String companyName,Integer curPage ,Integer pageSize ,String deep,Model model,HttpServletRequest request,HttpServletResponse response){
		try {
			companyName = URLDecoder.decode(companyName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		ArrayList<HashMap<String,String>> fRPositionArray = null;
		Map<String,Object> map = (Map<String,Object>) redisTemplate.opsForValue().get(companyName+"_bizFRMsg");
		if(map!=null){
			fRPositionArray = (ArrayList<HashMap<String,String>>)map.get("fRPositionArray");
		}else{
			BizMsg company = bizService.getOriginalGongShangMsg(companyName);
			fRPositionArray = company.getfRInvArray();
			map.put("fRInvArray", company.getfRInvArray());
			map.put("fRPositionArray", company.getfRPositionArray());
			redisTemplate.delete(companyName+"_bizFRMsg");
			redisTemplate.opsForValue().set(companyName+"_bizFRMsg", map, 2*60*60, TimeUnit.SECONDS);//2小时
		}
		
		Map<String, Object> fRPositionPage = (Map<String, Object>)request.getSession().getAttribute("fRPositionPage");
		if(fRPositionPage==null){
			if(pageSize==null){
				pageSize = Constant.DETAULT_PAGE_SIZE;
			}
			fRPositionPage = bizService.initPage(curPage,pageSize, fRPositionArray);
			
		}else{
			fRPositionPage.put("curPage",curPage);
		}
		if(fRPositionArray!=null&&fRPositionArray.size()>0){
			request.getSession().setAttribute("fRPositionPage", fRPositionPage);
			model.addAttribute("fRPositionArray", fRPositionArray);		
		}
		model.addAttribute("companyName", companyName);
		model.addAttribute("deep", deep);
		return "/modules/monitor/monitorCompany/fRPosition";
	}
}

