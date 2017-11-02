/**   
* @Title: MonitorCompanyEvent.java 
* @Package com.srd.ljzd.entity.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年11月1日 下午4:18:46 
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
 * @ClassName: MonitorCompanyEvent
 * @Description: 动态监控公司事件信息
 * @author shiyong
 * @date 2016年11月1日 下午4:18:46
 *  
 */
@Entity
@Table(name = "monitorcompanyevent")
public class MonitorCompanyEvent {
	/**
	* @Fields eventID : 事件ID
	*/
	private String eventID;
	/**
	* @Fields companyName : 公司名称
	*/
	private String companyName;
	/**
	* @Fields eventType : 事件类型
	*/
	private String eventType;
	/**
	* @Fields eventSubType : 事件子类型
	*/
	private String eventSubType;
	/**
	* @Fields eventLevel : 事件等级
	*/
	private String eventLevel;
	/**
	* @Fields eventLabel : 事件标签
	*/
	private String eventLabel;
	/**
	* @Fields happenDate : 发生日期
	*/
	private Date happenDate;
	/**
	* @Fields contentDate : 事件对应内容的发生时间
	*/
	private Date contentDate;
	/**
	* @Fields createTime : 创建时间
	*/
	private Date createTime;
	
	/**
	 * @return the eventID
	 */
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "EventID", unique = true, nullable = false, length = 36)
	public String getEventID() {
		return eventID;
	}
	/**
	 * @param eventID the eventID to set
	 */
	public void setEventID(String eventID) {
		this.eventID = eventID;
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
	 * @return the eventType
	 */
	@Column(name = "EventType")
	public String getEventType() {
		return eventType;
	}
	/**
	 * @param eventType the eventType to set
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	/**
	 * @return the eventSubType
	 */
	@Column(name = "EventSubType")
	public String getEventSubType() {
		return eventSubType;
	}
	/**
	 * @param eventSubType the eventSubType to set
	 */
	public void setEventSubType(String eventSubType) {
		this.eventSubType = eventSubType;
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
	 * @return the eventLabel
	 */
	@Column(name = "EventLabel")
	public String getEventLabel() {
		return eventLabel;
	}
	/**
	 * @param eventLabel the eventLabel to set
	 */
	public void setEventLabel(String eventLabel) {
		this.eventLabel = eventLabel;
	}
	/**
	 * @return the happenDate
	 */
	@Column(name = "HappenDate")
	public Date getHappenDate() {
		return happenDate;
	}
	/**
	 * @param happenDate the happenDate to set
	 */
	public void setHappenDate(Date happenDate) {
		this.happenDate = happenDate;
	}
	/**
	 * @return the contentDate
	 */
	@Column(name = "ContentDate")
	public Date getContentDate() {
		return contentDate;
	}
	/**
	 * @param contentDate the contentDate to set
	 */
	public void setContentDate(Date contentDate) {
		this.contentDate = contentDate;
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
	
	

}
