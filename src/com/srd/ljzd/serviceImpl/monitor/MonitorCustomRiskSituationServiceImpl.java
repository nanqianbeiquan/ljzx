package com.srd.ljzd.serviceImpl.monitor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.srd.ljzd.dao.monitor.MonitorCompanyDao;
import com.srd.ljzd.dao.monitor.MonitorCustomRiskInfoDao;
import com.srd.ljzd.dao.monitor.MonitorCustomRiskSituationDao;
import com.srd.ljzd.entity.client.ClientAccount;
import com.srd.ljzd.entity.common.ResultBean;
import com.srd.ljzd.entity.monitor.MonitorCompany;
import com.srd.ljzd.entity.monitor.MonitorCustomRiskInfo;
import com.srd.ljzd.entity.monitor.MonitorCustomRiskSituation;
import com.srd.ljzd.service.log.OperationLogService;
import com.srd.ljzd.service.monitor.MonitorCustomRiskSituationService;
import com.srd.ljzd.util.DateUtils;
@Service("monitorCustomRiskSituationService")
public class MonitorCustomRiskSituationServiceImpl implements
		MonitorCustomRiskSituationService {
	@Autowired
	MonitorCustomRiskSituationDao monitorCustomRiskSituationDao;
	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	@Autowired
	private MonitorCompanyDao monitorCompanyDao;
	@Autowired
	MonitorCustomRiskInfoDao monitorCustomRiskInfoDao;
	@Autowired
	private OperationLogService operationLogService;
	
	@Override
	public MonitorCustomRiskSituation getById(String customRiskId) {
		return monitorCustomRiskSituationDao.get(customRiskId);
	}
	
	@Override
	public ResultBean addCustomRisk(String riskSituation, String oldRiskSituation,
			MonitorCompany company, Set<MonitorCustomRiskInfo> riskInfoSet) {
		MonitorCustomRiskSituation customRisk = null;
		customRisk = new MonitorCustomRiskSituation();
		customRisk.setCreateTime(Calendar.getInstance().getTime());
		customRisk.setLastModifiedTime(Calendar.getInstance().getTime());
		customRisk.setDeleteFlag("0");
		customRisk.setRiskSituation(riskSituation);
		customRisk.setOldRiskSituation(oldRiskSituation);
		customRisk.setMonitorCompany(company);
		
		if(riskInfoSet!=null&&riskInfoSet.size()>0){
			Map<String, Object> riskTypeMap = null;
			for(MonitorCustomRiskInfo riskInfo : riskInfoSet){
				riskInfo.setMonitorCustomRiskSituation(customRisk);
				riskInfo.setCreateTime(Calendar.getInstance().getTime());
			}
		}
		customRisk.setRiskInfoSet(riskInfoSet);
		
		//更新monitorCompany
		company.setCustomRiskFlag("1");
		company.setCustomRiskSituation(riskSituation);
		
		if(Integer.valueOf(riskSituation) > Integer.valueOf(oldRiskSituation)){
			company.setRiskStatus("1");
		}else if(Integer.valueOf(riskSituation) < Integer.valueOf(oldRiskSituation)){
			company.setRiskStatus("2");
		}
		
		String newLabel = this.buildLabel(riskInfoSet);
		company.setCustomRiskLabel(newLabel);
		
		if("3".equals(riskSituation)){
			company.setCustomRiskResult("鉴于：主体企业当前风险状况为特别预警，建议每天保持高度关注，及时采取风险规避措施。");
		}else if("2".equals(riskSituation)){
			company.setCustomRiskResult("鉴于：主体企业当前风险状况为一般预警，建议每周保持高度关注，评估后采取相应措施。");
		}else if("1".equals(riskSituation)){
			company.setCustomRiskResult("鉴于：主体企业当前风险状况为关注，建议每月保持密切关注，适时采取措施。");
		}else{
			company.setCustomRiskResult("鉴于：主体企业当前风险状况为正常，建议持续监控。");
		}
		
		boolean flag = monitorCustomRiskSituationDao.save(customRisk);
		
		ResultBean result = new ResultBean();
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("riskSuggestion", company.getCustomRiskResult());
		
		resultMap.put("customRiskSituation", customRisk);
		
		if(flag){
			result.setResultCode("0");
			result.setResultMsg("SUCCESS");
			result.setResultData(resultMap);
			//保存用户使用记录
			 Map<String,Object> contentMap = getRiskChangeDesc(customRisk);
			 
			operationLogService.save(company.getClientAccount().getAccountId(),company.getClientAccount().getAccountName()
	        		,"新增风险自定义"
	        		,"企业名称 "+company.getCompanyName()+"，内容："+contentMap.get("change")+"，"+contentMap.get("reason"),
	        		DateUtils.getCurrentDateStr(DateUtils.DEFAULT_PATTERN));
			
		}else{
			result.setResultCode("2");
			result.setResultMsg("EXCEPTION");
		}
		
		return result;
	}

	@Override
	public List<MonitorCustomRiskSituation> getLCustomRiskList(String monitorId) {
		List<MonitorCustomRiskSituation> list = monitorCustomRiskSituationDao.getListByMonitorId(monitorId);
		return list;
	}
	
	@Override
	public boolean update(MonitorCustomRiskSituation record) {
		return monitorCustomRiskSituationDao.update(record);
	}
	@Override
	public ResultBean update(MonitorCustomRiskSituation customRisk, String riskSituation,
			Set<MonitorCustomRiskInfo> riskInfoSet) {
		ResultBean result = new ResultBean();
		//验证是否做了修改
		boolean isModified = false;
		if(!customRisk.getRiskSituation().equals(riskSituation)){
			customRisk.setOldRiskSituation(customRisk.getRiskSituation());
			customRisk.setRiskSituation(riskSituation);
			isModified = true;
		}
		if(riskInfoSet.size()!=customRisk.getRiskInfoSet().size()
				|| !riskInfoSet.containsAll(customRisk.getRiskInfoSet())){
			isModified = true;
			//删除旧的列表
			
			for(MonitorCustomRiskInfo riskInfo : customRisk.getRiskInfoSet()){
				monitorCustomRiskInfoDao.delete(riskInfo);
			}
			//构造最新的风险详细信息列表
			for(MonitorCustomRiskInfo riskInfo : riskInfoSet){
				riskInfo.setMonitorCustomRiskSituation(customRisk);
				riskInfo.setCreateTime(Calendar.getInstance().getTime());
				riskInfo.setMonitorCustomRiskSituation(customRisk);
			}
			customRisk.setRiskInfoSet(riskInfoSet);
		}
		
		if(isModified){
			if(customRisk.getMonitorCompany()!=null){
				
				List<MonitorCustomRiskSituation>  list = this.getLCustomRiskList(customRisk.getMonitorCompany().getMonitorID());
				if(list!=null&&list.size()>0){
					//如果更新的是最后一个自定义，则同时更新monitorCompany
					if(customRisk.getRiskId().equals(list.get(0).getRiskId())){
						String newLabel = this.buildLabel(riskInfoSet);
						customRisk.getMonitorCompany().setCustomRiskLabel(newLabel);
						customRisk.getMonitorCompany().setCustomRiskSituation(riskSituation);
					}
				}
			}
			
			customRisk.setLastModifiedTime(Calendar.getInstance().getTime());
			boolean flag = monitorCustomRiskSituationDao.update(customRisk);
			if(flag){
			    
			       //保存用户使用记录
			       Map<String,Object> contentMap = getRiskChangeDesc(customRisk);
			        
			       operationLogService.save(customRisk.getMonitorCompany().getClientAccount().getAccountId(),customRisk.getMonitorCompany().getClientAccount().getAccountName()
		        		,"修改风险自定义"
		        		,"企业名称 "+customRisk.getMonitorCompany().getCompanyName()+"，内容："+contentMap.get("change")+"，"+contentMap.get("reason")
		        		,DateUtils.getCurrentDateStr(DateUtils.DEFAULT_PATTERN));
			    
				result.setResultCode("0");
				result.setResultMsg("SUCCESS");
				result.setResultData(customRisk);
			}else{
				result.setResultCode("3");
				result.setResultMsg("MAY_BE_DB_EXCEPTION");
			}
				
		}else{
			result.setResultCode("2");
			result.setResultMsg("NOT_MODIFY_ANYTHING");
			return result;
		}
		return result;
	}
	
	@Override
	public boolean deleteCustomRisk(MonitorCustomRiskSituation record) {
		MonitorCompany company = record.getMonitorCompany();
		if(company!=null){
			List<MonitorCustomRiskSituation>  list = this.getLCustomRiskList(company.getMonitorID());
			if(list!=null&&list.size()>0){
				if(record.getRiskId().equals(list.get(0).getRiskId())){//删除的是最后一个自定义
					
					if(list.size()>1){//取上一个自定义
						MonitorCustomRiskSituation lastedRecord = list.get(1);
						if(lastedRecord!=null){
							company.setCustomRiskSituation(lastedRecord.getRiskSituation());
							String newLabel = this.buildLabel(lastedRecord.getRiskInfoSet());
							company.setCustomRiskLabel(newLabel);
						}
						
					}else if(list.size()==1){
						//清空自定义
						company.setCustomRiskFlag("0");//设置为未定义
						String newLabel = this.buildLabel(null);
						company.setCustomRiskLabel(newLabel);
					}
					
				}
			}
		}
		record.setDeleteFlag("1");
		boolean flag = monitorCustomRiskSituationDao.update(record);
		if(flag){
		  //保存用户使用记录
		  Map<String,Object> contentMap = getRiskChangeDesc(record);
			 
		  operationLogService.save(company.getClientAccount().getAccountId(),company.getClientAccount().getAccountName()
	        		,"删除风险自定义"
	        		,"企业名称 "+company.getCompanyName()+"，内容："+contentMap.get("change")+"，"+contentMap.get("reason"),
	        		DateUtils.getCurrentDateStr(DateUtils.DEFAULT_PATTERN));
		}
		return flag;
	}
	
	/**
	 * 构造最新标签
	 * @param riskLabel
	 * @param customRiskCategory
	 * @return
	 */
	public String buildLabel(Set<MonitorCustomRiskInfo> riskInfoSet) {
		List<String> sourceLabelList = new ArrayList<String>();
		
		//构造自定义标签map
		Map<String, Map<String, Object>> customRiskCategory = this.getCategoryMap();
		
		if(riskInfoSet!=null&&riskInfoSet.size()>0){
			Map<String, Object> riskTypeMap = null;
			for(MonitorCustomRiskInfo riskInfo : riskInfoSet){
				//需求：其他预警 不做为风险标签，要去掉
				if(!StringUtils.isEmpty(riskInfo.getRiskType()) && !"15".equals(riskInfo.getRiskType())){
					riskTypeMap = customRiskCategory.get(riskInfo.getRiskType());
					
					if(!StringUtils.isEmpty(riskTypeMap)){
						riskTypeMap.put("isSelect", "true");
					}
				}
			}
		}
		
		//更新标签
		for(Map<String, Object> riskTypeMap : customRiskCategory.values()){
			if("true".equals(riskTypeMap.get("isSelect"))){//选择了该种类
				if(!sourceLabelList.contains(riskTypeMap.get("name"))){
					sourceLabelList.add((String)riskTypeMap.get("name"));
				}
			}else{
				if(sourceLabelList.contains(riskTypeMap.get("name"))){
					sourceLabelList.remove(riskTypeMap.get("name"));
				}
			}
		}
		
		StringBuffer buffer = new StringBuffer();
		int size = sourceLabelList.size();
	    for(int i=0;i<size;i++){
	    	if(i!=size-1){
	    		buffer.append(sourceLabelList.get(i)).append(",");
	    	}else{
	    		buffer.append(sourceLabelList.get(i));
	    	}
			
		}
		return buffer.toString();
	}
	
/**
 * 构造自定义风险大类
 * @return
 */
	private Map<String,Map<String,Object>> getCategoryMap(){
		
		Map<String,Map<String,Object>> target = (Map<String,Map<String,Object>>) redisTemplate.opsForValue().get("customRisk_category");
		if(target==null){
			target = new HashMap<String, Map<String,Object>>(5);
			Map<String,Object> tempItem = new HashMap<String, Object>();
			tempItem.put("isSelect", "false");
			tempItem.put("name", "经营预警");
			target.put("11", tempItem);
			
			tempItem = new HashMap<String, Object>();
			tempItem.put("isSelect", "false");
			tempItem.put("name", "信用预警");
			target.put("12", tempItem);
			
			tempItem = new HashMap<String, Object>();
			tempItem.put("isSelect", "false");
			tempItem.put("name", "财务预警");
			target.put("13", tempItem);
			
			tempItem = new HashMap<String, Object>();
			tempItem.put("isSelect", "false");
			tempItem.put("name", "高管预警");
			target.put("14", tempItem);
			
			tempItem = new HashMap<String, Object>();
			tempItem.put("isSelect", "false");
			tempItem.put("name", "其他预警");
			target.put("15", tempItem);
			
			redisTemplate.delete("customRisk_category");
			redisTemplate.opsForValue().set("customRisk_category",target, 24*60*60, TimeUnit.SECONDS);//24小时
		}
		return target;
	}
	
	@Override
	public ResultBean clearCustomRisk(MonitorCompany company) {
		ResultBean result = new ResultBean();
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if(company!=null){
			company.setCustomRiskFlag("0");//设置为未定义
			company.setCustomRiskLabel("");
			
			if(Integer.valueOf(company.getRiskSituation()) > Integer.valueOf(company.getCustomRiskSituation())){
				company.setRiskStatus("1");
			}else if(Integer.valueOf(company.getRiskSituation()) < Integer.valueOf(company.getCustomRiskSituation())){
				company.setRiskStatus("2");
			}
			
			company.setCustomRiskSituation(company.getRiskSituation());
			
			company.setCustomRiskResult(company.getRiskResult());
			
			boolean flag = monitorCompanyDao.update(company);
			
			resultMap.put("riskSuggestion", company.getRiskResult());
			
			if(flag){
			       //保存用户使用记录
			       operationLogService.save(company.getClientAccount().getAccountId(),company.getClientAccount().getAccountName()
			        		,"删除风险自定义"
			        		,"清空风险自定义 企业名称 "+company.getCompanyName(),
			        		DateUtils.getCurrentDateStr(DateUtils.DEFAULT_PATTERN));
				result.setResultCode("0");
				result.setResultMsg("SUCCESS");
				result.setResultData(resultMap);
			}else{
				result.setResultCode("2");
				result.setResultMsg("MAY_BE_DB_EXCEPTION");
			}
		}else{
			result.setResultCode("1");
			result.setResultMsg("MONITOR_COMPANY_NOT_EXIST");
		}
		
		return result;
	}

	private Map<String,Object> getRiskChangeDesc(MonitorCustomRiskSituation customRiskSituation) {
		StringBuffer targetChange = new StringBuffer("从");
		StringBuffer targetReason = new StringBuffer();
		Map<String,Object> target = new HashMap<String, Object>();
		
		if(customRiskSituation!=null){
			targetChange.append("“")
			 .append(this.getRiskSituationDesc(customRiskSituation.getOldRiskSituation()))
			 .append("”改为“")
			 .append(this.getRiskSituationDesc(customRiskSituation.getRiskSituation()))
			 .append("”");
			
			Set<MonitorCustomRiskInfo> riskInfoSet = customRiskSituation.getRiskInfoSet();
			if(riskInfoSet!=null){
				Map<String,Object> cateMap = new HashMap<String, Object>();
				for(MonitorCustomRiskInfo record : riskInfoSet){
					if(cateMap.containsKey(record.getRiskType())){
						cateMap.put(record.getRiskType()
								,((StringBuffer)cateMap.get(record.getRiskType())).append("，").append(record.getRiskContent()));
					}else{
						cateMap.put(record.getRiskType(), new StringBuffer(record.getRiskContent()));
					}
				}
				
				//经营预警
				if(cateMap.containsKey("11")){
					targetReason.append("经营预警：").append(cateMap.get("11"));
				}
				if(cateMap.containsKey("12")){
					if(targetReason.length()>0){
						targetReason.append("\n");
					}
					targetReason.append("信用预警：").append(cateMap.get("12"));
				}
				if(cateMap.containsKey("13")){
					if(targetReason.length()>0){
						targetReason.append("\n");
					}
					targetReason.append("财务预警：").append(cateMap.get("13"));
				}
				if(cateMap.containsKey("14")){
					if(targetReason.length()>0){
						targetReason.append("\n");
					}
					targetReason.append("高管预警：").append(cateMap.get("14"));
				}
				if(cateMap.containsKey("15")){
					if(targetReason.length()>0){
						targetReason.append("\n");
					}
					targetReason.append("补充说明：").append(cateMap.get("15"));
				}
			}
			target.put("date", DateUtils.getLocalStrNew(null,customRiskSituation.getLastModifiedTime()) );
		}else{
			targetChange = new StringBuffer("无");
			targetReason = new StringBuffer("无");
		}
		target.put("change", targetChange.toString());
		target.put("reason", targetReason.toString());
		return target;
	}
	private String getRiskSituationDesc(String riskSituation){
		String target = null;
		switch(riskSituation){
			case "0":
				target = "正常";
				break;
			case "1":
				target = "关注";
				break;
			case "2":
				target = "一般预警";
				break;
			case "3":
				target = "特别预警";
				break;
			default : 
				target = "正常";
				break;
	    }
		return target;
	}
}
