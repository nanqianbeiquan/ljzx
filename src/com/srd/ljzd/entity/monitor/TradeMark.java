package com.srd.ljzd.entity.monitor;

import java.io.Serializable;

/**
 * 
 * 版权所有：
 * 项目名称：ljzd 
 *
 * 类描述：商标
 * 类名称：com.srd.ljzd.entity.monitor  
 * 创建人：jiang.zhou
 * 创建时间：20162016年11月28日下午3:47:56
 * 修改人： 
 * 修改时间： 
 * 修改备注：   
 * @version   V2.0
 */
public class TradeMark extends BaseDimension implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4355718411621134299L;

	/**
	 * 商标名称
	 */
	private String brand_name;
	/**
	 * 商标图片
	 */
	private String img_str;
	/**
	 * 申请时间
	 */
	private String apply_time;
	/**
	 * 状态
	 */
	private String brand_status;
	/**
	 * 注册号
	 */
	private String reg_no;
	/**
	 * 类别 
	 */
	private String cat_no;
	
	/**
	 * 事件等级
	 */
	private String level;

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public String getImg_str() {
		return img_str;
	}

	public void setImg_str(String img_str) {
		this.img_str = img_str;
	}

	public String getApply_time() {
		return apply_time;
	}

	public void setApply_time(String apply_time) {
		this.apply_time = apply_time;
	}

	public String getBrand_status() {
		return brand_status;
	}

	public void setBrand_status(String brand_status) {
		this.brand_status = brand_status;
	}

	public String getReg_no() {
		return reg_no;
	}

	public void setReg_no(String reg_no) {
		this.reg_no = reg_no;
	}

	public String getCat_no() {
		return cat_no;
	}

	public void setCat_no(String cat_no) {
		this.cat_no = cat_no;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
}
