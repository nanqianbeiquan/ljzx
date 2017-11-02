/**   
* @Title: MonitorPersonEvent.java 
* @Package com.srd.ljzd.entity.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年11月3日 下午1:43:23 
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
 * @ClassName: MonitorPersonEvent
 * @Description: 动态监控个人事件信息
 * @author shiyong
 * @date 2016年11月3日 下午1:43:23
 *  
 */
@Entity
@Table(name = "monitorpersonevent")
public class MonitorPersonEvent {

	/**
	* @Fields eventID : 事件ID
	*/
	private String eventID;
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
