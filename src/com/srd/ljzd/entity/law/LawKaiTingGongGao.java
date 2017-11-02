package com.srd.ljzd.entity.law;

import java.io.Serializable;

/** 
 * @ClassName: LawKaiTingGongGao
 * @Description: 开庭公告
 * @author zengCG
 * @date 2016年7月14日 
 *  
 */
public class LawKaiTingGongGao implements Serializable{
	private static final long serialVersionUID = -1329062090865401519L;
	private String caseNo;//案号
	private String causeAction;//案由
	private String chengBanTing;//承办庭
	private String party;//当事人
	private String courtName;//法院名称
	private String companyName;//公司名称
	private String publishDate;//开庭时间
	private String hearCourt;//审理法庭
	private String hearJudge;//审理法官
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public String getCauseAction() {
		return causeAction;
	}
	public void setCauseAction(String causeAction) {
		this.causeAction = causeAction;
	}
	public String getChengBanTing() {
		return chengBanTing;
	}
	public void setChengBanTing(String chengBanTing) {
		this.chengBanTing = chengBanTing;
	}
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	public String getCourtName() {
		return courtName;
	}
	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	public String getHearCourt() {
		return hearCourt;
	}
	public void setHearCourt(String hearCourt) {
		this.hearCourt = hearCourt;
	}
	public String getHearJudge() {
		return hearJudge;
	}
	public void setHearJudge(String hearJudge) {
		this.hearJudge = hearJudge;
	}
}
