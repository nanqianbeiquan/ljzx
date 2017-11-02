package com.srd.ljzd.dao.thirdparty.common;

import java.util.Map;

import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.srd.ljzd.dao.thirdparty.base.BaseDAO;

public interface ThirdPartInterfaceDao  extends BaseDAO{
	
	public JSONObject invoke(String relativeURI,Map<String,String> params,MediaType mediaType);
	
	public JSONArray invokeReturnArray(String relativeURI,Map<String, String> params,MediaType mediaType);
	
	public String invokeReturnString(String relativeURI,Map<String, String> params,MediaType mediaType);
}
