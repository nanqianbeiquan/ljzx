package com.srd.ljzd.serviceImpl.monitor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.srd.ljzd.entity.company.CompanyInfo;
import com.srd.ljzd.entity.monitor.MonitorCompany;
import com.srd.ljzd.entity.monitor.MonitorCustomRiskInfo;
import com.srd.ljzd.entity.monitor.MonitorCustomRiskSituation;
import com.srd.ljzd.service.monitor.DownloadReportService;
import com.srd.ljzd.service.monitor.MonitorCompanyRiskAnalysisService;
import com.srd.ljzd.service.monitor.MonitorCustomRiskSituationService;
import com.srd.ljzd.service.monitor.MonitorRelationCompanyRiskAnalysisService;
import com.srd.ljzd.service.monitor.MonitorRelationPersonRiskAnalysisService;
import com.srd.ljzd.util.DateUtils;
import com.srd.ljzd.util.LoggerUtils;
import com.srd.ljzd.util.PropertiesLoader;

@Service("downloadReportService")
public class DownloadReportServiceImpl implements DownloadReportService{
	Pattern pattern =  Pattern.compile("<br>");
	@Autowired
	MonitorCustomRiskSituationService monitorCustomRiskSituationService;
	@Autowired
	private MonitorCompanyRiskAnalysisService monitorCompanyRiskAnalysisService;
	
	@Autowired
	private MonitorRelationCompanyRiskAnalysisService monitorRelationCompanyRiskAnalysisService;
	
	@Autowired
	private MonitorRelationPersonRiskAnalysisService monitorRelationPersonRiskAnalysisService;
	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	
	@Override
	public XSSFWorkbook buildReport(String templatePath, Map<String, Object> data) {
		XSSFWorkbook work =null;
		InputStream in = null;
		try{
			 
			in = new FileInputStream(new File(templatePath));  
			
			work = new XSSFWorkbook(in);  
			// 得到excel的第0张表  
			XSSFSheet sheet = work.getSheetAt(0);  
			Iterator<Row> rowIterator = sheet.rowIterator();
			StringBuffer content = null;
			//第二行检查日期
			Date checkDate = (Date)data.get("checkDate");
			content = new StringBuffer("日期：").append(DateUtils.getLocalStrNew(DateUtils.CHINA_PATTERN, checkDate));
			sheet.getRow(1).getCell(0).setCellValue(content.toString());
			
			//第三行客户名称
			sheet.getRow(2).getCell(1).setCellValue((String)data.get("companyName"));
			//第4行 加入动态监控时间和风险状况
			sheet.getRow(3).getCell(1).setCellValue( DateUtils.getLocalStrNew(DateUtils.CHINA_PATTERN, (Date)data.get("monitorDate")));
			sheet.getRow(3).getCell(3).setCellValue((String)data.get("riskSituation"));
			
			//第5行注册资本和经营范围
			sheet.getRow(4).getCell(1).setCellValue((String)data.get("registeredCapital"));
			sheet.getRow(4).getCell(3).setCellValue((String)data.get("businessScope"));
			//6行其他监测主体(企业和个人)
			content = new StringBuffer((String)data.get("relationSubject"));
			sheet.getRow(5).getCell(1).setCellValue(content.toString());
			//7行基本信息有无变化  ☑ □
			boolean flag = false;
			if(data.containsKey("isBaseMsgChange")){
				flag = (boolean)data.get("isBaseMsgChange");
				content = new StringBuffer("基本信息有无变化：  ").append(flag?"☑是          ":"□是          ").append(flag?"□否":"☑否");
				sheet.getRow(6).getCell(1).setCellValue(content.toString());
			}
			
			//8行是否有重大负面信息
			if(data.containsKey("isBigNegativeInfo")){
				flag = (boolean)data.get("isBigNegativeInfo");
				content = new StringBuffer("是否有重大舆情信息：  ").append(flag?"☑是          ":"□是          ").append(flag?"□否":"☑否");
				sheet.getRow(7).getCell(1).setCellValue(content.toString());
			}
			
			//9行是否有涉诉信息
			if(data.containsKey("isInvolvedInAppeal")){
				flag = (boolean)data.get("isInvolvedInAppeal");
				content = new StringBuffer("是否有涉诉信息：  ").append(flag?"☑是          ":"□是          ").append(flag?"□否":"☑否");
				sheet.getRow(8).getCell(1).setCellValue(content.toString());
			}
						
			//10行是否有重大经营变化信息
			if(data.containsKey("isBusBigChange")){
				flag = (boolean)data.get("isBusBigChange");
				content = new StringBuffer("是否有重大经营变化信息：  ").append(flag?"☑是          ":"□是          ").append(flag?"□否":"☑否");
				sheet.getRow(9).getCell(1).setCellValue(content.toString());
			}
			
			//11行 风险分析
			sheet.getRow(10).getCell(1).setCellValue((String)data.get("riskAnalysis"));
			//12行 建 议
			sheet.getRow(11).getCell(1).setCellValue((String)data.get("riskResult"));
			
			//13行  自定义风险状况
			sheet.getRow(12).getCell(2).setCellValue((String)data.get("riskChange"));
			//14行  自定义风险 主要原因
			sheet.getRow(13).getCell(2).setCellValue((String)data.get("riskChangeReason"));
			
			if(data.get("historyRiskChangList")!=null){
				//构造历史自定义风险
				buildHistoryRiskSituation(work,(String)data.get("companyName"),(List<Map<String,Object>>)data.get("historyRiskChangList"));
			}else{
				sheet.getRow(15).getCell(0).setCellValue("");
				work.removeSheetAt(1);
			}
			work.setActiveSheet(0);
		} catch (FileNotFoundException e) {  
			LoggerUtils.error("文件路径错误", e);
		} catch (IOException e) { 
			LoggerUtils.error("文件输入流错误", e);
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				LoggerUtils.error(e.getMessage(), e);
			}
		}
		
		return work;
	}
    private void buildHistoryRiskSituation(XSSFWorkbook work,String companyName,List<Map<String,Object>> historyRiskChangList){
    	XSSFSheet sheet2 = work.getSheetAt(1);
    	
    	//1. 附件1
    	XSSFRow row1 = sheet2.createRow(0);
    	XSSFCell row1Cell = row1.createCell(0);
    	CellStyle titleCellStyle =work.createCellStyle();
	      // 设置这些样式
    	XSSFFont planFont = work.createFont();
		planFont.setFontName("微软雅黑");
		
		XSSFFont blodFont = work.createFont();
		blodFont.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		blodFont.setFontName("微软雅黑");
		titleCellStyle.setFont(blodFont);
    	row1Cell.setCellValue("附件1  历史风险自定义");
    	row1Cell.setCellStyle(titleCellStyle);
    	row1.setHeightInPoints(20);
    	sheet2.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
    	
    	//2. 公司名称
		XSSFRow row2 = sheet2.createRow(1);
		XSSFCell companyNameCell = row2.createCell(0);
		companyNameCell.setCellValue(companyName);
		CellStyle companyNameCellStyle =work.createCellStyle();
	      // 设置这些样式
		blodFont.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		companyNameCellStyle.setFont(blodFont);
		companyNameCellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		companyNameCellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		companyNameCellStyle.setBorderBottom((short)1);
		companyNameCellStyle.setBorderLeft((short)1);
		companyNameCellStyle.setBorderRight((short)1);
		companyNameCellStyle.setBorderTop((short)1);
		companyNameCell.setCellStyle(companyNameCellStyle);
		row2.createCell(1).setCellStyle(companyNameCellStyle);
		row2.createCell(2).setCellStyle(companyNameCellStyle);
		row2.createCell(3).setCellStyle(companyNameCellStyle);
		row2.setHeightInPoints(25);
		sheet2.addMergedRegion(new CellRangeAddress(1, 1, 0, 3));
		//3 历史
		if(historyRiskChangList!=null){
			
			XSSFRow rowTemp = null;
			XSSFRow rowTemp2 = null;
			
			XSSFCell cell1 = null;
			XSSFCell cell2 = null;
			XSSFCell cell3 = null;
			XSSFCell cell4 = null;
			XSSFCell cell5 = null;
			XSSFCell cell6 = null;
			//cellStyle11 水平垂直居中，  自动换行 ，字体加粗,左边框
			CellStyle cellStyle11 =work.createCellStyle();
			cellStyle11.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			cellStyle11.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
			cellStyle11.setFont(blodFont);
			cellStyle11.setWrapText(true);
			cellStyle11.setBorderLeft((short)1);
			
			//cellStyle 左边框，下边框
			CellStyle cellStyle21 =work.createCellStyle();
			cellStyle21.setBorderLeft((short)1);
			cellStyle21.setBorderBottom((short)1);
			
			//cellStyle12 字体加粗，水平垂直居中，自动换行，左边框
			CellStyle cellStyle12 =work.createCellStyle();
			cellStyle12.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			cellStyle12.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
			cellStyle12.setWrapText(true);
			cellStyle12.setFont(blodFont);
			cellStyle12.setBorderLeft((short)1);
			//cellStyle22 左边框，下边框
			CellStyle cellStyle22 = cellStyle21;
			
			//cellStyle13  水平垂直居中,自动换行, 左边框，下边框
			CellStyle cellStyle13 =work.createCellStyle();
			cellStyle13.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			cellStyle13.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
			cellStyle13.setWrapText(true);
			cellStyle13.setBorderLeft((short)1);
			cellStyle13.setBorderBottom((short)1);
			cellStyle13.setFont(blodFont);
			//cellStyle23  左边框，下边框
			CellStyle cellStyle23 = cellStyle13;
			
			//cellStyle14  水平靠左,垂直居中,自动换行, 左边框，下边框，右边框
			CellStyle cellStyle14 =work.createCellStyle();
			cellStyle14.setAlignment(XSSFCellStyle.ALIGN_LEFT);
			cellStyle14.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
			cellStyle14.setWrapText(true);
			cellStyle14.setBorderLeft((short)1);
			cellStyle14.setBorderBottom((short)1);
			cellStyle14.setBorderRight((short)1);
			cellStyle14.setFont(planFont);
			//cellStyle14  水平靠左,垂直居中,自动换行, 左边框，下边框，右边框
			CellStyle cellStyle24 = cellStyle14;
			int rowIndex = 1;
			for(int i=0;i<historyRiskChangList.size();i++){
				rowTemp = sheet2.createRow(rowIndex+1);
				rowTemp2 = sheet2.createRow(rowIndex+2);
				//(1,1)单元格
				cell1 = rowTemp.createCell(0);
				cell1.setCellValue("历史认定");
				cell1.setCellStyle(cellStyle11);
				//(2,1)单元格
				rowTemp2.createCell(0).setCellStyle(cellStyle21);
				
				//(1,2)单元格
				cell2 = rowTemp.createCell(1);
				cell2.setCellValue((String)historyRiskChangList.get(i).get("date"));
				cell2.setCellStyle(cellStyle12);
				//(2,2)单元格
				rowTemp2.createCell(1).setCellStyle(cellStyle22);
				//(1,3)单元格
				cell3 = rowTemp.createCell(2);
				cell3.setCellValue("风险认定");
				cell3.setCellStyle(cellStyle13);
				//(2,3)单元格
				cell5 = rowTemp2.createCell(2);
				cell5.setCellValue("主要原因");
				cell5.setCellStyle(cellStyle23);
				
				//(1,4)单元格
				cell4 = rowTemp.createCell(3);
				cell4.setCellValue((String)historyRiskChangList.get(i).get("change"));
				cell4.setCellStyle(cellStyle14);
				
				cell6 = rowTemp2.createCell(3);
				cell6.setCellValue((String)historyRiskChangList.get(i).get("reason"));
				cell6.setCellStyle(cellStyle24);
				
				rowTemp.setHeightInPoints(25);
				
				sheet2.addMergedRegion(new CellRangeAddress(rowIndex+1, rowIndex+2, 0, 0));
				sheet2.addMergedRegion(new CellRangeAddress(rowIndex+1, rowIndex+2, 1, 1));
				rowIndex += 2;
			}
		}
		
		
	}
	
	/**
	 * 获得下载报告中显示的风险状况
	 * @param monitorCompany
	 * @return
	 */
	private String getReportRiskSituation(MonitorCompany monitorCompany){
		String riskSituation = "";
		//没有自定义风险，取系统风险状况
		if(null==monitorCompany.getCustomRiskFlag()
				||"0".equals(monitorCompany.getCustomRiskFlag())){
			riskSituation = monitorCompany.getRiskSituation();
		}else{
			riskSituation = monitorCompany.getCustomRiskSituation();
		}
		return this.getRiskSituationDesc(riskSituation);
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
	/**
	 * 获取所有关联对象，包括关联企业和关系人
	 * @return
	 */
	private String getRelationSubject(List<Map<String, Object>> relCompanyAnalysisList,List<Map<String, Object>> relPersonAnalysisList){
		StringBuffer relCompanys = new StringBuffer("关系企业：");
		StringBuffer relPersons = new StringBuffer("关系自然人：");
		
		int relCompanyIndex = 0;
	    int relPersonIndex = 0;
	    
	    if((relCompanyAnalysisList==null||relCompanyAnalysisList.size()==0)
	    		&&(relPersonAnalysisList==null||relPersonAnalysisList.size()==0)){
	    	relCompanys.append("无  ").append("\n");
	    	relPersons.append("无");
	    }else{
			if(relCompanyAnalysisList==null||relCompanyAnalysisList.size()==0){
				relCompanys.append("无  ");
			}else{
				Map<String, Object> relCompanyAnalysis = null;
				
				for(relCompanyIndex=0;relCompanyIndex<relCompanyAnalysisList.size();relCompanyIndex++){
					relCompanyAnalysis = relCompanyAnalysisList.get(relCompanyIndex);
					relCompanys.append(relCompanyAnalysis.get("companyName")).append("  ");
				}
			}
			relCompanys.append("\n");
			if(relPersonAnalysisList==null||relPersonAnalysisList.size()==0){
				relPersons.append("无");
			}else{
				Map<String, Object> relPersonAnalysis = null;
				for(relPersonIndex=0;relPersonIndex<relPersonAnalysisList.size();relPersonIndex++){
					relPersonAnalysis = relPersonAnalysisList.get(relPersonIndex);
					relPersons.append(relPersonAnalysis.get("personName")).append("  ");
				}
			}
	    }
	    return relCompanys.append(relPersons).toString();
	}
	/**
	 * 获取风险分析
	 * @param monitorCompany
	 * @return
	 */
	private String getRiskAnalysis(MonitorCompany monitorCompany
			,List<Map<String, Object>> relCompanyAnalysisList,List<Map<String, Object>> relPersonAnalysisList){
		StringBuffer analysis = new StringBuffer("主要风险提示：\n风险标签：");
		//1.风险标签
		List<String> tempList = new ArrayList<String>();
		if(monitorCompany.getRiskLabel()!=null){
			String[] tempArray = monitorCompany.getRiskLabel().split(",");
			for(int i=0;i<tempArray.length;i++){
				if(!StringUtils.isEmpty(tempArray[i])){
					tempList.add(tempArray[i]);
				}
			}
		}
		if(!StringUtils.isEmpty(monitorCompany.getCustomRiskFlag())
				&&"1".equals(monitorCompany.getCustomRiskFlag())){
			if(monitorCompany.getCustomRiskLabel()!=null){
				String[] tempArray = monitorCompany.getCustomRiskLabel().split(",");
				for(int i=0;i<tempArray.length;i++){
					if(!StringUtils.isEmpty(tempArray[i])){
						tempList.add(tempArray[i]);
					}
				}

			}
		}
		if(tempList!=null&&tempList.size()>0){
			for(int i=0;i<tempList.size();i++){
				if(!StringUtils.isEmpty(tempList.get(i))){
					analysis.append(tempList.get(i)).append(" ");
				}
			}
		}else{
			analysis.append("无");
		}
		Properties eventCategoryMap = (Properties) redisTemplate.opsForValue().get("eventCategoryMap");
		if(eventCategoryMap==null){
			PropertiesLoader loader = new PropertiesLoader("/resources/data/event_category_map.properties");
			eventCategoryMap = loader.getProperties();
			redisTemplate.opsForValue().set("eventCategoryMap", eventCategoryMap, 24*60*60, TimeUnit.SECONDS);
		}
		
		Properties eventUnitMap = (Properties) redisTemplate.opsForValue().get("eventUnitMap");
		if(eventUnitMap==null){
			PropertiesLoader loader = new PropertiesLoader("/resources/data/event_unit_map.properties");
			eventUnitMap = loader.getProperties();
			redisTemplate.opsForValue().set("eventUnitMap", eventUnitMap, 24*60*60, TimeUnit.SECONDS);
		}
		//2.主体企业
		analysis.append("\n\n主体企业变动情况：\n");
		Map<String, Object> analysisMap = monitorCompanyRiskAnalysisService.getMonitorCompanyRiskAnalysis(monitorCompany.getMonitorID());
		this.buildOneSubjectAnalysis(analysis, analysisMap,eventCategoryMap,eventUnitMap);
		
		analysis.append("\n关系企业（自然人）变动情况:\n");
		//3.关联企业  
		//List<Map<String, Object>> monitorRelationCompanyRiskAnalysisList = monitorRelationCompanyRiskAnalysisService.getMonitorRelationCompanyRiskAnalysisList(monitorCompany.getMonitorID());
		//4.关联个人
		//List<Map<String, Object>> monitorRelationPersonRiskAnalysisList = monitorRelationPersonRiskAnalysisService.getMonitorRelationPersonRiskAnalysisList(monitorCompany.getMonitorID());
		
		if((relCompanyAnalysisList==null||relCompanyAnalysisList.size()==0)
			&&(relPersonAnalysisList==null||relPersonAnalysisList.size()==0)){
			analysis.append("未加入关系企业（自然人）");
		}else{
			if(relCompanyAnalysisList!=null&&relCompanyAnalysisList.size()>0){
				for(Map<String, Object> record : relCompanyAnalysisList){
					this.buildOneSubjectAnalysis(analysis, record,eventCategoryMap,eventUnitMap);
					analysis.append("\n");
				}
				//analysis.append("\n");
			}
            if(relPersonAnalysisList!=null&&relPersonAnalysisList.size()>0){
                for(Map<String, Object> record : relPersonAnalysisList){
                	this.buildOneSubjectAnalysisWithRelationPerson(analysis, record,eventCategoryMap,eventUnitMap);
					analysis.append("\n");
				}
			}
		}
		
		return analysis.toString();
	}
	
	private void buildOneSubjectAnalysisWithRelationPerson(StringBuffer targetAnalysis,Map<String, Object> subjectAnalysisMap
			,Properties eventCategoryMap,Properties eventUnitMap){
		Object targetName = subjectAnalysisMap.get("companyName");
		if(targetName==null){
			targetName = subjectAnalysisMap.get("personName");
		}
		targetAnalysis.append(targetName)
		.append("  ")
		.append(subjectAnalysisMap.get("infoShowDate"))
		.append("~至今")
		.append("\n");
		String tempStr = buildSecondCategoryAnalysis("涉诉：","shesu",subjectAnalysisMap,eventCategoryMap,eventUnitMap);
		if(tempStr==null){
			targetAnalysis.append("涉诉：无事件发生");
		}else{
			targetAnalysis.append(tempStr);
		}
		targetAnalysis.append("\n");
	}
	
	/**
	 * 构造一个对象的风险分析，一个对象是指 企业或者个人
	 */
	private void buildOneSubjectAnalysis(StringBuffer targetAnalysis,Map<String, Object> subjectAnalysisMap,Properties eventCategoryMap,Properties eventUnitMap){
		Object targetName = subjectAnalysisMap.get("companyName");
		if(targetName==null){
			targetName = subjectAnalysisMap.get("personName");
		}
		targetAnalysis.append(targetName)
		.append("  ")
		.append(subjectAnalysisMap.get("infoShowDate"))
		.append("~至今")
		.append("\n");
		String tempStr = null;
		StringBuffer hasEventCate = new StringBuffer();
		StringBuffer noEventCate = new StringBuffer();
		tempStr = buildSecondCategoryAnalysis("经营：","jingying",subjectAnalysisMap,eventCategoryMap,eventUnitMap);
		if(tempStr!=null){
			hasEventCate.append(tempStr);
		}else{
			noEventCate.append("经营");
		}
		tempStr = buildSecondCategoryAnalysis("涉诉：","shesu",subjectAnalysisMap,eventCategoryMap,eventUnitMap);
		if(tempStr!=null){
			if(hasEventCate.length()>0){
				hasEventCate.append("\n");
			}
			hasEventCate.append(tempStr);
		}else{
			if(noEventCate.length()>0){
				noEventCate.append("、");
			}
			noEventCate.append("涉诉");
		}
		
		tempStr = buildSecondCategoryAnalysis("违规：","weigui",subjectAnalysisMap,eventCategoryMap,eventUnitMap);
		if(tempStr!=null){
			if(hasEventCate.length()>0){
				hasEventCate.append("\n");
			}
			hasEventCate.append(tempStr);
		}else{
			if(noEventCate.length()>0){
				noEventCate.append("、");
			}
			noEventCate.append("违规");
		}
		
		tempStr = buildSecondCategoryAnalysis("税务：","shuiwu",subjectAnalysisMap,eventCategoryMap,eventUnitMap);
		if(tempStr!=null){
			if(hasEventCate.length()>0){
				hasEventCate.append("\n");
			}
			hasEventCate.append(tempStr);
		}else{
			if(noEventCate.length()>0){
				noEventCate.append("、");
			}
			noEventCate.append("税务");
		}
		
		tempStr = buildSecondCategoryAnalysis("舆情：","yuqing",subjectAnalysisMap,eventCategoryMap,eventUnitMap);
		if(tempStr!=null){
			if(hasEventCate.length()>0){
				hasEventCate.append("\n");
			}
			hasEventCate.append(tempStr);
		}else{
			if(noEventCate.length()>0){
				noEventCate.append("、");
			}
			noEventCate.append("舆情");
		}
		tempStr = buildSecondCategoryAnalysis("投融资：","tourongzi",subjectAnalysisMap,eventCategoryMap,eventUnitMap);
		if(tempStr!=null){
			if(hasEventCate.length()>0){
				hasEventCate.append("\n");
			}
			hasEventCate.append(tempStr);
		}else{
			if(noEventCate.length()>0){
				noEventCate.append("、");
			}
			noEventCate.append("投融资");
		}
		if(hasEventCate.length()>0){
			targetAnalysis.append(hasEventCate);
		}
		if(noEventCate.length()>0){
			if(hasEventCate.length()>0){
				targetAnalysis.append("\n");
			}
			targetAnalysis.append(noEventCate).append("  无事件发生");
		}
		targetAnalysis.append("\n");
	}
	/**
	 * 构造第二类别的分析，第二类别是指 经营、涉诉、违规、税务、舆情和投融资
	 */
	private String buildSecondCategoryAnalysis(String showName,String secondCateName,Map<String, Object> subjectAnalysisMap
			,Properties eventCategoryMap,Properties eventUnitMap){
		StringBuffer targetAnalysis = null;
		Map<String, Object> firstCateMap = (Map<String, Object>)subjectAnalysisMap.get(secondCateName);
		if(firstCateMap!=null){
			Integer eventNum = (Integer)firstCateMap.get("eventNum");
			if(eventNum!=null&&eventNum>0){
				
				List<Object> analysisList = (List<Object>)firstCateMap.get("riskAnalysisList");
				if(analysisList!=null){
					targetAnalysis = new StringBuffer(showName);
					for(Object record:analysisList){
							try {
								Field typeField = record.getClass().getDeclaredField("eventSubType");
								typeField.setAccessible(true);
								String tempType = (String)typeField.get(record);
								
								Field numField = record.getClass().getDeclaredField("eventNum");
								numField.setAccessible(true);
								Object tempNum = numField.get(record);
								
								targetAnalysis.append(eventCategoryMap.get(tempType))
								  .append(tempNum)
								  .append(eventUnitMap.getProperty(tempType, "")).append("  ");
								
							} catch (Exception e) {
								LoggerUtils.error("解析风险对象报错", e);
							}
					}
				}
			}
		}
		if(targetAnalysis!=null){
			return targetAnalysis.toString();
		}
		return null;
	}
	@Override
	public Map<String, Object> buildReportData(MonitorCompany monitorCompany,CompanyInfo companyRemote) {
		
		//MonitorCompany monitorCompany = companyRisk.getMonitorCompany();
		
		Map<String, Object> data = new HashMap<String,Object>();
		data.put("companyName",monitorCompany.getCompanyName());
		data.put("checkDate",Calendar.getInstance().getTime());
		data.put("monitorDate",monitorCompany.getMonitorDate());
		//风险状况
		String riskSituation = this.getReportRiskSituation(monitorCompany);
		data.put("riskSituation",riskSituation);
		
		if(companyRemote!=null&&companyRemote.getRegisterInfo()!=null){
			data.put("registeredCapital",companyRemote.getRegisterInfo().getRegisteredCapital());
			data.put("businessScope",companyRemote.getRegisterInfo().getBusinessScope());
		}else{
			data.put("registeredCapital","");
			data.put("businessScope","");
		}
		//关联企业  
		List<Map<String, Object>> relCompanyAnalysisList = monitorRelationCompanyRiskAnalysisService.getMonitorRelationCompanyRiskAnalysisList(monitorCompany.getMonitorID());
		//关联个人
		List<Map<String, Object>> relPersonAnalysisList = monitorRelationPersonRiskAnalysisService.getMonitorRelationPersonRiskAnalysisList(monitorCompany.getMonitorID());
		//其他监控对象，关联企业和关联个人
		String relationSubject = this.getRelationSubject(relCompanyAnalysisList, relPersonAnalysisList);
		data.put("relationSubject",relationSubject);
		//风险分析
		String riskAnalysis = this.getRiskAnalysis(monitorCompany,relCompanyAnalysisList, relPersonAnalysisList);
		data.put("riskAnalysis",riskAnalysis);
		if(monitorCompany.getEventCheckFlag()!=null){
			char[] flagChar = monitorCompany.getEventCheckFlag().toCharArray();
			if(flagChar.length>=1){
				data.put("isBaseMsgChange",'1'==flagChar[0]?true:false);
			}else{
				data.put("isBaseMsgChange",false);
			}
			if(flagChar.length>=2){
				data.put("isBigNegativeInfo",'1'==flagChar[1]?true:false);
			}else{
				data.put("isBigNegativeInfo",false);
			}
			if(flagChar.length>=3){
				data.put("isInvolvedInAppeal",'1'==flagChar[2]?true:false);
			}else{
				data.put("isInvolvedInAppeal",false);
			}
			if(flagChar.length>=4){
				data.put("isBusBigChange",'1'==flagChar[3]?true:false);
			}else{
				data.put("isBusBigChange",false);
			}
		}
		
		//风险建议
		data.put("riskResult",monitorCompany.getCustomRiskResult());
		//风险认定状况
		MonitorCustomRiskSituation lasted = null;
		if(!StringUtils.isEmpty(monitorCompany.getCustomRiskFlag())
				&&"1".equals(monitorCompany.getCustomRiskFlag())){
			List<MonitorCustomRiskSituation> list = monitorCustomRiskSituationService.getLCustomRiskList(monitorCompany.getMonitorID());
			if(list!=null&&list.size()>0){
				lasted = list.get(0);
				if(list.size()==1){
					//第一条自定义的 oldRiskSituation 设置为 monitorCompany 中 riskSituation(系统风险)
					lasted.setOldRiskSituation(monitorCompany.getRiskSituation());
				}
			}
			if(list!=null&&list.size()>1){
				//构造历史
				Map<String,Object> historyRiskChangeItemMap = null;
				List<Map<String,Object>> historyRiskChangList = new ArrayList<Map<String,Object>>();
				//i=1,从倒数第二条开始算是历史
				for(int i=1;i<list.size();i++){
					if(i==list.size()-1){
						//第一条自定义的 oldRiskSituation 设置为 monitorCompany 中 riskSituation(系统风险)
						list.get(i).setOldRiskSituation(monitorCompany.getRiskSituation());
					}
					historyRiskChangeItemMap = this.getRiskChangeDesc(list.get(i));
					historyRiskChangList.add(historyRiskChangeItemMap);
				}
				data.put("historyRiskChangList", historyRiskChangList);
			}
		}
		
		Map<String,Object> riskChangeMap = this.getRiskChangeDesc(lasted);
		data.put("riskChange",riskChangeMap.get("change"));
		data.put("riskChangeReason",riskChangeMap.get("reason"));
		
		
		return data;
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
	
	
	
	public void testPrintTemplate(HttpServletRequest request,String path){
		
		try{
			
			InputStream in = new FileInputStream(new File(path));  
			XSSFWorkbook work = new XSSFWorkbook(in);  
			
			// 得到excel的第0张表  
			XSSFSheet sheet = work.getSheetAt(0);  
			Row row = null;
			Cell cell = null;
			for(int i=sheet.getFirstRowNum();i<=sheet.getLastRowNum();i++){
				
				row = sheet.getRow(i);
				System.out.println("#################### 第 "+(i)+" 行 ######################");
				for(int j=row.getFirstCellNum();j<=row.getLastCellNum();j++){
					cell = row.getCell(j);
					System.out.println("-------positon=("+i+","+j+"),value="+cell);
				}
				
			}
			
		} catch (FileNotFoundException e) {  
			LoggerUtils.error("文件路径错误", e);
		} catch (IOException e) {  
			LoggerUtils.error("文件输入流错误", e);
		}
	}

}
