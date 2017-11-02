package com.srd.ljzd.entity.monitor;

import java.io.Serializable;

/**
 * 
 * 版权所有：
 * 项目名称：ljzd 
 *
 * 类描述：股权冻结
 * 类名称：com.srd.ljzd.entity.monitor  
 * 创建人：jiang.zhou
 * 创建时间：20162016年11月28日下午2:41:04
 * 修改人： 
 * 修改时间： 
 * 修改备注：   
 * @version   V2.0
 */
public class SharesFrost implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4207758311346092547L;
	/**
	 * 注册号
	 */
	private String registrationno;
	/**
	 * 冻结起始日期
	 */
	private String frofrom;
	/**
	 * 冻结日期 
	 */
	private String thawdate;
	/**
	 * 解冻说明
	 */
	private String thawcomment;
	/**
	 * 解冻说明
	 */
	private String froauth;
	/**
	 * 冻结截至日期
	 */
	
	private String froto;
	/**解冻文号
	 * 
	 */
	private String thawdocno;
	/**
	 * 冻结文号
	 */
	private String frodocno;
	/**
	 * 冻结金额(万元)
	 */
	private String froam;
	/**
	 * 解冻机关
	 */
	private String thawauth;
	public String getRegistrationno() {
		return registrationno;
	}
	public void setRegistrationno(String registrationno) {
		this.registrationno = registrationno;
	}
	public String getFrofrom() {
		return frofrom;
	}
	public void setFrofrom(String frofrom) {
		this.frofrom = frofrom;
	}
	public String getThawdate() {
		return thawdate;
	}
	public void setThawdate(String thawdate) {
		this.thawdate = thawdate;
	}
	public String getThawcomment() {
		return thawcomment;
	}
	public void setThawcomment(String thawcomment) {
		this.thawcomment = thawcomment;
	}
	public String getFroauth() {
		return froauth;
	}
	public void setFroauth(String froauth) {
		this.froauth = froauth;
	}
	public String getFroto() {
		return froto;
	}
	public void setFroto(String froto) {
		this.froto = froto;
	}
	public String getThawdocno() {
		return thawdocno;
	}
	public void setThawdocno(String thawdocno) {
		this.thawdocno = thawdocno;
	}
	public String getFrodocno() {
		return frodocno;
	}
	public void setFrodocno(String frodocno) {
		this.frodocno = frodocno;
	}
	public String getFroam() {
		return froam;
	}
	public void setFroam(String froam) {
		this.froam = froam;
	}
	public String getThawauth() {
		return thawauth;
	}
	public void setThawauth(String thawauth) {
		this.thawauth = thawauth;
	}

}
