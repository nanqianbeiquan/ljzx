package com.srd.ljzd.daoImpl.thirdparty.base;

import java.util.Map;

import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.srd.ljzd.config.Global;
import com.srd.ljzd.dao.thirdparty.base.BaseDAO;
import com.srd.ljzd.util.JerseyUtil2;
import com.srd.ljzd.util.LoggerUtils;

public class BaseDAOImpl implements BaseDAO{
	
	public static String baseURL = Global.getConfig("dataService");
	@Override
    public JSONObject remoteInvoke(String url,Map<String, String> params){
		JSONObject result = JerseyUtil2.sendHttpFormRequest(url, params,MediaType.APPLICATION_FORM_URLENCODED_TYPE);
		
		if(result==null){
			return null;
		}
		
		if("200".equals(result.getString("respCode")) && "success".equals(result.getString("respMsg"))){
			try{
				JSONObject data = result.getJSONObject("data");
				if(data!=null){
					return data;
				}
			}catch(JSONException e){
				LoggerUtils.error(url + "参数：" +params + "remote RemoteBaseService error ,result=" + result.toString());
				return null;
			}
		}else{
			LoggerUtils.warn(url + "参数：" +params + "remote RemoteBaseService error,response error,params[respCode="
		     +result.getString("respCode")+",respMsg="+result.getString("respMsg")+"]");
		}
		
    	return null;
    }
	@Override
    public String remoteInvokeWithString(String url,Map<String, String> params){
		JSONObject result = JerseyUtil2.sendHttpFormRequest(url, params,MediaType.APPLICATION_FORM_URLENCODED_TYPE);
		
		if(result==null){
			return null;
		}
		
		if("200".equals(result.getString("respCode"))&&
				"success".equals(result.getString("respMsg"))){
			try{
				String data = result.getString("data");
				if(data!=null){
					return data;
				}
			}catch(JSONException e){
				LoggerUtils.error(url + "参数：" +params + "remote RemoteBaseService error ,result=" + result.toString());
				return null;
			}
		}else{
			LoggerUtils.warn(url + "参数：" +params + "remote RemoteBaseService error,response error,params[respCode="
		     +result.getString("respCode")+",respMsg="+result.getString("respMsg")+"]");
		}
		
    	return null;
    }
	@Override
    public JSONObject remoteInvoke(String url,Map<String, String> params,MediaType mediaType){
		JSONObject result = JerseyUtil2.sendHttpFormRequest(url, params,mediaType);
		
		if(result==null){
			return null;
		}
		
		if("200".equals(result.getString("respCode"))&&
				"success".equals(result.getString("respMsg"))){
			try{
				JSONObject data = result.getJSONObject("data");
				if(data!=null){
					return data;
				}
			}catch(JSONException e){
				LoggerUtils.error(url + "参数：" +params + "remote RemoteBaseService error ,result=" + result.toString());
				return null;
			}
		}else{
			LoggerUtils.warn(url + "参数：" +params + "remote RemoteBaseService error,response error,params[respCode="
		     +result.getString("respCode")+",respMsg="+result.getString("respMsg")+"]");
		}
		
    	return null;
    }
}
