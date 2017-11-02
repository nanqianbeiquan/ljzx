
package com.srd.ljzd.entity.monitor;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * 
 * 版权所有：
 * 项目名称：ljzd 
 *
 * 类描述：关联个人公司公共实体
 * 类名称：com.srd.ljzd.entity.monitor  
 * 创建人：jiang.zhou
 * 创建时间：20162016年12月3日上午10:29:57
 * 修改人： 
 * 修改时间： 
 * 修改备注：   
 * @version   V2.0
 */
public class MonitorRelInfoRisk {
	
	/**
	 * 名字
	 */
	private String name;
	/**
	 * 标签
	 */
	private String label;
	
	/**
	* @Fields eventLevel : 事件等级（1：一般，2：异常，3：严重）
	*/
	private String eventLevel;
	
	/**
	 * 风险状况
	 */
	private String riskGrade;
	
	private String analysis;
	
	public String getAnalysis() {
		return analysis;
	}
	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getEventLevel() {
		return eventLevel;
	}
	public void setEventLevel(String eventLevel) {
		this.eventLevel = eventLevel;
	}

	public String getRiskGrade() {
		return riskGrade;
	}
	public void setRiskGrade(String riskGrade) {
		this.riskGrade = riskGrade;
	}


	
}
