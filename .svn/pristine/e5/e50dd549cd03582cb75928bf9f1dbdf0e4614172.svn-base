/**  
 * @Title: BizPopulorCompany.java
 * @Package com.srd.ljzd.entity.biz
 * @Description: TODO(热门企业查询实体)
 * @author zihui.pei
 * @date 2016年5月10日 下午3:30:41
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
* @ClassName: PopulorCompany
* @Description: 热门企业
* @author shiyong
* @date 2016年11月17日 上午10:46:32
*  
*/
@Entity
@Table(name = "bizpopulorcompany")
public class PopulorCompany {

	private String companyId;// 热门企业Id；
	private String companyName;// 热门企业名称；
	private String resentative; // 法人 ，代理人；
	private Date foundedDate; // 成立日期；
	private Date createTime;
	private Date lastModifiedTime;
	private Boolean isDelete;
	
	/**
	 * @return the companyId
	 */
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "popCompanyId", unique = true, nullable = false, length = 36)
	public String getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	/**
	 * @return the companyName
	 */
	@Column(name = "popCompanyName")
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	@Column(name = "resentative", length = 40)
	public String getResentative() {
		return resentative;
	}

	public void setResentative(String resentative) {
		this.resentative = resentative;
	}

	@Column(name = "foundedDate", length = 0)
	public Date getFoundedDate() {
		return foundedDate;
	}

	public void setFoundedDate(Date foundedDate) {
		this.foundedDate = foundedDate;
	}

	@Column(name = "createTime", length = 0)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "lastModifiedTime", length = 0)
	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	@Column(name = "IsDelete", nullable = false)
	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}


	

}
