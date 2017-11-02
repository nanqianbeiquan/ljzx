package com.srd.ljzd.daoImpl.thirdparty.biz;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.srd.ljzd.config.Global;
import com.srd.ljzd.dao.thirdparty.biz.BizDAO;
import com.srd.ljzd.daoImpl.thirdparty.base.BaseDAOImpl;
import com.srd.ljzd.entity.biz.BizMsg;
import com.srd.ljzd.entityparser.BizJsonParserNew;
import com.srd.ljzd.util.DateUtils;
import com.srd.ljzd.util.JerseyUtil;
import com.srd.ljzd.util.LoggerUtils;
@Repository("bizDAO")
public class BizDAOImpl extends BaseDAOImpl implements BizDAO{
	public static String url = baseURL + Global.getConfig("bizInfo");
	public static String urlQiChaCha = baseURL + Global.getConfig("bizInfoWithQiChaCha");
	String brandUrl = Global.getConfig("dataService") + Global.getConfig("BRAND");
	@Autowired
	BizJsonParserNew bizJsonParserNew;
	@Override
	public BizMsg remoteGongShangMsg(String companyName) {
		
		Map<String, String> params = new HashMap<String, String>();  
		params.put("companyName", companyName);  
		params.put("columns", "");
		JSONObject result = super.remoteInvoke(url, params);
		if(result!=null){
			return bizJsonParserNew.parse(result);
		}
		return null;
	}

	@Override
	public BizMsg remoteGongShangMsgWithQiChaCha(String companyName) {
		
		Map<String, String> params = new HashMap<String, String>();  
		params.put("companyName", companyName);  
		JSONObject result = super.remoteInvoke(urlQiChaCha, params);
		if(result!=null&&result.containsKey("json")){
			return bizJsonParserNew.parse(result.get("json"));
		}
		return null;
	}
	@Override
	public BizMsg remoteGongShangMsgWithThirdParty(String companyName) {
		
		return null;
	}
	//获取商标列表
		@Override
		public JSONArray getBrand(String companyName) {
			
			
			Map<String, String> params = new HashMap<String, String>();  
			params.put("companyName", companyName);  
			params.put("cols", "");
			Calendar cal = Calendar.getInstance();
			params.put("stopTime", DateUtils.getLocalStr(DateUtils.formatPattern, cal.getTime()));
			cal.add(Calendar.YEAR, -1);
			params.put("beginTime", DateUtils.getLocalStr(DateUtils.formatPattern, cal.getTime()));
			
			JSONObject result = JerseyUtil.sendHttpFormRequest(brandUrl, params);
			
			if(result==null){
				return null;
			}
			if("200".equals(result.getString("respCode"))&&
					"success".equals(result.getString("respMsg"))){
				try{
					JSONArray array = result.getJSONArray("data");
					if(array!=null&&array.size()>0){
						return array;
					}
				}catch(JSONException e){
					LoggerUtils.error("remote getBrand error,JSONException ,result=" +result.toString(), e);
					
					return null;
				}
			}
			return null;
		}

}
