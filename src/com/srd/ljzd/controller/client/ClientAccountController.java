/**   
* @Title: ClientAccountController.java 
* @Package com.srd.ljzd.controller.client 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年8月23日 下午2:50:56 
* @version V1.0   
*/
package com.srd.ljzd.controller.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.srd.ljzd.controller.base.BaseController;
import com.srd.ljzd.entity.client.ClientAccount;
import com.srd.ljzd.entity.common.ResultBean;
import com.srd.ljzd.service.client.ClientAccountService;
import com.srd.ljzd.service.sys.AuthService;
import com.srd.ljzd.util.DateUtils;
import com.srd.ljzd.util.MD5Util;
import com.srd.ljzd.util.StringUtils;


/** 
 * @ClassName: ClientAccountController
 * @Description: 客户账号控制器
 * @author shiyong
 * @date 2017年8月23日 下午2:50:56
 *  
 */
@Controller
@RequestMapping("/clientAccount")
public class ClientAccountController extends BaseController {
	@Autowired
	private ClientAccountService clientAccountService;
	
	@Autowired
	private AuthService authService;
	
	/** 
	* @Title: toClientAccountManage 
	* @Description: 跳转到客户账号管理页面
	* @param @param preDeep
	* @param @param request
	* @param @param response
	* @param @param model
	* @param @return 设定文件 
	* @return String 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月1日 上午10:49:06
	*/
	@RequestMapping("/toClientAccountManage")
	public String toClientAccountManage(HttpServletRequest request, HttpServletResponse response, Model model){
		
		return "/modules/client/account/client_account_list";
	}
	
	/** 
	* @Title: toClientAccountInfo 
	* @Description: 打开账号信息页面
	* @param @param preDeep
	* @param @param request
	* @param @param response
	* @param @param model
	* @param @return 设定文件 
	* @return String 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月15日 下午3:35:53
	*/
	@RequestMapping("/toClientAccountInfo")
	public String toClientAccountInfo(HttpServletRequest request, HttpServletResponse response, Model model){
		String accountId = getAccountIdFromSession(request);
		
		//从数据库中获取账号信息在页面上展示
		ClientAccount clientAccount = clientAccountService.getClientAccountInfoById(accountId);
		
		model.addAttribute("clientAccount", clientAccount);
		
		return "/modules/client/account/client_account_info";
	}
	
	/** 
	* @Title: toSecurityCenter 
	* @Description: 跳转到账号安全中心
	* @param @param deep
	* @param @param request
	* @param @param response
	* @param @param model
	* @param @return 设定文件 
	* @return String 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月15日 下午5:33:55
	*/
	@RequestMapping("/toSecurityCenter")
	public String toSecurityCenter(HttpServletRequest request, HttpServletResponse response, Model model){
		
		return "/modules/client/account/client_security_center";
	}
	
	/** 
	* @Title: addClientAccount 
	* @Description: 新增客户账号
	* @param @param clientAccount
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年8月24日 下午4:50:30
	*/
	@RequestMapping("/addClientAccount")
	@ResponseBody
	public ResultBean addClientAccount(ClientAccount clientAccount, HttpServletRequest request, HttpServletResponse response){
		
		ResultBean result = new ResultBean();
		
		ClientAccount creator = getClientAccountFromSession(request);
		
		if(null != creator){
			if(StringUtils.isNotEmpty(clientAccount.getAccountName()) && StringUtils.isNotEmpty(clientAccount.getParentId()) 
				&& StringUtils.isNotEmpty(clientAccount.getPassword()) && StringUtils.isNotEmpty(clientAccount.getType()) 
				&& StringUtils.isNotEmpty(clientAccount.getEmail())){
				
				clientAccount.setClientCompany(creator.getClientCompany());
				clientAccount.setUsedMonitorNum(0);
				clientAccount.setUsedChildNum(0);
				clientAccount.setStatus("0");
				clientAccount.setCreatorId(creator.getAccountId());
				clientAccount.setCreatorName(creator.getAccountName());
				clientAccount.setCreateTime(DateUtils.getCurrentDate());
				clientAccount.setModifierId(creator.getAccountId());
				clientAccount.setModifierName(creator.getAccountName());
				clientAccount.setModifyTime(DateUtils.getCurrentDate());
				clientAccount.setDeleteFlag("0");
				
				result = clientAccountService.saveClientAccount(clientAccount);
			}else{
				result.setResultCode("1");
				result.setResultMsg("信息缺失，请核对后重新提交！");
			}
		}else{
			result.setResultCode("1");
			result.setResultMsg("当前登录用户会话已失效，请重新登录！");
		}
		
		return result;
	}
	
	/** 
	* @Title: editClientAccount 
	* @Description: 编辑客户账号信息
	* @param @param clientAccount
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年8月30日 下午2:08:49
	*/
	@RequestMapping("/editClientAccount")
	@ResponseBody
	public ResultBean editClientAccount(ClientAccount clientAccount, HttpServletRequest request, HttpServletResponse response){
		
		ResultBean result = new ResultBean();
		
		ClientAccount modifier = getClientAccountFromSession(request);
		
		if(null != modifier){
			if(StringUtils.isNotEmpty(clientAccount.getAccountName()) && StringUtils.isNotEmpty(clientAccount.getEmail())){
					
				clientAccount.setModifierId(modifier.getAccountId());
				clientAccount.setModifierName(modifier.getAccountName());
				clientAccount.setModifyTime(DateUtils.getCurrentDate());
				
				result = clientAccountService.updateClientAccount(clientAccount);
			}else{
				result.setResultCode("1");
				result.setResultMsg("信息缺失，请核对后重新提交！");
			}
		}else{
			result.setResultCode("1");
			result.setResultMsg("当前登录用户会话已失效，请重新登录！");
		}
		
		return result;
	}
	
	/** 
	* @Title: getClientAccount 
	* @Description: 根据账号ID获取账号信息
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return ClientAccount 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月7日 下午4:15:17
	*/
	@RequestMapping("/getClientAccount")
	@ResponseBody
	public ClientAccount getClientAccount(HttpServletRequest request, HttpServletResponse response){
		String accountId = getParameterFromRequest(request, "accountId");
		
		ClientAccount account = clientAccountService.getClientAccountInfoById(accountId);
		
		return account;
	}
	
	/** 
	* @Title: disableClientAccount 
	* @Description: 禁用账号
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年8月30日 下午5:07:11
	*/
	@RequestMapping("/disableClientAccount")
	@ResponseBody
	public ResultBean disableClientAccount(HttpServletRequest request, HttpServletResponse response){
		ResultBean result = new ResultBean();
		
		ClientAccount modifier = getClientAccountFromSession(request);
		
		if(null != modifier){
			String accountId = getParameterFromRequest(request, "accountId");
			
			if(StringUtils.isNotEmpty(accountId)){
				result = clientAccountService.disableClientAccount(accountId, modifier);
			}else{
				result.setResultCode("1");
				result.setResultMsg("信息缺失，请核对后重新提交！");
			}
		}else{
			result.setResultCode("1");
			result.setResultMsg("当前登录用户会话已失效，请重新登录！");
		}
		
		return result;
	}
	
	/** 
	* @Title: enableClientAccount 
	* @Description: 启用账号
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月7日 下午3:27:56
	*/
	@RequestMapping("/enableClientAccount")
	@ResponseBody
	public ResultBean enableClientAccount(HttpServletRequest request, HttpServletResponse response){
		ResultBean result = new ResultBean();
		
		ClientAccount modifier = getClientAccountFromSession(request);
		
		if(null != modifier){
			String accountId = getParameterFromRequest(request, "accountId");
			
			if(StringUtils.isNotEmpty(accountId)){
				result = clientAccountService.enableClientAccount(accountId, modifier);
			}else{
				result.setResultCode("1");
				result.setResultMsg("信息缺失，请核对后重新提交！");
			}
		}else{
			result.setResultCode("1");
			result.setResultMsg("当前登录用户会话已失效，请重新登录！");
		}
		
		return result;
	}
	
	/** 
	* @Title: deleteClientAccount 
	* @Description: 删除客户账号
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年8月30日 下午5:57:31
	*/
	@RequestMapping("/deleteClientAccount")
	@ResponseBody
	public ResultBean deleteClientAccount(HttpServletRequest request, HttpServletResponse response){
		ResultBean result = new ResultBean();
		
		ClientAccount modifier = getClientAccountFromSession(request);
		
		if(null != modifier){
			String accountId = getParameterFromRequest(request, "accountId");
			
			if(StringUtils.isNotEmpty(accountId)){
				result = clientAccountService.deleteClientAccount(accountId, modifier);
			}else{
				result.setResultCode("1");
				result.setResultMsg("信息缺失，请核对后重新提交！");
			}
		}else{
			result.setResultCode("1");
			result.setResultMsg("当前登录用户会话已失效，请重新登录！");
		}
		
		return result;
	}
	
	/** 
	* @Title: getClientAccountTree 
	* @Description: 根据账号ID获取树节点
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年8月30日 下午7:01:25
	*/
	@RequestMapping("/getClientAccountTree")
	@ResponseBody
	public ResultBean getClientAccountTree(HttpServletRequest request, HttpServletResponse response){
		ResultBean result = new ResultBean();
		
		String accountId = getAccountIdFromSession(request);
		
		if(StringUtils.isNotEmpty(accountId)){
			result = clientAccountService.getClientAccountTree(accountId);
		}else{
			result.setResultCode("1");
			result.setResultMsg("信息缺失，请核对后重新提交！");
		}
		
		return result;
	}
	
	
	/** 
	* @Title: getClientAccountList 
	* @Description: 获取客户账号列表
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年8月31日 下午8:24:57
	*/
	@RequestMapping("/getClientAccountList")
	@ResponseBody
	public ResultBean getClientAccountList(HttpServletRequest request, HttpServletResponse response){
		ResultBean result = new ResultBean();
		
		String accountId = getParameterFromRequest(request, "accountId");
		
		if(StringUtils.isNotEmpty(accountId)){
			//母账号本身也要在列表中显示
			ClientAccount account = clientAccountService.getClientAccountInfoById(accountId);
			
			List<ClientAccount> accountList = new ArrayList<ClientAccount>();
			
			accountList.add(account);
			
			List<ClientAccount> childAccountList = clientAccountService.getClientAccountListByParentId(accountId);
			
			for(ClientAccount clientAccount : childAccountList){
				accountList.add(clientAccount);
			}
			
			result.setResultCode("0");
			result.setResultData(accountList);
		}else{
			result.setResultCode("1");
			result.setResultMsg("信息缺失，请核对后重新提交！");
		}
		
		return result;
	}
	
	/** 
	* @Title: getAccountQuotasByParent 
	* @Description: 获取母账号所有账号的额度情况
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月7日 下午2:40:16
	*/
	@RequestMapping("/getAccountQuotasByParent")
	@ResponseBody
	public ResultBean getAccountQuotasByParent(HttpServletRequest request, HttpServletResponse response){
		ResultBean result = new ResultBean();
		
		String accountId = getAccountIdFromSession(request);
		
		Map<String, Object> accountQuotasMap = clientAccountService.getAccountQuotasByParentId(accountId);
		
		result.setResultCode("0");
		result.setResultData(accountQuotasMap);
		
		return result;
	}
	
	/** 
	* @Title: updateClientInfo 
	* @Description: 更新客户信息
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月19日 下午3:36:29
	*/
	@RequestMapping("/updateClientInfo")
	@ResponseBody
	public ResultBean updateClientInfo(HttpServletRequest request, HttpServletResponse response){
		
		String userName = getParameterFromRequest(request, "userName");
		
		ResultBean result = new ResultBean();
		
		ClientAccount account = getClientAccountFromSession(request);
		
		if(null != account){
			account.setName(userName);
			
			result = clientAccountService.updateClientAccountInfo(account);
		}else{
			result.setResultCode("1");
			result.setResultMsg("当前登录用户会话已失效，请重新登录！");
		}
		
		return result;
	}
	
	/** 
	* @Title: updateClientAccountPassword 
	* @Description: 更新客户账号密码
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月19日 下午6:20:36
	*/
	@RequestMapping(value="/updateClientAccountPassword")
  	@ResponseBody
  	public ResultBean updateClientAccountPassword(HttpServletRequest request, HttpServletResponse response){
  		ResultBean result = new ResultBean();
  		
		String accountId = getAccountIdFromSession(request);
  		
  		String oldPassword = request.getParameter("oldPassword");
  		String newPassword = request.getParameter("newPassword");
  		
  		ClientAccount account = clientAccountService.getClientAccountInfoById(accountId);
  		
  		//验证旧密码是否正确
  		oldPassword = MD5Util.GetMD5Code(oldPassword);
  		
  		if(!oldPassword.equals(account.getPassword())){
  			result.setResultCode("1");
  			result.setResultMsg("旧密码不正确！");
  			
  			return result;
  		}
  		
  		account.setPassword(MD5Util.GetMD5Code(newPassword));
  		account.setModifierId(accountId);
  		account.setModifierName(account.getAccountName());
  		account.setModifyTime(new Date());
  		
  		result = clientAccountService.updateClientAccountInfo(account);
  		
  		//修改密码成功后，清空session中的账户信息，重新登录页面
  		if("0".equals(result.getResultCode())){
  			saveInfoToSession(request, "account", null);
  		}
  		
  		return result;
  	}
	
	/** 
	* @Title: updateClientAccountMobilePhone 
	* @Description: 更新客户手机号码
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月19日 下午6:42:32
	*/
	@RequestMapping(value="/updateClientAccountMobilePhone")
	@ResponseBody
	public ResultBean updateClientAccountMobilePhone(HttpServletRequest request, HttpServletResponse response){
		
		ResultBean result = new ResultBean();
		
		String accountId = getAccountIdFromSession(request);
		
        String cellPhone = request.getParameter("cellPhone").trim();
        String verificationCode = request.getParameter("verificationCode").trim();
        
        ClientAccount account = clientAccountService.getClientAccountInfoById(accountId);
        
		//验证码是否正确
		String cachedVerificationCode = authService.getCachedCode(cellPhone);
		
		if(StringUtils.isNotEmpty(cachedVerificationCode)){
			if(cachedVerificationCode.equals(verificationCode)){
				account.setMobilePhone(cellPhone);
				
				result = clientAccountService.updateClientAccountInfo(account);
				
				if("0".equals(result.getResultCode())){
					//更新Session中的用户信息
					request.getSession().setAttribute("account",account);
				}
				
				//清空验证码
				authService.clearCachedCode(cellPhone);
			}else{
				result.setResultCode("1");
	  			result.setResultMsg("验证码有误，请核实！");
			}
		}else{
			result.setResultCode("1");
  			result.setResultMsg("验证码失效，请重新获取！");
		}
		
		return result;
	}
	
	/** 
	* @Title: updateClientAccountEmail 
	* @Description: 更新客户账号邮箱
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月26日 下午1:48:25
	*/
	@RequestMapping(value="/updateClientAccountEmail")
	@ResponseBody
	public ResultBean updateClientAccountEmail(HttpServletRequest request, HttpServletResponse response){
		
		ResultBean result = new ResultBean();
		
		String accountId = getAccountIdFromSession(request);
		
        String email = request.getParameter("email").trim();
        String emailVerificationCode = request.getParameter("emailVerificationCode").trim();
        
        ClientAccount account = clientAccountService.getClientAccountInfoById(accountId);
        
		//验证码是否正确
		String cachedVerificationCode = authService.getCachedCode(email);
		
		if(StringUtils.isNotEmpty(cachedVerificationCode)){
			if(cachedVerificationCode.equals(emailVerificationCode)){
				account.setEmail(email);
				
				result = clientAccountService.updateClientAccountInfo(account);
				
				if("0".equals(result.getResultCode())){
					//更新Session中的用户信息
					request.getSession().setAttribute("account",account);
				}
				
				//清空验证码
				authService.clearCachedCode(email);
			}else{
				result.setResultCode("1");
	  			result.setResultMsg("验证码有误，请核实！");
			}
		}else{
			result.setResultCode("1");
  			result.setResultMsg("验证码失效，请重新获取！");
		}
		
		return result;
	}
}
