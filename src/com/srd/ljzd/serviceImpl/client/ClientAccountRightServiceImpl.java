/**   
* @Title: ClientAccountRightServiceImpl.java 
* @Package com.srd.ljzd.serviceImpl.client 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年8月22日 下午5:41:21 
* @version V1.0   
*/
package com.srd.ljzd.serviceImpl.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.srd.ljzd.config.Global;
import com.srd.ljzd.dao.client.ClientAccountDao;
import com.srd.ljzd.entity.client.ClientAccount;
import com.srd.ljzd.entity.common.ResultBean;
import com.srd.ljzd.service.client.ClientAccountRightService;
import com.srd.ljzd.serviceImpl.base.BaseServiceImpl;
import com.srd.ljzd.util.JerseyUtil;
import com.srd.ljzd.util.LoggerUtils;
import com.srd.ljzd.util.StringUtils;

/** 
 * @ClassName: ClientAccountRightServiceImpl
 * @Description: 客户账号权限Service实现类
 * @author shiyong
 * @date 2017年8月22日 下午5:41:21
 *  
 */
@Service("clientAccountRightService")
public class ClientAccountRightServiceImpl extends BaseServiceImpl implements ClientAccountRightService {

	@Autowired
	private ClientAccountDao clientAccountDao;
	
	private String dataService = Global.getConfig("dataService");
	private String savechildAccount = Global.getConfig("saveChildAccount");
	private String updateAccountInfo = Global.getConfig("updateAccountInfo");
	private String delAccount = Global.getConfig("delAccount");
	private String getAccountInfo = Global.getConfig("getAccountInfo");
	private String getAllAccountInfo = Global.getConfig("getAllAccountInfo");
	
	@Override
	public ResultBean saveClientAccountRight(ClientAccount clientAccount) {
		ResultBean result = new ResultBean();
		
		String url = dataService + savechildAccount;
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("accountId", clientAccount.getAccountId());
		paramMap.put("userName", clientAccount.getAccountName());
		paramMap.put("access", clientAccount.getRights());
		paramMap.put("relationId", clientAccount.getParentId());
		
		//图数据库中relation不能保存为数字，所以转换为字母
		String relation = "";
		
		if("2".equals(clientAccount.getType())){
			relation = "b";
		}else{
			relation = "c";
		}
		
		paramMap.put("relation", relation);
		
		JSONObject jsonResult = JerseyUtil.sendHttpFormRequest(url, paramMap);
		
		if(jsonResult != null && jsonResult.containsKey("respCode")){
			if(jsonResult.getString("respCode").equals("200") && jsonResult.containsKey("data")){
				JSONObject data = jsonResult.getJSONObject("data");
				
				if(data.containsKey("flag") && "success".equals(data.getString("flag"))){
					result.setResultCode("0");
					result.setResultMsg("保存客户账号权限信息成功！");
				}else{
					result.setResultCode("1");
					result.setResultMsg("保存客户账号权限信息失败！");
					
					LoggerUtils.error("保存客户账号权限信息失败，大数据接口返回数据有误，data:" + jsonResult.getString("data"));
				}
			}else{
				result.setResultCode("1");
				result.setResultMsg("保存客户账号权限信息失败！");
				
				LoggerUtils.error("保存客户账号权限信息失败，大数据接口返回数据有误，respCode:" + jsonResult.getString("respCode") + ", respMsg:" + jsonResult.getString("respMsg"));
			}
		}else{
			result.setResultCode("1");
			result.setResultMsg("保存客户账号权限信息失败！");
		}
		
		return result;
	}

	@Override
	public ResultBean updateClientAccountRight(ClientAccount clientAccount) {
		ResultBean result = new ResultBean();
		
		String url = dataService + updateAccountInfo;
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("accountId", clientAccount.getAccountId());
		paramMap.put("col", "userName|access");
		paramMap.put("value", clientAccount.getAccountName() + "|" + clientAccount.getRights());
		
		JSONObject jsonResult = JerseyUtil.sendHttpFormRequest(url, paramMap);
		
		if(jsonResult != null && jsonResult.containsKey("respCode")){
			if(jsonResult.getString("respCode").equals("200") && jsonResult.containsKey("data")){
				JSONObject data = jsonResult.getJSONObject("data");
				
				if(data.containsKey("flag") && "success".equals(data.getString("flag"))){
					result.setResultCode("0");
					result.setResultMsg("更新客户账号权限信息成功！");
				}else{
					result.setResultCode("1");
					result.setResultMsg("更新客户账号权限信息失败！");
					
					LoggerUtils.error("更新客户账号权限信息失败，大数据接口返回数据有误，data:" + jsonResult.getString("data"));
				}
			}else{
				result.setResultCode("1");
				result.setResultMsg("更新客户账号权限信息失败！");
				
				LoggerUtils.error("更新客户账号权限信息失败，大数据接口返回数据有误，respCode:" + jsonResult.getString("respCode") + ", respMsg:" + jsonResult.getString("respMsg"));
			}
		}else{
			result.setResultCode("1");
			result.setResultMsg("更新客户账号权限信息失败！");
		}
		
		return result;
	}

	@Override
	public ResultBean deleteClientAccountRight(String accountId) {
		ResultBean result = new ResultBean();
		
		String url = dataService + delAccount;
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("accountId", accountId);
		
		JSONObject jsonResult = JerseyUtil.sendHttpFormRequest(url, paramMap);
		
		if(jsonResult != null && jsonResult.containsKey("respCode")){
			if(jsonResult.getString("respCode").equals("200") && jsonResult.containsKey("data")){
				JSONObject data = jsonResult.getJSONObject("data");
				
				if(data.containsKey("flag") && "success".equals(data.getString("flag"))){
					result.setResultCode("0");
					result.setResultMsg("删除客户账号权限信息成功！");
				}else{
					result.setResultCode("1");
					result.setResultMsg("删除客户账号权限信息失败！");
					
					LoggerUtils.error("删除客户账号权限信息失败，大数据接口返回数据有误，data:" + jsonResult.getString("data"));
				}
			}else{
				result.setResultCode("1");
				result.setResultMsg("删除客户账号权限信息失败！");
				
				LoggerUtils.error("删除客户账号权限信息失败，大数据接口返回数据有误，respCode:" + jsonResult.getString("respCode") + ", respMsg:" + jsonResult.getString("respMsg"));
			}
		}else{
			result.setResultCode("1");
			result.setResultMsg("删除客户账号权限信息失败！");
		}
		
		return result;
	}

	@Override
	public ClientAccount getClientAccountRight(String accountId) {
		
		ClientAccount account = new ClientAccount();
		
		String access = "";
		
		String url = dataService + getAccountInfo;
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("accountId", accountId);
		
		JSONObject jsonResult = JerseyUtil.sendHttpFormRequest(url, paramMap);
		
		if(jsonResult != null && jsonResult.containsKey("respCode")){
			if(jsonResult.getString("respCode").equals("200") && jsonResult.containsKey("data")){
				JSONObject data = jsonResult.getJSONObject("data");
				
				if(data.containsKey("results")){
					JSONArray results = data.getJSONArray("results");
					
					if(results.size() > 0){
						JSONObject result = results.getJSONObject(0);
						
						if(result.containsKey("data")){
							JSONArray relationArray = result.getJSONArray("data");
							
							if(relationArray.size() > 0){
								JSONObject relation = relationArray.getJSONObject(0);
								
								JSONObject graph = relation.getJSONObject("graph");
								
								JSONArray nodes = graph.getJSONArray("nodes");
								
								JSONObject node = nodes.getJSONObject(0);
								
								JSONObject properties = node.getJSONObject("properties");
								
								access = properties.getString("access");
							}else{
								LoggerUtils.error("获取客户账号权限失败，大数据接口返回数据有误，data为空");
							}
						}else{
							LoggerUtils.error("获取客户账号权限失败，大数据接口返回数据有误，缺少data");
						}
					}else{
						LoggerUtils.error("获取客户账号权限失败，大数据接口返回数据有误，results为空");
					}
				}else{
					LoggerUtils.error("获取客户账号权限失败，大数据接口返回数据有误，缺少results");
				}
			}else{
				LoggerUtils.error("获取客户账号权限失败，大数据接口返回数据有误，respCode:" + jsonResult.getString("respCode") + ", respMsg:" + jsonResult.getString("respMsg"));
			}
		}
		
		account.setRights(access);
		
		return account;
	}
	
	@Override
	public List<ClientAccount> getWholeClientAccountTree(String accountId) {
		List<ClientAccount> accountList = null;
		
		String url = dataService + getAllAccountInfo;
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("accountId", accountId);
		
		JSONObject jsonResult = JerseyUtil.sendHttpFormRequest(url, paramMap);
		
		if(jsonResult != null && jsonResult.containsKey("respCode")){
			if(jsonResult.getString("respCode").equals("200") && jsonResult.containsKey("data")){
				
				JSONObject data = jsonResult.getJSONObject("data");
				
				if(data.containsKey("results")){
					JSONArray results = data.getJSONArray("results");
					
					if(results.size() > 0){
						JSONObject result = results.getJSONObject(0);
						
						if(result.containsKey("data")){
							accountList = new ArrayList<ClientAccount>();
							
							ClientAccount account = new ClientAccount();
							
							//获取母账号信息
							String companyId = clientAccountDao.get(accountId).getClientCompany().getCompanyId();
							
							account = clientAccountDao.getMotherAccount(companyId);
							
							accountList.add(account);
							
							JSONArray relationArray = result.getJSONArray("data");
							
							JSONObject relation = null;
							JSONObject graph = null;
							JSONArray nodes = null;
							JSONObject node = null;
							JSONArray relationships = null;
							JSONObject relationship = null;
							String startNode = "";
							String type = "";
							String id = "";
							
							for(int i=0;i<relationArray.size();i++){
								relation = relationArray.getJSONObject(i);
								graph = relation.getJSONObject("graph");
								nodes = graph.getJSONArray("nodes");
								relationships = graph.getJSONArray("relationships");
								relationship = relationships.getJSONObject(0);
								
								startNode = relationship.getString("startNode");
								
								if("b".equals(relationship.getString("type"))){
									type = "2";
								}else{
									type = "3";
								}
								
								for(int j=0;j<nodes.size();j++){
									node = nodes.getJSONObject(j);
									
									id = node.getString("id");
									
									//获取所有子账号节点添加到list中
									if(id.equals(startNode)){
										account = new ClientAccount();
										
										account.setAccountId(node.getString("accountId"));
										account.setAccountName(node.getString("accountName"));
										account.setType(type);
										
										accountList.add(account);
										
										break;
									}
								}
							}
						}else{
							LoggerUtils.error("获取客户账号树失败，大数据接口返回数据有误，缺少data");
						}
					}else{
						LoggerUtils.error("获取客户账号树失败，大数据接口返回数据有误，results为空");
					}
				}else{
					LoggerUtils.error("获取客户账号树失败，大数据接口返回数据有误，缺少results");
				}
			}else{
				LoggerUtils.error("获取客户账号树失败，大数据接口返回数据有误，respCode:" + jsonResult.getString("respCode") + ", respMsg:" + jsonResult.getString("respMsg"));
			}
		}
		
		return accountList;
	}

	@Override
	public List<ClientAccount> getAllClientAccountListByParentId(String parentId) {
		List<ClientAccount> accountList = this.getWholeClientAccountTree(parentId);
		
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
		tempAccountList.add(clientAccountDao.get(parentId));
		
		return tempAccountList;
	}
	
	@Override
	public List<ClientAccount> getClientAccountListByParentId(String parentId) {
		
		List<ClientAccount> accountList = this.getWholeClientAccountTree(parentId);
		
		List<ClientAccount> tempAccountList = new ArrayList<ClientAccount>();
		
		StringBuffer parentIds = new StringBuffer(parentId);
		
		//多次遍历账号树，获取所有子节点
		for(int i=0;i<accountList.size();i++){
			for (ClientAccount clientAccount : accountList) {
				if(parentIds.toString().contains(clientAccount.getParentId()) ){
					if(!tempAccountList.contains(clientAccount)){
						tempAccountList.add(clientAccount);
						
						parentIds.append("," + clientAccount.getAccountId());
					}
				}
			}
		}
		
		return tempAccountList;
	}

	/* (non-Javadoc)
	 * @see com.srd.ljzd.service.client.ClientAccountRightService#getChlidAccountNum(java.lang.String)
	 */
	@Override
	public int getChlidAccountNum(String accountId) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
