/**   
* @Title: MonitorRelationPersonRiskAnalysisServiceImpl.java 
* @Package com.srd.ljzd.serviceImpl.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年5月12日 下午2:24:07 
* @version V1.0   
*/
package com.srd.ljzd.serviceImpl.monitor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srd.ljzd.dao.monitor.MonitorRelationPersonDao;
import com.srd.ljzd.dao.monitor.MonitorRelationPersonRiskAnalysisDao;
import com.srd.ljzd.entity.monitor.MonitorRelationPerson;
import com.srd.ljzd.entity.monitor.MonitorRelationPersonRiskAnalysis;
import com.srd.ljzd.service.monitor.MonitorRelationPersonRiskAnalysisService;
import com.srd.ljzd.util.StringUtils;

/** 
 * @ClassName: MonitorRelationPersonRiskAnalysisServiceImpl
 * @Description: 动态监控关系个人风险分析Service实现类
 * @author shiyong
 * @date 2017年5月12日 下午2:24:07
 *  
 */
@Service
public class MonitorRelationPersonRiskAnalysisServiceImpl implements MonitorRelationPersonRiskAnalysisService{

	@Autowired
	private MonitorRelationPersonDao monitorRelationPersonDao;
	
	@Autowired
	private MonitorRelationPersonRiskAnalysisDao monitorRelationPersonRiskAnalysisDao;
	
	SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public List<Map<String, Object>> getMonitorRelationPersonRiskAnalysisList(String monitorId) {
		
		List<Map<String, Object>> riskAnalysisList = new ArrayList<Map<String,Object>>();
		
		Map<String, Object> map = null;
		
		List<MonitorRelationPerson> relationPersonList = monitorRelationPersonDao.queryMonitorRelationPersonListByMonitorId(monitorId, new Date());
		
		String relationPersonMonitorId = "";
		
		List<MonitorRelationPersonRiskAnalysis> monitorRelationPersonRiskAnalysisList = null;
		
		Map<String, Object> shesuMap = null;
		
		List<MonitorRelationPersonRiskAnalysis> shesuList = null;
		
		int eventNum = 0;
		String eventLevel = "";
		
		for (MonitorRelationPerson monitorRelationPerson : relationPersonList) {
			//初始化参数
			map = new HashMap<String, Object>();
			relationPersonMonitorId = "";
			monitorRelationPersonRiskAnalysisList = null;
			shesuMap = new HashMap<String, Object>();
			shesuList = new ArrayList<MonitorRelationPersonRiskAnalysis>();
			
			relationPersonMonitorId = monitorRelationPerson.getId();
			
			monitorRelationPersonRiskAnalysisList = monitorRelationPersonRiskAnalysisDao.queryMonitorRelationPersonRiskAnalysisListByMonitorId(relationPersonMonitorId);
			
			//开庭公告
			for (MonitorRelationPersonRiskAnalysis monitorRelationPersonRiskAnalysis : monitorRelationPersonRiskAnalysisList) {
				if("30".equals(monitorRelationPersonRiskAnalysis.getEventSubType())){
					shesuList.add(monitorRelationPersonRiskAnalysis);
					break;
				}
			}
			
			//司法案件
			for (MonitorRelationPersonRiskAnalysis monitorRelationPersonRiskAnalysis : monitorRelationPersonRiskAnalysisList) {
				if("31".equals(monitorRelationPersonRiskAnalysis.getEventSubType())){
					shesuList.add(monitorRelationPersonRiskAnalysis);
					break;
				}
			}
			
			//被执行人
			for (MonitorRelationPersonRiskAnalysis monitorRelationPersonRiskAnalysis : monitorRelationPersonRiskAnalysisList) {
				if("35".equals(monitorRelationPersonRiskAnalysis.getEventSubType())){
					shesuList.add(monitorRelationPersonRiskAnalysis);
					break;
				}
			}
			
			//失信被执行人
			for (MonitorRelationPersonRiskAnalysis monitorRelationPersonRiskAnalysis : monitorRelationPersonRiskAnalysisList) {
				if("36".equals(monitorRelationPersonRiskAnalysis.getEventSubType())){
					shesuList.add(monitorRelationPersonRiskAnalysis);
					break;
				}
			}
			
			if(shesuList.size()>0){
				eventNum = 0;
				eventLevel = "";
				
				for (MonitorRelationPersonRiskAnalysis monitorRelationPersonRiskAnalysis : shesuList) {
					eventNum += monitorRelationPersonRiskAnalysis.getEventNum();
					
					if(StringUtils.isEmpty(eventLevel)){
						eventLevel = monitorRelationPersonRiskAnalysis.getEventLevel();
					}else{
						if(Integer.valueOf(eventLevel) < Integer.valueOf(monitorRelationPersonRiskAnalysis.getEventLevel())){
							eventLevel = monitorRelationPersonRiskAnalysis.getEventLevel();
						}
					}
				}
				
				shesuMap.put("eventNum", eventNum);
				shesuMap.put("eventLevel", eventLevel);
				shesuMap.put("riskAnalysisList", shesuList);
			}else{
				shesuMap.put("eventNum", 0);
				shesuMap.put("eventLevel", "");
				shesuMap.put("riskAnalysisList", shesuList);
			}
			
			map.put("personName", monitorRelationPerson.getName());
			map.put("infoShowDate", sdfDate.format(monitorRelationPerson.getInfoShowDate()));
			map.put("shesu", shesuMap);
			
			riskAnalysisList.add(map);
		}
		
		return riskAnalysisList;
	}
	
}
