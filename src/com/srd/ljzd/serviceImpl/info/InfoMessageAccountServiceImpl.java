/**   
* @Title: InfoMessageAccountServiceImpl.java 
* @Package com.srd.ljzd.serviceImpl.info 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年11月22日 下午2:50:37 
* @version V1.0   
*/
package com.srd.ljzd.serviceImpl.info;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.srd.ljzd.dao.info.InfoMessageAccountDao;
import com.srd.ljzd.entity.common.ResultBean;
import com.srd.ljzd.entity.info.InfoMessageAccount;
import com.srd.ljzd.service.info.InfoMessageAccountService;
import com.srd.ljzd.util.Page;

/** 
 * @ClassName: InfoMessageAccountServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author shiyong
 * @date 2016年11月22日 下午2:50:37
 *  
 */
@Service("infoMessageAccountService")
public class InfoMessageAccountServiceImpl implements InfoMessageAccountService {

	@Autowired
	private InfoMessageAccountDao infoMessageAccountDao;
	
	@Override
	public Page<InfoMessageAccount> findMessageAccountPage(
			InfoMessageAccount infoMessageAccount, int currentPageNo, int pageSize) {
		
		return infoMessageAccountDao.findMessageAccountPage(infoMessageAccount, currentPageNo, pageSize);
	}

	@Override
	public ResultBean updateMessageReadStatus(InfoMessageAccount infoMessageAccount) {
		
		infoMessageAccount = infoMessageAccountDao.get(infoMessageAccount.getId());
		
		infoMessageAccount.setReadStatus("1");
		infoMessageAccount.setLastModifiedTime(new Date());
		
		boolean flag = infoMessageAccountDao.update(infoMessageAccount);
		
		ResultBean result = new ResultBean();
		
		if(flag){
			result.setResultCode("0");
			result.setResultMsg("更新消息已读状态成功！");
		}else{
			result.setResultCode("1");
			result.setResultMsg("更新消息已读状态失败！");
		}
		
		return result;
	}
	
	@Override
	public ResultBean updateMessageReadStatus(List<InfoMessageAccount> infoMessageAccountList) {
		ResultBean result = new ResultBean();
		
		for(InfoMessageAccount infoMessageAccount : infoMessageAccountList){
			result = updateMessageReadStatus(infoMessageAccount);
			
			if("1".equals(result.getResultCode())){
				return result;
			}
		}
		
		return result;
	}

	@Override
	public List<InfoMessageAccount> queryMessageAccountList(InfoMessageAccount infoMessageAccount) {
		
		return infoMessageAccountDao.queryMessageAccountList(infoMessageAccount);
	}

	@Override
	public ResultBean deleteMessageAccounts(String[] messageIds) {
		ResultBean result = new ResultBean();
		
		InfoMessageAccount infoMessageAccount = null;
		
		for(int i=0;i<messageIds.length;i++){
			infoMessageAccount = infoMessageAccountDao.get(messageIds[i]);
			infoMessageAccount.setIsDelete("1");
			infoMessageAccount.setLastModifiedTime(new Date());
			
			boolean flag = infoMessageAccountDao.update(infoMessageAccount);
			
			if(!flag){
				result.setResultCode("1");
				result.setResultMsg("删除消息失败！");
				
				return result;
			}
		}
		
		result.setResultCode("0");
		result.setResultMsg("删除消息成功！");
		
		return result;
	}

	
	@Override
	public int getUnreadMessageNum(String accountId) {
		return infoMessageAccountDao.getUnreadMessageNum(accountId);
	}

	
	
	
	
}
