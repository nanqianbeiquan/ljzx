/**   
* @Title: ClientAccountRightService.java 
* @Package com.srd.ljzd.service.client 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年8月22日 下午5:40:36 
* @version V1.0   
*/
package com.srd.ljzd.service.client;

import java.util.List;

import com.srd.ljzd.entity.client.ClientAccount;
import com.srd.ljzd.entity.common.ResultBean;
import com.srd.ljzd.service.base.BaseService;

/** 
 * @ClassName: ClientAccountRightService
 * @Description: 客户账号权限Service接口
 * @author shiyong
 * @date 2017年8月22日 下午5:40:36
 *  
 */
public interface ClientAccountRightService extends BaseService {

	/** 
	* @Title: saveClientAccountRight 
	* @Description: 保存客户账号权限
	* @param @param clientAccount
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年8月22日 下午5:56:19
	*/
	public ResultBean saveClientAccountRight(ClientAccount clientAccount);
	
	/** 
	* @Title: updateClientAccountRight 
	* @Description: 更新客户账号权限
	* @param @param clientAccount
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年8月23日 上午11:38:18
	*/
	public ResultBean updateClientAccountRight(ClientAccount clientAccount);
	
	/** 
	* @Title: deleteClientAccountRight 
	* @Description: 删除客户账号权限
	* @param @param accountId
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年8月23日 上午11:42:48
	*/
	public ResultBean deleteClientAccountRight(String accountId);
	
	/** 
	* @Title: getClientAccountRight 
	* @Description: 根据账号ID获取权限
	* @param @param accountId
	* @param @return 设定文件 
	* @return ClientAccount 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年8月23日 上午11:45:05
	*/
	public ClientAccount getClientAccountRight(String accountId);
	
	/** 
	* @Title: getWholeClientAccountTree 
	* @Description: 获取整个组织架构树
	* @param @param accountId
	* @param @return 设定文件 
	* @return List<ClientAccount> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年8月31日 下午7:12:02
	*/
	public List<ClientAccount> getWholeClientAccountTree(String accountId);
	
	/** 
	* @Title: getClientAccountList 
	* @Description: 根据父账号ID获取本身和所有子账号及附属账号
	* @param @param parentId
	* @param @return 设定文件 
	* @return List<ClientAccount> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年8月23日 下午2:41:59
	*/
	public List<ClientAccount> getAllClientAccountListByParentId(String parentId);
	
	/** 
	* @Title: getClientAccountListByParentId 
	* @Description: 根据父账号ID获取所有子账号及附属账号
	* @param @param parentId
	* @param @return 设定文件 
	* @return List<ClientAccount> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年8月30日 下午7:20:06
	*/
	public List<ClientAccount> getClientAccountListByParentId(String parentId);
	
	/** 
	* @Title: getChlidAccountNum 
	* @Description: 根据账号ID获取所有子账号数量
	* @param @param accountId
	* @param @return 设定文件 
	* @return int 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年8月24日 下午7:34:26
	*/
	public int getChlidAccountNum(String accountId);
}
