/**   
* @Title: LawLegalInstrument.java 
* @Package com.srd.ljzd.entity.law 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年5月5日 下午2:30:05 
* @version V1.0   
*/
package com.srd.ljzd.entity.law;

import java.io.Serializable;
import java.util.Date;

/** 
 * @ClassName: LawLegalInstrument
 * @Description: 法律文书
 * @author shiyong
 * @date 2016年5月5日 下午2:30:05
 *  
 */
public class LawLegalInstrument implements Serializable{
	private static final long serialVersionUID = 1L;
	private String title;//标题
	private String caseNo;//案号
	private String courtName;//法院
	private String caseType;//案件类型
	private String instrumentType;//文书类型
	private String causeAction;//案由
	private String trialClass;//审级
	private String litigationType;//诉讼类型
	private String content;//内容
	private String companyCode;//公司编号
	private String companyId;//公司id
	private String companyName;//公司名称
	private String judgmentID;//判决ID
	private String judgmentResult;//判决结果
	private String judgmentTime;//判决时间
	private String receiveSource;//收入来源
	private String receiveTime;//收到时间
	private String receiveURL;//收入链接
	private String amountCount;//涉案金额
	private String loser;//败诉方
	private String personNameList;//当事人列表
	private String relatedAmount;//涉案金额
	private String relatedPerson;//当事人信息
	private String relatedPosition;//当事人地位
	private Date lastmodifytime;//最后修改时间
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public String getCourtName() {
		return courtName;
	}
	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}
	public String getCaseType() {
		return caseType;
	}
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}
	public String getInstrumentType() {
		return instrumentType;
	}
	public void setInstrumentType(String instrumentType) {
		this.instrumentType = instrumentType;
	}
	public String getCauseAction() {
		return causeAction;
	}
	public void setCauseAction(String causeAction) {
		this.causeAction = causeAction;
	}
	public String getTrialClass() {
		return trialClass;
	}
	public void setTrialClass(String trialClass) {
		this.trialClass = trialClass;
	}
	public String getLitigationType() {
		return litigationType;
	}
	public void setLitigationType(String litigationType) {
		this.litigationType = litigationType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getJudgmentID() {
		return judgmentID;
	}
	public void setJudgmentID(String judgmentID) {
		this.judgmentID = judgmentID;
	}
	public String getJudgmentResult() {
		return judgmentResult;
	}
	public void setJudgmentResult(String judgmentResult) {
		this.judgmentResult = judgmentResult;
	}
	public String getJudgmentTime() {
		return judgmentTime;
	}
	public void setJudgmentTime(String judgmentTime) {
		this.judgmentTime = judgmentTime;
	}
	public String getReceiveSource() {
		return receiveSource;
	}
	public void setReceiveSource(String receiveSource) {
		this.receiveSource = receiveSource;
	}
	public String getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}
	public String getReceiveURL() {
		return receiveURL;
	}
	public void setReceiveURL(String receiveURL) {
		this.receiveURL = receiveURL;
	}
	public String getAmountCount() {
		return amountCount;
	}
	public void setAmountCount(String amountCount) {
		this.amountCount = amountCount;
	}
	public String getLoser() {
		return loser;
	}
	public void setLoser(String loser) {
		this.loser = loser;
	}
	public String getPersonNameList() {
		return personNameList;
	}
	public void setPersonNameList(String personNameList) {
		this.personNameList = personNameList;
	}
	public String getRelatedAmount() {
		return relatedAmount;
	}
	public void setRelatedAmount(String relatedAmount) {
		this.relatedAmount = relatedAmount;
	}
	public String getRelatedPerson() {
		return relatedPerson;
	}
	public void setRelatedPerson(String relatedPerson) {
		this.relatedPerson = relatedPerson;
	}
	public String getRelatedPosition() {
		return relatedPosition;
	}
	public void setRelatedPosition(String relatedPosition) {
		this.relatedPosition = relatedPosition;
	}
	public Date getLastmodifytime() {
		return lastmodifytime;
	}
	public void setLastmodifytime(Date lastmodifytime) {
		this.lastmodifytime = lastmodifytime;
	}
	
	
	
}
