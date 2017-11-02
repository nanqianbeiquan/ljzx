/**   
* @Title: Department.java 
* @Package com.srd.ljzx.entity.sys 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年6月15日 下午2:38:40 
* @version V1.0   
*/
package com.srd.ljzd.entity.sys;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/** 
 * @ClassName: Department
 * @Description: 部门信息
 * @author shiyong
 * @date 2017年6月15日 下午2:38:40
 *  
 */
@Entity
@Table(name = "sys_department")
public class SysDepartment {
	/**
	* @Fields departmentId : 部门ID
	*/
	private String departmentId;
	/**
	* @Fields parentId : 上级部门ID
	*/
	private String parentId;
	/**
	* @Fields departmentName : 部门名称
	*/
	private String departmentName;
	/**
	* @Fields type : 部门类型（1：公司，2：部门）
	*/
	private String type;
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
	 * @return the departmentId
	 */
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "department_id", unique = true, nullable = false, length = 36)
	public String getDepartmentId() {
		return departmentId;
	}
	/**
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
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
	 * @return the departmentName
	 */
	@Column(name = "department_name")
	public String getDepartmentName() {
		return departmentName;
	}
	/**
	 * @param departmentName the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
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
