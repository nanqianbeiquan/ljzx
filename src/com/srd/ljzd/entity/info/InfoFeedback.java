/**   
* @Title: InfoFeedback.java 
* @Package com.srd.ljzd.entity.info 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年7月20日 下午3:44:21 
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
 * @ClassName: InfoFeedback
 * @Description: 问题反馈
 * @author shiyong
 * @date 2016年7月20日 下午3:44:21
 *  
 */
@Entity
@Table(name = "info_feedback")
public class InfoFeedback {
	/**
	* @Fields feedbackID : 反馈ID
	*/
	private String feedbackID;
	/**
	* @Fields accountID : 反馈人ID
	*/
	private String accountID;
	/**
	* @Fields feedbackContent : 反馈内容
	*/
	private String feedbackContent;
	/**
	* @Fields replierID : 答复人ID
	*/
	private String replierID;
	/**
	* @Fields replyContent : 答复内容
	*/
	private String replyContent;
	/**
	* @Fields replyTime : 答复时间
	*/
	private Date replyTime;
	/**
	* @Fields replyStatus : 答复状态(0：未答复，1：已答复)
	*/
	private String replyStatus;
	/**
	* @Fields createTime : 创建时间
	*/
	private Date createTime;
	/**
	* @Fields lastModifiedTime : 最后修改时间
	*/
	private Date lastModifiedTime;
	
	
	/**
	 * @return the feedbackID
	 */
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "feedback_id", unique = true, nullable = false, length = 36)
	public String getFeedbackID() {
		return feedbackID;
	}
	/**
	 * @param feedbackID the feedbackID to set
	 */
	public void setFeedbackID(String feedbackID) {
		this.feedbackID = feedbackID;
	}
	/**
	 * @return the accountID
	 */
	@Column(name = "account_id", nullable = false, length = 36)
	public String getAccountID() {
		return accountID;
	}
	/**
	 * @param accountID the accountID to set
	 */
	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}
	/**
	 * @return the feedbackContent
	 */
	@Column(name = "feedback_content", nullable = false, length = 1000)
	public String getFeedbackContent() {
		return feedbackContent;
	}
	/**
	 * @param feedbackContent the feedbackContent to set
	 */
	public void setFeedbackContent(String feedbackContent) {
		this.feedbackContent = feedbackContent;
	}
	/**
	 * @return the replierID
	 */
	@Column(name = "replier_id")
	public String getReplierID() {
		return replierID;
	}
	/**
	 * @param replierID the replierID to set
	 */
	public void setReplierID(String replierID) {
		this.replierID = replierID;
	}
	/**
	 * @return the replyContent
	 */
	@Column(name = "reply_content")
	public String getReplyContent() {
		return replyContent;
	}
	/**
	 * @param replyContent the replyContent to set
	 */
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	/**
	 * @return the replyTime
	 */
	@Column(name = "reply_time")
	public Date getReplyTime() {
		return replyTime;
	}
	/**
	 * @param replyTime the replyTime to set
	 */
	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}
	/**
	 * @return the replyStatus
	 */
	@Column(name = "reply_status")
	public String getReplyStatus() {
		return replyStatus;
	}
	/**
	 * @param replyStatus the replyStatus to set
	 */
	public void setReplyStatus(String replyStatus) {
		this.replyStatus = replyStatus;
	}
	/**
	 * @return the createTime
	 */
	//@Column(name = "CreateTime, nullable = false")
	@Column(name = "create_time", nullable = false, length = 0)
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
	
	

}
