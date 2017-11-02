/**   
* @Title: MonitorGroupCompany.java 
* @Package com.srd.ljzd.entity.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年10月31日 上午9:14:43 
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

import com.srd.ljzd.entity.monitor.MonitorCompany;
import com.srd.ljzd.entity.monitor.MonitorGroup;

/** 
 * @ClassName: MonitorGroupCompany
 * @Description: 动态监控分组和公司关联信息
 * @author shiyong
 * @date 2016年10月31日 上午9:14:43
 *  
 */
@Entity
@Table(name = "monitor_group_company")
public class MonitorGroupCompany {

	/**
	* @Fields id : 主键
	*/
	private String id;
	/**
	* @Fields monitorGroup : 分组信息
	*/
	private MonitorGroup monitorGroup;
	/**
	* @Fields monitorCompany : 公司信息
	*/
	private MonitorCompany monitorCompany;
	/**
	* @Fields createTime : 创建时间
	*/
	private Date createTime;
	
	/**
	 * @return the id
	 */
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false, length = 36)
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
	 * @return the monitorGroup
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id")
	public MonitorGroup getMonitorGroup() {
		return monitorGroup;
	}
	/**
	 * @param monitorGroup the monitorGroup to set
	 */
	public void setMonitorGroup(MonitorGroup monitorGroup) {
		this.monitorGroup = monitorGroup;
	}
	/**
	 * @return the monitorCompany
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "monitor_id")
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
	 * @return the createTime
	 */
	@Column(name = "create_time")
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
