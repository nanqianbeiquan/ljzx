package com.srd.ljzd.daoImpl.thirdparty.common;

import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.srd.ljzd.dao.thirdparty.common.ThirdPartInterfaceDao;
import com.srd.ljzd.daoImpl.thirdparty.base.BaseDAOImpl;
import com.srd.ljzd.util.JerseyUtil2;
@Repository("thirdPartInterfaceDao")
public class ThirdPartInterfaceDaoImpl  extends BaseDAOImpl implements ThirdPartInterfaceDao{

	@Override
	public JSONObject invoke(String relativeURI, Map<String, String> params,MediaType mediaType) {
		return JerseyUtil2.sendHttpFormRequest(super.baseURL+relativeURI, params, mediaType);
	}

	@Override
	public JSONArray invokeReturnArray(String relativeURI, Map<String, String> params,MediaType mediaType) {
		return JerseyUtil2.sendHttpFormRequestReturnArray(super.baseURL+relativeURI, params, mediaType);
	}

	@Override
	public String invokeReturnString(String relativeURI,
			Map<String, String> params,MediaType mediaType) {
		return JerseyUtil2.sendHttpFormRequestString(super.baseURL+relativeURI, params, mediaType);
	}
}
