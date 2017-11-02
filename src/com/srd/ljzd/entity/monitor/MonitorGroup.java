/**   
* @Title: MonitorGroup.java 
* @Package com.srd.ljzd.entity.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年10月28日 下午5:51:19 
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
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.srd.ljzd.entity.client.ClientAccount;

/** 
 * @ClassName: MonitorGroup
 * @Description: 动态监控分组信息
 * @author shiyong
 * @date 2016年10月28日 下午5:51:19
 *  
 */
@Entity
@Table(name = "monitor_group")
public class MonitorGroup {
	/**
	* @Fields groupId : 分组ID
	*/
	private String groupId;
	/**
	* @Fields clientAccount : 客户账号
	*/
	private ClientAccount clientAccount;
	/**
	* @Fields groupName : 分组名称
	*/
	private String groupName;
	/**
	* @Fields canDeleteFlag : 是否可删除（0：不可删除，1：可以删除）
	*/
	private String canDeleteFlag;
	/**
	* @Fields companyNum : 公司数量
	*/
	private Integer companyNum;
	/**
	* @Fields sort : 排序
	*/
	private Integer sort;
	/**
	* @Fields icon : 背景图片地址
	*/
	private String icon;
	/**
	* @Fields remark : 备注
	*/
	private String remark;
	/**
	* @Fields createTime : 创建时间
	*/
	private Date createTime;
	/**
	* @Fields modifyTime : 最后修改时间
	*/
	private Date modifyTime;
	/**
	* @Fields deleteFlag : 删除标志（0：未删除，1：已删除）
	*/
	private String deleteFlag;
	
	public MonitorGroup() {
	}
	public MonitorGroup(String groupId, String groupName) {
		this.groupId = groupId;
		this.groupName = groupName;
	}
	/**
	 * @return the groupId
	 */
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "group_id", unique = true, nullable = false, length = 36)
	public String getGroupId() {
		return groupId;
	}
	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	/**
	 * @return the clientAccount
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id")
	@JsonIgnore
	public ClientAccount getClientAccount() {
		return clientAccount;
	}
	/**
	 * @param clientAccount the clientAccount to set
	 */
	public void setClientAccount(ClientAccount clientAccount) {
		this.clientAccount = clientAccount;
	}
	/**
	 * @return the groupName
	 */
	@Column(name = "group_name")
	public String getGroupName() {
		return groupName;
	}
	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	/**
	 * @return the canDeleteFlag
	 */
	@Column(name = "can_delete_flag")
	public String getCanDeleteFlag() {
		return canDeleteFlag;
	}
	/**
	 * @param canDeleteFlag the canDeleteFlag to set
	 */
	public void setCanDeleteFlag(String canDeleteFlag) {
		this.canDeleteFlag = canDeleteFlag;
	}
	/**
	 * @return the companyNum
	 */
	@Transient
	public Integer getCompanyNum() {
		return companyNum;
	}
	/**
	 * @param companyNum the companyNum to set
	 */
	public void setCompanyNum(Integer companyNum) {
		this.companyNum = companyNum;
	}
	/**
	 * @return the sort
	 */
	@Column(name = "sort")
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
	 * @return the icon
	 */
	@Column(name = "icon")
	public String getIcon() {
		return icon;
	}
	/**
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
	/**
	 * @return the remark
	 */
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
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
