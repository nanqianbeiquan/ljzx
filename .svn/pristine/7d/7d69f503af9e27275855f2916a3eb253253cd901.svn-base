package com.srd.ljzd.service.monitor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.srd.ljzd.entity.company.CompanyInfo;
import com.srd.ljzd.entity.monitor.MonitorCompany;

/**
 * 下载报告服务组件
 * @author Administrator
 *
 */
public interface DownloadReportService {
	
	/**
	 * 生成报告
	 * @param templatePath 报告模板
	 * @param data  数据
	 */
	public XSSFWorkbook buildReport(String templatePath,Map<String,Object> data);
	
	/**
	 * 构造报告需要的数据
	 * @param companyRisk
	 * @param company
	 * @param relCompanyList
	 * @param relPersonList
	 * @return
	 */
	public Map<String, Object> buildReportData(MonitorCompany monitorCompany,CompanyInfo company);
	
	public void testPrintTemplate(HttpServletRequest request,String path);
}
