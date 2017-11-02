/**   
* @Title: InfoMessageAccountDao.java 
* @Package com.srd.ljzd.dao.info 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年11月22日 下午2:48:29 
* @version V1.0   
*/
package com.srd.ljzd.dao.info;

import java.util.List;

import com.srd.ljzd.dao.base.BaseDao;
import com.srd.ljzd.entity.info.InfoMessageAccount;
import com.srd.ljzd.util.Page;

/** 
 * @ClassName: InfoMessageAccountDao
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author shiyong
 * @date 2016年11月22日 下午2:48:29
 *  
 */
public interface InfoMessageAccountDao extends BaseDao<InfoMessageAccount,String>{
	
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
	* @date 2016年11月22日 下午5:25:21
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
	* @date 2016年11月23日 上午10:07:31
	*/
	public List<InfoMessageAccount> queryMessageAccountList(InfoMessageAccount infoMessageAccount);
	
	/** 
	* @Title: getUnreadMessageNum 
	* @Description: 获取账号未读消息数量
	* @param @param accountId
	* @param @return 设定文件 
	* @return int 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月23日 下午2:12:31
	*/
	public int getUnreadMessageNum(String accountId);
	
}
