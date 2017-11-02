/**   
* @Title: MonitorRelationCompanyRiskAnalysisServiceImpl.java 
* @Package com.srd.ljzd.serviceImpl.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年5月12日 下午2:22:48 
* @version V1.0   
*/
package com.srd.ljzd.serviceImpl.monitor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srd.ljzd.dao.monitor.MonitorRelationCompanyDao;
import com.srd.ljzd.dao.monitor.MonitorRelationCompanyRiskAnalysisDao;
import com.srd.ljzd.entity.monitor.MonitorCompanyFinanceAnalysis;
import com.srd.ljzd.entity.monitor.MonitorCompanyFinanceSituation;
import com.srd.ljzd.entity.monitor.MonitorRelationCompany;
import com.srd.ljzd.entity.monitor.MonitorRelationCompanyRiskAnalysis;
import com.srd.ljzd.service.monitor.MonitorCompanyFinanceService;
import com.srd.ljzd.service.monitor.MonitorRelationCompanyRiskAnalysisService;
import com.srd.ljzd.util.StringUtils;

/** 
 * @ClassName: MonitorRelationCompanyRiskAnalysisServiceImpl
 * @Description: 动态监控关系企业风险分析Service实现类
 * @author shiyong
 * @date 2017年5月12日 下午2:22:48
 *  
 */
@Service
public class MonitorRelationCompanyRiskAnalysisServiceImpl implements MonitorRelationCompanyRiskAnalysisService{

	@Autowired
	private MonitorRelationCompanyDao monitorRelationCompanyDao;
	
	@Autowired
	private MonitorRelationCompanyRiskAnalysisDao monitorRelationCompanyRiskAnalysisDao;
	
	@Autowired
	private MonitorCompanyFinanceService monitorCompanyFinanceService;
	
	SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public List<Map<String, Object>> getMonitorRelationCompanyRiskAnalysisList(String monitorId) {
		
		List<Map<String, Object>> riskAnalysisList = new ArrayList<Map<String,Object>>();
		
		Map<String, Object> map = null;
		
		List<MonitorRelationCompany> relationCompanyList = monitorRelationCompanyDao.queryMonitorRelationCompanyListByMonitorId(monitorId);
		
		String relationCompanyMonitorId = "";
		
		List<MonitorRelationCompanyRiskAnalysis> monitorRelationCompanyRiskAnalysisList = null;
		
		Map<String, Object> jingyingMap = null;
		Map<String, Object> shesuMap = null;
		Map<String, Object> weiguiMap = null;
		Map<String, Object> shuiwuMap = null;
		Map<String, Object> yuqingMap = null;
		Map<String, Object> tourongziMap = null;
		Map<String, Object> caiwuMap = null;
		
		List<MonitorRelationCompanyRiskAnalysis> jingyingList = null;
		List<MonitorRelationCompanyRiskAnalysis> shesuList = null;
		List<MonitorRelationCompanyRiskAnalysis> weiguiList = null;
		List<MonitorRelationCompanyRiskAnalysis> shuiwuList = null;
		List<MonitorRelationCompanyRiskAnalysis> yuqingList = null;
		List<MonitorRelationCompanyRiskAnalysis> tourongziList = null;
		
		int eventNum = 0;
		String eventLevel = "";
		
		for (MonitorRelationCompany monitorRelationCompany : relationCompanyList) {
			//初始化参数
			map = new HashMap<String, Object>();
			relationCompanyMonitorId = "";
			monitorRelationCompanyRiskAnalysisList = null;
			jingyingMap = new HashMap<String, Object>();
			shesuMap = new HashMap<String, Object>();
			weiguiMap = new HashMap<String, Object>();
			shuiwuMap = new HashMap<String, Object>();
			yuqingMap = new HashMap<String, Object>();
			tourongziMap = new HashMap<String, Object>();
			caiwuMap = new HashMap<String, Object>();
			jingyingList = new ArrayList<MonitorRelationCompanyRiskAnalysis>();
			shesuList = new ArrayList<MonitorRelationCompanyRiskAnalysis>();
			weiguiList = new ArrayList<MonitorRelationCompanyRiskAnalysis>();
			shuiwuList = new ArrayList<MonitorRelationCompanyRiskAnalysis>();
			yuqingList = new ArrayList<MonitorRelationCompanyRiskAnalysis>();
			tourongziList = new ArrayList<MonitorRelationCompanyRiskAnalysis>();
			
			relationCompanyMonitorId = monitorRelationCompany.getId();
			
			monitorRelationCompanyRiskAnalysisList = monitorRelationCompanyRiskAnalysisDao.queryMonitorRelationCompanyRiskAnalysisListByMonitorId(relationCompanyMonitorId);
			
			//按顺序添加风险分析到各类别中
			//法人代表变更
			for (MonitorRelationCompanyRiskAnalysis monitorRelationCompanyRiskAnalysis : monitorRelationCompanyRiskAnalysisList) {
				if("01".equals(monitorRelationCompanyRiskAnalysis.getEventSubType())){
					jingyingList.add(monitorRelationCompanyRiskAnalysis);
					break;
				}
			}
			
			//高管变更
			for (MonitorRelationCompanyRiskAnalysis monitorRelationCompanyRiskAnalysis : monitorRelationCompanyRiskAnalysisList) {
				if("03".equals(monitorRelationCompanyRiskAnalysis.getEventSubType())){
					jingyingList.add(monitorRelationCompanyRiskAnalysis);
					break;
				}
			}
			
			//股东变更
			for (MonitorRelationCompanyRiskAnalysis monitorRelationCompanyRiskAnalysis : monitorRelationCompanyRiskAnalysisList) {
				if("02".equals(monitorRelationCompanyRiskAnalysis.getEventSubType())){
					jingyingList.add(monitorRelationCompanyRiskAnalysis);
					break;
				}
			}
			
			//企业更名
			for (MonitorRelationCompanyRiskAnalysis monitorRelationCompanyRiskAnalysis : monitorRelationCompanyRiskAnalysisList) {
				if("05".equals(monitorRelationCompanyRiskAnalysis.getEventSubType())){
					jingyingList.add(monitorRelationCompanyRiskAnalysis);
					break;
				}
			}
			
			//对外投资
			for (MonitorRelationCompanyRiskAnalysis monitorRelationCompanyRiskAnalysis : monitorRelationCompanyRiskAnalysisList) {
				if("07".equals(monitorRelationCompanyRiskAnalysis.getEventSubType())){
					jingyingList.add(monitorRelationCompanyRiskAnalysis);
					break;
				}
			}
			
			//经营异常
			for (MonitorRelationCompanyRiskAnalysis monitorRelationCompanyRiskAnalysis : monitorRelationCompanyRiskAnalysisList) {
				if("04".equals(monitorRelationCompanyRiskAnalysis.getEventSubType())){
					jingyingList.add(monitorRelationCompanyRiskAnalysis);
					break;
				}
			}
			
			//经营状态
			for (MonitorRelationCompanyRiskAnalysis monitorRelationCompanyRiskAnalysis : monitorRelationCompanyRiskAnalysisList) {
				if("06".equals(monitorRelationCompanyRiskAnalysis.getEventSubType())){
					jingyingList.add(monitorRelationCompanyRiskAnalysis);
					break;
				}
			}
			
			//股权冻结
			for (MonitorRelationCompanyRiskAnalysis monitorRelationCompanyRiskAnalysis : monitorRelationCompanyRiskAnalysisList) {
				if("10".equals(monitorRelationCompanyRiskAnalysis.getEventSubType())){
					jingyingList.add(monitorRelationCompanyRiskAnalysis);
					break;
				}
			}
			
			//专利
			for (MonitorRelationCompanyRiskAnalysis monitorRelationCompanyRiskAnalysis : monitorRelationCompanyRiskAnalysisList) {
				if("27".equals(monitorRelationCompanyRiskAnalysis.getEventSubType())){
					jingyingList.add(monitorRelationCompanyRiskAnalysis);
					break;
				}
			}
			
			//商标
			for (MonitorRelationCompanyRiskAnalysis monitorRelationCompanyRiskAnalysis : monitorRelationCompanyRiskAnalysisList) {
				if("29".equals(monitorRelationCompanyRiskAnalysis.getEventSubType())){
					jingyingList.add(monitorRelationCompanyRiskAnalysis);
					break;
				}
			}
			
			//招投标
			for (MonitorRelationCompanyRiskAnalysis monitorRelationCompanyRiskAnalysis : monitorRelationCompanyRiskAnalysisList) {
				if("28".equals(monitorRelationCompanyRiskAnalysis.getEventSubType())){
					jingyingList.add(monitorRelationCompanyRiskAnalysis);
					break;
				}
			}
			
			//开庭公告
			for (MonitorRelationCompanyRiskAnalysis monitorRelationCompanyRiskAnalysis : monitorRelationCompanyRiskAnalysisList) {
				if("23".equals(monitorRelationCompanyRiskAnalysis.getEventSubType())){
					shesuList.add(monitorRelationCompanyRiskAnalysis);
					break;
				}
			}
			
			//裁判文书
			for (MonitorRelationCompanyRiskAnalysis monitorRelationCompanyRiskAnalysis : monitorRelationCompanyRiskAnalysisList) {
				if("18".equals(monitorRelationCompanyRiskAnalysis.getEventSubType())){
					shesuList.add(monitorRelationCompanyRiskAnalysis);
					break;
				}
			}
			
			//裁判文书（金融合同）
			for (MonitorRelationCompanyRiskAnalysis monitorRelationCompanyRiskAnalysis : monitorRelationCompanyRiskAnalysisList) {
				if("19".equals(monitorRelationCompanyRiskAnalysis.getEventSubType())){
					shesuList.add(monitorRelationCompanyRiskAnalysis);
					break;
				}
			}
			
			//裁判文书（劳务纠纷）
			for (MonitorRelationCompanyRiskAnalysis monitorRelationCompanyRiskAnalysis : monitorRelationCompanyRiskAnalysisList) {
				if("20".equals(monitorRelationCompanyRiskAnalysis.getEventSubType())){
					shesuList.add(monitorRelationCompanyRiskAnalysis);
					break;
				}
			}
			
			//被执行人
			for (MonitorRelationCompanyRiskAnalysis monitorRelationCompanyRiskAnalysis : monitorRelationCompanyRiskAnalysisList) {
				if("21".equals(monitorRelationCompanyRiskAnalysis.getEventSubType())){
					shesuList.add(monitorRelationCompanyRiskAnalysis);
					break;
				}
			}
			
			//失信被执行人
			for (MonitorRelationCompanyRiskAnalysis monitorRelationCompanyRiskAnalysis : monitorRelationCompanyRiskAnalysisList) {
				if("22".equals(monitorRelationCompanyRiskAnalysis.getEventSubType())){
					shesuList.add(monitorRelationCompanyRiskAnalysis);
					break;
				}
			}
			
			//工商行政处罚
			for (MonitorRelationCompanyRiskAnalysis monitorRelationCompanyRiskAnalysis : monitorRelationCompanyRiskAnalysisList) {
				if("11".equals(monitorRelationCompanyRiskAnalysis.getEventSubType())){
					weiguiList.add(monitorRelationCompanyRiskAnalysis);
					break;
				}
			}
			
			//食药监监察
			for (MonitorRelationCompanyRiskAnalysis monitorRelationCompanyRiskAnalysis : monitorRelationCompanyRiskAnalysisList) {
				if("12".equals(monitorRelationCompanyRiskAnalysis.getEventSubType())){
					weiguiList.add(monitorRelationCompanyRiskAnalysis);
					break;
				}
			}
			
			//质量监督
			for (MonitorRelationCompanyRiskAnalysis monitorRelationCompanyRiskAnalysis : monitorRelationCompanyRiskAnalysisList) {
				if("13".equals(monitorRelationCompanyRiskAnalysis.getEventSubType())){
					weiguiList.add(monitorRelationCompanyRiskAnalysis);
					break;
				}
			}
			
			//社保欠缴
			for (MonitorRelationCompanyRiskAnalysis monitorRelationCompanyRiskAnalysis : monitorRelationCompanyRiskAnalysisList) {
				if("14".equals(monitorRelationCompanyRiskAnalysis.getEventSubType())){
					weiguiList.add(monitorRelationCompanyRiskAnalysis);
					break;
				}
			}
			
			//环境核查
			for (MonitorRelationCompanyRiskAnalysis monitorRelationCompanyRiskAnalysis : monitorRelationCompanyRiskAnalysisList) {
				if("15".equals(monitorRelationCompanyRiskAnalysis.getEventSubType())){
					weiguiList.add(monitorRelationCompanyRiskAnalysis);
					break;
				}
			}
			
			//欠税信息
			for (MonitorRelationCompanyRiskAnalysis monitorRelationCompanyRiskAnalysis : monitorRelationCompanyRiskAnalysisList) {
				if("16".equals(monitorRelationCompanyRiskAnalysis.getEventSubType())){
					shuiwuList.add(monitorRelationCompanyRiskAnalysis);
					break;
				}
			}
			
			//税务非正常户
			for (MonitorRelationCompanyRiskAnalysis monitorRelationCompanyRiskAnalysis : monitorRelationCompanyRiskAnalysisList) {
				if("17".equals(monitorRelationCompanyRiskAnalysis.getEventSubType())){
					shuiwuList.add(monitorRelationCompanyRiskAnalysis);
					break;
				}
			}
			
			//媒体资讯
			for (MonitorRelationCompanyRiskAnalysis monitorRelationCompanyRiskAnalysis : monitorRelationCompanyRiskAnalysisList) {
				if("24".equals(monitorRelationCompanyRiskAnalysis.getEventSubType())){
					yuqingList.add(monitorRelationCompanyRiskAnalysis);
					break;
				}
			}
			
			//重点关注舆情
			for (MonitorRelationCompanyRiskAnalysis monitorRelationCompanyRiskAnalysis : monitorRelationCompanyRiskAnalysisList) {
				if("25".equals(monitorRelationCompanyRiskAnalysis.getEventSubType())){
					yuqingList.add(monitorRelationCompanyRiskAnalysis);
					break;
				}
			}
			
			//动产质押
			for (MonitorRelationCompanyRiskAnalysis monitorRelationCompanyRiskAnalysis : monitorRelationCompanyRiskAnalysisList) {
				if("08".equals(monitorRelationCompanyRiskAnalysis.getEventSubType())){
					tourongziList.add(monitorRelationCompanyRiskAnalysis);
					break;
				}
			}
			
			//股权出质
			for (MonitorRelationCompanyRiskAnalysis monitorRelationCompanyRiskAnalysis : monitorRelationCompanyRiskAnalysisList) {
				if("09".equals(monitorRelationCompanyRiskAnalysis.getEventSubType())){
					tourongziList.add(monitorRelationCompanyRiskAnalysis);
					break;
				}
			}
			
			if(jingyingList.size()>0){
				eventNum = 0;
				eventLevel = "";
				
				for (MonitorRelationCompanyRiskAnalysis monitorRelationCompanyRiskAnalysis : jingyingList) {
					eventNum += monitorRelationCompanyRiskAnalysis.getEventNum();
					
					if(StringUtils.isEmpty(eventLevel)){
						eventLevel = monitorRelationCompanyRiskAnalysis.getEventLevel();
					}else{
						if(Integer.valueOf(eventLevel) < Integer.valueOf(monitorRelationCompanyRiskAnalysis.getEventLevel())){
							eventLevel = monitorRelationCompanyRiskAnalysis.getEventLevel();
						}
					}
				}
				
				jingyingMap.put("eventNum", eventNum);
				jingyingMap.put("eventLevel", eventLevel);
				jingyingMap.put("riskAnalysisList", jingyingList);
			}else{
				jingyingMap.put("eventNum", 0);
				jingyingMap.put("eventLevel", "");
				jingyingMap.put("riskAnalysisList", jingyingList);
			}
			
			if(shesuList.size()>0){
				eventNum = 0;
				eventLevel = "";
				
				for (MonitorRelationCompanyRiskAnalysis monitorRelationCompanyRiskAnalysis : shesuList) {
					eventNum += monitorRelationCompanyRiskAnalysis.getEventNum();
					
					if(StringUtils.isEmpty(eventLevel)){
						eventLevel = monitorRelationCompanyRiskAnalysis.getEventLevel();
					}else{
						if(Integer.valueOf(eventLevel) < Integer.valueOf(monitorRelationCompanyRiskAnalysis.getEventLevel())){
							eventLevel = monitorRelationCompanyRiskAnalysis.getEventLevel();
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
			
			if(weiguiList.size()>0){
				eventNum = 0;
				eventLevel = "";
				
				for (MonitorRelationCompanyRiskAnalysis monitorRelationCompanyRiskAnalysis : weiguiList) {
					eventNum += monitorRelationCompanyRiskAnalysis.getEventNum();
					
					if(StringUtils.isEmpty(eventLevel)){
						eventLevel = monitorRelationCompanyRiskAnalysis.getEventLevel();
					}else{
						if(Integer.valueOf(eventLevel) < Integer.valueOf(monitorRelationCompanyRiskAnalysis.getEventLevel())){
							eventLevel = monitorRelationCompanyRiskAnalysis.getEventLevel();
						}
					}
				}
				
				weiguiMap.put("eventNum", eventNum);
				weiguiMap.put("eventLevel", eventLevel);
				weiguiMap.put("riskAnalysisList", weiguiList);
			}else{
				weiguiMap.put("eventNum", 0);
				weiguiMap.put("eventLevel", "");
				weiguiMap.put("riskAnalysisList", weiguiList);
			}
			
			if(shuiwuList.size()>0){
				eventNum = 0;
				eventLevel = "";
				
				for (MonitorRelationCompanyRiskAnalysis monitorRelationCompanyRiskAnalysis : shuiwuList) {
					eventNum += monitorRelationCompanyRiskAnalysis.getEventNum();
					
					if(StringUtils.isEmpty(eventLevel)){
						eventLevel = monitorRelationCompanyRiskAnalysis.getEventLevel();
					}else{
						if(Integer.valueOf(eventLevel) < Integer.valueOf(monitorRelationCompanyRiskAnalysis.getEventLevel())){
							eventLevel = monitorRelationCompanyRiskAnalysis.getEventLevel();
						}
					}
				}
				
				shuiwuMap.put("eventNum", eventNum);
				shuiwuMap.put("eventLevel", eventLevel);
				shuiwuMap.put("riskAnalysisList", shuiwuList);
			}else{
				shuiwuMap.put("eventNum", 0);
				shuiwuMap.put("eventLevel", "");
				shuiwuMap.put("riskAnalysisList", shuiwuList);
			}
			
			if(yuqingList.size()>0){
				eventNum = 0;
				eventLevel = "";
				
				for (MonitorRelationCompanyRiskAnalysis monitorRelationCompanyRiskAnalysis : yuqingList) {
					eventNum += monitorRelationCompanyRiskAnalysis.getEventNum();
					
					if(StringUtils.isEmpty(eventLevel)){
						eventLevel = monitorRelationCompanyRiskAnalysis.getEventLevel();
					}else{
						if(Integer.valueOf(eventLevel) < Integer.valueOf(monitorRelationCompanyRiskAnalysis.getEventLevel())){
							eventLevel = monitorRelationCompanyRiskAnalysis.getEventLevel();
						}
					}
				}
				
				yuqingMap.put("eventNum", eventNum);
				yuqingMap.put("eventLevel", eventLevel);
				yuqingMap.put("riskAnalysisList", yuqingList);
			}else{
				yuqingMap.put("eventNum", 0);
				yuqingMap.put("eventLevel", "");
				yuqingMap.put("riskAnalysisList", yuqingList);
			}
			
			if(tourongziList.size()>0){
				eventNum = 0;
				eventLevel = "";
				
				for (MonitorRelationCompanyRiskAnalysis monitorRelationCompanyRiskAnalysis : tourongziList) {
					eventNum += monitorRelationCompanyRiskAnalysis.getEventNum();
					
					if(StringUtils.isEmpty(eventLevel)){
						eventLevel = monitorRelationCompanyRiskAnalysis.getEventLevel();
					}else{
						if(Integer.valueOf(eventLevel) < Integer.valueOf(monitorRelationCompanyRiskAnalysis.getEventLevel())){
							eventLevel = monitorRelationCompanyRiskAnalysis.getEventLevel();
						}
					}
				}
				
				tourongziMap.put("eventNum", eventNum);
				tourongziMap.put("eventLevel", eventLevel);
				tourongziMap.put("riskAnalysisList", tourongziList);
			}else{
				tourongziMap.put("eventNum", 0);
				tourongziMap.put("eventLevel", "");
				tourongziMap.put("riskAnalysisList", tourongziList);
			}
			
			MonitorCompanyFinanceSituation monitorCompanyFinanceSituation = monitorCompanyFinanceService.getCompanyFinanceSituationByCompanyId(monitorRelationCompany.getCompanyId());
			
			if(null != monitorCompanyFinanceSituation){
				if(1 == monitorCompanyFinanceSituation.getYearNum()){
					caiwuMap.put("yearNum", 1);
					caiwuMap.put("color", "1");
					
					List<MonitorCompanyFinanceAnalysis> monitorCompanyFinanceAnalysisList = new ArrayList<MonitorCompanyFinanceAnalysis>();
					MonitorCompanyFinanceAnalysis monitorCompanyFinanceAnalysis = new MonitorCompanyFinanceAnalysis();
					monitorCompanyFinanceAnalysis.setColor("1");
					monitorCompanyFinanceAnalysis.setLabel("1年财务数据");
					monitorCompanyFinanceAnalysisList.add(monitorCompanyFinanceAnalysis);
					
					caiwuMap.put("financeAnalysisList", monitorCompanyFinanceAnalysisList);
				}else{
					caiwuMap.put("yearNum", monitorCompanyFinanceSituation.getYearNum());
					caiwuMap.put("color", monitorCompanyFinanceSituation.getColor());
					caiwuMap.put("financeAnalysisList", monitorCompanyFinanceSituation.getMonitorCompanyFinanceAnalysisList());
				}
			}else{
				caiwuMap.put("yearNum", 0);
				caiwuMap.put("color", "");
				caiwuMap.put("financeAnalysisList", null);
			}
			
			map.put("companyName", monitorRelationCompany.getCompanyName());
			map.put("infoShowDate", sdfDate.format(monitorRelationCompany.getInfoShowDate()));
			map.put("jingying", jingyingMap);
			map.put("shesu", shesuMap);
			map.put("weigui", weiguiMap);
			map.put("shuiwu", shuiwuMap);
			map.put("yuqing", yuqingMap);
			map.put("tourongzi", tourongziMap);
			map.put("caiwu", caiwuMap);
			
			riskAnalysisList.add(map);
		}
		
		return riskAnalysisList;
	}

}
