/**   
* @Title: LoginServiceImpl.java 
* @Package com.srd.ljzd.serviceImpl.login 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年11月27日 下午12:19:11 
* @version V1.0   
*/
package com.srd.ljzd.serviceImpl.login;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.srd.ljzd.dao.client.ClientAccountDao;
import com.srd.ljzd.entity.client.ClientAccount;
import com.srd.ljzd.entity.client.ClientCompany;
import com.srd.ljzd.entity.common.ResultBean;
import com.srd.ljzd.service.client.ClientAccountService;
import com.srd.ljzd.service.log.OperationLogService;
import com.srd.ljzd.service.login.LoginService;
import com.srd.ljzd.service.sys.AuthService;
import com.srd.ljzd.util.AuthCodeGenerator;
import com.srd.ljzd.util.DateUtils;
import com.srd.ljzd.util.MD5Util;
import com.srd.ljzd.util.MessageUtils;
import com.srd.ljzd.util.StringUtils;

/** 
 * @ClassName: LoginServiceImpl
 * @Description: 登录Service实现类
 * @author shiyong
 * @date 2016年11月27日 下午12:19:11
 *  
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Autowired
	private HttpSession httpSession;
	
	protected static Logger logger = LogManager.getLogger(LoginServiceImpl.class.getName());
	
	@Autowired
	private ClientAccountService clientAccountService;
	
	@Autowired
	private ClientAccountDao clientAccountDao;
	@Autowired
	private OperationLogService operationLogService;
	
	@Override
	public ResultBean doLogin(String accountName, String pwd) {
		ResultBean result = new ResultBean();
		
		String md5Pwd = MD5Util.GetMD5Code(pwd);
		
		List<ClientAccount> accountList = clientAccountDao.getListByMultiAttr(accountName);
		
		if(accountList==null||accountList.size()==0){
			result.setResultCode("1");
			result.setResultMsg("账户不存在！");
			return result;
		}
		
		if(accountList.size()>1){
			result.setResultCode("1");
			result.setResultMsg("存在多个重名账户！");
			return result;
		}
		
		ClientAccount account = accountList.get(0);
		
	    //验证重复密码错误次数
		boolean isForbidden = authService.isInForbidden(account.getAccountId());
		
		if(isForbidden){
			result.setResultCode("1");
			result.setResultMsg("连续密码错误,请10分钟后再尝试登陆！");
			return result;
		}
		
		//验证密码
		if(StringUtils.isNotEmpty(md5Pwd)&&md5Pwd.equals(account.getPassword())){
			authService.clearMisPwdCount(account.getAccountId());
		}else{
			//记录密码错误次数
			int count = authService.updateMisPwdCount(account.getAccountId());
			if(count>=10){
				authService.addToForbidden(account.getAccountId());
				authService.clearMisPwdCount(account.getAccountId());
			}
			result.setResultCode("1");
			result.setResultMsg("密码错误！");
			return result;
		}
		
		//验证账户状态
		ClientCompany clientCompany = account.getClientCompany();
		
		if(clientCompany==null || StringUtils.isEmpty(clientCompany.getStatus())){
			result.setResultCode("1");
			result.setResultMsg("账号异常，请联系工作人员！");
			return result;
		}
		
		if(!"1".equals(clientCompany.getStatus().trim())){
			result.setResultCode("1");
			switch(clientCompany.getStatus().trim()){
			  case "0":
				 result.setResultMsg("该账户所属企业未开通！");
				break;
			  case "2":
				 result.setResultMsg("该账户所属企业已过期！");	
				break;
			  case "3":
				 result.setResultMsg("账号异常，请联系工作人员！");	
				break;
			}
			return result;
		}
		
		if(StringUtils.isEmpty(account.getStatus())){
			result.setResultCode("1");
			result.setResultMsg("该账户状态信息异常！");
			return result;
		}
		
		if(!"0".equals(account.getStatus().trim())){
			result.setResultCode("1");
			result.setResultMsg("该账户已被禁用！");
			return result;
		}
		
		//获取账号信息及权限保存到session中
		account = clientAccountService.getClientAccountById(account.getAccountId());
		
		//将账户信息写入session
		httpSession.setAttribute("account", account);
		
		//保存账号和session关联
		saveAccountSessionId(account.getAccountId(), httpSession.getId());
		
		//如果是附属账号，则查看权限同母账号
		if("3".equals(account.getType())){
			account = clientAccountService.getParentAccount(account.getAccountId());
		}
		
		httpSession.setAttribute("masterAccount", account);
		
		//获取下级子账号列表，保存到session中
		List<ClientAccount> childAccountList = clientAccountService.getChildAccountListByParentId(account.getAccountId());
		
		clientAccountService.updateChildAccountParamListInSession(childAccountList);
		
		//保存用户登录记录
        operationLogService.save(account.getAccountId(),account.getAccountName()
        		,"登录","登录",DateUtils.getCurrentDateStr(DateUtils.DEFAULT_PATTERN));
		
        result.setResultCode("0");
        
        //判断账号是否拥有子账号，跳转到不同的首页
        Map<String, String> resultMap = new HashMap<String, String>();
        
        if(childAccountList.size() > 0){
        	resultMap.put("url", "/toSummaryIndex");
        }else{
        	resultMap.put("url", "/index");
        }
        
        result.setResultData(resultMap);
		
		return result;
	}
	
	@Override
	public void saveAccountSessionId(String accountId, String sessionId) {
		//删除已有记录
		redisTemplate.delete(accountId+"_sessionId");
		
		//保存新的登录session
		redisTemplate.opsForValue().set(accountId+"_sessionId", sessionId, 2, TimeUnit.HOURS);//缓存2个小时
	}
	
	@Override
	public String getAccountSessionId(String accountId) {
		
		String sessionId = (String)redisTemplate.opsForValue().get(accountId+"_sessionId");
		
		return sessionId;
	}
	
	@Override
	public void deleteAccountSessionId(String accountId){
		//删除已有记录
		redisTemplate.delete(accountId+"_sessionId");
	}

	@Override
	public ResultBean sendAuthCode(String receiver) {
        ResultBean result = new ResultBean();
		
        List<ClientAccount> accountList =  clientAccountDao.getListByMultiAttr(receiver);
        if(accountList!=null&&accountList.size()>1){
        	result.setResultCode("1");
			result.setResultMsg("发送失败，手机号或邮箱绑定了多个账户！");
			return result;
        }
        
        String type = "";
        
        ClientAccount account = clientAccountDao.getAccountByMobilePhone(receiver);
        
        if(account!=null){
        	type = "cellphone";
        }else{
        	account = clientAccountDao.getAccountByEmail(receiver);
        	if(account!=null){
        		type = "email";
        	}
        }
		if(account==null){
			result.setResultCode("1");
			result.setResultMsg("账户不存在！");
			return result;
		}
		String authCode = AuthCodeGenerator.generateAuthCode();
		String msg = "找回密码验证码："+authCode;
		if(type.equals("email")){
			MessageUtils.sendEmail(Arrays.asList(receiver), msg, "找回密码验证码");
		}else if(type.equals("cellphone")){
			MessageUtils.sendSMS(receiver, msg);
		}
		authService.cacheCode(receiver, authCode);
		result.setResultCode("0");
		result.setResultMsg("验证码发送成功，请查收！");
		return result;
	}

	@Override
	public ResultBean setNewPassword(String receiver, String verificationCode,
			String newPwd) {
		 ResultBean result = new ResultBean();
			
         List<ClientAccount> accountList =  clientAccountDao.getListByMultiAttr(receiver);
         if(accountList==null||accountList.size()==0){
        	 result.setResultCode("1");
			 result.setResultMsg("账号不存在！");
			 return result;
         }
         if(accountList!=null&&accountList.size()>1){
        	 result.setResultCode("1");
			 result.setResultMsg("手机号或邮箱绑定了多个账户！");
			 return result;
         }
         String cachedCode = authService.getCachedCode(receiver);   
         if(StringUtils.isEmpty(cachedCode)){
        	 result.setResultCode("1");
			 result.setResultMsg("验证码已经失效，请重新获取！");
			 return result;
         }
	     if(!cachedCode.equals(verificationCode)){
	    	 result.setResultCode("1");
			 result.setResultMsg("验证码不正确！");
			 return result;
	     }
	     ClientAccount account = accountList.get(0);
	     account.setPassword(MD5Util.GetMD5Code(newPwd));
	     account.setModifyTime(Calendar.getInstance().getTime());
	     boolean flag =  clientAccountDao.update(account);
	     if(flag){
	    	 result.setResultCode("0");
			 result.setResultMsg("设置成功！");
			 return result;
	     }else{
	    	 result.setResultCode("1");
			 result.setResultMsg("数据库异常！");
			 return result;
	     }
	}
}
