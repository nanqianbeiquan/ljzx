package com.srd.ljzd.serviceImpl.index;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.srd.ljzd.service.index.NavMenuService;

@Service("navMenuService")
public class NavMenuServiceImpl implements NavMenuService{
	private static final String keySuffix = "_navData";

	@Autowired
	private HttpSession session;
	
	@Override
	public List<Map<String, Object>> updateNavPathData(String accountId,List<Map<String, Object>> data) {
		session.setAttribute(accountId+keySuffix, data);
		return data;
	}
	@Override
	public List<Map<String, Object>> getNavPathData(String accountId) {
		return (List<Map<String, Object>>)session.getAttribute(accountId+keySuffix);
	}

}
