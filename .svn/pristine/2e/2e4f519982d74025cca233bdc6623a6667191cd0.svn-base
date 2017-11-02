/**   
* @Title: BaseController.java 
* @Package com.srd.ljzd.controller.base 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年4月13日 上午10:18:29 
* @version V1.0   
*/
package com.srd.ljzd.controller.base;

import javax.servlet.http.HttpServletRequest;

import com.srd.ljzd.entity.client.ClientAccount;
import com.srd.ljzd.exception.SessionOverdueException;
import com.srd.ljzd.util.Constant;
import com.srd.ljzd.util.ErrorCode;
import com.srd.ljzd.util.StringUtils;

/** 
 * @ClassName: BaseController
 * @Description: 控制器基类
 * @author shiyong
 * @date 2017年4月13日 上午10:18:29
 *  
 */
public class BaseController {

	/** 
	* @Title: getSysAccountFromSession 
	* @Description: 获取保存在Session中的账号信息
	* @param @param request
	* @param @return 设定文件 
	* @return ClientAccount 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年4月13日 上午10:26:52
	*/
	protected ClientAccount getClientAccountFromSession(HttpServletRequest request) {
		ClientAccount account = (ClientAccount) request.getSession().getAttribute(Constant.ACCOUNT_CONTEXT);
		
		if(null == account || null == account.getAccountId()){
			throw new SessionOverdueException(ErrorCode.ER_SESSION_OVERDUE, "登录会话过期", null, this.getClass());
		}
		
        return account;
    }
	
	/** 
	* @Title: getAccountIdFromSession 
	* @Description: 从Session中获取账号ID
	* @param @param request
	* @param @return 设定文件 
	* @return String 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年6月14日 下午4:11:13
	*/
	public String getAccountIdFromSession(HttpServletRequest request){
		ClientAccount account = (ClientAccount)request.getSession().getAttribute("account");
		
		if(null == account || null == account.getAccountId()){
			throw new SessionOverdueException(ErrorCode.ER_SESSION_OVERDUE, "登录会话过期", null, this.getClass());
		}
		
		return account.getAccountId();
	}
	
	/** 
	* @Title: saveInfoToSession 
	* @Description: 将信息保存到Session中
	* @param @param request
	* @param @param name
	* @param @param object 设定文件 
	* @return void 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年6月15日 下午1:12:56
	*/
	public void saveInfoToSession(HttpServletRequest request, String name, Object object){
		request.getSession().setAttribute(name, object);
	}
	
	/** 
	* @Title: getParameterFromRequest 
	* @Description: 获取请求参数
	* @param @param request
	* @param @param name
	* @param @return 设定文件 
	* @return String 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年7月21日 下午3:24:32
	*/
	public String getParameterFromRequest(HttpServletRequest request, String name){
		String parameter = request.getParameter(name);
		
		if(StringUtils.isNotEmpty(parameter)){
			parameter = parameter.trim();
		}else{
			parameter = "";
		}
		
		return parameter;
	}
	
	
}
