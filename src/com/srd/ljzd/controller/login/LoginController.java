/**   
* @Title: LoginController.java 
* @Package com.srd.ljzd.controller.login 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年11月25日 下午5:53:27 
* @version V1.0   
*/
package com.srd.ljzd.controller.login;


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
import com.srd.ljzd.service.login.LoginService;
import com.srd.ljzd.util.Constant;
import com.srd.ljzd.util.StringUtils;

/** 
 * @ClassName: LoginController
 * @Description: 登录控制Controller
 * @author shiyong
 * @date 2016年11月25日 下午5:53:27
 *  
 */
@Controller
public class LoginController extends BaseController {
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value="/login")
	public String login(HttpServletRequest request,HttpServletResponse response){
		
		//清楚session中账户信息
 		request.getSession().setAttribute("account", null);
		
		return "/modules/login/login4Web";
	}
	
	/** 
	* @Title: doLogin 
	* @Description: 用户登录
	* @return ResultBean 返回类型 
	*/
	@RequestMapping(value="/doLogin")
	@ResponseBody
	public ResultBean doLogin(String pwd,String accountName,HttpServletRequest request,HttpServletResponse response){
		ResultBean result = new ResultBean();
		
		if(StringUtils.isNotEmpty(pwd) && StringUtils.isNotEmpty(accountName)){
			pwd = pwd.trim();
			accountName = accountName.trim();
			
			result = loginService.doLogin(accountName, pwd);
		}else{
			result.setResultCode("1");
			result.setResultMsg("用户名或密码不能为空！");
		}
		
		return result;
	}
	
	/** 
	* @Title: logout 
	* @Description: 退出
	* @param @param request
	* @param @param response
	* @param @param model
	* @param @return 设定文件 
	* @return String 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月30日 下午2:39:22
	*/
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, Model model){
		
		ClientAccount account = (ClientAccount) request.getSession().getAttribute(Constant.ACCOUNT_CONTEXT);
		
		//清除session中的用户信息
		request.getSession().setAttribute("account", null);
		
		request.getSession().setAttribute("childAccountList", null);
		
		//清除缓存中的sessionId
		if(account != null){
			loginService.deleteAccountSessionId(account.getAccountId());
		}
		
		return "/modules/login/logout";
	}

	//获取动态密码
	@RequestMapping(value="/login/sendAuthCode")
	@ResponseBody
	public ResultBean sendAuthCode(String receiver, HttpServletRequest request, HttpServletResponse response){
		
		ResultBean result = new ResultBean();
  		
  		if(StringUtils.isNotEmpty(receiver)){
  			receiver = receiver.trim();
  			result = loginService.sendAuthCode(receiver);
  		}
		return result;
	}
	
	@RequestMapping(value="/login/setNewPassword")
	@ResponseBody
	public ResultBean setNewPassword(String receiver,String verificationCode,String newPwd,HttpServletRequest request, HttpServletResponse response){
		ResultBean result = new ResultBean();
		
		if(StringUtils.isEmpty(receiver)){
			result.setResultCode("1");
  			result.setResultMsg("手机号或邮箱不能为空！");
  			return result;
		}
		if(StringUtils.isEmpty(verificationCode)){
			result.setResultCode("1");
  			result.setResultMsg("验证码不能为空！");
  			return result;
		}
		if(StringUtils.isEmpty(newPwd)){
			result.setResultCode("1");
  			result.setResultMsg("密码不能为空！");
  			return result;
		}
		result = loginService.setNewPassword(receiver,verificationCode,newPwd);
		return result;
	}
}
