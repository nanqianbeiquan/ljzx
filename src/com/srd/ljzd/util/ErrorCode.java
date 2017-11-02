package com.srd.ljzd.util;

public class ErrorCode {

	public static final String SUCCESS = "0000";//成功
	public static final  String ER_CELLPHONE_NOT_EXIST = "1000";//手机号未签约
	public static final String ER_COMPANY_NOT_AVAILABLE = "1001";//企业不可用,该企业未开通服务，或不在服务期，或已删除
	public static final String ER_ACCOUNT_NOT_AVAILABLE = "1002";//账户被禁用或删除
	public static final String ER_SEND_AUTHCODE_FAILED = "1003";//发送验证码失败
	public static final String ER_ACCOUNT_NUM_LIMIT = "1004";//账户数量已达到上限
	public static final String ER_AUTHCODE_MISMATCH = "1005";//验证码不匹配
	public static final String ER_AUTHCODE_OVERDUE = "1021";//验证码过期
	public static final String ER_CREATE_ACCOUNT_FAILED = "1006";//创建账户失败
	public static final String ER_NOTIFY_ACCOUNT_MSG_FAILED = "1007";//发送账户信息 短信失败
	public static final String ER_ACCOUNT_EXIST = "1008";//该机器已经注册过账户，不允许重复注册
	public static final String ER_PWD_MISMATCH = "1009";//密码错误
	public static final String ER_INIT_PWD = "1010";//初始密码登录
	public static final String ER_SQL_EXCEPTION = "9999";//操作数据库异常
	public static final String ER_MAC_AND_CPU_MISSING = "1011";//参数中没有硬件信息
	public static final String ER_CACHE_AUTHCODE_FAILED = "1012";//缓存验证码失败
	public static final String ER_NOT_LOGIN = "1013";//未登陆
	public static final String ER_ACCOUNT_NOT_EXIST = "1014";//账户不存在
	public static final String ER_MORE_THAN_ONE_ACCOUNT = "1015";//多个账户
	public static final String ER_ACCOUNT_NOT_IN_COMPANY = "1016";//该账户没有和任何公司关联
	
	public static final String ER_TIMEOUT = "1017";//超时,调用大数据接口超时
	public static final String ER_RESULT_NULL = "1018";//查询结果为空
	public static final String ER_ACCOUNT_BASE_MSG_NOT_EXIST = "1019";//账户的基本信息（手机号）未录入到本系统
	public static final String ER_CELLPHONE_NOT_MATCH = "1020";//手机号不匹配
	
	public static final String ER_NO_CHANG = "1022";//没有修改内容
	
	public static final String ER_CELLPHONE_ALEARDY_BIND = "1023";//手机号已经绑定过账号
	
	public static final String ER_ACCOUNT_NAME_EXIST = "1024";//账户名已经存在
	
	public static final String ER_UNKNOWN_EXCEPTION = "8888";//未知异常
	
	public static final String ER_ACCOUNT_OUT_OF_CONTRACT_DERUTION = "1025";//账号已经超过最近合同期；不在最近的有效合同期内；
	public static final String ER_NEED_AUTHCODE = "1026";//需要验证码
	
	public static final String ER_SESSION_OVERDUE = "1027";//登录会话过期
	
	public static String getErrorMesssage(String errorCode){
		String message = "未知错误";
		switch(errorCode){
		
			case ErrorCode.SUCCESS:
				message = "成功";
				break;
			case ErrorCode.ER_CELLPHONE_NOT_EXIST:
				message = "手机号未签约";
				break;
			case ErrorCode.ER_ACCOUNT_NOT_AVAILABLE:
				message = "账户已经过试用期、或被禁用、或删除、或未激活";
				break;
			case ErrorCode.ER_COMPANY_NOT_AVAILABLE:
			    message = "企业不可用,该企业被禁用，或不在服务期，或已删除";
			    break;
			case ErrorCode.ER_SEND_AUTHCODE_FAILED:
			    message = "发送验证码失败";
			    break;
			case ErrorCode.ER_ACCOUNT_NUM_LIMIT:
			    message = "账户数量已达到上限";
			    break;
			case ErrorCode.ER_AUTHCODE_MISMATCH:
			    message = "验证码不匹配";
			    break;
			case ErrorCode.ER_CREATE_ACCOUNT_FAILED:
			    message = "创建账户失败";
			    break;
			case ErrorCode.ER_NOTIFY_ACCOUNT_MSG_FAILED:
			    message = "发送账户信息 短信失败";
			    break;
			case ErrorCode.ER_ACCOUNT_EXIST:
			    message = "该机器已经激活绑定过账户，不允许重复绑定";
			    break;
			case ErrorCode.ER_PWD_MISMATCH:
			    message = "密码错误";
			    break;
			case ErrorCode.ER_INIT_PWD:
			    message = "初始密码登录";
			    break;
			case ErrorCode.ER_SQL_EXCEPTION:
			    message = "操作数据库异常";
			    break;
			case ErrorCode.ER_MAC_AND_CPU_MISSING:
			    message = "参数中没有硬件信息";
			    break;
			case ErrorCode.ER_CACHE_AUTHCODE_FAILED:
			    message = "缓存验证码失败";
			    break;
			case ErrorCode.ER_NOT_LOGIN:
			    message = "未登陆";
			    break;
			case ErrorCode.ER_ACCOUNT_NOT_EXIST:
			    message = "账户不存在";
			    break;
			case ErrorCode.ER_MORE_THAN_ONE_ACCOUNT:
			    message = "一台机器不允许对应多个账户";
			    break;
			case ErrorCode.ER_ACCOUNT_NOT_IN_COMPANY:
			    message = "该账户必须关联到某个公司下";
			    break;
			case ErrorCode.ER_TIMEOUT:
			    message = "调用大数据接口超时";
			    break;
			case ErrorCode.ER_RESULT_NULL:
			    message = "查询结果为空";
			    break;
			case ErrorCode.ER_ACCOUNT_BASE_MSG_NOT_EXIST:
			    message = "该手机号未录入到本系统中";
			    break;
			case ErrorCode.ER_CELLPHONE_NOT_MATCH:
			    message = "手机号不匹配";
			    break;
			case ErrorCode.ER_AUTHCODE_OVERDUE:
			    message = "验证码已过期";
			    break;
			    
			case ErrorCode.ER_UNKNOWN_EXCEPTION:
			    message = "未知异常";
			    break;
			    
			case ErrorCode.ER_NO_CHANG:
			    message = "无变化";
			    break;
			case ErrorCode.ER_CELLPHONE_ALEARDY_BIND:
			    message = "该手机号已经绑定过账户";
			    break;
			case ErrorCode.ER_ACCOUNT_NAME_EXIST:
			    message = "该账户名已经存在";
			    break;
			case ErrorCode.ER_ACCOUNT_OUT_OF_CONTRACT_DERUTION: 
				 message = "该账户所属公司最近合同已经逾期";
				    break;
			case ErrorCode.ER_NEED_AUTHCODE: 
				 message = "密码错误";
				    break;
			case ErrorCode.ER_SESSION_OVERDUE: 
				message = "登录会话过期";
				break;
		}
		
		return message;
	}
}
