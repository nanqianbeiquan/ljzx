/**   
* @Title: MonitorCompanyEventState.java 
* @Package com.srd.ljzd.entity.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年2月23日 下午7:28:34 
* @version V1.0   
*/
package com.srd.ljzd.entity.monitor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/** 
 * @ClassName: MonitorCompanyEventState
 * @Description: 动态监控企业事件阅读状态
 * @author shiyong
 * @date 2017年2月23日 下午7:28:34
 *  
 */
@Entity
@Table(name = "MonitorCompanyEventState")
public class MonitorCompanyEventState {
	
	/**
	* @Fields id : 主键
	*/
	private String id;
	/**
	* @Fields eventId : 事件ID
	*/
	private String eventId;
	/**
	* @Fields companyId : 主体企业ID或关系企业ID
	*/
	private String companyId;
	/**
	* @Fields accountId : 账号ID
	*/
	private String accountId;
	/**
	* @Fields state : 阅读状态（0：未读，1：已读）
	*/
	private String state;
	
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
	 * @return the eventId
	 */
	@Column(name = "EventID")
	public String getEventId() {
		return eventId;
	}
	/**
	 * @param eventId the eventId to set
	 */
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	/**
	 * @return the companyId
	 */
	@Column(name = "CompanyID")
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
	 * @return the state
	 */
	@Column(name = "State")
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	
}
