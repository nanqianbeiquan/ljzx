package com.srd.ljzd.entity.monitor;

import java.io.Serializable;

/**
 * 
 * 版权所有：
 * 项目名称：ljzd 
 *
 * 类描述：社保缴纳
 * 类名称：com.srd.ljzd.entity.monitor  
 * 创建人：jiang.zhou
 * 创建时间：20162016年12月5日下午4:54:11
 * 修改人： 
 * 修改时间： 
 * 修改备注：   
 * @version   V2.0
 */
public class SocialSecurietyPay extends BaseDimension implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1227833473853360615L;

	/**
	 * 企业名称
	 */
	private String enterprisename;
	
	/**
	 * 社保缴纳情况
	 */
	private String items;
	
	/**
	 * 发布时间
	 */
	private String time;
	
	/**
	 * 事件等级
	 */
	private String level;

	public String getEnterprisename() {
		return enterprisename;
	}

	public void setEnterprisename(String enterprisename) {
		this.enterprisename = enterprisename;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
}
