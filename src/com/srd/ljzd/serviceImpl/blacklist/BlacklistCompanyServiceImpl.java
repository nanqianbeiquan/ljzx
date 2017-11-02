/**   
* @Title: BlacklistCompanyServiceImpl.java 
* @Package com.srd.ljzd.serviceImpl.blacklist 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年3月29日 下午4:49:33 
* @version V1.0   
*/
package com.srd.ljzd.serviceImpl.blacklist;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.srd.ljzd.dao.blacklist.BlacklistCompanyDao;
import com.srd.ljzd.dao.thirdparty.blacklist.BlacklistDAO;
import com.srd.ljzd.entity.blacklist.BlacklistCompany;
import com.srd.ljzd.entity.client.ClientAccount;
import com.srd.ljzd.service.blacklist.BlacklistCompanyService;
import com.srd.ljzd.service.client.ClientAccountService;
import com.srd.ljzd.service.monitor.MonitorCompanyService;
import com.srd.ljzd.util.Page;

/** 
 * @ClassName: BlacklistCompanyServiceImpl
 * @Description: 黑名单企业Service实现类
 * @author shiyong
 * @date 2017年3月29日 下午4:49:33
 *  
 */
@Service("blacklistCompanyService")
public class BlacklistCompanyServiceImpl implements BlacklistCompanyService {

	private static final String DELETE_FLAG = "1";//删除
	private static final String NOT_DELETE_FLAG = "0";//未删除，
	@Autowired
	BlacklistCompanyDao blacklistCompanyDao;
	@Autowired
	BlacklistDAO blacklistDAO;
	
	@Autowired
	private MonitorCompanyService monitorCompanyService;
	
	@Autowired
	private ClientAccountService clientAccountService;
	
	@Autowired
	private HttpSession session;
	
	@Override
	public int getBlacklistCompanyNumByCompany(String companyId) {
		return blacklistCompanyDao.getBlacklistCompanyNumByCompany(companyId);
	}
	
	@Override
	public Long getBlacklistCompanyNum(String accountId) {
		return blacklistCompanyDao.getBlacklistCompanyNum(accountId);
	}
	@Override
	public Page<Map<String,Object>> getBlacklistCompanyByPage(String accountId,Integer currentPageNo,Integer pageSize) {
		
		 Page<BlacklistCompany> tempPage = blacklistCompanyDao.getBlacklistCompanyByPage(accountId, currentPageNo, pageSize);
		 return parseTargetPage(tempPage);
	}
	@Override
	public Page<Map<String, Object>> getBlacklistCompanyByPageWithCompanyId(String companyId,
			Integer pageNo, Integer pageSize) {
		Page<BlacklistCompany> tempPage = blacklistCompanyDao.getBlacklistCompanyByPageWithCompanyId(companyId, pageNo, pageSize);
		return parseTargetPage(tempPage);
	}
	
	private Page<Map<String, Object>> parseTargetPage(Page<BlacklistCompany> tempPage){
		if(tempPage!=null&&tempPage.getRecordSum()>0){
			 Page<Map<String, Object>> targetPage  = new Page<Map<String, Object>>(tempPage.getPageSize(), tempPage.getRecordSum()
					 , tempPage.getPageSum(), tempPage.getCurrentPageNo(), tempPage.getBeginIndex()
					 , tempPage.isHasPrePage(), tempPage.isHasNextPage());
			 List<Map<String,Object>> targetList = new ArrayList<Map<String,Object>>();
			 Map<String,Object> item = null;
			 
			 ClientAccount loginAccount = (ClientAccount)session.getAttribute("account");
			 
			 for(BlacklistCompany bc : tempPage.getResults()){
				 item = new HashMap<String, Object>();
				 item.put("companyName", bc.getCompanyName());
				 item.put("accountName", bc.getAccount().getAccountName());
				 item.put("addReason", bc.getAddReason());
				 item.put("otherAddReason", bc.getOtherAddReason());
				 item.put("createTime", bc.getCreateTime());
				 item.put("addDate", bc.getAddDate());
				 if(loginAccount==null||!loginAccount.getAccountId().equals(bc.getAccount().getAccountId())){
					 item.put("showActionBtnFlag", false);
				 }else{
					 item.put("showActionBtnFlag", true);
				 }
				 targetList.add(item);
			 }
			 targetPage.setResults(targetList);
			 return targetPage;
		 }
		
		return null;
	}
	
	@Override
	public Map<String,Object> addToBlacklist(String accountId, String companyName,
			String reason, String otherReason) {
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("isSuccess", true);
		result.put("msg", "加入成功。");
		BlacklistCompany company = blacklistCompanyDao.getBlacklistCompany(accountId, companyName);
		if(company!=null){
			result.put("isSuccess", false);
			result.put("msg", "加入失败，该公司已经在黑名单中。");
			result.put("data", "");
		}else{
			ClientAccount account = clientAccountService.getClientAccountInfoById(accountId);
			company = new BlacklistCompany();
			company.setAccount(account);
			company.setCompanyName(companyName);
			company.setAddReason(reason);
			if(otherReason!=null&&"".equals(otherReason)){
				otherReason = null;
			}
			company.setOtherAddReason(otherReason);
			company.setDeleteFlag(BlacklistCompanyServiceImpl.NOT_DELETE_FLAG);
			company.setLastModifiedTime(Calendar.getInstance().getTime());
			company.setCreateTime(Calendar.getInstance().getTime());
			company.setAddDate(Calendar.getInstance().getTime());
			boolean flag = blacklistCompanyDao.save(company);
			if(!flag){
				result.put("isSuccess", false);
				result.put("msg", "加入失败，数据库错误。");
				result.put("data", company);
			}else{
				//添加失信标签
				monitorCompanyService.addBlacklistFlag(accountId, companyName);
			}
		}
		
		return result;
	}

	@Override
	public Map<String,Object> removeFromBlacklist(String accountId, String companyName,
			String reason, String otherReason) {
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("isSuccess", true);
		result.put("msg", "移出成功。");
		BlacklistCompany company = blacklistCompanyDao.getBlacklistCompany(accountId, companyName);
		if(company==null){
			result.put("isSuccess", false);
			result.put("msg", "移出失败，该公司不在黑名单中。");
		}else{
			company.setRemoveReason(reason);
			company.setOtherRemoveReason(otherReason);
			company.setDeleteFlag(BlacklistCompanyServiceImpl.DELETE_FLAG);
			company.setLastModifiedTime(Calendar.getInstance().getTime());
			company.setRemoveDate(Calendar.getInstance().getTime());
			boolean flag = blacklistCompanyDao.update(company);
			
			if(!flag){
				result.put("isSuccess", false);
				result.put("msg", "移出失败，数据库错误。");
				result.put("data",company);
			}else{
				//删除失信标签
				Map<String, Object> map = checkSystemBlacklist(companyName, "0");
				
				if("false".equals(map.get("systemBlacklistFlag").toString())){
					monitorCompanyService.deleteBlacklistFlag(accountId, companyName);
				}
			}
		}
		
		return result;
	}
	/**
	 * 检查是否在用户黑名单中
	 */
	@Override
	public BlacklistCompany checkCustomBlacklist(String accountId,String companyName) {
		return blacklistCompanyDao.getBlacklistCompany(accountId, companyName);
	}
	/**
	 * 检查是否在系统黑名单中，调用接口
	 */
	@Override
	public Map<String, Object> checkSystemBlacklist(String companyName,String cnt) {
		Map<String,Object> target = new HashMap<String, Object>();
		
		JSONObject result = blacklistDAO.checkSystemBlacklist(companyName,cnt);
		if(!StringUtils.isEmpty(result)
				&&result.containsKey("flag")){
			target.put("systemBlacklistFlag", Boolean.valueOf((String)result.get("flag")));
			target.put("message", result);
		}else{
			target.put("systemBlacklistFlag", false);
			target.put("message", null);
		}
		
		return target;
	}
	

	
	
}
