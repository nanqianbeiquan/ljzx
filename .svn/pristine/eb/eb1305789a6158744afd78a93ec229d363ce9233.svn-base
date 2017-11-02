package com.srd.ljzd.dao.monitor;

import java.util.List;
import java.util.Map;

import com.srd.ljzd.dao.base.BaseDao;
import com.srd.ljzd.entity.client.ClientAccount;
import com.srd.ljzd.entity.monitor.MonitorCompany;
import com.srd.ljzd.entity.monitor.MonitorRelationCompany;
import com.srd.ljzd.util.Page;

/** 
* @ClassName: MonitorRelationCompanyDao
* @Description: 动态监控关联企业信息Dao接口
* @author shiyong
* @date 2017年5月12日 下午5:34:03
*  
*/
public interface MonitorRelationCompanyDao extends BaseDao<MonitorRelationCompany,String>{
	
	List<String> queryRelationCompanyNameByMonitorId(String monitorId);
	
	/** 
	* @Title: queryMonitorRelationCompanyListByMonitorId 
	* @Description: 根据主体企业监控id获取关系企业列表
	* @param @param monitorId
	* @param @return 设定文件 
	* @return List<MonitorRelationCompany> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月12日 下午5:32:59
	*/
	public List<MonitorRelationCompany> queryMonitorRelationCompanyListByMonitorId(String monitorId);
	
	/** 
	* @Title: getMonitorRelationCompanyPage 
	* @Description: 获取关系企业分页列表
	* @param @param accountId
	* @param @param currentPageNo
	* @param @param pageSize
	* @param @return 设定文件 
	* @return Page<MonitorRelationCompany> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月24日 上午10:45:50
	*/
	Page<MonitorRelationCompany> getMonitorRelationCompanyPage(String accountId, int currentPageNo, int pageSize);
	
	/** 
	* @Title: deleteMonitorRelationCompanyByMonitorId 
	* @Description: 根据主体企业id删除关系企业
	* @param @param monitorId
	* @param @return 设定文件 
	* @return boolean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月26日 下午4:22:19
	*/
	public boolean deleteMonitorRelationCompanyByMonitorId(String monitorId);
	
	/** 
	* @Title: deleteMonitorRelationCompanyById 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param id
	* @param @return 设定文件 
	* @return boolean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月20日 下午5:32:59
	*/
	public boolean deleteMonitorRelationCompanyById(String id);
	
	/** 
	* @Title: getRelationCompanyNum 
	* @Description: 获取关系企业数量
	* @param @param monitorCompanyList
	* @param @return 设定文件 
	* @return Map<String,Integer> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年6月13日 上午11:04:16
	*/
	public Map<String, Integer> getRelationCompanyNum(List<MonitorCompany> monitorCompanyList);
	
	/** 
	* @Title: getRelationCompanyNum 
	* @Description: 获取关系企业数量
	* @param @param accountId
	* @param @return 设定文件 
	* @return int 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年4月28日 下午2:23:40
	*/
	public int getRelationCompanyNum(String accountId);
	
	/** 
	* @Title: getTotalRelationCompanyNum 
	* @Description: 获取账号的关系企业总数
	* @param @param clientAccountList
	* @param @return 设定文件 
	* @return int 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月11日 下午8:12:19
	*/
	public int getTotalRelationCompanyNum(List<ClientAccount> clientAccountList);
	
	/** 
	* @Title: getMonitorRelationCompany 
	* @Description: 获取关联公司的信息
	* @param @param monitorId
	* @param @param companyName
	* @param @return 设定文件 
	* @return MonitorRelationCompany 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月20日 下午5:26:29
	*/
	public MonitorRelationCompany getMonitorRelationCompany(String monitorId, String companyName);
}
