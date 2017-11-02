/**   
* @Title: CompanyUsedName.java 
* @Package com.srd.ljzd.entity.company 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年8月10日 上午9:40:00 
* @version V1.0   
*/
package com.srd.ljzd.entity.company;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/** 
 * @ClassName: CompanyUsedName
 * @Description: 企业曾用名
 * @author shiyong
 * @date 2017年8月10日 上午9:40:00
 *  
 */
@Entity
@Table(name = "company_usedname")
public class CompanyUsedName {
	/**
	* @Fields id : 主键
	*/
	private String id;
	/**
	* @Fields companyName : 企业名称
	*/
	private String companyName;
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
	 * @return the companyName
	 */
	@Column(name = "company_name")
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
