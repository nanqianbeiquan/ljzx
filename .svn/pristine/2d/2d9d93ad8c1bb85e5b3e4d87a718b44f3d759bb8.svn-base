/**   
* @Title: InfoMessageAccountService.java 
* @Package com.srd.ljzd.service.info 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年11月22日 下午2:50:20 
* @version V1.0   
*/
package com.srd.ljzd.service.info;

import java.util.List;

import com.srd.ljzd.entity.common.ResultBean;
import com.srd.ljzd.entity.info.InfoMessageAccount;
import com.srd.ljzd.util.Page;

/** 
 * @ClassName: InfoMessageAccountService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author shiyong
 * @date 2016年11月22日 下午2:50:20
 *  
 */
public interface InfoMessageAccountService {

	/** 
	* @Title: findMessageAccountPage 
	* @Description: 获取消息列表分页
	* @param @param infoMessageAccount
	* @param @param currentPageNo
	* @param @param pageSize
	* @param @return 设定文件 
	* @return Page<InfoMessageAccount> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月22日 下午5:32:14
	*/
	public Page<InfoMessageAccount> findMessageAccountPage(InfoMessageAccount infoMessageAccount, int currentPageNo, int pageSize);
	
	/** 
	* @Title: queryMessageAccountList 
	* @Description: 查询用户消息列表
	* @param @param infoMessageAccount
	* @param @return 设定文件 
	* @return List<InfoMessageAccount> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月23日 上午9:59:27
	*/
	public List<InfoMessageAccount> queryMessageAccountList(InfoMessageAccount infoMessageAccount);
	
	/** 
	* @Title: getUnreadMessageNum 
	* @Description: 获取未读消息数量
	* @param @param accountId
	* @param @return 设定文件 
	* @return int 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月23日 下午2:06:01
	*/
	public int getUnreadMessageNum(String accountId);
	
	/** 
	* @Title: updateMessageReadStatus 
	* @Description: 标记消息为已读 
	* @param @param infoMessageAccount
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月22日 下午6:06:36
	*/
	public ResultBean updateMessageReadStatus(InfoMessageAccount infoMessageAccount);
	
	/** 
	* @Title: updateMessageReadStatus 
	* @Description: 标记消息为已读 
	* @param @param infoMessageAccountList 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月23日 上午10:04:27
	*/
	public ResultBean updateMessageReadStatus(List<InfoMessageAccount> infoMessageAccountList);
	
	/** 
	* @Title: deleteMessageAccounts 
	* @Description: 删除账号消息
	* @param @param messageIds
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月23日 下午1:34:12
	*/
	public ResultBean deleteMessageAccounts(String[] messageIds);
	
}
