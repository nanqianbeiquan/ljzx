package com.srd.ljzd.exception;
/**
 * 登录注册模块异常类
 * @author weitao.liu
 *
 */
public class AuthException extends  BusinessException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuthException(String errorCode, String description,
			Exception parent, Class<? extends Object> source) {
		super(errorCode,description,parent,source);
	}
}
