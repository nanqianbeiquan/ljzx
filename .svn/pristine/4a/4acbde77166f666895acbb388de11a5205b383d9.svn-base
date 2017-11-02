/**   
* @Title: MonitorUserEventLevelCompanyNumTrend.java 
* @Package com.srd.ljzd.entity.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年3月10日 上午9:49:05 
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
 * @ClassName: MonitorUserEventLevelCompanyNumTrend
 * @Description: 用户动态监控各事件类型企业数量趋势
 * @author shiyong
 * @date 2017年3月10日 上午9:49:05
 *  
 */
@Entity
@Table(name = "monitorusereventlevelcompanynumtrend")
public class MonitorUserEventLevelCompanyNumTrend {
	/**
	* @Fields id : 主键
	*/
	private String id;
	/**
	* @Fields clientAccount : 客户
	*/
	private ClientAccount clientAccount;
	/**
	* @Fields warnCompanyNum : 事件等级严重公司数 
	*/
	private int warnCompanyNum;
	/**
	* @Fields attentionCompanyNum : 事件等级异常公司数
	*/
	private int attentionCompanyNum;
	/**
	* @Fields normalCompanyNum : 事件等级一般公司数
	*/
	private int normalCompanyNum;
	/**
	* @Fields cycle : 周期（1：日，2：月，3：年）
	*/
	private String cycle;
	/**
	* @Fields reportDate : 统计日期
	*/
	private Date reportDate;
	/**
	* @Fields createTime : 创建时间
	*/
	private Date createTime;
	
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
	 * @return the sysAccount
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id")
	/**
	 * @return the clientAccount
	 */
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
	 * @return the warnCompanyNum
	 */
	@Column(name = "WarnCompanyNum")
	public int getWarnCompanyNum() {
		return warnCompanyNum;
	}
	/**
	 * @param warnCompanyNum the warnCompanyNum to set
	 */
	public void setWarnCompanyNum(int warnCompanyNum) {
		this.warnCompanyNum = warnCompanyNum;
	}
	/**
	 * @return the attentionCompanyNum
	 */
	@Column(name = "AttentionCompanyNum")
	public int getAttentionCompanyNum() {
		return attentionCompanyNum;
	}
	/**
	 * @param attentionCompanyNum the attentionCompanyNum to set
	 */
	public void setAttentionCompanyNum(int attentionCompanyNum) {
		this.attentionCompanyNum = attentionCompanyNum;
	}
	/**
	 * @return the normalCompanyNum
	 */
	@Column(name = "NormalCompanyNum")
	public int getNormalCompanyNum() {
		return normalCompanyNum;
	}
	/**
	 * @param normalCompanyNum the normalCompanyNum to set
	 */
	public void setNormalCompanyNum(int normalCompanyNum) {
		this.normalCompanyNum = normalCompanyNum;
	}
	/**
	 * @return the cycle
	 */
	@Column(name = "Cycle")
	public String getCycle() {
		return cycle;
	}
	/**
	 * @param cycle the cycle to set
	 */
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	/**
	 * @return the reportDate
	 */
	@Column(name = "ReportDate")
	public Date getReportDate() {
		return reportDate;
	}
	/**
	 * @param reportDate the reportDate to set
	 */
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
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

}
