package com.srd.ljzd.serviceImpl.monitor;

import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.srd.ljzd.dao.thirdparty.common.ThirdPartInterfaceDao;
import com.srd.ljzd.service.monitor.ThirdPartInterfaceService;

@Service("thirdPartInterfaceService")
public class ThirdPartInterfaceServiceImpl implements ThirdPartInterfaceService{
	@Autowired
	ThirdPartInterfaceDao thirdPartInterfaceDao;
	@Override
	public JSONObject invoke(String relativeURI, Map<String, String> params) {
		
		return this.invoke(relativeURI, params, MediaType.APPLICATION_FORM_URLENCODED_TYPE);
	}
	@Override
	public JSONArray invokeReturnArray(String relativeURI,
			Map<String, String> params) {
		return this.invokeReturnArray(relativeURI, params, MediaType.APPLICATION_FORM_URLENCODED_TYPE);
	}
	@Override
	public String invokeReturnString(String relativeURI,
			Map<String, String> params) {
		return this.invokeReturnString(relativeURI, params, MediaType.APPLICATION_FORM_URLENCODED_TYPE);
	}
	@Override
	public JSONObject invoke(String relativeURI, Map<String, String> params,
			MediaType mediaType) {
		return thirdPartInterfaceDao.invoke(relativeURI, params,mediaType);
	}
	@Override
	public JSONArray invokeReturnArray(String relativeURI,
			Map<String, String> params, MediaType mediaType) {
		return thirdPartInterfaceDao.invokeReturnArray(relativeURI, params,mediaType);
	}
	@Override
	public String invokeReturnString(String relativeURI,
			Map<String, String> params, MediaType mediaType) {
		return thirdPartInterfaceDao.invokeReturnString(relativeURI, params,mediaType);
	}
}
