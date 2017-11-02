/**   
* @Title: AuthController.java 
* @Package com.srd.ljzd.controller.sys 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年9月19日 下午5:28:03 
* @version V1.0   
*/
package com.srd.ljzd.controller.sys;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.srd.ljzd.controller.base.BaseController;
import com.srd.ljzd.entity.common.ResultBean;
import com.srd.ljzd.service.sys.AuthService;
import com.srd.ljzd.service.sys.EmailService;
import com.srd.ljzd.service.sys.ShortMessageService;
import com.srd.ljzd.util.AuthCodeGenerator;
import com.srd.ljzd.util.StringUtils;

/** 
 * @ClassName: AuthController
 * @Description: 安全验证控制器
 * @author shiyong
 * @date 2017年9月19日 下午5:28:03
 *  
 */
@Controller
@RequestMapping("/auth")
public class AuthController extends BaseController{
	@Autowired
	private ShortMessageService shortMessageService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private AuthService authService;
	
	/** 
	* @Title: sendVerificationCodeByMobilePhone 
	* @Description: 通过手机发送动态验证码
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月19日 下午6:27:01
	*/
	@RequestMapping(value="/sendVerificationCodeByMobilePhone")
  	@ResponseBody
  	public ResultBean sendVerificationCodeByMobilePhone(HttpServletRequest request, HttpServletResponse response){
  		
  		ResultBean result = new ResultBean();
  		
  		String cellPhone = getParameterFromRequest(request, "cellPhone");
  		
  		if(StringUtils.isNotEmpty(cellPhone)){
  			//发送验证码
  	  		String verificationCode = AuthCodeGenerator.generateAuthCode();
  			authService.cacheCode(cellPhone, verificationCode);
  	  		
  			//发送验证码,并缓存验证码
  			String msg = "您正在RiskRaider风险雷达申请的验证码为：" + verificationCode + "。";
  			
  			result = shortMessageService.sendShortMessage(cellPhone, msg);
  		}else{
  			result.setResultCode("1");
  			result.setResultMsg("手机号码为空！");
  		}
  		
  		return result;
  	}
	
	/** 
	* @Title: sendVerificationCodeByEmail 
	* @Description: 通过邮箱发送动态验证码
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月19日 下午5:35:59
	*/
	@RequestMapping(value="/sendVerificationCodeByEmail")
  	@ResponseBody
  	public ResultBean sendVerificationCodeByEmail(HttpServletRequest request, HttpServletResponse response){
  		
  		ResultBean result = new ResultBean();
  		
  		String email = getParameterFromRequest(request, "email");
  		
  		if(StringUtils.isNotEmpty(email)){
  			//发送验证码,并缓存验证码
  	  		String verificationCode = AuthCodeGenerator.generateAuthCode();
  			authService.cacheCode(email, verificationCode);
  			
  			List<String> recipientList = new ArrayList<String>();
  			recipientList.add(email);
  			
  			result = emailService.sendEmail(recipientList, "邮箱验证码：" + verificationCode, "风声征信邮箱验证码");
  		}else{
  			result.setResultCode("1");
  			result.setResultMsg("邮件地址为空！");
  		}
  		
  		return result;
  	}
	
}
