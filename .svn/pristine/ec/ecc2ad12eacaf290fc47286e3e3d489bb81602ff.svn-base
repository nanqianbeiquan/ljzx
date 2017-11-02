/**   
* @Title: MonitorCompanyFinanceSituation.java 
* @Package com.srd.ljzd.entity.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年6月22日 下午5:22:20 
* @version V1.0   
*/
package com.srd.ljzd.entity.monitor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.srd.ljzd.entity.company.CompanyBasicInfo;

/** 
 * @ClassName: MonitorCompanyFinanceSituation
 * @Description: 动态监控企业财务状况
 * @author shiyong
 * @date 2017年6月22日 下午5:22:20
 *  
 */
@Entity
@Table(name = "monitor_company_finance_situation")
public class MonitorCompanyFinanceSituation {
	/**
	* @Fields id : 主键
	*/
	private String id;
	/**
	* @Fields companyName : 企业基本信息
	*/
	private CompanyBasicInfo companyBasicInfo;
	/**
	* @Fields operationSituation : 经验状况（0：良好，1：一般，2：关注，3：严重关注）
	*/
	private String operationSituation;
	/**
	* @Fields score : 得分
	*/
	private Integer score;
	/**
	* @Fields color : 颜色（1：蓝色，2：红色）
	*/
	private String color;
	/**
	* @Fields yearNum : 数据年数
	*/
	private Integer yearNum;
	/**
	* @Fields conclusion1 : 分析结论
	*/
	private String conclusion;
	/**
	* @Fields createTime : 创建时间
	*/
	private Date createTime;
	/**
	* @Fields modifyTime : 最后修改时间
	*/
	private Date modifyTime;
    /**
    * @Fields monitorCompanyFinanceAnalysisList : 企业财务分析列表
    */
    private List<MonitorCompanyFinanceAnalysis> monitorCompanyFinanceAnalysisList = new ArrayList<MonitorCompanyFinanceAnalysis>(); 
	
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
	 * @return the companyBasicInfo
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	public CompanyBasicInfo getCompanyBasicInfo() {
		return companyBasicInfo;
	}
	/**
	 * @param companyBasicInfo the companyBasicInfo to set
	 */
	public void setCompanyBasicInfo(CompanyBasicInfo companyBasicInfo) {
		this.companyBasicInfo = companyBasicInfo;
	}
	/**
	 * @return the operationSituation
	 */
	@Column(name = "operation_situation")
	public String getOperationSituation() {
		return operationSituation;
	}
	/**
	 * @param operationSituation the operationSituation to set
	 */
	public void setOperationSituation(String operationSituation) {
		this.operationSituation = operationSituation;
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
	 * @return the yearNum
	 */
	@Column(name = "year_num")
	public Integer getYearNum() {
		return yearNum;
	}
	/**
	 * @param yearNum the yearNum to set
	 */
	public void setYearNum(Integer yearNum) {
		this.yearNum = yearNum;
	}
	/**
	 * @return the conclusion
	 */
	@Column(name = "conclusion")
	public String getConclusion() {
		return conclusion;
	}
	/**
	 * @param conclusion1 the conclusion to set
	 */
	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
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
	/**
	 * @return the monitorCompanyFinanceAnalysisList
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="situation_id")
	@OrderBy(value = "financialIndex asc")
	public List<MonitorCompanyFinanceAnalysis> getMonitorCompanyFinanceAnalysisList() {
		return monitorCompanyFinanceAnalysisList;
	}
	/**
	 * @param monitorCompanyFinanceAnalysisList the monitorCompanyFinanceAnalysisList to set
	 */
	public void setMonitorCompanyFinanceAnalysisList(
			List<MonitorCompanyFinanceAnalysis> monitorCompanyFinanceAnalysisList) {
		this.monitorCompanyFinanceAnalysisList = monitorCompanyFinanceAnalysisList;
	}
	
}
