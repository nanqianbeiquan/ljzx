/**   
* @Title: CompanyInfo.java 
* @Package com.srd.ljzd.entity.company 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年11月17日 下午1:21:02 
* @version V1.0   
*/
package com.srd.ljzd.entity.company;

import java.io.Serializable;
import java.util.Date;


/** 
 * @ClassName: CompanyInfo
 * @Description: 公司信息
 * @author shiyong
 * @date 2016年11月17日 下午1:21:02
 *  
 */
public class CompanyInfo implements Serializable{
	private static final long serialVersionUID = 1L;

	/**
	* @Fields registerInfo : 工商登记信息 
	*/
	private RegisterInfo registerInfo;
	
	/**
	* @Fields monitorId : 监控企业id
	*/
	private String monitorId;
	
	/**
	* @Fields monitorFlag : 动态监控标志(0：未监控，1：已监控)
	*/
	private String monitorFlag;
	
	/**
	* @Fields monitorDate : 动态监控日期
	*/
	private Date monitorDate;
	
	/**
	* @Fields riskId : 风险id
	*/
	private String riskId;

	private boolean existFlag;
	
	/**
	 * @return the registerInfo
	 */
	public RegisterInfo getRegisterInfo() {
		return registerInfo;
	}

	/**
	 * @param registerInfo the registerInfo to set
	 */
	public void setRegisterInfo(RegisterInfo registerInfo) {
		this.registerInfo = registerInfo;
	}
	
	/**
	 * @return the monitorId
	 */
	public String getMonitorId() {
		return monitorId;
	}

	/**
	 * @param monitorId the monitorId to set
	 */
	public void setMonitorId(String monitorId) {
		this.monitorId = monitorId;
	}

	/**
	 * @return the monitorFlag
	 */
	public String getMonitorFlag() {
		return monitorFlag;
	}

	/**
	 * @param monitorFlag the monitorFlag to set
	 */
	public void setMonitorFlag(String monitorFlag) {
		this.monitorFlag = monitorFlag;
	}

	/**
	 * @return the monitorDate
	 */
	public Date getMonitorDate() {
		return monitorDate;
	}

	/**
	 * @param monitorDate the monitorDate to set
	 */
	public void setMonitorDate(Date monitorDate) {
		this.monitorDate = monitorDate;
	}

	/**
	 * @return the riskId
	 */
	public String getRiskId() {
		return riskId;
	}

	/**
	 * @param riskId the riskId to set
	 */
	public void setRiskId(String riskId) {
		this.riskId = riskId;
	}

	public boolean isExistFlag() {
		return existFlag;
	}

	public void setExistFlag(boolean existFlag) {
		this.existFlag = existFlag;
	}

	
	
}
