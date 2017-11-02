/**   
* @Title: InfoMessage.java 
* @Package com.srd.ljzd.entity.info 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年7月20日 下午4:22:09 
* @version V1.0   
*/
package com.srd.ljzd.entity.info;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/** 
 * @ClassName: InfoMessage
 * @Description: 消息内容
 * @author shiyong
 * @date 2016年7月20日 下午4:22:09
 *  
 */
@Entity
@Table(name = "info_message")
public class InfoMessage {
	/**
	* @Fields messageId : 消息ID
	*/
	private String messageId;
	/**
	* @Fields sender : 发件人
	*/
	private String sender;
	/**
	* @Fields senderId : 发件人ID
	*/
	private String senderId;
	/**
	* @Fields title : 标题
	*/
	private String title;
	/**
	* @Fields content : 内容
	*/
	private String content;
	/**
	* @Fields messageStatus : 消息状态（0：未发送，1：已发送）
	*/
	private String messageStatus;
	/**
	* @Fields sendTime : 发送时间
	*/
	private Date sendTime;
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
	 * @return the messageId
	 */
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "message_id", unique = true, nullable = false, length = 36)
	public String getMessageId() {
		return messageId;
	}
	public InfoMessage(String sender){
		this.sender=sender;
	}
	public InfoMessage(){
		
	}
	
	/**
	 * @param messageId the messageId to set
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	/**
	 * @return the sender
	 */
	@Column(name = "Sender", nullable = false, length = 50)
	public String getSender() {
		return sender;
	}
	/**
	 * @param sender the sender to set
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}
	/**
	 * @return the senderId
	 */
	@Column(name = "sender_id")
	public String getSenderId() {
		return senderId;
	}
	/**
	 * @param senderId the senderId to set
	 */
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	/**
	 * @return the title
	 */
	@Column(name = "Title")
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the content
	 */
	@Column(name = "Content")
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the messageStatus
	 */
	@Column(name = "status")
	public String getMessageStatus() {
		return messageStatus;
	}
	/**
	 * @param messageStatus the messageStatus to set
	 */
	public void setMessageStatus(String messageStatus) {
		this.messageStatus = messageStatus;
	}
	/**
	 * @return the sendTime
	 */
	@Column(name = "send_time")
	public Date getSendTime() {
		return sendTime;
	}
	/**
	 * @param sendTime the sendTime to set
	 */
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
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
	 * @return the lastModifiedTime
	 */
	@Column(name = "modify_time")
	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}
	/**
	 * @param lastModifiedTime the lastModifiedTime to set
	 */
	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}
	
	@Column(name = "delete_flag")
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	
	
}
