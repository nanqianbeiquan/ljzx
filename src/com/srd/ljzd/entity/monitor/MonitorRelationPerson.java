/**   
* @Title: MonitorRelationPerson.java 
* @Package com.srd.ljzd.entity.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年11月3日 下午1:20:08 
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
 * @ClassName: MonitorRelationPerson
 * @Description: 动态监控关联个人信息
 * @author shiyong
 * @date 2016年11月3日 下午1:20:08
 *  
 */
@Entity
@Table(name = "monitorrelationperson")
public class MonitorRelationPerson {
	/**
	* @Fields id : 主键
	*/
	private String id;
	/**
	* @Fields monitorId : 主体企业ID
	*/
	private String monitorId;
	/**
	* @Fields accountId : 账户ID
	*/
	private String accountId;
	/**
	* @Fields name : 姓名
	*/
	private String name;
	/**
	* @Fields idNumber : 身份证号
	*/
	private String idNumber;
	/**
	* @Fields province : 省
	*/
	private String province;
	/**
	* @Fields city : 市
	*/
	private String city;
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
	* @Fields riskLabel : 个人风险标签
	*/
	private String riskLabel;
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
	 * @return the accountId
	 */
	@Column(name = "account_id")
	public String getAccountId() {
		return accountId;
	}
	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	/**
	 * @return the name
	 */
	@Column(name = "Name")
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the idNumber
	 */
	@Column(name = "IDNumber")
	public String getIdNumber() {
		return idNumber;
	}
	/**
	 * @param idNumber the idNumber to set
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
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
	 * @return the city
	 */
	@Column(name = "City")
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
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
