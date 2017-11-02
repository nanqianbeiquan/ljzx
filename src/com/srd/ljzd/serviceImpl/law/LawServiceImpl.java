package com.srd.ljzd.serviceImpl.law;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.srd.ljzd.config.Global;
import com.srd.ljzd.dto.law.LawLegalInstrumentDTO;
import com.srd.ljzd.entity.law.LawAdaptor;
import com.srd.ljzd.entity.law.LawCourtAnnouncement;
import com.srd.ljzd.entity.law.LawDishonestInfo;
import com.srd.ljzd.entity.law.LawExecutedPerson;
import com.srd.ljzd.entity.law.LawKaiTingGongGao;
import com.srd.ljzd.entity.law.LawLegalInstrument;
import com.srd.ljzd.service.law.LawService;
import com.srd.ljzd.util.DateUtils;
import com.srd.ljzd.util.JerseyUtil;
import com.srd.ljzd.util.Page;

/** 
* @ClassName: LawServiceImpl
* @Description: 司法信息Service实现类
* @author zengCG
* @date 2016年01月03日 
*  
*/
@Service("lawService")
public class LawServiceImpl implements LawService{
	
	protected static Logger logger = LogManager.getLogger(LawServiceImpl.class.getName());
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	
	
	/** 
	* @Description: 解析法院公告数据
	* @author zengCG
	* @date 2016年5月18日 
	*/
	public LawCourtAnnouncement resolveCourtAnnouncement(JSONObject jsonObject){
		
		LawCourtAnnouncement courtAnnouncement = new LawCourtAnnouncement();
		
		if(jsonObject.containsKey("bltin:rld_prn")){
			courtAnnouncement.setParty(jsonObject.getString("bltin:rld_prn"));
		}
		if(jsonObject.containsKey("bltin:blt_content")){
			courtAnnouncement.setAnnouncementContent(jsonObject.getString("bltin:blt_content"));
		}
		if(jsonObject.containsKey("bltin:blt_type")){
			courtAnnouncement.setAnnouncementType(jsonObject.getString("bltin:blt_type"));
		}
		if(jsonObject.containsKey("bltin:crt_name")){
			courtAnnouncement.setCourtName(jsonObject.getString("bltin:crt_name"));
		}
		if(jsonObject.containsKey("bltin:id")){
			courtAnnouncement.setCourtID(jsonObject.getString("bltin:id"));
		}
		if(jsonObject.containsKey("bltin:pub_date")){
			courtAnnouncement.setPublishDate(jsonObject.getString("bltin:pub_date"));
		}
		
		return courtAnnouncement;
	}
	
	/** 
	* @Description: 法院公告详细信息
	* @author zengCG
	* @date 2016年5月18日 
	*/
	@Override
	public LawCourtAnnouncement queryCourtAnnouncementListDetails(String companyName, String courtID) {
		String url = Global.getConfig("dataService") + Global.getConfig("lawInfo");
		
		Map<String, String> params = new HashMap<String, String>();
		String columns = "bltin:blt_content,bltin:blt_type,bltin:crt_name,bltin:id,bltin:pub_date,bltin:rld_prn";
		params.put("columns", columns);
		params.put("companyName", companyName);
		params.put("id", courtID+"&bltin");
		
		JSONObject jsonResult = JerseyUtil.sendHttpFormRequest(url, params);
		
		LawCourtAnnouncement courtAnnouncement = new LawCourtAnnouncement();
		
		if(jsonResult != null){
			if(jsonResult.getString("respCode").equals("200") && jsonResult.getString("respMsg").equals("success")){
				if(jsonResult.get("data") != null && !jsonResult.get("data").equals("")&&jsonResult.getJSONArray("data").size()>0){
					courtAnnouncement = resolveCourtAnnouncement((JSONObject)jsonResult.getJSONArray("data").get(0));
				}
			}else{
				logger.error("respCode:" + jsonResult.getString("respCode") + ", respMsg:" + jsonResult.getString("respMsg"));
			}
		}
		
		return courtAnnouncement;
	}
	
	/** 
	* @Description: 失信信息详细信息
	* @author zengCG
	* @date 2016年5月18日 
	*/
	@Override
	public LawDishonestInfo queryDishonestInfoDetails(String caseNo,String companyName) {
		String url = Global.getConfig("dataService") + Global.getConfig("lawInfo");
		
		Map<String, String> params = new HashMap<String, String>();
		String columns = "shixin:fddbr,shixin:shixinid,shixin:ah,shixin:fbsj,"
				+ "shixin:zxfy,shixin:dm,shixin:mc,shixin:sf,shixin:lxqk,"
				+ "shixin:wsqdyw,shixin:sxjtqk,shixin:sj";
		params.put("columns", columns);
		params.put("companyName", companyName);
		caseNo = caseNo.replaceAll("[(]","（");
		caseNo = caseNo.replaceAll("[)]","）");
		
		params.put("id", caseNo+"&shixin");
		
		JSONObject jsonResult = JerseyUtil.sendHttpFormRequest(url, params);
		
		LawDishonestInfo dishonestInfo = new LawDishonestInfo();
		
		if(jsonResult != null){
			if(jsonResult.getString("respCode").equals("200") && jsonResult.getString("respMsg").equals("success")){
				if(jsonResult.get("data") != null && !jsonResult.get("data").equals("")&&jsonResult.getJSONArray("data").size()>0){
					dishonestInfo = resolveDishonestInfo((JSONObject)jsonResult.getJSONArray("data").get(0));
				}
			}else{
				logger.error("respCode:" + jsonResult.getString("respCode") + ", respMsg:" + jsonResult.getString("respMsg"));
			}
		}
		
		return dishonestInfo;
	}
	
	/** 
	* @Description: 解析失信信息数据
	* @author zengCG
	* @date 2016年5月18日 
	*/
	public LawDishonestInfo resolveDishonestInfo(JSONObject jsonObject){
		LawDishonestInfo dishonestInfo = new LawDishonestInfo();
		
		if(jsonObject.containsKey("shixin:mc")){
			dishonestInfo.setExecutorName(jsonObject.getString("shixin:mc"));
		}
		if(jsonObject.containsKey("shixin:dm")){
			dishonestInfo.setOrganizationCode(jsonObject.getString("shixin:dm"));
		}
		if(jsonObject.containsKey("shixin:zxfy")){
			dishonestInfo.setExecutionCourt(jsonObject.getString("shixin:zxfy"));
		}
		if(jsonObject.containsKey("shixin:ah")){
			dishonestInfo.setCaseNo(jsonObject.getString("shixin:ah"));
		}
		if(jsonObject.containsKey("shixin:sf")){
			dishonestInfo.setProvince(jsonObject.getString("shixin:sf"));
		}
		if(jsonObject.containsKey("shixin:fddbr")){
			dishonestInfo.setLegalPerson(jsonObject.getString("shixin:fddbr"));
		}
		if(jsonObject.containsKey("shixin:lxqk")){
			dishonestInfo.setPerformanceStatus(jsonObject.getString("shixin:lxqk"));
		}
		if(jsonObject.containsKey("shixin:wsqdyw")){
			dishonestInfo.setDuty(jsonObject.getString("shixin:wsqdyw"));
		}
		if(jsonObject.containsKey("shixin:sxjtqk")){
			dishonestInfo.setSpecificCircumstance(jsonObject.getString("shixin:sxjtqk"));
		}
		if(jsonObject.containsKey("shixin:shixinid")){
			dishonestInfo.setShixinid(jsonObject.getString("shixin:shixinid"));
		}
		if(jsonObject.containsKey("shixin:fbsj")&&StringUtils.isNotEmpty(jsonObject.getString("shixin:fbsj"))){
			dishonestInfo.setPublishDate(jsonObject.getString("shixin:fbsj"));
		}
		try {
			
			if(jsonObject.containsKey("shixin:lastupdatetime")&&StringUtils.isNotEmpty(jsonObject.getString("shixin:lastupdatetime"))){
				dishonestInfo.setLastmodifytime(sdf.parse(jsonObject.getString("shixin:lastupdatetime")));
			}
			if(jsonObject.containsKey("shixin:sj")&&StringUtils.isNotEmpty(jsonObject.getString("shixin:sj"))){
				dishonestInfo.setFilingTime(sdf.parse(jsonObject.getString("shixin:sj")));
			}
		} catch (ParseException e) {
			logger.error("日期转换出错：" + e.getMessage());
		}
		return dishonestInfo;
	}
	
	/** 
	* @Description: 解析被执行人数据
	* @author zengCG
	* @date 2016年6月22日 
	*/
	public LawExecutedPerson resolveExecutedPerson(JSONObject jsonObject){
		LawExecutedPerson executedPerson = new LawExecutedPerson();
		
		if(jsonObject.containsKey("beizhixing:mc")){
			executedPerson.setExecutorName(jsonObject.getString("beizhixing:mc"));
		}
		if(jsonObject.containsKey("beizhixing:sj")){
			executedPerson.setFilingTime(jsonObject.getString("beizhixing:sj"));
		}
		if(jsonObject.containsKey("beizhixing:ah")){
			executedPerson.setCaseNo(jsonObject.getString("beizhixing:ah"));
		}
		if(jsonObject.containsKey("beizhixing:dm")){
			executedPerson.setIdentityID(jsonObject.getString("beizhixing:dm"));
		}
		if(jsonObject.containsKey("beizhixing:zxfy")){
			executedPerson.setExecutionCourt(jsonObject.getString("beizhixing:zxfy"));
		}
		if(jsonObject.containsKey("beizhixing:ajzt")){
			executedPerson.setCaseStatus(jsonObject.getString("beizhixing:ajzt"));
		}
		if(jsonObject.containsKey("beizhixing:gzcs")){
			executedPerson.setAttentionFrequency(jsonObject.getString("beizhixing:gzcs"));
		}
		if(jsonObject.containsKey("beizhixing:zxbd")){
			executedPerson.setObjectOfExecution(jsonObject.getString("beizhixing:zxbd"));
		}
		return executedPerson;
	}
	
	/** 
	* @Description: 解析开庭公告数据
	* @author zengCG
	* @date 2016年7月14日 
	*/
	public LawKaiTingGongGao resolveKaiTingGongGao(JSONObject jsonObject){
		LawKaiTingGongGao ktGongGao = new LawKaiTingGongGao();
		
		if(jsonObject.containsKey("kai_ting_gong_gao:an_hao")){
			ktGongGao.setCaseNo(jsonObject.getString("kai_ting_gong_gao:an_hao"));
		}
		if(jsonObject.containsKey("kai_ting_gong_gao:an_you")){
			ktGongGao.setCauseAction(jsonObject.getString("kai_ting_gong_gao:an_you"));
		}
		if(jsonObject.containsKey("kai_ting_gong_gao:cheng_ban_ting")){
			ktGongGao.setChengBanTing(jsonObject.getString("kai_ting_gong_gao:cheng_ban_ting"));
		}
		if(jsonObject.containsKey("kai_ting_gong_gao:dang_shi_ren")){
			ktGongGao.setParty(jsonObject.getString("kai_ting_gong_gao:dang_shi_ren"));
		}
		if(jsonObject.containsKey("kai_ting_gong_gao:fa_yuan_ming_cheng")){
			ktGongGao.setCourtName(jsonObject.getString("kai_ting_gong_gao:fa_yuan_ming_cheng"));
		}
		if(jsonObject.containsKey("kai_ting_gong_gao:gong_si_ming_cheng")){
			ktGongGao.setCompanyName(jsonObject.getString("kai_ting_gong_gao:gong_si_ming_cheng"));
		}
		if(jsonObject.containsKey("kai_ting_gong_gao:kai_ting_ri_qi")){
			ktGongGao.setPublishDate(jsonObject.getString("kai_ting_gong_gao:kai_ting_ri_qi"));
		}
		if(jsonObject.containsKey("kai_ting_gong_gao:shen_li_fa_ting")){
			ktGongGao.setHearCourt(jsonObject.getString("kai_ting_gong_gao:shen_li_fa_ting"));
		}
		if(jsonObject.containsKey("kai_ting_gong_gao:zhu_shen_fa_guan")){
			ktGongGao.setHearJudge(jsonObject.getString("kai_ting_gong_gao:zhu_shen_fa_guan"));
		}
		return ktGongGao;
	}
	
	@Override
	public List<LawLegalInstrument> queryLegalInstrument(LawLegalInstrumentDTO legalInstrumentDTO) {
		String url = Global.getConfig("dataService") + Global.getConfig("lawInfo");
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("companyName", legalInstrumentDTO.getCompanyName());
		
		StringBuffer columns = new StringBuffer("judgidentifier:amountcount,judgidentifier:companyname,judgidentifier:judgmentid,");
		columns.append("judgidentifier:causeaction,judgidentifier:shuxing,judgidentifier:docket,");
		
		if(StringUtils.isNotEmpty(legalInstrumentDTO.getInstrumentTypes())){
			columns.append("judgidentifier:instrumenttype=" + legalInstrumentDTO.getInstrumentTypes().replace(",", "||") + ",");
		}else{
			columns.append("judgidentifier:instrumenttype,");
		}
		
		if(StringUtils.isNotEmpty(legalInstrumentDTO.getTrialClasses())){
			columns.append("judgidentifier:trialclass=" + legalInstrumentDTO.getTrialClasses().replace(",", "||") + ",");
		}else{
			columns.append("judgidentifier:trialclass,");
		}
		
		if(StringUtils.isNotEmpty(legalInstrumentDTO.getLitigationTypes())){
			columns.append("judgidentifier:litigationtype=" + legalInstrumentDTO.getLitigationTypes().replace(",", "||") + ",");
		}else{
			columns.append("judgidentifier:litigationtype,");
		}
		
		if(StringUtils.isNotEmpty(legalInstrumentDTO.getRelatedPositions())){
			columns.append("judgidentifier:shuxing=" + legalInstrumentDTO.getRelatedPositions().replace(",", "||") + ",");
		}else{
			columns.append("judgidentifier:shuxing,");
		}
		
		if(StringUtils.isNotEmpty(legalInstrumentDTO.getBeginDate()) && StringUtils.isNotEmpty(legalInstrumentDTO.getEndDate())){
			columns.append("judgidentifier:judgmenttime=" + legalInstrumentDTO.getBeginDate() + "&" + legalInstrumentDTO.getEndDate() + ",");
		}else if(StringUtils.isNotEmpty(legalInstrumentDTO.getBeginDate())){
			columns.append("judgidentifier:judgmenttime=" + legalInstrumentDTO.getBeginDate() + "&,");
		}else if(StringUtils.isNotEmpty(legalInstrumentDTO.getEndDate())){
			columns.append("judgidentifier:judgmenttime=&" + legalInstrumentDTO.getEndDate() + ",");
		}else{
			columns.append("judgidentifier:judgmenttime,");
		}
		
		if(StringUtils.isNotEmpty(legalInstrumentDTO.getMinAmountCount()) && StringUtils.isNotEmpty(legalInstrumentDTO.getMaxAmountCount())){
			columns.append("judgidentifier:amountcount=" + legalInstrumentDTO.getMinAmountCount() + "&" + legalInstrumentDTO.getMaxAmountCount());
		}else if(StringUtils.isNotEmpty(legalInstrumentDTO.getMinAmountCount())){
			columns.append("judgidentifier:amountcount=" + legalInstrumentDTO.getMinAmountCount()  + "&");
		}else if(StringUtils.isNotEmpty(legalInstrumentDTO.getMaxAmountCount())){
			columns.append("judgidentifier:amountcount=" + legalInstrumentDTO.getMaxAmountCount());
		}else{
			columns.append("judgidentifier:amountcount");
		}
		
		params.put("columns", columns.toString());
		
		JSONObject jsonResult = JerseyUtil.sendHttpFormRequest(url, params);
		
		List<LawLegalInstrument> legalInstrumentList = new ArrayList<LawLegalInstrument>();
		
		if(jsonResult != null){
			if(jsonResult.getString("respCode").equals("200") && jsonResult.getString("respMsg").equals("success")){
				if(jsonResult.get("data") != null && !jsonResult.get("data").equals("")){
					JSONArray jsonArray = jsonResult.getJSONArray("data");
					
					for(int i=0;i<jsonArray.size();i++){
						legalInstrumentList.add(resolveLegalInstrument((JSONObject)jsonArray.get(i)));
					}
				}
			}else{
				logger.error("respCode:" + jsonResult.getString("respCode") + ", respMsg:" + jsonResult.getString("respMsg"));
			}
		}
		if(legalInstrumentList!=null &&legalInstrumentList.size()>0){
			LegalInstrumentComparator legalInstrumentComparator = new LegalInstrumentComparator();
			
			Collections.sort(legalInstrumentList, legalInstrumentComparator);
		}
		return legalInstrumentList;
	}

	/** 
	* @ClassName: LegalInstrumentComparator
	* @Description: 裁判文书比较器
	* @author zengCG
	* @date 2016年01月03日 
	*  
	*/
	class LegalInstrumentComparator implements Comparator<LawLegalInstrument>{
		
		@Override
		public int compare(LawLegalInstrument legalInstrument1, LawLegalInstrument legalInstrument2){
			int result = 0;
			
			Date date1 = DateUtils.getLocalDate(legalInstrument1.getJudgmentTime());
			Date date2 = DateUtils.getLocalDate(legalInstrument2.getJudgmentTime());
			
			if(date1==null&&date2!=null){
				result = 1;
			}
			if(date2==null&&date1!=null){
				result = -1;
			}
			if(date1!=null&&date2!=null){
				if(date1.before(date2)){//返回true，说明date1 比date2早，
					result = 1;
				}else{//返回false,包含两种情况，一是date1==date2,二是date1比date2晚
					/*if(DateUtils.getLocalStr("yyyy-MM-dd", date1).equals(DateUtils.getLocalStr("yyyy-MM-dd", date2))){
						result = 0;
					}else{
						result = -1;
					}*/
					if(date1.after(date2)){result = -1;}else{result = 0;}
				}
			}
			
			return result;
		}
	}
	
	/** 
	* @ClassName: CourtAnnouncementComparator
	* @Description: 法院公告比较器
	* @author shiyong
	* @date 2016年7月27日 下午1:15:32
	*  
	*/
	class CourtAnnouncementComparator implements Comparator<LawCourtAnnouncement>{
		
		@Override
		public int compare(LawCourtAnnouncement lawCourtAnnouncement1, LawCourtAnnouncement lawCourtAnnouncement2){
			int result = 0;
			
			Date date1 = DateUtils.getLocalDate(lawCourtAnnouncement1.getPublishDate());
			Date date2 = DateUtils.getLocalDate(lawCourtAnnouncement2.getPublishDate());
			
			if(date1==null&&date2!=null){
				result = 1;
			}
			if(date2==null&&date1!=null){
				result = -1;
			}
			if(date1!=null&&date2!=null){
				if(date1.before(date2)){//返回true，说明date1 比date2早，
					result = 1;
				}else{//返回false,包含两种情况，一是date1==date2,二是date1比date2晚
					if(date1.after(date2)){result = -1;}else{result = 0;}
				}
			}
			
			return result;
		}
	}
	
	/** 
	* @ClassName: ExecutedPersonComparator
	* @Description: 被执行人比较器
	* @author shiyong
	* @date 2016年7月27日 下午1:15:55
	*  
	*/
	class ExecutedPersonComparator implements Comparator<LawExecutedPerson>{
		
		@Override
		public int compare(LawExecutedPerson lawExecutedPerson1, LawExecutedPerson lawExecutedPerson2){
			int result = 0;
			
			Date date1 = DateUtils.getLocalDate(lawExecutedPerson1.getFilingTime());
			Date date2 = DateUtils.getLocalDate(lawExecutedPerson2.getFilingTime());
			
			if(date1==null&&date2!=null){
				result = 1;
			}
			if(date2==null&&date1!=null){
				result = -1;
			}
			if(date1!=null&&date2!=null){
				if(date1.before(date2)){//返回true，说明date1 比date2早，
					result = 1;
				}else{//返回false,包含两种情况，一是date1==date2,二是date1比date2晚
					if(date1.after(date2)){result = -1;}else{result = 0;}
				}
			}
			
			return result;
		}
	}

	/** 
	* @ClassName: DishonestInfoComparator
	* @Description: 失信人比较器
	* @author shiyong
	* @date 2016年7月27日 下午1:16:19
	*  
	*/
	class DishonestInfoComparator implements Comparator<LawDishonestInfo>{
		
		@Override
		public int compare(LawDishonestInfo lawDishonestInfo1, LawDishonestInfo lawDishonestInfo2){
			int result = 0;
			
			Date date1 = DateUtils.getLocalDate(lawDishonestInfo1.getPublishDate());
			Date date2 = DateUtils.getLocalDate(lawDishonestInfo2.getPublishDate());
			
			if(date1==null&&date2!=null){
				result = 1;
			}
			if(date2==null&&date1!=null){
				result = -1;
			}
			if(date1!=null&&date2!=null){
				if(date1.before(date2)){//返回true，说明date1 比date2早，
					result = 1;
				}else{//返回false,包含两种情况，一是date1==date2,二是date1比date2晚
					if(date1.after(date2)){result = -1;}else{result = 0;}
				}
			}
			
			return result;
		}
	}
	
	/** 
	* @ClassName: KaiTingGongGaoComparator
	* @Description: 开庭公告比较器
	* @author shiyong
	* @date 2016年7月27日 下午1:16:39
	*  
	*/
	class KaiTingGongGaoComparator implements Comparator<LawKaiTingGongGao>{
		
		@Override
		public int compare(LawKaiTingGongGao lawKaiTingGongGao1, LawKaiTingGongGao lawKaiTingGongGao2){
			int result = 0;
			
			Date date1 = DateUtils.getLocalDate(lawKaiTingGongGao1.getPublishDate());;
			Date date2 = DateUtils.getLocalDate(lawKaiTingGongGao2.getPublishDate());;
			
			if(date1==null&&date2!=null){
				result = 1;
			}
			if(date2==null&&date1!=null){
				result = -1;
			}
			if(date1!=null&&date2!=null){
				if(date1.before(date2)){//返回true，说明date1 比date2早，
					result = 1;
				}else{//返回false,包含两种情况，一是date1==date2,二是date1比date2晚
					if(date1.after(date2)){result = -1;}else{result = 0;}
				}
			}
			
			return result;
		}
	}
	
	@Override
	public Page<LawLegalInstrument> findLegalInstrumentPage(LawLegalInstrumentDTO legalInstrumentDTO, int currentPageNo, int pageSize) {
		List<LawLegalInstrument> legalInstrumentList = queryLegalInstrument(legalInstrumentDTO);
		
		Page<LawLegalInstrument> page = new Page<LawLegalInstrument>();
		page.setPrePageNo(currentPageNo - 1);
		page.setNextPageNo(currentPageNo + 1);
		page.setCurrentPageNo(currentPageNo);
		page.setPageSize(pageSize);
		page.setRecordSum(new Long(legalInstrumentList.size()));
		page.setPageSum(legalInstrumentList.size()/pageSize + 1);
		page.setBeginIndex(1);
		
		if(currentPageNo > 1){
			page.setHasPrePage(true);
		}else{
			page.setHasPrePage(false);
		}
		
		if(currentPageNo < (legalInstrumentList.size()/pageSize + 1)){
			page.setHasNextPage(true);
		}else{
			page.setHasNextPage(false);
		}
		
		List<LawLegalInstrument> tempInstrumentList = new ArrayList<LawLegalInstrument>();
		
		if(currentPageNo*pageSize > legalInstrumentList.size()){
			for(int i=((currentPageNo-1)*pageSize);i<legalInstrumentList.size();i++){
				tempInstrumentList.add(legalInstrumentList.get(i));
			}
		}else{
			for(int i=((currentPageNo-1)*pageSize);i<(currentPageNo*pageSize);i++){
				tempInstrumentList.add(legalInstrumentList.get(i));
			}
		}
		
		page.setResults(tempInstrumentList);
		
		return page;
	}
	
	@Override
	public LawLegalInstrument viewLegalInstrumentDetail(String companyName, String judgmentID) {
		String url = Global.getConfig("dataService") + Global.getConfig("lawInfo");
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("companyName", companyName);
		
		String columns = "judgidentifier:judgmenttime,judgidentifier:content,judgidentifier:companyname,judgidentifier:shuxing,judgidentifier:judgmentid,"
				+ "judgidentifier:judgmenttime,judgidentifier:title,judgidentifier:instrumenttype,judgidentifier:docket,judgidentifier:courtname";
		
		params.put("columns", columns);
		
		params.put("id", judgmentID + "&judgidentifier");
		
		JSONObject jsonResult = JerseyUtil.sendHttpFormRequest(url, params);
		
		LawLegalInstrument legalInstrument = new LawLegalInstrument();
		
		if(jsonResult != null){
			if(jsonResult.getString("respCode").equals("200") && jsonResult.getString("respMsg").equals("success")){
				if(jsonResult.get("data") != null && !jsonResult.get("data").equals("")&&jsonResult.getJSONArray("data").size()>0){
					JSONArray jsonArray = jsonResult.getJSONArray("data");
					
					legalInstrument = resolveLegalInstrument((JSONObject)jsonArray.get(0));
				}
			}else{
				logger.error("respCode:" + jsonResult.getString("respCode") + ", respMsg:" + jsonResult.getString("respMsg"));
			}
		}
		
		return legalInstrument;
	}
	
	/** 
	* @Title: resolveLegalInstrument
	* @Description: 解析裁判文书数据
	* @param @param array
	* @param @return 设定文件 
	* @return List<LawLegalInstrument> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年5月9日 下午2:45:41
	*/
	public LawLegalInstrument resolveLegalInstrument(JSONObject jsonObject){
		
		LawLegalInstrument legalInstrument = new LawLegalInstrument();
		
		if(jsonObject.containsKey("judgidentifier:title")){
			if("null".equals(jsonObject.getString("judgidentifier:title"))){
				legalInstrument.setTitle("--");
			}else{
				legalInstrument.setTitle(jsonObject.getString("judgidentifier:title"));
			}
		}
		if(jsonObject.containsKey("judgidentifier:docket")){
			if("null".equals(jsonObject.getString("judgidentifier:docket"))){
				legalInstrument.setCaseNo("--");
			}else{
				legalInstrument.setCaseNo(jsonObject.getString("judgidentifier:docket"));
			}
		}
		if(jsonObject.containsKey("judgidentifier:courtname")){
			if("null".equals(jsonObject.getString("judgidentifier:courtname"))){
				legalInstrument.setCourtName("--");
			}else{
				legalInstrument.setCourtName(jsonObject.getString("judgidentifier:courtname"));
			}
		}
		if(jsonObject.containsKey("judgidentifier:casetype")){
			if("null".equals(jsonObject.getString("judgidentifier:casetype"))){
				legalInstrument.setCaseType("--");
			}else{
				legalInstrument.setCaseType(jsonObject.getString("judgidentifier:casetype"));
			}
		}
		if(jsonObject.containsKey("judgidentifier:instrumenttype")){
			if("null".equals(jsonObject.getString("judgidentifier:instrumenttype"))){
				legalInstrument.setInstrumentType("--");
			}else{
				legalInstrument.setInstrumentType(jsonObject.getString("judgidentifier:instrumenttype"));
			}
		}
		if(jsonObject.containsKey("judgidentifier:causeaction")){
			if("null".equals(jsonObject.getString("judgidentifier:causeaction"))){
				legalInstrument.setCauseAction("--");
			}else{
				legalInstrument.setCauseAction(jsonObject.getString("judgidentifier:causeaction"));
			}
		}
		if(jsonObject.containsKey("judgidentifier:trialclass")){
			if("null".equals(jsonObject.getString("judgidentifier:trialclass"))){
				legalInstrument.setTrialClass("--");
			}else{
				legalInstrument.setTrialClass(jsonObject.getString("judgidentifier:trialclass"));
			}
		}
		if(jsonObject.containsKey("judgidentifier:litigationtype")){
			if("null".equals(jsonObject.getString("judgidentifier:litigationtype"))){
				legalInstrument.setLitigationType("--");
			}else{
				legalInstrument.setLitigationType(jsonObject.getString("judgidentifier:litigationtype"));
			}
		}
		if(jsonObject.containsKey("judgidentifier:content")){
			if("null".equals(jsonObject.getString("judgidentifier:content"))){
				legalInstrument.setContent("--");
			}else{
				StringBuffer content = new StringBuffer(jsonObject.getString("judgidentifier:content"));
				
				 String patt = "(裁定如下|判决如下)";
				 Pattern r = Pattern.compile(patt);
				 Matcher mv = r.matcher(content);
				 String result=mv.replaceAll("<span style=\"background:#d1edfe;\">$1</span>");
				 content=new StringBuffer(result);
				 
				legalInstrument.setContent(content.toString());
			}
			
		}
		if(jsonObject.containsKey("judgidentifier:companycode")){
			legalInstrument.setCompanyCode(jsonObject.getString("judgidentifier:companycode"));
		}
		if(jsonObject.containsKey("judgidentifier:companyid")){
			legalInstrument.setCompanyId(jsonObject.getString("judgidentifier:companyid"));
		}
		if(jsonObject.containsKey("judgidentifier:companyname")){
			legalInstrument.setCompanyName(jsonObject.getString("judgidentifier:companyname"));
		}
		if(jsonObject.containsKey("judgidentifier:judgmentid")){
			legalInstrument.setJudgmentID(jsonObject.getString("judgidentifier:judgmentid"));
		}
		if(jsonObject.containsKey("judgidentifier:judgmentresult")){
			if("null".equals(jsonObject.getString("judgidentifier:judgmentresult"))){
				legalInstrument.setJudgmentResult("--");
			}else{
				legalInstrument.setJudgmentResult(jsonObject.getString("judgidentifier:judgmentresult"));
			}
			
		}
		if(jsonObject.containsKey("judgidentifier:judgmenttime")){
			if("null".equals(jsonObject.getString("judgidentifier:judgmenttime"))){
				legalInstrument.setJudgmentTime("--");
			}else{
				legalInstrument.setJudgmentTime(jsonObject.getString("judgidentifier:judgmenttime"));
			}
		}
		if(jsonObject.containsKey("judgidentifier:receivefrom")){
			legalInstrument.setReceiveSource(jsonObject.getString("judgidentifier:receivefrom"));
		}
		if(jsonObject.containsKey("judgidentifier:receivetime")){
			legalInstrument.setReceiveTime(jsonObject.getString("judgidentifier:receivetime"));
		}
		if(jsonObject.containsKey("judgidentifier:receiveurl")){
			legalInstrument.setReceiveURL(jsonObject.getString("judgidentifier:receiveurl"));
		}
		if(jsonObject.containsKey("judgidentifier:amountcount")){
			if("null".equals(jsonObject.getString("judgidentifier:amountcount"))){
				legalInstrument.setAmountCount("--");
			}else{
				legalInstrument.setAmountCount(jsonObject.getString("judgidentifier:amountcount"));
			}
		}
		if(jsonObject.containsKey("judgidentifier:loser")){
			legalInstrument.setLoser(jsonObject.getString("judgidentifier:loser"));
		}
		if(jsonObject.containsKey("judgidentifier:personnamelist")){
			if("null".equals(jsonObject.getString("judgidentifier:personnamelist"))){
				legalInstrument.setPersonNameList("--");
			}else{
				legalInstrument.setPersonNameList(jsonObject.getString("judgidentifier:personnamelist"));
			}
		}
		if(jsonObject.containsKey("judgidentifier:relatedamount")){
			legalInstrument.setRelatedAmount(jsonObject.getString("judgidentifier:relatedamount"));
		}
		if(jsonObject.containsKey("judgidentifier:relatedperson")){
			legalInstrument.setRelatedPerson(jsonObject.getString("judgidentifier:relatedperson"));
		}
		if(jsonObject.containsKey("judgidentifier:shuxing")){
			if("null".equals(jsonObject.getString("judgidentifier:shuxing"))){
				legalInstrument.setRelatedPosition("--");
			}else{
				legalInstrument.setRelatedPosition(jsonObject.getString("judgidentifier:shuxing"));
			}
		}
		
		return legalInstrument;
	}
	
	@Override
	public Map<String, Object> initPage(int curPage, int pageSize,
			List<? extends Object> array) {
		if (curPage <= 0) {
			curPage = 1;
		}
		if (pageSize < 0) {
			pageSize = 5;
		}

		Map<String, Object> page = new HashMap<String, Object>();
		page.put("curPage", curPage);
		page.put("pageSize", pageSize);
		int size = 0;
		if (array != null && array.size()> 0) {
			size = array.size();
		}
		page.put("size", size);
		page.put("pageNum", (size % pageSize == 0) ? (size / pageSize) : (size
				/ pageSize + 1));
		page.put("startIndex", pageSize * (curPage - 1));
		page.put("endIndex", pageSize * curPage - 1);
		return page;
	}
	
	
	public LawAdaptor queryLawInfo(String companyName) {
		LawAdaptor lawMessage = new LawAdaptor();
		
		String url = Global.getConfig("dataService") + Global.getConfig("lawInfo");
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("companyName", companyName);
		
		StringBuffer columns = new StringBuffer();
		columns.append("judgidentifier:judgmentid,judgidentifier:title,judgidentifier:judgmenttime,judgidentifier:shuxing,judgidentifier:judgmentresult,judgidentifier:instrumenttype,");
		columns.append("judgidentifier:companyname,judgidentifier:personnamelist,judgidentifier:courtname,shixin:fbsj,shixin:ah,shixin:sf,shixin:zxfy,shixin:lxqk,shixin:mc,shixin:shixinid,");
		columns.append("bltin:pub_date,bltin:blt_content,bltin:blt_type,bltin:crt_name,bltin:id,");
		columns.append("beizhixing:ah,beizhixing:ajzt,beizhixing:zxfy,beizhixing:dm,beizhixing:gzcs,beizhixing:mc,beizhixing:sj,beizhixing:zxbd,");
		columns.append("kai_ting_gong_gao:an_hao,kai_ting_gong_gao:an_you,kai_ting_gong_gao:cheng_ban_ting,kai_ting_gong_gao:dang_shi_ren,kai_ting_gong_gao:fa_yuan_ming_cheng,"
				+ "kai_ting_gong_gao:gong_si_ming_cheng,kai_ting_gong_gao:kai_ting_ri_qi,kai_ting_gong_gao:shen_li_fa_ting,kai_ting_gong_gao:zhu_shen_fa_guan");
		params.put("columns", columns.toString());
		
		JSONObject jsonResult = JerseyUtil.sendHttpFormRequest(url, params);
		
		List<LawLegalInstrument> judgmentInstrumentList = new ArrayList<LawLegalInstrument>();
		List<LawCourtAnnouncement> courtAnnouncementList = new ArrayList<LawCourtAnnouncement>();
		List<LawDishonestInfo> dishonestInfoList = new ArrayList<LawDishonestInfo>();
		List<LawExecutedPerson> executedPersonList = new ArrayList<LawExecutedPerson>();
		List<LawKaiTingGongGao> kaiTingGongGaoList = new ArrayList<LawKaiTingGongGao>();
		
		if(null!=jsonResult&&jsonResult.getString("respCode").equals("200") && jsonResult.getString("respMsg").equals("success")){
			if(jsonResult.get("data") != null && !jsonResult.get("data").equals("")){
				
				JSONArray array = null;
				
				try{
					 array = jsonResult.getJSONArray("data");
				}catch(JSONException e){
					logger.error("remote queryLawInfo error,JSONException ,result="+jsonResult.toString(), e);
				}
				
				if(array==null){
					return lawMessage;
				}
				
				JSONObject lawInfo = null;
				
				for(int i=0;i<array.size();i++){
					lawInfo = (JSONObject)array.get(i);
					
					if(lawInfo != null){
						if(lawInfo.containsKey("beizhixing:ah")&&StringUtils.isNotEmpty(lawInfo.getString("beizhixing:ah"))
								&&lawInfo.containsKey("beizhixing:mc")&&StringUtils.isNotEmpty(lawInfo.getString("beizhixing:mc"))){
							if(null!=lawInfo.getString("beizhixing:mc")&&companyName.equals(lawInfo.getString("beizhixing:mc"))){
								executedPersonList.add(resolveExecutedPerson(lawInfo));
							}
							
						}else if(lawInfo.containsKey("bltin:blt_content")&&StringUtils.isNotEmpty(lawInfo.getString("bltin:blt_content"))
								&&lawInfo.containsKey("bltin:blt_type")&&StringUtils.isNotEmpty(lawInfo.getString("bltin:blt_type"))){
							courtAnnouncementList.add(resolveCourtAnnouncement(lawInfo));
						}
						else if(lawInfo.containsKey("kai_ting_gong_gao:gong_si_ming_cheng")&&StringUtils.isNotEmpty(lawInfo.getString("kai_ting_gong_gao:gong_si_ming_cheng"))){
							kaiTingGongGaoList.add(resolveKaiTingGongGao(lawInfo));
						}
						else if(lawInfo.containsKey("judgidentifier:companyname")&&StringUtils.isNotEmpty(lawInfo.getString("judgidentifier:companyname"))
								&&lawInfo.containsKey("judgidentifier:instrumenttype")&&StringUtils.isNotEmpty(lawInfo.getString("judgidentifier:instrumenttype"))){
							LawLegalInstrument legalInstrument = resolveLegalInstrument(lawInfo);
							
							if(legalInstrument.getInstrumentType().equals("判决书")){
								judgmentInstrumentList.add(legalInstrument);
							}else{
								judgmentInstrumentList.add(legalInstrument);
							}
						}else if(null!=companyName&&lawInfo.containsKey("shixin:ah")&&StringUtils.isNotEmpty(lawInfo.getString("shixin:ah"))
								&&lawInfo.containsKey("shixin:zxfy")&&StringUtils.isNotEmpty(lawInfo.getString("shixin:zxfy"))){
							if(null!=companyName&&null!=lawInfo.getString("shixin:mc")&&companyName.equals(lawInfo.getString("shixin:mc"))){
									dishonestInfoList.add(resolveDishonestInfo(lawInfo));
							}
						}
					}
				}
			}
		}else{
			logger.error("respCode:" + jsonResult.getString("respCode") + ", respMsg:" + jsonResult.getString("respMsg"));
		}
		
		lawMessage.setCourtAnnouncementList(courtAnnouncementList);
		lawMessage.setDishonestInfoList(dishonestInfoList);
		lawMessage.setJudgmentInstrumentList(judgmentInstrumentList);
		lawMessage.setExecutedPersonList(executedPersonList);
		lawMessage.setKaiTingGongGaoList(kaiTingGongGaoList);
		sort(lawMessage);
		if(null!=lawMessage){
			redisTemplate.opsForValue().set(companyName+"_lawMessage", lawMessage, 2*60*60, TimeUnit.SECONDS);//2小时
		}
		return lawMessage;
	}
	//排序
	private void sort(LawAdaptor lawInfo){
		if(lawInfo.getJudgmentInstrumentList()!=null && lawInfo.getJudgmentInstrumentList().size()>0){
			LegalInstrumentComparator legalInstrumentComparator = new LegalInstrumentComparator();
			
			Collections.sort(lawInfo.getJudgmentInstrumentList(), legalInstrumentComparator);
		}
		
		if(lawInfo.getCourtAnnouncementList()!=null && lawInfo.getCourtAnnouncementList().size()>0){
			CourtAnnouncementComparator courtAnnouncementComparator = new CourtAnnouncementComparator();
			
			Collections.sort(lawInfo.getCourtAnnouncementList(), courtAnnouncementComparator);
		}
		
		if(lawInfo.getExecutedPersonList()!=null && lawInfo.getExecutedPersonList().size()>0){
			ExecutedPersonComparator executedPersonComparator = new ExecutedPersonComparator();
			
			Collections.sort(lawInfo.getExecutedPersonList(), executedPersonComparator);
		}
		
		if(lawInfo.getDishonestInfoList()!=null && lawInfo.getDishonestInfoList().size()>0){
			DishonestInfoComparator dishonestInfoComparator = new DishonestInfoComparator();
			
			Collections.sort(lawInfo.getDishonestInfoList(), dishonestInfoComparator);
		}
		
		if(lawInfo.getKaiTingGongGaoList()!=null && lawInfo.getKaiTingGongGaoList().size()>0){
			KaiTingGongGaoComparator kaiTingGongGaoComparator = new KaiTingGongGaoComparator();
			
			Collections.sort(lawInfo.getKaiTingGongGaoList(), kaiTingGongGaoComparator);
		}
	}
	@Override
	public List<LawLegalInstrument> queryJudgmentInstrumentList(String companyName, int currentPageNo, int pageSize) {
		
		LawAdaptor company =(LawAdaptor) redisTemplate.opsForValue().get(companyName+"_lawMessage");
		
		if(company==null){
			company  = queryLawInfo(companyName);
	    }
		
		List<LawLegalInstrument> judgmentInstrumentList = company.getJudgmentInstrumentList();
		List<LawLegalInstrument> resultList = new ArrayList<LawLegalInstrument>();
		
		if(judgmentInstrumentList.size() <= currentPageNo*pageSize){
			resultList = judgmentInstrumentList.subList((currentPageNo-1)*pageSize, judgmentInstrumentList.size());
		}else{
			resultList = judgmentInstrumentList.subList((currentPageNo-1)*pageSize, currentPageNo*pageSize);
		}
		
		return resultList;
	}

	@Override
	public int getLegalInstrumentNum(String companyName, String instrumentType) {
		int result = 0;

		String url = Global.getConfig("dataService") + Global.getConfig("lawInfo");
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("companyName", companyName);

		StringBuffer columns = new StringBuffer("judgidentifier:judgmentid,");
		
		columns.append("judgidentifier:instrumenttype=" + instrumentType);
		
		params.put("columns", columns.toString());
		
		JSONObject jsonResult = JerseyUtil.sendHttpFormRequest(url, params);
		
		if(jsonResult != null){
			if(jsonResult.getString("respCode").equals("200") && jsonResult.getString("respMsg").equals("success")){
				if(jsonResult.get("data") != null && !jsonResult.get("data").equals("")){
					JSONArray jsonArray = jsonResult.getJSONArray("data");
					
					result = jsonArray.size();
				}
			}else{
				logger.error("respCode:" + jsonResult.getString("respCode") + ", respMsg:" + jsonResult.getString("respMsg"));
			}
		}
		
		return result;
	}

	@Override
	public int getKaiTingGongGaoNum(String companyName) {
		int result = 0;
		
		String url = Global.getConfig("dataService") + Global.getConfig("lawInfo");
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("companyName", companyName);
		
		String columns = "kai_ting_gong_gao:fa_yuan_ming_cheng,kai_ting_gong_gao:gong_si_ming_cheng";
		
		params.put("columns", columns);
		
		JSONObject jsonResult = JerseyUtil.sendHttpFormRequest(url, params);
		
		if(jsonResult.getString("respCode").equals("200") && jsonResult.getString("respMsg").equals("success")){
			JSONArray jsonArray = jsonResult.getJSONArray("data");
			
			result = jsonArray.size();
		}else{
			logger.error("respCode:" + jsonResult.getString("respCode") + ", respMsg:" + jsonResult.getString("respMsg"));
		}
		
		return result;
	}

	@Override
	public LawAdaptor getCachedLawMsg(String companyName) {
		LawAdaptor lawInfo = (LawAdaptor) redisTemplate.opsForValue().get(companyName+"_lawMessage");
		
		if(lawInfo == null){
			lawInfo = this.queryLawInfo(companyName);
		}
		return lawInfo;
	}
}
