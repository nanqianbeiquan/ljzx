package com.srd.ljzd.serviceImpl.relationMap;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.srd.ljzd.dao.thirdparty.relationMap.RelationMapDao;
import com.srd.ljzd.service.relationMap.RelationMapService;
@Service("relationMapService")
public class RelationMapServiceImpl implements RelationMapService {
	@Autowired
	RelationMapDao relationMapDAO;
	@Override
	public JSONObject invoke(String relativeURI, Map<String, String> params) {
		
		return relationMapDAO.invoke(relativeURI, params);
	}
	@Override
	public JSONArray invokeReturnArray(String relativeURI,
			Map<String, String> params) {
		return relationMapDAO.invokeReturnArray(relativeURI, params);
	}
	@Override
	public String invokeReturnString(String relativeURI,
			Map<String, String> params) {
		return relationMapDAO.invokeReturnString(relativeURI, params);
	}

}
