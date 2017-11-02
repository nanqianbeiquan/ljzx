package com.srd.ljzd.entity.monitor;

import java.io.Serializable;

public class HuanBao extends BaseDimension implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7665922939685610051L;

	/**
	 * 被处罚名称
	 */
	private String enterprisename;
	/**
	 * 违法行为类别
	 */
	private String type;
	/**
	 * 处罚内容
	 */
	private String penalty_unit;
	/**
	 * 处罚决定时间
	 */
	private String decide_time;
	/**
	 * 处罚单位
	 */
	private String penalties;
	/**
	 * 事件等级
	 */
	private String level;

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPenalty_unit() {
		return penalty_unit;
	}
	public void setPenalty_unit(String penalty_unit) {
		this.penalty_unit = penalty_unit;
	}
	public String getDecide_time() {
		return decide_time;
	}
	public void setDecide_time(String decide_time) {
		this.decide_time = decide_time;
	}
	public String getPenalties() {
		return penalties;
	}
	public void setPenalties(String penalties) {
		this.penalties = penalties;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getEnterprisename() {
		return enterprisename;
	}
	public void setEnterprisename(String enterprisename) {
		this.enterprisename = enterprisename;
	}


}
