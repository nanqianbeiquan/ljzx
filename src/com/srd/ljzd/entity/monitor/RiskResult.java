/**  
 * @Title: SearchResult.java
 * @Package com.srd.ljzd.entity.biz
 * @Description: TODO(用一句话描述该文件做什么)
 * @author zihui.pei
 * @date 2016年5月17日 下午6:35:49
 * @version V1.2  
 */
package com.srd.ljzd.entity.monitor;

import java.io.Serializable;
import java.util.Date;

/**
 * 版权所有：2016 项目名称：ljzd1.3
 *
 * 类描述： 风险结果对象； 类名称：com.srd.ljzd.entity.biz.RiskResult 创建人：裴子辉 创建时间：2016年6月2日
 * 上午11:47:55 修改人： 修改时间：2016年6月2日 上午11:47:55 修改备注：
 * 
 * @version V1.3
 */

public class RiskResult implements Serializable {
	private static final long serialVersionUID = 1L;

	private String companyName;
	private String riskValuefixed;// 风险值
	private String riskRatefixed; // 风险等级
	private String dateStr;
	private Date date;
	private String colsValues;
	
	private String bizInfoRiskFlag="0"; // 工商信息风险   0 无风险，1 有风险
	private String lawInfoRiskFlag="0"; // 司法信息风险	 0 无风险，1 有风险
	private String newsInfoRiskFlag="0";// 舆情信息风险   0 无风险，1 有风险
	private String bizGroupInfoRiskFlag="0";//企业族谱信息风险 0 无风险，1 有风险
	
	

	public RiskResult(){};


	public RiskResult(String companyName, String riskValuefixed,
			String riskRatefixed, String dateStr, Date date, String colsValues,
			String bizInfoRiskFlag, String lawInfoRiskFlag,
			String newsInfoRiskFlag, String bizGroupInfoRiskFlag) {
		super();
		this.companyName = companyName;
		this.riskValuefixed = riskValuefixed;
		this.riskRatefixed = riskRatefixed;
		this.dateStr = dateStr;
		this.date = date;
		this.colsValues = colsValues;
		this.bizInfoRiskFlag = bizInfoRiskFlag;
		this.lawInfoRiskFlag = lawInfoRiskFlag;
		this.newsInfoRiskFlag = newsInfoRiskFlag;
		this.bizGroupInfoRiskFlag = bizGroupInfoRiskFlag;
	}


	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getRiskValuefixed() {
		return riskValuefixed;
	}

	public void setRiskValuefixed(String riskValuefixed) {
		this.riskValuefixed = riskValuefixed;
	}

	public String getRiskRatefixed() {
		return riskRatefixed;
	}

	public void setRiskRatefixed(String riskRatefixed) {
		this.riskRatefixed = riskRatefixed;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	public String getColsValues() {
		return colsValues;
	}
	public void setColsValues(String colsValues) {
		this.colsValues = colsValues;
	}


	public String getBizInfoRiskFlag() {
		return bizInfoRiskFlag;
	}


	public void setBizInfoRiskFlag(String bizInfoRiskFlag) {
		this.bizInfoRiskFlag = bizInfoRiskFlag;
	}


	public String getLawInfoRiskFlag() {
		return lawInfoRiskFlag;
	}


	public void setLawInfoRiskFlag(String lawInfoRiskFlag) {
		this.lawInfoRiskFlag = lawInfoRiskFlag;
	}


	public String getNewsInfoRiskFlag() {
		return newsInfoRiskFlag;
	}


	public void setNewsInfoRiskFlag(String newsInfoRiskFlag) {
		this.newsInfoRiskFlag = newsInfoRiskFlag;
	}


	public String getBizGroupInfoRiskFlag() {
		return bizGroupInfoRiskFlag;
	}


	public void setBizGroupInfoRiskFlag(String bizGroupInfoRiskFlag) {
		this.bizGroupInfoRiskFlag = bizGroupInfoRiskFlag;
	}

	
}
