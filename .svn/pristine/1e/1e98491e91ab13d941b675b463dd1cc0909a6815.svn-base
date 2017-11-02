package com.srd.ljzd.entityparser;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.srd.ljzd.entity.biz.BizMsg;
import com.srd.ljzd.entityparser.base.BizParser;
import com.srd.ljzd.util.StringTool;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Component("bizJsonParserNew")
public class BizJsonParserNew extends BizParser{

	protected static Logger log = LogManager.getLogger(BizJsonParserNew.class.getName());
	
	@Override
	public BizMsg parse(Object data){
		
		if(data==null){
			return null;
		}
		JSONObject sourceCompany = (JSONObject)data;
			
		BizMsg company = new BizMsg();

		if(sourceCompany!=null){
			if(sourceCompany.containsKey("Changed_Announcement")
					&&sourceCompany.get("Changed_Announcement")!=null){
				//变更记录
				company.setBianGengJiLuArray(adaptor(sourceCompany, "Changed_Announcement"));
			}
			
			if(sourceCompany.containsKey("Registered_Info")
					&&sourceCompany.get("Registered_Info")!=null){
				//公司基本信息
				ArrayList<HashMap<String,String>> brefCompanyArray = adaptor(sourceCompany, "Registered_Info");
				if(brefCompanyArray.size()==1){
					company.setBrefCompany(brefCompanyArray.get(0));
				}else if(brefCompanyArray.size()>1){
					String companyName = null;
					if(brefCompanyArray.get(0)!=null&&brefCompanyArray.get(0).containsKey("enterprisename")){
						companyName = brefCompanyArray.get(0).get("enterprisename");
					}
					log.error("remote getBrefCompany error,too many result,params[name="+companyName+"]");
					company.setBrefCompany(brefCompanyArray.get(0));
				}
			}
			
			if(sourceCompany.containsKey("Administrative_Penalty")
					&&sourceCompany.get("Administrative_Penalty")!=null){
				//行政处罚信息
				company.setAdministrativePenaltyArray(adaptor(sourceCompany, "Administrative_Penalty"));
			}
			
			if(sourceCompany.containsKey("fr_Investor_Info")
					&&sourceCompany.get("fr_Investor_Info")!=null){
				//企业法人对外投资
				company.setfRInvArray(adaptor(sourceCompany, "fr_Investor_Info"));
				
			}
			
			if(sourceCompany.containsKey("ent_Investor_Info")
					&&sourceCompany.get("ent_Investor_Info")!=null){
				//企业对外投资
				company.setEntInvArray(adaptor(sourceCompany, "ent_Investor_Info"));
			}
			
			if(sourceCompany.containsKey("KeyPerson_Info")
					&&sourceCompany.get("KeyPerson_Info")!=null){
				//主要人员
				company.setKeyPersonArray(adaptor(sourceCompany, "KeyPerson_Info"));
			}
			
			if(sourceCompany.containsKey("fr_Keyperson_Info")
					&&sourceCompany.get("fr_Keyperson_Info")!=null){
				//法人在外任职信息
				company.setfRPositionArray(adaptor(sourceCompany, "fr_Keyperson_Info"));
			}
			
			if(sourceCompany.containsKey("Shareholder_Info")
					&&sourceCompany.get("Shareholder_Info")!=null){
				//股东
				company.setShareholderArray(adaptor(sourceCompany, "Shareholder_Info"));
			}
			
			
			if(sourceCompany.containsKey("Branches")
					&&sourceCompany.get("Branches")!=null){
				//分支机构
				company.setFiLiationArray(adaptor(sourceCompany, "Branches"));
			}
			
			if(sourceCompany.containsKey("Business_Abnormal")
					&&sourceCompany.get("Business_Abnormal")!=null){
				//经营异常
				company.setJingYingYiChangArray(adaptor(sourceCompany, "Business_Abnormal"));
			}
			
			if(sourceCompany.containsKey("Chattel_Mortgage")
					&&sourceCompany.get("Chattel_Mortgage")!=null){
				//动产抵押
				company.setDongChanDiYaArray(adaptor(sourceCompany, "Chattel_Mortgage"));
			}
			
			if(sourceCompany.containsKey("Equity_Pledge")
					&&sourceCompany.get("Equity_Pledge")!=null){
				//股权出质
				company.setShareSIMPAWNArray(adaptor(sourceCompany, "Equity_Pledge"));
			}
			
		}
	
		return company;
	}
	
	public ArrayList<HashMap<String,String>> adaptor(JSONObject sourceCompany,String moduleName){
		ArrayList<HashMap<String,String>> targetArray = new ArrayList<HashMap<String,String>>();
		
		JSONArray array = (JSONArray)sourceCompany.get(moduleName);
		HashMap<String,String> targetItem = null;
		JSONObject sourceItem = null;
		String value = null;
		if(array!=null&&array.size()>0){
			for(Object obj : array){
				sourceItem = (JSONObject)obj;
				targetItem = new HashMap<String,String>();
				if(sourceItem==null){
					continue;
				}
				for(Object key : sourceItem.keySet()){
					if(key!=null){
						value = (sourceItem.get(key)==null?"":sourceItem.get(key)).toString().trim();
						
						if("null".equals(value)||"\\N".equals(value)){
							value = "";
						}
						
						try {
							value =	StringTool.formartForPdf(value);
						} catch (Exception e) {
							log.error("StringTool.formartForPdf 解析替换多余字符：error===="+e);
						}
						targetItem.put(((String)key).replace(moduleName+":", ""), value);
					}
				}
				if("Administrative_Penalty".equals(moduleName)&&!targetItem.containsKey("penalty_decisiondate")){
					targetItem.put("penalty_decisiondate", "");
				}
				targetArray.add(targetItem);
			}
		}
		return targetArray;
	}
	
}

 
