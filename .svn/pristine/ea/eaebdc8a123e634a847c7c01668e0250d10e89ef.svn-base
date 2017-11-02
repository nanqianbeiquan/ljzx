package com.srd.ljzd.serviceImpl.monitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.srd.ljzd.config.Global;
import com.srd.ljzd.entity.monitor.RiskResult;
import com.srd.ljzd.service.monitor.RiskInfoService;
import com.srd.ljzd.util.JerseyUtil;
import com.srd.ljzd.util.StringTool;

@Service("riskInfoService")
@CacheConfig
@Transactional
public class RiskInfoServiceImpl implements RiskInfoService {

	protected static Logger log = LogManager.getLogger(RiskInfoServiceImpl.class.getName());
	@Override
	public List<RiskResult> getCompanyRiskInfo(Map paraMap) {
		String glableUrl = Global.getConfig("dataService") + Global.getConfig("riskInfo");
		List <RiskResult> list = new ArrayList<RiskResult>();
		JSONObject jsonResult = JerseyUtil.sendHttpFormRequest(glableUrl, paraMap);
		
		if(jsonResult!=null){
			if(jsonResult.getString("respCode").equals("200") && jsonResult.getString("respMsg").equals("success")){
				try {
					
					if(jsonResult.get("data")!=null&&!jsonResult.get("data").equals("")){
						list = converFromJsonToEntity(jsonResult.getJSONArray("data"));
					};
					
					
				} catch (Exception e) {
					log.error("e===="+e);
				}
				
			}else{
				   log.error("respCode:" + jsonResult.getString("respCode") + ", respMsg:" + jsonResult.getString("respMsg"));
			}
		}
		return list;
	}
	
	

public List<RiskResult> converFromJsonToEntity(JSONArray array){
	List<RiskResult> list = new ArrayList<RiskResult>();
	if(array!=null){
		for(int i=0;i<array.size();i++){
	
			try {
					RiskResult  riskResult = new RiskResult();
					if(array.getJSONObject(i).containsKey("companyName")){
						riskResult.setCompanyName(StringTool.ifNullToBlack(array.getJSONObject(i).getString("companyName")));
					}
					
					if(array.getJSONObject(i).containsKey("riskvaluefixed")){
						riskResult.setRiskValuefixed(StringTool.ifNullToBlack(array.getJSONObject(i).getString("riskvaluefixed")));
					}
					
					if(array.getJSONObject(i).containsKey("riskratefixed")){
						riskResult.setRiskRatefixed(StringTool.ifNullToBlack(array.getJSONObject(i).getString("riskratefixed")));
					}
					
					if(array.getJSONObject(i).containsKey("date")){
						riskResult.setDateStr(StringTool.ifNullToBlack(array.getJSONObject(i).getString("date")));
					}
					
					if(array.getJSONObject(i).containsKey("colsvalues")){
						riskResult.setColsValues(StringTool.ifNullToBlack(array.getJSONObject(i).getString("colsvalues")));
						if(StringTool.isNotNullAndBlack(riskResult.getColsValues())){
							try {
								setRiskDetailInfo(riskResult);
							} catch (Exception e) {
								log.error("error ==="+e);
							}
							
						}
					}
					
					
					if(riskResult!=null){
						list.add(riskResult);
					}
				
			} catch (Exception e) {
				log.error("converFromJsonToEntity error ==="+e);
			}
		}
	}
	return list;
}



/**
* @Title: setRiskDetailInfo
* @Description: TODO(设置工商，司法，舆情，企业族谱等风险值)
* @param @param riskResult    设定文件
* @return void    返回类型
* @throws
  @date 2016年6月7日 下午4:37:22
*/
private void setRiskDetailInfo(RiskResult riskResult){
	String str = riskResult.getColsValues();
	
	String [] riskInfo = str.split(",");
	if(riskInfo.length>0){
		splitRiskInfoByType(riskResult, riskInfo,"工商");
		splitRiskInfoByType(riskResult, riskInfo,"司法");
		splitRiskInfoByType(riskResult, riskInfo,"负面舆情");
		
	}
	
}



/**
* @Title: splitRiskInfoByType
* @Description: TODO(分别截取工商，司法等信息设置风险情况)
* @param @param riskResult
* @param @param riskInfo
* @param @param infoType    设定文件
* @return void    返回类型
* @throws
  @date 2016年6月7日 下午4:55:44
*/
private void splitRiskInfoByType(RiskResult riskResult, String[] riskInfo,String infoType) {
	for(int i=0;i<riskInfo.length;i++){
		if(riskInfo[i].toString().indexOf(infoType)>-1){
			String [] bizriskInfo = riskInfo[i].split(":");
			if(bizriskInfo.length==2){
				int risk = Integer.parseInt(bizriskInfo[1]);
				if(risk>0){
					riskResult.setBizInfoRiskFlag("1");
				}else{
					riskResult.setBizInfoRiskFlag("0");
				}
				
			}
			
		}
	}
}

}
