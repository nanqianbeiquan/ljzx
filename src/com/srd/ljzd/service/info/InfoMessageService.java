/**   
* @Title: InfoMessageService.java 
* @Package com.srd.ljzd.service.info 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年11月22日 下午2:49:54 
* @version V1.0   
*/
package com.srd.ljzd.service.info;

import com.srd.ljzd.entity.info.InfoMessage;

/** 
 * @ClassName: InfoMessageService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author shiyong
 * @date 2016年11月22日 下午2:49:54
 *  
 */
public interface InfoMessageService {
	
	
	/** 
	* @Title: getMessageById 
	* @Description: 根据id获取消息内容
	* @param @param messageId
	* @param @return 设定文件 
	* @return InfoMessage 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月22日 下午4:47:14
	*/
	public InfoMessage getMessageById(String messageId);
}
