package com.srd.ljzd.dao.thirdparty.relationMap;

import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.srd.ljzd.dao.thirdparty.base.BaseDAO;

public interface RelationMapDao extends BaseDAO{

	public JSONObject invoke(String relativeURI,Map<String,String> params);
	
	public JSONArray invokeReturnArray(String relativeURI,Map<String, String> params);
	
	public String invokeReturnString(String relativeURI,Map<String, String> params);
}
