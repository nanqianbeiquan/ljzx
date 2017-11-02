package com.srd.ljzd.entity.monitor;

import java.io.Serializable;

/**
 * 
 * 版权所有：
 * 项目名称：ljzd 
 *
 * 类描述：质检
 * 类名称：com.srd.ljzd.entity.monitor  
 * 创建人：jiang.zhou
 * 创建时间：20162016年11月28日下午3:12:22
 * 修改人： 
 * 修改时间： 
 * 修改备注：   
 * @version   V2.0
 */
public class ZhiJian extends BaseDimension implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 99420629313108256L;

	/**
	 * 受检产品
	 */
	private String product;
	/**
	 * 商标
	 */
	private String trademark;
	/**
	 * 规格型号
	 */
	private String ggxh;
	/**
	 *  批号/生产日期
	 */
	private String product_time;
	/**
	 * 制作企业
	 */
	private String productEnterprise;
	
	/**
	 * 受检企业
	 */
	private String checkEnterprise;
	
	/**
	 * 事件等级企业
	 */
	private String level;

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getTrademark() {
		return trademark;
	}

	public void setTrademark(String trademark) {
		this.trademark = trademark;
	}

	public String getGgxh() {
		return ggxh;
	}

	public void setGgxh(String ggxh) {
		this.ggxh = ggxh;
	}

	public String getProduct_time() {
		return product_time;
	}

	public void setProduct_time(String product_time) {
		this.product_time = product_time;
	}

	public String getProductEnterprise() {
		return productEnterprise;
	}

	public void setProductEnterprise(String productEnterprise) {
		this.productEnterprise = productEnterprise;
	}

	public String getCheckEnterprise() {
		return checkEnterprise;
	}

	public void setCheckEnterprise(String checkEnterprise) {
		this.checkEnterprise = checkEnterprise;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
}
