/**   
* @Title: ShortMessageService.java 
* @Package com.srd.ljzd.service.sys 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年9月10日 上午11:23:28 
* @version V1.0   
*/
package com.srd.ljzd.service.sys;

import com.srd.ljzd.entity.common.ResultBean;
import com.srd.ljzd.service.base.BaseService;

/** 
 * @ClassName: ShortMessageService
 * @Description: 短消息服务接口
 * @author shiyong
 * @date 2017年9月10日 上午11:23:28
 *  
 */
public interface ShortMessageService extends BaseService {
	
	/** 
	* @Title: sendShortMessage 
	* @Description: 发送短信
	* @param @param cellphone
	* @param @param msg
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月10日 上午11:52:41
	*/
	public ResultBean sendShortMessage(String cellphone, String msg);
}
