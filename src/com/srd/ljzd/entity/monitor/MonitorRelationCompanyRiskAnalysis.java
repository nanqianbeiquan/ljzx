/**   
* @Title: MonitorRelationCompanyRiskAnalysis.java 
* @Package com.srd.ljzd.entity.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年5月12日 上午10:51:50 
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
 * @ClassName: MonitorRelationCompanyRiskAnalysis
 * @Description: 动态监控关系企业风险分析
 * @author shiyong
 * @date 2017年5月12日 上午10:51:50
 *  
 */
@Entity
@Table(name = "monitorrelationcompanyriskanalysis")
public class MonitorRelationCompanyRiskAnalysis {

	/**
	* @Fields id : 主键
	*/
	private String id;
	/**
	* @Fields relationCompanyMonitorId : 关系企业监控ID
	*/
	private String relationCompanyMonitorId;
	/**
	* @Fields eventType : 事件类型
	*/
	private String eventType;
	/**
	* @Fields eventSubType : 事件子类型
	*/
	private String eventSubType;
	/**
	* @Fields eventLevel : 该类别事件最高等级
	*/
	private String eventLevel;
	/**
	* @Fields eventNum : 该类事件数量
	*/
	private Integer eventNum;
	/**
	* @Fields createTime : 创建时间
	*/
	private Date createTime;
	/**
	* @Fields lastModifiedTime : 最后修改时间
	*/
	private Date lastModifiedTime;
	
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
	 * @return the relationCompanyMonitorId
	 */
	@Column(name = "RelationCompanyMonitorId")
	public String getRelationCompanyMonitorId() {
		return relationCompanyMonitorId;
	}
	/**
	 * @param relationCompanyMonitorId the relationCompanyMonitorId to set
	 */
	public void setRelationCompanyMonitorId(String relationCompanyMonitorId) {
		this.relationCompanyMonitorId = relationCompanyMonitorId;
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
	
	

}
