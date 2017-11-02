package com.srd.ljzd.daoImpl.client;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.srd.ljzd.dao.client.ClientAccountDao;
import com.srd.ljzd.daoImpl.base.BaseDaoImpl;
import com.srd.ljzd.entity.client.ClientAccount;
import com.srd.ljzd.util.LoggerUtils;
import com.srd.ljzd.util.StringUtils;
@Repository("clientAccountDao")
public class ClientAccountDaoImpl extends BaseDaoImpl<ClientAccount, String> implements ClientAccountDao{

	@Override
	public ClientAccount getAccountByName(String accountName) {
		String hql = "select account from ClientAccount account where deleteFlag='0' and accountName=?";
		List<ClientAccount> list = super.getListByHql(hql, accountName);
		if(list.size()>0){
			if(list.size()>1){
				LoggerUtils.error("more than one client account by accountName,accountName="+accountName);
			}
			return list.get(0);
		}
		return null;
	}

	@Override
	public ClientAccount getMotherAccount(String companyId) {
		String hql = "select account from ClientAccount account where deleteFlag='0' and clientCompany.companyId=? and type='1'";
		List<ClientAccount> list = super.getListByHql(hql, companyId);
		if(list.size()>0){
			if(list.size()>1){
				LoggerUtils.error("more than one mother client account by companyId,companyId="+companyId);
			}
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<ClientAccount> getAccountListAllByCompanyId(String companyId) {
		Map<String, Object> parameMap = new HashMap<String, Object>();
		
		String hql = "from ClientAccount where deleteFlag = '0' and clientCompany.companyId = :companyId order by accountName";
		
		parameMap.put("companyId", companyId);
		
		List<ClientAccount> list = super.getListByHql(hql, parameMap);
		
		return list;
	}

	@Override
	public ClientAccount getAccountByMobilePhone(String mobilePhone) {
		String hql = "select account from ClientAccount account where deleteFlag='0' and mobilePhone=? ";
		List<ClientAccount> list = super.getListByHql(hql, mobilePhone);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public ClientAccount getAccountByEmail(String email) {
		String hql = "select account from ClientAccount account where deleteFlag='0' and email=? ";
		List<ClientAccount> list = super.getListByHql(hql, email);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<ClientAccount> getListByMultiAttr(String accountName) {
		Map<String, Object> parameMap = new HashMap<String, Object>();
		
		String hql = "select account from ClientAccount account where deleteFlag='0' "
				+ "and (accountName=:accountName or mobilePhone=:mobilePhone or email=:email)";
		
		parameMap.put("accountName", accountName);
		parameMap.put("mobilePhone", accountName);
		parameMap.put("email", accountName);
		
		List<ClientAccount> list = super.getListByHql(hql, parameMap);
		
		return list;
	}

	@Override
	public boolean isHaveClientAccount(String accountName, String mobilePhone, String email) {
		Map<String, Object> parameMap = new HashMap<String, Object>();
		
		String hql = "";
		
		if(StringUtils.isEmpty(mobilePhone)){
			hql = "select count(accountId) from ClientAccount where deleteFlag = '0' and (accountName = :accountName "
					+ " or email = :email)";
		}else if(StringUtils.isEmpty(email)){
			hql = "select count(accountId) from ClientAccount where deleteFlag = '0' and (accountName = :accountName "
					+ " or mobilePhone = :mobilePhone)";
		}else{
			hql = "select count(accountId) from ClientAccount where deleteFlag = '0' and (accountName = :accountName "
					+ " or mobilePhone = :mobilePhone or email = :email)";
		}
		
		parameMap.put("accountName", accountName);
		parameMap.put("mobilePhone", mobilePhone);
		parameMap.put("email", email);
		
		int num = countByHql(hql, parameMap).intValue();
		
		boolean result = false;
		
		if(num > 0){
			result = true;
		}
		
		return result;
	}
	
	@Override
	public boolean isHaveSameAccountName(String accountName) {
		Map<String, Object> parameMap = new HashMap<String, Object>();
		
		String hql = "";
		
		hql = "select count(accountId) from ClientAccount where deleteFlag = '0' and accountName = :accountName ";
		
		parameMap.put("accountName", accountName);
		
		int num = countByHql(hql, parameMap).intValue();
		
		boolean result = false;
		
		if(num > 0){
			result = true;
		}
		
		return result;
	}

	@Override
	public boolean isHaveSameMobilePhone(String mobilePhone) {
		Map<String, Object> parameMap = new HashMap<String, Object>();
		
		String hql = "";
		
		hql = "select count(accountId) from ClientAccount where deleteFlag = '0' and mobilePhone = :mobilePhone";
		
		parameMap.put("mobilePhone", mobilePhone);
		
		int num = countByHql(hql, parameMap).intValue();
		
		boolean result = false;
		
		if(num > 0){
			result = true;
		}
		
		return result;
	}

	@Override
	public boolean isHaveSameEmail(String email) {
		Map<String, Object> parameMap = new HashMap<String, Object>();
		
		String hql = "";
		
		hql = "select count(accountId) from ClientAccount where deleteFlag = '0' and email = :email";
		
		parameMap.put("email", email);
		
		int num = countByHql(hql, parameMap).intValue();
		
		boolean result = false;
		
		if(num > 0){
			result = true;
		}
		
		return result;
	}

	@Override
	public boolean isHaveClientAccountExceptItself(String accountId,
			String accountName, String mobilePhone, String email) {
		
		Map<String, Object> parameMap = new HashMap<String, Object>();
		
		String hql = "";
		
		if(StringUtils.isEmpty(mobilePhone)){
			hql = "select count(accountId) from ClientAccount where deleteFlag = '0' and (accountName = :accountName "
					+ " or email = :email) and accountId != :accountId";
		}else if(StringUtils.isEmpty(email)){
			hql = "select count(accountId) from ClientAccount where deleteFlag = '0' and (accountName = :accountName "
					+ " or mobilePhone = :mobilePhone) and accountId != :accountId";
		}else{
			hql = "select count(accountId) from ClientAccount where deleteFlag = '0' and (accountName = :accountName "
					+ " or mobilePhone = :mobilePhone or email = :email) and accountId != :accountId";
		}
		
		parameMap.put("accountId", accountId);
		parameMap.put("accountName", accountName);
		parameMap.put("mobilePhone", mobilePhone);
		parameMap.put("email", email);
		
		int num = countByHql(hql, parameMap).intValue();
		
		boolean result = false;
		
		if(num > 0){
			result = true;
		}
		
		return result;
	}
	
	@Override
	public boolean isHaveSameAccountNameExceptItself(String accountId,
			String accountName) {
		Map<String, Object> parameMap = new HashMap<String, Object>();
		
		String hql = "";
		
		hql = "select count(accountId) from ClientAccount where deleteFlag = '0' and accountName = :accountName "
				+ " and accountId != :accountId";
		
		parameMap.put("accountId", accountId);
		parameMap.put("accountName", accountName);
		
		int num = countByHql(hql, parameMap).intValue();
		
		boolean result = false;
		
		if(num > 0){
			result = true;
		}
		
		return result;
	}

	@Override
	public boolean isHaveSameMobilePhoneExceptItself(String accountId,
			String mobilePhone) {
		Map<String, Object> parameMap = new HashMap<String, Object>();
		
		String hql = "";
		
		hql = "select count(accountId) from ClientAccount where deleteFlag = '0' and mobilePhone = :mobilePhone and accountId != :accountId";
		
		parameMap.put("accountId", accountId);
		parameMap.put("mobilePhone", mobilePhone);
		
		int num = countByHql(hql, parameMap).intValue();
		
		boolean result = false;
		
		if(num > 0){
			result = true;
		}
		
		return result;
	}

	@Override
	public boolean isHaveSameEmailExceptItself(String accountId, String email) {
		Map<String, Object> parameMap = new HashMap<String, Object>();
		
		String hql = "";
		
		hql = "select count(accountId) from ClientAccount where deleteFlag = '0' and email = :email and accountId != :accountId";
		
		parameMap.put("accountId", accountId);
		parameMap.put("email", email);
		
		int num = countByHql(hql, parameMap).intValue();
		
		boolean result = false;
		
		if(num > 0){
			result = true;
		}
		
		return result;
	}

	@Override
	public boolean isHaveChildAndAffiliatedAccount(String accountId) {
		
		Map<String, Object> parameMap = new HashMap<String, Object>();
		
		String hql = "select count(accountId) from ClientAccount where deleteFlag = '0' and parentId = :accountId";
		
		parameMap.put("accountId", accountId);
		
		int num = countByHql(hql, parameMap).intValue();
		
		boolean result = false;
		
		if(num > 0){
			result = true;
		}
		
		return result;
	}

	@Override
	public List<ClientAccount> getClientAccountListByParentId(String parentId) {
		String hql = "from ClientAccount where deleteFlag = '0' and parentId = '" + parentId + "' order by type, accountName";
		
		return super.getListByHql(hql);
	}

	@Override
	public List<ClientAccount> getChildAccountListByParentId(String parentId) {
		String hql = "from ClientAccount where deleteFlag = '0' and type = '2' and parentId = '" + parentId + "' order by accountName";
		
		return super.getListByHql(hql);
	}

	
}
