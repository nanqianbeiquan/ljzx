package com.srd.ljzd.service.client;

import java.util.List;
import java.util.Map;

import com.srd.ljzd.entity.client.ClientAccount;
import com.srd.ljzd.entity.client.ClientAccountTreeNode;
import com.srd.ljzd.entity.common.ResultBean;

public interface ClientAccountService {

	public ClientAccount getAccountByName(String accountName);

	public ClientAccount getMotherAccount(String companyId);

	/** 
	* @Title: getParentAccount 
	* @Description: 获取上级账号信息
	* @param @param accountId
	* @param @return 设定文件 
	* @return ClientAccount 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月15日 下午1:41:01
	*/
	public ClientAccount getParentAccount(String accountId);
	
	/** 
	* @Title: getClientAccountById 
	* @Description: 根据账号ID获取账号信息
	* @param @param accountId
	* @param @return 设定文件 
	* @return ClientAccount 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月7日 下午4:16:18
	*/
	public ClientAccount getClientAccountById(String accountId);
	
	/** 
	* @Title: getClientAccountInfoById 
	* @Description: 获取账号的基本信息不包含权限
	* @param @param accountId
	* @param @return 设定文件 
	* @return ClientAccount 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月19日 下午2:02:56
	*/
	public ClientAccount getClientAccountInfoById(String accountId);

	public List<ClientAccount> getAccountListAllByCompanyId(String companyId);

	public ClientAccount getAccountByMobilePhone(String mobilePhone);

	public ClientAccount getAccountByEmail(String email);
	
	/** 
	* @Title: saveClientAccount 
	* @Description: 保存客户账号
	* @param @param clientAccount
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年8月24日 上午11:27:22
	*/
	public ResultBean saveClientAccount(ClientAccount clientAccount);
	
	/** 
	* @Title: updateClientAccount 
	* @Description: 更新客户账号
	* @param @param clientAccount
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年8月24日 上午11:29:29
	*/
	public ResultBean updateClientAccount(ClientAccount clientAccount);
	
	/** 
	* @Title: updateClientAccountInfo 
	* @Description: 更新客户账号基本信息
	* @param @param clientAccount
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月19日 下午6:21:55
	*/
	public ResultBean updateClientAccountInfo(ClientAccount clientAccount);
	
	/** 
	* @Title: disableClientAccount 
	* @Description: 禁用账号
	* @param @param accountId
	* @param @param modifier
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年8月30日 下午5:11:47
	*/
	public ResultBean disableClientAccount(String accountId, ClientAccount modifier);
	
	/** 
	* @Title: enableClientAccount 
	* @Description: 启用账号
	* @param @param accountId
	* @param @param modifier
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月7日 下午3:32:35
	*/
	public ResultBean enableClientAccount(String accountId, ClientAccount modifier);
	
	/** 
	* @Title: deleteClientAccount 
	* @Description: 删除客户账号
	* @param @param accountId
	* @param @param modifier
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年8月30日 下午6:12:05
	*/
	public ResultBean deleteClientAccount(String accountId, ClientAccount modifier);
	
	/** 
	* @Title: getClientAccountTree 
	* @Description: 根据账号ID获取树节点
	* @param @param accountId
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年8月30日 下午7:02:23
	*/
	public ResultBean getClientAccountTree(String accountId);
	
	/** 
	* @Title: findChildClientAccount 
	* @Description: 递归查找子账号
	* @param @param treeNode
	* @param @param clientAccountList
	* @param @return 设定文件 
	* @return ClientAccountTreeNode 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月2日 上午11:03:29
	*/
	public ClientAccountTreeNode findChildClientAccount(ClientAccountTreeNode treeNode, List<ClientAccount> clientAccountList);
	
	/** 
	* @Title: getClientAccountListByParentId 
	* @Description: 根据父ID获取下一级别的子节点
	* @param @param parentId
	* @param @return 设定文件 
	* @return List<ClientAccount> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年8月31日 下午8:26:55
	*/
	public List<ClientAccount> getClientAccountListByParentId(String parentId);
	
	/** 
	* @Title: getChildAccountListByParentId 
	* @Description: 根据父ID获取下一级别的子账号（不包含附属账号）
	* @param @param parentId
	* @param @return 设定文件 
	* @return List<ClientAccount> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月11日 上午10:16:47
	*/
	public List<ClientAccount> getChildAccountListByParentId(String parentId);
	
	/** 
	* @Title: getAllClientAccountListByParentId 
	* @Description: 根据父ID获取所有子孙节点
	* @param @param parentId
	* @param @return 设定文件 
	* @return List<ClientAccount> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月7日 下午1:35:54
	*/
	public List<ClientAccount> getAllClientAccountListByParentId(String parentId);
	
	/** 
	* @Title: getAccountQuotasByParentId 
	* @Description: 根据父账号统计所有节点的额度使用情况 
	* @param @param parentId
	* @param @return 设定文件 
	* @return Map<String,Object> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月7日 下午2:45:27
	*/
	public Map<String, Object> getAccountQuotasByParentId(String parentId);
	
	/** 
	* @Title: getMonitorQuotaByParentId 
	* @Description: 根据父账号统计所有节点的监控情况
	* @param @param accountId
	* @param @return 设定文件 
	* @return Map<String,Object> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月13日 上午11:31:43
	*/
	public Map<String, Object> getMonitorQuotaByParentId(String parentId);
	
	/** 
	* @Title: canMonitorCompany 
	* @Description: 查询用户是否可以监控企业
	* @param @param accountId
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年12月12日 下午7:27:40
	*/
	public ResultBean canMonitorCompany(String accountId);
	
	/** 
	* @Title: reduceMonitorNum 
	* @Description: 减少用户可监控企业数量
	* @param @param accountId
	* @param @return 设定文件 
	* @return ResultBean  返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年12月12日 下午7:28:34
	*/
	public ResultBean reduceMonitorNum(String accountId);
	
	/** 
	* @Title: updateClientInfo 
	* @Description: 更新客户信息
	* @param @param clientAccount
	* @param @param companyType
	* @param @param contact
	* @param @param position
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月19日 下午3:37:50
	*/
	public ResultBean updateClientInfo(ClientAccount clientAccount, String companyType, String contact, String position);
	
	/** 
	* @Title: updateChildAccountParamListInSession 
	* @Description: 更新session中的子账号列表
	* @param @param childAccountList 设定文件 
	* @return void 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年10月14日 上午9:32:25
	*/
	public void updateChildAccountParamListInSession(List<ClientAccount> childAccountList);
	
}
