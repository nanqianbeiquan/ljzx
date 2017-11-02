package com.srd.ljzd.service.index;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface NavMenuService {

	public List<Map<String, Object>> updateNavPathData(String accountId,List<Map<String, Object>> data);
	
	public List<Map<String, Object>> getNavPathData(String accountId);
}
