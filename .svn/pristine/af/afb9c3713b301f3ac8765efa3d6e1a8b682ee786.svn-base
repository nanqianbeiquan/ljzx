/**   
* @Title: IndexController.java 
* @Package com.srd.ljzd.controller.system 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年4月13日 上午11:52:43 
* @version V1.0   
*/
package com.srd.ljzd.controller.index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.srd.ljzd.controller.base.BaseController;
import com.srd.ljzd.entity.client.ClientAccount;
import com.srd.ljzd.entity.monitor.MonitorCompany;
import com.srd.ljzd.entity.monitor.MonitorCompanyDistribution;
import com.srd.ljzd.entity.monitor.MonitorGroup;
import com.srd.ljzd.service.blacklist.BlacklistCompanyService;
import com.srd.ljzd.service.client.ClientAccountService;
import com.srd.ljzd.service.info.InfoMessageAccountService;
import com.srd.ljzd.service.monitor.MonitorCompanyDistributionService;
import com.srd.ljzd.service.monitor.MonitorCompanyService;
import com.srd.ljzd.service.monitor.MonitorGroupService;
import com.srd.ljzd.service.monitor.MonitorRelationCompanyService;
import com.srd.ljzd.service.monitor.MonitorUserConfigureService;
import com.srd.ljzd.util.StringUtils;


/** 
 * @ClassName: IndexController
 * @Description: 首页管理Controller
 * @author shiyong
 * @date 2016年4月13日 上午11:52:43
 *  
 */
@Controller
public class IndexController extends BaseController {
	
	@Autowired
	private ClientAccountService clientAccountService;
	
	@Autowired
	private MonitorGroupService monitorGroupService;
	
	@Autowired
	private MonitorCompanyDistributionService monitorCompanyDistributionService;
	
	@Autowired
	private MonitorCompanyService monitorCompanyService;
	
	@Autowired
	private InfoMessageAccountService infoMessageAccountService;
	
	@Autowired
	private MonitorUserConfigureService monitorUserConfigureService;
	
	@Autowired
	private MonitorRelationCompanyService monitorRelationCompanyService;
	
	@Autowired
	private BlacklistCompanyService blacklistCompanyService;
	
	/** 
	* @Title: toIndex 
	* @Description: 跳转到首页
	* @param @param request
	* @param @param response
	* @param @param model
	* @param @return 设定文件 
	* @return String 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月7日 上午11:24:48
	*/
	@RequestMapping("/index")
	public String toIndex(HttpServletRequest request, HttpServletResponse response, Model model){
		
		//先从request中获取账号ID，如果取不到，则从session中获取登录账号的ID
		String accountId = getParameterFromRequest(request, "accountId");
		
		if(StringUtils.isEmpty(accountId)){
			accountId = getAccountIdFromSession(request);
		}
		
		//保存当前账号到session中
		ClientAccount clientAccount = clientAccountService.getClientAccountById(accountId);
		
		//如果是附属账号，则查看权限同母账号
		if("3".equals(clientAccount.getType())){
			clientAccount = clientAccountService.getParentAccount(clientAccount.getAccountId());
			
			accountId = clientAccount.getAccountId();
		}
		
		request.getSession().setAttribute("currentAccount", clientAccount);
		
		//风险预览
		Map<String, Object> monitorCompanySituationMap = monitorCompanyService.queryMonitorCompanySituation(accountId);
		
		model.addAttribute("totalCompanyNum", monitorCompanySituationMap.get("totalCompanyNum"));
		model.addAttribute("highRiskCompanyNum", monitorCompanySituationMap.get("highRiskCompanyNum"));
		model.addAttribute("middleRiskCompanyNum", monitorCompanySituationMap.get("middleRiskCompanyNum"));
		model.addAttribute("lowRiskCompanyNum", monitorCompanySituationMap.get("lowRiskCompanyNum"));
		model.addAttribute("noRiskCompanyNum", monitorCompanySituationMap.get("noRiskCompanyNum"));
		model.addAttribute("newEventCompanyNum", monitorCompanySituationMap.get("newEventCompanyNum"));
		model.addAttribute("riskUpCompanyNum", monitorCompanySituationMap.get("riskUpCompanyNum"));
		model.addAttribute("riskDownCompanyNum", monitorCompanySituationMap.get("riskDownCompanyNum"));
		model.addAttribute("riskInvariantCompanyNum", monitorCompanySituationMap.get("riskInvariantCompanyNum"));
		model.addAttribute("newWarnCompanyNum", monitorCompanySituationMap.get("newWarnCompanyNum"));
		model.addAttribute("newAttentionCompanyNum", monitorCompanySituationMap.get("newAttentionCompanyNum"));
		model.addAttribute("newNormalCompanyNum", monitorCompanySituationMap.get("newNormalCompanyNum"));
		model.addAttribute("warnCompanyNum", monitorCompanySituationMap.get("warnCompanyNum"));
		model.addAttribute("attentionCompanyNum", monitorCompanySituationMap.get("attentionCompanyNum"));
		model.addAttribute("normalCompanyNum", monitorCompanySituationMap.get("normalCompanyNum"));
		
		//今日新增企业数量
		int todayMonitorCompanyNum = monitorCompanyService.queryTodayMonitorCompany(accountId).size();
		
		model.addAttribute("todayMonitorCompanyNum", todayMonitorCompanyNum);
		
		//区域分布
		List<MonitorCompanyDistribution> monitorCompanyDistributionList = monitorCompanyService.queryMonitorCompanyDistributionList(accountId);
		
		int highRiskAreaNum = 0;
		
		for(MonitorCompanyDistribution monitorCompanyDistribution : monitorCompanyDistributionList){
			if(monitorCompanyDistribution.getHighRiskCompanyNum() > 0){
				highRiskAreaNum++;
			}
		}
		
		model.addAttribute("highRiskAreaNum", highRiskAreaNum);
		
		int areaNum = 0;
		
		areaNum = monitorCompanyDistributionList.size();
		
		model.addAttribute("areaNum", areaNum);
		
		if(areaNum > 5){
			model.addAttribute("monitorCompanyDistributionList", monitorCompanyDistributionList.subList(0, 5));
		}else{
			model.addAttribute("monitorCompanyDistributionList", monitorCompanyDistributionList);
		}
		
		//显示分组信息
		List<MonitorGroup> monitorGroupList = monitorGroupService.queryMonitorGroupListByAccount(accountId);
		
		//全部分组中的公司数量
		int totalMonitorCompanyNum = 0;
		
		for (MonitorGroup monitorGroup : monitorGroupList) {
			if("全部".equals(monitorGroup.getGroupName())){
				totalMonitorCompanyNum = monitorGroup.getCompanyNum();
				
				monitorGroupList.remove(monitorGroup);
				
				break;
			}
		}
		
		model.addAttribute("totalMonitorCompanyNum", totalMonitorCompanyNum);
		model.addAttribute("monitorGroupList", monitorGroupList);
		model.addAttribute("monitorGroupNum", monitorGroupList.size());
		
		//关系企业数量
		int totalRelationCompanyNum = monitorRelationCompanyService.getRelationCompanyNum(accountId);
		
		model.addAttribute("totalRelationCompanyNum", totalRelationCompanyNum);
		
		//有事件更新的企业列表
		List<MonitorCompany> newEventCompanyList = monitorCompanyService.queryNewEventCompanyList(accountId, 5);
		
		model.addAttribute("newEventCompanyList", newEventCompanyList);
		
		//提示未读消息数量
		int unreadMessageNum = infoMessageAccountService.getUnreadMessageNum(getAccountIdFromSession(request));
		
		request.getSession().setAttribute("unreadMessageNum", unreadMessageNum);
		
		//查询黑名单数量
		int blackListNum = blacklistCompanyService.getBlacklistCompanyNumByCompany(clientAccount.getClientCompany().getCompanyId());
		
		request.getSession().setAttribute("blackListNum", blackListNum);
		
		return "/modules/index/index";
	}
	
	/** 
	* @Title: toSummaryIndex 
	* @Description: 跳转到母账号汇总首页
	* @param @param request
	* @param @param response
	* @param @param model
	* @param @return 设定文件 
	* @return String 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月9日 下午12:27:28
	*/
	@RequestMapping("/toSummaryIndex")
	public String toSummaryIndex(HttpServletRequest request, HttpServletResponse response, Model model){
		
		//先从request中获取账号ID，如果取不到，则从session中获取登录账号的ID
		String accountId = getParameterFromRequest(request, "accountId");
		
		if(StringUtils.isEmpty(accountId)){
			accountId = getAccountIdFromSession(request);
		}
		
		//账户情况
		ClientAccount motherAccount = clientAccountService.getClientAccountById(accountId);
		
		//如果是附属账号，则查看权限同母账号
		if("3".equals(motherAccount.getType())){
			motherAccount = clientAccountService.getParentAccount(motherAccount.getAccountId());
			
			accountId = motherAccount.getAccountId();
		}
		
		//保存当前账号到session中
		request.getSession().setAttribute("currentAccount", motherAccount);
		
		List<ClientAccount> childAccountList = clientAccountService.getChildAccountListByParentId(accountId);
		
		List<Map<String, Object>> childAccountMonitorQuotaList = new ArrayList<Map<String,Object>>();
		
		Map<String, Object> monitorQuotaMap = new HashMap<String, Object>();
		
		for(ClientAccount clientAccount : childAccountList){
			
			monitorQuotaMap = clientAccountService.getMonitorQuotaByParentId(clientAccount.getAccountId());
			
			childAccountMonitorQuotaList.add(monitorQuotaMap);
		}
		
		Map<String, Object> selfMonitorQuota = new HashMap<String, Object>();
		
		selfMonitorQuota.put("accountId", motherAccount.getAccountId());
		selfMonitorQuota.put("accountName", motherAccount.getAccountName());
		selfMonitorQuota.put("name", motherAccount.getName());
		selfMonitorQuota.put("monitorCompanyNum", monitorCompanyService.queryMonitorCompanyNumByAccountId(motherAccount.getAccountId()));
		
		//父账号加子账号数
		int accountNum = childAccountList.size() + 1;
		
		Map<String, Object> motherMonitorQuota = clientAccountService.getMonitorQuotaByParentId(accountId);
		
		model.addAttribute("selfMonitorQuota", selfMonitorQuota);
		model.addAttribute("motherMonitorQuota", motherMonitorQuota);
		model.addAttribute("childAccountMonitorQuotaList", childAccountMonitorQuotaList);
		model.addAttribute("accountNum", accountNum);
		
		//父账号及其下一级子账号列表
		List<ClientAccount> accountList = clientAccountService.getAllClientAccountListByParentId(accountId);
		
		//实时动态
		Map<String, Object> monitorCompanySituationMap = monitorCompanyService.queryMonitorCompanySituation(accountList);
		
		model.addAttribute("totalCompanyNum", monitorCompanySituationMap.get("totalCompanyNum"));
		model.addAttribute("highRiskCompanyNum", monitorCompanySituationMap.get("highRiskCompanyNum"));
		model.addAttribute("middleRiskCompanyNum", monitorCompanySituationMap.get("middleRiskCompanyNum"));
		model.addAttribute("lowRiskCompanyNum", monitorCompanySituationMap.get("lowRiskCompanyNum"));
		model.addAttribute("noRiskCompanyNum", monitorCompanySituationMap.get("noRiskCompanyNum"));
		model.addAttribute("newEventCompanyNum", monitorCompanySituationMap.get("newEventCompanyNum"));
		model.addAttribute("riskUpCompanyNum", monitorCompanySituationMap.get("riskUpCompanyNum"));
		model.addAttribute("riskDownCompanyNum", monitorCompanySituationMap.get("riskDownCompanyNum"));
		model.addAttribute("riskInvariantCompanyNum", monitorCompanySituationMap.get("riskInvariantCompanyNum"));
		model.addAttribute("newWarnCompanyNum", monitorCompanySituationMap.get("newWarnCompanyNum"));
		model.addAttribute("newAttentionCompanyNum", monitorCompanySituationMap.get("newAttentionCompanyNum"));
		model.addAttribute("newNormalCompanyNum", monitorCompanySituationMap.get("newNormalCompanyNum"));
		
		//今日新增企业数量
		int todayMonitorCompanyNum = monitorCompanyService.queryTodayMonitorCompanyNum(accountList);
		
		model.addAttribute("todayMonitorCompanyNum", todayMonitorCompanyNum);
		
		//关系企业数量
		int totalRelationCompanyNum = monitorRelationCompanyService.getTotalRelationCompanyNum(accountList);
		
		model.addAttribute("totalRelationCompanyNum", totalRelationCompanyNum);
		
		//提示未读消息数量
		int unreadMessageNum = infoMessageAccountService.getUnreadMessageNum(getAccountIdFromSession(request));
		
		request.getSession().setAttribute("unreadMessageNum", unreadMessageNum);
		
		//查询黑名单数量
		int blackListNum = blacklistCompanyService.getBlacklistCompanyNumByCompany(motherAccount.getClientCompany().getCompanyId());
		
		request.getSession().setAttribute("blackListNum", blackListNum);
		
		//区域分布
		List<MonitorCompanyDistribution> monitorCompanyDistributionList = monitorCompanyService.queryMonitorCompanyDistributionList(accountList);
		
		int highRiskAreaNum = 0;
		
		for(MonitorCompanyDistribution monitorCompanyDistribution : monitorCompanyDistributionList){
			if(monitorCompanyDistribution.getHighRiskCompanyNum() > 0){
				highRiskAreaNum++;
			}
		}
		
		model.addAttribute("highRiskAreaNum", highRiskAreaNum);
		
		int areaNum = 0;
		
		areaNum = monitorCompanyDistributionList.size();
		
		model.addAttribute("areaNum", areaNum);
		
		if(areaNum > 5){
			model.addAttribute("monitorCompanyDistributionList", monitorCompanyDistributionList.subList(0, 5));
		}else{
			model.addAttribute("monitorCompanyDistributionList", monitorCompanyDistributionList);
		}
		
		//各账号监控企业风险状况
		List<Map<String, Object>> riskSituationList = new ArrayList<Map<String,Object>>();
		
		Map<String, Object> riskSituation = null;
		
		for(ClientAccount clientAccount : childAccountList){
			riskSituation = monitorCompanyService.getRiskSituationByParent(clientAccount.getAccountId());
			
			if(0 != Integer.valueOf(monitorCompanySituationMap.get("highRiskCompanyNum").toString())){
				riskSituation.put("highRiskCompanyNumProportion", Integer.valueOf(riskSituation.get("highRiskCompanyNum").toString()) * 100 / Integer.valueOf(monitorCompanySituationMap.get("highRiskCompanyNum").toString()));
			}else{
				riskSituation.put("highRiskCompanyNumProportion", 0);
			}
			
			if(0 != Integer.valueOf(monitorCompanySituationMap.get("middleRiskCompanyNum").toString())){
				riskSituation.put("middleRiskCompanyNumProportion", Integer.valueOf(riskSituation.get("middleRiskCompanyNum").toString()) * 100 / Integer.valueOf(monitorCompanySituationMap.get("middleRiskCompanyNum").toString()));
			}else{
				riskSituation.put("middleRiskCompanyNumProportion", 0);
			}
			
			if(0 != Integer.valueOf(monitorCompanySituationMap.get("lowRiskCompanyNum").toString())){
				riskSituation.put("lowRiskCompanyNumProportion", Integer.valueOf(riskSituation.get("lowRiskCompanyNum").toString()) * 100 / Integer.valueOf(monitorCompanySituationMap.get("lowRiskCompanyNum").toString()));
			}else{
				riskSituation.put("lowRiskCompanyNumProportion", 0);
			}
			
			if(0 != Integer.valueOf(monitorCompanySituationMap.get("noRiskCompanyNum").toString())){
				riskSituation.put("noRiskCompanyNumProportion", Integer.valueOf(riskSituation.get("noRiskCompanyNum").toString()) * 100 / Integer.valueOf(monitorCompanySituationMap.get("noRiskCompanyNum").toString()));
			}else{
				riskSituation.put("noRiskCompanyNumProportion", 0);
			}
			
			riskSituationList.add(riskSituation);
		}
		
		model.addAttribute("riskSituationList", riskSituationList);
		
		Map<String, Object> selfRiskSituation = monitorCompanyService.getSelfRiskSituation(accountId);
		
		if(0 != Integer.valueOf(monitorCompanySituationMap.get("highRiskCompanyNum").toString())){
			selfRiskSituation.put("highRiskCompanyNumProportion", Integer.valueOf(selfRiskSituation.get("highRiskCompanyNum").toString()) * 100 / Integer.valueOf(monitorCompanySituationMap.get("highRiskCompanyNum").toString()));
		}else{
			selfRiskSituation.put("highRiskCompanyNumProportion", 0);
		}
		
		if(0 != Integer.valueOf(monitorCompanySituationMap.get("middleRiskCompanyNum").toString())){
			selfRiskSituation.put("middleRiskCompanyNumProportion", Integer.valueOf(selfRiskSituation.get("middleRiskCompanyNum").toString()) * 100 / Integer.valueOf(monitorCompanySituationMap.get("middleRiskCompanyNum").toString()));
		}else{
			selfRiskSituation.put("middleRiskCompanyNumProportion", 0);
		}
		
		if(0 != Integer.valueOf(monitorCompanySituationMap.get("lowRiskCompanyNum").toString())){
			selfRiskSituation.put("lowRiskCompanyNumProportion", Integer.valueOf(selfRiskSituation.get("lowRiskCompanyNum").toString()) * 100 / Integer.valueOf(monitorCompanySituationMap.get("lowRiskCompanyNum").toString()));
		}else{
			selfRiskSituation.put("lowRiskCompanyNumProportion", 0);
		}
		
		if(0 != Integer.valueOf(monitorCompanySituationMap.get("noRiskCompanyNum").toString())){
			selfRiskSituation.put("noRiskCompanyNumProportion", Integer.valueOf(selfRiskSituation.get("noRiskCompanyNum").toString()) * 100 / Integer.valueOf(monitorCompanySituationMap.get("noRiskCompanyNum").toString()));
		}else{
			selfRiskSituation.put("noRiskCompanyNumProportion", 0);
		}
		
		model.addAttribute("selfRiskSituation", selfRiskSituation);
		
		return "/modules/index/summary_index";
	}
	
	
	
}
