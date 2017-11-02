/**   
* @Title: MonitorCustomRiskSituation.java 
* @Package com.srd.ljzd.entity.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年4月17日 下午4:05:40 
* @version V1.0   
*/
package com.srd.ljzd.entity.monitor;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

/** 
 * @ClassName: MonitorCustomRiskSituation
 * @Description: 客户自定义风险状况
 * @author shiyong
 * @date 2017年4月17日 下午4:05:40
 *  
 */
@Entity
@Table(name = "monitorcustomrisksituation")
public class MonitorCustomRiskSituation {

	/**
	* @Fields riskId : 风险ID
	*/
	private String riskId;
	/**
	* @Fields monitorCompany : 监控企业
	*/
	private MonitorCompany monitorCompany;
	/**
	* @Fields riskSituation : 风险状况（0：正常，1：关注，2：一般预警，3：特别预警）
	*/
	private String riskSituation;
	/**
	* @Fields oldRiskSituation : 原风险状况（0：正常，1：关注，2：一般预警，3：特别预警）
	*/
	private String oldRiskSituation;
	/**
	* @Fields createTime : 创建时间
	*/
	private Date createTime;
	/**
	* @Fields lastModifiedTime : 最后修改时间
	*/
	private Date lastModifiedTime;
	/**
	* @Fields deleteFlag : 删除标志（0：否，1：是）
	*/
	private String deleteFlag;
	
	private Set<MonitorCustomRiskInfo> riskInfoSet;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "monitorCustomRiskSituation")
	public Set<MonitorCustomRiskInfo> getRiskInfoSet() {
		return riskInfoSet;
	}
	public void setRiskInfoSet(Set<MonitorCustomRiskInfo> riskInfoSet) {
		this.riskInfoSet = riskInfoSet;
	}
	/**
	 * @return the riskId
	 */
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "RiskID", unique = true, nullable = false, length = 36)
	public String getRiskId() {
		return riskId;
	}
	/**
	 * @param riskId the riskId to set
	 */
	public void setRiskId(String riskId) {
		this.riskId = riskId;
	}
	/**
	 * @return the monitorCompany
	 */
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MonitorID")
	public MonitorCompany getMonitorCompany() {
		return monitorCompany;
	}
	/**
	 * @param monitorCompany the monitorCompany to set
	 */
	public void setMonitorCompany(MonitorCompany monitorCompany) {
		this.monitorCompany = monitorCompany;
	}
	/**
	 * @return the riskSituation
	 */
	@Column(name = "RiskSituation")
	public String getRiskSituation() {
		return riskSituation;
	}
	/**
	 * @param riskSituation the riskSituation to set
	 */
	public void setRiskSituation(String riskSituation) {
		this.riskSituation = riskSituation;
	}
	/**
	 * @return the oldRiskSituation
	 */
	@Column(name = "OldRiskSituation")
	public String getOldRiskSituation() {
		return oldRiskSituation;
	}
	/**
	 * @param oldRiskSituation the oldRiskSituation to set
	 */
	public void setOldRiskSituation(String oldRiskSituation) {
		this.oldRiskSituation = oldRiskSituation;
	}
	/**
	 * @return the createTime
	 */
	@Column(name = "CreateTime")
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the lastModifiedTime
	 */
	@Column(name = "LastModifiedTime")
	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}
	/**
	 * @param lastModifiedTime the lastModifiedTime to set
	 */
	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}
	/**
	 * @return the deleteFlag
	 */
	@Column(name = "DeleteFlag")
	public String getDeleteFlag() {
		return deleteFlag;
	}
	/**
	 * @param deleteFlag the deleteFlag to set
	 */
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	
	
}
