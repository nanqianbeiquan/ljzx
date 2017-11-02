/**   
* @Title: ClientCompany.java 
* @Package com.srd.ljzx.entity.client 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年7月20日 下午1:56:10 
* @version V1.0   
*/
package com.srd.ljzd.entity.client;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/** 
 * @ClassName: ClientCompany
 * @Description: 客户公司信息
 * @author shiyong
 * @date 2017年7月20日 下午1:56:10
 *  
 */
@Entity
@Table(name = "client_company")
public class ClientCompany implements Serializable{
	/**
	* @Fields serialVersionUID : 
	*/
	private static final long serialVersionUID = 1L;
	/**
	* @Fields companyId : 公司ID
	*/
	private String companyId;
	/**
	* @Fields companyName : 公司名称
	*/
	private String companyName;
	/**
	* @Fields companyType : 企业类型（1：企业，2：金融机构）
	*/
	private String companyType;
	/**
	* @Fields contractBeginDate : 合同开始时间
	*/
	private String contractBeginDate;
	/**
	* @Fields contractEndDate : 合同结束时间
	*/
	private String contractEndDate;
	/**
	* @Fields accountType : 账号类型（0：试用，1：正式）
	*/
	private String accountType;
	/**
	* @Fields accountNum : 子账号数量
	*/
	private Integer accountNum;
	/**
	* @Fields monitorNum : 监控额度
	*/
	private Integer monitorNum;
	/**
	* @Fields amount : 金额
	*/
	private Integer amount;
	/**
	* @Fields contact : 联系人
	*/
	private String contact;
	/**
	* @Fields position : 联系人职位
	*/
	private String position;
	/**
	* @Fields telephone : 手机号码
	*/
	private String telephone;
	/**
	* @Fields status : 公司状态（0：未开通，1：已开通，2：已过期，3：已禁用）
	*/
	private String status;
	/**
	* @Fields sales : 销售人员ID 
	*/
	private String salesId;
	/**
	* @Fields remark : 备注
	*/
	private String remark;
	/**
	* @Fields creator : 创建者
	*/
	private String creator;
	/**
	* @Fields createTime : 创建时间
	*/
	private Date createTime;
	/**
	* @Fields modifier : 修改者
	*/
	private String modifier;
	/**
	* @Fields modifyTime : 最后修改时间
	*/
	private Date modifyTime;
	/**
	* @Fields deleteFlag : 删除标志（0：正常，1：已删除）
	*/
	private String deleteFlag;
	
	/**
	 * @return the companyId
	 */
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "company_id", unique = true, nullable = false, length = 36)
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
	 * @return the companyType
	 */
	@Column(name = "company_type")
	public String getCompanyType() {
		return companyType;
	}
	/**
	 * @param companyType the companyType to set
	 */
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	/**
	 * @return the contractBeginDate
	 */
	@Column(name = "contract_begin_date")
	public String getContractBeginDate() {
		return contractBeginDate;
	}
	/**
	 * @param contractBeginDate the contractBeginDate to set
	 */
	public void setContractBeginDate(String contractBeginDate) {
		this.contractBeginDate = contractBeginDate;
	}
	/**
	 * @return the contractEndDate
	 */
	@Column(name = "contract_end_date")
	public String getContractEndDate() {
		return contractEndDate;
	}
	/**
	 * @param contractEndDate the contractEndDate to set
	 */
	public void setContractEndDate(String contractEndDate) {
		this.contractEndDate = contractEndDate;
	}
	/**
	 * @return the accountType
	 */
	@Column(name = "account_type")
	public String getAccountType() {
		return accountType;
	}
	/**
	 * @param accountType the accountType to set
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	/**
	 * @return the accountNum
	 */
	@Column(name = "account_num")
	public Integer getAccountNum() {
		return accountNum;
	}
	/**
	 * @param accountNum the accountNum to set
	 */
	public void setAccountNum(Integer accountNum) {
		this.accountNum = accountNum;
	}
	/**
	 * @return the monitorNum
	 */
	@Column(name = "monitor_num")
	public Integer getMonitorNum() {
		return monitorNum;
	}
	/**
	 * @param monitorNum the monitorNum to set
	 */
	public void setMonitorNum(Integer monitorNum) {
		this.monitorNum = monitorNum;
	}
	/**
	 * @return the amount
	 */
	@Column(name = "amount")
	public Integer getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	/**
	 * @return the contact
	 */
	@Column(name = "contact")
	public String getContact() {
		return contact;
	}
	/**
	 * @param contact the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}
	/**
	 * @return the position
	 */
	@Column(name = "position")
	public String getPosition() {
		return position;
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	/**
	 * @return the telephone
	 */
	@Column(name = "telephone")
	public String getTelephone() {
		return telephone;
	}
	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * @return the status
	 */
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * @return the remark
	 */
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
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
	 * @return the deleteFlag
	 */
	@Column(name = "delete_flag")
	public String getDeleteFlag() {
		return deleteFlag;
	}
	/**
	 * @param deleteFlag the deleteFlag to set
	 */
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public ClientCompany(){}
	public ClientCompany(String companyId, String companyName) {
		super();
		this.companyId = companyId;
		this.companyName = companyName;
	}
	
	@Column(name = "sales_id")
	public String getSalesId() {
		return salesId;
	}
	public void setSalesId(String salesId) {
		this.salesId = salesId;
	}
	@Column(name = "creator")
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	@Column(name = "modifier")
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	
	

}
