/**   
* @Title: MonitorCompanyEventMapping.java 
* @Package com.srd.ljzd.entity.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年3月23日 上午10:52:28 
* @version V1.0   
*/
package com.srd.ljzd.entity.monitor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * @ClassName: MonitorCompanyEventMapping
 * @Description: 动态监控企业事件存储映射
 * @author shiyong
 * @date 2017年3月23日 上午10:52:28
 *  
 */
@Entity
@Table(name = "monitorcompanyeventmapping")
public class MonitorCompanyEventMapping {

	/**
	* @Fields id : 主键
	*/
	private Integer id;
	/**
	* @Fields companyName : 企业名称
	*/
	private String companyName;
	
	/**
	 * @return the id
	 */
	@Id
	@Column(name = "ID")
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
	
	
}
