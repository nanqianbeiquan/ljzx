/**   
* @Title: MonitorUserConfigure.java 
* @Package com.srd.ljzd.entity.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年11月1日 下午4:28:35 
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

import org.hibernate.annotations.GenericGenerator;

import com.srd.ljzd.entity.client.ClientAccount;


/** 
 * @ClassName: MonitorUserConfigure
 * @Description: 动态监控用户设置信息
 * @author shiyong
 * @date 2016年11月1日 下午4:28:35
 *  
 */
@Entity
@Table(name = "monitor_user_configure")
public class MonitorUserConfigure {
	
	/**
	* @Fields id : 主键
	*/
	private String id;
	/**
	* @Fields clientAccount : 客户账号
	*/
	private ClientAccount clientAccount;
	/**
	* @Fields attentionEventClass : 关注的事件分类（每一位字符代表一个分类是否关注，1表示关注，0表示不关注）
	*/
	private String attentionEventClass;
	/**
	* @Fields monitorCycle : 监控周期（1：日，2：周，3：月）
	*/
	private String monitorCycle;
	/**
	* @Fields notifyType : 通知方式（1：站内信，2：邮件，3：站内信加邮件）
	*/
	private String notifyType;
	/**
	* @Fields emails : 邮件列表
	*/
	private String emails;
	/**
	* @Fields createTime : 创建时间
	*/
	private Date createTime;
	/**
	* @Fields modifyTime : 最后修改时间
	*/
	private Date modifyTime;
	
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
	 * @return the clientAccount
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id")
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
	 * @return the attentionEventClass
	 */
	@Column(name = "attention_event_class")
	public String getAttentionEventClass() {
		return attentionEventClass;
	}
	/**
	 * @param attentionEventClass the attentionEventClass to set
	 */
	public void setAttentionEventClass(String attentionEventClass) {
		this.attentionEventClass = attentionEventClass;
	}
	/**
	 * @return the monitorCycle
	 */
	@Column(name = "monitor_cycle")
	public String getMonitorCycle() {
		return monitorCycle;
	}
	/**
	 * @param monitorCycle the monitorCycle to set
	 */
	public void setMonitorCycle(String monitorCycle) {
		this.monitorCycle = monitorCycle;
	}
	/**
	 * @return the notifyType
	 */
	@Column(name = "notify_type")
	public String getNotifyType() {
		return notifyType;
	}
	/**
	 * @param notifyType the notifyType to set
	 */
	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}
	/**
	 * @return the emails
	 */
	@Column(name = "emails")
	public String getEmails() {
		return emails;
	}
	/**
	 * @param emails the emails to set
	 */
	public void setEmails(String emails) {
		this.emails = emails;
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
	
	
}
