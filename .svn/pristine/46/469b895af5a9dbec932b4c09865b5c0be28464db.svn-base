/**   
* @Title: MonitorDefaultCompany.java 
* @Package com.srd.ljzd.entity.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年9月6日 下午6:20:14 
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

import com.srd.ljzd.entity.company.CompanyBasicInfo;

/** 
 * @ClassName: MonitorDefaultCompany
 * @Description: 默认动态监控公司
 * @author shiyong
 * @date 2017年9月6日 下午6:20:14
 *  
 */
@Entity
@Table(name = "monitor_default_company")
public class MonitorDefaultCompany {
	/**
	* @Fields id : 主键
	*/
	private String id;
	/**
	* @Fields companyBasicInfo : 企业基本信息
	*/
	private CompanyBasicInfo companyBasicInfo;
	/**
	* @Fields creatorID : 创建者
	*/
	private String creatorId;
	/**
	* @Fields createTime : 创建时间
	*/
	private Date createTime;
	/**
	* @Fields modifierID : 修改者
	*/
	private String modifierId;
	/**
	* @Fields lastModifiedTime : 最后修改时间
	*/
	private Date modifyTime;
	/**
	* @Fields deleteFlag : 删除标志（0：否，1：是）
	*/
	private String deleteFlag;
	
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
	 * @return the companyBasicInfo
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	public CompanyBasicInfo getCompanyBasicInfo() {
		return companyBasicInfo;
	}
	/**
	 * @param companyBasicInfo the companyBasicInfo to set
	 */
	public void setCompanyBasicInfo(CompanyBasicInfo companyBasicInfo) {
		this.companyBasicInfo = companyBasicInfo;
	}
	/**
	 * @return the creatorId
	 */
	@Column(name = "creator_id")
	public String getCreatorId() {
		return creatorId;
	}
	/**
	 * @param creatorId the creatorId to set
	 */
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
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
	/**
	 * @return the modifierId
	 */
	@Column(name = "modifier_id")
	public String getModifierId() {
		return modifierId;
	}
	/**
	 * @param modifierId the modifierId to set
	 */
	public void setModifierId(String modifierId) {
		this.modifierId = modifierId;
	}
	/**
	 * @return the modifyTime
	 */
	@Column(name = "modify_time")
	public Date getModifyTime() {
		return modifyTime;
	}
	/**
	 * @param modifyTime the modifyTime to set
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
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
