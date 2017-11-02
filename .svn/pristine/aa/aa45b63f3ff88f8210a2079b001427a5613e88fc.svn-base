/**   
* @Title: InfoMessageServiceImpl.java 
* @Package com.srd.ljzd.serviceImpl.info 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年11月22日 下午2:50:52 
* @version V1.0   
*/
package com.srd.ljzd.serviceImpl.info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.srd.ljzd.dao.info.InfoMessageDao;
import com.srd.ljzd.entity.info.InfoMessage;
import com.srd.ljzd.service.info.InfoMessageService;

/** 
 * @ClassName: InfoMessageServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author shiyong
 * @date 2016年11月22日 下午2:50:52
 *  
 */
@Service("infoMessageService")
public class InfoMessageServiceImpl implements InfoMessageService {

	@Autowired
	private InfoMessageDao infoMessageDao;

	@Override
	public InfoMessage getMessageById(String messageId) {
		
		return infoMessageDao.get(messageId);
	}
	
	
}
