/**   
* @Title: MonitorCompanyFinanceAnalysis.java 
* @Package com.srd.ljzd.entity.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年6月22日 下午5:19:13 
* @version V1.0   
*/
package com.srd.ljzd.entity.monitor;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/** 
 * @ClassName: MonitorCompanyFinanceAnalysis
 * @Description: 动态监控企业财务分析
 * @author shiyong
 * @date 2017年6月22日 下午5:19:13
 *  
 */
@Entity
@Table(name = "monitor_company_finance_analysis")
public class MonitorCompanyFinanceAnalysis {
	
	/**
	* @Fields id : 主键
	*/
	private String id;
	/**
	* @Fields situationId : 财务状况ID
	*/
	private String situationId;
	/**
	* @Fields financialIndex : 财务指标(1：营业收入，2：净利润，3：资产负债率，4：总资产周转率，5：利润率，6：股东权益)
	*/
	private String financialIndex;
	/**
	* @Fields score : 得分
	*/
	private Integer score;
	/**
	* @Fields label : 标签
	*/
	private String label;
	/**
	* @Fields color : 颜色（1：蓝色，2：红色）
	*/
	private String color;
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
	 * @return the situationId
	 */
	@Column(name = "situation_id")
	public String getSituationId() {
		return situationId;
	}
	/**
	 * @param situationId the situationId to set
	 */
	public void setSituationId(String situationId) {
		this.situationId = situationId;
	}
	/**
	 * @return the financialIndex
	 */
	@Column(name = "financial_index")
	public String getFinancialIndex() {
		return financialIndex;
	}
	/**
	 * @param financialIndex the financialIndex to set
	 */
	public void setFinancialIndex(String financialIndex) {
		this.financialIndex = financialIndex;
	}
	/**
	 * @return the score
	 */
	@Column(name = "score")
	public Integer getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(Integer score) {
		this.score = score;
	}
	/**
	 * @return the label
	 */
	@Column(name = "label")
	public String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	/**
	 * @return the color
	 */
	@Column(name = "color")
	public String getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
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

	
	
}
