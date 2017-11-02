/**   
* @Title: BlacklistReason.java 
* @Package com.srd.ljzd.entity.blacklist 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年3月29日 下午4:18:10 
* @version V1.0   
*/
package com.srd.ljzd.entity.blacklist;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * @ClassName: BlacklistReason
 * @Description: 黑名单原因
 * @author shiyong
 * @date 2017年3月29日 下午4:18:10
 *  
 */
@Entity
@Table(name = "blacklistreason")
public class BlacklistReason {
	
	/**
	* @Fields id : 主键
	*/
	private String id;
	/**
	* @Fields number : 原因编号
	*/
	private String number;
	/**
	* @Fields reason : 原因内容
	*/
	private String reason;
	/**
	* @Fields sort : 排序
	*/
	private Integer sort;
	/**
	* @Fields type : 原因类型（1：加入原因，2：删除原因）
	*/
	private String type;
	/**
	* @Fields userDefinedFlag : 是否为用户自定义原因（0：否，1：是）
	*/
	private String userDefinedFlag;
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
	@Id
	@Column(name = "ID")
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
	 * @return the number
	 */
	@Column(name = "Number")
	public String getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	/**
	 * @return the reason
	 */
	@Column(name = "Reason")
	public String getReason() {
		return reason;
	}
	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	/**
	 * @return the sort
	 */
	@Column(name = "Sort")
	public Integer getSort() {
		return sort;
	}
	/**
	 * @param sort the sort to set
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	/**
	 * @return the type
	 */
	@Column(name = "Type")
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the userDefinedFlag
	 */
	@Column(name = "UserDefinedFlag")
	public String getUserDefinedFlag() {
		return userDefinedFlag;
	}
	/**
	 * @param userDefinedFlag the userDefinedFlag to set
	 */
	public void setUserDefinedFlag(String userDefinedFlag) {
		this.userDefinedFlag = userDefinedFlag;
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
	
	

}
