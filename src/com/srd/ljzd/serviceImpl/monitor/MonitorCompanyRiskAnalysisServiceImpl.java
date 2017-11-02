/**   
* @Title: MonitorCompanyRiskAnalysisServiceImpl.java 
* @Package com.srd.ljzd.serviceImpl.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年5月12日 下午2:21:47 
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

import com.srd.ljzd.dao.monitor.MonitorCompanyDao;
import com.srd.ljzd.dao.monitor.MonitorCompanyRiskAnalysisDao;
import com.srd.ljzd.entity.monitor.MonitorCompany;
import com.srd.ljzd.entity.monitor.MonitorCompanyFinanceAnalysis;
import com.srd.ljzd.entity.monitor.MonitorCompanyFinanceSituation;
import com.srd.ljzd.entity.monitor.MonitorCompanyRiskAnalysis;
import com.srd.ljzd.service.monitor.MonitorCompanyFinanceService;
import com.srd.ljzd.service.monitor.MonitorCompanyRiskAnalysisService;
import com.srd.ljzd.util.StringUtils;

/** 
 * @ClassName: MonitorCompanyRiskAnalysisServiceImpl
 * @Description: 动态监控企业风险分析Service实现类
 * @author shiyong
 * @date 2017年5月12日 下午2:21:47
 *  
 */
@Service
public class MonitorCompanyRiskAnalysisServiceImpl implements MonitorCompanyRiskAnalysisService{

	@Autowired
	private MonitorCompanyRiskAnalysisDao monitorCompanyRiskAnalysisDao;
	
	@Autowired
	private MonitorCompanyDao monitorCompanyDao;
	
	@Autowired
	private MonitorCompanyFinanceService monitorCompanyFinanceService;
	
	SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public Map<String, Object> getMonitorCompanyRiskAnalysis(String monitorId) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<MonitorCompanyRiskAnalysis> monitorCompanyRiskAnalysisList = monitorCompanyRiskAnalysisDao.queryMonitorCompanyRiskAnalysisListByMonitorId(monitorId);
		
		Map<String, Object> jingyingMap = new HashMap<String, Object>();
		Map<String, Object> shesuMap = new HashMap<String, Object>();
		Map<String, Object> weiguiMap = new HashMap<String, Object>();
		Map<String, Object> shuiwuMap = new HashMap<String, Object>();
		Map<String, Object> yuqingMap = new HashMap<String, Object>();
		Map<String, Object> tourongziMap = new HashMap<String, Object>();
		Map<String, Object> caiwuMap = new HashMap<String, Object>();
		
		List<MonitorCompanyRiskAnalysis> jingyingList = new ArrayList<MonitorCompanyRiskAnalysis>();
		List<MonitorCompanyRiskAnalysis> shesuList = new ArrayList<MonitorCompanyRiskAnalysis>();
		List<MonitorCompanyRiskAnalysis> weiguiList = new ArrayList<MonitorCompanyRiskAnalysis>();
		List<MonitorCompanyRiskAnalysis> shuiwuList = new ArrayList<MonitorCompanyRiskAnalysis>();
		List<MonitorCompanyRiskAnalysis> yuqingList = new ArrayList<MonitorCompanyRiskAnalysis>();
		List<MonitorCompanyRiskAnalysis> tourongziList = new ArrayList<MonitorCompanyRiskAnalysis>();
		
		//按顺序添加风险分析到各类别中
		//法人代表变更
		for (MonitorCompanyRiskAnalysis monitorCompanyRiskAnalysis : monitorCompanyRiskAnalysisList) {
			if("01".equals(monitorCompanyRiskAnalysis.getEventSubType())){
				jingyingList.add(monitorCompanyRiskAnalysis);
				break;
			}
		}
		
		//高管变更
		for (MonitorCompanyRiskAnalysis monitorCompanyRiskAnalysis : monitorCompanyRiskAnalysisList) {
			if("03".equals(monitorCompanyRiskAnalysis.getEventSubType())){
				jingyingList.add(monitorCompanyRiskAnalysis);
				break;
			}
		}
		
		//股东变更
		for (MonitorCompanyRiskAnalysis monitorCompanyRiskAnalysis : monitorCompanyRiskAnalysisList) {
			if("02".equals(monitorCompanyRiskAnalysis.getEventSubType())){
				jingyingList.add(monitorCompanyRiskAnalysis);
				break;
			}
		}
		
		//企业更名
		for (MonitorCompanyRiskAnalysis monitorCompanyRiskAnalysis : monitorCompanyRiskAnalysisList) {
			if("05".equals(monitorCompanyRiskAnalysis.getEventSubType())){
				jingyingList.add(monitorCompanyRiskAnalysis);
				break;
			}
		}
		
		//对外投资
		for (MonitorCompanyRiskAnalysis monitorCompanyRiskAnalysis : monitorCompanyRiskAnalysisList) {
			if("07".equals(monitorCompanyRiskAnalysis.getEventSubType())){
				jingyingList.add(monitorCompanyRiskAnalysis);
				break;
			}
		}
		
		//经营异常
		for (MonitorCompanyRiskAnalysis monitorCompanyRiskAnalysis : monitorCompanyRiskAnalysisList) {
			if("04".equals(monitorCompanyRiskAnalysis.getEventSubType())){
				jingyingList.add(monitorCompanyRiskAnalysis);
				break;
			}
		}
		
		//经营状态
		for (MonitorCompanyRiskAnalysis monitorCompanyRiskAnalysis : monitorCompanyRiskAnalysisList) {
			if("06".equals(monitorCompanyRiskAnalysis.getEventSubType())){
				jingyingList.add(monitorCompanyRiskAnalysis);
				break;
			}
		}
		
		//股权冻结
		for (MonitorCompanyRiskAnalysis monitorCompanyRiskAnalysis : monitorCompanyRiskAnalysisList) {
			if("10".equals(monitorCompanyRiskAnalysis.getEventSubType())){
				jingyingList.add(monitorCompanyRiskAnalysis);
				break;
			}
		}
		
		//专利
		for (MonitorCompanyRiskAnalysis monitorCompanyRiskAnalysis : monitorCompanyRiskAnalysisList) {
			if("27".equals(monitorCompanyRiskAnalysis.getEventSubType())){
				jingyingList.add(monitorCompanyRiskAnalysis);
				break;
			}
		}
		
		//商标
		for (MonitorCompanyRiskAnalysis monitorCompanyRiskAnalysis : monitorCompanyRiskAnalysisList) {
			if("29".equals(monitorCompanyRiskAnalysis.getEventSubType())){
				jingyingList.add(monitorCompanyRiskAnalysis);
				break;
			}
		}
		
		//招投标
		for (MonitorCompanyRiskAnalysis monitorCompanyRiskAnalysis : monitorCompanyRiskAnalysisList) {
			if("28".equals(monitorCompanyRiskAnalysis.getEventSubType())){
				jingyingList.add(monitorCompanyRiskAnalysis);
				break;
			}
		}
		
		//开庭公告
		for (MonitorCompanyRiskAnalysis monitorCompanyRiskAnalysis : monitorCompanyRiskAnalysisList) {
			if("23".equals(monitorCompanyRiskAnalysis.getEventSubType())){
				shesuList.add(monitorCompanyRiskAnalysis);
				break;
			}
		}
		
		//裁判文书
		for (MonitorCompanyRiskAnalysis monitorCompanyRiskAnalysis : monitorCompanyRiskAnalysisList) {
			if("18".equals(monitorCompanyRiskAnalysis.getEventSubType())){
				shesuList.add(monitorCompanyRiskAnalysis);
				break;
			}
		}
		
		//裁判文书（金融合同）
		for (MonitorCompanyRiskAnalysis monitorCompanyRiskAnalysis : monitorCompanyRiskAnalysisList) {
			if("19".equals(monitorCompanyRiskAnalysis.getEventSubType())){
				shesuList.add(monitorCompanyRiskAnalysis);
				break;
			}
		}
		
		//裁判文书（劳务纠纷）
		for (MonitorCompanyRiskAnalysis monitorCompanyRiskAnalysis : monitorCompanyRiskAnalysisList) {
			if("20".equals(monitorCompanyRiskAnalysis.getEventSubType())){
				shesuList.add(monitorCompanyRiskAnalysis);
				break;
			}
		}
		
		//被执行人
		for (MonitorCompanyRiskAnalysis monitorCompanyRiskAnalysis : monitorCompanyRiskAnalysisList) {
			if("21".equals(monitorCompanyRiskAnalysis.getEventSubType())){
				shesuList.add(monitorCompanyRiskAnalysis);
				break;
			}
		}
		
		//失信被执行人
		for (MonitorCompanyRiskAnalysis monitorCompanyRiskAnalysis : monitorCompanyRiskAnalysisList) {
			if("22".equals(monitorCompanyRiskAnalysis.getEventSubType())){
				shesuList.add(monitorCompanyRiskAnalysis);
				break;
			}
		}
		
		//工商行政处罚
		for (MonitorCompanyRiskAnalysis monitorCompanyRiskAnalysis : monitorCompanyRiskAnalysisList) {
			if("11".equals(monitorCompanyRiskAnalysis.getEventSubType())){
				weiguiList.add(monitorCompanyRiskAnalysis);
				break;
			}
		}
		
		//食药监监察
		for (MonitorCompanyRiskAnalysis monitorCompanyRiskAnalysis : monitorCompanyRiskAnalysisList) {
			if("12".equals(monitorCompanyRiskAnalysis.getEventSubType())){
				weiguiList.add(monitorCompanyRiskAnalysis);
				break;
			}
		}
		
		//质量监督
		for (MonitorCompanyRiskAnalysis monitorCompanyRiskAnalysis : monitorCompanyRiskAnalysisList) {
			if("13".equals(monitorCompanyRiskAnalysis.getEventSubType())){
				weiguiList.add(monitorCompanyRiskAnalysis);
				break;
			}
		}
		
		//社保欠缴
		for (MonitorCompanyRiskAnalysis monitorCompanyRiskAnalysis : monitorCompanyRiskAnalysisList) {
			if("14".equals(monitorCompanyRiskAnalysis.getEventSubType())){
				weiguiList.add(monitorCompanyRiskAnalysis);
				break;
			}
		}
		
		//环境核查
		for (MonitorCompanyRiskAnalysis monitorCompanyRiskAnalysis : monitorCompanyRiskAnalysisList) {
			if("15".equals(monitorCompanyRiskAnalysis.getEventSubType())){
				weiguiList.add(monitorCompanyRiskAnalysis);
				break;
			}
		}
		
		//欠税信息
		for (MonitorCompanyRiskAnalysis monitorCompanyRiskAnalysis : monitorCompanyRiskAnalysisList) {
			if("16".equals(monitorCompanyRiskAnalysis.getEventSubType())){
				shuiwuList.add(monitorCompanyRiskAnalysis);
				break;
			}
		}
		
		//税务非正常户
		for (MonitorCompanyRiskAnalysis monitorCompanyRiskAnalysis : monitorCompanyRiskAnalysisList) {
			if("17".equals(monitorCompanyRiskAnalysis.getEventSubType())){
				shuiwuList.add(monitorCompanyRiskAnalysis);
				break;
			}
		}
		
		//媒体资讯
		for (MonitorCompanyRiskAnalysis monitorCompanyRiskAnalysis : monitorCompanyRiskAnalysisList) {
			if("24".equals(monitorCompanyRiskAnalysis.getEventSubType())){
				yuqingList.add(monitorCompanyRiskAnalysis);
				break;
			}
		}
		
		//重点关注舆情
		for (MonitorCompanyRiskAnalysis monitorCompanyRiskAnalysis : monitorCompanyRiskAnalysisList) {
			if("25".equals(monitorCompanyRiskAnalysis.getEventSubType())){
				yuqingList.add(monitorCompanyRiskAnalysis);
				break;
			}
		}
		
		//动产质押
		for (MonitorCompanyRiskAnalysis monitorCompanyRiskAnalysis : monitorCompanyRiskAnalysisList) {
			if("08".equals(monitorCompanyRiskAnalysis.getEventSubType())){
				tourongziList.add(monitorCompanyRiskAnalysis);
				break;
			}
		}
		
		//股权出质
		for (MonitorCompanyRiskAnalysis monitorCompanyRiskAnalysis : monitorCompanyRiskAnalysisList) {
			if("09".equals(monitorCompanyRiskAnalysis.getEventSubType())){
				tourongziList.add(monitorCompanyRiskAnalysis);
				break;
			}
		}
		
		int eventNum = 0;
		String eventLevel = "";
		
		if(jingyingList.size()>0){
			eventNum = 0;
			eventLevel = "";
			
			for (MonitorCompanyRiskAnalysis monitorCompanyRiskAnalysis : jingyingList) {
				eventNum += monitorCompanyRiskAnalysis.getEventNum();
				
				if(StringUtils.isEmpty(eventLevel)){
					eventLevel = monitorCompanyRiskAnalysis.getEventLevel();
				}else{
					if(Integer.valueOf(eventLevel) < Integer.valueOf(monitorCompanyRiskAnalysis.getEventLevel())){
						eventLevel = monitorCompanyRiskAnalysis.getEventLevel();
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
			
			for (MonitorCompanyRiskAnalysis monitorCompanyRiskAnalysis : shesuList) {
				eventNum += monitorCompanyRiskAnalysis.getEventNum();
				
				if(StringUtils.isEmpty(eventLevel)){
					eventLevel = monitorCompanyRiskAnalysis.getEventLevel();
				}else{
					if(Integer.valueOf(eventLevel) < Integer.valueOf(monitorCompanyRiskAnalysis.getEventLevel())){
						eventLevel = monitorCompanyRiskAnalysis.getEventLevel();
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
			
			for (MonitorCompanyRiskAnalysis monitorCompanyRiskAnalysis : weiguiList) {
				eventNum += monitorCompanyRiskAnalysis.getEventNum();
				
				if(StringUtils.isEmpty(eventLevel)){
					eventLevel = monitorCompanyRiskAnalysis.getEventLevel();
				}else{
					if(Integer.valueOf(eventLevel) < Integer.valueOf(monitorCompanyRiskAnalysis.getEventLevel())){
						eventLevel = monitorCompanyRiskAnalysis.getEventLevel();
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
			
			for (MonitorCompanyRiskAnalysis monitorCompanyRiskAnalysis : shuiwuList) {
				eventNum += monitorCompanyRiskAnalysis.getEventNum();
				
				if(StringUtils.isEmpty(eventLevel)){
					eventLevel = monitorCompanyRiskAnalysis.getEventLevel();
				}else{
					if(Integer.valueOf(eventLevel) < Integer.valueOf(monitorCompanyRiskAnalysis.getEventLevel())){
						eventLevel = monitorCompanyRiskAnalysis.getEventLevel();
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
			
			for (MonitorCompanyRiskAnalysis monitorCompanyRiskAnalysis : yuqingList) {
				eventNum += monitorCompanyRiskAnalysis.getEventNum();
				
				if(StringUtils.isEmpty(eventLevel)){
					eventLevel = monitorCompanyRiskAnalysis.getEventLevel();
				}else{
					if(Integer.valueOf(eventLevel) < Integer.valueOf(monitorCompanyRiskAnalysis.getEventLevel())){
						eventLevel = monitorCompanyRiskAnalysis.getEventLevel();
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
			
			for (MonitorCompanyRiskAnalysis monitorCompanyRiskAnalysis : tourongziList) {
				eventNum += monitorCompanyRiskAnalysis.getEventNum();
				
				if(StringUtils.isEmpty(eventLevel)){
					eventLevel = monitorCompanyRiskAnalysis.getEventLevel();
				}else{
					if(Integer.valueOf(eventLevel) < Integer.valueOf(monitorCompanyRiskAnalysis.getEventLevel())){
						eventLevel = monitorCompanyRiskAnalysis.getEventLevel();
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
		
		MonitorCompany monitorCompany = monitorCompanyDao.get(monitorId);
		
		MonitorCompanyFinanceSituation monitorCompanyFinanceSituation = monitorCompanyFinanceService.getCompanyFinanceSituationByCompanyId(monitorCompany.getCompanyId());
		
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
		
		map.put("companyName", monitorCompany.getCompanyName());
		map.put("infoShowDate", sdfDate.format(monitorCompany.getInfoShowDate()));
		map.put("jingying", jingyingMap);
		map.put("shesu", shesuMap);
		map.put("weigui", weiguiMap);
		map.put("shuiwu", shuiwuMap);
		map.put("yuqing", yuqingMap);
		map.put("tourongzi", tourongziMap);
		map.put("caiwu", caiwuMap);
		
		return map;
	}

}
