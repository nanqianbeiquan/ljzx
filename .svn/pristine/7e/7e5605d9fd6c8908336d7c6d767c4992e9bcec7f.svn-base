/**   
* @Title: LoginService.java 
* @Package com.srd.ljzd.service.login 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年11月27日 下午12:18:42 
* @version V1.0   
*/
package com.srd.ljzd.service.login;


import com.srd.ljzd.entity.common.ResultBean;

/** 
 * @ClassName: LoginService
 * @Description: 登录Service
 * @author shiyong
 * @date 2016年11月27日 下午12:18:42
 *  
 */
public interface LoginService {
	
	
	/** 
	* @Title: saveAccountSessionId
	* @Description: 保存账号和session关联
	* @param @param accountId
	* @param @param sessionId 设定文件 
	* @return void 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年7月13日 上午9:47:37
	*/
	public void saveAccountSessionId(String accountId, String sessionId);
	
	/** 
	* @Title: getAccountSessionId
	* @Description: 获取和账号关联的SessionId
	* @param @param accountId
	* @param @return 设定文件 
	* @return String 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年7月28日 下午4:55:36
	*/
	public String getAccountSessionId(String accountId);
	
	/** 
	* @Title: deleteAccountSessionId 
	* @Description: 删除账号和session关联
	* @param @param accountId 设定文件 
	* @return void 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年7月28日 下午4:55:22
	*/
	public void deleteAccountSessionId(String accountId);

	public ResultBean doLogin(String accountName, String pwd);
	
	public ResultBean sendAuthCode(String receiver);

	public ResultBean setNewPassword(String receiver, String verificationCode,
			String newPwd);
}
