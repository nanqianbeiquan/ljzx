package com.srd.ljzd.entity.monitor;

import java.io.Serializable;


/**
 * 
 * 版权所有：
 * 项目名称：ljzd 
 *
 * 类描述：行政处罚信息:pachong_administrative_penalty
 * 类名称：com.srd.ljzd.entity.monitor  
 * 创建人：jiang.zhou
 * 创建时间：20162016年11月24日下午3:30:06
 * 修改人： 
 * 修改时间： 
 * 修改备注：   
 * @version   V2.0
 */
public class Penalty extends BaseDimension implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 210193105275078111L;


	
	private String anHao;
	
	public String getDecisionDate() {
		return decisionDate;
	}
	public void setDecisionDate(String decisionDate) {
		this.decisionDate = decisionDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	/**
	 * 判决时间
	 */
	private String decisionDate;
	
	/**
	 * 身份
	 */
	private String name;
	/**
	 * 详情
	 */
	private String details;
	
	/**
	 * 事件等级
	 */
	private String level;
	
	/**
	 * 裁判文书Id
	 */
	private String judgmentId;
	
	/**
	 * 属性:原告还是被告
	 */
	private String shuxing;
	
	/**
	 * 文书类型
	 */
	private String instrumenttype;
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 案号
	 */
	private String docket;
    private String result;
	
	private String amount;
	public String getJudgmentId() {
		return judgmentId;
	}

	public void setJudgmentId(String judgmentId) {
		this.judgmentId = judgmentId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getJudgmentTime() {
		return judgmentTime;
	}

	public void setJudgmentTime(String judgmentTime) {
		this.judgmentTime = judgmentTime;
	}
	public String getShuxing() {
		return shuxing;
	}

	public void setShuxing(String shuxing) {
		this.shuxing = shuxing;
	}
	public String getInstrumenttype() {
		return instrumenttype;
	}

	public void setInstrumenttype(String instrumenttype) {
		this.instrumenttype = instrumenttype;
	}
	public String getDocket() {
		return docket;
	}

	public void setDocket(String docket) {
		this.docket = docket;
	}
	public String getCourtname() {
		return courtname;
	}

	public void setCourtname(String courtname) {
		this.courtname = courtname;
	}
	public String getAnHao() {
		return anHao;
	}

	public void setAnHao(String anHao) {
		this.anHao = anHao;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	/**
	 * 判决时间
	 */
	private String judgmentTime;
	
	/**
	 * 法庭
	 */
	private String courtname;
	
}
