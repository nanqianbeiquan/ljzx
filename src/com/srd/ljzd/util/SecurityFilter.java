/**  
 * 文件名: SecurityFilter.java
 * 包    名: com.srd.ljzd.util
 * 描    述: TODO(用一句话描述该文件做什么)
 * 作    者： 谭显伦 
 * 日    期： 2016年4月5日
 * 版    本： V1.0  
 */

package com.srd.ljzd.util;

/**
 * 类名: SecurityFilter
 * 描述: 系统登录过滤器
 * 作者： 谭显伦
 * 日期： 2016年4月5日
 */

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.srd.ljzd.entity.client.ClientAccount;
import com.srd.ljzd.service.login.LoginService;

public class SecurityFilter implements Filter {

	protected static Logger log = LogManager.getLogger(SecurityFilter.class.getName());
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		String url = req.getRequestURL().toString();
	
        //登陆页面无需过滤、提供账户访问接口给外部调用无需过滤
        if(url.indexOf("doLogin") > -1 || url.indexOf("auth") > -1 || url.indexOf("login") > -1 || url.indexOf("logout") > -1 || url.indexOf("userApi") > -1) {
            chain.doFilter(req, res);
            return;
        }
        
        //访问的是js、css文件和图片直接跳转
        if(-1<url.indexOf("css")||-1<url.indexOf("images")||-1!=url.indexOf(".js")){
			chain.doFilter(request, response);
			return;
    	}
        
		//判断如果没有取到账户信息，就跳转到登录页
		if (null == session.getAttribute("account")) {
        	// 跳转到登陆页面
        	res.sendRedirect("/ljzx/logout");
		} else {
        	ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
        	LoginService loginService = ctx.getBean(LoginService.class);
        	 
        	ClientAccount account = (ClientAccount)session.getAttribute("account");
        	
        	String sessionId = loginService.getAccountSessionId(account.getAccountId());
        	
    		if(StringUtils.isNotEmpty(sessionId)){
        		//判断当前的sessionid和用户登录时的sessionid是否一致，如果不一致，则重新登录
        		if(sessionId.equals(session.getId())){
		            // 已经登陆,继续此次请求
        			chain.doFilter(request, response);
        		}else{
        			// 跳转到登陆页面
        			res.sendRedirect("/ljzx/logout");
        		}
    		}else{
        		// 跳转到登陆页面
        		res.sendRedirect("/ljzx/logout");
    		}
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void destroy() {
	}
	
}