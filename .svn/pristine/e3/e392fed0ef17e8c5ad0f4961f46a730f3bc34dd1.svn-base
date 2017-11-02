/**   
* @Title: ClientAccount.java 
* @Package com.srd.ljzx.entity.client 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年7月20日 下午6:06:50 
* @version V1.0   
*/
package com.srd.ljzd.entity.client;

import java.io.Serializable;
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

/** 
 * @ClassName: ClientAccount
 * @Description: 客户账号信息
 * @author shiyong
 * @date 2017年7月20日 下午6:06:50
 *  
 */
@Entity
@Table(name = "client_account")
public class ClientAccount implements Serializable{

	/**
	* @Fields serialVersionUID : 
	*/
	private static final long serialVersionUID = 1L;
	/**
	* @Fields accountId : 账户ID
	*/
	private String accountId;
	/**
	* @Fields parentId : 父账号ID
	*/
	private String parentId;
	/**
	* @Fields clientCompany : 客户公司ID 
	*/
	private ClientCompany clientCompany;
	/**
	* @Fields accountName : 账户名
	*/
	private String accountName;
	/**
	* @Fields name : 客户姓名
	*/
	private String name;
	/**
	* @Fields password : 账户密码
	*/
	private String password;
	/**
	* @Fields type : 账号类型（1：母账号，2：子账号，3：附属账号）
	*/
	private String type;
	/**
	* @Fields rights : 权限
	*/
	private String rights;
	/**
	* @Fields mobilePhone : 手机号码
	*/
	private String mobilePhone;
	/**
	* @Fields email : 邮箱
	*/
	private String email;
	/**
	* @Fields monitorNum : 监控额度
	*/
	private Integer monitorNum;
	/**
	* @Fields usedMonitorNum : 当月已使用监控数
	*/
	private Integer usedMonitorNum;
	/**
	* @Fields usedMonitorProportion : 已使用监控数占比
	*/
	private Integer usedMonitorProportion;
	/**
	* @Fields childNum : 可创建子账号数
	*/
	private Integer childNum;
	/**
	* @Fields usedChildNum : 已创建子账号数
	*/
	private Integer usedChildNum;
	/**
	* @Fields canCreateChild : 是否可以创建子账号
	*/
	private String canCreateChild;
	/**
	* @Fields status : 账号状态（0：正常，1：已禁用）
	*/
	private String status;
	/**
	* @Fields remark : 备注
	*/
	private String remark;
	/**
	* @Fields creatorId : 创建者ID
	*/
	private String creatorId;
	/**
	* @Fields creatorName : 创建者姓名
	*/
	private String creatorName;
	/**
	* @Fields createTime : 创建时间
	*/
	private Date createTime;
	/**
	* @Fields modifierId : 修改者ID
	*/
	private String modifierId;
	/**
	* @Fields modifierName : 修改者姓名
	*/
	private String modifierName;
	/**
	* @Fields modifyTime : 最后修改时间
	*/
	private Date modifyTime;
	/**
	* @Fields deleteFlag : 删除标志（0：正常，1：已删除）
	*/
	private String deleteFlag;
	
	/**
	 * @return the accountId
	 */
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "account_id", unique = true, nullable = false, length = 36)
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
	 * @return the parentId
	 */
	@Column(name = "parent_id")
	public String getParentId() {
		return parentId;
	}
	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	/**
	 * @return the clientCompany
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	@JsonIgnore
	public ClientCompany getClientCompany() {
		return clientCompany;
	}
	/**
	 * @param clientCompany the clientCompany to set
	 */
	public void setClientCompany(ClientCompany clientCompany) {
		this.clientCompany = clientCompany;
	}
	/**
	 * @return the accountName
	 */
	@Column(name = "account_name")
	public String getAccountName() {
		return accountName;
	}
	/**
	 * @param accountName the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	/**
	 * @return the name
	 */
	@Column(name = "name")
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
	 * @return the password
	 */
	@Column(name = "password")
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the type
	 */
	@Column(name = "type")
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
	 * @return the rights
	 */
	@Transient
	public String getRights() {
		return rights;
	}
	/**
	 * @param rights the rights to set
	 */
	public void setRights(String rights) {
		this.rights = rights;
	}
	/**
	 * @return the mobilePhone
	 */
	@Column(name = "mobile_phone")
	public String getMobilePhone() {
		return mobilePhone;
	}
	/**
	 * @param mobilePhone the mobilePhone to set
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	/**
	 * @return the email
	 */
	@Column(name = "email")
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the monitorNum
	 */
	@Column(name = "monitor_num")
	public Integer getMonitorNum() {
		return monitorNum;
	}
	/**
	 * @param monitorNum the monitorNum to set
	 */
	public void setMonitorNum(Integer monitorNum) {
		this.monitorNum = monitorNum;
	}
	/**
	 * @return the usedMonitorNum
	 */
	@Column(name = "used_monitor_num")
	public Integer getUsedMonitorNum() {
		return usedMonitorNum;
	}
	/**
	 * @param usedMonitorNum the usedMonitorNum to set
	 */
	public void setUsedMonitorNum(Integer usedMonitorNum) {
		this.usedMonitorNum = usedMonitorNum;
	}
	/**
	 * @return the usedMonitorProportion
	 */
	@Transient
	public Integer getUsedMonitorProportion() {
		return usedMonitorProportion;
	}
	/**
	 * @param usedMonitorProportion the usedMonitorProportion to set
	 */
	public void setUsedMonitorProportion(Integer usedMonitorProportion) {
		this.usedMonitorProportion = usedMonitorProportion;
	}
	/**
	 * @return the childNum
	 */
	@Column(name = "child_num")
	public Integer getChildNum() {
		return childNum;
	}
	/**
	 * @param childNum the childNum to set
	 */
	public void setChildNum(Integer childNum) {
		this.childNum = childNum;
	}
	/**
	 * @return the usedChildNum
	 */
	@Column(name = "used_child_num")
	public Integer getUsedChildNum() {
		return usedChildNum;
	}
	/**
	 * @param usedChildNum the usedChildNum to set
	 */
	public void setUsedChildNum(Integer usedChildNum) {
		this.usedChildNum = usedChildNum;
	}
	/**
	 * @return the canCreateChild
	 */
	@Transient
	public String getCanCreateChild() {
		return canCreateChild;
	}
	/**
	 * @param canCreateChild the canCreateChild to set
	 */
	public void setCanCreateChild(String canCreateChild) {
		this.canCreateChild = canCreateChild;
	}
	/**
	 * @return the status
	 */
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * @return the creatorName
	 */
	@Column(name = "creator_name")
	public String getCreatorName() {
		return creatorName;
	}
	/**
	 * @param creatorName the creatorName to set
	 */
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
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
	 * @return the modifierName
	 */
	@Column(name = "modifier_name")
	public String getModifierName() {
		return modifierName;
	}
	/**
	 * @param modifierName the modifierName to set
	 */
	public void setModifierName(String modifierName) {
		this.modifierName = modifierName;
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
