/**   
* @Title: MonitorCompany.java 
* @Package com.srd.ljzd.entity.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年10月31日 上午9:10:33 
* @version V1.0   
*/
package com.srd.ljzd.entity.monitor;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.srd.ljzd.entity.client.ClientAccount;

/** 
 * @ClassName: MonitorCompany
 * @Description: 动态监控公司信息
 * @author shiyong
 * @date 2016年10月31日 上午9:10:33
 *  
 */
@Entity
@Table(name = "monitor_company")
public class MonitorCompany {
	
	/**
	* @Fields monitorID : 监控ID
	*/
	private String monitorID;
	/**
	* @Fields clientAccount : 客户账号
	*/
	private ClientAccount clientAccount;
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
	* @Fields customRiskSituation : 风险状况（0：正常，1：关注，2：一般预警，3：特别预警）
	*/
	private String riskSituation;
	/**
	* @Fields customRiskFlag : 自定义风险标记（0：未自定义，1：已自定义）
	*/
	private String customRiskFlag;
	/**
	* @Fields customRiskSituation : 风险状况（0：正常，1：关注，2：一般预警，3：特别预警）
	*/
	private String customRiskSituation;
	/**
	* @Fields riskStatus : 风险变化（0：不变，1：升高，2：降低）
	*/
	private String riskStatus;
	/**
	* @Fields riskLabel : 企业风险标签
	*/
	private String riskLabel;
	/**
	* @Fields customRiskLabel : 自定义企业风险标签
	*/
	private String customRiskLabel;
	/**
	* @Fields newEventFlag : 新事件标志（0：无新事件，1：有新事件）
	*/
	private String newEventFlag;
	/**
	* @Fields eventLevel : 事件等级（1：正常，2：关注，3：预警）
	*/
	private String eventLevel;
	/**
	* @Fields groupStatus : 分组状态(0:未分组,1:已分组)
	*/
	private String groupStatus;
	/**
	* @Fields monitorDate : 监控日期
	*/
	private Date monitorDate;
	/**
	* @Fields infoShowDate : 信息展示日期
	*/
	private Date infoShowDate;
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
	* @Fields eventNum : 该企业事件总数
	*/
	private Integer eventNum;
	/**
	* @Fields riskResult : 系统风险建议
	*/
	private String riskResult;
	/**
	* @Fields customRiskResult : 自定义风险建议
	*/
	private String customRiskResult;
	/**
	* @Fields eventCheckFlag : 事件检查标记（目前有四项检查内容，使用1111表示四项检查内容的结果，每一位字符代表一项内容，1表示是，0表示否，从左往右每一位分别代表基本信息有无变化、是否有重大负面信息、是否有涉诉信息、是否有重大经营变化信息）
	*/
	private String eventCheckFlag;
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
	 * @return the monitorID
	 */
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "MonitorID", unique = true, nullable = false, length = 36)
	public String getMonitorID() {
		return monitorID;
	}
	/**
	 * @param monitorID the monitorID to set
	 */
	public void setMonitorID(String monitorID) {
		this.monitorID = monitorID;
	}
	/**
	 * @return the clientAccount
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id")
	public ClientAccount getClientAccount() {
		return clientAccount;
	}
	/**
	 * @param clientAccount the clientAccount to set
	 */
	public void setClientAccount(ClientAccount clientAccount) {
		this.clientAccount = clientAccount;
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
	 * @return the customRiskFlag
	 */
	@Column(name = "CustomRiskFlag")
	public String getCustomRiskFlag() {
		return customRiskFlag;
	}
	/**
	 * @param customRiskFlag the customRiskFlag to set
	 */
	public void setCustomRiskFlag(String customRiskFlag) {
		this.customRiskFlag = customRiskFlag;
	}
	/**
	 * @return the customRiskSituation
	 */
	@Column(name = "CustomRiskSituation")
	public String getCustomRiskSituation() {
		return customRiskSituation;
	}
	/**
	 * @param customRiskSituation the customRiskSituation to set
	 */
	public void setCustomRiskSituation(String customRiskSituation) {
		this.customRiskSituation = customRiskSituation;
	}
	/**
	 * @return the riskStatus
	 */
	@Column(name = "RiskStatus")
	public String getRiskStatus() {
		return riskStatus;
	}
	/**
	 * @param riskStatus the riskStatus to set
	 */
	public void setRiskStatus(String riskStatus) {
		this.riskStatus = riskStatus;
	}
	/**
	 * @return the customRiskSituation
	 */
	@Column(name = "RiskSituation")
	public String getRiskSituation() {
		return riskSituation;
	}
	/**
	 * @param customRiskSituation the customRiskSituation to set
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
	 * @return the customRiskLabel
	 */
	@Column(name = "CustomRiskLabel")
	public String getCustomRiskLabel() {
		return customRiskLabel;
	}
	/**
	 * @param customRiskLabel the customRiskLabel to set
	 */
	public void setCustomRiskLabel(String customRiskLabel) {
		this.customRiskLabel = customRiskLabel;
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
	 * @return the groupStatus
	 */
	@Column(name = "GroupStatus")
	public String getGroupStatus() {
		return groupStatus;
	}
	/**
	 * @param groupStatus the groupStatus to set
	 */
	public void setGroupStatus(String groupStatus) {
		this.groupStatus = groupStatus;
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
	 * @return the eventNum
	 */
	@Column(name = "EventNum")
	public Integer getEventNum() {
		return eventNum;
	}
	/**
	 * @param eventNum the eventNum to set
	 */
	public void setEventNum(Integer eventNum) {
		this.eventNum = eventNum;
	}
	/**
	 * @return the riskResult
	 */
	@Column(name = "RiskResult")
	public String getRiskResult() {
		return riskResult;
	}
	/**
	 * @param riskResult the riskResult to set
	 */
	public void setRiskResult(String riskResult) {
		this.riskResult = riskResult;
	}
	/**
	 * @return the customRiskResult
	 */
	@Column(name = "CustomRiskResult")
	public String getCustomRiskResult() {
		return customRiskResult;
	}
	/**
	 * @param customRiskResult the customRiskResult to set
	 */
	public void setCustomRiskResult(String customRiskResult) {
		this.customRiskResult = customRiskResult;
	}
	/**
	 * @return the eventCheckFlag
	 */
	@Column(name = "EventCheckFlag")
	public String getEventCheckFlag() {
		return eventCheckFlag;
	}
	/**
	 * @param eventCheckFlag the eventCheckFlag to set
	 */
	public void setEventCheckFlag(String eventCheckFlag) {
		this.eventCheckFlag = eventCheckFlag;
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
