/**   
* @Title: InfoMessageAccount.java 
* @Package com.srd.ljzd.entity.info 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年7月20日 下午4:22:22 
* @version V1.0   
*/
package com.srd.ljzd.entity.info;

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
 * @ClassName: InfoMessageAccount
 * @Description: 消息账号关联
 * @author shiyong
 * @date 2016年7月20日 下午4:22:22
 *  
 */
@Entity
@Table(name = "info_message_account")
public class InfoMessageAccount {
	/**
	* @Fields id : 主键
	*/
	private String id;
	
	private InfoMessage infoMessage;
	/**
	* @Fields accountId : 账号ID
	*/
	private String accountId;
	/**
	* @Fields readStatus : 阅读状态(0:未读,1:已读)
	*/
	private String readStatus;
	/**
	* @Fields createTime : 创建时间
	*/
	private Date createTime;
	/**
	* @Fields lastModifiedTime : 最后修改时间
	*/
	private Date lastModifiedTime;
	/**
	* @Fields isDelete : 是否删除（0：否，1：是）
	*/
	private String isDelete;
	
	
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
	 * @return the accountId
	 */
	@Column(name = "account_id", nullable = false, length = 36)
	public String getAccountId() {
		return accountId;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "message_id")
	public InfoMessage getInfoMessage() {
		return this.infoMessage;
	}
	
	public void setInfoMessage(InfoMessage infoMessage) {
		this.infoMessage = infoMessage;
	}
	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	/**
	 * @return the readStatus
	 */
	@Column(name = "ReadStatus")
	public String getReadStatus() {
		return readStatus;
	}
	/**
	 * @param readStatus the readStatus to set
	 */
	public void setReadStatus(String readStatus) {
		this.readStatus = readStatus;
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
	 * @return the isDelete
	 */
	@Column(name = "IsDelete")
	public String getIsDelete() {
		return isDelete;
	}
	/**
	 * @param isDelete the isDelete to set
	 */
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	
	

}
