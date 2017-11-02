package com.srd.ljzd.service.monitor;

import java.util.Map;

import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface ThirdPartInterfaceService {
	public JSONObject invoke(String relativeURI,Map<String,String> params);
	
	public JSONArray invokeReturnArray(String relativeURI,Map<String,String> params);
	
	public String invokeReturnString(String relativeURI,Map<String,String> params);
	
	public JSONObject invoke(String relativeURI,Map<String,String> params,MediaType mediaType);
	
	public JSONArray invokeReturnArray(String relativeURI,Map<String,String> params,MediaType mediaType);
	
	public String invokeReturnString(String relativeURI,Map<String,String> params,MediaType mediaType);
}
