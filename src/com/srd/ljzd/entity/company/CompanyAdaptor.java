package com.srd.ljzd.entity.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.srd.ljzd.entity.law.LawCourtAnnouncement;
import com.srd.ljzd.entity.law.LawDishonestInfo;
import com.srd.ljzd.entity.law.LawExecutedPerson;
import com.srd.ljzd.entity.law.LawKaiTingGongGao;
import com.srd.ljzd.entity.law.LawLegalInstrument;
import com.srd.ljzd.entity.news.NewsOpinion;


/**
 * 公司适配实体类，适配从大数据查询到的公司数据
 * @author weitao.liu
 *
 */
public class CompanyAdaptor implements Serializable{


	private static final long serialVersionUID = 9197167761942801670L;

	private HashMap<String,String> brefCompany;//公司简略信息
	
	private ArrayList<HashMap<String,String>> shareholderArray;//股东
	
	private ArrayList<HashMap<String,String>> keyPersonArray;//主要人员

	private ArrayList<HashMap<String,String>> bianGengJiLuArray;//变更记录
	
	private ArrayList<HashMap<String,String>> jingYingYiChangArray;//经营异常
	
	private ArrayList<HashMap<String,String>> dongChanDiYaArray;//动产抵押
	
	private ArrayList<HashMap<String,String>> shareSIMPAWNArray;//股权出质
	
	private ArrayList<HashMap<String,String>> fRInvArray;//企业法人代表对外投资信息
	
	private ArrayList<HashMap<String,String>> fRPositionArray;//企业法人代表在外任职信息
	
	private ArrayList<HashMap<String,String>> entInvArray;//企业对外投资
	
	private ArrayList<HashMap<String,String>> legalPersonArray;//企业法定代表人信息
	
	private ArrayList<HashMap<String,String>> fiLiationArray;//分支机构
	
	private ArrayList<HashMap<String,String>> idCardDishonest;//个人查询失信信息
	
	private ArrayList<HashMap<String,String>> idCardPerson;//个人查询被执行人信息
	
	private List<LawCourtAnnouncement> courtAnnouncementList;//法院公告
	
	private List<LawDishonestInfo> dishonestInfoList;//失信信息
	
	private List<LawExecutedPerson> executedPersonList;//被执行人
	
	private List<LawLegalInstrument> judgmentInstrumentList;//法院判决文书
	
	private List<LawLegalInstrument> otherInstrumentList;//其他文书
	
	private List<NewsOpinion> newsSentimeList;//新闻舆情经营风险
	
	private List<NewsOpinion> financeRiskList;//新闻舆情财务风险
	
	private List<NewsOpinion> lawRiskList;//新闻舆情法律风险
	
	private List<NewsOpinion> violateRiskList;//新闻舆情违规风险
	
	private List<NewsOpinion> marketRiskList;//新闻舆情市场风险
	
	private	List<LawKaiTingGongGao> kaiTingGongGaoList;//开庭公告
	
	public ArrayList<HashMap<String, String>> getIdCardDishonest() {
		return idCardDishonest;
	}

	public void setIdCardDishonest(
			ArrayList<HashMap<String, String>> idCardDishonest) {
		this.idCardDishonest = idCardDishonest;
	}

	public ArrayList<HashMap<String, String>> getIdCardPerson() {
		return idCardPerson;
	}

	public void setIdCardPerson(ArrayList<HashMap<String, String>> idCardPerson) {
		this.idCardPerson = idCardPerson;
	}

	public ArrayList<HashMap<String, String>> getFiLiationArray() {
		return fiLiationArray;
	}

	public void setFiLiationArray(ArrayList<HashMap<String, String>> fiLiationArray) {
		this.fiLiationArray = fiLiationArray;
	}

	public ArrayList<HashMap<String, String>> getShareSIMPAWNArray() {
		return shareSIMPAWNArray;
	}

	public void setShareSIMPAWNArray(
			ArrayList<HashMap<String, String>> shareSIMPAWNArray) {
		this.shareSIMPAWNArray = shareSIMPAWNArray;
	}

	public ArrayList<HashMap<String, String>> getfRInvArray() {
		return fRInvArray;
	}

	public void setfRInvArray(ArrayList<HashMap<String, String>> fRInvArray) {
		this.fRInvArray = fRInvArray;
	}

	public ArrayList<HashMap<String, String>> getfRPositionArray() {
		return fRPositionArray;
	}

	public void setfRPositionArray(
			ArrayList<HashMap<String, String>> fRPositionArray) {
		this.fRPositionArray = fRPositionArray;
	}

	public ArrayList<HashMap<String, String>> getEntInvArray() {
		return entInvArray;
	}

	public void setEntInvArray(ArrayList<HashMap<String, String>> entInvArray) {
		this.entInvArray = entInvArray;
	}

	public List<NewsOpinion> getFinanceRiskList() {
		return financeRiskList;
	}

	public void setFinanceRiskList(List<NewsOpinion> financeRiskList) {
		this.financeRiskList = financeRiskList;
	}

	public List<NewsOpinion> getLawRiskList() {
		return lawRiskList;
	}

	public void setLawRiskList(List<NewsOpinion> lawRiskList) {
		this.lawRiskList = lawRiskList;
	}

	public List<NewsOpinion> getViolateRiskList() {
		return violateRiskList;
	}

	public void setViolateRiskList(List<NewsOpinion> violateRiskList) {
		this.violateRiskList = violateRiskList;
	}

	public List<NewsOpinion> getMarketRiskList() {
		return marketRiskList;
	}

	public void setMarketRiskList(List<NewsOpinion> marketRiskList) {
		this.marketRiskList = marketRiskList;
	}

	public List<NewsOpinion> getNewsOpinionList() {
		return newsSentimeList;
	}

	public void setNewsOpinionList(List<NewsOpinion> newsSentimeList) {
		this.newsSentimeList = newsSentimeList;
	}

	public ArrayList<HashMap<String, String>> getBianGengJiLuArray() {
		return bianGengJiLuArray;
	}

	public void setBianGengJiLuArray(
			ArrayList<HashMap<String, String>> bianGengJiLuArray) {
		this.bianGengJiLuArray = bianGengJiLuArray;
	}

	public ArrayList<HashMap<String, String>> getJingYingYiChangArray() {
		return jingYingYiChangArray;
	}

	public void setJingYingYiChangArray(
			ArrayList<HashMap<String, String>> jingYingYiChangArray) {
		this.jingYingYiChangArray = jingYingYiChangArray;
	}

	public ArrayList<HashMap<String, String>> getDongChanDiYaArray() {
		return dongChanDiYaArray;
	}

	public void setDongChanDiYaArray(
			ArrayList<HashMap<String, String>> dongChanDiYaArray) {
		this.dongChanDiYaArray = dongChanDiYaArray;
	}

	public HashMap<String, String> getBrefCompany() {
		return brefCompany;
	}

	public void setBrefCompany(HashMap<String, String> brefCompany) {
		this.brefCompany = brefCompany;
	}

	public ArrayList<HashMap<String, String>> getShareholderArray() {
		return shareholderArray;
	}

	public void setShareholderArray(
			ArrayList<HashMap<String, String>> shareholderArray) {
		this.shareholderArray = shareholderArray;
	}

	public ArrayList<HashMap<String, String>> getKeyPersonArray() {
		return keyPersonArray;
	}

	public void setKeyPersonArray(ArrayList<HashMap<String, String>> keyPersonArray) {
		this.keyPersonArray = keyPersonArray;
	}

	public List<LawCourtAnnouncement> getCourtAnnouncementList() {
		return courtAnnouncementList;
	}

	public void setCourtAnnouncementList(
			List<LawCourtAnnouncement> courtAnnouncementList) {
		this.courtAnnouncementList = courtAnnouncementList;
	}

	public List<LawDishonestInfo> getDishonestInfoList() {
		return dishonestInfoList;
	}

	public void setDishonestInfoList(List<LawDishonestInfo> dishonestInfoList) {
		this.dishonestInfoList = dishonestInfoList;
	}

	public ArrayList<HashMap<String, String>> getLegalPersonArray() {
		return legalPersonArray;
	}

	public void setLegalPersonArray(
			ArrayList<HashMap<String, String>> legalPersonArray) {
		this.legalPersonArray = legalPersonArray;
	}

	/**
	 * @return the executedPersonList
	 */
	public List<LawExecutedPerson> getExecutedPersonList() {
		return executedPersonList;
	}

	/**
	 * @param executedPersonList the executedPersonList to set
	 */
	public void setExecutedPersonList(List<LawExecutedPerson> executedPersonList) {
		this.executedPersonList = executedPersonList;
	}

	public List<LawLegalInstrument> getJudgmentInstrumentList() {
		return judgmentInstrumentList;
	}

	public void setJudgmentInstrumentList(
			List<LawLegalInstrument> judgmentInstrumentList) {
		this.judgmentInstrumentList = judgmentInstrumentList;
	}

	public List<LawLegalInstrument> getOtherInstrumentList() {
		return otherInstrumentList;
	}

	public void setOtherInstrumentList(List<LawLegalInstrument> otherInstrumentList) {
		this.otherInstrumentList = otherInstrumentList;
	}

	public List<LawKaiTingGongGao> getKaiTingGongGaoList() {
		return kaiTingGongGaoList;
	}

	public void setKaiTingGongGaoList(List<LawKaiTingGongGao> kaiTingGongGaoList) {
		this.kaiTingGongGaoList = kaiTingGongGaoList;
	}
	
}
