package com.srd.ljzd.serviceImpl.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srd.ljzd.dao.client.ClientAccountDao;
import com.srd.ljzd.dao.client.ClientCompanyDao;
import com.srd.ljzd.dao.monitor.MonitorCompanyDao;
import com.srd.ljzd.entity.client.ClientAccount;
import com.srd.ljzd.entity.client.ClientAccountTreeNode;
import com.srd.ljzd.entity.client.ClientCompany;
import com.srd.ljzd.entity.common.ResultBean;
import com.srd.ljzd.service.client.ClientAccountRightService;
import com.srd.ljzd.service.client.ClientAccountService;
import com.srd.ljzd.service.log.OperationLogService;
import com.srd.ljzd.service.monitor.MonitorDefaultCompanyService;
import com.srd.ljzd.service.monitor.MonitorUserConfigureService;
import com.srd.ljzd.service.sys.EmailService;
import com.srd.ljzd.service.sys.ShortMessageService;
import com.srd.ljzd.util.DateUtils;
import com.srd.ljzd.util.MD5Util;
import com.srd.ljzd.util.StringUtils;

@Service("clientAccountService")
public class ClientAccountServiceImpl implements ClientAccountService{
	
	@Autowired
	private ClientAccountDao clientAccountDao;
	
	@Autowired
	private ClientCompanyDao clientCompanyDao;
	
	@Autowired
	private ClientAccountRightService clientAccountRightService;
	
	@Autowired
	private MonitorUserConfigureService monitorUserConfigureService;
	
	@Autowired
	private MonitorDefaultCompanyService monitorDefaultCompanyService;
	
	@Autowired
	private ShortMessageService shortMessageService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private MonitorCompanyDao monitorCompanyDao;
	
	@Autowired
	private OperationLogService operationLogService;
	
	@Autowired
	private HttpSession httpSession;
	
	@Override
	public ClientAccount getAccountByName(String accountName) {
		
		return clientAccountDao.getAccountByName(accountName);
	}
	@Override
	public ClientAccount getMotherAccount(String companyId) {
		// TODO Auto-generated method stub
		return clientAccountDao.getMotherAccount(companyId);
	}
	
	@Override
	public ClientAccount getParentAccount(String accountId) {
		
		ClientAccount parentAccount = null;
		
		ClientAccount account = clientAccountDao.get(accountId);
		
		if(null != account && StringUtils.isNotEmpty(account.getParentId())){
			parentAccount = clientAccountDao.get(account.getParentId());
		}
		
		return parentAccount;
	}
	
	@Override
	public ClientAccount getClientAccountById(String accountId) {
		ClientAccount temp = clientAccountDao.get(accountId);
		
		if(null != temp){
//			ClientAccount accountRight = clientAccountRightService.getClientAccountRight(accountId);
//			
//			temp.setRights(accountRight.getRights());
//			
//			if(temp.getRights().contains("canCreateChild")){
//				temp.setCanCreateChild("1");
//			}else{
//				temp.setCanCreateChild("0");
//			}

			temp.setCanCreateChild("1");
		}
		
		return temp;
	}
	
	@Override
	public ClientAccount getClientAccountInfoById(String accountId) {
		
		return clientAccountDao.get(accountId);
	}
	
	@Override
	public List<ClientAccount> getAccountListAllByCompanyId(String companyId) {
		
		
		return clientAccountDao.getAccountListAllByCompanyId(companyId);
		
	}
	@Override
	public ClientAccount getAccountByMobilePhone(String mobilePhone) {
		return clientAccountDao.getAccountByMobilePhone(mobilePhone);
	}
	@Override
	public ClientAccount getAccountByEmail(String email) {
		return clientAccountDao.getAccountByEmail(email);
	}
	
	@Override
	public ResultBean saveClientAccount(ClientAccount clientAccount) {
		ResultBean result = new ResultBean();
		
		//判断账号名、手机号、邮箱是否存在
		boolean flag = clientAccountDao.isHaveSameAccountName(clientAccount.getAccountName());
		
		if(flag){
			result.setResultCode("1");
			result.setResultMsg("账户名已存在！");
			
			return result;
		}
		
		if(StringUtils.isNotEmpty(clientAccount.getMobilePhone())){
			flag = clientAccountDao.isHaveSameMobilePhone(clientAccount.getMobilePhone());
			
			if(flag){
				result.setResultCode("1");
				result.setResultMsg("手机号已存在！");
				
				return result;
			}
		}
		
		if(StringUtils.isNotEmpty(clientAccount.getEmail())){
			flag = clientAccountDao.isHaveSameEmail(clientAccount.getEmail());
			
			if(flag){
				result.setResultCode("1");
				result.setResultMsg("邮箱已存在！");
				
				return result;
			}
		}
		
		if(!flag){
			ClientAccount parentAccount = clientAccountDao.get(clientAccount.getParentId());
			
			//判断母账号是否有创建权限，是否有创建额度
			if(parentAccount.getChildNum() > parentAccount.getUsedChildNum()){
				//如果创建的是子账号，则要判断监控额度
				if("2".equals(clientAccount.getType())){
					//判断分配监控额度是否小于等于母账号的可用额度
					if(clientAccount.getMonitorNum() <= (parentAccount.getMonitorNum() - parentAccount.getUsedMonitorNum())){
						flag = true;
					}else{
						flag = false;
						
						result.setResultCode("1");
						result.setResultMsg("剩余监控额度不足！");
					}
					
					if(flag){
						//判断子账号创建额度
						if((clientAccount.getChildNum() + 1) <= (parentAccount.getChildNum() - parentAccount.getUsedChildNum())){
							flag = true;
							
							clientAccount.setRights("canCreateChild");
							
							//从母账号创建账号额度中扣除
							parentAccount.setChildNum(parentAccount.getChildNum() - clientAccount.getChildNum());
						}else{
							flag = false;
							
							result.setResultCode("1");
							result.setResultMsg("剩余子账号数量不足！");
						}
					}
				}else{
					flag = true;
					
					clientAccount.setMonitorNum(0);
				}
			}else{
				flag = false;
				
				result.setResultCode("1");
				result.setResultMsg("你没有创建子账号的权限或子账号创建额度不足！");
			}
			
			if(flag){
				//用户密码加密
				String password = clientAccount.getPassword();
				clientAccount.setPassword(MD5Util.GetMD5Code(password));
				
				flag = clientAccountDao.save(clientAccount);
				
				if(flag){
				    
					ClientAccount loginAccount = (ClientAccount)httpSession.getAttribute("account");
					//保存用户登录记录
					operationLogService.save(loginAccount.getAccountId(),loginAccount.getAccountName()
				        		,"新增账户","目标账户："+clientAccount.getAccountName(),DateUtils.getCurrentDateStr(DateUtils.DEFAULT_PATTERN));
					
					result.setResultCode("0");
					result.setResultMsg("新增账号成功！");
					
					//更新session中的子账号列表
					List<ClientAccount> childAccountList = this.getChildAccountListByParentId(loginAccount.getAccountId());
					
					this.updateChildAccountParamListInSession(childAccountList);
					
					//扣除母账号的可用额度
					parentAccount.setUsedChildNum(parentAccount.getUsedChildNum() + 1);
					parentAccount.setMonitorNum(parentAccount.getMonitorNum() - clientAccount.getMonitorNum());
					
					clientAccountDao.update(parentAccount);
					
					//新增客户成功后，保存用户权限
//					result = clientAccountRightService.saveClientAccountRight(clientAccount);
					
					//如果账号是子账号，则初始化监控信息，附属账号不需要初始化监控配置信息
					if("2".equals(clientAccount.getType())){
						//初始化动态监控用户设置
						monitorUserConfigureService.initMonitorUserConfigure(clientAccount.getAccountId());
						
						//初始化默认动态监控分组
						monitorDefaultCompanyService.initDefaultGroupAndCompany(clientAccount.getAccountId());
					}
					
					//向客户发送账号密码
					String msg = "账户名称：" + clientAccount.getAccountName()+ ",初始密码：" + password + "。初始密码有泄露风险，请尽快登录系统修改密码。";
					
					if(StringUtils.isNotEmpty(clientAccount.getMobilePhone())){
						shortMessageService.sendShortMessage(clientAccount.getMobilePhone(), msg);
					}
					
					if(StringUtils.isNotEmpty(clientAccount.getEmail())){
						emailService.sendEmail(Arrays.asList(clientAccount.getEmail()), msg, "风险雷达账户开通通知");
					}
				}else{
					result.setResultCode("1");
					result.setResultMsg("新增账号失败！");
				}
			}
		}else{
			result.setResultCode("1");
			result.setResultMsg("账号已存在！");
		}
		
		return result;
	}
	
	@Override
	public ResultBean updateClientAccount(ClientAccount clientAccount) {
		ResultBean result = new ResultBean();
		
		//判断账号名、手机号、邮箱是否存在
		boolean flag = clientAccountDao.isHaveSameAccountNameExceptItself(clientAccount.getAccountId(), clientAccount.getAccountName());
		
		if(flag){
			result.setResultCode("1");
			result.setResultMsg("账户名已存在！");
			
			return result;
		}
		
		if(StringUtils.isNotEmpty(clientAccount.getMobilePhone())){
			flag = clientAccountDao.isHaveSameMobilePhoneExceptItself(clientAccount.getAccountId(), clientAccount.getMobilePhone());
			
			if(flag){
				result.setResultCode("1");
				result.setResultMsg("手机号已存在！");
				
				return result;
			}
		}
		
		if(StringUtils.isNotEmpty(clientAccount.getEmail())){
			flag = clientAccountDao.isHaveSameEmailExceptItself(clientAccount.getAccountId(), clientAccount.getEmail());
			
			if(flag){
				result.setResultCode("1");
				result.setResultMsg("邮箱已存在！");
				
				return result;
			}
		}
		
		if(!flag){
			//从数据库中获取账号信息
			ClientAccount tempClientAccount = clientAccountDao.get(clientAccount.getAccountId());
			
			ClientAccount parentAccount = clientAccountDao.get(tempClientAccount.getParentId());
			
			//如果账号类型是子账号，则要判断当前设置的监控数量是否大于等于该账号已用的数量，子账号的创建数量是否大于等于该账号已用的数量
			if("2".equals(tempClientAccount.getType())){
				if(clientAccount.getMonitorNum() >= tempClientAccount.getUsedMonitorNum()){
					flag = true;
				}else{
					flag = false;
					
					result.setResultCode("1");
					result.setResultMsg("分配监控额度小于已使用的额度");
				}
				
				if(flag){
					//判断子账号的创建数量是否大于等于该账号已用的数量
					if(clientAccount.getChildNum() >= tempClientAccount.getUsedChildNum()){
						flag = true;
					}else{
						flag = false;
						
						result.setResultCode("1");
						result.setResultMsg("分配账户创建额度小于已使用的额度！");
					}
				}
				
				if(flag){
					//判断分配监控额度是否小于等于母账号的可用额度
					if((clientAccount.getMonitorNum() - tempClientAccount.getMonitorNum()) <= (parentAccount.getMonitorNum() - parentAccount.getUsedMonitorNum())){
						flag = true;
						
						//扣除或返还母账号的可用额度
						parentAccount.setMonitorNum(parentAccount.getMonitorNum() - (clientAccount.getMonitorNum() - tempClientAccount.getMonitorNum()));
						
						tempClientAccount.setMonitorNum(clientAccount.getMonitorNum());
					}else{
						flag = false;
						
						result.setResultCode("1");
						result.setResultMsg("剩余监控额度不足！");
					}
				}
				
				if(flag){
					//判断子账号创建额度
					if(clientAccount.getChildNum() <= (parentAccount.getChildNum() + tempClientAccount.getChildNum() - parentAccount.getUsedChildNum())){
						flag = true;
						
						//扣除或返还母账号的可用额度
						parentAccount.setChildNum(parentAccount.getChildNum() - (clientAccount.getChildNum() - tempClientAccount.getChildNum()));
						
						tempClientAccount.setChildNum(clientAccount.getChildNum());
					}else{
						flag = false;
						
						result.setResultCode("1");
						result.setResultMsg("剩余子账号数量不足！");
					}
				}
			}else{
				flag = true;
			}
			
			if(flag){
				tempClientAccount.setAccountName(clientAccount.getAccountName());
				tempClientAccount.setName(clientAccount.getName());
				tempClientAccount.setMobilePhone(clientAccount.getMobilePhone());
				tempClientAccount.setEmail(clientAccount.getEmail());
				tempClientAccount.setRemark(clientAccount.getRemark());
				tempClientAccount.setModifierId(clientAccount.getModifierId());
				tempClientAccount.setModifierName(clientAccount.getModifierName());
				tempClientAccount.setModifyTime(clientAccount.getModifyTime());
				
				flag = clientAccountDao.update(tempClientAccount);
				
				if(flag){
				       
					ClientAccount loginAccount = (ClientAccount)httpSession.getAttribute("account");
				    
					//保存用户登录记录
				   	operationLogService.save(loginAccount.getAccountId(),loginAccount.getAccountName()
				        		,"修改账户","目标账户："+tempClientAccount.getAccountName(),DateUtils.getCurrentDateStr(DateUtils.DEFAULT_PATTERN));
					result.setResultCode("0");
					result.setResultMsg("编辑账号成功！");
					
					//更新母账户额度
					clientAccountDao.update(parentAccount);
					
					//修改客户成功后，保存用户权限
//					result = clientAccountRightService.updateClientAccountRight(tempClientAccount);
				}else{
					result.setResultCode("1");
					result.setResultMsg("编辑账号失败！");
				}
			}
		}else{
			result.setResultCode("1");
			result.setResultMsg("账号已存在！");
		}
		
		return result;
	}
	
	@Override
	public ResultBean updateClientAccountInfo(ClientAccount clientAccount) {
		
		boolean flag = clientAccountDao.update(clientAccount);
		
		ResultBean result = new ResultBean();
		
		if(flag){
			result.setResultCode("0");
			result.setResultMsg("更新账号成功！");
		}else{
			result.setResultCode("0");
			result.setResultMsg("更新账号成功！");
		}
		
		return result;
	}
	
	@Override
	public ResultBean disableClientAccount(String accountId, ClientAccount modifier) {
		ResultBean result = new ResultBean();
		StringBuffer targetAccountNames = new StringBuffer("目标账户：");
		List<ClientAccount> clientAccountList = this.getAllClientAccountListByParentId(accountId);
		
		//禁用当前账号
		ClientAccount clientAccount = clientAccountDao.get(accountId);
		
		if(null != clientAccount){
			clientAccount.setModifierId(modifier.getAccountId());
			clientAccount.setModifierName(modifier.getAccountName());
			clientAccount.setModifyTime(DateUtils.getCurrentDate());
			clientAccount.setStatus("1");
			
			boolean flag = clientAccountDao.update(clientAccount);
			
			//禁用所有子账号和附属账号
			if(flag){
			        targetAccountNames.append(clientAccount.getAccountName()).append(" ");
				ClientAccount tempAccount = null;
				
				for (ClientAccount chlidAccount : clientAccountList) {
					//当前账号已禁用，无需再禁用
					if(chlidAccount.getAccountId().equals(accountId)){
						continue;
					}
					
					tempAccount = clientAccountDao.get(chlidAccount.getAccountId());
					tempAccount.setModifierId(modifier.getAccountId());
					tempAccount.setModifierName(modifier.getAccountName());
					tempAccount.setModifyTime(DateUtils.getCurrentDate());
					tempAccount.setStatus("1");
					targetAccountNames.append(tempAccount.getAccountName()).append(" ");
					flag = clientAccountDao.update(tempAccount);
					
					if(!flag){
						break;
					}
				}
			}
			
			if(flag){
			        ClientAccount loginAccount = (ClientAccount)httpSession.getAttribute("account");
				//保存用户登录记录
			        operationLogService.save(loginAccount.getAccountId(),loginAccount.getAccountName()
			        		,"禁用账户",targetAccountNames.toString(),DateUtils.getCurrentDateStr(DateUtils.DEFAULT_PATTERN));
				
				result.setResultCode("0");
				result.setResultMsg("禁用账号成功！");
			}else{
				result.setResultCode("1");
				result.setResultMsg("禁用账号失败！");
			}
		}else{
			result.setResultCode("1");
			result.setResultMsg("禁用账号失败！");
		}
		
		return result;
	}
	
	@Override
	public ResultBean enableClientAccount(String accountId, ClientAccount modifier) {
		ResultBean result = new ResultBean();
		
		//启用当前账号
		ClientAccount clientAccount = clientAccountDao.get(accountId);
		
		if(null != clientAccount){
			clientAccount.setModifierId(modifier.getAccountId());
			clientAccount.setModifierName(modifier.getAccountName());
			clientAccount.setModifyTime(DateUtils.getCurrentDate());
			clientAccount.setStatus("0");
			
			boolean flag = clientAccountDao.update(clientAccount);
			
			if(flag){
			        ClientAccount loginAccount = (ClientAccount)httpSession.getAttribute("account");
				//保存用户登录记录
			        operationLogService.save(loginAccount.getAccountId(),loginAccount.getAccountName()
			        		,"启用账户","目标账户："+clientAccount.getAccountName(),DateUtils.getCurrentDateStr(DateUtils.DEFAULT_PATTERN));
				result.setResultCode("0");
				result.setResultMsg("启用账号成功！");
			}else{
				result.setResultCode("1");
				result.setResultMsg("启用账号失败！");
			}
		}else{
			result.setResultCode("1");
			result.setResultMsg("启用账号失败！");
		}
		
		return result;
	}
	
	@Override
	public ResultBean deleteClientAccount(String accountId, ClientAccount modifier) {
		ResultBean result = new ResultBean();
		
		//判断账号是否存在
		ClientAccount clientAccount = clientAccountDao.get(accountId);
		
		if(null != clientAccount){
			//判断是否存在子账号和附属账号
			boolean flag = clientAccountDao.isHaveChildAndAffiliatedAccount(accountId);
			
			if(!flag){
				ClientAccount parentAccount = clientAccountDao.get(clientAccount.getParentId());
				
				clientAccount.setModifierId(modifier.getAccountId());
				clientAccount.setModifierName(modifier.getAccountName());
				clientAccount.setModifyTime(DateUtils.getCurrentDate());
				clientAccount.setDeleteFlag("1");
				
				flag = clientAccountDao.update(clientAccount);
				
				if(flag){
				        ClientAccount loginAccount = (ClientAccount)httpSession.getAttribute("account");
					//保存用户登录记录
				        operationLogService.save(loginAccount.getAccountId(),loginAccount.getAccountName()
				        		,"删除账户","目标账户："+clientAccount.getAccountName(),DateUtils.getCurrentDateStr(DateUtils.DEFAULT_PATTERN));
					
					result.setResultCode("0");
					result.setResultMsg("删除账号成功！");
					
//					result = clientAccountRightService.deleteClientAccountRight(accountId);
					
					//将删除账号的额度返回给母账号
					parentAccount.setChildNum(parentAccount.getChildNum() + clientAccount.getChildNum());
					parentAccount.setUsedChildNum(parentAccount.getUsedChildNum() - 1);
					parentAccount.setMonitorNum(parentAccount.getMonitorNum() + clientAccount.getMonitorNum());
					parentAccount.setUsedMonitorNum(parentAccount.getUsedMonitorNum() + clientAccount.getUsedMonitorNum());
					
					clientAccountDao.update(parentAccount);
					
					//更新session中的子账号列表
					List<ClientAccount> childAccountList = this.getChildAccountListByParentId(loginAccount.getAccountId());
					
					this.updateChildAccountParamListInSession(childAccountList);
				}else{
					result.setResultCode("1");
					result.setResultMsg("删除账号失败！");
				}
			}else{
				result.setResultCode("1");
				result.setResultMsg("包含子账号或附属账号，不能删除！");
			}
		}else{
			result.setResultCode("1");
			result.setResultMsg("删除账号失败！");
		}
		
		return result;
	}
	
	@Override
	public ResultBean getClientAccountTree(String accountId) {
		ResultBean result = new ResultBean();
		
		ClientAccount parent = clientAccountDao.get(accountId);
		
		List<ClientAccount> clientAccountList = clientAccountDao.getAccountListAllByCompanyId(parent.getClientCompany().getCompanyId());
		
		if(null != clientAccountList && clientAccountList.size() > 0){
			result.setResultCode("0");
			result.setResultMsg("获取账号列表成功！");
			
			Map<String, Object> map = new HashMap<String, Object>();
			List<ClientAccountTreeNode> clientAccountTrees = new ArrayList<ClientAccountTreeNode>();
			
			int childNum = parent.getChildNum() + 1;//母账号本身算一个
			int usedChildNum = 1;//母账号本身算一个
			
			for (ClientAccount clientAccount : clientAccountList) {
				if(accountId.equals(clientAccount.getParentId())){
					childNum += clientAccount.getChildNum();
					usedChildNum += clientAccount.getChildNum() + 1;
				}
			}
			
			//先生成根节点
			for (ClientAccount clientAccount : clientAccountList) {
				if(accountId.equals(clientAccount.getAccountId())){
					ClientAccountTreeNode treeNode = new ClientAccountTreeNode();
					treeNode.setId(clientAccount.getAccountId());
					treeNode.setpId(clientAccount.getParentId());
					treeNode.setName(clientAccount.getAccountName());
					treeNode.setType(clientAccount.getType());
					treeNode.setRemainingMonitorNum(clientAccount.getMonitorNum() - clientAccount.getUsedMonitorNum());
					treeNode.setRemainingChildNum(clientAccount.getChildNum() - clientAccount.getUsedChildNum());
					
					//母账号的子账户数量设置为总账户数量
					treeNode.setChildNum(childNum);
					treeNode.setUsedChildNum(usedChildNum);
					
					treeNode.setAccounts(new ArrayList<ClientAccountTreeNode>());
					treeNode.setAttach(new ArrayList<ClientAccountTreeNode>());
					
					clientAccountTrees.add(findChildClientAccount(treeNode, clientAccountList));
				}
			}
			
			map.put("accounts", clientAccountTrees);
			
			result.setResultData(map);
		}else{
			result.setResultCode("1");
			result.setResultMsg("获取账号列表失败！");
		}
		
		return result;
	}
	
	@Override
	public ClientAccountTreeNode findChildClientAccount(ClientAccountTreeNode treeNode, List<ClientAccount> clientAccountList) {
        
		ClientAccountTreeNode childTreeNode = null;
		
		for (ClientAccount account : clientAccountList) {
            if(treeNode.getId().equals(account.getParentId())){
            	childTreeNode = new ClientAccountTreeNode();
            	
            	childTreeNode.setId(account.getAccountId());
            	childTreeNode.setpId(account.getParentId());
            	childTreeNode.setName(account.getAccountName());
            	childTreeNode.setType(account.getType());
            	childTreeNode.setRemainingMonitorNum(account.getMonitorNum() - account.getUsedMonitorNum());
            	childTreeNode.setChildNum(account.getChildNum()+1);
            	childTreeNode.setUsedChildNum(account.getUsedChildNum()+1);
            	childTreeNode.setRemainingChildNum(account.getChildNum() - account.getUsedChildNum());
            	childTreeNode.setAccounts(new ArrayList<ClientAccountTreeNode>());
            	childTreeNode.setAttach(new ArrayList<ClientAccountTreeNode>());
            	
            	if("2".equals(account.getType())){//子账号
            		treeNode.getAccounts().add(findChildClientAccount(childTreeNode, clientAccountList));
            	}else if("3".equals(account.getType())){//附属账号
            		treeNode.getAttach().add(findChildClientAccount(childTreeNode, clientAccountList));
            	}
            }
        }
        
        return treeNode;
    }
	
	@Override
	public List<ClientAccount> getClientAccountListByParentId(String parentId) {
		
		return clientAccountDao.getClientAccountListByParentId(parentId);
	}
	
	@Override
	public List<ClientAccount> getChildAccountListByParentId(String parentId) {
		
		return clientAccountDao.getChildAccountListByParentId(parentId);
	}
	
	@Override
	public List<ClientAccount> getAllClientAccountListByParentId(String parentId) {
		ClientAccount parent = clientAccountDao.get(parentId);
		
		List<ClientAccount> accountList = clientAccountDao.getAccountListAllByCompanyId(parent.getClientCompany().getCompanyId());
				
		List<ClientAccount> tempAccountList = new ArrayList<ClientAccount>();
		
		StringBuffer parentIds = new StringBuffer(parentId);
		
		//多次遍历账号树，获取所有子节点
		if(null != accountList){
			for(int i=0;i<accountList.size();i++){
				for (ClientAccount clientAccount : accountList) {
					if(parentIds.toString().contains(clientAccount.getParentId()) && StringUtils.isNotEmpty(clientAccount.getParentId())){
						if(!tempAccountList.contains(clientAccount)){
							tempAccountList.add(clientAccount);
							
							parentIds.append("," + clientAccount.getAccountId());
						}
					}
				}
			}
		}
		
		//添加父节点
		tempAccountList.add(parent);
		
		return tempAccountList;
	}

	@Override
	public Map<String, Object> getAccountQuotasByParentId(String parentId) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<ClientAccount> accountList = this.getAllClientAccountListByParentId(parentId);
		
		int childNum = 1;//母账号本身算一个
		int remainingChildNum = 0;
		int usedChildNum = 1;//母账号本身算一个
		int monitorNum = 0;
		int remainingMonitorNum = 0;
		
		List<Map<String, Object>> accountQuotaList = new ArrayList<Map<String,Object>>();
		Map<String, Object> accountQuotaMap = new HashMap<String, Object>();
		
		for (ClientAccount clientAccount : accountList) {
			//附属账户不用加到账户额度汇总里
			if(!"3".equals(clientAccount.getType())){
				childNum += clientAccount.getChildNum();
				remainingChildNum += (clientAccount.getChildNum() - clientAccount.getUsedChildNum());
				usedChildNum += clientAccount.getUsedChildNum();
				monitorNum += clientAccount.getMonitorNum();
				remainingMonitorNum += (clientAccount.getMonitorNum() - clientAccount.getUsedMonitorNum());
				
				accountQuotaMap = new HashMap<String, Object>();
				
				accountQuotaMap.put("accountName", clientAccount.getAccountName());
				accountQuotaMap.put("monitorNum", clientAccount.getMonitorNum());
				
				accountQuotaList.add(accountQuotaMap);
			}
		}
		
		map.put("childNum", childNum);
		map.put("remainingChildNum", remainingChildNum);
		map.put("usedChildNum", usedChildNum);
		map.put("monitorNum", monitorNum);
		map.put("remainingMonitorNum", remainingMonitorNum);
		map.put("accountQuotaList", accountQuotaList);
		
		return map;
	}

	@Override
	public Map<String, Object> getMonitorQuotaByParentId(String parentId) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<ClientAccount> accountList = this.getAllClientAccountListByParentId(parentId);
		
		int monitorCompanyNum = monitorCompanyDao.queryMonitorCompanyNum(accountList);
		
		String isHaveChild = "0";
		
		ClientAccount parent = clientAccountDao.get(parentId);
		
		map.put("accountId", parent.getAccountId());
		map.put("accountName", parent.getAccountName());
		map.put("name", parent.getName());
		map.put("monitorCompanyNum", monitorCompanyNum);
		map.put("isHaveChild", isHaveChild);
		
		return map;
	}

	@Override
	public ResultBean canMonitorCompany(String accountId) {
		ResultBean result = new ResultBean();
		
		ClientAccount account = clientAccountDao.get(accountId);
		
		if(account.getMonitorNum() > account.getUsedMonitorNum()){
			result.setResultCode("0");
			result.setResultMsg("该账号可以添加监控企业！");
		}else{
			result.setResultCode("1");
			result.setResultMsg("该账号监控企业额度不足！");
		}
		
		return result;
	}

	@Override
	public ResultBean reduceMonitorNum(String accountId) {
		ResultBean result = new ResultBean();
		
		ClientAccount account = clientAccountDao.get(accountId);
		
		if(account.getMonitorNum() > account.getUsedMonitorNum()){
			account.setUsedMonitorNum(account.getUsedMonitorNum() + 1);
			account.setModifierId(account.getAccountId());
			account.setModifierName(account.getAccountName());
			account.setModifyTime(new Date());
			
			boolean flag = clientAccountDao.update(account);
			
			if(flag){
				result.setResultCode("0");
				result.setResultMsg("该账号监控额度扣除成功！");
			}else{
				result.setResultCode("1");
				result.setResultMsg("该账号监控额度扣除失败！");
			}
		}else{
			result.setResultCode("1");
			result.setResultMsg("该账号监控额度扣除失败！");
		}
		
		return result;
	}
	
	
	@Override
	public ResultBean updateClientInfo(ClientAccount clientAccount, String companyType, String contact, String position) {
		ResultBean result = new ResultBean();
		
		clientAccount.setModifierId(clientAccount.getAccountId());
		clientAccount.setModifierName(clientAccount.getAccountName());
		clientAccount.setModifyTime(new Date());
		
		boolean flag = clientAccountDao.update(clientAccount);
		
		ClientCompany clientCompany = clientAccount.getClientCompany();
		
		clientCompany.setCompanyType(companyType);
		clientCompany.setContact(contact);
		clientCompany.setPosition(position);
		
		flag = clientCompanyDao.update(clientCompany);
		
		if(flag){
			result.setResultCode("0");
			result.setResultMsg("该账号信息更新成功！");
		}else{
			result.setResultCode("1");
			result.setResultMsg("该账号信息更新失败！");
		}
		
		return result;
	}
	
	@Override
	public void updateChildAccountParamListInSession(List<ClientAccount> childAccountList) {
        List<Map<String, Object>> childAccountParamList = new ArrayList<Map<String,Object>>();
        
        Map<String, Object> childAccountParam = new HashMap<String, Object>();
        
        String isHaveChild = "0";
        
        List<ClientAccount> clientAccountList = null;
        
        for(ClientAccount childAccount : childAccountList){
        	childAccountParam = new HashMap<String, Object>();
        	
        	clientAccountList = this.getAllClientAccountListByParentId(childAccount.getAccountId());
        	
        	isHaveChild = "0";
        	
        	for (ClientAccount clientAccount : clientAccountList) {
    			if("2".equals(clientAccount.getType()) && !childAccount.getAccountId().equals(clientAccount.getAccountId())){
    				isHaveChild = "1";
    			}
    		}
        	
        	childAccountParam.put("accountId", childAccount.getAccountId());
        	childAccountParam.put("accountName", childAccount.getAccountName());
        	childAccountParam.put("isHaveChild", isHaveChild);
    		
        	childAccountParamList.add(childAccountParam);
        }
        
        httpSession.setAttribute("childAccountParamList", childAccountParamList);
        
        httpSession.setAttribute("childAccountNum", childAccountParamList.size());
	}
}
