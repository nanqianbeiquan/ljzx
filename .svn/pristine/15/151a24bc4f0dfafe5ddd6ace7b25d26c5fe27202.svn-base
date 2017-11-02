/**   
* @Title: CompanyBasicInfo.java 
* @Package com.srd.ljzd.entity.company 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年6月23日 上午9:49:23 
* @version V1.0   
*/
package com.srd.ljzd.entity.company;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/** 
 * @ClassName: CompanyBasicInfo
 * @Description: 企业基本信息
 * @author shiyong
 * @date 2017年6月23日 上午9:49:23
 *  
 */
@Entity
@Table(name = "company_basic_info")
public class CompanyBasicInfo {
	/**
	* @Fields companyId : 企业ID
	*/
	private String companyId;
	/**
	* @Fields companyName : 企业名称
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
	* @Fields eventMapping : 企业事件表映射
	*/
	private Integer eventMapping;
	/**
	* @Fields createTime : 创建时间
	*/
	private Date createTime;
	
	/**
	 * @return the companyId
	 */
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "company_id", unique = true, nullable = false, length = 36)
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
	@Column(name = "company_name")
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
	@Column(name = "province")
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
	@Column(name = "area_name")
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
	 * @return the eventMapping
	 */
	@Column(name = "event_mapping")
	public Integer getEventMapping() {
		return eventMapping;
	}
	/**
	 * @param eventMapping the eventMapping to set
	 */
	public void setEventMapping(Integer eventMapping) {
		this.eventMapping = eventMapping;
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
