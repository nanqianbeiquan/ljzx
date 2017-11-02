package com.srd.ljzd.dao.client;

import java.util.List;

import com.srd.ljzd.dao.base.BaseDao;
import com.srd.ljzd.entity.client.ClientAccount;

public interface ClientAccountDao extends BaseDao<ClientAccount, String>{

	public ClientAccount getAccountByName(String accountName);

	public ClientAccount getMotherAccount(String companyId);

	public List<ClientAccount> getAccountListAllByCompanyId(String companyId);

	public ClientAccount getAccountByMobilePhone(String mobilePhone);

	public ClientAccount getAccountByEmail(String email);

	public List<ClientAccount> getListByMultiAttr(String accountName);
	
	/** 
	* @Title: isHaveClientAccount 
	* @Description: 账号是否已存在
	* @param @param accountName
	* @param @param mobilePhone
	* @param @param email
	* @param @return 设定文件 
	* @return boolean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年8月24日 下午4:34:42
	*/
	public boolean isHaveClientAccount(String accountName, String mobilePhone, String email);
	
	/** 
	* @Title: isHaveSameAccountName 
	* @Description: 是否存在同样的账号名称
	* @param @param accountName
	* @param @return 设定文件 
	* @return boolean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年10月17日 下午2:32:49
	*/
	public boolean isHaveSameAccountName(String accountName);
	
	/** 
	* @Title: isHaveSameMobilePhone 
	* @Description: 是否存在同样的手机号 
	* @param @param mobilePhone
	* @param @return 设定文件 
	* @return boolean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年10月17日 下午2:32:52
	*/
	public boolean isHaveSameMobilePhone(String mobilePhone);
	
	/** 
	* @Title: isHaveSameEmail 
	* @Description: 是否存在同样的电子邮箱
	* @param @param email
	* @param @return 设定文件 
	* @return boolean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年10月17日 下午2:32:55
	*/
	public boolean isHaveSameEmail(String email);
	
	/** 
	* @Title: isHaveClientAccountExceptItself 
	* @Description: 除本身外是否有账号名称、手机号和邮箱重复的账号
	* @param @param accountId
	* @param @param accountName
	* @param @param mobilePhone
	* @param @param email
	* @param @return 设定文件 
	* @return boolean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年8月30日 下午2:13:58
	*/
	public boolean isHaveClientAccountExceptItself(String accountId, String accountName, String mobilePhone, String email);

	/** 
	* @Title: isHaveSameAccountNameExceptItself 
	* @Description: 除本身外是否存在同样的账号名称
	* @param @param accountName
	* @param @return 设定文件 
	* @return boolean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年10月17日 下午2:32:49
	*/
	public boolean isHaveSameAccountNameExceptItself(String accountId, String accountName);
	
	/** 
	* @Title: isHaveSameMobilePhoneExceptItself
	* @Description: 除本身外是否存在同样的手机号 
	* @param @param mobilePhone
	* @param @return 设定文件 
	* @return boolean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年10月17日 下午2:32:52
	*/
	public boolean isHaveSameMobilePhoneExceptItself(String accountId, String mobilePhone);
	
	/** 
	* @Title: isHaveSameEmailExceptItself 
	* @Description: 除本身外是否存在同样的电子邮箱
	* @param @param email
	* @param @return 设定文件 
	* @return boolean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年10月17日 下午2:32:55
	*/
	public boolean isHaveSameEmailExceptItself(String accountId, String email);
	
	/** 
	* @Title: isHaveChildAndAffiliatedAccount 
	* @Description: 是否拥有子账号和附属账号
	* @param @param accountId
	* @param @return 设定文件 
	* @return boolean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年8月30日 下午6:29:03
	*/
	public boolean isHaveChildAndAffiliatedAccount(String accountId);
	
	/** 
	* @Title: getClientAccountListByParentId 
	* @Description: 根据父ID获取下一级别的子节点
	* @param @param parentId
	* @param @return 设定文件 
	* @return List<ClientAccount> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年8月31日 下午8:29:29
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
	* @date 2017年9月11日 上午10:12:25
	*/
	public List<ClientAccount> getChildAccountListByParentId(String parentId);
}
