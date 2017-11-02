package com.srd.ljzd.entity.monitor;

import java.io.Serializable;

/**
 * 
 * 版权所有：
 * 项目名称：ljzd 
 *
 * 类描述：开庭公告
 * 类名称：com.srd.ljzd.entity.monitor  
 * 创建人：jiang.zhou
 * 创建时间：20162016年11月24日下午4:14:34
 * 修改人： 
 * 修改时间： 
 * 修改备注：   
 * @version   V2.0
 */
public class CourtAnnouncement extends BaseDimension implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8589318253062839425L;

	private String anHao;

	/**
	 * 开庭日期
	 */
	private String trialDate;
	
	/**
	 * 案由
	 */
	private String origin;
	/**
	 * 当事人
	 */
	private String privy;
	
	/**
	 * 执行法院
	 */
	private String executeCourt;
	/**
	 * 审理法院
	 */
	private String trialCourt;
	
	/**
	 * 审理法官
	 */
	private String trialJudge; 
	
	/**
	 * 事件等级
	 */
	private String level;

	public String getTrialDate() {
		return trialDate;
	}

	public void setTrialDate(String trialDate) {
		this.trialDate = trialDate;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getPrivy() {
		return privy;
	}

	public void setPrivy(String privy) {
		this.privy = privy;
	}

	public String getExecuteCourt() {
		return executeCourt;
	}

	public void setExecuteCourt(String executeCourt) {
		this.executeCourt = executeCourt;
	}

	public String getTrialCourt() {
		return trialCourt;
	}

	public void setTrialCourt(String trialCourt) {
		this.trialCourt = trialCourt;
	}

	public String getTrialJudge() {
		return trialJudge;
	}

	public void setTrialJudge(String trialJudge) {
		this.trialJudge = trialJudge;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getAnHao() {
		return anHao;
	}

	public void setAnHao(String anHao) {
		this.anHao = anHao;
	}
	
}
