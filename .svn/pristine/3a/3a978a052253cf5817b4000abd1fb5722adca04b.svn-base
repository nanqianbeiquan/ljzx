/**   
* @Title: MonitorCustomRiskInfo.java 
* @Package com.srd.ljzd.entity.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年4月17日 下午4:15:14 
* @version V1.0   
*/
package com.srd.ljzd.entity.monitor;

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

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.org.apache.xalan.internal.utils.Objects;

/** 
 * @ClassName: MonitorCustomRiskInfo
 * @Description: 客户自定义风险信息
 * @author shiyong
 * @date 2017年4月17日 下午4:15:14
 *  
 */
@Entity
@Table(name = "monitorcustomriskinfo")
public class MonitorCustomRiskInfo implements Serializable{
	private static final long serialVersionUID = -4987925164914873604L;
	/**
	* @Fields id : 主键
	*/
	private String id;
	/**
	* @Fields monitorCustomRiskSituation : 客户自定义风险状况
	*/
	private MonitorCustomRiskSituation monitorCustomRiskSituation;
	/**
	* @Fields riskType : 风险类别大类
	*/
	private String riskType;
	/**
	* @Fields riskSubType : 风险类别小类
	*/
	private String riskSubType;
	/**
	* @Fields riskContent : 自定义风险内容
	*/
	private String riskContent;
	/**
	* @Fields createTime : 创建时间
	*/
	private Date createTime;
	
	public MonitorCustomRiskInfo() {}
	
	public MonitorCustomRiskInfo(String riskType, String riskSubType,
			String riskContent) {
		super();
		this.riskType = riskType;
		this.riskSubType = riskSubType;
		this.riskContent = riskContent;
	}
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
	 * @return the monitorCustomRiskSituation
	 */
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RiskID")
	public MonitorCustomRiskSituation getMonitorCustomRiskSituation() {
		return monitorCustomRiskSituation;
	}
	/**
	 * @param monitorCustomRiskSituation the monitorCustomRiskSituation to set
	 */
	public void setMonitorCustomRiskSituation(
			MonitorCustomRiskSituation monitorCustomRiskSituation) {
		this.monitorCustomRiskSituation = monitorCustomRiskSituation;
	}
	/**
	 * @return the riskType
	 */
	@Column(name = "RiskType")
	public String getRiskType() {
		return riskType;
	}
	/**
	 * @param riskType the riskType to set
	 */
	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}
	/**
	 * @return the riskSubType
	 */
	@Column(name = "RiskSubType")
	public String getRiskSubType() {
		return riskSubType;
	}
	/**
	 * @param riskSubType the riskSubType to set
	 */
	public void setRiskSubType(String riskSubType) {
		this.riskSubType = riskSubType;
	}
	/**
	 * @return the riskContent
	 */
	@Column(name = "RiskContent")
	public String getRiskContent() {
		return riskContent;
	}
	/**
	 * @param riskContent the riskContent to set
	 */
	public void setRiskContent(String riskContent) {
		this.riskContent = riskContent;
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
	@Override
	public int hashCode() {
		int result = 17;
		result = 31*result + this.riskType.hashCode();
		result = 31*result + this.riskSubType.hashCode();
		result = 31*result + this.riskContent==null?"".hashCode():this.riskContent.hashCode();
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if(this==obj){
			return true;
		}
		if(obj==null){
			return false;
		}
		if(getClass()!=obj.getClass()){
			return false;
		}
		MonitorCustomRiskInfo info = (MonitorCustomRiskInfo)obj;
		return Objects.equals(this.riskType, info.getRiskType())
				&&Objects.equals(this.riskSubType,info.getRiskSubType())
				&&Objects.equals(this.riskContent,info.getRiskContent());
	}

	
}
