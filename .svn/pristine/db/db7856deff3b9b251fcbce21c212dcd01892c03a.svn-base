/**   
* @Title: Account.java 
* @Package com.srd.ljzx.entity.sys 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年6月20日 下午1:01:49 
* @version V1.0   
*/
package com.srd.ljzd.entity.sys;

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

/** 
 * @ClassName: Account
 * @Description: 账号信息
 * @author shiyong
 * @date 2017年6月20日 下午1:01:49
 *  
 */
@Entity
@Table(name = "sys_account")
public class SysAccount {
	/**
	* @Fields accountId : 账户ID
	*/
	private String accountId;
	/**
	* @Fields department : 部门
	*/
	private SysDepartment department;
	/**
	* @Fields role : 角色
	*/
	private SysRole role;
	/**
	* @Fields loginName : 登录名
	*/
	private String loginName;
	/**
	* @Fields name : 姓名
	*/
	private String name;
	/**
	* @Fields password : 密码
	*/
	private String password;
	/**
	* @Fields mobilePhone : 手机号码
	*/
	private String mobilePhone;
	/**
	* @Fields telephone : 电话号码
	*/
	private String telephone;
	/**
	* @Fields email : 邮箱
	*/
	private String email;
	/**
	* @Fields status : 状态（0：正常，1：禁用）
	*/
	private String status;
	/**
	* @Fields area : 区域
	*/
	private SysArea area;
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
	 * @return the department
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "department_id")
	public SysDepartment getDepartment() {
		return department;
	}
	/**
	 * @param department the department to set
	 */
	public void setDepartment(SysDepartment department) {
		this.department = department;
	}
	/**
	 * @return the role
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	public SysRole getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(SysRole role) {
		this.role = role;
	}
	/**
	 * @return the loginName
	 */
	@Column(name = "login_name")
	public String getLoginName() {
		return loginName;
	}
	/**
	 * @param loginName the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
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
	 * @return the telephone
	 */
	@Column(name = "telephone")
	public String getTelephone() {
		return telephone;
	}
	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
	 * @return the area
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "area_id")
	public SysArea getArea() {
		return area;
	}
	/**
	 * @param area the area to set
	 */
	public void setArea(SysArea area) {
		this.area = area;
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
