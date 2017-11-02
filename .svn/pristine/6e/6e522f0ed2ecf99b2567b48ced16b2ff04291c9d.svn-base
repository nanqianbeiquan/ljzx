package com.srd.ljzd.serviceImpl.base;

import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.srd.ljzd.util.JerseyUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

public abstract class RemoteBaseService  {
	protected static Logger log = LogManager.getLogger(RemoteBaseService.class.getName());
    public JSONArray remoteInvoke(String url,String columns,String identify,String companyName ){
    	
    	Map<String, String> params = new HashMap<String, String>();  
		params.put("companyName", companyName);  
		params.put("columns", columns);
		JSONObject result = JerseyUtil.sendHttpFormRequest(url, params);
		
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
				log.error("remote RemoteBaseService error,JSONException ,result="
					     +result.toString());
				return null;
			}
			
		}else{
			log.error("remote RemoteBaseService error,response error,params[respCode="
		     +result.getString("respCode")+",respMsg="+result.getString("respMsg")+"]");
		}
		return null;
    }
    
    public JSONObject remoteInvokeXML(String url,String columns,String identify,String companyName){
    	Map<String, String> params = new HashMap<String, String>();  
		params.put("companyName", companyName);  
		params.put("columns", columns);
		JSONObject result = JerseyUtil.sendHttpFormRequest(url, params);
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
				log.error("remote RemoteBaseService error ,result="
					     +result.toString());
				return null;
			}
		}else{
			log.error("remote RemoteBaseService error,response error,params[respCode="
		     +result.getString("respCode")+",respMsg="+result.getString("respMsg")+"]");
		}
    	return null;
    }
    public String remoteInvokeXML(String url,Map<String, String> params){
    	String result=null;
    	try{
    		 result = JerseyUtil.sendHttpFormRequestString(url, params);
    	}catch(Exception e){
    		log.error(e.getMessage());
    		return null;
    	}
		
		if(result.contains("\"respCode\":\"500\"")&&result.contains("\"respMsg\":\"error\"")){
			return null;
		}
		if(result.contains("\"source\":\"DB\"")&&result.contains("\"respCode\":\"200\"")){
			return result;
		}
		if(result.contains("\"source\":\"ZS\"")&&result.contains("\"respCode\":\"200\"")){
			int index=result.indexOf("\"data\":");
			if (index>-1) {
				return result.substring(index+7,result.length()-1);
			}
		}
    	return null;
    }
	public JSONObject remoteInvoke(String url,Map<String, String> params){
			
			JSONObject result = JerseyUtil.sendHttpFormRequest(url, params);
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
					log.error("remote RemoteBaseService error ,result="
						     +result.toString());
					return null;
				}
			}else{
				log.error("remote RemoteBaseService error,response error,params[respCode="
			     +result.getString("respCode")+",respMsg="+result.getString("respMsg")+"]");
			}
			return null;
	    }
	public JSONObject remoteInvoke(String url,String params){
		
		JSONObject result = JerseyUtil.sendHttpFormRequest(url, params);
		if(result==null){
			return null;
		}
		if("200".equals(result.getString("code"))){
			return result;
		}
		if("0".equals(result.getString("code"))){
			return result;
		}
		return result;
    }
}
