/**   
* @Title: MonitorRelationCompany.java 
* @Package com.srd.ljzd.entity.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年10月31日 上午9:26:11 
* @version V1.0   
*/
package com.srd.ljzd.entity.monitor;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/** 
 * @ClassName: MonitorRelationCompany
 * @Description: 动态监控关联企业信息
 * @author shiyong
 * @date 2016年10月31日 上午9:26:11
 *  
 */
@Entity
@Table(name = "monitorrelationcompany")
public class MonitorRelationCompany {
	/**
	* @Fields id : 主键 
	*/
	private String id;
	/**
	* @Fields monitorId : 主体企业ID
	*/
	private String monitorId;
	/**
	* @Fields accountID : 账户ID
	*/
	private String accountID;
	/**
	* @Fields companyId : 企业ID
	*/
	private String companyId;
	/**
	* @Fields companyName : 公司名称
	*/
	private String companyName;
	/**
	* @Fields province : 省份
	*/
	private String province;
	/**
	* @Fields areaName : 区域名称
	*/
	private String areaName;
	/**
	* @Fields monitorDate : 监控日期
	*/
	private Date monitorDate;
	/**
	* @Fields infoShowDate : 信息展示日期
	*/
	private Date infoShowDate;
	/**
	* @Fields eventLevel : 事件等级（1：正常，2：关注，3：预警）
	*/
	private String eventLevel;
	/**
	* @Fields newEventFlag : 新事件标志（0：无新事件，1：有新事件）
	*/
	private String newEventFlag;
	/**
	* @Fields normalEventNum : 正常事件数量
	*/
	private Integer normalEventNum;
	/**
	* @Fields attentionEventNum : 关注事件数量
	*/
	private Integer attentionEventNum;
	/**
	* @Fields warnEventNum : 预警事件数量
	*/
	private Integer warnEventNum;
	/**
	* @Fields riskSituation : 风险状况（0：正常，1：关注，2：一般预警，3：特别预警）
	*/
	private String riskSituation;
	/**
	* @Fields riskLabel : 关系企业风险标签
	*/
	private String riskLabel;
	/**
	* @Fields companyBackgroundIndex : 企业背景指数
	*/
	private Integer companyBackgroundIndex;
	/**
	* @Fields judicialLitigationIndex : 司法涉诉指数
	*/
	private Integer judicialLitigationIndex;
	/**
	* @Fields operateExceptionIndex : 经营异常指数
	*/
	private Integer operateExceptionIndex;
	/**
	* @Fields negativeNewsIndex : 负面舆情指数 
	*/
	private Integer negativeNewsIndex;
	/**
	* @Fields developmentPotentialIndex : 发展潜力指数
	*/
	private Integer developmentPotentialIndex;
	/**
	* @Fields renameStatus : 企业更名状态(0:未更名，1：已更名)
	*/
	private String renameStatus;
	/**
	* @Fields createTime : 创建时间
	*/
	private Date createTime;
	/**
	* @Fields lastModifiedTime : 最后修改时间
	*/
	private Date lastModifiedTime;
	/**
	* @Fields deleteFlag : 删除标志（0：正常，1：已删除）
	*/
	private String deleteFlag;
	
	/**
	 * @return the id
	 */
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 36)
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the monitorId
	 */
	@Column(name = "MonitorID")
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
	 * @return the accountID
	 */
	@Column(name = "account_id")
	public String getAccountID() {
		return accountID;
	}
	/**
	 * @param accountID the accountID to set
	 */
	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}
	/**
	 * @return the companyId
	 */
	@Column(name = "company_id")
	public String getCompanyId() {
		return companyId;
	}
	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	/**
	 * @return the companyName
	 */
	@Column(name = "CompanyName")
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * @return the province
	 */
	@Column(name = "Province")
	public String getProvince() {
		return province;
	}
	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * @return the areaName
	 */
	@Column(name = "AreaName")
	public String getAreaName() {
		return areaName;
	}
	/**
	 * @param areaName the areaName to set
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	/**
	 * @return the monitorDate
	 */
	@Column(name = "MonitorDate")
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
	 * @return the infoShowDate
	 */
	@Column(name = "InfoShowDate")
	public Date getInfoShowDate() {
		return infoShowDate;
	}
	/**
	 * @param infoShowDate the infoShowDate to set
	 */
	public void setInfoShowDate(Date infoShowDate) {
		this.infoShowDate = infoShowDate;
	}
	/**
	 * @return the eventLevel
	 */
	@Column(name = "EventLevel")
	public String getEventLevel() {
		return eventLevel;
	}
	/**
	 * @param eventLevel the eventLevel to set
	 */
	public void setEventLevel(String eventLevel) {
		this.eventLevel = eventLevel;
	}
	/**
	 * @return the newEventFlag
	 */
	@Column(name = "NewEventFlag")
	public String getNewEventFlag() {
		return newEventFlag;
	}
	/**
	 * @param newEventFlag the newEventFlag to set
	 */
	public void setNewEventFlag(String newEventFlag) {
		this.newEventFlag = newEventFlag;
	}
	/**
	 * @return the normalEventNum
	 */
	@Column(name = "NormalEventNum")
	public Integer getNormalEventNum() {
		return normalEventNum;
	}
	/**
	 * @param normalEventNum the normalEventNum to set
	 */
	public void setNormalEventNum(Integer normalEventNum) {
		this.normalEventNum = normalEventNum;
	}
	/**
	 * @return the attentionEventNum
	 */
	@Column(name = "AttentionEventNum")
	public Integer getAttentionEventNum() {
		return attentionEventNum;
	}
	/**
	 * @param attentionEventNum the attentionEventNum to set
	 */
	public void setAttentionEventNum(Integer attentionEventNum) {
		this.attentionEventNum = attentionEventNum;
	}
	/**
	 * @return the warnEventNum
	 */
	@Column(name = "WarnEventNum")
	public Integer getWarnEventNum() {
		return warnEventNum;
	}
	/**
	 * @param warnEventNum the warnEventNum to set
	 */
	public void setWarnEventNum(Integer warnEventNum) {
		this.warnEventNum = warnEventNum;
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
	 * @return the riskLabel
	 */
	@Column(name = "RiskLabel")
	public String getRiskLabel() {
		return riskLabel;
	}
	/**
	 * @param riskLabel the riskLabel to set
	 */
	public void setRiskLabel(String riskLabel) {
		this.riskLabel = riskLabel;
	}
	/**
	 * @return the companyBackgroundIndex
	 */
	@Column(name = "CompanyBackgroundIndex")
	public Integer getCompanyBackgroundIndex() {
		return companyBackgroundIndex;
	}
	/**
	 * @param companyBackgroundIndex the companyBackgroundIndex to set
	 */
	public void setCompanyBackgroundIndex(Integer companyBackgroundIndex) {
		this.companyBackgroundIndex = companyBackgroundIndex;
	}
	/**
	 * @return the judicialLitigationIndex
	 */
	@Column(name = "JudicialLitigationIndex")
	public Integer getJudicialLitigationIndex() {
		return judicialLitigationIndex;
	}
	/**
	 * @param judicialLitigationIndex the judicialLitigationIndex to set
	 */
	public void setJudicialLitigationIndex(Integer judicialLitigationIndex) {
		this.judicialLitigationIndex = judicialLitigationIndex;
	}
	/**
	 * @return the operateExceptionIndex
	 */
	@Column(name = "OperateExceptionIndex")
	public Integer getOperateExceptionIndex() {
		return operateExceptionIndex;
	}
	/**
	 * @param operateExceptionIndex the operateExceptionIndex to set
	 */
	public void setOperateExceptionIndex(Integer operateExceptionIndex) {
		this.operateExceptionIndex = operateExceptionIndex;
	}
	/**
	 * @return the negativeNewsIndex
	 */
	@Column(name = "NegativeNewsIndex")
	public Integer getNegativeNewsIndex() {
		return negativeNewsIndex;
	}
	/**
	 * @param negativeNewsIndex the negativeNewsIndex to set
	 */
	public void setNegativeNewsIndex(Integer negativeNewsIndex) {
		this.negativeNewsIndex = negativeNewsIndex;
	}
	/**
	 * @return the developmentPotentialIndex
	 */
	@Column(name = "DevelopmentPotentialIndex")
	public Integer getDevelopmentPotentialIndex() {
		return developmentPotentialIndex;
	}
	/**
	 * @param developmentPotentialIndex the developmentPotentialIndex to set
	 */
	public void setDevelopmentPotentialIndex(Integer developmentPotentialIndex) {
		this.developmentPotentialIndex = developmentPotentialIndex;
	}
	/**
	 * @return the renameStatus
	 */
	@Column(name = "rename_status")
	public String getRenameStatus() {
		return renameStatus;
	}
	/**
	 * @param renameStatus the renameStatus to set
	 */
	public void setRenameStatus(String renameStatus) {
		this.renameStatus = renameStatus;
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
	@Column(name = "delete_flag")
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
