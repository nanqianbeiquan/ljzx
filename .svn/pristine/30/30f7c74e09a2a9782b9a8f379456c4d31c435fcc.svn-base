/**   
* @Title: SysLog.java 
* @Package com.srd.ljzx.entity.sys 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年7月20日 上午11:07:46 
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
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;


/** 
 * @ClassName: SysLog
 * @Description: 系统日志
 * @author shiyong
 * @date 2017年7月20日 上午11:07:46
 *  
 */
@Entity
@Table(name = "sys_log")
public class SysLog {
	
	/**
	* @Fields id : 主键
	*/
	private String id;
	/**
	* @Fields sysAccount : 账号
	*/
	private SysAccount sysAccount;
	/**
	* @Fields operationType : 操作类型（1：登录后台，2：新增客户公司，3：修改客户公司，4：删除客户公司，5：开通客户公司，6：禁用客户公司，7：启用客户公司，
	*                         8：新增后台部门，9：修改后台部门，10：删除后台部门，11：新增后台账户，12：修改后台账号，13：重置后台账号密码，14：禁用后台账号，
	*                         15：启用后台账号，16：删除后台账号，17：绑定客服销售，18：解绑客服销售）
	*/
	private String operationType;
	/**
	* @Fields operationContent : 操作内容
	*/
	private String operationContent;
	/**
	* @Fields createTime : 创建时间
	*/
	private Date createTime;
	/**
	* @Fields startTime : 开始时间
	*/
	private Date startTime;
	/**
	* @Fields endTime : 结束时间
	*/
	private Date endTime;
	
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
	 * @return the sysAccount
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "account_id")
	public SysAccount getSysAccount() {
		return sysAccount;
	}
	/**
	 * @param sysAccount the sysAccount to set
	 */
	public void setSysAccount(SysAccount sysAccount) {
		this.sysAccount = sysAccount;
	}
	/**
	 * @return the operationType
	 */
	@Column(name = "operation_type")
	public String getOperationType() {
		return operationType;
	}
	/**
	 * @param operationType the operationType to set
	 */
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	/**
	 * @return the operationContent
	 */
	@Column(name = "operation_content")
	public String getOperationContent() {
		return operationContent;
	}
	/**
	 * @param operationContent the operationContent to set
	 */
	public void setOperationContent(String operationContent) {
		this.operationContent = operationContent;
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
	 * @return the startTime
	 */
	@Transient
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return the endTime
	 */
	@Transient
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	

}
