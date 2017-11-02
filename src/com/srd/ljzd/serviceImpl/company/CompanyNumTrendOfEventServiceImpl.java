package com.srd.ljzd.serviceImpl.company;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.srd.ljzd.config.Global;
import com.srd.ljzd.service.company.CompanyNumTrendOfEventService;
import com.srd.ljzd.util.JerseyUtil;

@Service("companyNumService")
public class CompanyNumTrendOfEventServiceImpl implements CompanyNumTrendOfEventService{
	
	protected static Logger logger = LogManager.getLogger(CompanyNumTrendOfEventServiceImpl.class.getName());
	
	private String dataService = Global.getConfig("dataService");
	private String companyNum = Global.getConfig("companyNum");
	
	@Override
	public Map<String, String> queryCompanyNum(String companyName) {
		String url = dataService+companyNum;
		Map<String, String> params = new HashMap<String, String>();
		params.put("companyName", companyName);
		
		JSONObject jsonResult = JerseyUtil.sendHttpFormRequest(url, params);
		
		HashMap<String, String> comNum = new HashMap<String, String>();
		if(jsonResult != null){
			if(jsonResult.getString("respCode").equals("200") && jsonResult.getString("respMsg").equals("success")){
				
				if(jsonResult.get("data") != null && !jsonResult.get("data").equals("")){
					JSONObject companyNumData= jsonResult.getJSONObject("data");
					
					if(null!=companyNumData.getString("GS")){
						JSONObject bizNum=companyNumData.getJSONObject("GS");
						//注册信息
						if(bizNum.containsKey("Registered_Info")){
							comNum.put("Registered_Info", bizNum.getString("Registered_Info"));
						}
						//股东信息
						if(bizNum.containsKey("Shareholder_Info")){
							comNum.put("Shareholder_Info", bizNum.getString("Shareholder_Info"));
						}
						//主要人员信息
						if(bizNum.containsKey("KeyPerson_Info")){
							comNum.put("KeyPerson_Info", bizNum.getString("KeyPerson_Info"));
						}
						//公司对外投资
						if(bizNum.containsKey("ent_Investor_Info")){
							comNum.put("ent_Investor_Info", bizNum.getString("ent_Investor_Info"));
						}
						//投资人信息
						if(bizNum.containsKey("Investor_Info")){
							comNum.put("Investor_Info", bizNum.getString("Investor_Info"));
						}
						//合伙人信息
						if(bizNum.containsKey("Partner_Info")){
							comNum.put("Partner_Info", bizNum.getString("Partner_Info"));
						}
						//变更信息
						if(bizNum.containsKey("Changed_Announcement")){
							comNum.put("Changed_Announcement", bizNum.getString("Changed_Announcement"));
						}
						//家庭成员信息
						if(bizNum.containsKey("Family_Info")){
							comNum.put("Family_Info", bizNum.getString("Family_Info"));
						}
						//分支机构信息
						if(bizNum.containsKey("Branches")){
							comNum.put("Branches", bizNum.getString("Branches"));
						}
						//清算信息
						if(bizNum.containsKey("liquidation_Information")){
							comNum.put("liquidation_Information", bizNum.getString("liquidation_Information"));
						}
						//动产抵押信息
						if(bizNum.containsKey("Chattel_Mortgage")){
							comNum.put("Chattel_Mortgage", bizNum.getString("Chattel_Mortgage"));
						}
						//股权出质登记
						if(bizNum.containsKey("Equity_Pledge")){
							comNum.put("Equity_Pledge", bizNum.getString("Equity_Pledge"));
						}
						//行政处罚信息
						if(bizNum.containsKey("Administrative_Penalty")){
							comNum.put("Administrative_Penalty", bizNum.getString("Administrative_Penalty"));
						}
						//经营异常信息
						if(bizNum.containsKey("Business_Abnormal")){
							comNum.put("Business_Abnormal", bizNum.getString("Business_Abnormal"));
						}
						//严重违法信息
						if(bizNum.containsKey("Serious_Violations")){
							comNum.put("Serious_Violations", bizNum.getString("Serious_Violations"));
						}
						//抽查检查信息	
						if(bizNum.containsKey("Spot_Check")){
							comNum.put("Spot_Check", bizNum.getString("Spot_Check"));
						}
						//股权冻结历史信息
						if(bizNum.containsKey("sharesfrost")){
							comNum.put("sharesfrost", bizNum.getString("sharesfrost"));
						}
						//控股股东信息
						if(bizNum.containsKey("finalshareholder")){
							comNum.put("finalshareholder", bizNum.getString("finalshareholder"));
						}
						//企业法定代表人信息
						if(bizNum.containsKey("ryposfr")){
							comNum.put("ryposfr", bizNum.getString("ryposfr"));
						}
						//企业股东信息
						if(bizNum.containsKey("rypossha")){
							comNum.put("rypossha", bizNum.getString("rypossha"));
						}
						//企业主要人员信息
						if(bizNum.containsKey("ryposper")){
							comNum.put("ryposper", bizNum.getString("ryposper"));
						}
					}
					
					if(null!=companyNumData.getString("LOGO")){
						JSONObject LOGONum=companyNumData.getJSONObject("LOGO");
						//商标信息
						if(LOGONum.containsKey("LOGO")){
							comNum.put("LOGO", LOGONum.getString("LOGO"));
						}
					}else{
						comNum.put("LOGO", "0");
					}
					
					if(null!=companyNumData.getString("SF")){
						JSONObject lawNum=companyNumData.getJSONObject("SF");
						if(lawNum.containsKey("pjs")){
							comNum.put("pjs", lawNum.getString("pjs"));
						}
						if(lawNum.containsKey("qt")){
							comNum.put("qt", lawNum.getString("qt"));
						}
						if(lawNum.containsKey("beizhixing")){
							comNum.put("beizhixing", lawNum.getString("beizhixing"));
						}
						if(lawNum.containsKey("bltin")){
							comNum.put("bltin", lawNum.getString("bltin"));
						}
						if(lawNum.containsKey("shixin")){
							comNum.put("shixin", lawNum.getString("shixin"));
						}
						//开庭公告
						if(lawNum.containsKey("kai_ting_gong_gao")){
							comNum.put("kai_ting_gong_gao", lawNum.getString("kai_ting_gong_gao"));
						}
					}
					
					if(null!=companyNumData.getString("newYQ")){
						JSONObject newsYQNum=companyNumData.getJSONObject("newYQ");
						if(newsYQNum.containsKey("risk")){
							comNum.put("risk", newsYQNum.getString("risk"));
						}
						if(newsYQNum.containsKey("norisk")){
							comNum.put("norisk", newsYQNum.getString("norisk"));
						}
					}
				}
			}else{
				logger.error("respCode:" + jsonResult.getString("respCode") + ", respMsg:" + jsonResult.getString("respMsg"));
			}
		}
		return comNum;
	}

}
