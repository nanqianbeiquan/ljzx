/**   
* @Title: BlacklistCompany.java 
* @Package com.srd.ljzd.entity.blacklist 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年3月29日 下午4:26:07 
* @version V1.0   
*/
package com.srd.ljzd.entity.blacklist;

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
 * @ClassName: BlacklistCompany
 * @Description: 黑名单企业信息
 * @author shiyong
 * @date 2017年3月29日 下午4:26:07
 *  
 */
@Entity
@Table(name = "blacklistcompany")
public class BlacklistCompany {

	/**
	* @Fields id : 主键
	*/
	private String id;
	/**
	* @Fields accountId : 账号ID
	*/
	private ClientAccount account;
	/**
	* @Fields companyName : 企业名称
	*/
	private String companyName;
	/**
	* @Fields addReason : 加入原因编号，逗号分隔
	*/
	private String addReason;
	/**
	* @Fields otherAddReason : 其他加入原因，客户自定义
	*/
	private String otherAddReason;
	/**
	* @Fields addDate : 加入日期
	*/
	private Date addDate;
	/**
	* @Fields removeReason : 移除原因编号，逗号分隔
	*/
	private String removeReason;
	/**
	* @Fields otherRemoveReason : 其他移除原因，客户自定义
	*/
	private String otherRemoveReason;
	/**
	* @Fields removeDate : 移除日期
	*/
	private Date removeDate;
	/**
	* @Fields createTime : 创建时间
	*/
	private Date createTime;
	/**
	* @Fields lastModifiedTime : 最后修改时间 
	*/
	private Date lastModifiedTime;
	/**
	* @Fields deleteFlag : 是否删除（0：否，1：是）
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
	 * @return the addReason
	 */
	@Column(name = "AddReason")
	public String getAddReason() {
		return addReason;
	}
	/**
	 * @param addReason the addReason to set
	 */
	public void setAddReason(String addReason) {
		this.addReason = addReason;
	}
	/**
	 * @return the otherAddReason
	 */
	@Column(name = "OtherAddReason")
	public String getOtherAddReason() {
		return otherAddReason;
	}
	/**
	 * @param otherAddReason the otherAddReason to set
	 */
	public void setOtherAddReason(String otherAddReason) {
		this.otherAddReason = otherAddReason;
	}
	/**
	 * @return the addDate
	 */
	@Column(name = "AddDate")
	public Date getAddDate() {
		return addDate;
	}
	/**
	 * @param addDate the addDate to set
	 */
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	/**
	 * @return the removeReason
	 */
	@Column(name = "RemoveReason")
	public String getRemoveReason() {
		return removeReason;
	}
	/**
	 * @param removeReason the removeReason to set
	 */
	public void setRemoveReason(String removeReason) {
		this.removeReason = removeReason;
	}
	/**
	 * @return the otherRemoveReason
	 */
	@Column(name = "OtherRemoveReason")
	public String getOtherRemoveReason() {
		return otherRemoveReason;
	}
	/**
	 * @param otherRemoveReason the otherRemoveReason to set
	 */
	public void setOtherRemoveReason(String otherRemoveReason) {
		this.otherRemoveReason = otherRemoveReason;
	}
	/**
	 * @return the removeDate
	 */
	@Column(name = "RemoveDate")
	public Date getRemoveDate() {
		return removeDate;
	}
	/**
	 * @param removeDate the removeDate to set
	 */
	public void setRemoveDate(Date removeDate) {
		this.removeDate = removeDate;
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
	@Column(name = "DeleteFlag")
	public String getDeleteFlag() {
		return deleteFlag;
	}
	/**
	 * @param deleteFlag the deleteFlag to set
	 */
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id")
	public ClientAccount getAccount() {
		return account;
	}
	public void setAccount(ClientAccount account) {
		this.account = account;
	}
	

}
