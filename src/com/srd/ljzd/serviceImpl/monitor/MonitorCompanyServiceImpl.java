package com.srd.ljzd.serviceImpl.monitor;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srd.ljzd.dao.monitor.MonitorCompanyDao;
import com.srd.ljzd.dao.monitor.MonitorCompanyEventDao;
import com.srd.ljzd.dao.monitor.MonitorGroupCompanyDao;
import com.srd.ljzd.dao.monitor.MonitorGroupDao;
import com.srd.ljzd.dao.monitor.MonitorRelationCompanyDao;
import com.srd.ljzd.dao.monitor.MonitorRelationPersonDao;
import com.srd.ljzd.dto.monitor.MonitorCompanyDTO;
import com.srd.ljzd.entity.client.ClientAccount;
import com.srd.ljzd.entity.common.ResultBean;
import com.srd.ljzd.entity.monitor.MonitorCompany;
import com.srd.ljzd.entity.monitor.MonitorCompanyDistribution;
import com.srd.ljzd.entity.monitor.MonitorGroup;
import com.srd.ljzd.entity.monitor.MonitorGroupCompany;
import com.srd.ljzd.service.client.ClientAccountService;
import com.srd.ljzd.service.company.CompanyInfoService;
import com.srd.ljzd.service.log.OperationLogService;
import com.srd.ljzd.service.monitor.MonitorCompanyService;
import com.srd.ljzd.service.monitor.MonitorRelationCompanyService;
import com.srd.ljzd.service.monitor.MonitorRelationPersonService;
import com.srd.ljzd.service.monitor.MonitorUserConfigureService;
import com.srd.ljzd.util.DateUtils;
import com.srd.ljzd.util.LoggerUtils;
import com.srd.ljzd.util.Page;
import com.srd.ljzd.util.StringUtils;

/** 
* @ClassName: MonitorCompanyServiceImpl
* @Description: 动态监控企业Service实现类
* @author shiyong
* @date 2017年5月25日 下午4:06:49
*  
*/
@Service
public class MonitorCompanyServiceImpl implements MonitorCompanyService{

	@Autowired
	private MonitorCompanyDao monitorCompanyDao;
	
	@Autowired
	private MonitorGroupCompanyDao monitorGroupCompanyDao;
	
	@Autowired
	private MonitorGroupDao monitorGroupDao;
	
	@Autowired
	private MonitorCompanyEventDao monitorComEventDao;
	
	@Autowired
	private MonitorRelationCompanyService monitorRelComService;
	
	@Autowired
	private MonitorRelationPersonService monitorRelPersonService;
	
	@Autowired
	private MonitorRelationCompanyDao monitorRelationCompanyDao;
	
	@Autowired
	private MonitorRelationPersonDao monitorRelationPersonDao;
	
	@Autowired
	private CompanyInfoService companyInfoService;
	
	@Autowired
	private ClientAccountService clientAccountService;
	
	@Autowired
	private MonitorUserConfigureService monitorUserConfigureService;
	
	@Autowired
	private OperationLogService operationLogService;
	
	SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public List<MonitorCompany> queryRecentMonitorCompanyList(String accountId, int num) {
		return monitorCompanyDao.queryRecentMonitorCompanyList(accountId, num);
	}
	
	@Override
	public ResultBean saveMonitorCompany(String accountId, String companyId,String companyName, String province, String[] groupIds, String renameStatus) {
		ResultBean result = new ResultBean();
		
		boolean flag = false;
		
		int count = 0;
		
		//判断该账号是否还有监控额度
		result = clientAccountService.canMonitorCompany(accountId);
		
		MonitorCompany monitorCompany = null;
		
		if(!"1".equals(result.getResultCode())){
			if(StringUtils.isNotEmpty(province)){
				//保存动态监控公司信息
				monitorCompany = new MonitorCompany();
				monitorCompany.setMonitorID(UUID.randomUUID().toString());
				
				ClientAccount clientAccount = new ClientAccount();
				clientAccount.setAccountId(accountId);
				
				monitorCompany.setClientAccount(clientAccount);
				monitorCompany.setCompanyId(companyId);
				monitorCompany.setCompanyName(companyName);
				
				if("工商总局".equals(province)){
					monitorCompany.setProvince("北京市");
				}else{
					monitorCompany.setProvince(province);
				}
				
				monitorCompany.setAreaName(province);
				monitorCompany.setRiskSituation("");
				monitorCompany.setCustomRiskFlag("0");
				monitorCompany.setCustomRiskSituation("");
				monitorCompany.setRiskStatus("");
				monitorCompany.setRiskLabel("");
				monitorCompany.setCustomRiskLabel("");
				monitorCompany.setNewEventFlag("");
				monitorCompany.setEventLevel("");
				monitorCompany.setNormalEventNum(0);
				monitorCompany.setAttentionEventNum(0);
				monitorCompany.setWarnEventNum(0);
				monitorCompany.setRiskResult("");
				monitorCompany.setCustomRiskResult("");
				monitorCompany.setRenameStatus(renameStatus);
				
				Calendar cal = Calendar.getInstance();
				monitorCompany.setMonitorDate(cal.getTime());
				monitorCompany.setCreateTime(cal.getTime());
				
				cal.add(Calendar.YEAR, -1);
				monitorCompany.setInfoShowDate(cal.getTime());
				
				if(groupIds.length > 1){
					monitorCompany.setGroupStatus("1");
				}else{
					monitorCompany.setGroupStatus("0");
				}
				
				monitorCompany.setDeleteFlag("0");
				
				flag = monitorCompanyDao.save(monitorCompany);
				
				if(flag){
					count++;
				}
				
				//保存动态监控公司和分组的关联关系
				for(int i=0;i<groupIds.length;i++){
					MonitorGroup monitorGroup = monitorGroupDao.get(groupIds[i]);
					
					MonitorGroupCompany monitorGroupCompany = new MonitorGroupCompany();
					monitorGroupCompany.setMonitorCompany(monitorCompany);
					monitorGroupCompany.setMonitorGroup(monitorGroup);
					monitorGroupCompany.setCreateTime(new Date());
					
					flag = monitorGroupCompanyDao.save(monitorGroupCompany);
					
					if(flag){
						count++;
					}
				}
				
				if(count == (1 + groupIds.length)){
					if("2".equals(result.getResultCode())){
						result.setResultCode("0");
						result.setResultMsg("加入成功！监控详情将于次日展示，" + result.getResultMsg());
					}else{
						result.setResultCode("0");
						result.setResultMsg("加入成功！监控详情将于次日展示");
					}
					
					//加入成功后，减去用户剩余家数
					clientAccountService.reduceMonitorNum(accountId);
				}else{
					result.setResultCode("1");
					result.setResultMsg("加入动态监控失败！");
				}
			}else{
				result.setResultCode("1");
				result.setResultMsg("该企业信息不全，无法加入动态监控！");
			}
		}
		return result;
	}

	@Override
	public ResultBean deleteMonitorCompany(MonitorCompany monitorCompany) {
		ResultBean result = new ResultBean();
		
		monitorCompany = monitorCompanyDao.getMonitorCompany(monitorCompany);
		
		boolean flag = false;
		
		if(monitorCompany!=null){
			
			//删除公司和分组关系
			flag = monitorGroupCompanyDao.deleteByMonitorCompany(monitorCompany);
			
			//删除关系企业和关系自然人，逻辑删除
			flag = monitorRelationCompanyDao.deleteMonitorRelationCompanyByMonitorId(monitorCompany.getMonitorID());
			flag = monitorRelationPersonDao.deleteMonitorRelationPersonByMonitorId(monitorCompany.getMonitorID());
			
			//删除主体企业，逻辑删除
			monitorCompany.setDeleteFlag("1");
			flag = monitorCompanyDao.update(monitorCompany);
		}
		
		if(flag){
			result.setResultCode("0");
			result.setResultMsg("取消动态监控成功！");
			//保存用户使用记录
			operationLogService.save(monitorCompany.getClientAccount().getAccountId(),monitorCompany.getClientAccount().getAccountName()
	        		,"取消动态监控","企业名称 "+monitorCompany.getCompanyName(),DateUtils.getCurrentDateStr(DateUtils.DEFAULT_PATTERN));
		}else{
			result.setResultCode("1");
			result.setResultMsg("取消动态监控失败！");
		}
		
		return result;
	}
	
	@Override
	public ResultBean deleteMonitorCompanyBatch(List<String> monitorIdList) {
		ResultBean result = new ResultBean();
		
		boolean flag = false;
		
		for(String monitorId : monitorIdList){
			
			//删除公司和分组关系
			flag = monitorGroupCompanyDao.deleteMonitorGroupCompanyByMonitorId(monitorId);
			
			//删除关系企业和关系自然人
			flag = monitorRelationCompanyDao.deleteMonitorRelationCompanyByMonitorId(monitorId);
			
			if(!flag){
				LoggerUtils.error("取消监控企业失败， 删除关系企业报错， monitorId为" + monitorId);
				
				break;
			}
			
			flag = monitorRelationPersonDao.deleteMonitorRelationPersonByMonitorId(monitorId);
			
			if(!flag){
				LoggerUtils.error("取消监控企业失败，删除关系自然人报错， monitorId为" + monitorId);
				
				break;
			}
			
			//逻辑删除监控企业
			flag = monitorCompanyDao.deleteMonitorCompanyById(monitorId);
			
			if(!flag){
				LoggerUtils.error("取消监控企业失败，删除主体企业报错， monitorId为" + monitorId);
				
				break;
			}
			//保存用户使用记录
			MonitorCompany monitorCompany = monitorCompanyDao.get(monitorId);
			operationLogService.save(monitorCompany.getClientAccount().getAccountId(),monitorCompany.getClientAccount().getAccountName()
	        		,"取消动态监控","企业名称 "+monitorCompany.getCompanyName(),DateUtils.getCurrentDateStr(DateUtils.DEFAULT_PATTERN));
		}
		
		if(flag){
			result.setResultCode("0");
			result.setResultMsg("取消监控企业成功！");
		}else{
			result.setResultCode("1");
			result.setResultMsg("取消监控企业失败！");
		}
		
		return result;
	}

	@Override
	public boolean isMonitorOfCompany(MonitorCompany monitorCompany) {
		
		boolean result = false;
		
		monitorCompany = monitorCompanyDao.getMonitorCompany(monitorCompany);
		
		if(monitorCompany != null){
			result = true;
		}
		
		return result;
	}
	
	@Override
	public boolean isMonitorOfCompany(String accountId,String companyName) {
		
		boolean result = false;
		
		MonitorCompany  monitorCompany = monitorCompanyDao.getMonitorCompany(accountId,companyName);
		
		if(monitorCompany != null){
			result = true;
		}
		
		return result;
	}
	
	@Override
	public List<MonitorCompany> queryTodayMonitorCompany(String accountId) {
		return monitorCompanyDao.queryTodayMonitorCompany(accountId);
	}
	
	@Override
	public int queryTodayMonitorCompanyNum(List<ClientAccount> clientAccountList) {
		
		return monitorCompanyDao.queryTodayMonitorCompanyNum(clientAccountList);
	}
	
	@Override
	public int queryMonitorCompanyNum(List<ClientAccount> clientAccountList) {
		
		return monitorCompanyDao.queryMonitorCompanyNum(clientAccountList);
	}
	
	@Override
	public int queryMonitorCompanyNumByAccountId(String accountId) {
		
		return monitorCompanyDao.queryMonitorCompanyNumByAccountId(accountId);
	}

	@Override
	public MonitorCompany queryMonitorCompanyById(String monitorId) {
		return monitorCompanyDao.get(monitorId);
	}
	

	@Override
	public Page<MonitorCompany> getMonitorGroupComInfo(String sysAccount,
			String groupName, int currentPageNo, int pageSize) {
		return monitorCompanyDao.getMonitorGroupComInfo(sysAccount, groupName, currentPageNo, pageSize);
	}

	@Override
	public List<MonitorCompany> getMonitorComListByAccId(String accountId,
			List<String> nameList) {
		return monitorCompanyDao.getMonitorComListByAccId(accountId, nameList);
	}

	@Override
	public MonitorCompany getMonitorCompany(String accountId, String companyName) {
		return monitorCompanyDao.getMonitorCompany(accountId, companyName);
	}

	@Override
	public Page<MonitorCompany> findTodayMonitorCompanyPage(MonitorCompany monitorCompany, int currentPageNo, int pageSize) {
		
		return monitorCompanyDao.findTodayMonitorCompanyPage(monitorCompany, currentPageNo, pageSize);
	}
	
	@Override
	public Page<MonitorCompany> findMonitorCompanyPage(MonitorCompanyDTO monitorCompanyRiskDTO, int currentPageNo, int pageSize,String sortAttr,boolean isDesc) {
		
		Page<MonitorCompany> monitorCompanyRiskDTOPage = monitorCompanyDao.findMonitorCompanyPage(monitorCompanyRiskDTO, currentPageNo, pageSize,sortAttr,isDesc);
		
		return monitorCompanyRiskDTOPage;
	}
	
	@Override
	public List<MonitorCompany> getMonitorCompanyList(MonitorCompanyDTO monitorCompanyRiskDTO) {
		
		List<MonitorCompany> monitorCompanyList = monitorCompanyDao.getMonitorCompanyList(monitorCompanyRiskDTO);
		
		return monitorCompanyList;
	}

	@Override
	public int getMaxSortOfGroup(String monitorId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean updateGroupStatus(String monitorId, String groupStatus) {
		return monitorCompanyDao.updateGroupStatus(monitorId, groupStatus);
	}

	@Override
	public List<MonitorCompany> getGroupCompanyList(String groupId) {
	
		return monitorCompanyDao.getGroupCompanyList(groupId);
	}
	
	@Override
	public Map<String, Object> queryMonitorCompanySituation(String accountId) {
		
		Map<String, Object> monitorCompanySituationMap = monitorCompanyDao.queryMonitorCompanySituation(accountId);
		
		return monitorCompanySituationMap;
	}
	
	@Override
	public Map<String, Object> queryMonitorCompanySituation(List<ClientAccount> clientAccountList) {
		
		return monitorCompanyDao.queryMonitorCompanySituation(clientAccountList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MonitorCompanyDistribution> queryMonitorCompanyDistributionList(String accountId) {
		List<MonitorCompanyDistribution> monitorCompanyDistributionList = null;
		
		monitorCompanyDistributionList = monitorCompanyDao.queryMonitorCompanyDistributionList(accountId);
		
		if(monitorCompanyDistributionList.size() > 0){
			
			String province = "";
			
			for (MonitorCompanyDistribution monitorCompanyDistribution : monitorCompanyDistributionList) {
				province = monitorCompanyDistribution.getAreaName();
				
				province = province.replaceAll("省", "");
				province = province.replaceAll("市", "");
				province = province.replaceAll("壮族", "");
				province = province.replaceAll("回族", "");
				province = province.replaceAll("维吾尔", "");
				province = province.replaceAll("自治区", "");
				
				monitorCompanyDistribution.setAreaName(province);
				monitorCompanyDistribution.setHighRiskCompanyPercent((double)(Math.round(monitorCompanyDistribution.getHighRiskCompanyNum()*100/monitorCompanyDistribution.getTotalCompanyNum())));
			}
			
			Collections.sort(monitorCompanyDistributionList, new SortByHighRiskCompanyPercent());
		}
		
		return monitorCompanyDistributionList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MonitorCompanyDistribution> queryMonitorCompanyDistributionList(List<ClientAccount> clientAccountList) {
		
		List<MonitorCompanyDistribution> monitorCompanyDistributionList = null;
		
		monitorCompanyDistributionList = monitorCompanyDao.queryMonitorCompanyDistributionList(clientAccountList);
		
		if(monitorCompanyDistributionList.size() > 0){
			
			String province = "";
			
			for (MonitorCompanyDistribution monitorCompanyDistribution : monitorCompanyDistributionList) {
				province = monitorCompanyDistribution.getAreaName();
				
				province = province.replaceAll("省", "");
				province = province.replaceAll("市", "");
				province = province.replaceAll("壮族", "");
				province = province.replaceAll("回族", "");
				province = province.replaceAll("维吾尔", "");
				province = province.replaceAll("自治区", "");
				
				monitorCompanyDistribution.setAreaName(province);
				monitorCompanyDistribution.setHighRiskCompanyPercent((double)(Math.round(monitorCompanyDistribution.getHighRiskCompanyNum()*100/monitorCompanyDistribution.getTotalCompanyNum())));
			}
			
			Collections.sort(monitorCompanyDistributionList, new SortByHighRiskCompanyPercent());
		}
		
		return monitorCompanyDistributionList;
	}
	
	@SuppressWarnings("rawtypes")
	class SortByHighRiskCompanyPercent implements Comparator {
    	public int compare(Object o1, Object o2) {
    		MonitorCompanyDistribution m1 = (MonitorCompanyDistribution) o1;
    		MonitorCompanyDistribution m2 = (MonitorCompanyDistribution) o2;
    		
    		return m2.getHighRiskCompanyPercent().compareTo(m1.getHighRiskCompanyPercent());
    	}
	}

	@Override
	public ResultBean updateMonitorCompany(MonitorCompany monitorCompany) {
		
		ResultBean result = new ResultBean();
		
		boolean flag = monitorCompanyDao.update(monitorCompany);
		
		if(flag){
			result.setResultCode("0");
			result.setResultMsg("更新动态监控企业信息成功！");
		}else{
			result.setResultCode("1");
			result.setResultCode("更新动态监控企业信息失败！");
		}
		
		return result;
	}

	@Override
	public List<MonitorCompany> queryNewEventCompanyList(String accountId, int num) {
		
		return monitorCompanyDao.queryNewEventCompanyList(accountId, num);
	}

	@Override
	public ResultBean addBlacklistFlag(String accountId, String companyName) {
		ResultBean result = new ResultBean();
		
		MonitorCompany monitorCompany = monitorCompanyDao.getMonitorCompany(accountId, companyName);
		
		if(null != monitorCompany){
			String riskLabel = monitorCompany.getRiskLabel();
			
			if(riskLabel.contains("失信")){
				result.setResultCode("0");
				result.setResultMsg("已被认定为失信企业！");
			}else{
				if(StringUtils.isEmpty(riskLabel)){
					monitorCompany.setRiskLabel("失信");
				}else{
					monitorCompany.setRiskLabel(riskLabel + ",失信");
				}
				
				boolean flag = monitorCompanyDao.update(monitorCompany);
				
				if(flag){
					result.setResultCode("0");
					result.setResultMsg("添加企业失信标签成功！");
				}else{
					result.setResultCode("1");
					result.setResultMsg("添加企业失信标签失败！");
				}
			}
		}else{
			result.setResultCode("1");
			result.setResultMsg("该企业未被监控！");
		}
		
		return result;
	}

	@Override
	public ResultBean deleteBlacklistFlag(String accountId, String companyName) {
		ResultBean result = new ResultBean();
		
		MonitorCompany monitorCompany = monitorCompanyDao.getMonitorCompany(accountId, companyName);
		
		if(null != monitorCompany){
			String riskLabel = monitorCompany.getRiskLabel();
			
			if(riskLabel.contains("失信")){
				riskLabel = riskLabel.replace("失信", "");
				
				if(StringUtils.isNotEmpty(riskLabel)){
					riskLabel = riskLabel.substring(0, riskLabel.length()-1);
				}
				
				monitorCompany.setRiskLabel(riskLabel);
				
				boolean flag = monitorCompanyDao.update(monitorCompany);
				
				if(flag){
					result.setResultCode("0");
					result.setResultMsg("删除企业失信标签成功！");
				}else{
					result.setResultCode("1");
					result.setResultMsg("删除企业失信标签失败！");
				}
			}
		}else{
			result.setResultCode("1");
			result.setResultMsg("该企业未被监控！");
		}
		
		return result;
	}

	@Override
	public List<Map<String, Object>> getRiskSituationList(List<ClientAccount> clientAccountList) {
		
		return monitorCompanyDao.getRiskSituationList(clientAccountList);
	}

	@Override
	public Map<String, Object> getSelfRiskSituation(String accountId) {
		
		return monitorCompanyDao.getSelfRiskSituation(accountId);
	}

	@Override
	public Map<String, Object> getRiskSituationByParent(String parentId) {
		
		List<ClientAccount> clientAccountList = clientAccountService.getAllClientAccountListByParentId(parentId);
		
		Map<String, Object> riskSituation = monitorCompanyDao.getRiskSituationByParent(clientAccountList);
		
		String isHaveChild = "0";
		
		for (ClientAccount clientAccount : clientAccountList) {
			if("2".equals(clientAccount.getType()) && !parentId.equals(clientAccount.getAccountId())){
				isHaveChild = "1";
			}
		}
		
		ClientAccount account = clientAccountService.getClientAccountInfoById(parentId);
		
		riskSituation.put("accountId", account.getAccountId());
		riskSituation.put("accountName", account.getAccountName());
		riskSituation.put("isHaveChild", isHaveChild);
		
		return riskSituation;
	}

	
}
